/* 
 This is a class that represents a User entity as described in the database.
 This class implements methods to retrieve a User from the database, edit a User in the database,
 query the database for all Posts associated with a specific User, and creating a JSON object 
 from the User object.
 */

package dbConnect;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.google.gson.JsonObject;

public class User {

	// Instance variables to represent the different attributes of a User
	private int userId;
	private String userName;
	private Date dateJoined;
	private String password;

	// Constructor for creating an existing User object
	public User(int userId, String userName, Date dateJoined, String password) {
		this.userId = userId;
		this.userName = userName;
		this. dateJoined = dateJoined;
		this.password = password;
	}
	
	// Constructor for creating a new User and adding it to the database
	public User (String userName, Date dateJoined, String password) throws SQLException {
		this.userName = userName;
		this.dateJoined = dateJoined;
		this.password = password;
		this.userId = DataConnection.addUser(userName, dateJoined, password);
	}
	
	// Method for editing a User object and record in the database
	public void editUser(String userName, Date dateJoined, String password) throws SQLException {
		this.userName = userName;
		this.dateJoined = dateJoined;
		this.password = password;
		DataConnection.editUser(userId, userName, dateJoined, password);
	}
	
	// method for retrieving all associated Posts for the specified User
	public ResultSet getPostsForUser() throws SQLException {
		ResultSet rs = DataConnection.getPostsForUser(userId);
		return rs ;
	}
	
	// getters and setters for User object.
	// setters update the database as well
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

	// method to create a JSON object from the User object
    public JsonObject toJsonObj() {
    	JsonObject job = new JsonObject();
    	job.addProperty("userId", this.userId);
    	job.addProperty("userName", this.userName);
    	job.addProperty("password",  this.password);
    	job.addProperty("dateJoined", this.dateJoined.toString());
        return job;
    }

    public User() {}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public void setDate(Date date) {
		this.dateJoined = date;
	}

}
