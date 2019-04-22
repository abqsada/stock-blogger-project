import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class test {

	public static void main(String[] args) throws SQLException {
		Connection connection = DataConnection.getConnection();
		
		int iu = DataConnection.addUser("caleb", Date.valueOf("2000-11-01"), "p@ssword");
		
		System.out.println(iu);
		
		int ip = DataConnection.addPost(1, "some title", "some text", Date.valueOf("2000-12-01"));
		
		System.out.println(ip);
		
		int ic = DataConnection.addComment(1, 1, "some text", Date.valueOf("2002-12-01"));
		
		System.out.println(ic);

	}

}
