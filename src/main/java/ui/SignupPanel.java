package ui;

import javax.swing.*;
import java.awt.*;

import core.UserMgr;

public class SignupPanel extends JPanel {

    public SignupPanel() {
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
        idField.setBounds(70, 205, 180, 36);
        add(idField);

        JButton dupBtn = new JButton("중복확인");
        dupBtn.setBounds(255, 205, 75, 36);
        dupBtn.setBackground(new Color(255, 205, 210));
        add(dupBtn);

        JLabel pwLabel = new JLabel("PW");
        pwLabel.setBounds(70, 255, 200, 20);
        add(pwLabel);

        JPasswordField pwField = new JPasswordField();
        pwField.setBounds(70, 280, 260, 36);
        add(pwField);

        JLabel nameLabel = new JLabel("이름");
        nameLabel.setBounds(70, 330, 200, 20);
        add(nameLabel);

        JTextField nameField = new JTextField();
        nameField.setBounds(70, 355, 260, 36);
        add(nameField);

        JButton joinBtn = new JButton("회원가입");
        joinBtn.setBounds(70, 405, 260, 44);
        joinBtn.setBackground(new Color(255, 205, 210));
        add(joinBtn);

        JButton backBtn = new JButton("뒤로");
        backBtn.setBounds(10, 10, 60, 26);
        add(backBtn);
        backBtn.addActionListener(e -> {
            MainFrame frame = (MainFrame) SwingUtilities.getWindowAncestor(this);
            frame.switchPanel(new LoginPanel());
        });

     //중복확인 버튼 기능
        dupBtn.addActionListener(e -> {
            String inputId = idField.getText().trim(); // 입력한 아이디 (공백 제거)

            // 1. 아무것도 안 적었을 때
            if (inputId.isEmpty()) {
                JOptionPane.showMessageDialog(this, "아이디를 입력해주세요!", "경고", JOptionPane.WARNING_MESSAGE);
                return; // 함수 종료
            }

            // 2. 중복인지 확인
            if (userMgr.isDuplicatedId(inputId)) {
                // 중복일 때
                JOptionPane.showMessageDialog(this, "중복된 아이디입니다.", "아이디 중복 팝업창", JOptionPane.ERROR_MESSAGE);
                idField.setText(""); // 입력창 비우기
                idField.requestFocus(); // 다시 입력하라고 커서 두기
            } else {
                // 사용 가능할 때
                JOptionPane.showMessageDialog(this, "사용 가능한 아이디입니다!", "성공", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        joinBtn.addActionListener(e -> {
            String id = idField.getText().trim();
            String pw = pwField.getText().trim();
            String name = nameField.getText().trim();

            if(id.isEmpty()) {
                JOptionPane.showMessageDialog(this, "아이디를 입력하세요.");
                return;
            }
            if (pw.isEmpty()) {
                JOptionPane.showMessageDialog(this, "비밀번호를 입력하세요.");
                return;
            }
            if (name.isEmpty()) {
                JOptionPane.showMessageDialog(this, "이름을 입력하세요.");
                return;
            }
            
            boolean result = userMgr.signUp(id, pw, name);
            if (result) {
                JOptionPane.showMessageDialog(this, "회원가입 성공! 로그인 해주세요.");
            } else {
                JOptionPane.showMessageDialog(this, "회원가입 실패");
            }

            MainFrame frame = (MainFrame) SwingUtilities.getWindowAncestor(this);
            frame.switchPanel(new LoginPanel());
        });

        joinBtn.addActionListener(e -> {
            String id = idField.getText();
            JOptionPane.showMessageDialog(this, "회원가입 처리: " + id);
        });
    }
}
