package ui;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {

    public MainFrame() {
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
