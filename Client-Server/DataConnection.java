import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;

public class DataConnection {
	
	private static final String USERNAME = "dbuser";
	private static final String PASSWORD = "user";
	private static final String CONN = "jdbc:mysql://localhost/stockblogger";
	
	private static PreparedStatement stmt = null;
	private static ResultSet rs = null;
	private static Connection connection = null;
	
	public static Connection getConnection() throws SQLException {
		return connection = DriverManager.getConnection(CONN, USERNAME, PASSWORD);
	}
	
	public static ResultSet getPostsForUser(String user_id) throws SQLException {
		stmt = connection.prepareStatement("SELECT * FROM posts WHERE user_id = ?");
		stmt.setString(1, user_id);
		return rs = stmt.executeQuery();
	}

}
