package core;

import mgr.PetRecordMgr;

import java.time.LocalDate;
import java.util.ArrayList;

public class MedicineRecordMgr extends PetRecordMgr<MedicineRecord> {
    private static MedicineRecordMgr mgr = null;
    int nextIndexId = -1;   // 루틴 → 기록 넘길 시 자동 배정될 인덱스 번호

    public static MedicineRecordMgr getInstance() {
        if (mgr == null)
            mgr = new MedicineRecordMgr();
        return mgr;
    }

    public void initNextIndexId() {
        int max = 0;
        for (MedicineRecord r: mList) {
            if (r.indexId > max) {
                max = r.indexId;
            }
        }
        nextIndexId = max + 1;
    }

    public int generateIndexId() {
        return nextIndexId++;
    }

    public ArrayList<MedicineRecord> searchPeriod(LocalDate start, LocalDate end) {
        ArrayList<MedicineRecord> result = new ArrayList<>();
        for(MedicineRecord r : mList) {
            if(r.matchesPeriod(start, end))
                result.add(r);
        }
        return result;
    }

    public void removeById(int id) {
        // mList 안에서 indexId == id인 요소 삭제
        mList.removeIf(r -> r.indexId == id);
    }

    @Override
    public void addNewRow(String[] uiTexts) {

    }
}
