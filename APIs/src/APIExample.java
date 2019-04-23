import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import javax.net.ssl.HttpsURLConnection;
import org.json.JSONArray;
import org.json.JSONObject;
import java.io.FileWriter;
import java.io.IOException;

public class APIExample {

	private final String USER = "Ken";
	
	//HTTP GET Request
	public void sendGet() throws Exception
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
		
		//Printing the response to a json file
		 try (FileWriter file = new FileWriter("C:\\Users\\Ken-Laptop\\Documents\\GitHub\\stock-blogger-project\\test.json")) 
		 {

	            file.write(response.toString());
	            file.flush();
		 }
		//print result
		System.out.println(response.toString());
		

	}
	private void urlBuilder() 
	{
		String userInput = "";
		String urlPart1 = "https://www.worldtradingdata.com/api/v1/stock?symbol=";
		String urlPart2 = "&api_token=ljkHFuF0MiRoGZtWL8Y0BmaMNQvdcQPtfjL3Yid5rjPLOEA4cmr3OvT3NL1F";
		String finalUrl = "";
	}

	private void sendPost() throws Exception 
	{
		//declaring our post URL 
		String url = "http://localhost:3000";
		URL obj = new URL(url);
		//Setting our URL connection as an obj 
		HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();
		
		//add request header
		con.setRequestMethod("POST");
		con.setRequestProperty("User-Agent", USER);
		con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
		
		//POST parameters
		String urlParameters = "/api/v1/";
		
		// Send post request
		con.setDoOutput(true);
		DataOutputStream wr = new DataOutputStream(con.getOutputStream());
		wr.writeBytes(urlParameters);
		wr.flush();
		wr.close();
		
		int responseCode = con.getResponseCode();
		System.out.println("\nSending 'POST' request to URL : " + url);
		System.out.println("Post parameters : " + urlParameters);
		System.out.println("Response Code : " + responseCode);
		
		//Writing response to inputLine
		BufferedReader in = new BufferedReader(
		new InputStreamReader(con.getInputStream()));
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

	}
	
	public static void main(String[] args) throws Exception 
	{

		APIExample http = new APIExample();

		System.out.println("Testing 1 - Send Http GET request");
		http.sendGet();
		
		System.out.println("Testing 2 - Send Http POST request");
		http.sendPost();

	}

}
//https://www.worldtradingdata.com/api/v1/stock?symbol=AAPL,MSFT,HSBA.L&api_token=ljkHFuF0MiRoGZtWL8Y0BmaMNQvdcQPtfjL3Yid5rjPLOEA4cmr3OvT3NL1F
