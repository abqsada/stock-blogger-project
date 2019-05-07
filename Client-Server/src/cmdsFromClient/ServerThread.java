package cmdsFromClient;

import java.io.IOException;
import java.net.ServerSocket;

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
        while(running) {
            try {
            	// Waiting for a command from a web browser that opens port 8888
            	// and gives commands and data in the URL string
            	// i.e.: localhost:8888/?action=getUserbyid&user_id=5
                System.out.println("Server Awaiting Client Connection...");
                // create a thread to recieve connection from the web client 
                new Thread(new ClientThread(serverSocket.accept())).start();
                // the .start() method starts the Thread above running the method
                //  ClientThread.run()  
                //  which will parse the web command and start database actions
            } catch (IOException e) {
                System.out.println("Exception thrown in ServerThread.\n");
                e.printStackTrace();
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

}
