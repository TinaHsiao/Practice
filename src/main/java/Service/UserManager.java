package Service;

import java.util.HashMap;
import java.util.List;

import Bean.UserBean;
import Dao.MovieOnlineDao;
import Dao.UserDAO;

public class UserManager {
	
	private UserDAO dao = new UserDAO();
	
	public boolean checkMemberId(String memberId){
		return dao.checkMemberId(memberId);
	}
	
	public boolean checkPassword(String memberId,String password){
		return dao.checkPassword(memberId,password);
	}
	
	public void insertUserData(UserBean bean){
		dao.insertUserData(bean);
	}
}
