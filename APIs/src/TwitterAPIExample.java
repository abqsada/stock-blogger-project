import twitter4j.*;
import twitter4j.conf.ConfigurationBuilder;


public class TwitterAPIExample {

	public static void main(String[] args) 
	{
		try {

		       ConfigurationBuilder cb = new ConfigurationBuilder();
		cb.setDebugEnabled(true).setOAuthConsumerKey("T9zEpYe54M7KibCWA2RYGttWH").setOAuthConsumerSecret("toTa0w2mQccojQC0dTpIy9hfZHLEpYSYUV3k1X7LWUVIZtaEGN").setOAuthAccessToken("1113817167940792320-qd5xTD72KsJsDmPV1ECzBtto8yu5Q8").setOAuthAccessTokenSecret("E69XScf6YSV7RCyakVn8wtSPhQf66AnZc3EPw1Z2qrNjd");

		        TwitterFactory tf = new TwitterFactory(cb.build());
		        Twitter twitter = tf.getInstance();

		        ResponseList<Location> locations;
		        locations = twitter.getAvailableTrends();

		        Integer idTrendLocation = getTrendLocationId("United States");

		        if (idTrendLocation == null) {
		        System.out.println("Trend Location Not Found");
		        System.exit(0);
		        }

		        Trends trends = twitter.getPlaceTrends(idTrendLocation);
		        for (int i = 0; i < trends.getTrends().length; i++) {
		        System.out.println(trends.getTrends()[i].getName());
		        }

		        System.exit(0);

		    } catch (TwitterException te) {
		        te.printStackTrace();
		        System.out.println("Failed to get trends: " + te.getMessage());
		        System.exit(-1);
		    }
	}
	 private static Integer getTrendLocationId(String locationName) {

		    int idTrendLocation = 0;

		    try {

		        ConfigurationBuilder cb = new ConfigurationBuilder();
		cb.setDebugEnabled(true).setOAuthConsumerKey("T9zEpYe54M7KibCWA2RYGttWH").setOAuthConsumerSecret("toTa0w2mQccojQC0dTpIy9hfZHLEpYSYUV3k1X7LWUVIZtaEGN").setOAuthAccessToken("1113817167940792320-qd5xTD72KsJsDmPV1ECzBtto8yu5Q8").setOAuthAccessTokenSecret("E69XScf6YSV7RCyakVn8wtSPhQf66AnZc3EPw1Z2qrNjd");

		        TwitterFactory tf = new TwitterFactory(cb.build());
		        Twitter twitter = tf.getInstance();

		        ResponseList<Location> locations;
		        locations = twitter.getAvailableTrends();

		        for (Location location : locations) {
		        if (location.getName().toLowerCase().equals(locationName.toLowerCase())) {
		            idTrendLocation = location.getWoeid();
		            break;
		        }
		        }

		        if (idTrendLocation > 0) {
		        return idTrendLocation;
		        }

		        return null;

		    } catch (TwitterException te) {
		        te.printStackTrace();
		        System.out.println("Failed to get trends: " + te.getMessage());
		        return null;
		    }

		    }

}

