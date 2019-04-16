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

import org.json.JSONTokener;
import org.json.JSONArray;
import org.json.JSONObject;
//import com.google.gson.JsonElement;
//import com.google.gson.JsonParser;


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
        	System.out.println(("found file named: "+customersJFile.getName()));
              try (BufferedReader in = new BufferedReader(
                                       new FileReader(customersJFile))) {
            	
              	System.out.println("reading from BufferedReader \"in\" into an object");
              	// example using Gson reader
            	//Object Obj = gson.fromJson(in, Object.class);
            	//String jsonObj = gson.toJson(Obj);
                //System.out.println(("JSON object from file"+"\n"+jsonObj+"\n"+"\n"));
                //JsonParser jp = new JsonParser();
                //JsonElement jele = jp.parse(jsonObj);  // throws JsonIException & JsonSyntaxException
                //System.out.println(("JSON tree Using parser into JsonElement"+"\n"+ jele +"\n"));

				String line = "";
				String rows ="";
				while ((line = in.readLine()) != null) {
					rows += line;
				}
                System.out.println(("rows of Strings : \n"+rows));
            	//JSONObject jobj = new JSONObject(rows);
                //System.out.println(("JSONObject from string : \n"+jobj));

            	JSONArray usersJson = new JSONArray(rows);
        
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
