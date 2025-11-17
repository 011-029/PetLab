package core;

import facade.UIData;
import mgr.Manageable;
import util.DateUtil;
import util.ReadUtil;

import java.time.LocalDate;
import java.util.Scanner;

public class MedicalRecord implements Manageable, UIData {
    int index;
    LocalDate date;
    String hospital;
    String category;
    int cost;

    public void read(Scanner scan) {
        index = scan.nextInt();
        date = ReadUtil.readDate(scan);
        hospital = ReadUtil.readHospital(scan);
        category = scan.next();
        cost = scan.nextInt();
    }

    public void print() {
        System.out.printf("[%s] %s / %s / %d원 / %s \n",
                date, hospital, category, cost, getDDayText());
    }

    public boolean matches(String kwd) {
        if(kwd.isEmpty())
            return true;
        if(hospital.contains(kwd) || category.contains(kwd) )
            return true;
        return ("" + cost).equals(kwd);
    }

    public boolean matchesPeriod(LocalDate start, LocalDate end){
        return DateUtil.matchesInPeriod(date, start, end);
    }

    //필요할 때 쓰기 (검색 등)
    //public long getDDay() {
    //    return DateUtil.getDDay(date);
    //}

    public String getDDayText() {
        return DateUtil.getDDayText(date);
    }

    @Override
    public void set(String[] uitexts) {
        // uitexts = {date, hospital, category, cost, petName}
        date = LocalDate.parse(uitexts[0]);
        hospital = uitexts[1];
        category = uitexts[2];
        cost = Integer.parseInt(uitexts[3]);
    }

    @Override
    public String[] getUITexts() {
        return new String[] {
                date.toString(),
                hospital,
                category,
                String.valueOf(cost)
        };
    }

    //getter
    public String getHospital(){
        return hospital;
    }
    public String getCategory(){
        return category;
    }
}