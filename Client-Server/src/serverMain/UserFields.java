/**
 * 
 */
package serverMain;

//import java.time.LocalDateTime;
//import com.google.gson.Gson;
//import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

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
    //private LocalDateTime dateUserJoined;
	//Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public UserFields() {
		this.userName = "";
		this.password = "";
		this.userId = 0;
		//this.dateUserJoined = LocalDateTime.of(2019, 03, 30, 14, 32);
	}

	public UserFields(String userName, String password, int userId) {
		this.userName = userName;
		this.password = password;
		this.userId = userId;
		//this.dateUserJoined = dateUserJoined;
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

	/**
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
    	
        return job;
    }

	/**
    public String fromJsonObj(JsonObject jobj) {
    	String str = gson.fromJson(jobj, String.class);
    	
        return str;
    }
	 */


}
