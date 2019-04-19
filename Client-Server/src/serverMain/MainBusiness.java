package serverMain;

import java.io.IOException;
import ioFormat.UserJsonFile;
import com.google.gson.JsonArray;
//import org.json.*;

//import java.util.Scanner;

public class MainBusiness {

	public static void main(String[] args) throws IOException {
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
                displayAllUsers();
            } else if (action.equalsIgnoreCase("query")) {
                System.out.println("Action Not implemented yet.\n");
                // This action is intended to request a all users from dataBase
            	//kensApiDriver();
            } else if (action.equalsIgnoreCase("trend")) {
                System.out.println("Action Not implemented yet.\n");
                // This action is intended to test the web access api
            	//kensApiDriver();
            } else if (action.equalsIgnoreCase("ident")) { 
                System.out.println("Action Not implemented yet.\n");
                System.out.println("Will be listing a specific user account.\n");
            	//displayOneUser();
            } else if (action.equalsIgnoreCase("add")) {
                System.out.println("Action Not implemented yet.\n");
                //addCustomer();
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
                //addCustomer();
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
    
    public static void quit() {
        System.out.println("Bye.\n");
        System.exit(0);
    }

}
