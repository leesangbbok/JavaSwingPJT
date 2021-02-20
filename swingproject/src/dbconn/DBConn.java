package dbconn;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConn {
	
	private static final String driver  = "oracle.jdbc.OracleDriver";
	private static final String url        = "jdbc:oracle:thin:@localhost:1521:xe";
	private static final String uid       = "test1";
	private static final String pwd      = "1234";
	
	private static Connection conn = null;
	
	//singleton 패턴 : 프로그램에서 한번 인스턴스 할 수 있도로고 private생성자로 만듬
	private DBConn() {}
	
	public  static Connection getInstance() {
		if(conn != null) {
			return conn;
		}
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, uid, pwd);
		} catch (ClassNotFoundException e) {
			System.out.println(e.getMessage());
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return conn;
	}//getInstance END
	
}
