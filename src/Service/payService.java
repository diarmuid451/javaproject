package service;

public interface payService {
		
		/*
		 * 회원 정보를 가져와서 영화 영화좌석(미구현) DB에서 영화가 몇관인지 정보를 추가
		 * 사용자의 지불 금액 - 영화 금액 = 거스름돈
		 * 카카오페이, 삼성페이, 현금, 신용카드일때 마일리지 적립량이 다르다.
		 * 회원등급 0~5까지 있을때, 마일리지 적립량과 혜택이 다름
		 * 좌석에 대한 정보
		 * 마일리지사용시 결제금액할인
		 */
	/*
	 * @ 정대석
	 * @ payService 인터페이스에서 pay메서드 생성
	 */
	void pay();
	
	
	void payWayInfo();
	
	void refund();


	
}
