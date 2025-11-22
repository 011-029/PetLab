package core;

import mgr.Factory;
import mgr.PetRecordMgr;

import java.time.LocalDate;
import java.util.ArrayList;

public class MedicineRecordMgr extends PetRecordMgr<MedicineRecord> {
    private static MedicineRecordMgr mgr = null;

    public static MedicineRecordMgr getInstance() {
        if (mgr == null)
            mgr = new MedicineRecordMgr();
        return mgr;
    }

    public ArrayList<MedicineRecord> searchPeriod(LocalDate start, LocalDate end) {
        ArrayList<MedicineRecord> result = new ArrayList<>();
        for(MedicineRecord r : mList) {
            if(r.matchesPeriod(start, end))
                result.add(r);
        }
        return result;
    }

    public MedicineRecord createFromRoutine(MedicineRoutine routine) {
        MedicineRecord record = routine.RoutineToRecord();
        addWithIndexId(record);
        return record;
    }

    @Override
    public void addNewRow(String[] uiTexts) {

    }

    public void loadFromFile() {
        readAll("data/medicineRecord.txt", new Factory<MedicineRecord>() {
            public MedicineRecord create() {
                return new MedicineRecord();
            }
        });
        initNextIndexId();
    }
}
