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
import CRUD.RoomType;

public class RoomType_Display extends JPanel {

	private JTextField txtSearch;
	private JTextField txtRoomTypeID;
	private JTextField txtRoomTypeName;
	private JTable table;

	
	ArrayList<CRUD_1> roomTypeList = new ArrayList<CRUD_1>();


	RoomType roomType = new RoomType(roomTypeList);
	ConnectDatabase db = new ConnectDatabase();

	
	
	/**
	 * Create the panel.
	 */
	public RoomType_Display() {

		JPanel panel = new JPanel();
    	panel.setBackground(Color.BLACK);
   	 	panel.setLayout(null);
		setLayout(null);


		JLabel lblRoomTypeID = new JLabel("Room Type ID:");
		lblRoomTypeID.setBounds(29, 141, 86, 16);
		add(lblRoomTypeID);
		
		JLabel lblRoomTypeName = new JLabel("Room Type Name:");
		lblRoomTypeName.setBounds(29, 172, 107, 16);
		add(lblRoomTypeName);

		
		
		txtRoomTypeID = new JTextField();
		txtRoomTypeID.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char keyChar = e.getKeyChar();
			    if (Character.isLowerCase(keyChar)) {
			      e.setKeyChar(Character.toUpperCase(keyChar));
			    }
			}
		});
		txtRoomTypeID.setBounds(140, 138, 80, 22);
		add(txtRoomTypeID);
		txtRoomTypeID.setColumns(10);
		
		txtRoomTypeName = new JTextField();
		txtRoomTypeName.setColumns(10);
		txtRoomTypeName.setBounds(140, 169, 135, 22);
		add(txtRoomTypeName);

		
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

				if(fieldCheck("Insert", txtRoomTypeID.getText(), txtRoomTypeName.getText()) == 0) {
						String query = "INSERT INTO roomType VALUES ('" +txtRoomTypeID.getText()+ "','" +txtRoomTypeName.getText()+"')";
						roomType.addRecord(roomTypeList, new CRUD_1(txtRoomTypeID.getText(), txtRoomTypeName.getText()));
						executeSQL(query, "Inserted");

				}

			}
		});
		btnAdd.setBounds(12, 431, 86, 39);
		add(btnAdd);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				if(fieldCheck("Update", txtRoomTypeID.getText(), txtRoomTypeName.getText()) == 0) {
					
					String query = "UPDATE roomType SET roomTypeName ='" +txtRoomTypeName.getText()+"'WHERE roomTypeID ='" +txtRoomTypeID.getText()+"'";
					roomType.setRecord(roomTypeList, new CRUD_1(txtRoomTypeID.getText(), txtRoomTypeName.getText()));
					executeSQL(query, "Updated");
				}
			}
		});
		btnUpdate.setBounds(110, 431, 86, 39);
		add(btnUpdate);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(fieldCheck("Delete", txtRoomTypeID.getText(), txtRoomTypeName.getText()) == 0) {
					String query = "DELETE FROM roomType WHERE roomTypeID ='"+txtRoomTypeID.getText()+"'";
					roomType.deleteRecord(roomTypeList, txtRoomTypeID.getText());
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
		
		//display the employee panel 
		displayData();
		
	}


	/*
	Validates the fields for empty and invalid inputs & prompts proper error message
	
	@param actionName: whether the user is inserting, updating / deleting the record
	@param ID, ..., role : All the fields that undergo the validation
	@return errCounter: if the errCounter returns 0, then there are no error encountered 
	*/
	int fieldCheck(String actionName, String roomTypeId, String roomTypeName) {
		int errCounter = 0;
		
		if(roomTypeId.equals("") || roomTypeName.equals("")) {
			JOptionPane.showMessageDialog(null, "Please fill in the fields!");
			errCounter ++;
		}else {
			if(actionName.equals("Insert")) {
				if(roomType.isIdExist(roomTypeList, roomTypeId)){
					JOptionPane.showMessageDialog(null, "Failed to add new room type record as the same ID already exists!");
					errCounter ++;
				}
			}
						
			if(actionName.equals("Update")) {
				if(roomType.isRecordExist(roomTypeList, new CRUD_1(roomTypeId, roomTypeName))){
					JOptionPane.showMessageDialog(null, " Failed to update room type record as the exact record already exists!");
					errCounter ++;
				}else if(!roomType.isIdExist(roomTypeList, roomTypeId)) {
					JOptionPane.showMessageDialog(null, "Failed to update room type record as no such ID is found!");
					errCounter ++;
				}
			}

			if(actionName.equals("Delete")) {
				if(!roomType.isIdExist(roomTypeList, roomTypeId)) {
					JOptionPane.showMessageDialog(null, "Failed to update room type record as no such ID is found!");
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
		Object[] columns = {"Room Type ID", "Room Type Name"};
		
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
		
		for(int i = 0; i < 1; i++) {
				table.getColumnModel().getColumn(i).setCellRenderer(Renderer);
		}
		
		//allow the selected row values to be displayed in the JTextField
		table.addMouseListener(new MouseAdapter() {
			
			public void mouseClicked(MouseEvent e) {
				int i = table.getSelectedRow();
				
				if (table.getRowSorter()!=null) {
				    i = table.getRowSorter().convertRowIndexToModel(i);
				}
				
				txtRoomTypeID.setText(model.getValueAt(i, 0).toString());
				txtRoomTypeName.setText(model.getValueAt(i, 1).toString());
			}
			
		});
		
		
		
		try {
			Class.forName(db.getClassName());//the database driver 
			
			String roomTypeId, roomTypeName;
			
			
			conn = DriverManager.getConnection(db.getURL(), db.getUserName(), db.getPassword()); //connect to the database
			conn.setAutoCommit(false);//do not auto commit the SQL statements
			stmt = conn.createStatement();//prepare the create a SQL statement
			rs = stmt.executeQuery("SELECT roomTypeID, roomTypeName from roomType");//SQL select query to display the class type // e.roleID,
			
			//add the rows in the class type details database to the JTable
			Object[] row = new Object[2];
			while (rs.next()) { 
			
				roomTypeId = rs.getString(1);
				roomTypeName = rs.getString(2);

				roomType.addRecord(roomTypeList, new CRUD_1(roomTypeId, roomTypeName));

				row[0] = roomTypeId;
				row[1] = roomTypeName;
				
				model.addRow(row);
            } 
			roomType.RowFilterTest(table, model, txtSearch);
			
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

			CRUD_1 roomTypeRefer ;
			
			Object[] row = new Object[2];
			

			for(int i = 0; i< roomType.getCount(roomTypeList); i++) {

				roomTypeRefer = roomType.getIndex(roomTypeList, i);

				row[0] = roomType.getRecord(roomTypeList, roomTypeRefer.getRoomTypeID()).getRoomTypeID();
				row[1] = roomType.getRecord(roomTypeList, roomTypeRefer.getRoomTypeID()).getRoomTypeName();

				model.addRow(row);
			}

		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}

}
