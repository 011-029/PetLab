package core;

import mgr.Factory;
import mgr.PetRecordMgr;

import java.time.LocalDate;
import java.util.ArrayList;

public class HealthMgr extends PetRecordMgr<HealthRecord> {
    private static HealthMgr mgr = null;
    private final String FILE_PATH = "data/health_records.txt";
    private int nextIndex = 1;


    private HealthMgr() { }

    public static HealthMgr getInstance() {
        if (mgr == null)
            mgr = new HealthMgr();
        return mgr;
    }

    public void addNewRecord(Pet pet, LocalDate date, int meal,
                             int water, String brushed, String memo) {
        HealthRecord r = new HealthRecord();
        r.apply(pet, date, meal, water, brushed, memo);
        saveWithIndexId(r);
    }

    public ArrayList<HealthRecord> searchPeriod(LocalDate start, LocalDate end) {
        ArrayList<HealthRecord> result = new ArrayList<>();
        for(HealthRecord r : mList) {
            if(r.matchesPeriod(start,end))
                result.add(r);
        }
        return result;
    }

    @Override
    public void addNewRow(String[] uiTexts) {
        HealthRecord r = new HealthRecord();
        r.set(uiTexts);
        r.indexId = nextIndex++;

        mList.add(r);

    }

    public void loadFromFile() {
        readAll(FILE_PATH, new Factory<HealthRecord>() {
            public HealthRecord create() {
                return new HealthRecord();
            }
        });
        initNextIndexId();
    }

    @Override
    protected String getFilePath() {
        return FILE_PATH;
    }
}
