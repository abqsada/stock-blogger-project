/**
 * 
 */
package serverMain;

//import java.time.LocalDateTime;
//import com.google.gson.Gson;
//import com.google.gson.GsonBuilder;
//import com.google.gson.JsonObject;

/**
 * @author Ruth
 * THis class defines the fields of the user account POJO.
 *   A UserFields object will be used to define the structure 
 *   of the JSON object 'User'
 */
public class UserFields {
    private String user_name;
    private String password;
    private int userId;
    //private LocalDateTime dateUserJoined;
	//Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public UserFields() {
		this.user_name = "";
		this.password = "";
		this.userId = 0;
		//this.dateUserJoined = LocalDateTime.of(2019, 03, 30, 14, 32);
	}

	public UserFields(String userName, String password, int userId) {
		this.user_name = userName;
		this.password = password;
		this.userId = userId;
		//this.dateUserJoined = dateUserJoined;
	}

	public String getUserName() {
		return user_name;
	}

	public void setUserName(String userName) {
		this.user_name = userName;
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

	/**
    public JsonObject toJsonObj() {
    	//String str = new String(("firstName:"+firstName+", lastname:"+lastName+", email:"+email));
    	//Object obj = gson.toJson(str);
    	//String str = "";
    	//str += "{\"firstName\":" + this.firstName;
    	//str += ", \"lastname\":" + this.lastName;
    	//str += ", \"email\":"    + this.email + "}";
    	//Object obj = (Object) str;
    	JsonObject job = new JsonObject();
    	job.addProperty("firstName", this.firstName);
    	job.addProperty("lastName",  this.lastName);
    	job.addProperty("email", this.email);
    	
        return job;
    }

    public String fromJsonObj(JsonObject jobj) {
    	String str = gson.fromJson(jobj, String.class);
    	
        return str;
    }
	 */


}
