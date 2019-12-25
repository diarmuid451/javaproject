package vo;

import java.util.Date;

public class PayVO {
	private int payId;		// 결제 아이디
	private String payWay;	// 결제 수단
	private String payDate;	// 결제 일자
	private String payInfo;	// 결제 정보  (이해를 못해서 손대지 못함)
	private double payPoint; // 결제 적립 포인트 @정대석   @결제수단에 따른 포인트 적립률 저장
//	private int price; //결제 금액은 어디로?
	
	
	//외래키
	private String userId;		// 로그인 한회원 아이디
	private int mScheduleId;	// 영화 시간 아이디
	
	public int getPayId() {
		return payId;
	}
	public void setPayId(int payId) {
		this.payId = payId;
	}
	public String getPayWay() {
		return payWay;
	}
	public void setPayWay(String payWay) {
		this.payWay = payWay;
	}
	public String getPayDate() {
		return payDate;
	}
	public void setPayDate(String string) {
		this.payDate = string;
	}
	public String getPayInfo() {
		return payInfo;
	}
	public void setPayInfo(String payInfo) {
		this.payInfo = payInfo;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public int getmScheduleId() {
		return mScheduleId;
	}
	public void setmScheduleId(int mScheduleId) {
		this.mScheduleId = mScheduleId;
	}
	public double getPayPoint() {
		return payPoint;
	}
	public void setPayPoint(double payPoint) {
		this.payPoint = payPoint;
	}
	

}
