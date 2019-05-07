package serverMain;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.io.IOException;

import serverMain.Console;
import dbConnect.DataConnection;
import dbConnect.User;
import cmdsFromClient.ServerThread;

import com.google.gson.JsonObject;
import com.google.gson.JsonArray;

public class MainBusiness {

	public static void main(String[] args) throws IOException, SQLException {
		// Resources that will be closed after try-with-resources block 
		//   create database connection to the local MySql database 
        try (	Connection connection = DataConnection.getConnection();
        		){ 
    		//  start server socket on port 8888 
    		//    listening to respond to commands from website
            ServerThread testServer = new ServerThread(8888);
            
	        System.out.println("This code is set up to run commands from the web.");
	        System.out.println("So you must have a blank browser open to start a command thread!!!");
	        System.out.println("On browser command line type localhost:8888 & add command.");
	        System.out.println("i.e.: localhost:8888/?action=getUser&user_id=5");
	        System.out.println("\n");
	        System.out.println("Also you need to have mySql running");
	        System.out.println("  and the local database instance connected.");
	        System.out.println("  and database already created by the stockbloggerDB.sql script.\n");
	        System.out.println("\n");
	        System.out.println("  I have inserted a wait point to allow the user to prepare.\n");
            String moveon = Console.getString("Please enter any key when you are ready to continue: ");


            
            // there is a JsonObject=job passed in for testing.
            // job should be removed when we have a client connection
            testServer.awaitClientCmd();
        
        } catch (SQLException e) {
            System.out.println(e);
		} finally {
	        System.out.println("ended back in MainBusiness.\n");
			quit();
		}
	}

    public static void displayMenu() {
        System.out.println("COMMAND MENU");
        System.out.println("webCommand     skips this console request, returns null cmd");
        System.out.println("getuser    return a specific user account from the database");
        System.out.println("adduser    add a user account to the database");
        System.out.println("addpost    add a post for a user to the database");
        System.out.println("getuserposts gets all the posts for a user from the database");
        System.out.println("delete     a user account from the database");
        System.out.println("update     a user account");
        System.out.println("help    - Show this menu");
        System.out.println("exit    - Exit this application\n");
    }

    
    // call the console to get a Json command object  for testing
    // this only gives one command per execution to send through the code
	public static JsonObject getConsoleCommand() {
        // display a welcome message
        System.out.println("Start server interface with console for development.\n");
        // The console can be used to run development & unit tests
        System.out.println("enter startWebCommand at prompt for client commands\n");

        // display the command menu
        displayMenu();

        // perform 1 actions ( Default in case of no input)
        String action = "";
        //Boolean consoleAction = true;
        JsonObject jobin = new JsonObject();
        //while (consoleAction==true) {
            // get the input from the user
            action = Console.getString("Please enter the the desired action on console: ");
            System.out.println();

            if (action.equalsIgnoreCase("webCommand")) {
            	// intended to be a Break command to leave this loop
            	//   which is no longer a loop
            	jobin.addProperty("command", "webCommand");
            } else if (action.equalsIgnoreCase("getuser")) { 
            	jobin = getOneUser();
            } else if (action.equalsIgnoreCase("adduser")) {
            	jobin = userToAdd();
            } else if (action.equalsIgnoreCase("addpost")) {
            	jobin = postToAdd();
            } else if (action.equalsIgnoreCase("getuserposts")) {
            	jobin = postsToDispylay();
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
            } else {
                System.out.println("Error! Not a valid command.\n");
            }
        //}
			return jobin;
    
    }
    
    // add a user from data entered on the console
	public static JsonObject userToAdd() {
			String userName = Console.getString("Enter userName ..as 1 word..: ");
			String password = Console.getString("Enter password: ");
			String dateJoined = Console.getString("Enter Date string ie 2019-03-30:");

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
	}

	// add a post for a user from data entered on the console
	public static JsonObject  postToAdd() {
			int userId = Console.getInt("Enter userId as int: ");
			String title = Console.getString("Enter post title not surrounded by quotes: ");
			String body  = Console.getString("Enter post body not surrounded by quotes: ");
			String postDate = Console.getString("Enter postDate string ie 2019-03-30:");

			//Post postObj = new Post(8, userId, Date.valueOf(postDate), password);
			JsonObject jobout = new JsonObject();
			jobout.addProperty("command",  "addpost");
						
			JsonObject jobinside = new JsonObject();
			jobinside.addProperty("userId",  userId);
			jobinside.addProperty("title",  title);
			jobinside.addProperty("body",  body);
			jobinside.addProperty("postDate",  postDate);

			jobout.add("commandData",  jobinside);
			System.out.println();
			System.out.println(("User Json object created for testing :\n"+jobout));
			
			return jobout;
	}

    // This would create the command for testing the display of posts
	// the return should be a JsonArray but if we can return an object that is good for now
	public static JsonObject postsToDispylay() {
			int userId = Console.getInt("Enter userId int of account to get posts for: ");
			JsonObject jobout = new JsonObject();
			jobout.addProperty("command",  "getuserposts");
			
			JsonObject jobinside = new JsonObject();
			jobinside.addProperty("userId",  userId);

			jobout.add("commandData",  jobinside);
			System.out.println(("command to get Posts for this userID= :\n"+jobout));
			return jobout;
	}

    // This would create the command for testing getting a user account
	public static JsonObject getOneUser() {
		String userName = Console.getString("Enter userName ..as 1 word..: ");
		String password = Console.getString("Enter password: ");
		JsonObject jobout = new JsonObject();
		jobout.addProperty("command",  "getuser");
		
		JsonObject jobinside = new JsonObject();
		jobinside.addProperty("userName",  userName);
		jobinside.addProperty("password",  password);

		jobout.add("commandData",  jobinside);
		System.out.println();
		System.out.println(("User Json object created for testing :\n"+jobout));
		return jobout;
	}

			
	public static void quit() {
        System.out.println("Bye.\n");
        System.exit(0);
    }

}
