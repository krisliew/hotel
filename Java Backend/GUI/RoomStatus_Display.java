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
import java.util.ArrayList;

import javax.swing.JButton;
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
import CRUD.RoomStatus;

public class RoomStatus_Display extends JPanel {

	private JTextField txtSearch;
	private JTextField txtRoomID;
	private JTextField txtRoomStatus;
	private JTable table;

	
	ArrayList<CRUD_1> roomStatusList = new ArrayList<CRUD_1>();


	RoomStatus roomStatus = new RoomStatus(roomStatusList);
	ConnectDatabase db = new ConnectDatabase();
	private JTextField txtRemarks;
	
	CRUD_1 roomStatuses;

	
	
	/**
	 * Create the panel.
	 */
	public RoomStatus_Display() {

		JPanel panel = new JPanel();
    	panel.setBackground(Color.BLACK);
   	 	panel.setLayout(null);
		setLayout(null);


		JLabel lblRoomID = new JLabel("Room ID:");
		lblRoomID.setBounds(29, 141, 86, 16);
		add(lblRoomID);
		
		JLabel lblRoomStatus = new JLabel("Room Status:");
		lblRoomStatus.setBounds(29, 172, 107, 16);
		add(lblRoomStatus);

		
		
		txtRoomID = new JTextField();
		txtRoomID.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char keyChar = e.getKeyChar();
			    if (Character.isLowerCase(keyChar)) {
			      e.setKeyChar(Character.toUpperCase(keyChar));
			    }
			}
		});
		txtRoomID.setBounds(140, 138, 80, 22);
		add(txtRoomID);
		txtRoomID.setColumns(10);
		
		txtRoomStatus = new JTextField();
		txtRoomStatus.setColumns(10);
		txtRoomStatus.setBounds(140, 169, 135, 22);
		add(txtRoomStatus);

		
		JLabel lblSearch = new JLabel("Search:");
		lblSearch.setBounds(709, 26, 45, 16);
		add(lblSearch);
		
		txtSearch = new JTextField();
		txtSearch.setBounds(766, 23, 200, 22);
		add(txtSearch);
		txtSearch.setColumns(10);
		
		JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				if(fieldCheck("Insert", txtRoomID.getText(), txtRoomStatus.getText() , txtRemarks.getText()) == 0) {
						String query = "INSERT INTO roomStatus VALUES ('" +txtRoomID.getText()+ "','" +txtRoomStatus.getText()+ "','" +txtRemarks.getText()+"')";
						roomStatus.addRecord(roomStatusList, new CRUD_1(txtRoomID.getText(), txtRoomStatus.getText(), txtRemarks.getText()));
						executeSQL(query, "Inserted");

				}

			}
		});
		btnAdd.setBounds(12, 431, 86, 39);
		add(btnAdd);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				if(fieldCheck("Update", txtRoomID.getText(), txtRoomStatus.getText() , txtRemarks.getText()) == 0) {
					
					String query = "UPDATE roomStatus SET roomStatus ='" +txtRoomStatus.getText()+ "', remarks ='" +txtRemarks.getText()+"'WHERE roomID ='" +txtRoomID.getText()+"'";
					roomStatus.setRecord(roomStatusList, new CRUD_1(txtRoomID.getText(), txtRoomStatus.getText(), txtRemarks.getText()));
					executeSQL(query, "Updated");
				}
			}
		});
		btnUpdate.setBounds(110, 431, 86, 39);
		add(btnUpdate);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(fieldCheck("Delete", txtRoomID.getText(), txtRoomStatus.getText() , txtRemarks.getText()) == 0) {
					String query = "DELETE FROM roomStatus WHERE roomID ='"+txtRoomID.getText()+"'";
					roomStatus.deleteRecord(roomStatusList, txtRoomID.getText());
					executeSQL(query, "Deleted");
				}
			}
		});
		btnDelete.setBounds(213, 430, 86, 39);
		add(btnDelete);
		
		
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(311, 62, 653, 402);
		add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JLabel lblRemarks = new JLabel("Remarks:");
		lblRemarks.setBounds(29, 202, 107, 16);
		add(lblRemarks);
		
		txtRemarks = new JTextField();
		txtRemarks.setColumns(10);
		txtRemarks.setBounds(140, 199, 135, 22);
		add(txtRemarks);
		
		//display the employee panel 
		displayData();
		
	}


	/*
	Validates the fields for empty and invalid inputs & prompts proper error message
	
	@param actionName: whether the user is inserting, updating / deleting the record
	@param ID, ..., role : All the fields that undergo the validation
	@return errCounter: if the errCounter returns 0, then there are no error encountered 
	*/
	int fieldCheck(String actionName, String roomId, String roomstatus, String remarks) {
		int errCounter = 0;
		
		if(roomId.equals("") || roomstatus.equals("")|| remarks.equals("")) {
			JOptionPane.showMessageDialog(null, "Please fill in the fields!");
			errCounter ++;
		}else {
			if(actionName.equals("Insert")) {
				if(roomStatus.isIdExist(roomStatusList, roomId)){
					JOptionPane.showMessageDialog(null, "Failed to add new room status record as the same ID already exists!");
					errCounter ++;
				}
			}
						
			if(actionName.equals("Update")) {
				if(roomStatus.isRecordExist(roomStatusList, new CRUD_1(roomId, roomstatus, remarks))){
					JOptionPane.showMessageDialog(null, " Failed to update room status record as the exact record already exists!");
					errCounter ++;
				}else if(!roomStatus.isIdExist(roomStatusList, roomId)) {
					JOptionPane.showMessageDialog(null, "Failed to update room status record as no such ID is found!");
					errCounter ++;
				}
			}

			if(actionName.equals("Delete")) {
				if(!roomStatus.isIdExist(roomStatusList, roomId)) {
					JOptionPane.showMessageDialog(null, "Failed to update room status record as no such ID is found!");
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
		Object[] columns = {"Room ID", "Room Status", "Remarks"};
		
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
		
		for(int i = 0; i < 3; i++) {
				table.getColumnModel().getColumn(i).setCellRenderer(Renderer);
		}
		
		//allow the selected row values to be displayed in the JTextField
		table.addMouseListener(new MouseAdapter() {
			
			public void mouseClicked(MouseEvent e) {
				int i = table.getSelectedRow();
				
				if (table.getRowSorter()!=null) {
				    i = table.getRowSorter().convertRowIndexToModel(i);
				}
				
				txtRoomID.setText(model.getValueAt(i, 0).toString());
				txtRoomStatus.setText(model.getValueAt(i, 1).toString());
				txtRemarks.setText(model.getValueAt(i, 2).toString());
			}
			
		});
		
		
		
		try {
			Class.forName(db.getClassName());//the database driver 
			
			String roomID, roomstatus, remarks;
			
			
			conn = DriverManager.getConnection(db.getURL(), db.getUserName(), db.getPassword()); //connect to the database
			conn.setAutoCommit(false);//do not auto commit the SQL statements
			stmt = conn.createStatement();//prepare the create a SQL statement
			rs = stmt.executeQuery("SELECT * from roomStatus ORDER BY LENGTH(roomID), roomID asc");//SQL select query to display the class type // e.roleID,
			
			//add the rows in the class type details database to the JTable
			Object[] row = new Object[3];
			while (rs.next()) { 
			
				roomID = rs.getString(1);
				roomstatus = rs.getString(2);
				remarks = rs.getString(3);

				roomStatus.addRecord(roomStatusList, new CRUD_1(roomID, roomstatus, remarks));

				row[0] = roomID;
				row[1] = roomstatus;
				row[2] = remarks;
				
				model.addRow(row);
            } 
			roomStatus.RowFilterTest(table, model, txtSearch);
			
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

			CRUD_1 roomStatusRefer ;
			
			Object[] row = new Object[3];
			

			for(int i = 0; i< roomStatus.getCount(roomStatusList); i++) {

				roomStatusRefer = roomStatus.getIndex(roomStatusList, i);

				row[0] = roomStatus.getRecord(roomStatusList, roomStatusRefer.getRoomID()).getRoomID();
				row[1] = roomStatus.getRecord(roomStatusList, roomStatusRefer.getRoomID()).getRoomStatus();
				row[2] = roomStatus.getRecord(roomStatusList, roomStatusRefer.getRoomID()).getRemarks();

				model.addRow(row);
			}

		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}

}
