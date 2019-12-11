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

import CRUD.CRUD_2;
import CRUD.ConnectDatabase;
import CRUD.Services_Usage_Log;




public class Services_Usage_Log_Display extends JPanel {

	private JTextField txtSearch;
	private JTextField txtUsageStartTime;
	private JTable table;
	private JTextField txtServiceID;
	private JTextField txtRoomID;
	private JTextField txtGuestID;
	private JTextField txtServiceTotalPrice;
	private JTextField txtEmpCode;
	private JTextField textField;
	private JTextField txtUsageEndTime;
	private JTextField txtRemarks;
	


	
	ArrayList<CRUD_2> serviceUsageLogList = new ArrayList<CRUD_2>();


	Services_Usage_Log serviceUsageLog = new Services_Usage_Log(serviceUsageLogList);
	ConnectDatabase db = new ConnectDatabase();
	
	CRUD_2 serviceUsageLogs ;

	
	
	/**
	 * Create the panel.
	 */
	public Services_Usage_Log_Display() {

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
		
		JLabel lblServiceTotalPrice = new JLabel("Service Total Price:");
		lblServiceTotalPrice.setBounds(36, 284, 144, 16);
		add(lblServiceTotalPrice);
		
		JLabel lblServiceID = new JLabel("Service ID:");
		lblServiceID.setBounds(36, 114, 76, 16);
		add(lblServiceID);
		
		txtUsageStartTime = new JTextField();
		txtUsageStartTime.setColumns(10);
		txtUsageStartTime.setBounds(205, 211, 121, 22);
		add(txtUsageStartTime);
		
		txtServiceTotalPrice = new JTextField();
		txtServiceTotalPrice.setText("0.00");
		txtServiceTotalPrice.setColumns(10);
		txtServiceTotalPrice.setBounds(205, 281, 121, 22);
		add(txtServiceTotalPrice);


		
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

				if(fieldCheck("Insert", txtServiceID.getText(), txtRoomID.getText(), txtGuestID.getText(),  txtUsageStartTime.getText(),txtUsageEndTime.getText(), txtServiceTotalPrice.getText(),txtRemarks.getText()) == 0) {
					
					String query = "INSERT INTO services_usage_log VALUES ('" +txtServiceID.getText()+ "','" +txtRoomID.getText()+ "','" +txtGuestID.getText()+ "','" +txtUsageStartTime.getText()+ "','" + txtUsageEndTime.getText()+ "','" +txtServiceTotalPrice.getText()+ "','" + txtRemarks.getText() + "')";
						
					serviceUsageLog.addRecord(serviceUsageLogList, new CRUD_2(txtServiceID.getText(),txtRoomID.getText(),txtGuestID.getText(),txtUsageStartTime.getText(),txtUsageEndTime.getText(),Double.parseDouble(txtServiceTotalPrice.getText()),txtRemarks.getText()));
					
						executeSQL(query, "Inserted");

				}

			}
		});
		btnAdd.setBounds(12, 427, 86, 39);
		add(btnAdd);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				if(fieldCheck("Update", txtServiceID.getText(), txtRoomID.getText(), txtGuestID.getText(),  txtUsageStartTime.getText(),txtUsageEndTime.getText(), txtServiceTotalPrice.getText(),txtRemarks.getText()) == 0) {
						
					String query = "UPDATE services_usage_log SET usageStartTime = '" +txtUsageStartTime.getText()+ "', usageEndTime ='" +txtUsageEndTime.getText()+ "', servicesTotalPrice ='" +txtServiceTotalPrice.getText()+ "', remarks ='" +txtRemarks.getText()+ "' WHERE serviceID ='"+txtServiceID.getText()+ "' and roomID = '"+txtRoomID.getText()+ "' and guestID = '"+txtGuestID.getText()+"'";
					
					
					serviceUsageLog.setRecord(serviceUsageLogList, new CRUD_2(txtServiceID.getText(),txtRoomID.getText(),txtGuestID.getText(),txtUsageStartTime.getText(),txtUsageEndTime.getText(),Double.parseDouble(txtServiceTotalPrice.getText()),txtRemarks.getText()));
					
					executeSQL(query, "Updated");
				}
			}
		});
		btnUpdate.setBounds(110, 427, 86, 39);
		add(btnUpdate);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(fieldCheck("Delete", txtServiceID.getText(), txtRoomID.getText(), txtGuestID.getText(),  txtUsageStartTime.getText(),txtUsageEndTime.getText(), txtServiceTotalPrice.getText(),txtRemarks.getText()) == 0) {
					
					String query = "DELETE FROM services_usage_log WHERE serviceID ='"+txtServiceID.getText()+ "' and roomID = '"+ txtRoomID.getText() + "' and guestID = '"+txtGuestID.getText()+"'";
					
					serviceUsageLog.deleteRecord(serviceUsageLogList, txtServiceID.getText(),txtRoomID.getText(),txtGuestID.getText());
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
		
		txtServiceID = new JTextField();
		txtServiceID.setColumns(10);
		txtServiceID.setBounds(205, 111, 121, 22);
		add(txtServiceID);
		
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
	int fieldCheck(String actionName, String serviceID, String roomID, String guestID, String usageStartTime, String usageEndTime, String serviceTotalPrice, String remarks) {
		int errCounter = 0;
		
		if(serviceID.equals("") || roomID.equals("") || guestID.equals("") || usageStartTime.equals("")  || usageEndTime.equals("") || serviceTotalPrice.equals("") || remarks.equals("") ) {
			JOptionPane.showMessageDialog(null, "Please fill in all the fields!");
			errCounter ++;
		}else {
			if(!serviceUsageLog.validateDouble(serviceTotalPrice)) {
				JOptionPane.showMessageDialog(null, "Prices can only have digits & decimals! ");
				errCounter ++;
			}else{
				if(actionName.equals("Insert")) {
					if(serviceUsageLog.isIdExist(serviceUsageLogList, serviceID, roomID, guestID)){
						JOptionPane.showMessageDialog(null, "Failed to add new services usage log record as the same ID already exists!");
						errCounter ++;
					}
				}
							
				if(actionName.equals("Update")) {
					if(serviceUsageLog.isRecordExist(serviceUsageLogList, new CRUD_2(serviceID, roomID, guestID, usageStartTime, usageEndTime, Double.parseDouble(serviceTotalPrice), remarks))){
						JOptionPane.showMessageDialog(null, " Failed to update services usage log record as the exact record already exists!");
						errCounter ++;
					}else if(!serviceUsageLog.isIdExist(serviceUsageLogList, serviceID, roomID, guestID)){
						JOptionPane.showMessageDialog(null, "Failed to update services usage log record as no such ID is found!");
						errCounter ++;
					}
				}
			}

			if(actionName.equals("Delete")) {
				if(!serviceUsageLog.isIdExist(serviceUsageLogList, serviceID, roomID, guestID)){
					JOptionPane.showMessageDialog(null, "Failed to update services usage log record as no such ID is found!");
					errCounter ++;
				}
			}
		}

		return errCounter;
	}



	void executeSQLUpdatePrice() {
		String query = "UPDATE services_usage_log sul, services s SET sul.servicesTotalPrice = s.servicePrice * (time_to_sec(timediff (sul.usageEndTime, sul.usageStartTime))/3600);";

		
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
		Object[] columns = {"Service ID", "Room ID", "Guest ID", "Usage Start Time", "Usage End Time", "Service Total Price", "Remarks"  };
		
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
				
				txtServiceID.setText(model.getValueAt(i, 0).toString());
				txtRoomID.setText(model.getValueAt(i, 1).toString());
				txtGuestID.setText(model.getValueAt(i, 2).toString());
				txtUsageStartTime.setText(model.getValueAt(i, 3).toString());
				txtUsageEndTime.setText(model.getValueAt(i, 4).toString());
				txtServiceTotalPrice.setText(model.getValueAt(i, 5).toString());
				txtRemarks.setText(model.getValueAt(i, 6).toString());
				
			}
			
		});
		
		
		
		try {
			Class.forName(db.getClassName());//the database driver 
			

			
			
			String serviceID, roomID, guestID, usageStartTime, usageEndTime, remarks;
			double serviceTotalPrice;
			
			conn = DriverManager.getConnection(db.getURL(), db.getUserName(), db.getPassword()); //connect to the database
			conn.setAutoCommit(false);//do not auto commit the SQL statements
			stmt = conn.createStatement();//prepare the create a SQL statement
			rs = stmt.executeQuery("SELECT * from services_usage_log ORDER BY LENGTH(serviceID), serviceID asc");//SQL select query to display the class type // e.roleID,
			
			//add the rows in the class type details database to the JTable
			Object[] row = new Object[7];
			while (rs.next()) { 
			
				serviceID = rs.getString(1);
				roomID = rs.getString(2);
				guestID = rs.getString(3);
				usageStartTime = rs.getString(4);
				usageEndTime = rs.getString(5);
				serviceTotalPrice = rs.getDouble(6);
				remarks = rs.getString(7);
				
	
	
				serviceUsageLog.addRecord(serviceUsageLogList, new CRUD_2(serviceID, roomID, guestID, usageStartTime, usageEndTime, serviceTotalPrice, remarks));
			
				
				row[0] = serviceID;
				row[1] = roomID;
				row[2] = guestID;
				row[3] = usageStartTime;
				row[4] = usageEndTime;
				row[5] = serviceTotalPrice;
				row[6] = remarks;
	
				
				model.addRow(row);
            } 
			serviceUsageLog.RowFilterTest(table, model, txtSearch);
			
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

			CRUD_2 serviceUsageLogRefer ;
			
			Object[] row = new Object[7];
			

			for(int i = 0; i< serviceUsageLog.getCount(serviceUsageLogList); i++) {
			
			
				serviceUsageLogRefer = serviceUsageLog.getIndex(serviceUsageLogList, i);
			
			
				
				row[0] = serviceUsageLog.getRecord(serviceUsageLogList, serviceUsageLogRefer.getServiceID(), serviceUsageLogRefer.getRoomID(), serviceUsageLogRefer.getGuestID()).getServiceID();
				row[1] = serviceUsageLog.getRecord(serviceUsageLogList, serviceUsageLogRefer.getServiceID(), serviceUsageLogRefer.getRoomID(), serviceUsageLogRefer.getGuestID()).getRoomID();
				row[2] = serviceUsageLog.getRecord(serviceUsageLogList, serviceUsageLogRefer.getServiceID(), serviceUsageLogRefer.getRoomID(), serviceUsageLogRefer.getGuestID()).getGuestID();
				row[3] = serviceUsageLog.getRecord(serviceUsageLogList, serviceUsageLogRefer.getServiceID(), serviceUsageLogRefer.getRoomID(), serviceUsageLogRefer.getGuestID()).getUsageStartTime();
				row[4] = serviceUsageLog.getRecord(serviceUsageLogList, serviceUsageLogRefer.getServiceID(), serviceUsageLogRefer.getRoomID(), serviceUsageLogRefer.getGuestID()).getUsageEndTime();
				row[5] = serviceUsageLog.getRecord(serviceUsageLogList, serviceUsageLogRefer.getServiceID(), serviceUsageLogRefer.getRoomID(), serviceUsageLogRefer.getGuestID()).getServicesTotalPrice();
				row[6] = serviceUsageLog.getRecord(serviceUsageLogList, serviceUsageLogRefer.getServiceID(), serviceUsageLogRefer.getRoomID(), serviceUsageLogRefer.getGuestID()).getRemarks();
	
	
				model.addRow(row);
			}
			
			
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
}
