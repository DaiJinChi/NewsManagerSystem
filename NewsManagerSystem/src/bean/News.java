package bean;
/**
 * 新闻实体类
 * @author DaiJinChi
 *	2017-07-15
 */
public class News {
	private String nid;//新闻id
	private String ntid;//主题id
	private String ntitle;//标题id
	private String nauthor;//新闻作者
	private String ncreatedate;//新闻创建时间
	private String npicpath;//附件路径
	private String ncontent;//新闻内容
	private String nmodifydate;//最后修改时间
	private String nsummary;//新闻摘要
	public News(String nid, String ntid, String ntitle, String nauthor,
			String ncreatedate, String npicpath, String ncontent,
			String nmodifydate, String nsummary) {
		super();
		this.nid = nid;
		this.ntid = ntid;
		this.ntitle = ntitle;
		this.nauthor = nauthor;
		this.ncreatedate = ncreatedate;
		this.npicpath = npicpath;
		this.ncontent = ncontent;
		this.nmodifydate = nmodifydate;
		this.nsummary = nsummary;
	}
	
	public News() {
		super();
	}
	public String getNid() {
		return nid;
	}
	public void setNid(String nid) {
		this.nid = nid;
	}
	public String getNtid() {
		return ntid;
	}
	public void setNtid(String ntid) {
		this.ntid = ntid;
	}
	public String getNtitle() {
		return ntitle;
	}
	public void setNtitle(String ntitle) {
		this.ntitle = ntitle;
	}
	public String getNauthor() {
		return nauthor;
	}
	public void setNauthor(String nauthor) {
		this.nauthor = nauthor;
	}
	public String getNcreatedate() {
		return ncreatedate;
	}
	public void setNcreatedate(String ncreatedate) {
		this.ncreatedate = ncreatedate;
	}
	public String getNpicpath() {
		return npicpath;
	}
	public void setNpicpath(String npicpath) {
		this.npicpath = npicpath;
	}
	public String getNcontent() {
		return ncontent;
	}
	public void setNcontent(String ncontent) {
		this.ncontent = ncontent;
	}
	public String getNmodifydate() {
		return nmodifydate;
	}
	public void setNmodifydate(String nmodifydate) {
		this.nmodifydate = nmodifydate;
	}
	public String getNsummary() {
		return nsummary;
	}
	public void setNsummary(String nsummary) {
		this.nsummary = nsummary;
	}
	
}
