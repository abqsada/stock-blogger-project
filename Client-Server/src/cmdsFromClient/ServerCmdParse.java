package cmdsFromClient;

import serverMain.UserFields;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonElement;

/**
 * 
 * @author Ruth
 * The purpose of this class is to accept Json from
 *   the web client and parse it into actions.
 *   This JSON should be checked for the correct set of commands
 *   and sufficient data in the associated user object to
 *     carry out those commands
 */
public class ServerCmdParse {
	private JsonObject joCmd;
	private JsonObject userJson;
	private String actionCmd;
	
	//default constructor
	public ServerCmdParse () {
		this.joCmd = new JsonObject();
		this.userJson = new JsonObject();
		this.actionCmd = "exit";
	}
	
	public ServerCmdParse (JsonObject joFromWeb) {
		this.joCmd = joFromWeb;
		// parse the Json from the web into a command string 
		// and it's associated user Json string
		
		// or the web page could probably send separate
		//command stream and data stream
	}

}
