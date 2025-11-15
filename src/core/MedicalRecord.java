package core;

import facade.UIData;
import mgr.Manageable;
import util.ReadUtil;

import java.time.LocalDate;
import java.util.Scanner;

public class MedicalRecord implements Manageable, UIData {
    int id;
    LocalDate date;
    String hospital;
    String category;
    int cost;

    public void read(Scanner scan) {
        id = scan.nextInt();
        date = ReadUtil.readDate(scan);
        hospital = ReadUtil.readHospital(scan);
        category = scan.next();
        cost = scan.nextInt();
    }

    public void print() {
        System.out.printf("[%s] %s / %s / %d원 \n",
                date, hospital, category, cost );
    }

    public boolean matches(String kwd) {
        if(kwd.length() == 0)
            return true;
        if(hospital.contains(kwd) || category.contains(kwd) )
            return true;
        if(("" + cost).equals(kwd))
            return true;
        return false;
    }

    @Override
    public void set(String[] uitexts) {

    }

    @Override
    public String[] getUITexts() {
        return new String[0];
    }
}