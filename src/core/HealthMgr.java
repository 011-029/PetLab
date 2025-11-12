package core;

import facade.DataEngineImpl;

// TODO: 클래스 필드, 메서드 작성 (현재는 필수 구현해야 하는 메서드만 자동완성해 둔 상태)
public class HealthMgr extends DataEngineImpl<HealthRecord> {
    private static HealthMgr mgr = null;

    public static HealthMgr getInstance() {
        if (mgr == null)
            mgr = new HealthMgr();
        return mgr;
    }

    @Override
    public void addNewRow(String[] uiTexts) {

    }
}
