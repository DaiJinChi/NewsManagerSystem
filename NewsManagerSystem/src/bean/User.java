package bean;
/**
 * 用户实体类
 * @author DaiJinChi
 *	2017-07-15
 */

public class User {
	private int id;//用户id
	private String username;//用户姓名
	private String upassword;//用户密码
	public User(String username, String upassword) {
		super();
		this.username = username;
		this.upassword = upassword;
	}
	public User() {
		super();
	}
	int getId() {
		return id;
	}
	void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getUpassword() {
		return upassword;
	}
	public void setUpassword(String upassword) {
		this.upassword = upassword;
	}
	
}
