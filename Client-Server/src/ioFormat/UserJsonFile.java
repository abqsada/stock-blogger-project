package ioFormat;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import serverMain.UserFields;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
import com.google.gson.JsonElement;


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
    private static JsonArray usersJson = getUsers();

    // prevent instantiation of the class
    //private UserJsonFile () {}
    
    public static JsonArray getUsers() {
        // if the customers file has already been read, don't read it again
        if (usersJson != null) {
            return usersJson;
        }
        
  		// create an instance of a Gson JSON object for JSON manipulation
  		Gson gson = new GsonBuilder().setPrettyPrinting().create();

  		// This code is modified to open the Json file. 
        //  read the data into a Json object 
        if (Files.exists(customersJPath)) { // prevent the FileNotFoundException
              try (BufferedReader in = new BufferedReader(
                                       new FileReader(customersJFile))) {
            	

            	  
              	System.out.println("reading from BufferedReader \"in\" into an object");
              	// example using Gson reader
            	Object Obj = gson.fromJson(in, Object.class);
            	String jsonObj = gson.toJson(Obj);
                System.out.println(("JSON object from file"+"\n"+jsonObj+"\n"+"\n"));
                JsonParser jp = new JsonParser();
                JsonElement jele = jp.parse(jsonObj);
                //System.out.println(("JSON tree Using parser into JsonElement"+"\n"+ jele +"\n"));
                JsonArray usersJson = new JsonArray();
                usersJson.add(jele.getAsJsonArray());
                //System.out.println(("JSONArray from JsonElement"+"\n"+ usersJson +"\n"));
                System.out.println(("JsonElementneeds to be parsed into objects>JSONArray"+"\n"));
                System.out.println(("JsonElement>JSONArray, Throwing exception printing"+"\n"));
        
            } catch (IOException e) {
            	System.out.println(e);
            	e.printStackTrace();
            	return null;
            }
        }
        return usersJson;    	
    }
    
    public static JsonArray addUsers(JsonObject joUser) {
    	
    	
        //  write the data into a Json object 
        if (Files.exists(customersJPath)) { // prevent the FileNotFoundException
        	// for debug write out the 
        	System.out.println(("will be writing and adding to this file: "+ customersJFile.getName()));
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
        } else {
        	System.out.println("Could not find file user_account.json");        	
        }
        return usersJson;    	
          	
    	
    }
    
}

/*
 * This is the imorts and code I tried using org.json:
 *
import org.json.JSONTokener;
import org.json.JSONArray;
import org.json.JSONObject;
    private static JSONArray usersJson = getUsers();
				String line = "";
				String rows ="";
				while ((line = in.readLine()) != null) {
					rows += line;
				}
                System.out.println(("rows of Strings : \n"+rows));
					//output exception printing rows in the previous line
            	JSONArray usersJson = new JSONArray(rows);
 
 * */
