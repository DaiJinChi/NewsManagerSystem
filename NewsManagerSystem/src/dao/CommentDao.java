package dao;

import java.util.List;

import bean.Comment;
/**
 * 评论数据库控制层
 * @author DaiJinChi
 *	2017-7-20
 */
public interface CommentDao {
	//根据新闻id查询相应评论，返回评论实体类的List集合
	public List<Comment> findCommentById(String cnid);
	//根据评论id删除评论
	public boolean delComment(String cid);
}
