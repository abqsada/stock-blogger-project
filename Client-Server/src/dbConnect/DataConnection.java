/*
 * This a DataConnection class which implements methods to connect and interact with the database.
 * These methods will be called within the appropriate Class ie. User/Comment/Post to implement various
 * functionality. 
 */

package dbConnect;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.sql.PreparedStatement;

public class DataConnection {
	
	// login information for the database connection
	private static final String USERNAME = "dbuser";
	private static final String PASSWORD = "user";
	private static final String CONN = "jdbc:mysql://localhost/stockblogger";
	
	// instance variables for sql statements or stored procedures (stored proc)
	private static PreparedStatement pstmt = null;
	private static CallableStatement stmt = null;
	private static ResultSet rs = null;
	private static Connection connection = null;
	
	// establishes a connection to the database
	public static Connection getConnection() throws SQLException {
		return connection = DriverManager.getConnection(CONN, USERNAME, PASSWORD);
	}

	// Get user once logged in
	public static User getUser(String username, String password) throws SQLException{
		String query = "select * from users where user_name = \"" + username + "\" and password = \"" + password+"\"";
		pstmt = connection.prepareStatement(query);
		pstmt.executeQuery();
		rs = pstmt.getResultSet();
		User user = new User(rs.getInt(0),	rs.getString(1), rs.getDate(2), rs.getString(3));
		return user;
	}

	// Get user once logged in
	public static User getUserById(int user_id) throws SQLException{
		String query = "select * from users where user_id = " + user_id;
		pstmt = connection.prepareStatement(query);
		pstmt.executeQuery();
		rs = pstmt.getResultSet();
		User user = new User(rs.getInt(0),	rs.getString(1), rs.getDate(2), rs.getString(3));
		return user;
	}
	
	// method called to execute stored proc for select post from user. returns the result set
	public static ResultSet getPostsForUser(int user_id) throws SQLException {
		stmt = connection.prepareCall("{?= call select_posts_from_user(?)");
		stmt.setInt(1, user_id);
		return rs = stmt.executeQuery();
	}
	
	// method called to execute stored proc for selecting all comments for a post. returns the result set
	public static ResultSet getCommentsForPost(int post_id) throws SQLException {
		stmt = connection.prepareCall("{?= call select_comments_from_post(?)");
		stmt.setInt(1, post_id);
		return rs = stmt.executeQuery();
	}
	
	// method called to execute a sql statemen for adding a comment. returns the auto generated PK for comment id
	public static int addComment(int post_id, int user_id, String body, Date date) throws SQLException {
		String query = "insert into comments (post_id, user_id, body, comment_date) values(?,?,?,?)";
		pstmt = connection.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
		pstmt.setInt(1, post_id);
		pstmt.setInt(2, user_id);
		pstmt.setString(3, body);
		pstmt.setDate(4, date);
		pstmt.executeUpdate();
		rs = pstmt.getGeneratedKeys();
		if (rs.next()) {
			return rs.getInt(1);
		}
		else
			return 0;
	}
	
	// method called to execute a sql statemen for adding a post. returns the auto generated PK for post id
	public static int addPost(int user_id, String title, String body, Date date) throws SQLException {
		String query = "insert into posts (user_id, title,body,post_date) values(?,?,?,?)";
		pstmt = connection.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
		pstmt.setInt(1, user_id);
		pstmt.setString(2, title);
		pstmt.setString(3, body);
		pstmt.setDate(4, date);
		pstmt.executeUpdate();
		rs = pstmt.getGeneratedKeys();
		if (rs.next()) {
			return rs.getInt(1);
		}
		else
			return 0;
	}
	
	// method called to execute a sql statement for adding a user. returns the auto generated PK for user id
	public static int addUser(String name, Date date, String password) throws SQLException {
		String query = "insert into users (user_name, date_user_joined,password) values(?,?,?)";
		pstmt = connection.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
		pstmt.setString(1, name);
		pstmt.setDate(2, date);
		pstmt.setString(3, password);
		pstmt.executeUpdate();
		rs = pstmt.getGeneratedKeys();
		if (rs.next()) {
			return rs.getInt(1);
		}
		else
			return 0;
	}
	
	// method called to execute the stored proc for editing a user
	public static void editUser(int user_id, String name, Date date, String password) throws SQLException {
		stmt = connection.prepareCall("{ call edit_user(?,?,?,?) }");
		stmt.setInt(1,user_id);
		stmt.setString(2, name);
		stmt.setDate(3, date);
		stmt.setString(4, password);
		stmt.executeUpdate();
	}
	
	// method called to execute the stored proc for editing a post
	public static void editPost(int post_id, int user_id, String title, String body, Date date) throws SQLException {
		stmt = connection.prepareCall("{ call edit_post(?,?,?,?,?) }");
		stmt.setInt(1,post_id);
		stmt.setInt(2,user_id);
		stmt.setString(3, title);
		stmt.setString(4, body);
		stmt.setDate(5, date);
		stmt.executeUpdate();
	}
	
	// method called to execute the stored proc for editing a comment
	public static void editComment(int comment_id, int post_id, int user_id, String body, Date date) throws SQLException {
		stmt = connection.prepareCall("{ call edit_post(?,?,?,?,?) }");
		stmt.setInt(1,comment_id);
		stmt.setInt(2,post_id);
		stmt.setInt(3,user_id);
		stmt.setString(4, body);
		stmt.setDate(5, date);
		stmt.executeUpdate();
	}
	
	// method called to execute the stored proc for searching a post
	public static ResultSet searchPosts(String keywords) throws SQLException {
		stmt = connection.prepareCall("{?= call select_posts_from_user(?)");
		stmt.setString(1, keywords);
		return rs = stmt.executeQuery();
	}

}
