package dao;

import java.util.ArrayList;

import vo.PayVO;

public interface payDao {
	
	void getPay();
	
	String getPayment(String key,String payWay);
	
	ArrayList<PayVO> payWayAll();
	
	void selectMoiveScheduleInfo (int selectMoiveTime);	//선택한 상영 시간표를 부르는 메서드
}
