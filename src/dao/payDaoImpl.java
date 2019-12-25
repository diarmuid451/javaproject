package dao;

import java.util.ArrayList;


import data.Database;
import vo.PayVO;


public class payDaoImpl implements payDao {
	
private static payDaoImpl instance;
	
	private payDaoImpl(){}
	
	public static payDao getInstance() {
		if (instance == null) {
			instance = new payDaoImpl();
		}
		return instance;
	}
	Database payment = Database.getInstance();
	
	
	@Override
	public void getPay() {
			
	}
	
	@Override
	public String getPayment(String key, String payWay) {
		
		for (int i = 0; i < payment.paylist.size(); i++) {
			String payMent = payment.paylist.get(i).getPayWay();
			if (key.equals("결제수단")) {
				if (payMent.equals(payWay)) {
					return payMent;
				}
			} 
		}
		return null;

		
	}

	@Override
	public ArrayList<PayVO> payWayAll() {
		
		return payment.paylist;
	}

	
		
	

	
	
}	
	
