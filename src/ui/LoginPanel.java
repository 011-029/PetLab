package ui;

import javax.swing.*;
import java.awt.*;

import ui.MainFrame;
import ui.SignupPanel;

public class LoginPanel extends JPanel {

    public LoginPanel() {
        setLayout(null);
        setBackground(Color.WHITE);

        JLabel logo = new JLabel("🐾");
        logo.setFont(new Font("Dialog", Font.PLAIN, 48));
        logo.setBounds(150, 40, 120, 80);
        add(logo);

        JLabel idLabel = new JLabel("ID");
        idLabel.setBounds(70, 180, 200, 20);
        add(idLabel);

        JTextField idField = new JTextField();
        idField.setBounds(70, 205, 260, 36);
        add(idField);

        JLabel pwLabel = new JLabel("PW");
        pwLabel.setBounds(70, 255, 200, 20);
        add(pwLabel);

        JPasswordField pwField = new JPasswordField();
        pwField.setBounds(70, 280, 260, 36);
        add(pwField);

        JButton loginBtn = new JButton("로그인");
        loginBtn.setBounds(70, 330, 260, 44);
        loginBtn.setBackground(new Color(255, 205, 210));
        loginBtn.setBorder(BorderFactory.createLineBorder(new Color(255, 180, 185)));
        add(loginBtn);

        JButton joinBtn = new JButton("회원가입");
        joinBtn.setBounds(70, 385, 260, 26);
        joinBtn.setFocusPainted(false);
        joinBtn.setBorderPainted(false);
        joinBtn.setContentAreaFilled(false);
        add(joinBtn);

        joinBtn.addActionListener(e -> {
            MainFrame frame = (MainFrame) SwingUtilities.getWindowAncestor(this);
            frame.switchPanel(new SignupPanel());
        });

        loginBtn.addActionListener(e -> {
            // (여기 나중에 진짜 로그인 검증 넣어도 됨)
            MainFrame frame = (MainFrame) SwingUtilities.getWindowAncestor(this);
            frame.switchPanel(new MainPanel());
        });

    }
}
