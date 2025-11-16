package core;

import facade.DataEngineImpl;

import java.time.LocalDate;
import java.util.ArrayList;

public class WalkMgr extends DataEngineImpl<WalkRecord> {
    private static WalkMgr mgr = null;

    public static WalkMgr getInstance() {
        if (mgr == null)
            mgr = new WalkMgr();
        return mgr;
    }

    public ArrayList<WalkRecord> searchPeriod(LocalDate start, LocalDate end) {
        ArrayList<WalkRecord> result = new ArrayList<>();
        for(WalkRecord r : mList) {
            if(r.matchesPeriod(start,end))
                result.add(r);
        }
        return result;
    }

    @Override
    public void addNewRow(String[] uiTexts) {

    }
}
