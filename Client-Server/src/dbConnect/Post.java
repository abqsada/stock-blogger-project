/* 
 This is a class that represents a Post entity as described in the database.
 This class implements methods to retrieve a Post from the database, edit a Post in the database,
 query the database for all Comments associated with a specific Post, and creating a JSON object 
 from the Post object.
 */

package dbConnect;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.google.gson.JsonObject;

public class Post {

	// Instance variables to represent the different attributes of a Post
	private int postId;
	private int userId;
	private String title;
	private String body;
	private Date postDate;
	
	// Constructor for creating an existing Post object
	public Post (int postId, int userId, String title, String body, Date postDate) throws SQLException {
		this.postId = postId;
		this.userId = userId;
		this.title = title;
		this.body = body;
		this.postDate = postDate;
	}
	
	// Constructor for creating a new Post and adding it to the database
	public Post (int userId, String title, String body, Date postDate) throws SQLException {
		this.userId = userId;
		this.title = title;
		this.body = body;
		this.postDate = postDate;
		this.postId = DataConnection.addPost(userId, title, body, postDate);
	}
	
	// Method for editing a Post object and record in the database
	public void editPost(int userId, String title, String body, Date postDate) throws SQLException {
		this.userId = userId;
		this.title = title;
		this.body = body;
		this.postDate = postDate;
		DataConnection.editPost(postId, userId, title, body, postDate);
	}
	
	// method for retrieving all associated Comments for the specified Post
	public ResultSet getCommentsForPost() throws SQLException {
		ResultSet rs = DataConnection.getCommentsForPost(postId); 
		return rs ;
	}
	
	// getters and setters for Post object.
	// setters update the database as well
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
	
	// method to create a JSON object from the Post object
    public JsonObject toJsonObj() {
    	JsonObject job = new JsonObject();
    	job.addProperty("postId", this.postId);
    	job.addProperty("userId", this.userId);
    	job.addProperty("title", this.title);
    	job.addProperty("body",  this.body);
    	job.addProperty("postDate", this.postDate.toString());
        return job;
    }
}