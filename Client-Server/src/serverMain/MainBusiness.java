package serverMain;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.io.IOException;

import ioFormat.UserJsonFile;
import serverMain.UserFields;
import serverMain.Console;
import dbConnect.DataConnection;
import dbConnect.User;
import cmdsFromClient.ServerThread;

import com.google.gson.JsonObject;
import com.google.gson.JsonArray;
//import org.json.*;

public class MainBusiness {

	public static void main(String[] args) throws IOException, SQLException {
		
        // display a welcome message
        System.out.println("Start server interface with console for development.\n");
        // The console can be used to run development & unit tests
        System.out.println("enter startWebCommand at prompt for client commands\n");

        // display the command menu
        displayMenu();

        // perform 1 or more actions ( Default in case of no input)
		//JsonObject job = new JsonObject();
        String action = "";
        Boolean consoleAction = true;
        JsonObject job = new JsonObject();
        //while (consoleAction==true) {
            // get the input from the user
            action = Console.getString("Please enter the the desired action on console: ");
            System.out.println();

            if (action.equalsIgnoreCase("list")) {
            	// This specifically works from the file user_account.json
                displayAllUsers();
            } else if (action.equalsIgnoreCase("ident")) { 
                System.out.println("Action Not implemented yet.\n");
                System.out.println("Will be listing a specific user account.\n");
            	//displayOneUser();
            } else if (action.equalsIgnoreCase("adduser")) {
            	job = userToAdd();
            } else if (action.equalsIgnoreCase("addpost")) {
            	job = postToAdd();
            } else if (action.equalsIgnoreCase("getallposts")) {
                postsToDispylay();
            } else if (action.equalsIgnoreCase("del") || 
                       action.equalsIgnoreCase("delete")) {
                System.out.println("Action Not implemented yet.\n");
                //deleteCustomer();
            } else if (action.equalsIgnoreCase("update")) {
                System.out.println("Action Not implemented yet.\n");
                //updateCustomer();
            } else if (action.equalsIgnoreCase("help") || 
                       action.equalsIgnoreCase("menu")) {
                displayMenu();
            } else if (action.equalsIgnoreCase("exit") || 
                       action.equalsIgnoreCase("quit")) {
                quit();
            } else if (action.equalsIgnoreCase("startWebCommand")) {
            	// intended to be a Break command to leave this loop
            	consoleAction =false;
            } else {
                System.out.println("Error! Not a valid command.\n");
            }
        //}

        // After previous while loop, call methods start serverSocket
        //       listening for commands from front end
        // The following would respond to a JSON object from the webpage
		ServerThread testServer = new ServerThread(8888);
		
		// JsonObject for testing  !! make this a real JSON
		testServer.awaitClientCmd(job);
        
        System.out.println("ended back in MainBusiness.\n");
    	quit();
	}

    public static void displayMenu() {
        System.out.println("COMMAND MENU");
        System.out.println("list       all users info");
        System.out.println("ident      a specific users info");
        System.out.println("adduser    add a user account to the database");
        System.out.println("addpost    add a post for a user to the database");
        System.out.println("delete     a customer");
        System.out.println("update     a customer");
        System.out.println("help    - Show this menu");
        System.out.println("exit    - Exit this application\n");
    }

    // Display all users from a Json file
    public static void displayAllUsers() {
        System.out.println("User Account List from a file");
        //try (){
    	JsonArray usersJson = UserJsonFile.getUsers();
        System.out.println("return from getUsers");
        if (usersJson == null) {
        	System.out.println("usersJson was null");
        } else {
            System.out.println("JSON Array of all user accounts.\n");
            System.out.println(usersJson);        	
        }

		//} catch (IOException e) {
		//	System.out.println(e);
        //}
    }
    
    // add a user from data entered on the console
	public static JsonObject userToAdd() throws SQLException {
		try {
			String userName = Console.getString("Enter userName ..as 1 word..: ");
			String password = Console.getString("Enter password: ");
			String dateJoined = Console.getString("Enter Date string ie 2019-03-30:");

			//Connection connection = DataConnection.getConnection();
			//int newUserId = DataConnection.addUser(userName, Date.valueOf(userJoinedDate), password);
			//System.out.println(("User object added to database with userID= :\n"+newUserId));
			
			User userObj = new User(20, userName, Date.valueOf(dateJoined), password);
			
			JsonObject jobout = new JsonObject();
			jobout.addProperty("command",  "adduser");
			
			JsonObject jobinside = new JsonObject();
			jobinside.addProperty("userName",  userName);
			jobinside.addProperty("password",  password);
			jobinside.addProperty("dateJoined",  dateJoined);
			
			jobout.add("commandData",  jobinside);
			System.out.println();
			System.out.println(("User Json object created for testing :\n"+jobout));
			
			return jobout;
		//} catch (SQLException sq) {
		//	System.out.println(sq);
		//	sq.printStackTrace();
		////} catch (IOException e) {
		////		System.out.println(e);
		////		e.printStackTrace();
		} finally {
			// cleanup code
		}
	}

    
	public static JsonObject  postToAdd() throws SQLException {
		try {
			int userId = Console.getInt("Enter userId as int: ");
			String title = Console.getString("Enter post title not surrounded by quotes: ");
			String body  = Console.getString("Enter post body not surrounded by quotes: ");
			String postDate = Console.getString("Enter postDate string ie 2019-03-30:");

			//Connection connection = DataConnection.getConnection();
			//int newPostId = DataConnection.addPost(userId, title, body, Date.valueOf(postAddDate));
			//System.out.println(("Post object added to database with postID= :\n"+newPostId));
			
			//Post postObj = new Post(8, userId, Date.valueOf(postDate), password);
			JsonObject joboutp = new JsonObject();
			joboutp.addProperty("command",  "addpost");
						
			JsonObject jobinside = new JsonObject();
			jobinside.addProperty("userId",  userId);
			jobinside.addProperty("title",  title);
			jobinside.addProperty("body",  body);
			jobinside.addProperty("postDate",  postDate);

			joboutp.add("commandData",  jobinside);
			System.out.println();
			System.out.println(("User Json object created for testing :\n"+joboutp));
			
			return joboutp;

			
		//} catch (SQLException sqp) {
		//	System.out.println(sqp);
		//	sqp.printStackTrace();
		////} catch (IOException e) {
		////		System.out.println(e);
		////		e.printStackTrace();
		} finally {
			// cleanup code
		}
	}

    
    // create Json command to pull posts for a user and send to web for display
	public static void postsToDispylay() throws SQLException {
		try {
			int pUserId = Console.getInt("Enter userId as int: ");
			//Connection connection = DataConnection.getConnection();
			//int newPostId = DataConnection.addPost(userId, title, body, Date.valueOf(postAddDate));
			//System.out.println(("Post object added to database with postID= :\n"+newPostId));
			
			//Post postObj = new Post(8, userId, Date.valueOf(userJoinedDate), password);
		//} catch (SQLException sqp) {
		//	System.out.println(sqp);
		//	sqp.printStackTrace();
		////} catch (IOException e) {
		////		System.out.println(e);
		////		e.printStackTrace();
		} finally {
			// cleanup code
		}
	}

			
	public static void quit() {
        System.out.println("Bye.\n");
        System.exit(0);
    }

}
