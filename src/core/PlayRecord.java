package core;

import facade.UIData;
import mgr.Manageable;
import util.DateUtil;
import util.ReadUtil;

import java.time.LocalDate;
import java.util.Scanner;

public class PlayRecord implements Manageable, UIData {
    int indexId;     // 인덱스 번호
    LocalDate date;  // yyyy-mm-dd
    int playTime;    // 놀이 시간 (단위: 분)
    String playType; // 놀이 종류 (없으면 0)
    String memo;     // 메모 (없으면 0)

    public void read(Scanner scan) {
        // 10 2025-11-16 35 낚싯대 체력 좋아보임
        indexId = scan.nextInt();
        date = ReadUtil.readDate(scan);
        playTime = scan.nextInt();
        playType = scan.next();
        memo = scan.nextLine().trim();
    }

    public void print() {
        System.out.printf("#%d [%d년 %d월 %d일] ", indexId,
                date.getYear(), date.getMonthValue(), date.getDayOfMonth());
        System.out.printf("%d분", playTime);
        if (!playType.equals("0")) System.out.printf(" / %s", playType);
        if (!memo.equals("0")) System.out.printf(" / 메모: %s", memo);
        System.out.println();
    }

    public boolean matches(String kwd) {
        if (kwd.isEmpty())
            return true;
        return memo.contains(kwd) || playType.contains(kwd);
    }

    public boolean matchesPeriod(LocalDate start, LocalDate end){
        return DateUtil.matchesInPeriod(date, start, end);
    }

    @Override
    public void set(String[] uiTexts) {

    }

    @Override
    public String[] getUITexts() {
        return new String[0];
    }
}