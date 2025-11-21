package ui;

import com.formdev.flatlaf.themes.FlatMacLightLaf;

import javax.swing.*;

// https://www.formdev.com/flatlaf/

public class Main {
    public static void main(String[] args) {
        try {
            // 포인트 색상 색상 변경
            FlatMacLightLaf.setGlobalExtraDefaults(
                    java.util.Collections.singletonMap("@accentColor", "#A4C3DE")
            );
            // FlatLaf 테마 적용
            FlatMacLightLaf.setup();
        } catch (Exception e) {
            System.out.println("LnF 적용 오류");
        }

        SwingUtilities.invokeLater(() -> {
            new MainFrame();   // GUI 실행
        });
    }
}

