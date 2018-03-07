package dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.Connection;

import bean.News;
import bean.Topic;
import util.JDBC;
import dao.NewsDao;
import dao.TopicDao;
/**
 * 主题信息dao层的实现层
 * @author DaiJinchi
 *	2017-07-15
 */
public class TopicDaoImpl implements TopicDao{
	//分页查询所有主题
	@Override
	public List findAllTopic(int start,int num) {
		NewsDao nd = new NewsDaoImpl();
		JDBC jdbc = new JDBC();
		jdbc.openConn();
		List<Topic> list = new ArrayList<Topic>();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "select * from topic limit ?,?;";
		try {
			ps = jdbc.conn.prepareStatement(sql);
			ps.setInt(1, start);
			ps.setInt(2, num);
			rs = ps.executeQuery();
			while (rs.next()){
				Topic t = new Topic();
				t.setTid(rs.getInt("tid"));
				t.setTname(rs.getString("tname"));
				t.setNewscount(nd.findNewsCountBytid(rs.getInt("tid")+"")+"");
				list.add(t);
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
		return list;
	}
	//查询所有主题
	public List findAllTopic() {
		JDBC jdbc = new JDBC();
		jdbc.openConn();
		List<Topic> list = new ArrayList<Topic>();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "select * from topic;";
		try {
			ps = jdbc.conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()){
				Topic t = new Topic();
				t.setTid(rs.getInt("tid"));
				t.setTname(rs.getString("tname"));
				list.add(t);
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
		return list;
	}
	//添加主题
	@Override
	public boolean addTopic(String tname) {
		JDBC jdbc = new JDBC();
		PreparedStatement ps = null;
		jdbc.openConn();
		boolean flag = false;
		String sql = "insert into topic(tname) values (?);";
		try {
			ps = jdbc.conn.prepareStatement(sql);
			ps.setString(1, tname);
			int i = ps.executeUpdate();
			if (i>0){
				flag = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}
	//删除主题
	//（数据库创建外键时指定了ON DELETE CASCADE，删除主题时也会删除相应分类下的新闻）
	@Override
	public boolean delTopic(int tid) {
		JDBC jdbc = new JDBC();
		PreparedStatement ps = null;
		jdbc.openConn();
		boolean flag = false;
		String sql = "delete from topic where tid=?;";
		try {
			ps = jdbc.conn.prepareStatement(sql);
			ps.setInt(1, tid);
			int i = ps.executeUpdate();
			if (i>0){
				flag = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}
	//修改主题
	@Override
	public boolean updateTopic(int tid,String tname) {
		JDBC jdbc = new JDBC();
		PreparedStatement ps = null;
		jdbc.openConn();
		boolean flag = false;
		String sql = "update topic set tname=? where tid=?;";
		try {
			ps = jdbc.conn.prepareStatement(sql);
			ps.setString(1, tname);
			ps.setInt(2, tid);
			int i = ps.executeUpdate();
			if (i>0){
				flag = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}
	//查找主题总数
	public int topicCount() {
		JDBC jdbc = new JDBC();
		jdbc.openConn();
		PreparedStatement ps = null;
		int i = 0;
		String sql = "select count(*) from topic;";
		try {
			ps = jdbc.conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			if (rs.next()){
				i = rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if (ps != null){
				try {
					ps.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			jdbc.closeConn();
		}
		return i;
	}

}
