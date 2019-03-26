import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import javax.net.ssl.HttpsURLConnection;

public class APIExample {

	private final String USER = "Ken";
	
	//HTTP GET Request
	public void sendGet() throws Exception
	{
		String ApiUrl = "https://www.worldtradingdata.com/api/v1/stock?symbol=AAPL,MSFT,HSBA.L&api_token=ljkHFuF0MiRoGZtWL8Y0BmaMNQvdcQPtfjL3Yid5rjPLOEA4cmr3OvT3NL1F";
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
		

	}
	public static void main(String[] args) throws Exception 
	{

		APIExample http = new APIExample();

		System.out.println("Testing 1 - Send Http GET request");
		http.sendGet();

	}

}
//https://www.worldtradingdata.com/api/v1/stock?symbol=AAPL,MSFT,HSBA.L&api_token=ljkHFuF0MiRoGZtWL8Y0BmaMNQvdcQPtfjL3Yid5rjPLOEA4cmr3OvT3NL1F
