package core;

import javax.swing.SwingUtilities;
import ui.MainFrame;

public class Core {

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {
            new MainFrame();   // GUI 실행
        });
    }
}

