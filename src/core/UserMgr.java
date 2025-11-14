package core;

import facade.DataEngineImpl;

public class UserMgr extends DataEngineImpl<User> {
    private static UserMgr mgr = null;

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
        return true;
    }

    public User login(String id, String pw) {
        for (User u : mList) {
            if (u.getId().equals(id) && u.checkPassword(pw))
                return u;
        }
        return null;
    }
}
