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

import com.google.gson.JsonObject;
import com.google.gson.JsonArray;
//import org.json.*;

public class MainBusiness {

	public static void main(String[] args) throws IOException, SQLException {
		
		
        // display a welcome message
        System.out.println("Start server interface with console.\n");
        // The console can be used to run development & unit tests
        System.out.println("then a Serversocket from the webpage can enter commands.\n");

        // display the command menu
        displayMenu();

        // perform 1 or more actions ( Default in case of no input)
        String action = "exit";
        Boolean consoleAction = true;
        while (consoleAction==true) {
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
            } else if (action.equalsIgnoreCase("add")) {
                userToAdd();
            } else if (action.equalsIgnoreCase("addPost")) {
                postToAdd();
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
            	// or change while condition to Boolean consoleAction ==true
            	consoleAction =false; //& remove quit()
            	//quit();
            } else {
                System.out.println("Error! Not a valid command.\n");
            }
        }

        // After previous while loop, call methods start serverSocket
        //       listening for commands from front end
        // The following would respond to a JSON object from the webpage
        String webCommand = "exit";
        Boolean webAction = true;
        while (webAction==true) {
            // parse the webCommand from the Json object 
        	//   coming in on the serverSocket
        	// for now test this loop with strings
        	webCommand = "exit";
            System.out.println();

            if (webCommand.equalsIgnoreCase("ident")) {
                System.out.println("Action Not implemented yet.\n");
                System.out.println("Will be listing a specific user account.\n");
            	//displayOneUser(); // we should be able to use the same method
                // but I don't know how we will pass the Json object
            } else if (webCommand.equalsIgnoreCase("add")) {
                System.out.println("Action Not implemented yet.\n");
                //userToAdd();
            } else if (action.equalsIgnoreCase("query")) {
                System.out.println("Action Not implemented yet.\n");
                // This action is intended to test the stock web access api
            	//kensApiDriver();
            } else if (action.equalsIgnoreCase("trend")) {
                System.out.println("Action Not implemented yet.\n");
                // This action is intended to test the twitter web access api
            	//kensApiDriver();
            } else if (action.equalsIgnoreCase("help") || 
            	action.equalsIgnoreCase("menu")) {
            	displayWebMenu();
            } else if (webCommand.equalsIgnoreCase("exit") || 
            		webCommand.equalsIgnoreCase("quit")) {
                System.out.println("Ending webCommand.\n");
            	quit();
            } else {
            	System.out.println("Error! Not a valid WEBcommand.\n");
            }
        }

	}

    public static void displayMenu() {
        System.out.println("COMMAND MENU");
        System.out.println("list       all users info");
        System.out.println("query      list all users from dataBase");
        System.out.println("trend      website feed based on requested search");
        System.out.println("ident      a specific users info");
        System.out.println("add        a customer");
        System.out.println("delete     a customer");
        System.out.println("update     a customer");
        System.out.println("help    - Show this menu");
        System.out.println("exit    - Exit this application\n");
    }

    public static void displayWebMenu() {
        System.out.println("Web Page COMMAND MENU");
        System.out.println("ident  a specific users info");
        System.out.println("add    a customer");
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
	public static void userToAdd() throws SQLException {
		try {
			String userName = Console.getString("Enter userName ..as 1 word..: ");
			String password = Console.getString("Enter password: ");
			String userJoinedDate = Console.getString("Enter Date string ie 2019-03-30:");

			//Connection connection = DataConnection.getConnection();
			//int newUserId = DataConnection.addUser(userName, Date.valueOf(userJoinedDate), password);
			//System.out.println(("User object added to database with userID= :\n"+newUserId));
			
			UserFields userFieldsObj = new UserFields();
			userFieldsObj.setUserName(userName);
			userFieldsObj.setPassword(password);
			//userObj.setdateUserJoined(userJoinedDate);
			userFieldsObj.setUserId(20);
			JsonObject job = userFieldsObj.toJsonObj();
			System.out.println();
			System.out.println(("User Json object :\n"+job));
			
			User userObj = new User(20, userName, Date.valueOf(userJoinedDate), password);
			System.out.println();
			System.out.println(("User object created :\n"));
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

    
	public static void postToAdd() throws SQLException {
		try {
			int userId = Console.getInt("Enter userId as int: ");
			String title = Console.getString("Enter post title surrounded by double quotes: ");
			String body  = Console.getString("Enter post text surrounded by double quotes: ");
			String postAddDate = Console.getString("Enter Date string ie 2019-03-30:");

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
