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
import CRUD.Check_In_Form;
import CRUD.ConnectDatabase;





public class Check_In_Form_Display extends JPanel {

	private JTextField txtSearch;
	private JTable table;
	private JTextField txtReservationID;
	private JTextField txtCheckInFormID;
	private JTextField txtSignature;
	


	
	ArrayList<CRUD_1> checkInFormList = new ArrayList<CRUD_1>();


	Check_In_Form checkInForm = new Check_In_Form(checkInFormList);
	ConnectDatabase db = new ConnectDatabase();
	
	CRUD_1 checkInForms ;

	
	
	/**
	 * Create the panel.
	 */
	public Check_In_Form_Display() {

		JPanel panel = new JPanel();
    	panel.setBackground(Color.BLACK);
   	 	panel.setLayout(null);
		setLayout(null);
		
		
		
		JLabel lblCheckInFormID = new JLabel("Check In Form ID:");
		lblCheckInFormID.setBounds(41, 172, 121, 16);
		add(lblCheckInFormID);
		
		JLabel lblReservationID = new JLabel("Reservation ID:");
		lblReservationID.setBounds(41, 203, 121, 16);
		add(lblReservationID);


		
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

				if(fieldCheck("Insert", txtCheckInFormID.getText(), txtReservationID.getText(), txtSignature.getText()) == 0) {
					
					String query = "INSERT INTO check_in_form VALUES ('" +txtCheckInFormID.getText()+ "','" +txtReservationID.getText()+ "','" +txtSignature.getText() + "')";
						
					checkInForm.addRecord(checkInFormList, new CRUD_1(txtCheckInFormID.getText(), txtReservationID.getText(),Integer.parseInt(txtSignature.getText())));
					
						executeSQL(query, "Inserted");

				}

			}
		});
		btnAdd.setBounds(12, 347, 86, 39);
		add(btnAdd);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				if(fieldCheck("Update", txtCheckInFormID.getText(), txtReservationID.getText(), txtSignature.getText()) == 0) {
						
					String query = "UPDATE check_in_form SET signature = '" +txtSignature.getText()+"' WHERE reservationID ='"+txtReservationID.getText()+ "' and checkInFormID = '"+txtCheckInFormID.getText()+ "'";
					
					
					checkInForm.setRecord(checkInFormList, new CRUD_1(txtCheckInFormID.getText(), txtReservationID.getText(),Integer.parseInt(txtSignature.getText())));
					
					executeSQL(query, "Updated");
				}
			}
		});
		btnUpdate.setBounds(110, 347, 86, 39);
		add(btnUpdate);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(fieldCheck("Delete", txtCheckInFormID.getText(), txtReservationID.getText(), txtSignature.getText()) == 0) {
					
					String query = "DELETE FROM check_in_form WHERE reservationID ='"+txtReservationID.getText()+ "' and checkInFormID = '"+ txtCheckInFormID.getText() +"'";
					
					checkInForm.deleteRecord(checkInFormList, txtCheckInFormID.getText(),txtReservationID.getText());
					executeSQL(query, "Deleted");
				}
			}
		});
		btnDelete.setBounds(213, 347, 86, 39);
		add(btnDelete);
		
		
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(338, 62, 886, 402);
		add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JLabel lblSignature = new JLabel("Signature:");
		lblSignature.setBounds(41, 232, 142, 16);
		add(lblSignature);
		
		txtSignature = new JTextField();
		txtSignature.setColumns(10);
		txtSignature.setBounds(210, 232, 121, 22);
		add(txtSignature);
		
		txtReservationID = new JTextField();
		txtReservationID.setColumns(10);
		txtReservationID.setBounds(210, 200, 121, 22);
		add(txtReservationID);
		
		txtCheckInFormID = new JTextField();
		txtCheckInFormID.setColumns(10);
		txtCheckInFormID.setBounds(210, 169, 121, 22);
		add(txtCheckInFormID);
		
		

		
		//display the room panel 
		displayData();			
	}


	/*
	Validates the fields in the table for empty and invalid inputs & prompts proper error message
	
	@param actionName: whether the user is inserting, updating / deleting the record
	@param ID, ..., role : All the fields that undergo the validation
	@return errCounter: if the errCounter returns 0, then there are no error encountered 
	*/
	int fieldCheck(String actionName, String checkInFormID, String reservationID, String signature) {
		int errCounter = 0;
		
		if(checkInFormID.equals("") || reservationID.equals("") || signature.equals("")) {
			JOptionPane.showMessageDialog(null, "Please fill in all the fields!");
			errCounter ++;
		}else {
			if(!checkInForm.validateInt(signature)) {
				JOptionPane.showMessageDialog(null, "Signature can only be 1 or 0 to show that it has been obtained! ");
				errCounter ++;
			}else{
				if(actionName.equals("Insert")) {
					if(checkInForm.isIdExist(checkInFormList, checkInFormID, reservationID)){
						JOptionPane.showMessageDialog(null, "Failed to add new check in form record as the same ID already exists!");
						errCounter ++;
					}
				}
							
				if(actionName.equals("Update")) {
					if(checkInForm.isRecordExist(checkInFormList, new CRUD_1(checkInFormID, reservationID,Integer.parseInt(signature)))){
						JOptionPane.showMessageDialog(null, " Failed to update check in form record as the exact record already exists!");
						errCounter ++;
					}else if(!checkInForm.isIdExist(checkInFormList, checkInFormID, reservationID)){
						JOptionPane.showMessageDialog(null, "Failed to update check in form record as no such ID is found!");
						errCounter ++;
					}
				}
			}

			if(actionName.equals("Delete")) {
				if(!checkInForm.isIdExist(checkInFormList, checkInFormID, reservationID)){
					JOptionPane.showMessageDialog(null, "Failed to update check in form record as no such ID is found!");
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
		Object[] columns = {"Check In Form ID", "Reservation ID", "Signature"  };
		
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
				
				txtCheckInFormID.setText(model.getValueAt(i, 0).toString());
				txtReservationID.setText(model.getValueAt(i, 1).toString());
				txtSignature.setText(model.getValueAt(i, 2).toString());
	
				
			}
			
		});
		
		
		
		try {
			Class.forName(db.getClassName());//the database driver 
			

			
			
			String checkInFormID, reservationID;
			int signature;
			
			conn = DriverManager.getConnection(db.getURL(), db.getUserName(), db.getPassword()); //connect to the database
			conn.setAutoCommit(false);//do not auto commit the SQL statements
			stmt = conn.createStatement();//prepare the create a SQL statement
			rs = stmt.executeQuery("SELECT * from check_in_form ORDER BY LENGTH(checkInFormID), checkInFormID asc");//SQL select query to display the class type // e.roleID,
			
			//add the rows in the class type details database to the JTable
			Object[] row = new Object[3];
			while (rs.next()) { 
			
				checkInFormID = rs.getString(1);
				reservationID = rs.getString(2);
				signature = rs.getInt(3);
		
				
				
	
	
				checkInForm.addRecord(checkInFormList, new CRUD_1(checkInFormID,reservationID,signature));
			
				
				row[0] = checkInFormID;
				row[1] = reservationID;
				row[2] = signature;

	
				
				model.addRow(row);
            } 
			checkInForm.RowFilterTest(table, model, txtSearch);
			
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

			CRUD_1 checkInFormRefer ;
			
			Object[] row = new Object[3];
			

			for(int i = 0; i< checkInForm.getCount(checkInFormList); i++) {
			
			
				checkInFormRefer = checkInForm.getIndex(checkInFormList, i);
			
			
				
				row[0] = checkInForm.getRecord(checkInFormList, checkInFormRefer.getCheckInFormID(), checkInFormRefer.getReservationID()).getCheckInFormID();
				row[1] = checkInForm.getRecord(checkInFormList, checkInFormRefer.getCheckInFormID(), checkInFormRefer.getReservationID()).getReservationID();
				row[2] = checkInForm.getRecord(checkInFormList, checkInFormRefer.getCheckInFormID(), checkInFormRefer.getReservationID()).getSignature();
				
				
				
				model.addRow(row);
			}
			
			
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
}
