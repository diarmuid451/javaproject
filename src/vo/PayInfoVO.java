package vo;

public class PayInfoVO {	//고민해봤을때 payInfo 테이블은 있어야할 이유가 없어보임. 동일인이 다른 상영관의 영화를 예매한다면 영수증이 따로 나오기 떄문에 영수증 ID가 중복값이 나올 수 가 없음
	int payInfoId;	// 결제 정보 아이디
	
	// 외래키
	int payId;		// 결제 ID
	int seatId;		// 상영관 좌석 아이디
	
	
	
	
	public int getPayInfoId() {
		return payInfoId;
	}
	public void setPayInfoId(int payInfoId) {
		this.payInfoId = payInfoId;
	}
	public int getPayId() {
		return payId;
	}
	public void setPayId(int payId) {
		this.payId = payId;
	}
	public int getSeatId() {
		return seatId;
	}
	public void setSeatId(int seatId) {
		this.seatId = seatId;
	}
}
