/* 
 This is a class that represents a Comment entity as described in the database.
 This class implements methods to retrieve a Comment from the database, edit a Comment in the database,
 and creating a JSON object from the Comment object.
 */

package dbConnect;

import java.sql.Date;
import java.sql.SQLException;

import com.google.gson.JsonObject;

public class Comment {

	// Instance variables to represent the different attributes of a Comment
	private int commentId;
    private int postId;
    private int userId; // Change to: String userName in DB
    private String body;
    private Date commentDate;

    // Constructor for creating an existing Comment object
    public Comment(int commentId, int postID, int userID, String body, Date commentDate) throws SQLException{
    	this.commentId = commentId;
        this.postId = postID;
        this.userId = userID;
        this.body = body;
        this.commentDate = commentDate;
    }
    
    // Constructor for creating a new Comment and adding it to the database
    public Comment(int postID, int userID, String body, Date commentDate) throws SQLException{
        this.postId = postID;
        this.userId = userID;
        this.body = body;
        this.commentDate = commentDate;
        this.commentId = DataConnection.addComment(postID, userID, body, commentDate);
    }

    // Method for editing a Comment object and record in the database
    public void editComment(int postID, int userID, String body, Date commentDate) throws SQLException{
        this.postId = postID;
        this.userId = userID;
        this.body = body;
        this.commentDate = commentDate;
        DataConnection.editComment(commentId, postID, userID, body, commentDate);
    }

    // getters and setters for User object.
 	// setters update the database as well
    public int getCommentID() {
        return commentId;
    }

    public int getPostID() {
        return postId;
    }

    public int getUserID() {
        return userId;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) throws SQLException {
        this.body = body;
        editComment(postId, userId, body, commentDate);
    }

    public Date getCommentDate() {
        return commentDate;
    }

    public void setCommentDate(Date commentDate) throws SQLException {
        this.commentDate = commentDate;
        editComment(postId, userId, body, commentDate);
    }
    
    // method to create a JSON object from the Comment object
    public JsonObject toJsonObj() {
    	JsonObject job = new JsonObject();
    	job.addProperty("commentId", this.commentId);
    	job.addProperty("postId", this.postId);
    	job.addProperty("userId", this.userId);
    	job.addProperty("body",  this.body);
    	job.addProperty("commentDate", this.commentDate.toString());
        return job;
    }
}