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
        System.out.println("this will be run from the webpage eventually.\n");

        // display the command menu
        displayMenu();

        // perform 1 or more actions ( Default in case of no input)
        String action = "exit";
        while (true) {
            // get the input from the user
            action = Console.getString("Please enter the the desired action on console: ");
			//action = sc.nextLine();
            System.out.println();

            if (action.equalsIgnoreCase("list")) {
                displayAllUsers();
            } else if (action.equalsIgnoreCase("listjava")) {
                System.out.println("Action Not implemented yet.\n");
                System.out.println("debug Action reading a non-Json text file.\n");
            	//displayOneUser();
            } else if (action.equalsIgnoreCase("ident")) {
                System.out.println("Action Not implemented yet.\n");
                System.out.println("Will be listing a specific user.\n");
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
                //addJsonCustomer();
            } else if (action.equalsIgnoreCase("help") || 
                       action.equalsIgnoreCase("menu")) {
                displayMenu();
            } else if (action.equalsIgnoreCase("exit") || 
                       action.equalsIgnoreCase("quit")) {
                quit();
            } else {
                System.out.println("Error! Not a valid command.\n");
            }
        }

	}

    public static void displayMenu() {
        System.out.println("COMMAND MENU");
        System.out.println("listjava list all users from a text file");
        System.out.println("list   all users info");
        System.out.println("ident  a specific users info");
        System.out.println("add    a customer");
        System.out.println("delete a customer");
        System.out.println("update a customer");
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
        }

        System.out.println("JSON Array of all user accounts.\n");
        System.out.println(usersJson);
		//} catch (IOException e) {
		//	System.out.println(e);
        //}
    }
    
    public static void quit() {
        System.out.println("Bye.\n");
        System.exit(0);
    }

}
