package core;

import java.util.Scanner;
import facade.UIData;
import mgr.Manageable;

// 예방접종 기록 클래스
public class VaccineRecord implements Manageable, UIData {

    // 1. 필드 (데이터 저장 변수)
    private String vaccineName; // 백신 이름
    private String date;        // 접종 날짜
    private String hospital;    // 병원 이름

    // 2. 생성자 - 기본 (Manager가 파일 읽을 때 사용)
    public VaccineRecord() {
    }

    // 3. 생성자 - 화면용 (VaccinePanel에서 임시 데이터 만들 때 사용)
    public VaccineRecord(String vaccineName, String date, String hospital) {
        this.vaccineName = vaccineName;
        this.date = date;
        this.hospital = hospital;
    }

    // --- [Manageable 인터페이스 구현] ---
    @Override
    public void read(Scanner scan) {
        // 파일에서 읽어올 때 순서대로 저장 (빈칸으로 구분된다고 가정)
        vaccineName = scan.next();
        date = scan.next();
        hospital = scan.next();
    }

    @Override
    public void print() {
        // 콘솔에 출력해서 확인하는 용도
        System.out.format("[%s] %s (%s)\n", vaccineName, date, hospital);
    }

    @Override
    public boolean matches(String kwd) {
        // 검색 키워드(kwd)가 이름이나 날짜에 포함되어 있는지 확인
        if (vaccineName.contains(kwd)) return true;
        if (date.contains(kwd)) return true;
        if (hospital.contains(kwd)) return true;
        return false;
    }

    // --- [UIData 인터페이스 구현] ---
    @Override
    public void set(String[] uitexts) {
        // UI에서 수정된 내용을 변수에 저장
        vaccineName = uitexts[0];
        date = uitexts[1];
        hospital = uitexts[2];
    }

    @Override
    public String[] getUITexts() {
        // 변수 내용을 UI(테이블 등)에 보여주기 위해 배열로 반환
        return new String[] { vaccineName, date, hospital };
    }

    // --- [Getter 메서드] (VaccinePanel에서 사용) ---
    // 아까 빨간 줄 뜨던 부분을 해결해주는 핵심 함수들입니다.
    public String getVaccineName() {
        return vaccineName;
    }

    public String getDate() {
        return date;
    }

    public String getHospital() {
        return hospital;
    }
}