package core;

import facade.DataEngineImpl;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class PetMgr extends DataEngineImpl<Pet> {
    private static PetMgr mgr = null;
    private static final String PET_INFO_FILE = "pets.txt";

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

    public void registerPet(String[] uiTexts) {
        addNewRow(uiTexts);
        saveAll(PET_INFO_FILE);
    }

    public ArrayList<Pet> getPetsByOwner(String ownerId) {
        ArrayList<Pet> result = new ArrayList<>();
        for (Pet p : mList) {
            if (p.getOwnerId().equals(ownerId))
                result.add(p);
        }
        return result;
    }

    public void saveAll(String filename) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(filename))) {
            for (Pet p: mList) {
                String[] arr = p.getUITexts();
                pw.printf("%s %s %s %s %s %s\n",
                        arr[0], arr[1], arr[2], arr[3], arr[4], arr[5]);
            }
        } catch (IOException e) {
            System.out.println("파일 저장 중 오류 발생");
        }
    }
}
