package business;

import java.io.IOException;
import io.server.UserJsonFile;
import org.json.*;

//import java.util.Scanner;

public class MainBusiness {

	public static void main(String[] args) throws IOException {
        // display a welcome message
        System.out.println("Start server business.\n");
        System.out.println("Start will be replaced with the webpage running actions.\n");

        // display the command menu
        displayMenu();

        // Open simple console for action commands
		//Scanner sc = new Scanner(System.in);
		
        // perform 1 or more actions ( Default in case of no input)
        String action = "exit";
        while (true) {
            // get the input from the user
			System.out.print("Please enter the the desired action on console for now: ");
            action = Console.getString("Enter a command: ");
			//action = sc.nextLine();
            System.out.println();

            if (action.equalsIgnoreCase("list")) {
                displayAllUsers();
            } else if (action.equalsIgnoreCase("listjson")) {
                System.out.println("Action Not implemented yet.\n");
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
    	JSONArray usersJson = UserJsonFile.getUsers();
        System.out.println(usersJson);
        //System.out.println("The JSON Array is not being read correctly yet.\n");
    }
    
    public static void quit() {
        System.out.println("Bye.\n");
        System.exit(0);
    }

}
