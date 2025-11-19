package ui;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.time.LocalDate;
import java.util.ArrayList;

// core 패키지의 파일 가져오기 (에러나면 패키지명 확인)
import core.VaccineRecord;

public class VaccinePanel extends JPanel {
    final String FONT = "맑은 고딕";

    // UI 컴포넌트 및 데이터 변수
    private JPanel listPanel;
    private JTextField searchField;
    private ArrayList<VaccineRecord> allList = new ArrayList<>(); // 전체 데이터 저장소
   
    

    public VaccinePanel() {
        setLayout(null);
        setBackground(new Color(245, 245, 245));
     // [VaccinePanel.java 안에 추가]

     // 작성하기 버튼 (오른쪽 상단에 배치)
     JButton writeBtn = new JButton("작성하기");
     writeBtn.setBounds(260, 15, 100, 35); // 위치 조정
     writeBtn.setBackground(new Color(255, 230, 230)); // 핑크색 포인트
     writeBtn.setFont(new Font(FONT, Font.BOLD, 12));
     add(writeBtn);

     // 버튼 누르면 입력 화면(VaccineInputPanel)으로 이동
     writeBtn.addActionListener(e -> {
         MainFrame frame = (MainFrame) SwingUtilities.getWindowAncestor(this);
         if (frame != null) {
             frame.switchPanel(new VaccineInputPanel());
         }
     });

     // 1. 뒤로가기 버튼
     JButton backBtn = new JButton("←");
     backBtn.setBounds(10, 15, 50, 40); // 왼쪽 구석에 위치
     backBtn.setFont(new Font(FONT, Font.BOLD, 25));
     backBtn.setBorderPainted(false);    // 테두리 없애기
     backBtn.setContentAreaFilled(false); // 배경 투명하게
     backBtn.setFocusPainted(false);
     backBtn.setCursor(new Cursor(Cursor.HAND_CURSOR)); // 마우스 올리면 손가락 모양
     add(backBtn);

     // 2. 버튼 클릭 기능 (메인으로 이동)
     backBtn.addActionListener(e -> {
         // 현재 창이 속한 메인 프레임을 찾아서
         MainFrame frame = (MainFrame) SwingUtilities.getWindowAncestor(this);
         // 메인 패널로 화면을 갈아끼움
         if (frame != null) {
             frame.switchPanel(new MainPanel());
         }
     });


        // 1. 제목 라벨
        JLabel titleLabel = new JLabel("예방접종 관리");
        titleLabel.setFont(new Font(FONT, Font.BOLD, 20));
        titleLabel.setBounds(60, 20, 200, 30);
        add(titleLabel);

        // 2. 검색창
        searchField = new JTextField();
        searchField.setBounds(20, 60, 300, 40);
        searchField.setBorder(new LineBorder(Color.LIGHT_GRAY, 1, true));
        add(searchField);

        // 검색 버튼
        JButton searchBtn = new JButton("🔍");
        searchBtn.setBounds(330, 60, 50, 40);
        add(searchBtn);

        // 3. 탭 버튼 (과거 / 예정)
        JButton pastBtn = new JButton("과거");
        pastBtn.setBounds(20, 110, 170, 40);
        pastBtn.setBackground(Color.WHITE);
        add(pastBtn);

        JButton futureBtn = new JButton("예정");
        futureBtn.setBounds(200, 110, 170, 40);
        futureBtn.setBackground(new Color(255, 230, 230)); 
        add(futureBtn);

        // 4. 리스트가 들어갈 스크롤 영역
        listPanel = new JPanel();
        listPanel.setLayout(new BoxLayout(listPanel, BoxLayout.Y_AXIS));
        listPanel.setBackground(Color.WHITE);

        JScrollPane scrollPane = new JScrollPane(listPanel);
        scrollPane.setBounds(20, 160, 350, 500);
        scrollPane.setBorder(null);
        add(scrollPane);

        // 5. 초기 데이터 로드 (2025년 기준 데이터)
        initData();      
        updateList(allList); // 처음엔 전체 리스트 보여주기

        // --- [버튼 이벤트 연결] ---

        // (1) 검색 버튼 클릭 시 동작
        searchBtn.addActionListener(e -> {
            String keyword = searchField.getText();
            filterList(keyword);
        });

        // (2) '과거' 버튼 클릭 시 동작
        pastBtn.addActionListener(e -> {
            filterByDate(true); // true = 과거
        });

        // (3) '예정' 버튼 클릭 시 동작
        futureBtn.addActionListener(e -> {
            filterByDate(false); // false = 예정(미래)
        });
    }

