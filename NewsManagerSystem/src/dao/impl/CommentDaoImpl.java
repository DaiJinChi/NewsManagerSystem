package dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import util.JDBC;
import bean.Comment;
import dao.CommentDao;
/**
 * 评论信息dao层的实现层
 * @author DaiJinchi
 *	2017-07-15
 */
public class CommentDaoImpl implements CommentDao{
	//根据新闻id查询相应评论
	@Override
	public List<Comment> findCommentById(String cnid) {
		JDBC jdbc = new JDBC();
		List<Comment> list = new ArrayList<Comment>();
		jdbc.openConn();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "select * from ucomments where cnid=?;";
		try {
			ps = jdbc.conn.prepareStatement(sql);
			ps.setString(1, cnid);
			rs = ps.executeQuery();
			while (rs.next()){
				Comment c = new Comment();
				c.setCid(rs.getInt("cid")+"");
				c.setCnid(rs.getInt("cnid")+"");
				c.setCcontent(rs.getString("ccontent"));
				c.setCauthor(rs.getString("cauthor"));
				list.add(c);
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
	//删除评论
	@Override
	public boolean delComment(String cid) {
		JDBC jdbc = new JDBC();
		jdbc.openConn();
		PreparedStatement ps = null;
		boolean flag = false;
		String sql = "delete from ucomments where cid=?";
		try {
			ps = jdbc.conn.prepareStatement(sql);
			ps.setString(1, cid);
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

}
