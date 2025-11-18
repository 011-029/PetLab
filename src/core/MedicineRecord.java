package core;

import facade.UIData;
import mgr.Manageable;
import util.DateUtil;
import util.ReadUtil;

import java.time.LocalDate;
import java.util.Scanner;

public class MedicineRecord implements Manageable, UIData {
    int indexId;          // 인덱스 번호
    String medicineName;  // 약품명
    LocalDate takenDate;  // 복용일
    String takenTime;     // 복용 시간대 (아침, 점심, 저녁, 자기전)
    int dosage;           // 복용량 (단위: mg)

    public void read(Scanner scan) {
        indexId = scan.nextInt();
        medicineName = scan.next();
        takenDate = ReadUtil.readDate(scan);
        takenTime = scan.next();
        dosage = scan.nextInt();
    }

    public void print() {
        System.out.printf("#%d [%s] %s / %s / %dmg",
                indexId, takenDate, medicineName, takenTime, dosage);
        System.out.println();
    }

    public boolean matches(String kwd) {
        if (kwd.isEmpty())
            return true;
        return medicineName.contains(kwd) || takenTime.contains(kwd);
    }

    public boolean matchesPeriod(LocalDate start, LocalDate end){
        return DateUtil.matchesInPeriod(takenDate, start, end);
    }

    @Override
    public void set(String[] uiTexts) {

    }

    @Override
    public String[] getUITexts() {
        return new String[0];
    }
}
