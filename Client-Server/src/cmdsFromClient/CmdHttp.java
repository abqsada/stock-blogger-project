package cmdsFromClient;

import java.util.ArrayList;
import com.google.gson.JsonObject;
import java.sql.Date;

/**
 * 
 * @author Ruth
 *
 */
public class CmdHttp  {
	private ArrayList<String[]> cmdLines;
	private String actionCommand;
	private JsonObject cmdData;
	//String transient? postId; so that we may or may not need poetID
	
	/** Constructor parsing the commands from the web into Json
	 *  This constructor
	 * @param elements
	 */
	public CmdHttp(ArrayList<String[]> httpLines) {
		this.cmdLines = httpLines;
		int sizeCmdLines = httpLines.size();
		System.out.println(("CmdHttpGet constructor with #lines: "+sizeCmdLines));
		// We are only responding to 1 line of command
		String[] elements = httpLines.get(0);
		for(String element : elements) {
			System.out.println(("element of command line elements: "+element ) );
		}
		JsonObject httpParams = new JsonObject();
		// process the http line into our desired parameters
		// split along slashes to get major portions of string
		String[] pieces = splitOnSlash(elements[1]);
		// split along ampersand to get key=value pairs
		String[] params = splitOnAmpersand(pieces[0]);
		// split each pair to get values
		int sizeParms = params.length;
		for(int i=0; i<sizeParms; i++) {
			String[] doublet = splitOnEquals(params[i]);
			String propertyName  = doublet[0];
			String propertyValue = doublet[1];
			if (i==0) {
				// remove leading question mark off the first pair
				if(propertyName.startsWith("?") ) {
					propertyName = removeLeadingChar(propertyName);
				}
			}
			httpParams.addProperty(propertyName, propertyValue);
		}
		
		this.actionCommand = setAction(httpParams);
		this.cmdData = new JsonObject();
	}
		
	public String setAction (JsonObject params) {
		String action="";
		if (params.has("command")) {
			action = params.get("command").toString().replace("\"", "");
        } else {
        	System.out.println("no actionCmd was in the Json recieved from HTTP\n");
        }
		return action;
	}

		
	public JsonObject setDataForCmd (String httpAction, JsonObject params) {
		JsonObject buildCmdData = new JsonObject();
		if (params.has("command")) {
			String action = params.get("command").toString().replace("\"", "");
			
			if (action.equals("getuserbyid") ) {
            	int userId     = params.get("userId").getAsInt();
            	buildCmdData.addProperty("userId", userId);

			} else if (action.equals("getpost") ) {
				System.out.println("Action Not implemented yet.\n");			
			} else if (action.equals("getcomment") ) {
				System.out.println("Action Not implemented yet.\n");
			} else if (action.equals("adduser") ) {
	            if(params.has("userName")&&params.has("password")
	                      &&params.has("dateJoined")) {
					String tempUserName = params.get("userName").toString().replace("\"", "");
					String tempPassword = params.get("password").toString().replace("\"", "");
					String dateString   = params.get("dateJoined").toString().replace("\"", "");
					buildCmdData.addProperty("userName", tempUserName);
					buildCmdData.addProperty("password", tempPassword);
					buildCmdData.addProperty("dateJoined", dateString);
					
				}
			} else if (action.equals("addpost") ) {
				System.out.println("Action Not implemented yet.\n");			
			} else if (action.equals("addcomment") ) {
				System.out.println("Action Not implemented yet.\n");
			} else  {
				// signal that this http command is not recognized
				System.out.println("Problem parsing the Http cmd string.  ");
				System.out.println("Unrecognized Data for Action command.\n");
			}
			
		} else  {
			System.out.println("Problem parsing the Http cmd string.  ");
			System.out.println("no command in parameters.\n");
		}
		return buildCmdData;
	}
	
	// this is the Json object to pass into Server actions
	public String getActionCmd() {
		return actionCommand;
	}
	
	// this is the Json object to pass into Server actions
	public JsonObject getCmdData() {
		return cmdData;
	}
	
	// this is the Json object to pass into Server actions
	public String[] splitOnSlash(String input) {
		String[] slashStrings = input.split("/");
		return slashStrings;
	}
	
	// this is the Json object to pass into Server actions
	public String[] splitOnAmpersand(String input) {
		String[] ampStrings = input.split("&");
		return ampStrings;
	}
	
	// this is the Json object to pass into Server actions
	public String[] splitOnEquals(String input) {
		String[] pairs = input.split("=");
		return pairs;
	}
	// this is the Json object to pass into Server actions
	public String removeLeadingChar(String input) {
		StringBuilder sb = new StringBuilder(input);
		String newString = sb.deleteCharAt(0).toString();
		return newString;
	}
	

}
