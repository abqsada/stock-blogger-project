/*This client program is straightforward and simple because the echo server implements a simple
 protocol. The client sends text to the server, and the server echoes it back. When your client 
 programs are talking to a more complicated server such as an HTTP server, your client program 
 will also be more complicated. However, the basics are much the same as they are in this program
*/
import java.io.*;
import java.net.*;

public class TestClient {
    public static void main(String[] args) throws IOException {
        // Server Port Number
        int portNumber = 4200;
        // Server host name
        String hostName = "localhost";

        // This try-with-resources statement is critical!
        try (
            // Creates new socket object echoSocket
            // Parameters of localhost, 4200
            Socket echoSocket = new Socket(hostName, portNumber);
            // Gets the socket's output stream and opens a PrintWriter on it
            PrintWriter out =
                new PrintWriter(echoSocket.getOutputStream(), true);
            // Gets the socket's input stream and opens a BufferedReader on it    
            BufferedReader in =
                new BufferedReader(
                    new InputStreamReader(echoSocket.getInputStream()));
            // To send data through the socket to the server, the TestClient
            // needs to write to the PrintWriter. To get the server's response,
            // TestClient reads from the BufferedReader object stdIn
            BufferedReader stdIn =
                new BufferedReader(
                    new InputStreamReader(System.in))
        ) {
            String userInput;
            // reads a line at a time from the standard input stream and immediately
            // sends it to the server by writing it to the PrintWriter connected to the socket
            while ((userInput = stdIn.readLine()) != null) {
                out.println(userInput);
                // reads line of info from the BufferReader connected to the socket
                System.out.println("echo: " + in.readLine()); 
            }
        // Required Exception Handling
        } catch (UnknownHostException e) {
            System.err.println("Don't know about host " + hostName);
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection to " +
                hostName);
            System.exit(1);
        } 
    }
}