package CRUD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class test extends ConnectDatabase{

	static String connection = "";
	
	ConnectDatabase db = new ConnectDatabase();
	
	public test() {
	    Connection conn = null;
	    try {
	    	Class.forName(db.getClassName());//the database driver 
	    	conn = DriverManager.getConnection(db.getURL(), db.getUserName(), db.getPassword()); //connect to the database
	    	connection = "got it!";
	      System.out.println("Got it!");
	      
	      
	      Statement stmt = null;
	      String query = "select * from hotelDetails";
	      try {
	          stmt = conn.createStatement();
	          ResultSet rs = stmt.executeQuery(query);
	          while (rs.next()) {
	              String name = rs.getString("hotelName");
	              System.out.println(name);
	          }
	      } catch (SQLException e ) {
	          throw new Error("Problem", e);
	      } finally {
	          if (stmt != null) { stmt.close(); }
	      }

	    } catch (SQLException e) {
	        throw new Error("Problem", e);
	    } catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
	      try {
	        if (conn != null) {
	            conn.close();
	        }
	      } catch (SQLException ex) {
	          System.out.println(ex.getMessage());
	      }
	    }
	  }
	
	public static void main(String[] args) {
		test a = new test();
		System.out.println(a.connection);
	}
	

}
