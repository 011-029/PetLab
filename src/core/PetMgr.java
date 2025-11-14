package core;

import facade.DataEngineImpl;

import java.util.ArrayList;

public class PetMgr extends DataEngineImpl<Pet> {
    private static PetMgr mgr = null;

    public static PetMgr getInstance() {
        if (mgr == null)
            mgr = new PetMgr();
        return mgr;
    }

    @Override
    public void addNewRow(String[] uiTexts) {
        Pet p = new Pet();
        p.set(uiTexts);
        mList.add(p);
    }

    public ArrayList<Pet> getPetsByOwner(String ownerId) {
        ArrayList<Pet> result = new ArrayList<>();
        for (Pet p : mList) {
            if (p.getOwnerId().equals(ownerId))
                result.add(p);
        }
        return result;
    }

    public void registerPet(String[] uiTexts) {
        addNewRow(uiTexts);
    }
}
