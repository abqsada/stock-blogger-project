package cmdsFromClient;

import java.io.*;
import java.net.Socket;
import com.google.gson.JsonObject;

public class ClientThread implements Runnable {
    private Socket connection;
    private static final String OUTPUT = "<html><head><title>Testing</title></head><body><p>%s</p></body></html>";
    private static final String OUTPUT_HEADERS = "HTTP/1.1 200 OK\r\n" +
            "Content-Type: text/html\r\n" +
            "Content-Length: ";
    private static final String OUTPUT_END_OF_HEADERS = "\r\n\r\n";
    //private String[] elements;


    public ClientThread(Socket connection) {
        System.out.println("Connection Established New Thread...");
        System.out.println(connection.getRemoteSocketAddress());
        this.connection = connection;
    }

    @Override
    public void run() {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            System.out.println("Incoming Data...");
            String line = reader.readLine();
			String outputString = "";
			// create the String array to accumulate the web command
			String[] elements;
            while(!line.isEmpty()) {
                System.out.println(line);
				if (line.startsWith("GET")) {
					// save the command 'elements' for later action
					elements = line.split("/");
					System.out.print(elements[1]);
					if (elements[1].equals("user") ) {
						outputString = "I'll return information about user " + elements[2];
					}
				}
                line = reader.readLine();
                if(line.isEmpty()) {
                    break;
                }
            }
			System.out.println(outputString);
			// Create objects to react to GET & PUT (add) requests for users, posts, or comments
			CmdGet cmdGet = new CmdGet(elements);
			
			// Now request server action 
			ServerCmdParse serverCmd = new ServerCmdParse(cmdGet.getWebCmd() );
			JsonObject jsonReturnFromDb = serverCmd.takeAction();
			String response = jsonReturnFromDb.toString();
			// I don't know whether I need to add anything to this String
			//String response = OUTPUT_HEADERS + (OUTPUT.length() - 2 + outputString.length())  + OUTPUT_END_OF_HEADERS + String.format(OUTPUT,outputString);
            connection.getOutputStream().write(response.getBytes("UTF-8"));
            
            connection.getOutputStream().flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
