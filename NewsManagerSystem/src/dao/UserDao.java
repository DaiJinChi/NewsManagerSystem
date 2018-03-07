package dao;

import bean.User;

/**
 * 用户信息dao层
 * @author DaiJinChi
 *	2017-07-15
 */

public interface UserDao {
	//检查用户是否存在
	public boolean checkUser(User user);
}
