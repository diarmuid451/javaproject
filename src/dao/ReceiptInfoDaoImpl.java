package dao;

import java.util.Map;

import vo.PayVO;
import data.Database;

public class ReceiptInfoDaoImpl implements ReceiptInfoDao {
	private static ReceiptInfoDaoImpl instance;
	
	private ReceiptInfoDaoImpl(){}
	
	public static ReceiptInfoDao getInstance() {
		if (instance == null) {
			instance = new ReceiptInfoDaoImpl();
		}
		return instance;
	}
	
	Database database = Database.getInstance();

	@Override
	public void getReceipt(Map<String, Object> param) {
		
		for(String key : param.keySet()) {
			System.out.println(key + " : " + param.get(key));
		}
		
	}
}
