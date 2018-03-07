package dao;

import java.util.List;

import bean.Topic;

/**
 * 主题数据库控制层
 * @author DaiJinChi
 *	2017-07-17
 */

public interface TopicDao {
	//分页查询所有主题，返回List集合
	public List findAllTopic(int start,int num);
	//查询所有主题，返回List集合
	public List findAllTopic();
	//添加主题
	public boolean addTopic(String tname);
	//根据id删除主题
	public boolean delTopic(int tid);
	//根据id修改主题
	public boolean updateTopic(int tid,String tname);
	//查询主题总条数
	public int topicCount();
}
