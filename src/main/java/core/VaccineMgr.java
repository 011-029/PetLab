package core;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

import mgr.PetRecordMgr;

// 예방접종 기록 관리 클래스
// TODO: 클래스 필드, 메서드 작성 (현재는 필수 구현해야 하는 메서드만 자동완성해둔 상태)
public class VaccineMgr extends PetRecordMgr<VaccineRecord> {
    private static VaccineMgr mgr = null;

    private static final DateTimeFormatter FMT =
            DateTimeFormatter.ofPattern("yyyy-MM-dd");

    private VaccineMgr() {
        super();
    }

    public static VaccineMgr getInstance() {
        if (mgr == null) {
            mgr = new VaccineMgr();
        }
        return mgr;
    }

    @Override
    public void addNewRow(String[] uiTexts) {
        VaccineRecord rec = new VaccineRecord();
        rec.set(uiTexts);

        mList.add(rec);
    }

    public List<VaccineRecord> searchByPeriod(String from, String to) {
        LocalDate fromDate = parse(from);
        LocalDate toDate   = parse(to);

        List<VaccineRecord> result = new ArrayList<>();

        for (VaccineRecord r : mList) {
            LocalDate d = r.parseDate();
            if (d == null) continue;

            boolean ok = true;
            if (fromDate != null && d.isBefore(fromDate)) ok = false;
            if (toDate   != null && d.isAfter(toDate)) ok = false;

            if (ok) result.add(r);
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

    public void printAll() {
        for (int i = 0; i < mList.size(); i++) {
            System.out.printf("[%d] ", i);
            mList.get(i).print();
        }
    }

    private LocalDate parse(String s) {
        if (s == null || s.isBlank()) return null;
        try {
            return LocalDate.parse(s.trim(), FMT);
        } catch (DateTimeParseException e) {
            return null;
        }
    }

    public void loadFromFile() {
        // TODO: 예방접종 기록 데이터 생성 후 아래 주석 해제, 파일 경로 추가
//        readAll(".txt", new Factory<VaccineRecord>() {
//            public VaccineRecord create() {
//                return new VaccineRecord();
//            }
//        });
    }
}