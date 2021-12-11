package cn.edu.lnc.service;

import java.sql.SQLException;

import cn.edu.lnc.dao.UserDao;
import cn.edu.lnc.domain.User;

public class UserService {

	//注册用户
	public boolean register(User user) {
		UserDao dao = new UserDao();
		int count =0;;
		try {
			count = dao.register(user);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return count>0?true:false;
	}

	//修改激活码状态
	public void activeCode(String code) {
		UserDao dao = new UserDao();
		try {
			dao.activeCode(code);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	//查找用户名是否存在
	public boolean checkUsername(String username) {
		UserDao dao = new UserDao();
		int count =0;
		try {
			count = dao.checkUsername(username);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return count>0?true:false;
	}

	//查找用户名和密码
	public User login(String username, String password) {
		UserDao dao = new UserDao();
		User user = null;
		try {
			user = dao.login(username,password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return user;
	}

}
