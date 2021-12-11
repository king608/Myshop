package cn.edu.lnc.dao;

import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import cn.edu.lnc.domain.User;
import cn.edu.lnc.utils.DataSourceUtils;

public class UserDao {

	//注册用户
	public int register(User user) throws SQLException {
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "insert into user values(?,?,?,?,?,?,?,?,?,?)";
		int count = runner.update(sql, user.getUid(),user.getUsername(),user.getPassword(),user.getName(),user.getEmail(),
				user.getTelephone(),user.getBirthday(),user.getSex(),user.getState(),user.getCode());
		return count;
	}

	//修改激活码状态0改1--激活账户
	public void activeCode(String code) throws SQLException {
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "update user set state = ? where code=?";
		runner.update(sql,1,code);
		
	}

	//查找用户名是否存在
	public int checkUsername(String username) throws SQLException {
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "select count(*) from user where username=?";
		Long query = (Long) runner.query(sql, new ScalarHandler(),username);
		return query.intValue();
	}

	//查找用户名和密码
	public User login(String username, String password) throws SQLException {
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "select * from user where username=? and password=?";
		return runner.query(sql, new BeanHandler<User>(User.class),username,password);
	}

	

}
