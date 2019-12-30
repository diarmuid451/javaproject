package dao;

import java.util.ArrayList;
import java.util.HashMap;

import vo.UserVO;

public interface UserDao {

	UserVO selectUser(String string, String userId);

	void inserUser(UserVO user);

	UserVO selectUser(HashMap<String, String> param);

	UserVO choiceUser(HashMap<String, String> param);
	
	void changeUser(HashMap<String, String> param);

	void deleteUser(HashMap<String, String> param);
	
	
	void showUser();
	//관리자를 포함한 회원목록 출력
	//관리자에게 회원 탈퇴 권한 부여 >>마지막에
	//관리자가 회원에게 관리자 권한 부여 기능 = 관리자가 회원정보 수정 가능
	
	
	
	
	



	
}
