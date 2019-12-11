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


import CRUD.CRUD_2;
import CRUD.ConnectDatabase;
import CRUD.Housekeeping;

public class Housekeeping_Display extends JPanel {

	private JTextField txtSearch;
	private JTextField txtHousekeepingID;
	private JTextField txtHousekeepingName;
	private JTable table;

	
	ArrayList<CRUD_2> housekeepingList = new ArrayList<CRUD_2>();


	Housekeeping housekeeping = new Housekeeping(housekeepingList);
	ConnectDatabase db = new ConnectDatabase();
	private JTextField txtRemarks;
	
	CRUD_2 housekeepings;

	
	
	/**
	 * Create the panel.
	 */
	public Housekeeping_Display() {

		JPanel panel = new JPanel();
    	panel.setBackground(Color.BLACK);
   	 	panel.setLayout(null);
		setLayout(null);


		JLabel lblRoomID = new JLabel("Housekeeping ID:");
		lblRoomID.setBounds(29, 141, 107, 16);
		add(lblRoomID);
		
		JLabel lblRoomStatus = new JLabel("Housekeeping Name:");
		lblRoomStatus.setBounds(29, 172, 121, 16);
		add(lblRoomStatus);

		
		
		txtHousekeepingID = new JTextField();
		txtHousekeepingID.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char keyChar = e.getKeyChar();
			    if (Character.isLowerCase(keyChar)) {
			      e.setKeyChar(Character.toUpperCase(keyChar));
			    }
			}
		});
		txtHousekeepingID.setBounds(164, 138, 80, 22);
		add(txtHousekeepingID);
		txtHousekeepingID.setColumns(10);
		
		txtHousekeepingName = new JTextField();
		txtHousekeepingName.setColumns(10);
		txtHousekeepingName.setBounds(164, 169, 135, 22);
		add(txtHousekeepingName);

		
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

				if(fieldCheck("Insert", txtHousekeepingID.getText(), txtHousekeepingName.getText() , txtRemarks.getText()) == 0) {
						String query = "INSERT INTO housekeeping VALUES ('" +txtHousekeepingID.getText()+ "','" +txtHousekeepingName.getText()+ "','" +txtRemarks.getText()+"')";
						housekeeping.addRecord(housekeepingList, new CRUD_2(txtHousekeepingID.getText(), txtHousekeepingName.getText(), txtRemarks.getText()));
						executeSQL(query, "Inserted");

				}

			}
		});
		btnAdd.setBounds(12, 431, 86, 39);
		add(btnAdd);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				if(fieldCheck("Update", txtHousekeepingID.getText(), txtHousekeepingName.getText() , txtRemarks.getText()) == 0) {
					
					String query = "UPDATE housekeeping SET housekeepingName ='" +txtHousekeepingName.getText()+ "', remarks ='" +txtRemarks.getText()+"'WHERE housekeepingID ='" +txtHousekeepingID.getText()+"'";
					housekeeping.setRecord(housekeepingList, new CRUD_2(txtHousekeepingID.getText(), txtHousekeepingName.getText(), txtRemarks.getText()));
					executeSQL(query, "Updated");
				}
			}
		});
		btnUpdate.setBounds(110, 431, 86, 39);
		add(btnUpdate);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(fieldCheck("Delete", txtHousekeepingID.getText(), txtHousekeepingName.getText() , txtRemarks.getText()) == 0) {
					String query = "DELETE FROM housekeeping WHERE housekeepingID ='"+txtHousekeepingID.getText()+"'";
					housekeeping.deleteRecord(housekeepingList, txtHousekeepingID.getText());
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
		txtRemarks.setBounds(164, 199, 135, 22);
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
	int fieldCheck(String actionName, String housekeepingId, String housekeepingstatus, String remarks) {
		int errCounter = 0;
		
		if(housekeepingId.equals("") || housekeepingstatus.equals("")|| remarks.equals("")) {
			JOptionPane.showMessageDialog(null, "Please fill in the fields!");
			errCounter ++;
		}else {
			if(actionName.equals("Insert")) {
				if(housekeeping.isIdExist(housekeepingList, housekeepingId)){
					JOptionPane.showMessageDialog(null, "Failed to add new housekeeping record as the same ID already exists!");
					errCounter ++;
				}
			}
						
			if(actionName.equals("Update")) {
				if(housekeeping.isRecordExist(housekeepingList, new CRUD_2(housekeepingId, housekeepingstatus, remarks))){
					JOptionPane.showMessageDialog(null, " Failed to update housekeeping record as the exact record already exists!");
					errCounter ++;
				}else if(!housekeeping.isIdExist(housekeepingList, housekeepingId)) {
					JOptionPane.showMessageDialog(null, "Failed to update housekeeping record as no such ID is found!");
					errCounter ++;
				}
			}

			if(actionName.equals("Delete")) {
				if(!housekeeping.isIdExist(housekeepingList, housekeepingId)) {
					JOptionPane.showMessageDialog(null, "Failed to update housekeeping record as no such ID is found!");
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
		Object[] columns = {"Housekeeping ID", "Housekeeping Name", "Remarks"};
		
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
				
				txtHousekeepingID.setText(model.getValueAt(i, 0).toString());
				txtHousekeepingName.setText(model.getValueAt(i, 1).toString());
				txtRemarks.setText(model.getValueAt(i, 2).toString());
			}
			
		});
		
		
		
		try {
			Class.forName(db.getClassName());//the database driver 
			
			String housekeepingID, housekeepingstatus, remarks;
			
			
			conn = DriverManager.getConnection(db.getURL(), db.getUserName(), db.getPassword()); //connect to the database
			conn.setAutoCommit(false);//do not auto commit the SQL statements
			stmt = conn.createStatement();//prepare the create a SQL statement
			rs = stmt.executeQuery("SELECT * from housekeeping ORDER BY LENGTH(housekeepingID), housekeepingID asc");//SQL select query to display the class type // e.roleID,
			
			//add the rows in the class type details database to the JTable
			Object[] row = new Object[3];
			while (rs.next()) { 
			
				housekeepingID = rs.getString(1);
				housekeepingstatus = rs.getString(2);
				remarks = rs.getString(3);

				housekeeping.addRecord(housekeepingList, new CRUD_2(housekeepingID, housekeepingstatus, remarks));

				row[0] = housekeepingID;
				row[1] = housekeepingstatus;
				row[2] = remarks;
				
				model.addRow(row);
            } 
			housekeeping.RowFilterTest(table, model, txtSearch);
			
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

			CRUD_2 housekeepingRefer ;
			
			Object[] row = new Object[3];
			

			for(int i = 0; i< housekeeping.getCount(housekeepingList); i++) {

				housekeepingRefer = housekeeping.getIndex(housekeepingList, i);

				row[0] = housekeeping.getRecord(housekeepingList, housekeepingRefer.getHousekeepingID()).getHousekeepingID();
				row[1] = housekeeping.getRecord(housekeepingList, housekeepingRefer.getHousekeepingID()).getHousekeepingName();
				row[2] = housekeeping.getRecord(housekeepingList, housekeepingRefer.getHousekeepingID()).getRemarks();

				model.addRow(row);
			}

		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}

}
