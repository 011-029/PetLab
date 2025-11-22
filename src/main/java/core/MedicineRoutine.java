package core;

import facade.UIData;
import mgr.Manageable;
import mgr.PetOwned;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class MedicineRoutine implements Manageable, UIData, PetOwned {
    int indexId;     // 인덱스 번호 (고유)
    String ownerId;  // 어떤 유저의
    String petName;  // 어떤 펫의 기록인지

    String medicineName;     // 약품명
    ArrayList<String> takenDOW = new ArrayList<>(); // 복용 요일 (월, 화, ...)
    String takenTime;        // 복용 시간대 (아침, 점심, 저녁, 자기전)
    int dosage;              // 복용량 (단위: mg)
    boolean isTaken = false; // 복용 여부, 기본값 false
    int lastRecordId = -1;   // 루틴 → 기록 넘길 때 인덱스 번호 기억

    public void read(Scanner scan) {
        indexId = scan.nextInt();
        ownerId = scan.next();
        petName = scan.next();
        medicineName = scan.next();
        String dow = scan.next();
        for (int i = 0; i < dow.length(); i++)
            takenDOW.add(String.valueOf(dow.charAt(i)));
        takenTime = scan.next();
        dosage = scan.nextInt();
    }

    public void print() {
        System.out.printf("#%d / %s / %s / %s / %dmg / ",
                indexId, medicineName, takenDOW.toString(), takenTime, dosage);
        System.out.print(isTaken ? "복용 완료" : "복용 전");
        System.out.println();
    }

    public void toggleTaken() {
        // true ↔ false 변경 -> true 면 MedicineRecord 로 생성
        isTaken = !isTaken;
        MedicineRecordMgr mgr = MedicineRecordMgr.getInstance();

        if (isTaken) {
            MedicineRecord record = mgr.createFromRoutine(this);
            lastRecordId = record.getIndexId();
            System.out.printf("▶ %d번으로 Medicine Record가 생성되었습니다\n", lastRecordId);
        } else {
            if (lastRecordId != -1) {
                mgr.removeByIndexId(lastRecordId);
                System.out.printf("▶ %d번 Medicine Record가 삭제되었습니다\n", lastRecordId);
                lastRecordId = -1;
            }
        }
    }

    public MedicineRecord RoutineToRecord() {
        MedicineRecord r = new MedicineRecord();
        r.ownerId = this.ownerId;
        r.petName = this.petName;
        r.medicineName = this.medicineName;
        r.takenDate = LocalDate.now(); // 오늘
        r.takenTime = this.takenTime;
        r.dosage = this.dosage;
        return r;
    }

    public boolean matches(String kwd) {
        if (kwd.isEmpty())
            return true;
        if (takenDOW.contains(kwd))
            return true;
        return medicineName.contains(kwd) || takenTime.contains(kwd);
    }

    @Override
    public String getOwnerId() {
        return ownerId;
    }

    @Override
    public String getPetName() {
        return petName;
    }

    @Override
    public int getIndexId() {
        return indexId;
    }

    @Override
    public void setIndexId(int indexId) {
        this.indexId = indexId;
    }

    @Override
    public void set(String[] uiTexts) {

    }

    @Override
    public String[] getUITexts() {
        return new String[0];
    }
}
