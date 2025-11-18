package core;

import mgr.Factory;
import util.ReadUtil;

import java.time.LocalDate;
import java.util.ArrayList;
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
    Pet loggedInUserPet;

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
                case 10 -> registerPet();
                case 11 -> printPetsByOwner();
                case 0 -> {
                    return;
                }
                default -> System.out.println("잘못 입력하셨습니다.");
            }
        }
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

    private int mainMenu() {
        System.out.println("=================================================");
        updatePet(loggedInUser.getId());
        if (loggedInUserPet == null) {
            System.out.println("(현재 등록된 반려동물이 없습니다.)");
        } else {
            System.out.printf("(현재 선택된 펫: %s)\n", loggedInUserPet.getName());
        }
        while (true) {
            try {
                System.out.println("1. 건강 기록 기능");
                System.out.println("2. 병원 진료 기록 기능");
                System.out.println("3. 복용 기록 기능");
                System.out.println("4. 복용 루틴 기능");
                System.out.println("5. 놀이 기록 기능");
                System.out.println("6. 예방접종 기록 기능");
                System.out.println("7. 산책 기록 기능");
                System.out.println("10. 내 펫 등록");
                System.out.println("11. 내 펫 조회");
                System.out.println("0. 종료");
                System.out.print(">> 메뉴 입력: ");
                return scan.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("잘못 입력하셨습니다.");
                scan.nextLine();
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
            updatePet(id);
            System.out.printf("%s님 환영합니다.\n", u.getName());
            return true;
        } else {
            System.out.println("ID 또는 비밀번호가 틀렸습니다.");
            return false;
        }
    }

    // 펫 등록
    private void registerPet() {
        System.out.println("=============== 펫 등록 ===============");
        System.out.print("반려동물 이름을 입력하세요: ");
        String name = scan.next();
        System.out.print("반려동물의 종을 입력하세요: ");
        String species = scan.next();
        System.out.print("반려동물의 성별을 입력하세요(암컷/수컷): ");
        String gender = scan.next();
        System.out.print("중성화 여부를 입력하세요(y/n): ");
        boolean check = scan.next().equals("y");
        System.out.print("생일을 입력하세요(yyyy-mm-dd): ");
        LocalDate birth = ReadUtil.readDate(scan);
        System.out.print("체중을 입력하세요(kg): ");
        double weight = scan.nextDouble();

        String[] petData = {
                loggedInUser.getId(),
                name,
                species,
                gender + ((check) ? "(중성화)" : ""),
                birth.toString(),
                String.valueOf(weight)
        };
        petMgr.registerPet(petData);
        System.out.println("펫 등록 완료!");
    }

    // 내 펫 조회
    private void printPetsByOwner() {
        System.out.println("=============== 펫 목록 ===============");
        System.out.printf("%s님의 펫 목록\n", loggedInUser.getName());
        ArrayList<Pet> pets = petMgr.getPetsByOwner(loggedInUser.getId());
        if (pets.isEmpty())
            System.out.println("등록된 펫이 없습니다.");
        else {
            for (Pet p : pets)
                p.print();
        }
    }

    // 초기 데이터 불러오기
    private void loadAllData() {
        UserMgr.getInstance().readAll("users.txt", new Factory<User> () {
            public User create() {
                return new User();
            }
        });
        PetMgr.getInstance().readAll("pets.txt", new Factory<Pet> () {
            public Pet create() {
                return new Pet();
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

    // 건강 기록 기능
    private void healthMenu() {
        // TODO: 건강 기록 데이터 생성 후 아래 주석 해제
//        System.out.println("================= 건강 기록 리스트 =================");
//        healthMgr.printByPet(loggedInUser.getId());
        System.out.println("구현중");
    }

    // 진료 기록 기능
    private void medicalMenu() {
        System.out.println("================= 병원 진료 기록 리스트 =================");
        medicalMgr.printByPet(loggedInUser.getId());
    }

    // 복용 기록 기능
    private void medicineRecordMenu() {
        System.out.println("================= 복용 기록 리스트 =================");
        medicineRecordMgr.initNextIndexId();
        medicineRecordMgr.printByPet(loggedInUser.getId());
    }

    // 복용 루틴 기능
    private void medicineRoutineMenu() {
        System.out.println("================= 복용 루틴 =================");
        medicineRoutineMgr.printByPet(loggedInUser.getId());
        System.out.println("-------------- 오늘 복용해야 할 약 --------------");
        // TODO: 로그인한 유저 루틴만 출력
        medicineRoutineMgr.printTodayRoutine();
        medicineRoutineMgr.checkTaken();
    }

    // 놀이 기록 기능
    private void playMenu() {
        System.out.println("================= 놀이 기록 리스트 =================");
        playMgr.printByPet(loggedInUser.getId());
    }

    // 예방접종 기록 기능
    private void vaccineMenu() {
        // TODO: 예방접종 기록 데이터 생성 후 아래 주석 해제
//        System.out.println("================= 예방접종 기록 리스트 =================");
//        vaccineMgr.printByPet(loggedInUser.getId());
        System.out.println("구현중");
    }

    // 산책 기록 기능
    private void walkMenu() {
        System.out.println("================= 산책 기록 리스트 =================");
        walkMgr.printByPet(loggedInUser.getId());
    }

    // 등록된 펫 업데이트
    private void updatePet(String ownerId) {
        loggedInUserPet = petMgr.getPetByOwner(ownerId);
    }

    public static void main(String[] args) {
        Core core = new Core();
        core.run();
    }

}