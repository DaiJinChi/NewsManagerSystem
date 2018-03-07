package dao;

import java.util.List;

import bean.News;
/**
 * 新闻信息dao层
 * @author DaiJinchi
 *	2017-07-15
 */
public interface NewsDao {
	//分页查询新闻信息，返回新闻实体类的List集合
	public List<News> findAllNews(int start,int num);
	//添加新闻信息
	public boolean addNews(News news);
	//删除新闻
	public boolean deleteNews(int nid);
	//根据id查找用户信息，返回新闻实体类
	public News findNewsById(int nid);
	//修改用户信息
	public boolean modifyNews (News news);
	//查询新闻条数
	public int findNewsCount();
	//按新闻所属主题id分页查询新闻信息，返回新闻实体类的List集合
	public List<News> findNewsByTid(int start,int num,int ntid);
	//根据主题id查询所属新闻条数
	public int findNewsCountBytid(String i);
}
