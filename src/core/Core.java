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

    static PetMgr petMgr = PetMgr.getInstance();
    static UserMgr userMgr = UserMgr.getInstance();
    User loggedInUser;

    static HealthMgr healthMgr = HealthMgr.getInstance();
    static MedicalMgr medicalMgr = MedicalMgr.getInstance();
    static MedicineRecordMgr medicineRecordMgr = MedicineRecordMgr.getInstance();
    static MedicineRoutineMgr medicineRoutineMgr = MedicineRoutineMgr.getInstance();
    static PlayMgr playMgr = PlayMgr.getInstance();
    static VaccineMgr vaccineMgr = VaccineMgr.getInstance();
    static WalkMgr walkMgr = WalkMgr.getInstance();

    Scanner scan = new Scanner(System.in);

    public void run() {

        // User 로그인
        loginLoop:
        while (true) {
            System.out.print("1. 회원가입 | 2. 로그인 | 3. 종료 |  ");
            int opt = scan.nextInt();
            if (opt == 3)
                return;
            switch (opt) {
                case 1 -> {
                    System.out.print("ID: ");
                    String id = scan.next();
                    System.out.print("PW: ");
                    String pw = scan.next();
                    System.out.print("이름: ");
                    String name = scan.next();
                    System.out.printf("회원가입 결과: %b\n",
                            userMgr.signUp(id, pw, name));
                }
                case 2 -> {
                    System.out.print("ID: ");
                    String id = scan.next();
                    System.out.print("PW: ");
                    String pw = scan.next();
                    User u = userMgr.login(id, pw);
                    if (u != null) {
                        loggedInUser = u;
                        System.out.printf("%s님 환영합니다.\n", u.getName());
                        break loginLoop;
                    }
                    else System.out.println("ID 또는 비밀번호가 틀렸습니다.");
                }
                default -> System.out.println("잘못 입력하셨습니다.");
            }
        }

        // TODO: 실질적 로그인 기능 구현 (로그인한 유저 정보만 보이게)

        // 건강 기록 출력
        // TODO: 건강 기록 데이터 생성 후 아래 주석 해제
//        HealthMgr.getInstance().readAll(".txt", new Factory<HealthRecord>() {
//            public HealthRecord create() {
//                return new HealthRecord();
//            }
//        });
//        System.out.println("\n================= 건강 기록 리스트 =================");
//        healthMgr.printAll();

        // 예방접종 기록 출력
        // TODO: 예방접종 기록 데이터 생성 후 아래 주석 해제
//        VaccineMgr.getInstance().readAll(".txt", new Factory<VaccineRecord>() {
//            public VaccineRecord create() {
//                return new VaccineRecord();
//            }
//        });
//        System.out.println("\n================= 예방접종 기록 리스트 =================");
//        vaccineMgr.printAll();

        // 진료 기록 출력
        MedicalMgr.getInstance().readAll("medicalRecords.txt", new Factory<>() {
            @Override
            public MedicalRecord create() {
                return new MedicalRecord();
            }
        });
        System.out.println("\n================= 병원 진료 기록 리스트 =================");
        medicalMgr.printAll();

        // 산책 기록 출력
        WalkMgr.getInstance().readAll("WalkRecords.txt", new Factory<>() {
            @Override
            public WalkRecord create() {
                return new WalkRecord();
            }
        });
        System.out.println("\n================= 산책 기록 리스트 =================");
        walkMgr.printAll();

        // 놀이 기록 출력
        PlayMgr.getInstance().readAll("playRecord.txt", new Factory<PlayRecord>() {
            public PlayRecord create() {
                return new PlayRecord();
            }
        });
        System.out.println("\n================= 놀이 기록 리스트 =================");
        playMgr.printAll();

        // 복용 기록 출력
        MedicineRecordMgr.getInstance().readAll("medicineRecord.txt", new Factory<MedicineRecord>() {
            public MedicineRecord create() {
                return new MedicineRecord();
            }
        });
        System.out.println("\n================= 복용 기록 리스트 =================");
        medicineRecordMgr.initNextIndexId();
        medicineRecordMgr.printAll();

        // 복용 루틴 출력
        MedicineRoutineMgr.getInstance().readAll("medicineRoutine.txt", new Factory<MedicineRoutine>() {
            public MedicineRoutine create() {
                return new MedicineRoutine();
            }
        });
        System.out.println("\n================= 복용 루틴 =================");
        medicineRoutineMgr.printAll();
        System.out.println("\n----------------- 오늘 복용해야 할 약 -----------------");
        medicineRoutineMgr.printTodayRoutine();
        medicineRoutineMgr.checkTaken();
    }

    // TODO: 필요 메서드 구현

    public static void main(String[] args) {
        Core core = new Core();
        core.run();
    }

}
