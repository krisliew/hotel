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
import CRUD.Check_In;
import CRUD.ConnectDatabase;





public class Check_In_Display extends JPanel {

	private JTextField txtSearch;
	private JTable table;
	private JTextField txtCheckInFormID;
	private JTextField txtPaymentStatus;
	private JTextField txtCheckInID;
	private JTextField txtDepositAmount;


	
	ArrayList<CRUD_1> checkInList = new ArrayList<CRUD_1>();


	Check_In checkIn = new Check_In(checkInList);
	ConnectDatabase db = new ConnectDatabase();
	
	CRUD_1 checkIns ;
	

	
	
	/**
	 * Create the panel.
	 */
	public Check_In_Display() {

		JPanel panel = new JPanel();
    	panel.setBackground(Color.BLACK);
   	 	panel.setLayout(null);
		setLayout(null);
		
		
		
		JLabel lblCheckInFormID = new JLabel("Check In Form ID:");
		lblCheckInFormID.setBounds(41, 172, 121, 16);
		add(lblCheckInFormID);
		
		JLabel lblDepositAmount = new JLabel("Deposit Amount:");
		lblDepositAmount.setBounds(41, 203, 121, 16);
		add(lblDepositAmount);


		
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

				if(fieldCheck("Insert", txtCheckInID.getText(), txtCheckInFormID.getText(), txtDepositAmount.getText(), txtPaymentStatus.getText()) == 0) {
					
					String query = "INSERT INTO check_in VALUES ('" +txtCheckInID.getText()+ "','" +txtCheckInFormID.getText() + "','" +txtDepositAmount.getText() + "','" +txtPaymentStatus.getText() + "')";
						
					checkIn.addRecord(checkInList, new CRUD_1(txtCheckInID.getText(), txtCheckInFormID.getText(),Double.parseDouble(txtDepositAmount.getText()), txtPaymentStatus.getText()));
					
						executeSQL(query, "Inserted");

				}

			}
		});
		btnAdd.setBounds(12, 347, 86, 39);
		add(btnAdd);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				if(fieldCheck("Update", txtCheckInID.getText(), txtCheckInFormID.getText(), txtDepositAmount.getText(), txtPaymentStatus.getText()) == 0) {
						
					String query = "UPDATE check_in SET depositAmount = '" +txtDepositAmount.getText()+ "', paymentStatus ='" +txtPaymentStatus.getText()+"' WHERE checkInID ='"+txtCheckInID.getText()+	"' and checkInFormID = '"+txtCheckInFormID.getText()+"'";
					
					
					checkIn.setRecord(checkInList, new CRUD_1(txtCheckInID.getText(), txtCheckInFormID.getText(),Double.parseDouble(txtDepositAmount.getText()), txtPaymentStatus.getText()));
					
					executeSQL(query, "Updated");
				}
			}
		});
		btnUpdate.setBounds(110, 347, 86, 39);
		add(btnUpdate);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(fieldCheck("Delete", txtCheckInID.getText(), txtCheckInFormID.getText(), txtDepositAmount.getText(), txtPaymentStatus.getText()) == 0) {
					
					String query = "DELETE FROM check_in WHERE checkInID ='"+txtCheckInID.getText()+ "' and checkInFormID = '"+ txtCheckInFormID.getText() +"'";
					
					checkIn.deleteRecord(checkInList, txtCheckInID.getText(),txtCheckInFormID.getText());
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
		
		JLabel lblPaymentStatus = new JLabel("Payment Status:");
		lblPaymentStatus.setBounds(41, 232, 142, 16);
		add(lblPaymentStatus);
		
		txtPaymentStatus = new JTextField();
		txtPaymentStatus.setColumns(10);
		txtPaymentStatus.setBounds(210, 232, 121, 22);
		add(txtPaymentStatus);
		
		txtCheckInFormID = new JTextField();
		txtCheckInFormID.setColumns(10);
		txtCheckInFormID.setBounds(210, 169, 121, 22);
		add(txtCheckInFormID);
		
		JLabel lblCheckInId = new JLabel("Check In ID:");
		lblCheckInId.setBounds(41, 134, 121, 16);
		add(lblCheckInId);
		
		txtCheckInID = new JTextField();
		txtCheckInID.setColumns(10);
		txtCheckInID.setBounds(210, 131, 121, 22);
		add(txtCheckInID);
		
		txtDepositAmount = new JTextField();
		txtDepositAmount.setText("0.00");
		txtDepositAmount.setColumns(10);
		txtDepositAmount.setBounds(210, 200, 121, 22);
		add(txtDepositAmount);
		
		

		
		//display the room panel 
		displayData();			
	}


	/*
	Validates the fields in the table for empty and invalid inputs & prompts proper error message
	
	@param actionName: whether the user is inserting, updating / deleting the record
	@param ID, ..., role : All the fields that undergo the validation
	@return errCounter: if the errCounter returns 0, then there are no error encountered 
	*/
	int fieldCheck(String actionName, String checkInID, String checkInFormID, String depositAmount, String paymentStatus) {
		int errCounter = 0;
		
		if(checkInID.equals("") || checkInFormID.equals("") || depositAmount.equals("") || paymentStatus.equals("")) {
			JOptionPane.showMessageDialog(null, "Please fill in all the fields!");
			errCounter ++;
		}else {
			if( !checkIn.validateDouble(depositAmount)) {
				JOptionPane.showMessageDialog(null, "Amount can only have digits & decimals! ");
				errCounter ++;
			}else{
				if(actionName.equals("Insert")) {
					if(checkIn.isIdExist(checkInList, checkInID, checkInFormID)){
						JOptionPane.showMessageDialog(null, "Failed to add new check in record as the same ID already exists!");
						errCounter ++;
					}
				}
							
				if(actionName.equals("Update")) {
					if(checkIn.isRecordExist(checkInList, new CRUD_1(checkInID, checkInFormID,Double.parseDouble(depositAmount), paymentStatus))){
						JOptionPane.showMessageDialog(null, " Failed to update check in record as the exact record already exists!");
						errCounter ++;
					}else if(!checkIn.isIdExist(checkInList, checkInID, checkInFormID)){
						JOptionPane.showMessageDialog(null, "Failed to update check in record as no such ID is found!");
						errCounter ++;
					}
				}
			}

			if(actionName.equals("Delete")) {
				if(!checkIn.isIdExist(checkInList, checkInID, checkInFormID)){
					JOptionPane.showMessageDialog(null, "Failed to update check in record as no such ID is found!");
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
		Object[] columns = {"Check In ID", "Check In Form ID", "Deposit Amount", "Payment Status"  };
		
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
		
		for(int i = 0; i < 4; i++) {
				table.getColumnModel().getColumn(i).setCellRenderer(Renderer);
		}
		
		//allow the selected row values to be displayed in the JTextField
		table.addMouseListener(new MouseAdapter() {
			
			public void mouseClicked(MouseEvent e) {
				int i = table.getSelectedRow();
				
				if (table.getRowSorter()!=null) {
				    i = table.getRowSorter().convertRowIndexToModel(i);
				}
				
				txtCheckInID.setText(model.getValueAt(i, 0).toString());
				txtCheckInFormID.setText(model.getValueAt(i, 1).toString());
				txtDepositAmount.setText(model.getValueAt(i, 2).toString());
				txtPaymentStatus.setText(model.getValueAt(i, 3).toString());
	
				
			}
			
		});
		
		
		
		try {
			Class.forName(db.getClassName());//the database driver 
			

			
			
			String checkInID, checkInFormID, paymentStatus;
			double depositAmount;
			
			conn = DriverManager.getConnection(db.getURL(), db.getUserName(), db.getPassword()); //connect to the database
			conn.setAutoCommit(false);//do not auto commit the SQL statements
			stmt = conn.createStatement();//prepare the create a SQL statement
			rs = stmt.executeQuery("SELECT * from check_in ORDER BY LENGTH(checkInID), checkInID asc");//SQL select query to display the class type // e.roleID,
			
			//add the rows in the class type details database to the JTable
			Object[] row = new Object[4];
			while (rs.next()) { 
			
				checkInID = rs.getString(1);
				checkInFormID = rs.getString(2);
				depositAmount = rs.getDouble(3);
				paymentStatus = rs.getString(4);
				
				
	
	
				checkIn.addRecord(checkInList, new CRUD_1(checkInID,checkInFormID,depositAmount, paymentStatus));
			
				
				row[0] = checkInID;
				row[1] = checkInFormID;
				row[2] = depositAmount;
				row[3] = paymentStatus;

	
				
				model.addRow(row);
            } 
			checkIn.RowFilterTest(table, model, txtSearch);
			
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

			CRUD_1 checkInRefer ;
			
			Object[] row = new Object[4];
			

			for(int i = 0; i< checkIn.getCount(checkInList); i++) {
			
			
				checkInRefer = checkIn.getIndex(checkInList, i);
			
			
				
				row[0] = checkIn.getRecord(checkInList, checkInRefer.getCheckInID(), checkInRefer.getCheckInFormID()).getCheckInID();
				row[1] = checkIn.getRecord(checkInList, checkInRefer.getCheckInID(), checkInRefer.getCheckInFormID()).getCheckInFormID();
				row[2] = checkIn.getRecord(checkInList, checkInRefer.getCheckInID(), checkInRefer.getCheckInFormID()).getDepositAmount();
				row[3] = checkIn.getRecord(checkInList, checkInRefer.getCheckInID(), checkInRefer.getCheckInFormID()).getPaymentStatus();
				
				
				model.addRow(row);
			}
			
			
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
}
