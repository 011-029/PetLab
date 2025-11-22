package core;

import mgr.Factory;
import mgr.PetRecordMgr;

import java.time.LocalDate;
import java.util.ArrayList;

public class WalkMgr extends PetRecordMgr<WalkRecord> {
    private static WalkMgr mgr = null;
    private static final String FILE_PATH = "data/walk_records.txt";

    public static WalkMgr getInstance() {
        if (mgr == null)
            mgr = new WalkMgr();
        return mgr;
    }

    public void addNewRecord(Pet pet, LocalDate date, int walkTime,
                             String photoPath, String memo) {
        WalkRecord r = new WalkRecord();
        r.apply(pet, date, walkTime, photoPath, memo);
        saveWithIndexId(r);
    }

    public ArrayList<WalkRecord> searchPeriod(LocalDate start, LocalDate end) {
        ArrayList<WalkRecord> result = new ArrayList<>();
        for(WalkRecord r : mList) {
            if(r.matchesPeriod(start,end))
                result.add(r);
        }
        return result;
    }

    @Override
    public void addNewRow(String[] uiTexts) {

    }

    public void loadFromFile() {
        readAll(FILE_PATH, new Factory<>() {
            @Override
            public WalkRecord create() {
                return new WalkRecord();
            }
        });
        initNextIndexId();
    }

    @Override
    protected String getFilePath() {
        return FILE_PATH;
    }
}
