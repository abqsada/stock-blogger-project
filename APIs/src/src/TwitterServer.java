import java.io.IOException;
import java.net.ServerSocket;

public class TwitterServer {
	//Declaring Variables
    private ServerSocket serverSocket;
	private boolean running;
	//Declaring method to create server
    public TwitterServer(int port) 
    {
        try 
        {
            serverSocket = new ServerSocket(port);
        } 
        catch (IOException e) 
        {
            e.printStackTrace();
        }
        running = true;
    }
    //Creating getter for server socket.
    public ServerSocket getServerSocket() 
    {
        return serverSocket;
    }
    //Main Method to create server on defined port and await connection.
    public static void main(String[] args) 
    {
        TwitterServer testServer = new TwitterServer(3001);
        while(testServer.running) 
        {
        	//try clause to await server connection, accept the connection then start ClientConnectionTwitter 
            try 
            {
                System.out.println("Server Awaiting Client Connection...");
                new Thread(new ClientConnectionTwitter(testServer.getServerSocket().accept())).start();
            } 
            catch (IOException e) 
            {
                e.printStackTrace();
            }
        }
        try 
        {
            testServer.getServerSocket().close();
        } 
        catch (IOException e) 
        {
            e.printStackTrace();
        }
    }
}
