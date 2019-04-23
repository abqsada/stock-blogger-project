import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Post {

	private int postId;
	private int userId;
	private String title;
	private String body;
	private Date postDate;
	
	public Post (int userId, String title, String body, Date postDate) throws SQLException {
		this.userId = userId;
		this.title = title;
		this.body = body;
		this.postDate = postDate;
		this.postId = DataConnection.addPost(userId, title, body, postDate);
	}
	
	public void editPost(int userId, String title, String body, Date postDate) throws SQLException {
		this.userId = userId;
		this.title = title;
		this.body = body;
		this.postDate = postDate;
		DataConnection.editPost(postId, userId, title, body, postDate);
	}
	
	public ResultSet getCommentsForPost() throws SQLException {
		ResultSet rs = ""; // we need a comments for post Stored procedure and dataconnection method
		return rs ;
	}
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) throws SQLException {
		this.title = title;
		editPost(userId, title, body, postDate);
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) throws SQLException {
		this.body = body;
		editPost(userId, title, body, postDate);
	}

	public Date getPostDate() {
		return postDate;
	}

	public void setPostDate(Date postDate) throws SQLException {
		this.postDate = postDate;
		editPost(userId, title, body, postDate);
	}

	public int getPostId() {
		return postId;
	}

	public int getUserId() {
		return userId;
	}
}