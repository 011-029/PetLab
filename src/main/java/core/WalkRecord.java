package core;

import facade.UIData;
import mgr.Manageable;
import mgr.PetOwned;
import util.DateUtil;
import util.ReadUtil;

import java.time.LocalDate;
import java.util.Scanner;

public class WalkRecord implements Manageable, UIData, PetOwned {
    int indexId;     // 인덱스 번호 (고유)
    String ownerId;  // 어떤 유저의
    String petName;  // 어떤 펫의 기록인지

    LocalDate date;
    int walkTime;
    double distance;
    String photoPath;
    String memo;

    public void read(Scanner scan) {
        indexId = scan.nextInt();
        ownerId = scan.next();
        petName = scan.next();
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
    public String getOwnerId() {
        return ownerId;
    }

    @Override
    public String getPetName() {
        return petName;
    }


    @Override
    public void set(String[] uitexts) {

    }

    @Override
    public String[] getUITexts() {
        return new String[0];
    }
}
