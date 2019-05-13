import java.io.IOException;
import java.net.ServerSocket;

public class Server {
	//Declaring variables
    private ServerSocket serverSocket;
    private boolean running;
    //Creating the Server Socket
    public Server(int port) 
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
    //Getter for Server Socket
    public ServerSocket getServerSocket() 
    {
        return serverSocket;
    }
    //Main method to create the server and await connection.
    public static void main(String[] args) 
    {
        Server testServer = new Server(3000);
        while(testServer.running) 
        {
            try 
            {
                System.out.println("Server Awaiting Client Connection...");
                new Thread(new ClientConnection(testServer.getServerSocket().accept())).start();
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