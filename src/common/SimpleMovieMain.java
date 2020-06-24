package common;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;

import daum.BoxOfficeDaum;
import naver.BoxOfficeNaver;

public class SimpleMovieMain {
	
	public static void main(String[] args) throws Exception {
		
		BoxOfficeParser bParser = new BoxOfficeParser();
		BoxOfficeNaver bon = new BoxOfficeNaver();
		BoxOfficeDaum don = new BoxOfficeDaum();
		// 1. 박스오피스 정보 + 네이버 영화 정보 + 다음 영화 정보(1~18위)

		// 순위, 영화제목, 예매율, 장르, 상영시간. 개봉일자 ,감독
		// 출연진, 누적관객수, 누적매출액, 네이버코드, 다음코드 총 12개
		String[][] mvRank = new String[10][12];

		// 1. 박스오피스 정보 + 네이버 영화 정보 + 다음 영화 정보(1~18위)

		// 1-1. BoxOffcie Parsing
		mvRank = bParser.getParser();

		// 1-2. NaverBoxOffice Crawling:)
		mvRank = bon.naverMovieRank(mvRank);

		// 1-3. Daum BoxOffice Crawling:)
		mvRank = don.daumMovieRank(mvRank);
		
		userInterface(mvRank);

	}

	// VIEW: 프로그램 시작 인터페이스 + 사용자 값 입력
	public static int userInterface(String[][] mvRank) {
		Scanner sc = new Scanner(System.in); // 사용자한테 값입력받을때 말그대로 스캔한다
		// 2.View 단
		// 2-1. 유저에게 BoxOffice 예매율 1~10위까지의 정보 제공

		// 현재 날짜 계산하기
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd.HH:mm:ss");
		String today = sdf.format(cal.getTime());
		Date date = new Date();
		SimpleDateFormat engSdf = new SimpleDateFormat("MM월dd일", Locale.KOREAN);
		String engDay = engSdf.format(cal.getTime());
		System.out.println(
				"■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■");
		System.out.println("■■ SimplMovie ver1.2");
		System.out.println(
				"■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■");
		System.out.println("■■ >> Developer : HeonJun Kim(redwhale)");
		System.out.println(
				"■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■");
		System.out.println("■■ >> TODAY: " + today);
		System.out.println(
				"■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■");
		System.out.println("■■BoxOffice Rank(" + (cal.get(Calendar.MONTH) + 1) + "월" + cal.get(Calendar.DATE) + "일)");
		String noneCode = "";
		for (int i = 0; i < mvRank.length; i++) {
			if (mvRank[i][10] == null) {
				noneCode = "(정보없음)";
			}
			System.out.println("■■ >> " + mvRank[i][0] + "위" + mvRank[i][1] + noneCode);
		}

		// 2-2. 사용자가 입력하는 부분
		while (true) {
			System.out.println(
					"■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■");
			System.out.println("■■ >> 보고싶은 영화 번호(순위)를 입력하세요");
			System.out.print("■■ >> 번호: ");
			int userVal = sc.nextInt();

			if (userVal < 0 || userVal > 10) {
				System.out.println("■■■ >> [Warning] 1~10사이의 숫자를 입력하세요 :(");
				// 잘못된값

			} else if (mvRank[userVal - 1][10] == null) {
				// 사용자가 입력한 번호의 영화가 정보가 있는지 없는지 체크
				System.out.println("■■ >>[Warning] 해당 영화는 상영정보가 없습니다. 다른영화를 선택해 주세요~");
				continue;
			} else {
				// 통과 : 사용자의 값이 0~10
				break;
			}

		}

		// 유효성 체크
		// >> 1~10까지의 값(정상)
		// 1. 1~10이외의 숫자를 넣엇을때
		// 2. 정보없는 영화 선택햇을때
		System.out.println(
				"■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■");

		// id
		// 1) NULL값 체크
		// 2) 길이 체크
		// 3) 공백값 체크
		// 4) 정규식 체크(대소문자,숫자 8~15자 특수문자 사용 혹은 미사용)
		// 5) 길이 체크(id의 길이 체크)

		return 0;
	}

	// mvRank 출력하는 코드
	public static void printArr(String[][] mvRank) {
		System.out.println("■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■");
		for (int i = 0; i < mvRank.length; i++) {
			System.out.print(mvRank[i][0] + "\t");
			System.out.print(mvRank[i][1] + "\t");
			System.out.print(mvRank[i][2] + "\t");
			System.out.print(mvRank[i][3] + "\t");
			System.out.print(mvRank[i][4] + "\t");
			System.out.print(mvRank[i][5] + "\t");
			System.out.print(mvRank[i][6] + "\t");
			System.out.print(mvRank[i][7] + "\t");
			System.out.print(mvRank[i][8] + "\t");
			System.out.print(mvRank[i][9] + "\t");
			System.out.print(mvRank[i][10] + "\t");
			System.out.println(mvRank[i][11]);

		}
		System.out.println("■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■");

	}
}