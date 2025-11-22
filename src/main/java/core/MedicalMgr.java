package core;

import mgr.Factory;
import mgr.PetRecordMgr;

import java.time.LocalDate;
import java.util.ArrayList;

// TODO: 클래스 필드, 메서드 작성 (현재는 필수 구현해야 하는 메서드만 자동완성해 둔 상태)
public class MedicalMgr extends PetRecordMgr<MedicalRecord> {
    private static MedicalMgr mgr = null;

    public static MedicalMgr getInstance() {
        if (mgr == null)
            mgr = new MedicalMgr();
        return mgr;
    }

    public void addNewRecord(Pet pet, LocalDate date,
                             String hospital, String category, int cost) {
        MedicalRecord r = new MedicalRecord();
        r.apply(pet, date, hospital, category, cost);
        addWithIndexId(r);
    }

    public ArrayList<MedicalRecord> searchPeriod(LocalDate start, LocalDate end) {
        ArrayList<MedicalRecord> result = new ArrayList<>();
        for(MedicalRecord r : mList) {
            if(r.matchesPeriod(start,end))
                result.add(r);
        }
        return result;
    }

    @Override
    public void addNewRow(String[] uiTexts) {
        MedicalRecord r = new MedicalRecord();
        r.set(uiTexts);
        mList.add(r);
    }

    public void loadFromFile() {
        readAll("data/medicalRecords.txt", new Factory<>() {
            @Override
            public MedicalRecord create() {
                return new MedicalRecord();
            }
        });
        initNextIndexId();
    }
}
