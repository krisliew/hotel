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
import CRUD.Guest;

import javax.swing.SwingConstants;
import javax.swing.ComboBoxModel;
import javax.swing.JTextArea;


public class Guest_Display extends JPanel {

	private JTextField txtSearch;
	private JTextField txtGuestID;
	private JTextField txtGuestName;
	private JTable table;
	private JTextField txtGuestPhoneNumber;
	private JTextField txtGuestEmail;
	JTextArea txtGuestAddress;
	JComboBox cbGuestGender;
	


	
	ArrayList<CRUD_1> guestList = new ArrayList<CRUD_1>();


	Guest guest = new Guest(guestList);
	ConnectDatabase db = new ConnectDatabase();

	CRUD_1 guests ;

	
	
	/**
	 * Create the panel.
	 */
	public Guest_Display() {

		JPanel panel = new JPanel();
    	panel.setBackground(Color.BLACK);
   	 	panel.setLayout(null);
		setLayout(null);
		
		
		
		JLabel lblGuestID = new JLabel("Guest ID:");
		lblGuestID.setBounds(36, 107, 76, 16);
		add(lblGuestID);
		
		JLabel lblGuestAddress = new JLabel("Guest Address:");
		lblGuestAddress.setBounds(36, 173, 102, 16);
		add(lblGuestAddress);
		
		JLabel lblGuestName = new JLabel("Guest Name:");
		lblGuestName.setBounds(36, 138, 76, 16);
		add(lblGuestName);

		
		
		txtGuestID = new JTextField();
		txtGuestID.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char keyChar = e.getKeyChar();
			    if (Character.isLowerCase(keyChar)) {
			      e.setKeyChar(Character.toUpperCase(keyChar));
			    }
			}
		});
		txtGuestID.setBounds(180, 106, 121, 22);
		add(txtGuestID);
		txtGuestID.setColumns(10);
		
		txtGuestName = new JTextField();
		txtGuestName.setColumns(10);
		txtGuestName.setBounds(180, 137, 121, 22);
		add(txtGuestName);


		
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

				if(fieldCheck("Insert", txtGuestID.getText(), txtGuestName.getText() , txtGuestAddress.getText()  ,txtGuestPhoneNumber.getText(),txtGuestEmail.getText(), cbGuestGender.getSelectedItem().toString()) == 0) {
					
					String query = "INSERT INTO guest VALUES ('" +txtGuestID.getText()+ "','" +txtGuestName.getText()+ "','" +txtGuestAddress.getText()+ "','" +txtGuestPhoneNumber.getText()+ "','" + txtGuestEmail.getText() + "','" + cbGuestGender.getSelectedItem().toString() +"')";
						
					guest.addRecord(guestList, new CRUD_1(txtGuestID.getText(), txtGuestName.getText(), txtGuestAddress.getText(), txtGuestPhoneNumber.getText(),txtGuestEmail.getText(), cbGuestGender.getSelectedItem().toString()));
					
						executeSQL(query, "Inserted");

				}

			}
		});
		btnAdd.setBounds(22, 381, 86, 39);
		add(btnAdd);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				if(fieldCheck("Update", txtGuestID.getText(), txtGuestName.getText() , txtGuestAddress.getText()  ,txtGuestPhoneNumber.getText(),txtGuestEmail.getText(), cbGuestGender.getSelectedItem().toString()) == 0) {
						
					String query = "UPDATE guest SET guestName = '" +txtGuestName.getText()+ "', guestAddress ='" +txtGuestAddress.getText()+ "', guestPhoneNumber ='" +txtGuestPhoneNumber.getText()+ "', guestEmail ='" +txtGuestEmail.getText()+ "', guestGender ='" +cbGuestGender.getSelectedItem().toString()+ "' WHERE guestID ='"+txtGuestID.getText()+"'";
					
					
					guest.setRecord(guestList, new CRUD_1(txtGuestID.getText(), txtGuestName.getText(), txtGuestAddress.getText(), txtGuestPhoneNumber.getText(),txtGuestEmail.getText(), cbGuestGender.getSelectedItem().toString()));
					executeSQL(query, "Updated");
				}
			}
		});
		btnUpdate.setBounds(120, 381, 86, 39);
		add(btnUpdate);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(fieldCheck("Delete", txtGuestID.getText(), txtGuestName.getText() , txtGuestAddress.getText()  ,txtGuestPhoneNumber.getText(),txtGuestEmail.getText(), cbGuestGender.getSelectedItem().toString()) == 0) {
					
					
					String query = "DELETE FROM guest WHERE guestID ='"+txtGuestID.getText()+"'";
					guest.deleteRecord(guestList, txtGuestID.getText());
					executeSQL(query, "Deleted");
				}
			}
		});
		btnDelete.setBounds(223, 381, 86, 39);
		add(btnDelete);
		
		
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(329, 62, 895, 402);
		add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JLabel lblGuestPhoneNumber = new JLabel("Guest Phone Number: ");
		lblGuestPhoneNumber.setBounds(36, 262, 142, 16);
		add(lblGuestPhoneNumber);
		
		txtGuestPhoneNumber = new JTextField();
		txtGuestPhoneNumber.setColumns(10);
		txtGuestPhoneNumber.setBounds(180, 261, 121, 22);
		add(txtGuestPhoneNumber);
		
		JLabel lblGuestEmail = new JLabel("Guest Email:");
		lblGuestEmail.setBounds(36, 298, 142, 16);
		add(lblGuestEmail);
		
		txtGuestEmail = new JTextField();
		txtGuestEmail.setColumns(10);
		txtGuestEmail.setBounds(180, 297, 121, 22);
		add(txtGuestEmail);
		
		txtGuestAddress = new JTextArea();
		txtGuestAddress.setWrapStyleWord(true);
		txtGuestAddress.setBounds(180, 170, 121, 75);
		add(txtGuestAddress);
		
		JLabel label = new JLabel("Guest Email:");
		label.setBounds(36, 333, 142, 16);
		add(label);
		
		cbGuestGender = new JComboBox(new DefaultComboBoxModel(new String[] {"Male", "Female"}));
		cbGuestGender.setBounds(180, 332, 121, 20);
		add(cbGuestGender);

		
		//display the guest panel 
		displayData();			
	}


	/*
	Validates the fields in the table for empty and invalid inputs & prompts proper error message
	
	@param actionName: whether the user is inserting, updating / deleting the record
	@param ID, ..., role : All the fields that undergo the validation
	@return errCounter: if the errCounter returns 0, then there are no error encountered 
	*/
	int fieldCheck(String actionName, String guestID, String guestName, String guestAddress, String guestPhoneNumber, String guestEmail, String guestGender) {
		int errCounter = 0;
		
		if(guestID.equals("") || guestName.equals("") || guestAddress.equals("") || guestPhoneNumber.equals("")  || guestEmail.equals("") || guestGender.equals("") ) {
			JOptionPane.showMessageDialog(null, "Please fill in all the fields!");
			errCounter ++;
		}else {
			if(!guest.validateEmail(guestEmail)) {
				JOptionPane.showMessageDialog(null, "Invalid email format! ");
				errCounter ++;
			}else if(!guest.validateContactNumber(guestPhoneNumber)) {
				JOptionPane.showMessageDialog(null, "Invalid contact number format! ");
				errCounter ++;
			}else{
				if(actionName.equals("Insert")) {
					if(guest.isIdExist(guestList, guestID)){
						JOptionPane.showMessageDialog(null, "Failed to add new guest record as the same ID already exists!");
						errCounter ++;
					}
				}
							
				if(actionName.equals("Update")) {
					if(guest.isRecordExist(guestList, new CRUD_1(guestID, guestName, guestAddress, guestPhoneNumber, guestEmail, guestGender))){
						JOptionPane.showMessageDialog(null, " Failed to update guest record as the exact record already exists!");
						errCounter ++;
					}else if(!guest.isIdExist(guestList, guestID)) {
						JOptionPane.showMessageDialog(null, "Failed to update guest record as no such ID is found!");
						errCounter ++;
					}
				}
			}

			if(actionName.equals("Delete")) {
				if(!guest.isIdExist(guestList, guestID)) {
					JOptionPane.showMessageDialog(null, "Failed to update guest record as no such ID is found!");
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
		Object[] columns = {"Guest ID", "Guest Name", "Guest Address", "Guest Phone Number", "Guest Email", "Guest Gender" };
		
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
				
				txtGuestID.setText(model.getValueAt(i, 0).toString());
				txtGuestName.setText(model.getValueAt(i, 1).toString());
				txtGuestAddress.setText(model.getValueAt(i, 2).toString());
				txtGuestPhoneNumber.setText(model.getValueAt(i, 3).toString());
				txtGuestEmail.setText(model.getValueAt(i, 4).toString());
				cbGuestGender.setSelectedItem(model.getValueAt(i, 5).toString());

				
			}
			
		});
		
		
		
		try {
			Class.forName(db.getClassName());//the database driver 
			
			String guestID, guestName, guestAddress, guestPhoneNumber, guestEmail, guestGender;
			
			
			conn = DriverManager.getConnection(db.getURL(), db.getUserName(), db.getPassword()); //connect to the database
			conn.setAutoCommit(false);//do not auto commit the SQL statements
			stmt = conn.createStatement();//prepare the create a SQL statement
			rs = stmt.executeQuery("SELECT * from guest ORDER BY LENGTH(guestID), guestID asc");//SQL select query to display the class type // e.roleID,
			
			//add the rows in the class type details database to the JTable
			Object[] row = new Object[6];
			while (rs.next()) { 
			
				guestID = rs.getString(1);
				guestName = rs.getString(2);
				guestAddress = rs.getString(3);
				guestPhoneNumber = rs.getString(4);
				guestEmail = rs.getString(5);
				guestGender = rs.getString(6);
	
				
	
	
				guest.addRecord(guestList, new CRUD_1(guestID, guestName, guestAddress, guestPhoneNumber, guestEmail, guestGender));
			
				
				row[0] = guestID;
				row[1] = guestName;
				row[2] = guestAddress;
				row[3] = guestPhoneNumber;
				row[4] = guestEmail;
				row[5] = guestGender;
		
				
				model.addRow(row);
            } 
			guest.RowFilterTest(table, model, txtSearch);
			
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

			CRUD_1 guestRefer ;
			
			Object[] row = new Object[15];
			

			for(int i = 0; i< guest.getCount(guestList); i++) {
			
			
				guestRefer = guest.getIndex(guestList, i);
			

				row[0] = guest.getRecord(guestList, guestRefer.getGuestID()).getGuestID();
				row[1] = guest.getRecord(guestList, guestRefer.getGuestID()).getGuestName();
				row[2] = guest.getRecord(guestList, guestRefer.getGuestID()).getGuestAddress();
				row[3] = guest.getRecord(guestList, guestRefer.getGuestID()).getGuestPhoneNumber();
				row[4] = guest.getRecord(guestList, guestRefer.getGuestID()).getGuestEmail();
				row[5] = guest.getRecord(guestList, guestRefer.getGuestID()).getGuestGender();


				model.addRow(row);
			}

		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
}
