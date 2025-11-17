package core;

import mgr.Factory;

import java.util.InputMismatchException;
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

    static UserMgr userMgr = UserMgr.getInstance();
    static PetMgr petMgr = PetMgr.getInstance();
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
        loadAllData();

        // User 로그인
        loginLoop:
        while (true) {
            int opt = startMenu();
            switch (opt) {
                case 1 -> signUp();
                case 2 -> {
                    if (login()) break loginLoop;
                }
                case 3 -> {
                    return;
                }
                default -> System.out.println("잘못 입력하셨습니다.");
            }
        }

        // 메인 시스템
        while (true) {
            int opt = mainMenu();
            switch (opt) {
                case 1 -> healthMenu();
                case 2 -> medicalMenu();
                case 3 -> medicineRecordMenu();
                case 4 -> medicineRoutineMenu();
                case 5 -> playMenu();
                case 6 -> vaccineMenu();
                case 7 -> walkMenu();
                case 0 -> {
                    return;
                }
                default -> System.out.println("잘못 입력하셨습니다.");
            }
        }
        // TODO: 실질적 로그인 기능 구현 (로그인한 유저 정보만 보이게)
    }

    // 메뉴 출력 & opt 입력
    private int startMenu() {
        System.out.println("=================================================");
        System.out.println("🐾 Paw Log 🐾");
        while (true) {
            try {
                System.out.print("1. 회원가입 | 2. 로그인 | 3. 종료 |  ");
                return scan.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("잘못 입력하셨습니다.");
                scan.nextLine(); // 버퍼 비움
            }
        }
    }

    // 회원가입
    private void signUp() {
        System.out.println("============= 회원가입 =============");
        System.out.print("ID를 입력하세요: ");
        String id = scan.next();
        System.out.print("PW를 입력하세요: ");
        String pw = scan.next();
        System.out.print("이름을 입력하세요: ");
        String name = scan.next();
        System.out.printf("회원가입 결과: %s\n",
                (userMgr.signUp(id, pw, name)) ? "성공" : "실패");
    }

    // 로그인
    private boolean login() {
        System.out.println("=============== 로그인 ===============");
        System.out.print("ID: ");
        String id = scan.next();
        System.out.print("PW: ");
        String pw = scan.next();
        User u = userMgr.login(id, pw);

        if (u != null) {
            loggedInUser = u;
            System.out.printf("%s님 환영합니다.\n", u.getName());
            return true;
        } else {
            System.out.println("ID 또는 비밀번호가 틀렸습니다.");
            return false;
        }
    }

    // 초기 데이터 불러오기
    private void loadAllData() {
        UserMgr.getInstance().readAll("users.txt", new Factory<User> () {
            public User create() {
                return new User();
            }
        });
        MedicalMgr.getInstance().readAll("medicalRecords.txt", new Factory<>() {
            @Override
            public MedicalRecord create() {
                return new MedicalRecord();
            }
        });
        WalkMgr.getInstance().readAll("WalkRecords.txt", new Factory<>() {
            @Override
            public WalkRecord create() {
                return new WalkRecord();
            }
        });
        PlayMgr.getInstance().readAll("playRecord.txt", new Factory<PlayRecord>() {
            public PlayRecord create() {
                return new PlayRecord();
            }
        });
        MedicineRecordMgr.getInstance().readAll("medicineRecord.txt", new Factory<MedicineRecord>() {
            public MedicineRecord create() {
                return new MedicineRecord();
            }
        });
        MedicineRoutineMgr.getInstance().readAll("medicineRoutine.txt", new Factory<MedicineRoutine>() {
            public MedicineRoutine create() {
                return new MedicineRoutine();
            }
        });
        // TODO: 예방접종 기록 데이터 생성 후 아래 주석 해제
//        VaccineMgr.getInstance().readAll(".txt", new Factory<VaccineRecord>() {
//            public VaccineRecord create() {
//                return new VaccineRecord();
//            }
//        });
        // TODO: 건강 기록 데이터 생성 후 아래 주석 해제
//        HealthMgr.getInstance().readAll(".txt", new Factory<HealthRecord>() {
//            public HealthRecord create() {
//                return new HealthRecord();
//            }
//        });
    }

    private int mainMenu() {
        System.out.println("=================================================");
        while (true) {
            try {
                System.out.println("1. 건강 기록 기능");
                System.out.println("2. 병원 진료 기록 기능");
                System.out.println("3. 복용 기록 기능");
                System.out.println("4. 복용 루틴 기능");
                System.out.println("5. 놀이 기록 기능");
                System.out.println("6. 예방접종 기록 기능");
                System.out.println("7. 산책 기록 기능");
                System.out.println("0. 종료");
                System.out.print(">> 메뉴 입력: ");
                return scan.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("잘못 입력하셨습니다.");
                scan.nextLine();
            }
        }
    }

    // 건강 기록 기능
    private void healthMenu() {
        // TODO: 건강 기록 데이터 생성 후 아래 주석 해제
//        System.out.println("================= 건강 기록 리스트 =================");
//        healthMgr.printAll();
    }

    // 진료 기록 기능
    private void medicalMenu() {
        System.out.println("================= 병원 진료 기록 리스트 =================");
        medicalMgr.printAll();
    }

    // 복용 기록 기능
    private void medicineRecordMenu() {
        System.out.println("================= 복용 기록 리스트 =================");
        medicineRecordMgr.initNextIndexId();
        medicineRecordMgr.printAll();
    }

    // 복용 루틴 기능
    private void medicineRoutineMenu() {
        System.out.println("================= 복용 루틴 =================");
        medicineRoutineMgr.printAll();
        System.out.println("-------------- 오늘 복용해야 할 약 --------------");
        medicineRoutineMgr.printTodayRoutine();
        medicineRoutineMgr.checkTaken();
    }

    // 놀이 기록 기능
    private void playMenu() {
        System.out.println("================= 놀이 기록 리스트 =================");
        playMgr.printAll();
    }

    // 예방접종 기록 기능
    private void vaccineMenu() {
        // TODO: 예방접종 기록 데이터 생성 후 아래 주석 해제
//        System.out.println("================= 예방접종 기록 리스트 =================");
//        vaccineMgr.printAll();
    }

    // 산책 기록 기능
    private void walkMenu() {
        System.out.println("================= 산책 기록 리스트 =================");
        walkMgr.printAll();
    }


    public static void main(String[] args) {
        Core core = new Core();
        core.run();
    }

}