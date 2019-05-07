package cmdsFromClient;

import java.io.*;
import java.net.Socket;
import com.google.gson.JsonObject;
import java.util.ArrayList;
import serverMain.MainBusiness;

public class ClientThread implements Runnable {
    private Socket connection;
    private static final String OUTPUT = "<html><head><title>Testing</title></head><body><p>%s</p></body></html>";
    private static final String OUTPUT_HEADERS = "HTTP/1.1 200 OK\r\n" +
            "Content-Type: text/html\r\n" +
            "Content-Length: ";
    private static final String OUTPUT_END_OF_HEADERS = "\r\n\r\n";
    private ArrayList<String[]> httpLines;


    public ClientThread(Socket connection) {
        System.out.println("Connection Established New Thread...");
        System.out.println(connection.getRemoteSocketAddress());
        this.connection = connection;
        this.httpLines = new ArrayList<String[]>();
    }

    @Override
    public void run() {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            System.out.println("Incoming Data...");
            String line = reader.readLine();
            while(!line.isEmpty()) {
                System.out.println(("http line: "+line));
				if (line.startsWith("GET") || line.startsWith("PUT")) {
					// split the command line into separate strings for later action
					String[] elements = line.split(" ");
					for(String element : elements) {
						System.out.println(("element of http cmd line: "+element ) );
					}
					// Save this line of command strings from Http web command
					httpLines.add(elements);
				}
                line = reader.readLine();
                if(line.isEmpty()) {
                    break;
                }
            }
			// Create objects to react to GET (retrieve) & PUT (add) requests for users, posts, or comments
			CmdHttp cmd = new CmdHttp(httpLines);
			// Create object ready for database action
			//ServerCmdParse serverCmd = new ServerCmdParse(cmd.getActionCmd(), cmd.getCmdData() );
			ServerCmdParse serverCmd = new ServerCmdParse();
			
            // This next code was testing Json commands driven from the console
            // delete this code once the thread is providing commands
            JsonObject job = new JsonObject();
            job = MainBusiness.getConsoleCommand();
            if (job.has("command")) {
    			String actionCmd = job.get("command").toString().replace("\"", "");
    			// the action "webCommand" will skip further testing with the console
    			if(actionCmd.equalsIgnoreCase("webCommand") ) {
    				//  Use the commands interpreted from the web 
    				serverCmd = new ServerCmdParse(cmd.getActionCmd(), cmd.getCmdData() );
        		} else {
    				//  parse the JsonObject job from the console 
    				serverCmd = new ServerCmdParse(job);
        			
        		}
    		}

			// Now request server action from database
			JsonObject jsonReturnFromDb = serverCmd.takeAction();
			String jsonResponse = jsonReturnFromDb.toString();
			int lenJResponse = jsonResponse.length();
            System.out.println(("Json response string = \n"+jsonResponse));

			// I don't know whether I need to add anything to this String
			String response = OUTPUT_HEADERS + (OUTPUT.length() - 2 + lenJResponse)  + OUTPUT_END_OF_HEADERS + String.format(OUTPUT,jsonResponse);
            connection.getOutputStream().write(response.getBytes("UTF-8"));
            // send the output buffer back to the web
            connection.getOutputStream().flush();
        } catch (IOException e) {
            System.out.println("Exception thrown in ServerThread.\n");
            e.printStackTrace();
        }
    }


}
