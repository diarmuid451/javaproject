package vo;

public class SeatVO {
	private int SeatId;		// 좌석 아이디
	private int SeatLevel;  // 좌석 등급
	private int SeatNum;	// 좌석 번호
	private int NumCnt;		// 좌석 열의 총 수
	private int SeatPrice;	// 좌석 가격
	private char seatRownumber;		// 행의 문자
	private int BlankSeat;	//예약 유무 	0:공석	1:예약석 
	//외래키
	private int screenId;  // 영화 상영관 아이디

	
	
	public int getNumCnt() {
		return NumCnt;
	}
	public void setNumCnt(int numCnt) {
		this.NumCnt = numCnt;
	}
	
	public int getSeatid() {
		return SeatId;
	}
	public void setSeatid(int seatid) {
		this.SeatId = seatid;
	}
	public int getSeatLevel() {
		return SeatLevel;
	}
	public void setSeatLevel(int seatLevel) {
		this.SeatLevel = seatLevel;
	}
	public int getSeatNum() {
		return SeatNum;
	}
	public void setSeatNum(int seatNum) {
		this.SeatNum = seatNum;
	}
	public int getSeatPrice() {
		return SeatPrice;
	}
	public void setSeatPrice(int seatPrice) {
		this.SeatPrice = seatPrice;
	}
	public int getScreenId() {
		return screenId;
	}
	public void setScreenId(int screenId) {
		this.screenId = screenId;
	}
	public char getSeatRownumber() {
		return seatRownumber;
	}
	public void setSeatRownumber(char seatRownumber) {
		this.seatRownumber = seatRownumber;
	}			
	public int getBlankSeat() {
		return BlankSeat;
	}
	public void setBlankSeat(int blankSeat) {
		BlankSeat = blankSeat;
	}
	

}
