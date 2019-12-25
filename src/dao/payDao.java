package dao;

import java.util.ArrayList;

import vo.PayVO;

public interface payDao {
	
	void getPay();
	
	String getPayment(String key,String payWay);
	
	ArrayList<PayVO> payWayAll();
	
}
