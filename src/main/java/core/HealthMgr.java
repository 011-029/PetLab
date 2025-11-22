package core;

import mgr.PetRecordMgr;

import java.time.LocalDate;
import java.util.ArrayList;

// TODO: 클래스 필드, 메서드 작성 (현재는 필수 구현해야 하는 메서드만 자동완성해 둔 상태)
public class HealthMgr extends PetRecordMgr<HealthRecord> {

    private static HealthMgr mgr = null;

    private int nextIndex = 1;

    private HealthMgr() { }

    public static HealthMgr getInstance() {
        if (mgr == null)
            mgr = new HealthMgr();
        return mgr;
    }

    public ArrayList<HealthRecord> searchPeriod(LocalDate start, LocalDate end) {
        ArrayList<HealthRecord> result = new ArrayList<>();
        for(HealthRecord r : mList) {
            if(r.matchesPeriod(start,end))
                result.add(r);
        }
        return result;
    }

    public boolean deleteByIndex(int index) {
        if (index < 0 || index >= mList.size()) {
            return false;
        }
        mList.remove(index);
        return true;
    }

    @Override
    public void addNewRow(String[] uiTexts) {
        HealthRecord r = new HealthRecord();
        r.set(uiTexts);
        r.indexId = nextIndex++;

        mList.add(r);

    }

    public void loadFromFile() {
        // TODO: 건강 기록 데이터 생성 후 아래 주석 해제, 파일 경로 추가
//        readAll(".txt", new Factory<HealthRecord>() {
//            public HealthRecord create() {
//                return new HealthRecord();
//            }
//        });
    }
}
