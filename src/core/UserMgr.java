package core;

import facade.DataEngineImpl;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class UserMgr extends DataEngineImpl<User> {
    private static UserMgr mgr = null;
    private static final String USER_INFO_FILE = "users.txt";

    public static UserMgr getInstance() {
        if (mgr == null)
            mgr = new UserMgr();
        return mgr;
    }

    @Override
    public void addNewRow(String[] uiTexts) {
        // uiTexts → {"id", "password", "name"}
        User u = new User();
        u.set(uiTexts);
        mList.add(u);
    }

    public boolean isDuplicatedId(String id) {
        for (User u : mList) {
            if (u.getId().equals(id))
                return true;
        }
        return false;
    }

    public boolean signUp(String id, String pw, String name) {
        if (isDuplicatedId(id)) return false;

        String[] arr = {id, pw, name};
        addNewRow(arr);
        saveToFile(USER_INFO_FILE);

        return true;
    }

    public void saveToFile(String filename) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(filename))) {
            for (User u: mList) {
                pw.printf("%s %s %s\n", u.getId(), u.getPassword(), u.getName());
            }
        } catch (IOException e) {
            System.out.println("파일 저장 중 오류 발생");
        }
    }

    public User login(String id, String pw) {
        for (User u : mList) {
            if (u.getId().equals(id) && u.checkPassword(pw))
                return u;
        }
        return null;
    }
}
