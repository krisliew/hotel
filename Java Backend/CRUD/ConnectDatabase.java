package CRUD;

import java.sql.SQLException;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;

@SuppressWarnings("serial")//instruct the compiler to ignore / suppress specified compiler warning in annotated element & all program element inside this class

//abstract ConnectDatabase class used to like the system to the mySQL database
public class ConnectDatabase {
	
	private static final int MYSQL_DUPLICATE_PK = 1062;  //Catch Duplicated primary key SQL code exception
	private static final int MYSQL_INTEGRITY_CONSTRAINT = 1451; // Catch java.sql.SQLIntegrityConstraintViolationException: Cannot delete or update a parent row: a foreign key constraint fails
	private static final int MYSQL_NO_FK = 1452; // Catch java.sql.SQLIntegrityConstraintViolationException: Cannot add or update a child row
	
	//the field needed to link to the database
	private final String className;
	private final String url;
	private final String username;
	private final String password;
	
	DefaultTableModel model = new DefaultTableModel() {
		public boolean isCellEditable(int row, int column) {//return true if the specific cell is editable
			return false;
	    }
	};	

	//constructor that holds the field details for database connection
	public ConnectDatabase(){
		className = "com.mysql.jdbc.Driver";
		url = "jdbc:mysql://localhost:3306/hotel?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
		username = "root";
		password = "";
	}
	
	//getter method for returning the className of the database driver
	public String getClassName() {
		return className;
	}
	
	//getter method for returning the url of the database 
	public String getURL() {
		return url;
	}
	
	//getter method for returning the username of the database 
	public String getUserName() {
		return username;
	}
	
	//getter method for returning the password of the database 
	public String getPassword() {
		return password;
	}	
	
	//method to detect duplicated unique fields
	void addException(Exception e2){
		if(((SQLException) e2).getErrorCode() == MYSQL_DUPLICATE_PK ){
			JOptionPane.showMessageDialog(null,"ERROR: Duplicated Class ID! Please enter a different Class ID!");
	    }else if(((SQLException) e2).getErrorCode() == MYSQL_NO_FK){
	    	JOptionPane.showMessageDialog(null,"ERROR: Input ID may be non-existant, please check if ID input is valid!");
	    }else{
			JOptionPane.showMessageDialog(null,"Add Failed!");
			System.out.println(e2);
			System.out.println(((SQLException) e2).getErrorCode());
	    }
	}
		
	//method that prevents rows to be dropped if there are foreign keys linked to the row
	void deleteException(Exception e2) {
		if(((SQLException) e2).getErrorCode() == MYSQL_INTEGRITY_CONSTRAINT ) {
			JOptionPane.showMessageDialog(null,"ERROR: Deleting row record exist in Booking Venue!"); 
		}else {
			JOptionPane.showMessageDialog(null,"Delete Error!");
			System.out.println(e2);
			System.out.println(((SQLException) e2).getErrorCode());
		}
	}

	//method that prevents the same time slot to be booked twice
	void updateException(Exception e2){
		if(((SQLException) e2).getErrorCode() == MYSQL_INTEGRITY_CONSTRAINT ){
			JOptionPane.showMessageDialog(null,"ERROR: Error Updating");
		}else if(((SQLException) e2).getErrorCode() == MYSQL_DUPLICATE_PK ){
			JOptionPane.showMessageDialog(null,"ERROR: Venue already booked by others!");
	    }else if(((SQLException) e2).getErrorCode() == MYSQL_NO_FK){
	    	JOptionPane.showMessageDialog(null,"ERROR: Input ID may be non-existant, please check if ID input is valid!");
	    }else{
			JOptionPane.showMessageDialog(null,"Update Error!"); 
			System.out.println(e2);
			System.out.println(((SQLException) e2).getErrorCode());			
		}
	}
}
