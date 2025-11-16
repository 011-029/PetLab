package core;

import facade.UIData;
import mgr.Manageable;
import util.DateUtil;
import util.ReadUtil;

import java.time.LocalDate;
import java.util.Scanner;

public class WalkRecord implements Manageable, UIData {
    int index;
    LocalDate date;
    int walkTime;
    double distance;
    String photoPath;
    String memo;

    public void read(Scanner scan) {
        index = scan.nextInt();
        date = ReadUtil.readDate(scan);
        walkTime = scan.nextInt();
        distance = scan.nextDouble();
        photoPath = scan.next();
        memo = scan.nextLine().trim();
    }

    public void print() {
        System.out.printf("[%s] %d분 / %.2fkm ",
                date, walkTime, distance);
        if(!memo.equals("0")) System.out.printf("/ 메모: %s\n", memo);
    }

    public boolean matches(String kwd) {
        if(kwd.isEmpty())
            return true;
        if((""+walkTime).equals(kwd))
            return true;
        return memo.contains(kwd) || ("" + distance).contains(kwd);
    }

    public boolean matchesPeriod(LocalDate start, LocalDate end){
        return DateUtil.matchesInPeriod(date, start, end);
    }

    @Override
    public void set(String[] uitexts) {

    }

    @Override
    public String[] getUITexts() {
        return new String[0];
    }
}
