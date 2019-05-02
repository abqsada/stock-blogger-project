package cmdsFromClient;

import com.google.gson.JsonObject;

public class CmdGet  {
	String[] elements;
	private JsonObject webCmd;
	private JsonObject webInsideCmd;
	String userId;
	//String transient? postId; so that we may or may not need poetID
	
	/** Constructor parsing the commands from the web into Json
	 * 
	 * @param elements
	 */
	public CmdGet(String[] elements) {
		super();
		this.elements = elements;
		if (elements[1].equals("user") ) {
			this.webCmd.addProperty("command", "getuser");
			if(!elements[2].equals("") ) {
				this.userId = elements[2];
				this.webInsideCmd.addProperty("userId", userId);
				this.webCmd.add("commandData", webInsideCmd);
			}
					
		} else if (elements[1].equals("post") ) {
			this.webCmd.addProperty("command", "getpost");
            System.out.println("Action Not implemented yet.\n");			
		} else if (elements[1].equals("comment") ) {
			this.webCmd.addProperty("command", "getcomment");
            System.out.println("Action Not implemented yet.\n");
		} else  {
			// signal that this GET command is not recognized
		}
	}
	
	// this is the Json object to pass into Server actions
	public JsonObject getWebCmd() {
		return webCmd;
	}

	

}
