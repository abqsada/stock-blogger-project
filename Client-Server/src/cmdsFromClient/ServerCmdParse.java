package cmdsFromClient;

//import serverMain.UserFields;
import dbConnect.User;
import dbConnect.DataConnection;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.ResultSet;
//import java.io.IOException;
import dbConnect.Post;
import dbConnect.Comment;

import com.google.gson.JsonObject;

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
	private JsonObject commandData;
	private String actionCmd;
	private JsonObject returnData;
	int errorCode=100;  //error code default should never be returned

	
	//default constructor
	public ServerCmdParse () {
		this.commandData = new JsonObject();
		this.returnData = new JsonObject();
		this.actionCmd = "exit";
		this.errorCode=1;  //error code denoting null object
	}
	
	//constructor using String ActionCmd & JsonObject commandData from web
	public ServerCmdParse (String command, JsonObject cmdData) {
		// parse the Json from the web into a command string 
		// and it's associated user JsonObject that contains
		// the data necessary to accomplish the command
		this.actionCmd = command;
		this.commandData = cmdData;
		this.returnData = new JsonObject();
		this.errorCode=100;  //error code denoting successful construction
	}
	
	//constructor using compound JsonObject including actionCmd & its data
	public ServerCmdParse (JsonObject joFromConsole) {
		// parse the Json developed from console interaction
		// that contains the command string and it's associated JsonObject
		//  that contains the data necessary to accomplish the command
		if (joFromConsole.has("command")) {
			this.actionCmd = joFromConsole.get("command").toString().replace("\"", "");
			this.errorCode=200;  //error code denoting successful Json parsing
        } else {
        	System.out.println("no actionCmd was in the Json recieved\n");
    		this.errorCode=2;  //error code denoting no "command" string
        }
		if (joFromConsole.has("commandData")) {
			this.commandData = joFromConsole.getAsJsonObject("commandData");
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
        
		if(actionCmd.equalsIgnoreCase(null)) {
			System.out.println(("Null data in actionCmd : "+actionCmd));
			System.out.println("Exit server execution");
			System.exit(0);
		}
		if(commandData.entrySet().size() == 0) {
			System.out.println(("Json command data is empty : "+commandData));
			System.out.println("Continue but respond with error code ");
        	errorCode=30;//error code denoting empty user data

        // Decide which action to take base on actionCmd
		} else if (actionCmd.equalsIgnoreCase("adduser")) {
        	addUserAction();

        } else if (actionCmd.equalsIgnoreCase("getuser")) {
        	getUserAction();

        } else if (actionCmd.equalsIgnoreCase("getuserbyid")) {
        	getUserByIdAction();

        } else if (actionCmd.equalsIgnoreCase("addpost")) {
        	addPostAction();

        } else if (actionCmd.equalsIgnoreCase("getuserposts")) {
        	getPostsAction();

        } else if (actionCmd.equalsIgnoreCase("addcomment")) {
        	addCommentAction();

        } else if (actionCmd.equalsIgnoreCase("getcomments")) {
        	getCommentsAction();

        } else if (actionCmd.equalsIgnoreCase("exit") || 
        		   actionCmd.equalsIgnoreCase("quit")) {
            System.out.println("Ending server execution.\n");
            System.exit(0);
        } else {
        	System.out.println("Error! Not a valid actionCmd.\n");
    		errorCode=4;  //error code denoting invalid "command" string
        }

        returnData.addProperty("errCode",  errorCode);
		return returnData;
	}

	public void addUserAction() {
		try {
            if(commandData.has("userName")&&commandData.has("password")
            		                      &&commandData.has("dateJoined")) {
            	String userName   = commandData.get("userName").toString().replace("\"", "");
            	String password   = commandData.get("password").toString().replace("\"", "");
            	String dateJoined = commandData.get("dateJoined").toString().replace("\"", "");
    			int newUserId = DataConnection.addUser(userName, Date.valueOf(dateJoined), password);
    			System.out.println(("User object added to database with userID= :\n"+newUserId));
                returnData.addProperty("userId",  newUserId);
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
    			// need to convert this SQL date to String
                returnData.addProperty("dateJoined", userReturn.getDateJoined().toString());
            	errorCode=600;//error code denoting successful return of user for display
    			System.out.println(("userData returned for getuser :\n"+returnData));
            } else {   
            	errorCode=6;//error code denoting incorrect deletion of user data
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
	}
	
	public void getUserByIdAction() {
		try {
            System.out.println(("Data provided for getuserbyid command\n"+commandData));
            if(commandData.has("userId") ) {
            	int userId     = commandData.get("userId").getAsInt();
            	User userReturn = DataConnection.getUserById(userId);

                returnData.addProperty("userId",     userReturn.getUserId());
                returnData.addProperty("userName",   userReturn.getUserName());
                returnData.addProperty("password",   userReturn.getPassword());
    			// need to convert this SQL date to String
                returnData.addProperty("dateJoined", userReturn.getDateJoined().toString());
            	errorCode=700;//error code denoting successful return of user for display
    			System.out.println(("userData returned for getuserbyid :\n"+returnData));
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
    			System.out.println(("Post object added to database with postId= :\n"+returnData));
            	errorCode=800;//error code denoting successful addition of post
            } else {   
            	errorCode=8;//error code denoting incorrect data to add post
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
            	rs.first(); //set result set at 1st row
            	Post post = new Post(rs.getInt(0),	rs.getInt(1), rs.getString(2), rs.getString(3),  rs.getDate(4));
                returnData.addProperty("postId",   post.getPostId());
                returnData.addProperty("userId",   post.getUserId());
                returnData.addProperty("title",    post.getTitle());
                returnData.addProperty("body",     post.getBody());
    			// need to convert this SQL date to String
                returnData.addProperty("postDate", post.getPostDate().toString());
    			System.out.println(("1st row posts returned from the database with userID= :\n"+userId));
            	errorCode=900;//error code denoting successful addition of post
            } else {   
            	errorCode=9;//error code denoting incorrect data to add post
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
	}

	public void addCommentAction() {
		try {
            System.out.println(("Data provided for addcomment command\n"+commandData));
            if(commandData.has("postId") &&commandData.has("userId") 
             &&commandData.has("body")&&commandData.has("commentDate")) {
            	int postId     = commandData.get("postId").getAsInt();
            	int userId     = commandData.get("userId").getAsInt();
            	String body   = commandData.get("body").toString().replace("\"", "");
            	String commentDate = commandData.get("commentDate").toString().replace("\"", "");
    			int commentId = DataConnection.addComment(postId, userId, body, Date.valueOf(commentDate));
                returnData.addProperty("commentId",  commentId);
    			System.out.println(("Comment object added to database with commentID= :\n"+returnData));
            	errorCode=1000;//error code denoting successful addition of post
            } else {   
            	errorCode=10;//error code denoting incorrect data to add post
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
	}

	public void getCommentsAction() {
		try {
            System.out.println(("Data provided for getcomments command\n"+commandData));
            if(commandData.has("postId") ) {
            	int postId     = commandData.get("postId").getAsInt();
            	ResultSet rs = DataConnection.getCommentsForPost(postId);
            	rs.first(); //set result set at 1st row
            	Comment comment = new Comment(rs.getInt(0),	rs.getInt(1),	rs.getInt(2), rs.getString(3),  rs.getDate(4));
                returnData.addProperty("commentId",   comment.getCommentID());
                returnData.addProperty("postId",   comment.getPostID());
                returnData.addProperty("userId",   comment.getUserID());
                returnData.addProperty("body",     comment.getBody());
    			// need to convert this SQL date to String
                returnData.addProperty("postDate", comment.getCommentDate().toString());
    			System.out.println(("1st row Comments returned from the database with postId= :\n"+postId));
            	errorCode=1100;//error code denoting successful addition of post
            } else {   
            	errorCode=11;//error code denoting incorrect data to add post
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
	}
	// 

}
