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
    
	public void awaitClientCmd() {
		// the JsonObject passed to this method is for testing.
		//  Ruth's intention is to remove this JsonObject parameter 
		//  once the connection from the web client is providing commands

        while(running) {
            try {
                System.out.println("Server Awaiting Client Connection...");
                // This is where we create a connection to the web client
                new Thread(new ClientThread(serverSocket.accept())).start();
                // the .start() method starts the Thread above running the method
                //  ClientThread.run()  
                //  which will parse the web command and start to take database actions
            } catch (IOException e) {
                System.out.println("Exception thrown in ServerThread.\n");
                e.printStackTrace();
//    		} finally {
//    			// How do we signal Server termination? Would the web give the command?
//    			// I believe this finally section will end the while loop after 1 execute.
//    	        running = false;
            }
        }
        try {
        	// server ended running.  Clean up and exit
        	serverSocket.close();
            System.out.println("Bye from ServerThread.\n");
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
