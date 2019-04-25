package cmdsFromClient;

import java.io.IOException;
import java.net.ServerSocket;
import com.google.gson.JsonObject;

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
    
    /*
	public static void main(String[] args) {
		ServerThread testServer = new ServerThread(8888);
        while(testServer.running) {
            try {
                System.out.println("Server Awaiting Client Connection...");
                new Thread(new ClientThreadConnect(testServer.getServerSocket().accept())).start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            testServer.getServerSocket().close();
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
     */
    
    /*
	public JsonObject getJsonFromWeb() {
		this method would be used to get the Json command strng
		from the server socket
	}
     */

}
