package service;


import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

import dao.payDao;
import dao.payDaoImpl;
import vo.PayVO;
import vo.ScreenVO;

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
	
	int num = 0; 	//결제 ID 초기화
	Date today = new Date(); //결제, 환불일은 현재 시간 기준으로 작성
	
	
	
	@Override
	public void pay() { 
		
		PayVO payment = new PayVO();
		num++; //결제 정보나 환불정보를 불러올때마다 결제 ID를 1씩 증가시킨다.
		payment.setPayId(num);
		payment.setPayDate(today); //결제일은 현재 시간을 기준
		
	}

	@Override
	public void payWayInfo() { 	//결제 수단 선택하는 선택지
		System.out.println("결제 수단을 선택해 주세요.");
		ArrayList<PayVO> wayInfo = payDao.payWayAll();		
		for(int i = 0; i < wayInfo.size(); i++) {
			System.out.print(wayInfo.get(i).getPayWay() +"\t\n "); //결제 수단을 모두 호출한다.

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
			System.out.println(pay.getPayWay() + "을(를) 선택하셨습니다.");
			System.out.println("결제창으로 넘어갑니다.");
			//결제정보를 호출하는 메서드로 넘어가도록 한다.
		}	
	}
	
	
	
	@Override
	public void refund() {
		PayVO payRefund = new PayVO();
		num++;
		payRefund.setPayId(num);
		payRefund.setPayDate(today);
		
		
	}
}
