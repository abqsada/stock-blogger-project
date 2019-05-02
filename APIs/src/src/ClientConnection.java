import java.io.*;
import java.net.Socket;

public class ClientConnection implements Runnable {

	static APIExample api = new APIExample();
    private Socket connection;
   
    public ClientConnection(Socket connection) {
        System.out.println("Connection Established New Thread...");
        System.out.println(connection.getRemoteSocketAddress());
        this.connection = connection;
    }

    @Override
    public void run() {
        try {
        	final String OUTPUT = api.sendGet();
        	final String OUTPUT_HEADERS = "HTTP/1.1 200 OK\r\n" +
                    "Content-Type: text/html\r\n" +
                    "Content-Length: ";
        	final String OUTPUT_END_OF_HEADERS = "\r\n\r\n";
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            System.out.println("Incoming Data...");
            String line = reader.readLine();
            while(!line.isEmpty()) {
                System.out.println(line);
                line = reader.readLine();
                if(line.isEmpty()) {
                    break;
                }
            }
            String response = OUTPUT_HEADERS + OUTPUT.length() + OUTPUT_END_OF_HEADERS + OUTPUT;
            connection.getOutputStream().write(response.getBytes("UTF-8"));
            connection.getOutputStream().flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
