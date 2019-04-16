package dbConnect;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataConnection {
	
	private static final String USERNAME = "dbuser";
	private static final String PASSWORD = "user";
	private static final String CONN = "jdbc:mysql://localhost/stockblogger";
	
	public static Connection getConnection() throws SQLException {
		return DriverManager.getConnection(CONN, USERNAME, PASSWORD);
	}

}
