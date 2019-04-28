package cmdsFromClient;

import java.io.IOException;
import java.net.ServerSocket;
import com.google.gson.JsonObject;

//import stock-blogger-project.APIs.src.ClientConnection;

/**
 * 
 * @author Ruth
 *  The purpose of the class is to open a threaded socket 
 *  to the stock-blogger webpage and wait for a command
 */
public class ServerThread {
    private ServerSocket serverSocket;
    private boolean running;

    public ServerThread(int port) {
        try {
            serverSocket = new ServerSocket(port);
        } catch (IOException e) {
            e.printStackTrace();
        }
        running = true;
    }

    public ServerSocket getServerSocket() {
        return serverSocket;
    }
    
	public void awaitClientCmd(JsonObject job) {
		// the JsonObject passed to this method is for testing.
		//  Ruth's intention is to remove this JsonObject parameter 
		//  once the connection from the web client is providing commands

        while(running) {
            try {
                System.out.println("Server Awaiting Client Connection...");
                // This is where we create a connection to the web client
                //new Thread(new ClientConnection(serverSocket.getServerSocket().accept())).start();
                
                // Now that we have some action from the web client 
                //  I assume we have a Json object that contains command & data
                ServerCmdParse cmdParsed = new ServerCmdParse(job);
                
                // take action with the parsed Json data
                JsonObject returnData = cmdParsed.takeAction();
                System.out.println(
                		("Json to be returned to web from this action\n"+returnData));
                // A successful action should result in some Json object 
                //  to return to the web, even if that object only has errorCode=0
                
                // ??How do we pass this Json back to the web client?

            
            //} catch (IOException e) {
            //    e.printStackTrace();
    		} finally {
    			// How do we signal Server termination? Would the web give the command?
    	        running = false;
            }
        }
        try {
        	// server ended running.  Clean up and exit
        	serverSocket.close();
            System.out.println("Bye from web client.\n");
            System.exit(0);
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
    
    /*
	public JsonObject getJsonFromWeb() {
		this method would be used to get the Json command string
		from the server socket
	}
     */

}
