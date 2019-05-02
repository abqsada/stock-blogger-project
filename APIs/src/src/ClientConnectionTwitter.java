import java.io.*;
import java.net.Socket;

public class ClientConnectionTwitter implements Runnable {
	
	//Pulling in the twitter API java.
	static TwitterAPIExample api = new TwitterAPIExample();
    private Socket connection;
    //Creating the client connection 
    public ClientConnectionTwitter(Socket connection) {
        System.out.println("Connection Established New Thread...");
        System.out.println(connection.getRemoteSocketAddress());
        this.connection = connection;
    }
    //Runnable output the getTweetName to the HTTP page.
    @Override
    public void run() {
        try {
        	//declaring String from getTweetName, which is pulled from TwitterAPIExample.
        	final String OUTPUTName = api.getTweetName();
        	//Declaring Output headers for the HTTP page
        	final String OUTPUT_HEADERS = "HTTP/1.1 200 OK\r\n" +
                    "Content-Type: text/html\r\n" +
                    "Content-Length: " +
                    "Access-Control-Allow-Origin: *";
        	final String OUTPUT_END_OF_HEADERS = "\r\n\r\n";
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            System.out.println("Incoming Data...");
            String line = reader.readLine();
            // If line is not empty, print the inputstream Reader. If it is empty, break out of loop. 
            while(!line.isEmpty()) {
                System.out.println(line);
                line = reader.readLine();
                if(line.isEmpty()) {
                    break;
                }
            }
            //Outputs the final data to the HTTP page using UTF-8
            String response = OUTPUT_HEADERS + OUTPUTName.length() +  OUTPUT_END_OF_HEADERS + OUTPUTName;
            	connection.getOutputStream().write(response.getBytes("UTF-8"));
                connection.getOutputStream().flush();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
