package core;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.util.Scanner;
import facade.UIData;
import mgr.Manageable;

// 예방접종 기록 클래스
public class VaccineRecord implements Manageable, UIData {

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

    // --- [UIData 인터페이스 구현] ---
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
        // 변수 내용을 UI(테이블 등)에 보여주기 위해 배열로 반환
        return new String[] { vaccineName, date, hospital };
    }

    // --- [Getter 메서드] (VaccinePanel에서 사용) ---
    // 아까 빨간 줄 뜨던 부분을 해결해주는 핵심 함수들입니다.
    public String getVaccineName() {
        return vaccineName;
    }

    public String getDate() {
        return date;
    }

    public String getHospital() {
        return hospital;
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

    private String safe(String s) {
        return (s == null ? "" : s.trim());
    }
}

