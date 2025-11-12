package core;

import facade.DataEngineImpl;

// TODO: 클래스 필드, 메서드 작성 (현재는 필수 구현해야 하는 메서드만 자동완성해 둔 상태)
public class PlayMgr extends DataEngineImpl<PlayRecord> {
    private static PlayMgr mgr = null;

    public static PlayMgr getInstance() {
        if (mgr == null)
            mgr = new PlayMgr();
        return mgr;
    }

    @Override
    public void addNewRow(String[] uiTexts) {

    }
}
