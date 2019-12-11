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

import CRUD.Amenities_Usage_Log;
import CRUD.CRUD_1;
import CRUD.ConnectDatabase;




public class Amenities_Usage_Log_Display extends JPanel {

	private JTextField txtSearch;
	private JTextField txtUsageStartTime;
	private JTable table;
	private JTextField txtAmenityID;
	private JTextField txtRoomID;
	private JTextField txtGuestID;
	private JTextField txtAmenityTotalPrice;
	private JTextField txtEmpCode;
	private JTextField textField;
	private JTextField txtUsageEndTime;
	private JTextField txtRemarks;
	


	
	ArrayList<CRUD_1> amenityUsageLogList = new ArrayList<CRUD_1>();


	Amenities_Usage_Log amenityUsageLog = new Amenities_Usage_Log(amenityUsageLogList);
	ConnectDatabase db = new ConnectDatabase();
	
	CRUD_1 amenityUsageLogs ;

	
	
	/**
	 * Create the panel.
	 */
	public Amenities_Usage_Log_Display() {

		JPanel panel = new JPanel();
    	panel.setBackground(Color.BLACK);
   	 	panel.setLayout(null);
		setLayout(null);
		
		
		
		JLabel lblRoomID = new JLabel("Room ID:");
		lblRoomID.setBounds(36, 145, 76, 16);
		add(lblRoomID);
		
		JLabel lblUsageStartTime = new JLabel("Usage Start Time:");
		lblUsageStartTime.setBounds(36, 211, 121, 16);
		add(lblUsageStartTime);
		
		JLabel lblGuestID = new JLabel("Guest ID:");
		lblGuestID.setBounds(36, 176, 76, 16);
		add(lblGuestID);
		
		JLabel lblAmenityTotalPrice = new JLabel("Amenity Total Price:");
		lblAmenityTotalPrice.setBounds(36, 284, 144, 16);
		add(lblAmenityTotalPrice);
		
		JLabel lblAmenityID = new JLabel("Amenity ID:");
		lblAmenityID.setBounds(36, 114, 76, 16);
		add(lblAmenityID);
		
		txtUsageStartTime = new JTextField();
		txtUsageStartTime.setColumns(10);
		txtUsageStartTime.setBounds(205, 211, 121, 22);
		add(txtUsageStartTime);
		
		txtAmenityTotalPrice = new JTextField();
		txtAmenityTotalPrice.setText("0.00");
		txtAmenityTotalPrice.setColumns(10);
		txtAmenityTotalPrice.setBounds(205, 281, 121, 22);
		add(txtAmenityTotalPrice);


		
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

				if(fieldCheck("Insert", txtAmenityID.getText(), txtRoomID.getText(), txtGuestID.getText(),  txtUsageStartTime.getText(),txtUsageEndTime.getText(), txtAmenityTotalPrice.getText(),txtRemarks.getText()) == 0) {
					
					String query = "INSERT INTO amenities_usage_log VALUES ('" +txtAmenityID.getText()+ "','" +txtRoomID.getText()+ "','" +txtGuestID.getText()+ "','" +txtUsageStartTime.getText()+ "','" + txtUsageEndTime.getText()+ "','" +txtAmenityTotalPrice.getText()+ "','" + txtRemarks.getText() + "')";
						
					amenityUsageLog.addRecord(amenityUsageLogList, new CRUD_1(txtAmenityID.getText(),txtRoomID.getText(),txtGuestID.getText(),txtUsageStartTime.getText(),txtUsageEndTime.getText(),Double.parseDouble(txtAmenityTotalPrice.getText()),txtRemarks.getText()));
					
						executeSQL(query, "Inserted");

				}

			}
		});
		btnAdd.setBounds(12, 427, 86, 39);
		add(btnAdd);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				if(fieldCheck("Update", txtAmenityID.getText(), txtRoomID.getText(), txtGuestID.getText(),  txtUsageStartTime.getText(),txtUsageEndTime.getText(), txtAmenityTotalPrice.getText(),txtRemarks.getText()) == 0) {
						
					String query = "UPDATE amenities_usage_log SET usageStartTime = '" +txtUsageStartTime.getText()+ "', usageEndTime ='" +txtUsageEndTime.getText()+ "', amenityTotalPrice ='" +txtAmenityTotalPrice.getText()+ "', remarks ='" +txtRemarks.getText()+ "' WHERE amenityID ='"+txtAmenityID.getText()+ "' and roomID = '"+txtRoomID.getText()+ "' and guestID = '"+txtGuestID.getText()+"'";
					
					
					amenityUsageLog.setRecord(amenityUsageLogList, new CRUD_1(txtAmenityID.getText(),txtRoomID.getText(),txtGuestID.getText(),txtUsageStartTime.getText(),txtUsageEndTime.getText(),Double.parseDouble(txtAmenityTotalPrice.getText()),txtRemarks.getText()));
					
					executeSQL(query, "Updated");
				}
			}
		});
		btnUpdate.setBounds(110, 427, 86, 39);
		add(btnUpdate);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(fieldCheck("Delete", txtAmenityID.getText(), txtRoomID.getText(), txtGuestID.getText(),  txtUsageStartTime.getText(),txtUsageEndTime.getText(), txtAmenityTotalPrice.getText(),txtRemarks.getText()) == 0) {
					
					String query = "DELETE FROM amenities_usage_log WHERE amenityID ='"+txtAmenityID.getText()+ "' and roomID = '"+ txtRoomID.getText() + "' and guestID = '"+txtGuestID.getText()+"'";
					
					amenityUsageLog.deleteRecord(amenityUsageLogList, txtAmenityID.getText(),txtRoomID.getText(),txtGuestID.getText());
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
		
		JLabel lblUsageEndTime = new JLabel("Usage End Time:");
		lblUsageEndTime.setBounds(36, 246, 142, 16);
		add(lblUsageEndTime);
		
		txtUsageEndTime = new JTextField();
		txtUsageEndTime.setColumns(10);
		txtUsageEndTime.setBounds(205, 246, 121, 22);
		add(txtUsageEndTime);
		
		JLabel lblRemarks = new JLabel("Remarks:");
		lblRemarks.setBounds(36, 314, 160, 16);
		add(lblRemarks);
		
		txtRemarks = new JTextField();
		txtRemarks.setColumns(10);
		txtRemarks.setBounds(205, 314, 121, 22);
		add(txtRemarks);
		
		txtAmenityID = new JTextField();
		txtAmenityID.setColumns(10);
		txtAmenityID.setBounds(205, 111, 121, 22);
		add(txtAmenityID);
		
		txtRoomID = new JTextField();
		txtRoomID.setColumns(10);
		txtRoomID.setBounds(205, 142, 121, 22);
		add(txtRoomID);
		
		txtGuestID = new JTextField();
		txtGuestID.setColumns(10);
		txtGuestID.setBounds(205, 173, 121, 22);
		add(txtGuestID);
		
		

		
		//display the room panel 
		displayData();			
	}


	/*
	Validates the fields in the table for empty and invalid inputs & prompts proper error message
	
	@param actionName: whether the user is inserting, updating / deleting the record
	@param ID, ..., role : All the fields that undergo the validation
	@return errCounter: if the errCounter returns 0, then there are no error encountered 
	*/
	int fieldCheck(String actionName, String amenityID, String roomID, String guestID, String usageStartTime, String usageEndTime, String amenityTotalPrice, String remarks) {
		int errCounter = 0;
		
		if(amenityID.equals("") || roomID.equals("") || guestID.equals("") || usageStartTime.equals("")  || usageEndTime.equals("") || amenityTotalPrice.equals("") || remarks.equals("") ) {
			JOptionPane.showMessageDialog(null, "Please fill in all the fields!");
			errCounter ++;
		}else {
			if(!amenityUsageLog.validateDouble(amenityTotalPrice)) {
				JOptionPane.showMessageDialog(null, "Prices can only have digits & decimals! ");
				errCounter ++;
			}else{
				if(actionName.equals("Insert")) {
					if(amenityUsageLog.isIdExist(amenityUsageLogList, amenityID, roomID, guestID)){
						JOptionPane.showMessageDialog(null, "Failed to add new amenities usage log record as the same ID already exists!");
						errCounter ++;
					}
				}
							
				if(actionName.equals("Update")) {
					if(amenityUsageLog.isRecordExist(amenityUsageLogList, new CRUD_1(amenityID, roomID, guestID, usageStartTime, usageEndTime, Double.parseDouble(amenityTotalPrice), remarks))){
						JOptionPane.showMessageDialog(null, " Failed to update amenities usage log record as the exact record already exists!");
						errCounter ++;
					}else if(!amenityUsageLog.isIdExist(amenityUsageLogList, amenityID, roomID, guestID)){
						JOptionPane.showMessageDialog(null, "Failed to update amenities usage log record as no such ID is found!");
						errCounter ++;
					}
				}
			}

			if(actionName.equals("Delete")) {
				if(!amenityUsageLog.isIdExist(amenityUsageLogList, amenityID, roomID, guestID)){
					JOptionPane.showMessageDialog(null, "Failed to update amenities usage log record as no such ID is found!");
					errCounter ++;
				}
			}
		}

		return errCounter;
	}


	void executeSQLUpdatePrice() {
		String query = "UPDATE amenities_usage_log aul, amenities a SET aul.amenityTotalPrice = a.amenityPrice * (time_to_sec(timediff (usageEndTime, usageStartTime))/3600);";

		
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

	               
	               System.out.println("Can Update");
	           }else{
	               System.out.println("Cannot Update");
	           }
	       }catch(Exception ex){
	           ex.printStackTrace();
	       }
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
		Object[] columns = {"Amenity ID", "Room ID", "Guest ID", "Usage Start Time", "Usage End Time", "Amenity Total Price", "Remarks"  };
		
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
		
		for(int i = 0; i < 7; i++) {
				table.getColumnModel().getColumn(i).setCellRenderer(Renderer);
		}
		
		//allow the selected row values to be displayed in the JTextField
		table.addMouseListener(new MouseAdapter() {
			
			public void mouseClicked(MouseEvent e) {
				int i = table.getSelectedRow();
				
				if (table.getRowSorter()!=null) {
				    i = table.getRowSorter().convertRowIndexToModel(i);
				}
				
				txtAmenityID.setText(model.getValueAt(i, 0).toString());
				txtRoomID.setText(model.getValueAt(i, 1).toString());
				txtGuestID.setText(model.getValueAt(i, 2).toString());
				txtUsageStartTime.setText(model.getValueAt(i, 3).toString());
				txtUsageEndTime.setText(model.getValueAt(i, 4).toString());
				txtAmenityTotalPrice.setText(model.getValueAt(i, 5).toString());
				txtRemarks.setText(model.getValueAt(i, 6).toString());
				
			}
			
		});
		
		
		
		try {
			Class.forName(db.getClassName());//the database driver 
			

			
			
			String amenityID, roomID, guestID, usageStartTime, usageEndTime, remarks;
			double amenityTotalPrice;
			
			conn = DriverManager.getConnection(db.getURL(), db.getUserName(), db.getPassword()); //connect to the database
			conn.setAutoCommit(false);//do not auto commit the SQL statements
			stmt = conn.createStatement();//prepare the create a SQL statement
			rs = stmt.executeQuery("SELECT * from amenities_usage_log ORDER BY LENGTH(amenityID), amenityID asc");//SQL select query to display the class type // e.roleID,
			
			//add the rows in the class type details database to the JTable
			Object[] row = new Object[7];
			while (rs.next()) { 
			
				amenityID = rs.getString(1);
				roomID = rs.getString(2);
				guestID = rs.getString(3);
				usageStartTime = rs.getString(4);
				usageEndTime = rs.getString(5);
				amenityTotalPrice = rs.getDouble(6);
				remarks = rs.getString(7);
				
	
	
				amenityUsageLog.addRecord(amenityUsageLogList, new CRUD_1(amenityID, roomID, guestID, usageStartTime, usageEndTime, amenityTotalPrice, remarks));
			
				
				row[0] = amenityID;
				row[1] = roomID;
				row[2] = guestID;
				row[3] = usageStartTime;
				row[4] = usageEndTime;
				row[5] = amenityTotalPrice;
				row[6] = remarks;
	
				
				model.addRow(row);
            } 
			amenityUsageLog.RowFilterTest(table, model, txtSearch);
			
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
		
		executeSQLUpdatePrice();
		
		try {

			DefaultTableModel model = (DefaultTableModel) table.getModel();

			CRUD_1 amenityUsageLogRefer ;
			
			Object[] row = new Object[7];
			

			for(int i = 0; i< amenityUsageLog.getCount(amenityUsageLogList); i++) {
			
			
				amenityUsageLogRefer = amenityUsageLog.getIndex(amenityUsageLogList, i);
			
			
				
				row[0] = amenityUsageLog.getRecord(amenityUsageLogList, amenityUsageLogRefer.getAmenityID(), amenityUsageLogRefer.getRoomID(), amenityUsageLogRefer.getGuestID()).getAmenityID();
				row[1] = amenityUsageLog.getRecord(amenityUsageLogList, amenityUsageLogRefer.getAmenityID(), amenityUsageLogRefer.getRoomID(), amenityUsageLogRefer.getGuestID()).getRoomID();
				row[2] = amenityUsageLog.getRecord(amenityUsageLogList, amenityUsageLogRefer.getAmenityID(), amenityUsageLogRefer.getRoomID(), amenityUsageLogRefer.getGuestID()).getGuestID();
				row[3] = amenityUsageLog.getRecord(amenityUsageLogList, amenityUsageLogRefer.getAmenityID(), amenityUsageLogRefer.getRoomID(), amenityUsageLogRefer.getGuestID()).getUsageStartTime();
				row[4] = amenityUsageLog.getRecord(amenityUsageLogList, amenityUsageLogRefer.getAmenityID(), amenityUsageLogRefer.getRoomID(), amenityUsageLogRefer.getGuestID()).getUsageEndTime();
				row[5] = amenityUsageLog.getRecord(amenityUsageLogList, amenityUsageLogRefer.getAmenityID(), amenityUsageLogRefer.getRoomID(), amenityUsageLogRefer.getGuestID()).getAmenityTotalPrice();
				row[6] = amenityUsageLog.getRecord(amenityUsageLogList, amenityUsageLogRefer.getAmenityID(), amenityUsageLogRefer.getRoomID(), amenityUsageLogRefer.getGuestID()).getRemarks();
	
	
				model.addRow(row);
			}
			
			
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
}
