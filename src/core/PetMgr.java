package core;

import facade.DataEngineImpl;

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

    // ownerId와 petName 으로 펫 객체 찾기
    public Pet findPet(String ownerId, String petName) {
        for (Pet p: mList) {
            if (p.getOwnerId().equals(ownerId)
            && p.getName().equals(petName))
                return p;
        }
        return null;
    }

    @Override
    public void addNewRow(String[] uiTexts) {
        Pet p = new Pet();
        p.set(uiTexts);
        mList.add(p);
    }

    public void registerPet(String[] uiTexts) {
        addNewRow(uiTexts);
        saveToFile(PET_INFO_FILE);
    }

    public Pet getPetByOwner(String ownerId) {
        for (Pet p: mList) {
            if (p.getOwnerId().equals(ownerId))
                return p;
        }
        return null;
    }

    public ArrayList<Pet> getPetsByOwner(String ownerId) {
        ArrayList<Pet> result = new ArrayList<>();
        for (Pet p : mList) {
            if (p.getOwnerId().equals(ownerId))
                result.add(p);
        }
        return result;
    }

    public void saveToFile(String filename) {
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
