package dbConnect;

import java.sql.Date;
import java.sql.SQLException;

public class Comment {

	private int commentId;
    private int postId;
    private int userId; // Change to: String userName in DB
    private String body;
    private Date commentDate;


    public Comment(int postID, int userID, String body, Date commentDate) throws SQLException{
        this.postId = postID;
        this.userId = userID;
        this.body = body;
        this.commentDate = commentDate;
        this.commentId = DataConnection.addComment(postID, userID, body, commentDate);
    }

    public void editComment(int postID, int userID, String body, Date commentDate) throws SQLException{
        this.postId = postID;
        this.userId = userID;
        this.body = body;
        this.commentDate = commentDate;
        DataConnection.editComment(commentId, postID, userID, body, commentDate);
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
}