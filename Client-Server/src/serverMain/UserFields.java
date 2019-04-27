/**
 * 
 */
package serverMain;

//import java.time.LocalDateTime;
//import com.google.gson.Gson;
//import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
//import java.util.Date;
import java.sql.Date;
import java.sql.SQLException;

/**
 * @author Ruth
 * THis class defines the fields of the user account POJO.
 *   A UserFields object will be used to define the structure 
 *   of the JSON object 'User'
 */
public class UserFields {
    private String userName;
    private String password;
    private int userId;
    //private Date dateUserJoined;
    //If a field is marked transient, it’s ignored by default and not included 
    // in the JSON serialization or deserialization.
    
    //Actually post,postTitle, & comment should be arrays
    private transient String post;
    private transient String postTitle;
    private transient String comment;
	//Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public UserFields() {
		this.userName = "";
		this.password = "";
		this.userId = 0;
		//this.dateUserJoined = Date.valueOf("2019-30-30");
		this.post = "";
		this.postTitle = "";
		this.comment = "";
	}
    
    //public UserFields(String userName, String password, int userId, Date dt) {
	public UserFields(String userName, String password, int userId) {
		this.userName = userName;
		this.password = password;
		this.userId = userId;
		//this.dateUserJoined = dt;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	/*
	public Date getdateUserJoined() {
		return dateUserJoined;
	}

	public void setdateUserJoined(String dateString) {
		this.dateUserJoined = Date.valueOf(dateString);
	}
	*/

	/*
	public LocalDateTime getDateUserJoined() {
		return dateUserJoined;
	}

	public void setDateUserJoined(LocalDateTime dateUserJoined) {
		this.dateUserJoined = dateUserJoined;
	}
	 */

    public JsonObject toJsonObj() {
    	JsonObject job = new JsonObject();
    	job.addProperty("userName", this.userName);
    	job.addProperty("password",  this.password);
    	job.addProperty("userId", this.userId);
    	//job.addProperty("dateUserJoined", this.dateUserJoined);
    	
        return job;
    }

	/**
    public String fromJsonObj(JsonObject jobj) {
    	String str = gson.fromJson(jobj, String.class);
    	
        return str;
    }
	 */


}

// nor used:import java.time.LocalDateTime;
// not used:     private LocalDateTime dateUserJoined;
//this.dateUserJoined = new LocalDateTime(2019, 03, 30);

