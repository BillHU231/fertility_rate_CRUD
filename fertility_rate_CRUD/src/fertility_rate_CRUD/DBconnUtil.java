package fertility_rate_CRUD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBconnUtil {
	private Connection conn;

	// 開連線
	public Connection getconnection() throws SQLException {
		String url = "jdbc:sqlserver://localhost:1433;database=packageDB;user=sa;password=P@ssw0rd";

		conn = DriverManager.getConnection(url);
//		System.out.println("connection finish");
		return conn;

	}

	// 關連線
	public void closeconnection() throws SQLException {
		if (conn != null) {
			conn.close();
            System.out.println("close connection finish");
		}
	}
}
