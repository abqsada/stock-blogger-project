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
	        System.out.println("i.e.: localhost:8888/?action=getUserbyid&user_id=5");
	        System.out.println("\n");
	        System.out.println("Also you need to have mySql running");
	        System.out.println("  and the local database instance connected.");
	        System.out.println("  and database already created by the stockbloggerDB.sql script.");
	        System.out.println("\n");
	        System.out.println("Once started the server will remain in a continuous loop");
	        System.out.println("  waiting for commands.");
	        System.out.println("  I have inserted a wait point to allow the user to prepare.\n");
            String moveon = Console.getString("Please enter any key when you are ready to continue: ");
            
            testServer.awaitClientCmd();
        
        } catch (SQLException e) {
            System.out.println(e);
		} finally {
	        System.out.println("ended back in MainBusiness.\n");
			quit();
		}
	}
			
	public static void quit() {
        System.out.println("Bye.\n");
        System.exit(0);
    }

}
