package ui;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class VaccineInputPanel extends JPanel {
    final String FONT = "맑은 고딕";

    public VaccineInputPanel() {
        setLayout(null);
        setBackground(new Color(245, 245, 245));

        // 1. 뒤로가기 버튼
        JButton backBtn = new JButton("←");
        backBtn.setBounds(10, 15, 50, 40);
        backBtn.setFont(new Font(FONT, Font.BOLD, 25));
        backBtn.setBorderPainted(false);
        backBtn.setContentAreaFilled(false);
        backBtn.setFocusPainted(false);
        backBtn.addActionListener(e -> goBack());
        add(backBtn);

        // 2. 제목
        JLabel titleLabel = new JLabel("예방접종 기록하기");
        titleLabel.setFont(new Font(FONT, Font.BOLD, 20));
        titleLabel.setBounds(60, 20, 200, 30);
        add(titleLabel);

        // --- 입력 양식 ---
        JPanel formPanel = new JPanel();
        formPanel.setLayout(null);
        formPanel.setBounds(20, 70, 350, 500);
        formPanel.setBackground(Color.WHITE);
        formPanel.setBorder(new LineBorder(new Color(220, 220, 220), 1));
        add(formPanel);

        // (1) 백신명
        addLabel(formPanel, "예방접종명", 20, 20);
        JTextField nameField = addTextField(formPanel, 20, 50);

        // (2) 접종일
        addLabel(formPanel, "접종일 (YYYY-MM-DD)", 20, 100);
        JTextField dateField = addTextField(formPanel, 20, 130);

        // (3) 병원명
        addLabel(formPanel, "병원명", 20, 180);
        JTextField hospitalField = addTextField(formPanel, 20, 210);

        // 저장 버튼
        JButton saveBtn = new JButton("기록 저장하기");
        saveBtn.setBounds(20, 430, 310, 50);
        saveBtn.setBackground(new Color(255, 230, 230)); // 핑크색
        saveBtn.setFont(new Font(FONT, Font.BOLD, 16));
        formPanel.add(saveBtn);

        // 저장 버튼 클릭 이벤트
        saveBtn.addActionListener(e -> {
            // TODO: 여기서 입력된 값(getText)을 가져와서 Manager에 저장하는 코드 추가 필요
            JOptionPane.showMessageDialog(this, "예방접종 기록이 저장되었습니다! (임시)");
            goBack(); // 저장 후 목록 화면으로 이동
        });
    }

    // 화면 이동 함수
    private void goBack() {
        MainFrame frame = (MainFrame) SwingUtilities.getWindowAncestor(this);
        if (frame != null) frame.switchPanel(new VaccinePanel());
    }

    // 라벨 생성 도우미 함수
    private void addLabel(JPanel panel, String text, int x, int y) {
        JLabel label = new JLabel(text);
        label.setFont(new Font(FONT, Font.BOLD, 14));
        label.setBounds(x, y, 200, 20);
        panel.add(label);
    }

    // 텍스트필드 생성 도우미 함수
    private JTextField addTextField(JPanel panel, int x, int y) {
        JTextField field = new JTextField();
        field.setBounds(x, y, 310, 40);
        field.setBorder(new LineBorder(Color.LIGHT_GRAY, 1, true));
        panel.add(field);
        return field;
    }
}