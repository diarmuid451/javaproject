package dao;




import data.Database;

public class SeatDaoImpl implements SeatDao {
	private static SeatDaoImpl instance;

	private SeatDaoImpl(){}

	public static SeatDao getInstnace(){
		if (instance == null) {
			instance = new SeatDaoImpl();
		}
		return instance;
	}

	Database database = Database.getInstance();



	@Override
	public void showScreenSeat(int screenId) {
		for (int i = 0; i < database.seatlist.size(); i++) {
			if (screenId == database.seatlist.get(i).getScreenId()) {
				
					String seatNumber = Character.toString(database.seatlist.get(i).getSeatRownumber()) + database.seatlist.get(i).getSeatNum();
					System.out.print(seatNumber + "\t");
					
					if (database.seatlist.get(i).getBlankSeat() == 0){	// 공석일 경우 □ 출력
						System.out.print("□ \t");	
					} else if (database.seatlist.get(i).getBlankSeat() == 1){	// 예약석일 경우 ■ 출력
						System.out.print("■ \t");
					} else {	// 없는 자리일 경우 빈칸으로 출력
						System.out.print("  \t");
					}
					if(database.seatlist.get(i).getSeatNum() == database.seatlist.get(i).getNumCnt()) System.out.println();
				}	
			}
					
		}			
		
	
				
	
	
	@Override
	public int checkSeat(String seatRownum, int seatNum, int screenId) {
		int blankSeat = 0;
		for (int i = 0; i < database.seatlist.size(); i++) {
			if ((screenId == database.seatlist.get(i).getScreenId()) && (database.seatlist.get(i).getSeatRownumber() == seatRownum.charAt(0)) && (database.seatlist.get(i).getSeatNum() == seatNum)) {
				blankSeat =  database.seatlist.get(i).getBlankSeat();
			}
			
		}
		return blankSeat;
		
	}
			
					

	@Override
	public int setBlankSeat(String seatRownum, int seatNum, int screenId) {
		int seatid = 0;
		
		for (int i = 0; i < database.seatlist.size(); i++) {
			if ((screenId == database.seatlist.get(i).getScreenId()) && (database.seatlist.get(i).getSeatRownumber() == seatRownum.charAt(0)) && (database.seatlist.get(i).getSeatNum() == seatNum)) {
				database.seatlist.get(i).setBlankSeat(1);  // 좌석이 예약된 형태로 변경
				seatid = database.seatlist.get(i).getSeatid();
				}
			}
		return seatid;
	
	}

	

}
