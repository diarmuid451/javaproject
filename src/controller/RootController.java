package controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import service.MovieScheduleService;
import service.MovieScheduleServiceImpl;
import service.MovieService;
import service.MovieServiceImpl;
import service.ReceiptInfoService;
import service.ReceiptInfoServiceImpl;
import service.ScreenService;
import service.ScreenServiceImpl;
import service.SeatService;
import service.SeatServiceImpl;
import service.UserService;
import service.UserServiceImpl;
import service.payService;
import service.payServiceImpl;
import vo.PayVO;
import data.Session;

public class RootController {
	
	/**
 	 * @param 자료형 $변수명 설명
 	 * @return 자료형 설명
 	 * @author 작성자
 	 * @brief 해당 코드에 대한 설명
 	 */
	
	/**
 	 * @author 김령환
 	 * @brief Service들을 모두 가져와서 객체인스턴스를 생성한다. main 메소드에서 관리자 페이지를 호출하는 메소드의 선택을 특정 단어를 입력함으로써 가능하도록 설치
 	 */
	UserService userService = UserServiceImpl.getInstance();
	MovieService movieService = MovieServiceImpl.getInstance();
	ScreenService screenService = ScreenServiceImpl.getInstance();
	MovieScheduleService movieSchService = MovieScheduleServiceImpl.getInstance();
	SeatService seatService = SeatServiceImpl.getInstance();//좌석관련 서비스 호출
	payService payservice = payServiceImpl.getInstance();
	ReceiptInfoService receiptService = ReceiptInfoServiceImpl.getInstance();
	
	
	// 파라미터를 담기 위한 맵
	Map<String, Object> paramMap = new HashMap<>();
	Scanner scan = new Scanner(System.in);
	public static void main(String[] args) {
		// 회원정보 확인 ->  영화 선택 -> 시간 선택 -> 인원수 선택 -> 좌석 선택 -> 결제창 -> 영수증 출력
		RootController control = new RootController();
		control.start();			// 1. 첫시작 로그인화면
		// 2. 영화 선택 페이지 보여주기
		// 3. 영화의 시간을 선택한다.
		
		
	}

	void start(){
		int menu;
		System.out.println("해당서비스는 로그인이 필요한 서비스 입니다.");
		do{
			
			
			if (Session.loginUser == null){
				System.out.println("1.로그인 \t 2. 회원가입 \t ");
				menu = Integer.parseInt(scan.nextLine());
				switch (menu) {
				case 1: //로그인 페이지
					System.out.println("로그인 페이지 입니다.");
					userService.login();
					break;				// swith의 break로 do{ }while 반복문을 break하는것이 아니다.
				case 2: //회원가입 페이지
					System.out.println("회원가입 페이지 입니다. 화면에 나오는 순서대로 입력해주세요.");
					userService.join();
					break;
				}
			} else if (Session.loginUser.getUserLevel() >= 90) {
				adminPerfom(); //90레벨 이상의 회원은 관리자 정보 창으로 이동하도록 설정
			} else if (Session.loginUser != null) {
				movieInfo(); //  2. 영화 선택 페이지 보여주기
			} 
		}while(true);
	}
	
	private void adminPerfom() {
		System.out.println("관리자 기능입니다. 수행하실 기능을 선택해 주세요.\r\n1.회원 관리\r\n2.영화 관리\r\n3.상영관 관리\r\n4.영화 예매\r\n0.로그 아웃");
		int	choice = Integer.parseInt(scan.nextLine());
		
			switch (choice){
			case 0://로그아웃
			Session.loginUser = null;
			System.out.println("처음 화면으로 돌아갑니다.");
			break;
			case 1:// 회원 관리
			userService.info();
			adminPerfom();
			break;
			case 2:// 영화 관리
			break;
			
			case 3://상영관 관리
			break;
			case 4://관리자도 영화 예매 할 수 있도록 설정
			movieInfo();
			break;
		}
	
	}

