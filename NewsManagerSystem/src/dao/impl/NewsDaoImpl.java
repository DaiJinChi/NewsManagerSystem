package dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import util.JDBC;
import bean.News;
import dao.NewsDao;
/**
 * 新闻信息dao层的实现层
 * @author DaiJinchi
 *
 */
public class NewsDaoImpl implements NewsDao {
	//添加新闻
	@Override
	public boolean addNews(News news) {
		JDBC jdbc = new JDBC();
		// TODO Auto-generated method stub
		jdbc.openConn();
		//获取当前时间
		Date date = new Date();
		Timestamp ts = new Timestamp(date.getTime());
		PreparedStatement ps = null;
		boolean flag = false;
		String sql = "insert into News (ntid,ntitle,nauthor,ncreatedate,ncontent,nsummary) values (?,?,?,?,?,?)";
		try {
			ps = jdbc.conn.prepareStatement(sql);
			ps.setString(1, news.getNtid());
			ps.setString(2, news.getNtitle());
			ps.setString(3, news.getNauthor());
			ps.setTimestamp(4, ts);
			ps.setString(5, news.getNcontent());
			ps.setString(6, news.getNsummary());
			int i = ps.executeUpdate();
			if (i>0){
				flag = true;
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
		return flag;
	}
	
	//删除新闻
	public boolean deleteNews(int nid){
		JDBC jdbc = new JDBC();
		jdbc.openConn();
		PreparedStatement ps = null;
		boolean flag = false;
		String sql = "delete from news where nid=?";
		try {
			ps = jdbc.conn.prepareStatement(sql);
			ps.setInt(1, nid);
			int i = ps.executeUpdate();
			if (i>0){
				flag = true;
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
		return flag;
	}
	//根据id查询用户信息
	public News findNewsById(int nid){
		JDBC jdbc = new JDBC();
		jdbc.openConn();
		News news = new News();
		PreparedStatement ps = null;
		boolean flag = false;
		String sql = "select * from news where nid=?";
		try {
			ps = jdbc.conn.prepareStatement(sql);
			ps.setInt(1, nid);
			ResultSet rs = ps.executeQuery();
			if (rs.next()){
				news.setNid(rs.getInt("nid")+"");
				news.setNtid(rs.getInt("ntid")+"");
				news.setNtitle(rs.getString("ntitle"));
				news.setNauthor(rs.getString("nauthor"));
				news.setNcontent(rs.getString("ncontent"));
				news.setNsummary(rs.getString("nsummary"));
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
		return news;
	}
	
	//修改新闻
	public boolean modifyNews(News news){
		JDBC jdbc = new JDBC();
		jdbc.openConn();
		//获取当前时间
		Date date = new Date();
		Timestamp ts = new Timestamp(date.getTime());
		PreparedStatement ps = null;
		boolean flag = false;
		String sql = 
		"update news set ntid=?,ntitle=?,nauthor=?,npicpath=?,ncontent=?,nmodifydate=?,nsummary=? where nid=?;";
		try {
			ps = jdbc.conn.prepareStatement(sql);
			ps.setString(1, news.getNtid());
			ps.setString(2, news.getNtitle());
			ps.setString(3, news.getNauthor());
			ps.setString(4, news.getNpicpath());
			ps.setString(5, news.getNcontent());
			ps.setTimestamp(6, ts);
			ps.setString(7, news.getNsummary());
			ps.setString(8, news.getNid());
			int i = ps.executeUpdate();
			if (i>0){
				flag = true;
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
		return flag;
	}
	//查询新闻总条数
	@Override
	public int findNewsCount() {
		JDBC jdbc = new JDBC();
		jdbc.openConn();
		PreparedStatement ps = null;
		int i = 0;
		String sql = "select count(*) from news;";
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
	//分页查询新闻信息
	@Override
	public List<News> findAllNews(int start, int num) {
		JDBC jdbc = new JDBC();
		List<News> list = new ArrayList<News>();
		jdbc.openConn();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "select * from news limit ?,?;";
		try {
			ps = jdbc.conn.prepareStatement(sql);
			ps.setInt(1, start);
			ps.setInt(2, num);
			rs = ps.executeQuery();
			while (rs.next()){
				News n = new News();
				n.setNid(rs.getInt("nid")+"");
				n.setNtitle(rs.getString("ntitle"));
				n.setNauthor(rs.getString("nauthor"));
				n.setNcreatedate(rs.getString("ncreatedate"));
				list.add(n);
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
	//按主题id分页查询新闻
	@Override
	public List<News> findNewsByTid(int start, int num, int ntid) {
		JDBC jdbc = new JDBC();
		List<News> list = new ArrayList<News>();
		jdbc.openConn();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "select * from news where ntid=? limit ?,?;";
		try {
			ps = jdbc.conn.prepareStatement(sql);
			ps.setInt(1, ntid);
			ps.setInt(2, start);
			ps.setInt(3, num);
			rs = ps.executeQuery();
			while (rs.next()){
				News n = new News();
				n.setNid(rs.getInt("nid")+"");
				n.setNtitle(rs.getString("ntitle"));
				n.setNauthor(rs.getString("nauthor"));
				n.setNcreatedate(rs.getString("ncreatedate"));
				list.add(n);
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
	//根据主题id查询所属新闻条数
	@Override
	public int findNewsCountBytid(String ntid) {
		JDBC jdbc = new JDBC();
		jdbc.openConn();
		PreparedStatement ps = null;
		int i = 0;
		String sql = "select count(*) from news where ntid=?;";
		try {
			ps = jdbc.conn.prepareStatement(sql);
			ps.setString(1, ntid);
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
