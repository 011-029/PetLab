package core;

import mgr.Factory;
import mgr.PetRecordMgr;

import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.Locale;
import java.util.Scanner;

public class MedicineRoutineMgr extends PetRecordMgr<MedicineRoutine> {
    private static MedicineRoutineMgr mgr = null;
    Scanner scan = new Scanner(System.in);

    public static MedicineRoutineMgr getInstance() {
        if (mgr == null)
            mgr = new MedicineRoutineMgr();
        return mgr;
    }

    public void printTodayRoutine() {
        // 오늘(요일)에 해당하는 루틴 출력
        String todayDOW = LocalDate.now()
                .getDayOfWeek()
                .getDisplayName(TextStyle.SHORT, Locale.KOREAN);

        System.out.println("오늘: " + todayDOW);
        for (MedicineRoutine r: mList) {
            if (r.takenDOW.contains(todayDOW)) {
                r.print();
            }
        }
    }

    public void checkTaken() {
        // 복용 여부 체크기능
        int checkIndexId;
        while (true) {
            System.out.print(">> 복용 체크할 인덱스 번호(-1 입력 시 종료): ");
            checkIndexId = scan.nextInt();
            if (checkIndexId == -1) break;
            for (MedicineRoutine r: mList) {
                if (r.indexId == checkIndexId)
                    r.toggleTaken();
            }
            printTodayRoutine();
        }
    }

    @Override
    public void addNewRow(String[] uiTexts) {

    }

    public void loadFromFile() {
        readAll("data/medicineRoutine.txt", new Factory<MedicineRoutine>() {
            public MedicineRoutine create() {
                return new MedicineRoutine();
            }
        });
        initNextIndexId();
    }
}
