package core;

import facade.UIData;
import mgr.Manageable;

import java.util.Scanner;

// 사용자 클래스
// TODO: 클래스 필드, 메서드 작성 (현재는 필수 구현해야 하는 메서드만 자동완성해둔 상태)
public class User implements Manageable, UIData {
    public void read(Scanner scan) {

    }

    public void print() {

    }

    public boolean matches(String kwd) {
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
