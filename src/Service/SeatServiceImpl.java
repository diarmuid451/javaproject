
package service;


import java.util.Scanner;

import dao.SeatDao;
import dao.SeatDaoImpl;

public class SeatServiceImpl implements SeatService {

	private static SeatServiceImpl instance;

	private SeatServiceImpl(){}

	public static SeatService getInstance(){
		if (instance == null) {
			instance = new SeatServiceImpl();
		}
		return instance;
	}


	SeatDao seatDao = SeatDaoImpl.getInstnace();
	Scanner scan = new Scanner(System.in);
	@Override
	public void showSeat(int screenId) {
		seatDao.showScreenSeat(screenId);
		
	}
	

	@Override
	public int selectSeat(String SeatId, int screenId) {
		// 사용자가 입력한 값은 String형태의 좌석의 위치이다. 그것을 우리는 id값으로 변환을 해야한다.
		System.out.println(SeatId+" 좌석이 맞습니까? (Y/N)");
		if (SeatId.length() == 2 && scan.nextLine().equalsIgnoreCase("Y")) {

			String SeatRownum = SeatId.substring(0,1);				// A, B, C와 같은 행의 정보
			int SeatNum = Integer.parseInt(SeatId.substring(1,2));  // 1 ,2, 3과 같은 열의 정보
			
			if(seatDao.checkSeat(SeatRownum, SeatNum, screenId) == 1) {
				System.out.println("이미 예약된 좌석입니다.");
				System.out.print("다시 ");
			} else {
			
			return seatDao.setBlankSeat(SeatRownum, SeatNum, screenId);
			}
		} else if (SeatId.length() != 2) {
			System.out.println("좌석번호를 잘못입력하셨습니다.");
			System.out.print("다시 ");
		} else {
			
			System.out.print("다시 ");
		}
		return 0;
	}

}
