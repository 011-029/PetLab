// TODO: 예시 코드 임시로 붙여넣기 한 것임. 추후 수정 필요
package mgr;

public interface Factory<T extends Manageable> {
    T create();
}
