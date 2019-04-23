package io.server;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import business.UserFields;

import org.json.JSONTokener;
import org.json.*;

/**
 * 
 * @author Ruth
 * This class reads and writes the User account information 
 *    to/from a file.
 *    This class should become obsolete when there is a class
 *    that reads/writes the user information to the database
 */
public class UserJsonFile {

    private static final Path customersJPath = Paths.get("user_account.json");
    private static final File customersJFile = customersJPath.toFile();
    //private static List<UserFields> usersJava = getUsers();
    private static JSONArray usersJson = getUsers();

    // prevent instantiation of the class
    //private UserJsonFile () {}
    
    public static JSONArray getUsers() {
        // if the customers file has already been read, don't read it again
        if (usersJson != null) {
            return usersJson;
        }

        // This code is modified to open the Json file. 
        //  read the data into a Json object 
        if (Files.exists(customersJPath)) { // prevent the FileNotFoundException
        	System.out.println(customersJFile.getName());
              try (BufferedReader in = new BufferedReader(
                                       new FileReader(customersJFile))) {
            	
              	System.out.println("The try for BufferedReader in is not working");
            	//JSONTokener tempInput = new JSONTokener(in);
            	//System.out.println("Got the Tokener tempInput");
            	//System.out.println(tempInput.toString());
            	//JSONArray usersJson = new JSONArray(tempInput);
            	JSONArray usersJson = new JSONArray("tempInput working soon");
        
            } catch (IOException e) {
            	System.out.println(e);
            	return null;
            }
        }
        return usersJson;    	
    }
    
    public static JSONArray addUsers(JSONObject joUser) {
    	
    	
        //  write the data into a Json object 
        if (Files.exists(customersJPath)) { // prevent the FileNotFoundException
        	System.out.println(("write: "+ customersJFile.getName()));
           // try (BufferedWriter out = new BufferedWriter(
             //                        new FileWriter(customersJFile))) {
            	// write to file the additional users
            	// Is this needed?  JSONObject joUser = new JSONObject()
            	
            	// usersJson.append(joUser)
            	
            	//call the 'saveUsers' method to write to the file
            	// saveUsers is probably the method that will need write resources
            	
                
            //} catch (IOException e) {
            //	System.out.println(e);
            //	return null;
            //}
        }
        return usersJson;    	
          	
    	
    }
    
}
