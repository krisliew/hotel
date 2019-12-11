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
import CRUD.Housekeeping_Log;




public class Housekeeping_Log_Display extends JPanel {

	private JTextField txtSearch;
	private JTextField txtDateOfLog;
	private JTable table;
	private JTextField txtHousekeepingID;
	private JTextField txtRoomID;
	private JTextField txtQuantity;
	private JTextField txtEmpCode;
	private JTextField textField;
	private JTextField txtHousekeepingStatus;
	private JTextField txtRemarks;
	


	
	ArrayList<CRUD_2> housekeepingLogList = new ArrayList<CRUD_2>();


	Housekeeping_Log housekeepingLog = new Housekeeping_Log(housekeepingLogList);
	ConnectDatabase db = new ConnectDatabase();
	
	CRUD_2 housekeepingLogs ;

	
	
	/**
	 * Create the panel.
	 */
	public Housekeeping_Log_Display() {

		JPanel panel = new JPanel();
    	panel.setBackground(Color.BLACK);
   	 	panel.setLayout(null);
		setLayout(null);
		
		
		
		JLabel lblRoomID = new JLabel("Room ID:");
		lblRoomID.setBounds(36, 145, 76, 16);
		add(lblRoomID);
		
		JLabel lblDateOfLog = new JLabel("Date Of Log:");
		lblDateOfLog.setBounds(36, 211, 121, 16);
		add(lblDateOfLog);
		
		JLabel lblQuantity = new JLabel("Quantity:");
		lblQuantity.setBounds(36, 176, 76, 16);
		add(lblQuantity);
		
		JLabel lblHousekeepingID = new JLabel("Housekeeping ID:");
		lblHousekeepingID.setBounds(36, 114, 121, 16);
		add(lblHousekeepingID);
		
		txtDateOfLog = new JTextField();
		txtDateOfLog.setColumns(10);
		txtDateOfLog.setBounds(205, 211, 121, 22);
		add(txtDateOfLog);


		
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

				if(fieldCheck("Insert", txtHousekeepingID.getText(), txtRoomID.getText(), txtQuantity.getText(),  txtDateOfLog.getText(),txtHousekeepingStatus.getText(),txtRemarks.getText()) == 0) {
					
					String query = "INSERT INTO housekeeping_log VALUES ('" +txtHousekeepingID.getText()+ "','" +txtRoomID.getText()+ "','" +txtQuantity.getText()+ "','" +txtDateOfLog.getText()+ "','" + txtHousekeepingStatus.getText()+ "','" + txtRemarks.getText() + "')";
						
					housekeepingLog.addRecord(housekeepingLogList, new CRUD_2( txtHousekeepingID.getText(),txtRoomID.getText(),Integer.parseInt(txtQuantity.getText()),txtDateOfLog.getText(),txtHousekeepingStatus.getText(),txtRemarks.getText()));
					
						executeSQL(query, "Inserted");

				}

			}
		});
		btnAdd.setBounds(12, 427, 86, 39);
		add(btnAdd);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				if(fieldCheck("Update", txtHousekeepingID.getText(), txtRoomID.getText(), txtQuantity.getText(),  txtDateOfLog.getText(),txtHousekeepingStatus.getText(),txtRemarks.getText()) == 0) {
						
					String query = "UPDATE housekeeping_log SET dateOfLog = '" +txtDateOfLog.getText()+ "', quantityUsed ='" +txtQuantity.getText()+ "', housekeepingStatus ='" +txtHousekeepingStatus.getText()+ "', remarks ='" +txtRemarks.getText()+ "' WHERE housekeepingID ='"+txtHousekeepingID.getText()+ "' and roomID = '"+txtRoomID.getText()+"'";
					
					
					housekeepingLog.setRecord(housekeepingLogList, new CRUD_2( txtHousekeepingID.getText(),txtRoomID.getText(),Integer.parseInt(txtQuantity.getText()),txtDateOfLog.getText(),txtHousekeepingStatus.getText(),txtRemarks.getText()));
					
					executeSQL(query, "Updated");
				}
			}
		});
		btnUpdate.setBounds(110, 427, 86, 39);
		add(btnUpdate);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(fieldCheck("Delete", txtHousekeepingID.getText(), txtRoomID.getText(), txtQuantity.getText(),  txtDateOfLog.getText(),txtHousekeepingStatus.getText(),txtRemarks.getText()) == 0) {
					
					String query = "DELETE FROM housekeeping_log WHERE housekeepingID ='"+txtHousekeepingID.getText()+ "' and roomID = '"+ txtRoomID.getText() +"'";
					
					housekeepingLog.deleteRecord(housekeepingLogList, txtHousekeepingID.getText(),txtRoomID.getText());
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
		
		JLabel lblHousekeepingStatus = new JLabel("Housekeeping Status:");
		lblHousekeepingStatus.setBounds(36, 246, 142, 16);
		add(lblHousekeepingStatus);
		
		txtHousekeepingStatus = new JTextField();
		txtHousekeepingStatus.setColumns(10);
		txtHousekeepingStatus.setBounds(205, 246, 121, 22);
		add(txtHousekeepingStatus);
		
		JLabel lblRemarks = new JLabel("Remarks:");
		lblRemarks.setBounds(36, 281, 160, 16);
		add(lblRemarks);
		
		txtRemarks = new JTextField();
		txtRemarks.setColumns(10);
		txtRemarks.setBounds(205, 281, 121, 22);
		add(txtRemarks);
		
		txtHousekeepingID = new JTextField();
		txtHousekeepingID.setColumns(10);
		txtHousekeepingID.setBounds(205, 111, 121, 22);
		add(txtHousekeepingID);
		
		txtRoomID = new JTextField();
		txtRoomID.setColumns(10);
		txtRoomID.setBounds(205, 142, 121, 22);
		add(txtRoomID);
		
		txtQuantity = new JTextField();
		txtQuantity.setColumns(10);
		txtQuantity.setBounds(205, 173, 121, 22);
		add(txtQuantity);
		
		

		
		//display the room panel 
		displayData();			
	}


	/*
	Validates the fields in the table for empty and invalid inputs & prompts proper error message
	
	@param actionName: whether the user is inserting, updating / deleting the record
	@param ID, ..., role : All the fields that undergo the validation
	@return errCounter: if the errCounter returns 0, then there are no error encountered 
	*/
	int fieldCheck(String actionName, String housekeepingID, String roomID, String quantityused, String dateoflog, String housekeeepingstatus, String remarks) {
		int errCounter = 0;
		
		if(housekeepingID.equals("") || roomID.equals("") || quantityused.equals("") || dateoflog.equals("")  || housekeeepingstatus.equals("") || remarks.equals("") ) {
			JOptionPane.showMessageDialog(null, "Please fill in all the fields!");
			errCounter ++;
		}else {
			if(!housekeepingLog.validateInt(quantityused)) {
				JOptionPane.showMessageDialog(null, "Quantity can only have digits! ");
				errCounter ++;
			}else{
				if(actionName.equals("Insert")) {
					if(housekeepingLog.isIdExist(housekeepingLogList, housekeepingID, roomID)){
						JOptionPane.showMessageDialog(null, "Failed to add new housekeeping log record as the same ID already exists!");
						errCounter ++;
					}
				}
							
				if(actionName.equals("Update")) {
					if(housekeepingLog.isRecordExist(housekeepingLogList, new CRUD_2(housekeepingID, roomID, Integer.parseInt(quantityused), dateoflog, housekeeepingstatus, remarks))){
						JOptionPane.showMessageDialog(null, " Failed to update housekeeping log record as the exact record already exists!");
						errCounter ++;
					}else if(!housekeepingLog.isIdExist(housekeepingLogList, housekeepingID, roomID)){
						JOptionPane.showMessageDialog(null, "Failed to update housekeeping log record as no such ID is found!");
						errCounter ++;
					}
				}
			}

			if(actionName.equals("Delete")) {
				if(!housekeepingLog.isIdExist(housekeepingLogList, housekeepingID, roomID)){
					JOptionPane.showMessageDialog(null, "Failed to update housekeeping log record as no such ID is found!");
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
		Object[] columns = {"Housekeeping ID", "Room ID", "Quantity Used", "Date Of Log", "Housekeeping Status", "Remarks"  };
		
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
		
		for(int i = 0; i < 6; i++) {
				table.getColumnModel().getColumn(i).setCellRenderer(Renderer);
		}
		
		//allow the selected row values to be displayed in the JTextField
		table.addMouseListener(new MouseAdapter() {
			
			public void mouseClicked(MouseEvent e) {
				int i = table.getSelectedRow();
				
				if (table.getRowSorter()!=null) {
				    i = table.getRowSorter().convertRowIndexToModel(i);
				}
				
				txtHousekeepingID.setText(model.getValueAt(i, 0).toString());
				txtRoomID.setText(model.getValueAt(i, 1).toString());
				txtQuantity.setText(model.getValueAt(i, 2).toString());
				txtDateOfLog.setText(model.getValueAt(i, 3).toString());
				txtHousekeepingStatus.setText(model.getValueAt(i, 4).toString());
				txtRemarks.setText(model.getValueAt(i, 5).toString());
				
			}
			
		});
		
		
		
		try {
			Class.forName(db.getClassName());//the database driver 
			

			
			
			String housekeepingID, roomID, dateoflog, housekeepingstatus, remarks;
			int quantityUsed;
			
			conn = DriverManager.getConnection(db.getURL(), db.getUserName(), db.getPassword()); //connect to the database
			conn.setAutoCommit(false);//do not auto commit the SQL statements
			stmt = conn.createStatement();//prepare the create a SQL statement
			rs = stmt.executeQuery("SELECT * from housekeeping_log ORDER BY LENGTH(housekeepingID), housekeepingID asc");//SQL select query to display the class type // e.roleID,
			
			//add the rows in the class type details database to the JTable
			Object[] row = new Object[6];
			while (rs.next()) { 
			
				housekeepingID = rs.getString(1);
				roomID = rs.getString(2);
				quantityUsed = rs.getInt(3);
				dateoflog = rs.getString(4);
				housekeepingstatus = rs.getString(5);
				remarks = rs.getString(6);
				
	
	
				housekeepingLog.addRecord(housekeepingLogList, new CRUD_2(housekeepingID, roomID, quantityUsed, dateoflog, housekeepingstatus, remarks));
			
				
				row[0] = housekeepingID;
				row[1] = roomID;
				row[2] = quantityUsed;
				row[3] = dateoflog;
				row[4] = housekeepingstatus;
				row[5] = remarks;
	
				
				model.addRow(row);
            } 
			housekeepingLog.RowFilterTest(table, model, txtSearch);
			
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

			CRUD_2 housekeepingLogRefer ;
			
			Object[] row = new Object[6];
			

			for(int i = 0; i< housekeepingLog.getCount(housekeepingLogList); i++) {
			
			
				housekeepingLogRefer = housekeepingLog.getIndex(housekeepingLogList, i);
			
			
				
				row[0] = housekeepingLog.getRecord(housekeepingLogList, housekeepingLogRefer.getHousekeepingID(), housekeepingLogRefer.getRoomID()).getHousekeepingID();
				row[1] = housekeepingLog.getRecord(housekeepingLogList, housekeepingLogRefer.getHousekeepingID(), housekeepingLogRefer.getRoomID()).getRoomID();
				row[2] = housekeepingLog.getRecord(housekeepingLogList, housekeepingLogRefer.getHousekeepingID(), housekeepingLogRefer.getRoomID()).getQuantityUsed();
				row[3] = housekeepingLog.getRecord(housekeepingLogList, housekeepingLogRefer.getHousekeepingID(), housekeepingLogRefer.getRoomID()).getDateOfLog();
				row[4] = housekeepingLog.getRecord(housekeepingLogList, housekeepingLogRefer.getHousekeepingID(), housekeepingLogRefer.getRoomID()).getHousekeepingStatus();
				row[5] = housekeepingLog.getRecord(housekeepingLogList, housekeepingLogRefer.getHousekeepingID(), housekeepingLogRefer.getRoomID()).getRemarks();
	
	
				model.addRow(row);
			}
			
			
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
}
