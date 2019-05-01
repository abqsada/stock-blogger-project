package dbConnect;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.google.gson.JsonObject;

public class User {

	private int userId;
	private String userName;
	private Date dateJoined;
	private String password;

	public User(int userId, String userName, Date dateJoined, String password) {
		this.userId = userId;
		this.userName = userName;
		this. dateJoined = dateJoined;
		this.password = password;
	}
	
	public User (String userName, Date dateJoined, String password) throws SQLException {
		this.userName = userName;
		this.dateJoined = dateJoined;
		this.password = password;
		this.userId = DataConnection.addUser(userName, dateJoined, password);
	}
	
	public void editUser(String userName, Date dateJoined, String password) throws SQLException {
		this.userName = userName;
		this.dateJoined = dateJoined;
		this.password = password;
		DataConnection.editUser(userId, userName, dateJoined, password);
	}
	
	public ResultSet getPostsForUser() throws SQLException {
		ResultSet rs = DataConnection.getPostsForUser(userId);
		return rs ;
	}
	
	
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) throws SQLException {
		this.userName = userName;
		editUser(userName, dateJoined, password);
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) throws SQLException {
		this.password = password;
		editUser(userName, dateJoined, password);
	}

	public int getUserId() {
		return userId;
	}

	public Date getDateJoined() {
		return dateJoined;
	}

    public JsonObject toJsonObj() {
    	JsonObject job = new JsonObject();
    	job.addProperty("userId", this.userId);
    	job.addProperty("userName", this.userName);
    	job.addProperty("password",  this.password);
    	
    	//job.addProperty("dateJoined", this.dateJoined);
        return job;
    }

}
