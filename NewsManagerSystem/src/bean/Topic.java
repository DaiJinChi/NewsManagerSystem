package bean;
/**
 * 主题实体类
 * @author DaiJinchi
 *	2017-07-15
 */
public class Topic {
	private int tid;//主题id
	private String tname;//主题名字
	private String newscount;//主题分类下的新闻数量
	public int getTid() {
		return tid;
	}
	public void setTid(int tid) {
		this.tid = tid;
	}
	public String getTname() {
		return tname;
	}
	public void setTname(String tname) {
		this.tname = tname;
	}
	public String getNewscount() {
		return newscount;
	}
	public void setNewscount(String newscount) {
		this.newscount = newscount;
	}
	public Topic(int tid, String tname) {
		super();
		this.tid = tid;
		this.tname = tname;
	}
	public Topic(int tid, String tname, String newscount) {
		super();
		this.tid = tid;
		this.tname = tname;
		this.newscount = newscount;
	}
	public Topic() {
		super();
	}
	
	
}
