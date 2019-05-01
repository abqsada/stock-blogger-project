package cmdsFromClient;

//import serverMain.UserFields;
import dbConnect.User;
import dbConnect.DataConnection;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.ResultSet;
//import java.io.IOException;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonElement;

/**
 * 
 * @author Ruth
 * The purpose of this class is to accept Json from
 *   the web client and parse it into actions.
 *   This JSON should be checked for the correct set of commands
 *   and sufficient data in the associated user object to
 *     carry out those commands
 */
public class ServerCmdParse {
	private JsonObject joCmd;
	private JsonObject commandData;
	private String actionCmd;
	private JsonObject returnData;
	int errorCode=100;  //error code default should never be returned

	
	//default constructor
	public ServerCmdParse () {
		this.joCmd = new JsonObject();
		this.commandData = new JsonObject();
		this.returnData = new JsonObject();
		this.actionCmd = "exit";
		this.errorCode=1;  //error code denoting null object
	}
	
	//constructor using JsonObject passed as parameter
	public ServerCmdParse (JsonObject joFromWeb) {
		this.joCmd = joFromWeb;
		// parse the Json from the web into a command string 
		// and it's associated user JsonObject that contains
		// the data necessary to accomplish the command
		if (joFromWeb.has("command")) {
			this.actionCmd = joFromWeb.get("command").toString().replace("\"", "");
			this.errorCode=200;  //error code denoting successful Json parsing
        } else {
        	// signal that bad Json was recieved
        	// maybe try/catch
        	System.out.println("no actionCmd was in the Json recieved\n");
    		this.errorCode=2;  //error code denoting no "command" string
        }
		if (joFromWeb.has("commandData")) {
			this.commandData = joFromWeb.getAsJsonObject("commandData");
        } else {
        	System.out.println("no commandData was in the Json recieved\n");
    		this.errorCode=3;  //error code denoting no "commandData" object
		}
		this.returnData = new JsonObject();
	}

	public String getactionCmd() {
		return actionCmd;
	}

	public JsonObject getcommandData() {
		return commandData;
	}
	
	/**
	 * TTHis method acts on the action command.
	 * @return a JsonObject that contains at a minimum the errorCode
	 *         describing the status of the parsing and actions.
	 *         Sometimes the return Json contains data for web display, i.e. posts
	 */
	public JsonObject takeAction() {
        System.out.println(("Command provided takeAction\n"+actionCmd));
        System.out.println(("Data    provided takeAction\n"+commandData));
        // zero the returnData Json for new command action
        returnData = null;
        
		if(actionCmd.equalsIgnoreCase(null)) {
			System.out.println(("Null data in actionCmd :\n"+actionCmd));
			System.exit(0);
		}
		// Decide which action to take base on actionCmd
        if (actionCmd.equalsIgnoreCase("adduser")) {
        	addUserAction();

        } else if (actionCmd.equalsIgnoreCase("getuser")) {
        	getUserAction();

        //} else if (actionCmd.equalsIgnoreCase("deluser")) {
            //System.out.println("Action Not implemented yet.\n");
        	//delUserAction();

        } else if (actionCmd.equalsIgnoreCase("addpost")) {
        	addPostAction();

        } else if (actionCmd.equalsIgnoreCase("getuserposts")) {
        	getPostsAction();

        } else if (actionCmd.equalsIgnoreCase("query")) {
            System.out.println("Action Not implemented yet.\n");
            // This action is intended to test the stock web access api
        	//kensApiDriver();
        } else if (actionCmd.equalsIgnoreCase("trend")) {
            System.out.println("Action Not implemented yet.\n");
            // This action is intended to test the twitter web access api
        	//kensApiDriver();
//        } else if (actionCmd.equalsIgnoreCase("exit") || 
//        		   actionCmd.equalsIgnoreCase("quit")) {
//            System.out.println("Ending server execution.\n");
//            System.exit(0);
        } else {
        	System.out.println("Error! Not a valid actionCmd.\n");
    		errorCode=4;  //error code denoting invalid "command" string
        }

        returnData.addProperty("errCode",  errorCode);
		return returnData;
	}

	public void addUserAction() {
		try {
            System.out.println(("Data provided for adduser command\n"+commandData));
            if(commandData.has("userName")&&commandData.has("password")
            		                      &&commandData.has("dateJoined")) {
            	String userName   = commandData.get("userName").toString().replace("\"", "");
            	String password   = commandData.get("password").toString().replace("\"", "");
            	String dateJoined = commandData.get("dateJoined").toString().replace("\"", "");
    			int newUserId = DataConnection.addUser(userName, Date.valueOf(dateJoined), password);
                returnData.addProperty("userId",  newUserId);
    			System.out.println(("User object added to database with userID= :\n"+returnData));
            	errorCode=500;//error code denoting successful addition of user
            } else {   
            	errorCode=5;//error code denoting incorrect user data
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
	}
	
	public void getUserAction() {
		try {
            System.out.println(("Data provided for getuser command\n"+commandData));
            if(commandData.has("userName")&&commandData.has("password") ) {
            	String userName   = commandData.get("userName").toString().replace("\"", "");
            	String password   = commandData.get("password").toString().replace("\"", "");
            	User userReturn = DataConnection.getUser(userName, password);

                returnData.addProperty("userId",     userReturn.getUserId());
                returnData.addProperty("userName",   userReturn.getUserName());
                returnData.addProperty("password",   userReturn.getPassword());
                //returnData.addProperty("dateJoined", userReturn.getDateJoined());
            	errorCode=800;//error code denoting successful return of user for display
    			System.out.println(("userData returned for  :\n"+returnData));
            } else {   
            	errorCode=7;//error code denoting incorrect deletion of user data
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
	}

	public void addPostAction() {
		try {
            System.out.println(("Data provided for addpost command\n"+commandData));
            if(commandData.has("userId") &&commandData.has("title")
             &&commandData.has("body") &&commandData.has("postDate")) {
            	int userId     = commandData.get("userId").getAsInt();
            	String title   = commandData.get("title").toString().replace("\"", "");
            	String body   = commandData.get("body").toString().replace("\"", "");
            	String postDate = commandData.get("postDate").toString().replace("\"", "");
    			int newPostId = DataConnection.addPost(userId, title, body, Date.valueOf(postDate));
                returnData.addProperty("postId",  newPostId);
    			System.out.println(("User object added to database with userID= :\n"+returnData));
            	errorCode=600;//error code denoting successful addition of post
            } else {   
            	errorCode=6;//error code denoting incorrect data to add post
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
	}

	public void getPostsAction() {
		try {
            System.out.println(("Data provided for getuserposts command\n"+commandData));
            if(commandData.has("userId") ) {
            	int userId     = commandData.get("userId").getAsInt();
            	ResultSet rs = DataConnection.getPostsForUser(userId);
                //returnData.addProperty("postId",  newPostId);
    			System.out.println(("Not implemented fully yet :\n"));
    			System.out.println(("All posts returned from the database with userID= :\n"+userId));
            	errorCode=600;//error code denoting successful addition of post
            } else {   
            	errorCode=6;//error code denoting incorrect data to add post
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
	}
	
	/*
	public void delUserAction() {
		try {
            System.out.println(("Data provided for deluser command\n"+commandData));
            if(commandData.has("userId")) {
            	int userId     = commandData.get("userId").getAsInt();
    			int delUserId = DataConnection.delUser(userId);
            	errorCode=700;//error code denoting successful deletion of user
    			System.out.println(("error code returned to web signalling deletion= :\n"+errorCode));
            } else {   
            	errorCode=7;//error code denoting incorrect deletion of user data
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
	}
	*/


}
