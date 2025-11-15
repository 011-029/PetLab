package core;

import mgr.Factory;

import java.util.Scanner;

public class Core {
    private static Core core = null;

    private Core() {
    }

    public static Core getInstance() {
        if (core == null)
            core = new Core();
        return core;
    }

    static HealthMgr healthMgr = HealthMgr.getInstance();
    static MedicalMgr medicalMgr = MedicalMgr.getInstance();
    static MedicineMgr medicineMgr = MedicineMgr.getInstance();
    static PetMgr petMgr = PetMgr.getInstance();
    static PlayMgr playMgr = PlayMgr.getInstance();
    static UserMgr userMgr = UserMgr.getInstance();
    static VaccineMgr vaccineMgr = VaccineMgr.getInstance();
    static WalkMgr walkMgr = WalkMgr.getInstance();

    Scanner scan = new Scanner(System.in);

    public void run() {
        // TODO: run() 구현
        MedicalMgr.getInstance().readAll("medicalRecords.txt", new Factory<>() {
            @Override
            public MedicalRecord create() {
                return new MedicalRecord();
            }
        });
        System.out.println("\n================= 병원 진료 기록 리스트 =================");
        medicalMgr.printAll();
    }

    // TODO: 필요 메서드 구현

    public static void main(String[] args) {
        Core core = new Core();
        core.run();
    }

}
