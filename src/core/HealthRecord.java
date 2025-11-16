package core;

import facade.UIData;
import mgr.Manageable;

import java.util.Scanner;

// TODO: 클래스 필드, 메서드 작성 (현재는 필수 구현해야 하는 메서드만 자동완성해 둔 상태)
public class HealthRecord implements Manageable, UIData {

    int index;
    String date;
    int meal;
    int water;
    String brushed;
    String memo;

    public HealthRecord() {}

    public void read(Scanner scan) {

        index = scan.nextInt();
        date = scan.next();
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
                index, date, meal, water,
                brushed != null ? brushed : "",
                memo != null ? memo : ""
        );

    }

    public boolean matches(String kwd) {
        if (kwd == null || kwd.isBlank()) return true;

        if (date != null && date.contains(kwd)) return true;

        if (memo != null && memo.contains(kwd)) return true;

        return false;
    }

    @Override
    public void set(String[] uiTexts) {
        date = uiTexts[0];
        meal = Integer.parseInt(uiTexts[1]);
        water = Integer.parseInt(uiTexts[2]);
        brushed = uiTexts[3];
        memo = uiTexts[4];
    }

    @Override
    public String[] getUITexts() {
    return new String[]{
    String.valueOf(index),
    date,
    String.valueOf(meal),
    String.valueOf(water),
    brushed,
    memo
    };
    }
}
