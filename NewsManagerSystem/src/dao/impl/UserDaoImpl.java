package dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import util.JDBC;
import bean.User;
import dao.UserDao;

/**
 * 
 * 用户信息dao层的实现层
 * @author DaiJinChi
 *	2017-07-15
 */

public class UserDaoImpl implements UserDao{
	//检查用户是否存在
	@Override
	public boolean checkUser(User user) {
		JDBC jdbc = new JDBC();
		jdbc.openConn();
		PreparedStatement ps = null;
		ResultSet rs = null;
		boolean flag = false;
		String sql = "select * from user where username=? and upassword=?";
		try {
			ps = jdbc.conn.prepareStatement(sql);
			ps.setString(1, user.getUsername());
			ps.setString(2, user.getUpassword());
			rs = ps.executeQuery();
			if (rs.next()){
				flag = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if (rs != null){
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (ps != null){
				try {
					ps.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			jdbc.closeConn();
		}
		return flag;
	}
	
}
