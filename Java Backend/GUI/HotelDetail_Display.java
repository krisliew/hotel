package GUI;

import java.awt.Color;
import java.awt.Font;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;


import CRUD.CRUD_1;
import CRUD.ConnectDatabase;
import CRUD.Room;

public class HotelDetail_Display extends JPanel {
	private JTextField txtEmail;
	private JTextField txtHotelName;
	private JTextField txtPhoneNumber;
	private JTextArea txtAddress;

	
	ArrayList<CRUD_1> roomList = new ArrayList<CRUD_1>();


	Room room = new Room(roomList);
	ConnectDatabase db = new ConnectDatabase();
	
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	
	
	
	/**
	 * Create the panel.
	 */
	public HotelDetail_Display() {

		JPanel panel = new JPanel();
    	panel.setBackground(Color.BLACK);
   	 	panel.setLayout(null);
		setLayout(null);

		JLabel lblFullName = new JLabel("Hotel Name:");
		lblFullName.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblFullName.setBounds(44, 111, 107, 16);
		add(lblFullName);

		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblEmail.setBounds(44, 146, 107, 16);
		add(lblEmail);
		
		JLabel lblContactNum = new JLabel("Phone Number:");
		lblContactNum.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblContactNum.setBounds(44, 178, 123, 16);
		add(lblContactNum);
		
		JLabel lblAddress = new JLabel("Address: ");
		lblAddress.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblAddress.setBounds(44, 221, 74, 16);
		add(lblAddress);
		
		txtEmail = new JTextField();
		txtEmail.setFont(new Font("Tahoma", Font.PLAIN, 18));
		txtEmail.setEditable(false);
		txtEmail.setColumns(10);
		txtEmail.setBounds(214, 143, 300, 22);
		add(txtEmail);
		
		txtHotelName = new JTextField();
		txtHotelName.setFont(new Font("Tahoma", Font.PLAIN, 18));
		txtHotelName.setEditable(false);
		txtHotelName.setColumns(10);
		txtHotelName.setBounds(214, 108, 300, 22);
		add(txtHotelName);
		
		txtAddress = new JTextArea();
		txtAddress.setFont(new Font("Tahoma", Font.PLAIN, 18));
		txtAddress.setEditable(false);
		txtAddress.setLineWrap(true);
		txtAddress.setBounds(214, 216, 501, 55);
		txtAddress.setBorder(BorderFactory.createLineBorder(Color.GRAY));
		add(txtAddress);
		
		txtPhoneNumber = new JTextField();
		txtPhoneNumber.setFont(new Font("Tahoma", Font.PLAIN, 18));
		txtPhoneNumber.setEditable(false);
		txtPhoneNumber.setColumns(10);
		txtPhoneNumber.setBounds(214, 177, 300, 22);
		add(txtPhoneNumber);

		//display the panel 
		displayData();
	}


	
	void displayData() {

		Connection conn = null;
		java.sql.Statement stmt;
		ResultSet rs;

		try {
			Class.forName(db.getClassName());//the database driver 
			
			String hotelName = "", phoneNumber = "", email = "" , address = "";
			
			
			conn = DriverManager.getConnection(db.getURL(), db.getUserName(), db.getPassword()); //connect to the database
			conn.setAutoCommit(false);//do not auto commit the SQL statements
			stmt = conn.createStatement();//prepare the create a SQL statement
			rs = stmt.executeQuery("SELECT hotelName, phoneNumber, email, address from hotelDetails");//SQL select query to display the class type // e.roleID,
			

			while (rs.next()) { 
				hotelName = rs.getString(1);
				phoneNumber = rs.getString(2);
				email = rs.getString(3);
				address = rs.getString(4);
            } 
			
			txtHotelName.setText(hotelName);
			txtPhoneNumber.setText(phoneNumber);
			txtEmail.setText(email);
			txtAddress.setText(address);
			
			rs.close();
			stmt.close();
			
			conn.commit();
			conn.close();
		} catch (ClassNotFoundException e) { //exception handling for class not found
           //e.printStackTrace(); 
		} catch (SQLException e) { //exception handling for SQL related exceptions 
			//e.printStackTrace(); 
		} finally{ 
            if(conn!=null){ 
                System.out.println("Connected successfully."); 
                try { 
                    conn.close(); 
                } catch (SQLException e) { 
                    //e.printStackTrace(); 
                } 
            } 
		}
		
	}
}
