package ui;

import javax.swing.*;
import java.awt.*;

import core.User;
import core.UserMgr;

public class LoginPanel extends JPanel {

    public LoginPanel() {
        UserMgr userMgr = UserMgr.getInstance();

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

        // pw 입력 후 바로 엔터 누르면 로그인
        pwField.addActionListener(e -> loginBtn.doClick());

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
            String id = idField.getText().trim();
            String pw = pwField.getText().trim();

            if(id.isEmpty()) {
                JOptionPane.showMessageDialog(this, "아이디를 입력하세요.");
                return;
            }
            if (pw.isEmpty()) {
                JOptionPane.showMessageDialog(this, "비밀번호를 입력하세요.");
                return;
            }

            // 로그인 검증
            User user = userMgr.login(id, pw);
            if (user != null) {
                MainFrame frame = (MainFrame) SwingUtilities.getWindowAncestor(this);
                frame.setLoggedInUser(user);
                // TODO: 아래 테스트용 코드 추후 삭제 (1줄)
                System.out.println("로그인 ID: " + user.getId());
                frame.switchPanel(new MainPanel(frame));
            } else {
                JOptionPane.showMessageDialog(this, "ID 또는 비밀번호가 틀렸습니다.");
                // 로그인 실패 시 idField, pwField 비우고 idField 에 포커스
                idField.setText("");
                pwField.setText("");
                idField.requestFocus();
            }
        });

    }
}
