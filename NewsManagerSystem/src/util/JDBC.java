package util;

import java.sql.Connection;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
/**
 * 数据库连接
 * @author DaiJinchi
 *	2017-07-18
 */
public class JDBC {
	public Connection conn = null;
	//创建连接
	public void openConn(){
		try {
			Context context = new InitialContext();
			Context context2 = (Context) context.lookup("java:comp/env");
			DataSource ds = (DataSource) context2.lookup("jdbc/databaseWeb");
			conn = ds.getConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	//关闭连接
	public void closeConn(){
		try {
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
