package ui;

import java.util.ArrayList;

public class UserDB {
    // 가입된 아이디들을 저장하는 리스트 (임시 DB 역할)
    private static ArrayList<String> userList = new ArrayList<>();

    // 테스트용으로 미리 몇 개 넣어둠 (이 아이디들은 중복이라고 뜸)
    static {
        userList.add("admin");
        userList.add("test");
        userList.add("kdh70");
    }

    // 1. 아이디가 이미 있는지 확인하는 함수 (있으면 true, 없으면 false)
    public static boolean isIdExists(String id) {
        return userList.contains(id);
    }

    // 2. 회원가입 시 새 아이디를 저장하는 함수
    public static void addUser(String id) {
        userList.add(id);
    }
}