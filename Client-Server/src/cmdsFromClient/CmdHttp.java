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
	private String actionCommand;
	private JsonObject cmdData;
	
	/** Constructor parsing the commands from the web into Json
	 *  This constructor
	 * @param elements
	 */
	public CmdHttp(ArrayList<String[]> httpLines) {
		int sizeCmdLines = httpLines.size();
		System.out.println(("CmdHttpGet constructor with #lines: "+sizeCmdLines));
		JsonObject httpParams = new JsonObject();
		if(sizeCmdLines == 0) {
			System.out.println("There was no GET or PUT on this thread to provide data");
			// httpParams will remain empty & be caught later
		} else {

			// The ArrayList of array of strings was to get around resizing of array
			// so we should only have 1 line of commands to parse
			String[] elements = httpLines.get(0);
			for(String element : elements) {
				System.out.println(("element of command line: "+element ) );
			}
			// process the http line into our desired parameters
			// split along slashes to get major portions of string
			String[] pieces = splitOnSlash(elements[1]);
			// split along ampersand to get key=value pairs
			String[] params = splitOnAmpersand(pieces[1]);
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
				// getting the parameters into Json lets us pass this data around
				//  and decide later whether we have the data for the desired action 
				httpParams.addProperty(propertyName, propertyValue);
			}
		}
		this.actionCommand = setAction(httpParams);
		this.cmdData = httpParams;
	}
		
	public String setAction (JsonObject params) {
		String action="";
		if (params.has("command")) {
			action = params.get("command").toString().replace("\"", "");
        } else {
        	System.out.println("no command was in the Json recieved from HTTP\n");
        }
		return action;
	}

	// this is the command String
	public String getActionCmd() {
		return actionCommand;
	}
	
	// this is the Json object to pass into Server actions
	public JsonObject getCmdData() {
		return cmdData;
	}
	
	public String[] splitOnSpace(String input) {
		String[] spaceStrings = input.split(" ");
		return spaceStrings;
	}
	
	public String[] splitOnSlash(String input) {
		String[] slashStrings = input.split("/");
		return slashStrings;
	}
	
	public String[] splitOnAmpersand(String input) {
		String[] ampStrings = input.split("&");
		return ampStrings;
	}
	
	public String[] splitOnEquals(String input) {
		String[] pairs = input.split("=");
		return pairs;
	}

	public String removeLeadingChar(String input) {
		StringBuilder sb = new StringBuilder(input);
		String newString = sb.deleteCharAt(0).toString();
		return newString;
	}
	

}
