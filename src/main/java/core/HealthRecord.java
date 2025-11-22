package core;

import facade.UIData;
import mgr.Manageable;
import mgr.PetOwned;
import util.DateUtil;
import util.ReadUtil;

import java.time.LocalDate;
import java.util.Scanner;

public class HealthRecord implements Manageable, UIData, PetOwned {
    int indexId;     // 인덱스 번호 (고유)
    String ownerId;  // 어떤 유저의
    String petName;  // 어떤 펫의 기록인지
    LocalDate date;
    int meal;
    int water;
    String brushed;
    String memo;

    public HealthRecord() {}

    public void read(Scanner scan) {

        indexId = scan.nextInt();
        ownerId = scan.next();
        petName = scan.next();

        date = ReadUtil.readDate(scan);
        meal = scan.nextInt();
        water = scan.nextInt();
        brushed = scan.next();
        memo = "";
        if (scan.hasNextLine()) {
            memo = scan.nextLine().trim();
        }
    }

    public void print() {
        System.out.printf("[%d] %s | 식사:%d 음수:%d 양치:%s | %s%n",
                indexId, date, meal, water,
                brushed != null ? brushed : "",
                memo != null ? memo : ""
        );

    }

    public boolean matches(String kwd) {
        if (kwd == null || kwd.isBlank()) return true;
        if (memo != null && memo.contains(kwd)) return true;
        return false;
    }

    public boolean matchesPeriod(LocalDate start, LocalDate end){
        return DateUtil.matchesInPeriod(date, start, end);
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
        //date = uiTexts[0];
        meal = Integer.parseInt(uiTexts[1]);
        water = Integer.parseInt(uiTexts[2]);
        brushed = uiTexts[3];
        memo = uiTexts[4];
    }

    @Override
    public String[] getUITexts() {
    return new String[]{
    String.valueOf(indexId),
    date.toString(),
    String.valueOf(meal),
    String.valueOf(water),
    brushed,
    memo
    };
    }
}
