package moroom.Model;
import java.sql.Connection;
import java.sql.DriverManager;

//데이터베이스 공유를 위한 연결 클래스
public class ConnectionPool {

	
	static Connection conn = null;
	private String driver = "oracle.jdbc.driver.OracleDriver";
	private String jdbc_url="jdbc:oracle:thin:@61.81.98.108:1521:iot3";
	private String db_id="mr";
	private String db_pwd="pass";
	
	private ConnectionPool() throws Exception {
		Class.forName(driver);
		conn = DriverManager.getConnection(jdbc_url, db_id, db_pwd);
	}
	
	public static Connection getInstance() throws Exception
	{
		if(conn ==null)
		{
			ConnectionPool col = new ConnectionPool();
		}
		
		return conn;
	}
}