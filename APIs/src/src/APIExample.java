import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import javax.net.ssl.HttpsURLConnection;
import java.io.FileWriter;
import java.io.IOException;
import java.net.Socket;

public class APIExample implements Runnable{

	private static Socket connection;
	private final String USER = "Ken";
	
	//HTTP GET Request
	public String sendGet() throws Exception
	{
		String ApiUrl = "https://www.worldtradingdata.com/api/v1/stock?symbol=AAPL,MSFT,&api_token=ljkHFuF0MiRoGZtWL8Y0BmaMNQvdcQPtfjL3Yid5rjPLOEA4cmr3OvT3NL1F";
		URL obj = new URL(ApiUrl);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
		
		con.setRequestMethod("GET");
		
		//Request Header
		con.setRequestProperty("User-Agent", USER);
		
		//Sending the GET Request signal
		
		int responseCode = con.getResponseCode();
		System.out.println("\nSending 'GET' request to URL : " + ApiUrl);
		System.out.println("Response Code : " + responseCode);
		
		//Declaring the API Reader
		BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();
		
		//Writing the API response to String inputLine
		while ((inputLine = in.readLine()) != null) 
		{
			response.append(inputLine);
		}
		in.close();
		
		//print result
		System.out.println(response.toString());
		return response.toString();
		

	}
	private void urlBuilder() 
	{
		String userInput = "";
		String urlPart1 = "https://www.worldtradingdata.com/api/v1/stock?symbol=";
		String urlPart2 = "&api_token=ljkHFuF0MiRoGZtWL8Y0BmaMNQvdcQPtfjL3Yid5rjPLOEA4cmr3OvT3NL1F";
		String finalUrl = "";
	}

	String sendPost(String string) throws Exception
	{
		//POST parameters
		String urlParameters = "/api/ticker";
		
		//declaring our post URL 
		String url = "http://localhost:3000";
		URL obj = new URL(null, url + urlParameters, new sun.net.www.protocol.https.Handler());
		//Setting our URL connection as an obj 
		HttpsURLConnection connection = (HttpsURLConnection) obj.openConnection();
		
		//add request header
		connection.setRequestMethod("POST");
		connection.setRequestProperty("User-Agent", USER);
		connection.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
		
		// Send post request
		connection.setDoOutput(true);
		DataOutputStream wr = new DataOutputStream(connection.getOutputStream());
		wr.writeBytes(urlParameters);
		wr.flush();
		wr.close();
		
		int responseCode = connection.getResponseCode();
		System.out.println("\nSending 'POST' request to URL : " + url);
		System.out.println("Post parameters : " + urlParameters);
		System.out.println("Response Code : " + responseCode);
		
		//Writing response to inputLine
		BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();
		//While loop to write response to input line
		while ((inputLine = in.readLine()) != null) 
		{
			response.append(inputLine);
		}
		in.close();
		
		
		//print result
		System.out.println(response.toString());
		return response.toString();

	}
	
	public static void main(String[] args) throws Exception 
	{

		APIExample http = new APIExample();

		System.out.println("Testing 1 - Send Http GET request");
		http.sendGet();
		
		System.out.println("Testing 2 - Send Http POST request");
		//http.sendPost();

	}
	
	@Override
	public void run() 
	{
		try {
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
            String response = sendGet();
            connection.getOutputStream().write(response.getBytes("UTF-8"));
            connection.getOutputStream().flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
	}

}
//https://www.worldtradingdata.com/api/v1/stock?symbol=AAPL,MSFT,HSBA.L&api_token=ljkHFuF0MiRoGZtWL8Y0BmaMNQvdcQPtfjL3Yid5rjPLOEA4cmr3OvT3NL1F
