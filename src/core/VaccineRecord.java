package core;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Scanner;

import facade.UIData;
import mgr.Manageable;
import mgr.PetOwned;

// 예방접종 기록 클래스
// TODO: 클래스 필드, 메서드 작성 (현재는 필수 구현해야 하는 메서드만 자동완성해둔 상태)
public class VaccineRecord implements Manageable, UIData, PetOwned {
    int indexId;     // 인덱스 번호 (고유)
    String ownerId;  // 어떤 유저의
    String petName;  // 어떤 펫의 기록인지

    String date;
    String vaccine;
    String hospital;
    String memo;

    private static final DateTimeFormatter FMT =
            DateTimeFormatter.ofPattern("yyyy-MM--dd");

    public VaccineRecord() { }

    @Override
    public void read(Scanner scan) {
        if(!scan.hasNext()) return;

        indexId = scan.nextInt();
        ownerId = scan.next();
        petName = scan.next();
        date = scan.next();
        if (scan.hasNext()) vaccine = scan.next();
        if (scan.hasNext()) hospital = scan.next();

        memo = scan.hasNextLine() ? scan.nextLine().trim() : "";
    }

    @Override
    public void print() {
        System.out.printf("%s %s %s %s (%s)\n",
                safe(date), safe(vaccine), safe(hospital), safe(memo), getDDayText());
    }

    @Override
    public boolean matches(String kwd) {
        if (kwd == null || kwd.isBlank()) return true;
        kwd = kwd.trim();

        return safe(date).contains(kwd)
                || safe(vaccine).contains(kwd)
                || safe(hospital).contains(kwd)
                || safe(memo).contains(kwd);
    }

    @Override
    public void set(String[] uiTexts) {
        if (uiTexts == null) return;

        if (uiTexts.length > 0) date     = uiTexts[0];
        if (uiTexts.length > 1) vaccine  = uiTexts[1];
        if (uiTexts.length > 2) hospital = uiTexts[2];
        if (uiTexts.length > 3) memo     = uiTexts[3];

    }

    @Override
    public String[] getUITexts() {
        return new String[]{
                safe(date),
                safe(vaccine),
                safe(hospital),
                safe(memo),
                getDDayText()
        };
    }

    LocalDate parseDate() {
        if(date == null || date.isBlank())
            return null;
        try {
            return LocalDate.parse(date, FMT);
        } catch (Exception e) {
            return null;
        }
    }

    public long getDDay() {
        LocalDate d = parseDate();
        if (d == null) return 0;

        LocalDate today = LocalDate.now();
        return ChronoUnit.DAYS.between(today, d);
    }

    public String getDDayText() {
        LocalDate d = parseDate();
        if (d == null) return "";

        long diff = getDDay();

        if (diff == 0) return "D-day";
        if (diff > 0) return "D-" + diff;
        return "D+" + Math.abs(diff);
    }

    @Override
    public String getOwnerId() {
        return ownerId;
    }

    @Override
    public String getPetName() {
        return petName;
    }


    private String safe(String s) {
        return (s == null ? "" : s.trim());
    }
}

