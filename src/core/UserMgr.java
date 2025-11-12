package core;

import facade.DataEngineImpl;

// 사용자 관리 클래스
// TODO: 클래스 필드, 메서드 작성 (현재는 필수 구현해야 하는 메서드만 자동완성해둔 상태)
public class UserMgr extends DataEngineImpl<User> {
    private static UserMgr mgr = null;

    public static UserMgr getInstance() {
        if (mgr == null)
            mgr = new UserMgr();
        return mgr;
    }

    @Override
    public void addNewRow(String[] uiTexts) {

    }
}
