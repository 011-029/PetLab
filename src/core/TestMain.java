package core;

import java.time.LocalDate;
import java.util.ArrayList;

public class TestMain {
    public static void main(String[] args) {

        UserMgr userMgr = UserMgr.getInstance();
        PetMgr petMgr = PetMgr.getInstance();

        // ----------------------------
        // 1. 회원가입 테스트
        // ----------------------------
        System.out.println("=== 회원가입 ===");
        boolean ok = userMgr.signUp("test01", "1234", "홍길동");
        System.out.println("회원가입 결과: " + ok);

        // ----------------------------
        // 2. 로그인 테스트
        // ----------------------------
        System.out.println("\n=== 로그인 ===");
        User u = userMgr.login("test01", "1234");
        if (u != null)
            System.out.println("로그인 성공: " + u.getId());
        else
            System.out.println("로그인 실패");

        // ----------------------------
        // 3. 펫 등록 테스트
        // ----------------------------
        System.out.println("\n=== 펫 등록 ===");
        String[] petData = {
                "test01",               // ownerId
                "후추",                 // name
                "강아지",               // species
                "암컷(중성화)",          // gender
                "2020-01-10",          // 생일 (문자열)
                "4.3"                  // weight
        };
        petMgr.registerPet(petData);
        System.out.println("펫 등록 완료!");

        // ----------------------------
        // 4. 펫 조회 테스트
        // ----------------------------
        System.out.println("\n=== test01의 펫 목록 ===");
        ArrayList<Pet> pets = petMgr.getPetsByOwner("test01");
        for (Pet p : pets) {
            p.print();
        }
    }
}
