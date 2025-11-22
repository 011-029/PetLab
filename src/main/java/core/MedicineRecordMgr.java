package core;

import mgr.Factory;
import mgr.PetRecordMgr;

import java.time.LocalDate;
import java.util.ArrayList;

public class MedicineRecordMgr extends PetRecordMgr<MedicineRecord> {
    private static MedicineRecordMgr mgr = null;
    private final String FILE_PATH = "data/medicineRecord.txt";

    public static MedicineRecordMgr getInstance() {
        if (mgr == null)
            mgr = new MedicineRecordMgr();
        return mgr;
    }

    public void addNewRecord(Pet pet, String medicineName,
                             LocalDate takenDate, String takenTime, int dosage) {
        MedicineRecord r = new MedicineRecord();
        r.apply(pet, medicineName, takenDate, takenTime, dosage);
        saveWithIndexId(r);
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
        saveWithIndexId(record);
        return record;
    }

    @Override
    public void addNewRow(String[] uiTexts) {

    }

    public void loadFromFile() {
        readAll(FILE_PATH, new Factory<MedicineRecord>() {
            public MedicineRecord create() {
                return new MedicineRecord();
            }
        });
        initNextIndexId();
    }

    @Override
    protected String getFilePath() {
        return FILE_PATH;
    }
}
