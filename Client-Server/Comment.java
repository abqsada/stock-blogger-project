import java.sql.Date;
import java.sql.SQLException;

public class Comment {

    private int postID;
    private int userID; // Change to: String userName in DB
    private String body;
    private Date commentDate;


    public Comment(int postID, int userID, String body, Date commentDate) throws SQLException{
        setPostID(postID);
        setUserID(userID);
        setBody(body);
        setCommentDate(commentDate);
        DataConnection.addComment(postID, userID, body, commentDate);
    }

    public void editComment(int postID, int userID, String body, Date commentDate) throws SQLException{
        setPostID(postID);
        setUserID(userID);
        setBody(body);
        setCommentDate(commentDate);
        DataConnection.editUser(postID, userID, body, commentDate);
    }

    public int getPostID() {
        return postID;
    }

    public void setPostID(int postID) throws SQLException {
        this.postID = postID;

    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) throws SQLException {
        this.userID = userID;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) throws SQLException {
        this.body = body;
    }

    public Date getCommentDate() {
        return commentDate;
    }

    public void setCommentDate(Date commentDate) throws SQLException {
        this.commentDate = commentDate;
    }
}