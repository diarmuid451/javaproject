package dao;

public interface SeatDao {
	
	void showScreenSeat(int screenId);
	
	int checkSeat(String seatRownum, int seatNum, int screenId);

	int setBlankSeat(String seatRownum, int seatNum, int screenId);

}
