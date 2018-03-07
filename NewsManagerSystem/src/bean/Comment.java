package bean;
/**
 * 评论实体类
 * @author DaiJinchi
 *	2017-07-15
 */
public class Comment {
	private String cid;//评论id
	private String cnid;//评论的新闻id
	private String ccontent;//评论内容
	private String cdate;//评论日期
	private String cip;//评论者的ip
	private String cauthor;//评论的作者
	public Comment(String cid, String cnid, String ccontent, String cdate,
			String cip, String cauthor) {
		super();
		this.cid = cid;
		this.cnid = cnid;
		this.ccontent = ccontent;
		this.cdate = cdate;
		this.cip = cip;
		this.cauthor = cauthor;
	}
	public Comment() {
		super();
	}
	public String getCid() {
		return cid;
	}
	public void setCid(String cid) {
		this.cid = cid;
	}
	public String getCnid() {
		return cnid;
	}
	public void setCnid(String cnid) {
		this.cnid = cnid;
	}
	public String getCcontent() {
		return ccontent;
	}
	public void setCcontent(String ccontent) {
		this.ccontent = ccontent;
	}
	public String getCdate() {
		return cdate;
	}
	public void setCdate(String cdate) {
		this.cdate = cdate;
	}
	public String getCip() {
		return cip;
	}
	public void setCip(String cip) {
		this.cip = cip;
	}
	public String getCauthor() {
		return cauthor;
	}
	public void setCauthor(String cauthor) {
		this.cauthor = cauthor;
	}
	@Override
	public String toString() {
		return "Comment [cid=" + cid + ", cnid=" + cnid + ", ccontent="
				+ ccontent + ", cdate=" + cdate + ", cip=" + cip + ", cauthor="
				+ cauthor + "]";
	}
	
}
