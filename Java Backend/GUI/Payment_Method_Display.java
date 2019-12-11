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
import CRUD.Payment_Method;




public class Payment_Method_Display extends JPanel {

	private JTextField txtSearch;
	private JTable table;
	private JTextField txtGuestID;
	private JTextField txtEmpCode;
	private JTextField textField;
	


	
	ArrayList<CRUD_2> paymentMethodList = new ArrayList<CRUD_2>();


	Payment_Method paymentMethod = new Payment_Method(paymentMethodList);
	ConnectDatabase db = new ConnectDatabase();
	
	CRUD_2 paymentMethods ;
	private JTextField txtPaymentAmount;
	private JTextField txtPaymentMethodID;
	private JTextField txtPaymentID;

	
	
	/**
	 * Create the panel.
	 */
	public Payment_Method_Display() {

		JPanel panel = new JPanel();
    	panel.setBackground(Color.BLACK);
   	 	panel.setLayout(null);
		setLayout(null);
		
		JLabel lblGuestID = new JLabel("Guest ID:");
		lblGuestID.setBounds(36, 216, 76, 16);
		add(lblGuestID);


		
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

				if(fieldCheck("Insert", txtPaymentMethodID.getText(), txtPaymentID.getText(), txtGuestID.getText(),  txtPaymentAmount.getText()) == 0) {
					
					
					String query = "INSERT INTO payment_method VALUES ('" +txtPaymentMethodID.getText()+ "','" + txtPaymentID.getText()+ "','" + txtGuestID.getText()+ "','" + txtPaymentAmount.getText()+"')";
						
					paymentMethod.addRecord(paymentMethodList, new CRUD_2(txtPaymentMethodID.getText(), txtPaymentID.getText(), txtGuestID.getText(),  Double.parseDouble(txtPaymentAmount.getText())));
					
						executeSQL(query, "Inserted");

				}

			}
		});
		btnAdd.setBounds(12, 427, 86, 39);
		add(btnAdd);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				if(fieldCheck("Update", txtPaymentMethodID.getText(), txtPaymentID.getText(), txtGuestID.getText(),  txtPaymentAmount.getText()) == 0) {
						
					String query = "UPDATE payment_method SET paymentAmount = '" +txtPaymentAmount.getText()+"' WHERE paymentMethodID ='"+txtPaymentMethodID.getText()+ "' and paymentID = '"+txtPaymentID.getText()+ "' and guestID = '"+txtGuestID.getText()+"'";
					
					
					paymentMethod.setRecord(paymentMethodList, new CRUD_2(txtPaymentMethodID.getText(), txtPaymentID.getText(), txtGuestID.getText(),  Double.parseDouble(txtPaymentAmount.getText())));
					
					executeSQL(query, "Updated");
				}
			}
		});
		btnUpdate.setBounds(110, 427, 86, 39);
		add(btnUpdate);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(fieldCheck("Delete", txtPaymentMethodID.getText(), txtPaymentID.getText(), txtGuestID.getText(),  txtPaymentAmount.getText()) == 0) {
					
					String query = "DELETE FROM payment_method WHERE paymentMethodID ='"+txtPaymentMethodID.getText()+ "' and paymentID = '"+txtPaymentID.getText()+ "' and guestID = '"+txtGuestID.getText()+"'";
					
					paymentMethod.deleteRecord(paymentMethodList, txtPaymentMethodID.getText(), txtPaymentID.getText(), txtGuestID.getText());
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
		
		txtGuestID = new JTextField();
		txtGuestID.setColumns(10);
		txtGuestID.setBounds(205, 213, 121, 22);
		add(txtGuestID);
		
		txtPaymentAmount = new JTextField();
		txtPaymentAmount.setText("0.00");
		txtPaymentAmount.setColumns(10);
		txtPaymentAmount.setBounds(205, 248, 121, 22);
		add(txtPaymentAmount);
		
		JLabel lblPaymentAmount = new JLabel("Payment Amount:");
		lblPaymentAmount.setBounds(36, 251, 144, 16);
		add(lblPaymentAmount);
		
		JLabel lblPaymentMethodID = new JLabel("Payment Method ID:");
		lblPaymentMethodID.setBounds(36, 149, 121, 16);
		add(lblPaymentMethodID);
		
		txtPaymentMethodID = new JTextField();
		txtPaymentMethodID.setColumns(10);
		txtPaymentMethodID.setBounds(205, 146, 121, 22);
		add(txtPaymentMethodID);
		
		txtPaymentID = new JTextField();
		txtPaymentID.setColumns(10);
		txtPaymentID.setBounds(205, 178, 121, 22);
		add(txtPaymentID);
		
		JLabel lblPaymentID = new JLabel("Payment ID:");
		lblPaymentID.setBounds(36, 181, 76, 16);
		add(lblPaymentID);
		
		

		
		//display the room panel 
		displayData();			
	}


	/*
	Validates the fields in the table for empty and invalid inputs & prompts proper error message
	
	@param actionName: whether the user is inserting, updating / deleting the record
	@param ID, ..., role : All the fields that undergo the validation
	@return errCounter: if the errCounter returns 0, then there are no error encountered 
	*/
	int fieldCheck(String actionName, String paymentMethodID, String paymentID, String guestID, String paymentAmount) {
		int errCounter = 0;
		
		if(paymentMethodID.equals("") || paymentID.equals("") || guestID.equals("") || paymentAmount.equals("")) {
			JOptionPane.showMessageDialog(null, "Please fill in all the fields!");
			errCounter ++;
		}else {
			if(!paymentMethod.validateDouble(paymentAmount)) {
				JOptionPane.showMessageDialog(null, "Amounts can only have digits & decimals! ");
				errCounter ++;
			}else{
				if(actionName.equals("Insert")) {
					if(paymentMethod.isIdExist(paymentMethodList, paymentMethodID, paymentID , guestID)){
						JOptionPane.showMessageDialog(null, "Failed to add new payment method record as the same ID already exists!");
						errCounter ++;
					}
				}
							
				if(actionName.equals("Update")) {
					if(paymentMethod.isRecordExist(paymentMethodList, new CRUD_2(paymentMethodID, paymentID, guestID, Double.parseDouble(paymentAmount)))){
						JOptionPane.showMessageDialog(null, " Failed to update payment method record as the exact record already exists!");
						errCounter ++;
					}else if(!paymentMethod.isIdExist(paymentMethodList, paymentMethodID, paymentID , guestID)){
						JOptionPane.showMessageDialog(null, "Failed to update payment method record as no such ID is found!");
						errCounter ++;
					}
				}
			}

			if(actionName.equals("Delete")) {
				if(!paymentMethod.isIdExist(paymentMethodList, paymentMethodID, paymentID , guestID)){
					JOptionPane.showMessageDialog(null, "Failed to update payment method record as no such ID is found!");
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
		Object[] columns = {"Payment Method ID", "Payment ID", "Guest ID", "Payment Amount" };
		
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
				
				txtPaymentMethodID.setText(model.getValueAt(i, 0).toString());
				txtPaymentID.setText(model.getValueAt(i, 1).toString());
				txtGuestID.setText(model.getValueAt(i, 2).toString());
				txtPaymentAmount.setText(model.getValueAt(i, 3).toString());
	
	
			}
			
		});
		
		
		
		try {
			Class.forName(db.getClassName());//the database driver 
			

			
			
			String paymentMethodID, paymentID, guestID;
			double paymentAmount;
			
			conn = DriverManager.getConnection(db.getURL(), db.getUserName(), db.getPassword()); //connect to the database
			conn.setAutoCommit(false);//do not auto commit the SQL statements
			stmt = conn.createStatement();//prepare the create a SQL statement
			rs = stmt.executeQuery("SELECT * from payment_method ORDER BY LENGTH(paymentMethodID), paymentMethodID asc");//SQL select query to display the class type 
			
			//add the rows in the class type details database to the JTable
			Object[] row = new Object[4];
			while (rs.next()) { 
			
				paymentMethodID = rs.getString(1);
				paymentID = rs.getString(2);
				guestID = rs.getString(3);
				paymentAmount = rs.getDouble(4);
		
				
				
	
	
				paymentMethod.addRecord(paymentMethodList, new CRUD_2(paymentMethodID, paymentID, guestID, paymentAmount));
			
				
				row[0] = paymentMethodID;
				row[1] = paymentID;
				row[2] = guestID;
				row[3] = paymentAmount;

	
				
				model.addRow(row);
            } 
			paymentMethod.RowFilterTest(table, model, txtSearch);
			
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

			CRUD_2 paymentMethodRefer ;
			
			Object[] row = new Object[4];
			

			for(int i = 0; i< paymentMethod.getCount(paymentMethodList); i++) {
			
			
				paymentMethodRefer = paymentMethod.getIndex(paymentMethodList, i);
			
		
				
				row[0] = paymentMethod.getRecord(paymentMethodList, paymentMethodRefer.getPaymentMethodID(), paymentMethodRefer.getPaymentID(), paymentMethodRefer.getGuestID()).getPaymentMethodID();
				row[1] = paymentMethod.getRecord(paymentMethodList, paymentMethodRefer.getPaymentMethodID(), paymentMethodRefer.getPaymentID(), paymentMethodRefer.getGuestID()).getPaymentID();
				row[2] = paymentMethod.getRecord(paymentMethodList, paymentMethodRefer.getPaymentMethodID(), paymentMethodRefer.getPaymentID(), paymentMethodRefer.getGuestID()).getGuestID();
				row[3] = paymentMethod.getRecord(paymentMethodList, paymentMethodRefer.getPaymentMethodID(), paymentMethodRefer.getPaymentID(), paymentMethodRefer.getGuestID()).getPaymentAmount();

				
				
				model.addRow(row);
			}
			
			
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
}