	private void movieInfo() {
		do {
		
			System.out.println("☆★☆★☆★☆★영화 목록☆★☆★☆★☆★");
			System.out.println("☆★☆★☆★영화를 선택해 주세요☆★☆★");
			movieService.getMovieName();
			
			System.out.println("로그 아웃을 원하시면 0을 입력해주세요");	
			int movieId = Integer.parseInt(scan.nextLine());
			if (movieId == 0) {
				Session.loginUser = null;
				break;
			} else{
				movieService.getMovieInfo(movieId);
				System.out.println("이 영화를 선택하시겠습니까? (Y/N)");
				if (scan.nextLine().equalsIgnoreCase("Y")) {
					getMovieSchedule(movieId);
				}
			}
		} while(true);
	}
	
	private void getMovieSchedule(int movieId) {
		
		do {
			System.out.println("영화의 상영관을 선택해주세요. 영화를 잘못선택했으면 0을 입력해주세요");
			// 해당영화의 시간 전체 출력
			movieSchService.getMovieSchedule(movieId);
			String screenMoiveId = scan.nextLine();
			if (Integer.parseInt(screenMoiveId) == 0) {
				break;
			}
			
			System.out.println(movieSchService.getOneMovieInfo(movieId, screenMoiveId) + "(이)가 맞습니까? (Y/N)");
			
			
			// 해당 영화의 상영시간 아이디를 뽑아온다.
			int movieSchId= movieSchService.getMovieSchId(movieId, screenMoiveId);
			// 영화의 상영관 아이디를 뽑아온다.
			int screenId = movieSchService.getScreenId(movieId, screenMoiveId);
			
			if (scan.nextLine().equalsIgnoreCase("y")) {
				paramMap.put("영화 상영시간 아이디", movieSchId);
				paramMap.put("영화 상영관 아이디", screenId);
				
				System.out.println("관람을 할 인원을 선택해주세요.");
				System.out.println("성인은 몇명 입니까?");
				paramMap.put("영화어른수", Integer.parseInt(scan.nextLine()));
				
				System.out.println("청소년은 몇명 입니까?");
				paramMap.put("영화청소년수", Integer.parseInt(scan.nextLine()));
				
				System.out.println("어린이는 몇명 입니까?");
				paramMap.put("영화어린이수", Integer.parseInt(scan.nextLine()));

				getScreenSeat(paramMap);
				break;
			}
		} while (true);
	}
	
	// 자리 출력 및 입력 메소드 이전것
	private void getScreenSeat(Map<String, Object> param) {
		
		int cnt = (int) param.get("영화어른수") + (int) param.get("영화청소년수") + (int) param.get("영화어린이수");
		System.out.println(cnt);
		do {
			System.out.println("좌석을 선택해주세요");
			
			//사용자가 선택한 상영관의 좌석들을 보여준다.
			seatService.showSeat((int) param.get("영화 상영관 아이디"));
			
			// 사용자가 좌석을 선택한곳의  아이디 값을 가져온다.
			int seatid = seatService.selectSeat(scan.nextLine(), (int) param.get("영화 상영관 아이디"));
			
			
			// 결제를 해야한다. 결제정보를 저장한다. 저장은 유저의 아이디 값을 세션에서 가져와서 저장한다.

			paramMap.put("좌석아이디", seatid);
			if(seatid != 0) {
				cnt--;
				seatid = 0;
			}
				
				
			
				//결제 인원을 예약을 하나 할때마다 한명씩 줄인다.
		} while (cnt >= 1);
		payMovie(paramMap);
	}	

	
	private void payMovie(Map<String, Object> param) {
		do{
			System.out.println("결제 방식을 선택해주세요 \n"
					+ "1. 카드  2. 현금  3. 페이  \r\n0.이전화면으로");
			int payWay = Integer.parseInt(scan.nextLine());
			
			if (payWay == 0) {
				System.out.println("이전화면으로 돌아갑니다.");
				break;
			} else if (payWay <= 3 || payWay >= 1) {
				paramMap.put("결제방법", payWay);
				
				payservice.setPayInfo(paramMap);
				
				//영수증을 불러온다.
				getReceiptInfo(paramMap);
			} else{
				System.out.println("잘못 입력하셨습니다.");
			}
		}while(true);

	}


	
	
	// 영수증 출력용 메소드
	private void getReceiptInfo(Map<String, Object> param) {
		receiptService.getReceipt(param);
	}






}
