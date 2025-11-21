package core;

import mgr.Factory;
import mgr.PetRecordMgr;

import java.time.LocalDate;
import java.util.ArrayList;

public class PlayMgr extends PetRecordMgr<PlayRecord> {
    private static PlayMgr mgr = null;

    public static PlayMgr getInstance() {
        if (mgr == null)
            mgr = new PlayMgr();
        return mgr;
    }

    public ArrayList<PlayRecord> searchPeriod(LocalDate start, LocalDate end) {
        ArrayList<PlayRecord> result = new ArrayList<>();
        for(PlayRecord r : mList) {
            if(r.matchesPeriod(start, end))
                result.add(r);
        }
        return result;
    }

    @Override
    public void addNewRow(String[] uiTexts) {

    }

    public void loadFromFile() {
        readAll("data/playRecord.txt", new Factory<PlayRecord>() {
            public PlayRecord create() {
                return new PlayRecord();
            }
        });
        initNextIndexId();
    }
}
