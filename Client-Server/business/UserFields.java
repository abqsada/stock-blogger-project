/**
 * 
 */
package business;

import java.time.LocalDateTime;

/**
 * @author Ruth
 * THis class defines the fields of the user account POJO.
 *   A UserFields object will be used to define the structure 
 *   of the JSON object 'User'
 */
public class UserFields {
    private String userName;
    private String password;
    private String email;
    private int userId;
    //private LocalDateTime dateUserJoined;

    public UserFields() {
		this.userName = "";
		this.password = "";
		this.email = "";
		this.userId = 0;
		//this.dateUserJoined = LocalDateTime.of(2019, 03, 30, 14, 32);
	}

	public UserFields(String userName, String password, String email, int userId) {
		this.userName = userName;
		this.password = password;
		this.password = email;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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


}
