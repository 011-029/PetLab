package ui;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MainPanel extends JPanel {
    final String FONT = "맑은 고딕";

    public MainPanel() {
        setLayout(null);
        setBackground(new Color(245, 245, 245));

        // 1. 흰색 메인 카드
        JPanel cardPanel = new JPanel();
        cardPanel.setLayout(null);
        cardPanel.setBounds(10, 10, 385, 680);
        cardPanel.setBackground(Color.WHITE);
        cardPanel.setBorder(new LineBorder(new Color(220, 220, 220), 1, true));
        add(cardPanel);

        JLabel titleLabel = new JLabel("반려동물 정보");
        titleLabel.setFont(new Font(FONT, Font.BOLD, 18));
        titleLabel.setBounds(20, 20, 200, 30);
        cardPanel.add(titleLabel);

        JSeparator separator = new JSeparator();
        separator.setBounds(20, 55, 345, 2);
        separator.setForeground(new Color(230, 230, 230));
        cardPanel.add(separator);

        // --- [2. 강아지 사진 넣기] ---
        // 이미지 파일을 불러와서 크기를 조절하는 코드입니다.
        ImageIcon originalIcon = new ImageIcon("images/profile.jpg");

        // 사진이 너무 클 수 있으니 120x120 크기로 강제 조절
        Image img = originalIcon.getImage().getScaledInstance(120, 120, Image.SCALE_SMOOTH);
        JLabel profileImage = new JLabel(new ImageIcon(img));
        profileImage.setBounds(20, 70, 120, 120);
        // 테두리 (사진 액자 느낌)
        profileImage.setBorder(new LineBorder(Color.LIGHT_GRAY, 1));
        cardPanel.add(profileImage);

        // 3. 텍스트 정보
        addProfileText(cardPanel, "이름: 후추", 160, 80);
        addProfileText(cardPanel, "종: 갈색푸들", 160, 105);
        addProfileText(cardPanel, "성별: 암컷(중성화)", 160, 130);
        addProfileText(cardPanel, "생일: 2020.01.10", 160, 155);
        addProfileText(cardPanel, "몸무게: 4.3kg", 160, 180);

        // --- [4. 메뉴 리스트 박스] ---
        JPanel menuBox = new JPanel();
        menuBox.setLayout(null);
        menuBox.setBounds(20, 220, 345, 430);
        menuBox.setBackground(Color.WHITE);
        menuBox.setBorder(new LineBorder(new Color(220, 220, 220), 1, true));
        cardPanel.add(menuBox);

        int startY = 30;
        int gap = 80;


        // MainPanel.java 내부

        addMenuItem(menuBox, "예방접종 관리", "과거 및 예정된 접종 기록", startY, () -> {

            JOptionPane.showMessageDialog(this, "💉 예방접종 관리 화면으로 이동합니다!");
            movePage(new VaccinePanel());
        });
        addMenuItem(menuBox, "병원 진료·예약", "진료 일정 및 기록", startY + gap, () -> {
            JOptionPane.showMessageDialog(this, "🏥 병원 진료 화면으로 이동합니다!");
            // TODO: 병원 진료기록 화면 연결
        });
        addMenuItem(menuBox, "복용 기록", "복용 알림과 기록", startY + gap * 2, () -> {
            JOptionPane.showMessageDialog(this, "💊 복용 기록 화면으로 이동합니다!");
            // TODO: 복용 기록 화면 연결
        });

        addMenuItem(menuBox, "건강 상태 기록", "체중, 컨디션 기록", startY + gap * 3, () -> {
            JOptionPane.showMessageDialog(this, "⚖️ 건강 상태 화면으로 이동합니다!");
            // TODO: 건강기록 화면 연결
        });

        addMenuItem(menuBox, "산책·놀이 기록", "금주의 산책량", startY + gap * 4, () -> {
            // TODO: 산책, 놀이기록 화면 연결
        });
    }

    // 화면 이동을 도와주는 함수
    private void movePage(JPanel nextPanel) {
        MainFrame frame = (MainFrame) SwingUtilities.getWindowAncestor(this);
        frame.switchPanel(nextPanel);
    }

    private void addProfileText(JPanel panel, String text, int x, int y) {
        JLabel label = new JLabel(text);
        label.setFont(new Font(FONT, Font.PLAIN, 14));
        label.setForeground(Color.DARK_GRAY);
        label.setBounds(x, y, 200, 20);
        panel.add(label);
    }

    // 메뉴 추가 함수 (클릭 기능 추가됨)
    private void addMenuItem(JPanel panel, String title, String desc, int y, Runnable action) {

        // 클릭 이벤트를 받을 투명 패널 (버튼 역할)
        JPanel clickArea = new JPanel();
        clickArea.setBounds(10, y - 10, 325, 70);
        clickArea.setLayout(null);
        clickArea.setBackground(Color.WHITE); // 투명하게 하려면 setOpaque(false)
        clickArea.setCursor(new Cursor(Cursor.HAND_CURSOR)); // 마우스 올리면 손가락 모양

        // 마우스 클릭 이벤트
        clickArea.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                action.run(); // 전달받은 이동 코드 실행
            }

            // 마우스 올렸을 때 살짝 회색으로 변하는 효과 (선택 사항)
            @Override
            public void mouseEntered(MouseEvent e) {
                clickArea.setBackground(new Color(245, 250, 255));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                clickArea.setBackground(Color.WHITE);
            }
        });

        panel.add(clickArea);

        // 아이콘
        JLabel icon = new JLabel("⭐");
        icon.setFont(new Font("Dialog", Font.BOLD, 24));
        icon.setBounds(10, 20, 30, 30); // clickArea 기준 좌표
        clickArea.add(icon);

        // 제목
        JLabel titleLabel = new JLabel(title);
        titleLabel.setFont(new Font(FONT, Font.BOLD, 15));
        titleLabel.setBounds(50, 15, 200, 20);
        clickArea.add(titleLabel);

        // 설명
        JLabel descLabel = new JLabel(desc);
        descLabel.setFont(new Font(FONT, Font.PLAIN, 12));
        descLabel.setForeground(Color.GRAY);
        descLabel.setBounds(50, 40, 250, 20);
        clickArea.add(descLabel);
    }
}