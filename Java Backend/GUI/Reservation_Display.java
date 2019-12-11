package GUI;


import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;


import CRUD.CRUD_1;
import CRUD.ConnectDatabase;
import CRUD.Reservation;




public class Reservation_Display extends JPanel {

	private JTextField txtSearch;
	private JTextField txtCheckOutDate;
	private JTable table;
	private JTextField txtReservationID;
	private JTextField txtRoomID;
	private JTextField txtGuestID;
	private JTextField txtReservationTotalPrice;
	private JTextField txtEmpCode;
	private JTextField textField;
	private JTextField txtAdultPax;
	private JTextField txtSpecialRemarks;
	


	
	ArrayList<CRUD_1> reservationList = new ArrayList<CRUD_1>();


	Reservation reservation = new Reservation(reservationList);
	ConnectDatabase db = new ConnectDatabase();
	
	CRUD_1 reservations ;
	private JTextField txtCheckInDate;
	private JTextField txtArrivalTime;
	private JTextField txtReservationEmailStatus;
	private JTextField txtChildPax;

	
	
	/**
	 * Create the panel.
	 */
	public Reservation_Display() {

		JPanel panel = new JPanel();
    	panel.setBackground(Color.BLACK);
   	 	panel.setLayout(null);
		setLayout(null);
		
		
		
		JLabel lblRoomID = new JLabel("Room ID:");
		lblRoomID.setBounds(36, 78, 76, 16);
		add(lblRoomID);
		
		JLabel lblCheckOutDate = new JLabel("Check Out Date:");
		lblCheckOutDate.setBounds(36, 211, 121, 16);
		add(lblCheckOutDate);
		
		JLabel lblGuestID = new JLabel("Guest ID:");
		lblGuestID.setBounds(36, 109, 76, 16);
		add(lblGuestID);
		
		JLabel lblReservationTotalPrice = new JLabel("Reservation Total Price:");
		lblReservationTotalPrice.setBounds(36, 348, 144, 16);
		add(lblReservationTotalPrice);
		
		JLabel lblReservationID = new JLabel("Reservation ID:");
		lblReservationID.setBounds(36, 47, 121, 16);
		add(lblReservationID);
		
		txtCheckOutDate = new JTextField();
		txtCheckOutDate.setColumns(10);
		txtCheckOutDate.setBounds(205, 211, 121, 22);
		add(txtCheckOutDate);
		
		txtReservationTotalPrice = new JTextField();
		txtReservationTotalPrice.setText("0.00");
		txtReservationTotalPrice.setColumns(10);
		txtReservationTotalPrice.setBounds(205, 345, 121, 22);
		add(txtReservationTotalPrice);


		
		JLabel lblSearch = new JLabel("Search:");
		lblSearch.setBounds(967, 27, 45, 16);
		add(lblSearch);
		
		txtSearch = new JTextField();
		txtSearch.setBounds(1024, 24, 200, 22);
		add(txtSearch);
		txtSearch.setColumns(10);
		
		JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				if(fieldCheck("Insert", txtReservationID.getText(), txtRoomID.getText(), txtGuestID.getText(),  txtCheckInDate.getText(),txtArrivalTime.getText(),txtCheckOutDate.getText(),txtAdultPax.getText(), txtChildPax.getText(), txtReservationEmailStatus.getText(),txtReservationTotalPrice.getText(),txtSpecialRemarks.getText()) == 0) {
					
					String query = "INSERT INTO reservation VALUES ('" +txtReservationID.getText()+ "','" +txtRoomID.getText()+ "','" +txtGuestID.getText()+ "','" +txtCheckInDate.getText()+ "','" + txtArrivalTime.getText()+ "','" + txtCheckOutDate.getText()+ "','" + txtAdultPax.getText()+ "','" +txtChildPax.getText()+ "','" +txtReservationEmailStatus.getText()+ "','" + txtReservationTotalPrice.getText()+ "','" + txtSpecialRemarks.getText() + "')";
						
					reservation.addRecord(reservationList, new CRUD_1(txtReservationID.getText(), txtRoomID.getText(), txtGuestID.getText(),  txtCheckInDate.getText(),txtArrivalTime.getText(),txtCheckOutDate.getText(),Integer.parseInt(txtAdultPax.getText()),Integer.parseInt(txtChildPax.getText()),txtReservationEmailStatus.getText(),Double.parseDouble(txtReservationTotalPrice.getText()), txtSpecialRemarks.getText()));
					
						executeSQL(query, "Inserted");

				}

			}
		});
		btnAdd.setBounds(12, 427, 86, 39);
		add(btnAdd);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				if(fieldCheck("Update", txtReservationID.getText(), txtRoomID.getText(), txtGuestID.getText(),  txtCheckInDate.getText(),txtArrivalTime.getText(),txtCheckOutDate.getText(),txtAdultPax.getText(), txtChildPax.getText(), txtReservationEmailStatus.getText(),txtReservationTotalPrice.getText(),txtSpecialRemarks.getText()) == 0) {
						
					String query = "UPDATE reservation SET checkInDate = '" +txtCheckInDate.getText()+"', arrivalTime ='" +txtArrivalTime.getText()+"', checkOutDate ='" +txtCheckOutDate.getText()+"', adultPax ='" +txtAdultPax.getText()+"', childPax ='" +txtChildPax.getText()+"', reservationEmailStatus ='" +txtReservationEmailStatus.getText()+"', reservationTotalPrice ='" +txtReservationTotalPrice.getText()+"', specialRequest ='" +txtSpecialRemarks.getText()+"' WHERE reservationID ='"+txtReservationID.getText()+ "' and roomID = '"+txtRoomID.getText()+ "' and guestID = '"+txtGuestID.getText()+"'";
					
					
					reservation.setRecord(reservationList, new CRUD_1(txtReservationID.getText(), txtRoomID.getText(), txtGuestID.getText(),  txtCheckInDate.getText(),txtArrivalTime.getText(),txtCheckOutDate.getText(),Integer.parseInt(txtAdultPax.getText()),Integer.parseInt(txtChildPax.getText()),txtReservationEmailStatus.getText(),Double.parseDouble(txtReservationTotalPrice.getText()), txtSpecialRemarks.getText()));
					
					executeSQL(query, "Updated");
				}
			}
		});
		btnUpdate.setBounds(110, 427, 86, 39);
		add(btnUpdate);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(fieldCheck("Delete", txtReservationID.getText(), txtRoomID.getText(), txtGuestID.getText(),  txtCheckInDate.getText(),txtArrivalTime.getText(),txtCheckOutDate.getText(),txtAdultPax.getText(), txtChildPax.getText(), txtReservationEmailStatus.getText(),txtReservationTotalPrice.getText(),txtSpecialRemarks.getText()) == 0) {
					
					String query = "DELETE FROM reservation WHERE reservationID ='"+txtReservationID.getText()+ "' and roomID = '"+ txtRoomID.getText() + "' and guestID = '"+txtGuestID.getText()+"'";
					
					reservation.deleteRecord(reservationList, txtReservationID.getText(),txtRoomID.getText(),txtGuestID.getText());
					executeSQL(query, "Deleted");
				}
			}
		});
		btnDelete.setBounds(213, 427, 86, 39);
		add(btnDelete);
		
		
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(338, 62, 886, 402);
		add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JLabel lblAdultPax = new JLabel("Adult Pax:");
		lblAdultPax.setBounds(36, 246, 142, 16);
		add(lblAdultPax);
		
		txtAdultPax = new JTextField();
		txtAdultPax.setColumns(10);
		txtAdultPax.setBounds(205, 246, 121, 22);
		add(txtAdultPax);
		
		JLabel lblSpecialRemarks = new JLabel("Special Remarks:");
		lblSpecialRemarks.setBounds(36, 378, 160, 16);
		add(lblSpecialRemarks);
		
		txtSpecialRemarks = new JTextField();
		txtSpecialRemarks.setColumns(10);
		txtSpecialRemarks.setBounds(205, 378, 121, 22);
		add(txtSpecialRemarks);
		
		txtReservationID = new JTextField();
		txtReservationID.setColumns(10);
		txtReservationID.setBounds(205, 44, 121, 22);
		add(txtReservationID);
		
		txtRoomID = new JTextField();
		txtRoomID.setColumns(10);
		txtRoomID.setBounds(205, 75, 121, 22);
		add(txtRoomID);
		
		txtGuestID = new JTextField();
		txtGuestID.setColumns(10);
		txtGuestID.setBounds(205, 106, 121, 22);
		add(txtGuestID);
		
		txtCheckInDate = new JTextField();
		txtCheckInDate.setColumns(10);
		txtCheckInDate.setBounds(205, 138, 121, 22);
		add(txtCheckInDate);
		
		txtArrivalTime = new JTextField();
		txtArrivalTime.setColumns(10);
		txtArrivalTime.setBounds(205, 173, 121, 22);
		add(txtArrivalTime);
		
		JLabel lblArrivalTime = new JLabel("Arrival Time:");
		lblArrivalTime.setBounds(36, 173, 142, 16);
		add(lblArrivalTime);
		
		JLabel lblCheckInDate = new JLabel("Check In Date:");
		lblCheckInDate.setBounds(36, 138, 121, 16);
		add(lblCheckInDate);
		
		txtReservationEmailStatus = new JTextField();
		txtReservationEmailStatus.setColumns(10);
		txtReservationEmailStatus.setBounds(205, 313, 121, 22);
		add(txtReservationEmailStatus);
		
		txtChildPax = new JTextField();
		txtChildPax.setColumns(10);
		txtChildPax.setBounds(205, 278, 121, 22);
		add(txtChildPax);
		
		JLabel lblChildPax = new JLabel("Child Pax:");
		lblChildPax.setBounds(36, 278, 121, 16);
		add(lblChildPax);
		
		JLabel lblReservationStatus = new JLabel("Reservation Status:");
		lblReservationStatus.setBounds(36, 313, 142, 16);
		add(lblReservationStatus);
		
		

		
		//display the room panel 
		displayData();			
	}


	/*
	Validates the fields in the table for empty and invalid inputs & prompts proper error message
	
	@param actionName: whether the user is inserting, updating / deleting the record
	@param ID, ..., role : All the fields that undergo the validation
	@return errCounter: if the errCounter returns 0, then there are no error encountered 
	*/
	int fieldCheck(String actionName, String reservationID, String roomID, String guestID, String checkInDate, String arrivalTime, String checkOutDate, String adultPax, String childPax, String reservationEmailStatus,String reservationTotalPrice,String specialRequest) {
		int errCounter = 0;
		
		if(reservationID.equals("") || roomID.equals("") || guestID.equals("") || checkInDate.equals("")  || checkInDate.equals("") || checkOutDate.equals("") || adultPax.equals("")|| childPax.equals("")  || reservationEmailStatus.equals("") || reservationTotalPrice.equals("") || specialRequest.equals("") ) {
			JOptionPane.showMessageDialog(null, "Please fill in all the fields!");
			errCounter ++;
		}else {
			if(!reservation.validateDouble(reservationTotalPrice) || !reservation.validateInt(adultPax) || !reservation.validateInt(childPax)) {
				JOptionPane.showMessageDialog(null, "Pax can only have digits and Prices can only have digits & decimals! ");
				errCounter ++;
			}else{
				if(actionName.equals("Insert")) {
					if(reservation.isIdExist(reservationList, reservationID, roomID, guestID)){
						JOptionPane.showMessageDialog(null, "Failed to add new reservation record as the same ID already exists!");
						errCounter ++;
					}
				}
							
				if(actionName.equals("Update")) {
					if(reservation.isRecordExist(reservationList, new CRUD_1(reservationID, roomID, guestID, checkInDate, arrivalTime, checkOutDate, Integer.parseInt(adultPax), Integer.parseInt(childPax), reservationEmailStatus, Double.parseDouble(reservationTotalPrice), specialRequest ))){
						JOptionPane.showMessageDialog(null, " Failed to update reservation record as the exact record already exists!");
						errCounter ++;
					}else if(!reservation.isIdExist(reservationList, reservationID, roomID, guestID)){
						JOptionPane.showMessageDialog(null, "Failed to update reservation record as no such ID is found!");
						errCounter ++;
					}
				}
			}

			if(actionName.equals("Delete")) {
				if(!reservation.isIdExist(reservationList, reservationID, roomID, guestID)){
					JOptionPane.showMessageDialog(null, "Failed to update reservation record as no such ID is found!");
					errCounter ++;
				}
			}
		}

		return errCounter;
	}



	/*
	Executes Insert, Update and Delete Queries passed in when the user clicks the Add, Update or Delete Button
		
	@param query: The mysql query that the button will pass
	@param message: The message to indicate the action of this query (Insert / Update / Delete) 
	*/
	void executeSQL(String query, String message) {
		
		Connection conn = null;
		java.sql.Statement stmt;
		ResultSet rs;
		
		
	       try{
	    	   conn = DriverManager.getConnection(db.getURL(), db.getUserName(), db.getPassword()); //connect to the database
	   		
	    	   stmt = conn.createStatement();
	           if((stmt.executeUpdate(query)) == 1)
	           {
	               // refresh jtable data
	               DefaultTableModel model = (DefaultTableModel)table.getModel();
	               model.setRowCount(0);
	               refreshTable();

	               
	               JOptionPane.showMessageDialog(null, "Data "+message+" Successfully");
	           }else{
	               JOptionPane.showMessageDialog(null, "Data Not "+message);
	           }
	       }catch(Exception ex){
	           ex.printStackTrace();
	       }
	}
	
	void displayData() {
		
		

		Connection conn = null;
		java.sql.Statement stmt;
		ResultSet rs;

		table.setBounds(296, 364, 578, -278);
		DefaultTableModel model = (DefaultTableModel) table.getModel();

		//Header of the JTable
		Object[] columns = {"Reservation ID", "Room ID", "Guest ID", "Check-In Date Time", "Arrival Time", "Check-Out Date Time", "Adult Pax", "Child Pax", "Reservation Status", "Reservation Total Price", "Special Requests"  };
		
		model.setColumnIdentifiers(columns);
		table.setModel(model);
		
		//set table styling
		table.setBackground(Color.white);
		table.setForeground(Color.black);
		Font font = new Font("Arial", 1, 12);
		table.setFont(font);
		table.setRowHeight(30);
		
		//put the values in the table to be in the center of each column
		DefaultTableCellRenderer Renderer = new DefaultTableCellRenderer();
		Renderer.setHorizontalAlignment(JLabel.CENTER);
		
		for(int i = 0; i < 11; i++) {
				table.getColumnModel().getColumn(i).setCellRenderer(Renderer);
		}
		
		//allow the selected row values to be displayed in the JTextField
		table.addMouseListener(new MouseAdapter() {
			
			public void mouseClicked(MouseEvent e) {
				int i = table.getSelectedRow();
				
				if (table.getRowSorter()!=null) {
				    i = table.getRowSorter().convertRowIndexToModel(i);
				}
				
				txtReservationID.setText(model.getValueAt(i, 0).toString());
				txtRoomID.setText(model.getValueAt(i, 1).toString());
				txtGuestID.setText(model.getValueAt(i, 2).toString());
				txtCheckInDate.setText(model.getValueAt(i, 3).toString());
				txtArrivalTime.setText(model.getValueAt(i, 4).toString());
				txtCheckOutDate.setText(model.getValueAt(i, 5).toString());
				txtAdultPax.setText(model.getValueAt(i, 6).toString());
				txtChildPax.setText(model.getValueAt(i, 7).toString());
				txtReservationEmailStatus.setText(model.getValueAt(i, 8).toString());
				txtReservationTotalPrice.setText(model.getValueAt(i, 9).toString());
				txtSpecialRemarks.setText(model.getValueAt(i, 10).toString());
				
			}
			
		});
		
		
		
		try {
			Class.forName(db.getClassName());//the database driver 
			

			
			
			String reservationID,roomID,guestID, checkInDate, arrivalTime, checkOutDate, reservationEmailStatus, specialRequest ;
			int adultPax, childPax;
			double reservationTotalPrice;
			
			conn = DriverManager.getConnection(db.getURL(), db.getUserName(), db.getPassword()); //connect to the database
			conn.setAutoCommit(false);//do not auto commit the SQL statements
			stmt = conn.createStatement();//prepare the create a SQL statement
			rs = stmt.executeQuery("SELECT * from reservation ORDER BY LENGTH(reservationID), reservationID asc");//SQL select query to display the class type // e.roleID,
			
			//add the rows in the class type details database to the JTable
			Object[] row = new Object[11];
			while (rs.next()) { 
			
				reservationID = rs.getString(1);
				roomID = rs.getString(2);
				guestID = rs.getString(3);
				checkInDate = rs.getString(4);
				arrivalTime = rs.getString(5);
				checkOutDate = rs.getString(6);
				adultPax = rs.getInt(7);
				childPax = rs.getInt(8);
				reservationEmailStatus = rs.getString(9);
				reservationTotalPrice = rs.getDouble(10);
				specialRequest = rs.getString(11);
				
				
	
	
				reservation.addRecord(reservationList, new CRUD_1(reservationID,roomID,guestID, checkInDate, arrivalTime, checkOutDate, adultPax, childPax, reservationEmailStatus, reservationTotalPrice, specialRequest ));
			
				
				row[0] = reservationID;
				row[1] = roomID;
				row[2] = guestID;
				row[3] = checkInDate;
				row[4] = arrivalTime;
				row[5] = checkOutDate;
				row[6] = adultPax;
				row[7] = childPax;
				row[8] = reservationEmailStatus;
				row[9] = reservationTotalPrice;
				row[10] = specialRequest;
	
				
				model.addRow(row);
            } 
			reservation.RowFilterTest(table, model, txtSearch);
			
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

	//Refresh the table once the Insert / Update / Delete queries has been executed
	void refreshTable() {
		
		try {

			DefaultTableModel model = (DefaultTableModel) table.getModel();

			CRUD_1 reservationRefer ;
			
			Object[] row = new Object[11];
			

			for(int i = 0; i< reservation.getCount(reservationList); i++) {
			
			
				reservationRefer = reservation.getIndex(reservationList, i);
			
			
				
				row[0] = reservation.getRecord(reservationList, reservationRefer.getReservationID(), reservationRefer.getRoomID(), reservationRefer.getGuestID()).getReservationID();
				row[1] = reservation.getRecord(reservationList, reservationRefer.getReservationID(), reservationRefer.getRoomID(), reservationRefer.getGuestID()).getRoomID();
				row[2] = reservation.getRecord(reservationList, reservationRefer.getReservationID(), reservationRefer.getRoomID(), reservationRefer.getGuestID()).getGuestID();
				row[3] = reservation.getRecord(reservationList, reservationRefer.getReservationID(), reservationRefer.getRoomID(), reservationRefer.getGuestID()).getCheckInDate();
				row[4] = reservation.getRecord(reservationList, reservationRefer.getReservationID(), reservationRefer.getRoomID(), reservationRefer.getGuestID()).getArrivalTime();
				row[5] = reservation.getRecord(reservationList, reservationRefer.getReservationID(), reservationRefer.getRoomID(), reservationRefer.getGuestID()).getCheckOutDate();
				row[6] = reservation.getRecord(reservationList, reservationRefer.getReservationID(), reservationRefer.getRoomID(), reservationRefer.getGuestID()).getAdultPax();
				row[7] = reservation.getRecord(reservationList, reservationRefer.getReservationID(), reservationRefer.getRoomID(), reservationRefer.getGuestID()).getChildPax();
				row[8] = reservation.getRecord(reservationList, reservationRefer.getReservationID(), reservationRefer.getRoomID(), reservationRefer.getGuestID()).getReservationEmailStatus();		
				row[9] = reservation.getRecord(reservationList, reservationRefer.getReservationID(), reservationRefer.getRoomID(), reservationRefer.getGuestID()).getReservationTotalPrice();
				row[10] = reservation.getRecord(reservationList, reservationRefer.getReservationID(), reservationRefer.getRoomID(), reservationRefer.getGuestID()).getSpecialRequest();
				
				
				model.addRow(row);
			}
			
			
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
}
