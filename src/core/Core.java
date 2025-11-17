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
    static MedicineRecordMgr medicineRecordMgr = MedicineRecordMgr.getInstance();
    static MedicineRoutineMgr medicineRoutineMgr = MedicineRoutineMgr.getInstance();
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

        WalkMgr.getInstance().readAll("WalkRecords.txt", new Factory<>() {
            @Override
            public WalkRecord create() {
                return new WalkRecord();
            }
        });
        System.out.println("\n================= 산책 기록 리스트 =================");
        walkMgr.printAll();

        PlayMgr.getInstance().readAll("play.txt", new Factory<PlayRecord>() {
            public PlayRecord create() {
                return new PlayRecord();
            }
        });
        System.out.println("\n================= 놀이 기록 리스트 =================");
        playMgr.printAll();

        MedicineRecordMgr.getInstance().readAll("medicineRecord.txt", new Factory<MedicineRecord>() {
            public MedicineRecord create() {
                return new MedicineRecord();
            }
        });
        System.out.println("\n================= 복용 기록 리스트 =================");
        medicineRecordMgr.initNextIndexId();
        medicineRecordMgr.printAll();

        MedicineRoutineMgr.getInstance().readAll("medicineRoutine.txt", new Factory<MedicineRoutine>() {
            public MedicineRoutine create() {
                return new MedicineRoutine();
            }
        });
        System.out.println("\n================= 복용 루틴 =================");
        medicineRoutineMgr.printAll();
        System.out.println("\n================= 오늘 복용해야 할 약 =================");
        medicineRoutineMgr.printTodayRoutine();
        medicineRoutineMgr.checkTaken();
    }

    // TODO: 필요 메서드 구현

    public static void main(String[] args) {
        Core core = new Core();
        core.run();
    }

}