    // -------------------------------------------------------
    // 데이터 관리 및 로직 메서드
    // -------------------------------------------------------

    // 1. 테스트용 가짜 데이터 생성 (2025년 기준)
    private void initData() {
        allList.clear();
        
        // [과거 데이터] - 2025년 11월 18일 이전 날짜들
        allList.add(new VaccineRecord("종합백신(DHPPL) 5차", "2025-01-10", "행복동물병원"));
        allList.add(new VaccineRecord("코로나 장염", "2025-03-15", "튼튼병원"));
        allList.add(new VaccineRecord("켄넬코프", "2025-05-20", "사랑동물병원"));
        allList.add(new VaccineRecord("광견병", "2025-06-01", "구청지정병원"));
        allList.add(new VaccineRecord("인플루엔자", "2025-10-05", "행복동물병원"));
        
        // [예정 데이터] - 2025년 11월 18일 이후 (12월, 2026년 등)
        allList.add(new VaccineRecord("심장사상충(예정)", "2025-12-01", "자가접종"));
        allList.add(new VaccineRecord("광견병 정기접종(예정)", "2026-06-01", "구청지정병원"));
        allList.add(new VaccineRecord("종합백신 추가접종(예정)", "2026-01-10", "행복동물병원"));
    }

    // 2. 검색어로 리스트 필터링
    private void filterList(String keyword) {
        if (keyword == null || keyword.trim().isEmpty()) {
            updateList(allList); // 검색어 없으면 전체 보여줌
            return;
        }

        ArrayList<VaccineRecord> result = new ArrayList<>();
        for (VaccineRecord r : allList) {
            // VaccineRecord의 matches 메서드를 이용해 검색
            if (r.matches(keyword)) { 
                result.add(r);
            }
        }
        updateList(result);
    }

    // 3. 날짜 기준(과거/예정) 필터링
    private void filterByDate(boolean isPast) {
        ArrayList<VaccineRecord> result = new ArrayList<>();
        String today = LocalDate.now().toString(); // 오늘 날짜 (YYYY-MM-DD 형태)

        for (VaccineRecord r : allList) {
            // 날짜 문자열 비교 (예: "2025-01-01" vs "2025-11-18")
            int compare = r.getDate().compareTo(today);
            
            if (isPast) {
                // 과거: 기록된 날짜가 오늘보다 이전일 때 (compare < 0)
                if (compare < 0) result.add(r);
            } else {
                // 예정: 기록된 날짜가 오늘이거나 미래일 때 (compare >= 0)
                if (compare >= 0) result.add(r);
            }
        }
        updateList(result);
    }

    // 4. 화면 그리기 (데이터 리스트를 받아서 UI로 만듦)
    private void updateList(ArrayList<VaccineRecord> dataList) {
        // 기존 목록 삭제
        listPanel.removeAll();

        // 데이터가 없을 경우 안내 문구
        if (dataList.isEmpty()) {
            JLabel emptyLabel = new JLabel("조건에 맞는 기록이 없습니다.");
            emptyLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            listPanel.add(Box.createVerticalStrut(50));
            listPanel.add(emptyLabel);
        }

        // 리스트 카드 생성
        for (VaccineRecord record : dataList) {
            JPanel card = new JPanel();
            card.setLayout(null);
            card.setPreferredSize(new Dimension(330, 100));
            card.setMaximumSize(new Dimension(330, 100));
            card.setBackground(Color.WHITE);
            card.setBorder(new LineBorder(new Color(230, 230, 230), 1));
            
            // 백신명
            JLabel nameLabel = new JLabel(record.getvaccine());
            nameLabel.setFont(new Font(FONT, Font.BOLD, 14));
            nameLabel.setBounds(15, 15, 200, 20);
            card.add(nameLabel);

            // 날짜
            JLabel dateLabel = new JLabel("접종일: " + record.getDate());
            dateLabel.setFont(new Font(FONT, Font.PLAIN, 12));
            dateLabel.setBounds(15, 40, 200, 20);
            card.add(dateLabel);

            // 병원
            JLabel hospitalLabel = new JLabel("병원: " + record.getHospital());
            hospitalLabel.setForeground(Color.GRAY);
            hospitalLabel.setBounds(15, 65, 200, 20);
            card.add(hospitalLabel);

            listPanel.add(card);
            listPanel.add(Box.createRigidArea(new Dimension(0, 10))); // 간격
        }
        
        // 화면 새로고침
        listPanel.revalidate();
        listPanel.repaint();
    }
}