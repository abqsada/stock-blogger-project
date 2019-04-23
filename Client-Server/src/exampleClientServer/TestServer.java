package exampleClientServer;

import java.net.*;
import java.io.*;

public class TestServer {
    public static void main(String[] args) throws IOException {
        // Server Port Number
        int portNumber = 4200;
        // Server host name
        String hostName = "localhost";
        // This try-with-resources statement is critical!
        try (
            // Create new server socket object with hostname 'localhost'
            ServerSocket serverSocket =
                new ServerSocket(portNumber);
            // Accepts the new socket as clientSocket
            Socket clientSocket = serverSocket.accept();  
            // Opens PrintWriter onto the clientSocket   
            PrintWriter out =
                new PrintWriter(clientSocket.getOutputStream(), true);  
            // Gets socket's input stream and opens BufferReader on it                 
            BufferedReader in = new BufferedReader(
                new InputStreamReader(clientSocket.getInputStream()));
        ) {
            String inputLine;
            // reads a line at a time from the standard input stream and immediately
            // sends it to the server by writing it to the PrintWriter connected to the socket
            while ((inputLine = in.readLine()) != null) {
                out.println(inputLine);
            }
        // Required Exception Handling
        } catch (IOException e) {
            System.out.println("Exception caught when trying to listen on port "
                + portNumber + " or listening for a connection");
            System.out.println(e.getMessage());
        }
    }
}