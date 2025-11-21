package ui;

import core.Pet;
import core.PetMgr;
import core.User;
import mgr.Factory;

import javax.swing.*;

public class MainFrame extends JFrame {
    private User loggedInUser;
    private Pet loggedInUserPet;

    public void setLoggedInUser(User user) {
        this.loggedInUser = user;
        // 펫 설정
        PetMgr petMgr = PetMgr.getInstance();
        this.loggedInUserPet = petMgr.getPetByOwner(user.getId());
    }

    public User getLoggedInUser() {
        return loggedInUser;
    }

    public Pet getLoggedInUserPet() {
        return loggedInUserPet;
    }

    public void loadPetData() {
        PetMgr petMgr = PetMgr.getInstance();
        PetMgr.getInstance().readAll("data/pets.txt", new Factory<Pet>() {
            public Pet create() {
                return new Pet();
            }
        });
    }

    public MainFrame() {
        loadPetData();

        setTitle("Pet Manager");
        setSize(420, 750);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        setContentPane(new LoginPanel());
        setVisible(true);
    }

    public void switchPanel(JPanel panel) {
        setContentPane(panel);
        revalidate();
        repaint();
    }
}
