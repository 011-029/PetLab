// TODO: 예시 코드 임시로 붙여넣기 한 것임. 추후 수정 필요
package mgr;

import java.util.Scanner;

public interface Manageable {
    void read(Scanner scan);
    void print();
    boolean matches(String kwd);
}