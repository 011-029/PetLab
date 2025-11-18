package core;

import facade.UIData;
import mgr.Manageable;
import util.ReadUtil;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class Pet implements Manageable, UIData {

    private String ownerId;
    private String name;
    private String species;
    private String gender;
    private LocalDate birthDate;
    private double weight;

    private final ArrayList<HealthRecord> healthRecords = new ArrayList<>();
    private final ArrayList<MedicalRecord> medicalRecords = new ArrayList<>();
    private final ArrayList<MedicineRecord> medicineRecords = new ArrayList<>();
    private final ArrayList<MedicineRoutine> medicineRoutines = new ArrayList<>();
    private final ArrayList<PlayRecord> playRecords = new ArrayList<>();
    private final ArrayList<VaccineRecord> vaccineRecords = new ArrayList<>();
    private final ArrayList<WalkRecord> walkRecords = new ArrayList<>();

    @Override
    public void read(Scanner scan) {
        ownerId = scan.next();
        name = scan.next();
        species = scan.next();
        gender = scan.next();
        birthDate = ReadUtil.readDate(scan);
        weight = scan.nextDouble();
    }

    public void addMedicalRecord(MedicalRecord r) {
        medicalRecords.add(r);
    }

    @Override
    public void print() {
        System.out.printf("[Pet] %s (%s) %.1fkg, %s\n",
                name, species, weight, gender);
    }

    @Override
    public boolean matches(String kwd) {
        return name.contains(kwd) || species.contains(kwd);
    }

    @Override
    public void set(String[] uitexts) {
        // uitexts = {ownerId, name, species, gender, birthDateStr, weightStr}
        ownerId = uitexts[0];
        name = uitexts[1];
        species = uitexts[2];
        gender = uitexts[3];
        birthDate = LocalDate.parse(uitexts[4]);
        weight = Double.parseDouble(uitexts[5]);
    }

    @Override
    public String[] getUITexts() {
        return new String[]{
                ownerId, name, species, gender,
                birthDate.toString(), Double.toString(weight)
        };
    }

    // getter
    public String getOwnerId() { return ownerId; }
    public String getName() { return name; }
    public ArrayList<MedicalRecord> getMedicalRecords(){
        return medicalRecords;
    }
}
