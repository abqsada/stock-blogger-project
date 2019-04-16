import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;

public class DataConnection {
	
	private static final String USERNAME = "dbuser";
	private static final String PASSWORD = "user";
	private static final String CONN = "jdbc:mysql://localhost/stockblogger";
	
	private static CallableStatement stmt = null;
	private static ResultSet rs = null;
	private static Connection connection = null;
	
	public static Connection getConnection() throws SQLException {
		return connection = DriverManager.getConnection(CONN, USERNAME, PASSWORD);
	}
	
	// method called to execute stored proc for select post from user. returns the result set
	public static ResultSet getPostsForUser(int user_id) throws SQLException {
		stmt = connection.prepareCall("{?= call select_posts_from_user(?)");
		stmt.setInt(1, user_id);
		return rs = stmt.executeQuery();
	}
	
	// method called to execute the stored proc for adding a comment. returns the auto generated PK for comment id
	public static int addComment(int post_id, int user_id, String body, String date) throws SQLException {
		stmt = connection.prepareCall("{ call add_comment(?,?,?,?) }");
		stmt.setInt(1, post_id);
		stmt.setInt(2, user_id);
		stmt.setString(3, body);
		stmt.setString(4, date);
		stmt.executeUpdate();
		rs = stmt.getGeneratedKeys();
		return rs.getInt(1);
	}
	
	public static int addPost(int user_id, String title, String body, Date date) throws SQLException {
		stmt = connection.prepareCall("{ call add_post(?,?,?,?) }");
		stmt.setInt(1, user_id);
		stmt.setString(2, title);
		stmt.setString(3, body);
		stmt.setDate(4, date);
		stmt.executeUpdate();
		rs = stmt.getGeneratedKeys();
		return rs.getInt(1);
	}
	
	public static int addUser(String name, Date date, String password) throws SQLException {
		stmt = connection.prepareCall("{ call add_user(?,?,?) }");
		stmt.setString(1, name);
		stmt.setDate(2, date);
		stmt.setString(3, password);
		stmt.executeUpdate();
		rs = stmt.getGeneratedKeys();
		return rs.getInt(1);
	}
	
	

}
