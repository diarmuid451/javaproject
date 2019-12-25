package service;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

import dao.payDao;
import dao.payDaoImpl;
import data.Session;
import vo.PayVO;


public class payServiceImpl implements payService {

	private static payServiceImpl instance;
	
	private payServiceImpl() {}
	
	public static payService getInstance() {
		if (instance == null) {
			instance = new payServiceImpl();
		}
		return instance;
	}

	payDao payDao = payDaoImpl.getInstance();
	
	
	/*
	 * @정대석
	 * @pay 인터페이서에서 받은 pay메소드를 오버라이드 한 뒤 사용  
	 * 결제 정보를 기술
	 */
	SimpleDateFormat dateform = new SimpleDateFormat("yyyy-MM-dd");
	int num = 0; 	//결제 ID 초기화
	Date today = new Date(); //결제, 환불일은 현재 시간 기준으로 작성
	
	ArrayList<PayVO> wayInfo = payDao.payWayAll();		
	PayVO payment = new PayVO();
	
	
	@Override
	public void SelectmSchedule(int mScheduleId) {
		payDao.selectMoiveScheduleInfo(mScheduleId);
		
		payment.setmScheduleId(mScheduleId);
	}
	
	
	
	
	@Override
	public void pay() { 
		num++; //결제 정보나 환불정보를 불러올때마다 결제 ID를 1씩 증가시킨다.
		payment.setPayId(num);
		payment.setPayDate(dateform.format(today));
		payment.setUserId(Session.loginUser.getUserId());	
//		payment.setPrice(price);
		
		
		
		System.out.println("영수증 번호 " + payment.getPayId()+"\r\n결제 날짜 : "+payment.getPayDate()+ "\r\n유저명 : "+ payment.getUserId() + "\r\n상영관 스케쥴 : " 
				+ payment.getmScheduleId());
	
	}

	@Override
	public void payWayInfo() { 	//결제 수단 선택하는 선택지
		
		
		for(int i = 0; i < wayInfo.size(); i++) {
			System.out.print(wayInfo.get(i).getPayWay() +"\t "); //결제 수단을 모두 호출한다.

		} 
		Scanner s = new Scanner(System.in); //결제 수단을 선택하게 한다.
		String payWay = s.nextLine();
		
		PayVO pay = new PayVO();
		pay.setPayWay(payWay);
		
		String payWayCheck = payDao.getPayment("결제수단", pay.getPayWay());
		
		if (payWayCheck == null) { //결제수단의 결점 여부를 확인한다.
			System.out.println("결제수단을 잘못 선택하셨습니다 . 다시 선택해 주세요.");
			payWayInfo();
		} else {
			System.out.println(pay.getPayWay() + "을(를) 선택하셨습니다. \r\n이대로 결제를 진행하겠습니까? (Y/N)");
			Scanner s1 = new Scanner(System.in);
			if(s1.nextLine().equalsIgnoreCase("y")) {
			System.out.println("결제 정보 출력으로 넘어갑니다.");	
			} else {
			System.out.println("결제 방식 선택으로 돌아갑니다.");
			payWayInfo();
			}
		}	
			
	}	
	
	
	
//	
//	@Override
//	public void refund() {
//		PayVO payRefund = new PayVO();
//		num++;
//		payRefund.setPayId(num);
//		payRefund.setPayDate(dateform.format(today));	//결제일은 현재 시간을 기준
//		
//		
//		
//	}
}
