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
import CRUD.Cheque;
import CRUD.ConnectDatabase;
import javax.swing.SwingConstants;




public class Cheque_Display extends JPanel {

	private JTextField txtSearch;
	private JTable table;
	private JTextField txtChequeAmount;
	private JTextField txtPaymentMethodID;
	private JTextField txtPaymentMethodName;
	private JTextField txtChequeHolderName;
	private JTextField txtChequeBankName;
	private JTextField txtChequeDate;
	


	
	ArrayList<CRUD_2> chequeList = new ArrayList<CRUD_2>();


	Cheque cheque = new Cheque(chequeList);
	ConnectDatabase db = new ConnectDatabase();
	
	CRUD_2 cheques ;
	

	
	
	/**
	 * Create the panel.
	 */
	public Cheque_Display() {

		JPanel panel = new JPanel();
    	panel.setBackground(Color.BLACK);
   	 	panel.setLayout(null);
		setLayout(null);


		
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

				if(fieldCheck("Insert", txtPaymentMethodID.getText(), txtPaymentMethodName.getText(), txtChequeHolderName.getText(),  txtChequeBankName.getText(), txtChequeAmount.getText(), txtChequeDate.getText()) == 0) {
					
					
					String query = "INSERT INTO cheque VALUES ('" + txtPaymentMethodID.getText()+ "','" + txtPaymentMethodName.getText()+ "','" + txtChequeHolderName.getText()+ "','" + txtChequeBankName.getText()+ "','" + txtChequeAmount.getText()+ "','" + txtChequeDate.getText()+ "')";
						
					cheque.addRecord(chequeList, new CRUD_2(txtPaymentMethodID.getText(), txtPaymentMethodName.getText(), txtChequeHolderName.getText(), txtChequeBankName.getText(), Double.parseDouble(txtChequeAmount.getText()), txtChequeDate.getText()));
					
						executeSQL(query, "Inserted");

				}

			}
		});
		btnAdd.setBounds(12, 427, 86, 39);
		add(btnAdd);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				if(fieldCheck("Update", txtPaymentMethodID.getText(), txtPaymentMethodName.getText(), txtChequeHolderName.getText(),  txtChequeBankName.getText(), txtChequeAmount.getText(), txtChequeDate.getText()) == 0) {
						
					String query = "UPDATE cheque SET paymentMethodName = '" +txtPaymentMethodName.getText()+ "', chequeHolderName ='" +txtChequeHolderName.getText()+ "', chequeBankName ='" +txtChequeBankName.getText()+ "', chequeAmount ='" +txtChequeAmount.getText()+ "', chequeDate ='" +txtChequeDate.getText()+ "' WHERE paymentMethodID ='"+txtPaymentMethodID.getText()+"'";
					
					
					cheque.setRecord(chequeList, new CRUD_2(txtPaymentMethodID.getText(), txtPaymentMethodName.getText(), txtChequeHolderName.getText(), txtChequeBankName.getText(), Double.parseDouble(txtChequeAmount.getText()), txtChequeDate.getText()));
					
					executeSQL(query, "Updated");
				}
			}
		});
		btnUpdate.setBounds(110, 427, 86, 39);
		add(btnUpdate);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(fieldCheck("Delete", txtPaymentMethodID.getText(), txtPaymentMethodName.getText(), txtChequeHolderName.getText(),  txtChequeBankName.getText(), txtChequeAmount.getText(), txtChequeDate.getText()) == 0) {
					
					String query = "DELETE FROM cheque WHERE paymentMethodID ='"+txtPaymentMethodID.getText()+"'";
					
					cheque.deleteRecord(chequeList, txtPaymentMethodID.getText());
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
		
		txtChequeAmount = new JTextField();
		txtChequeAmount.setText("0.00");
		txtChequeAmount.setColumns(10);
		txtChequeAmount.setBounds(205, 282, 121, 22);
		add(txtChequeAmount);
		
		JLabel lblChequeAmount = new JLabel("Cheque Amount:");
		lblChequeAmount.setBounds(36, 285, 144, 16);
		add(lblChequeAmount);
		
		JLabel lblPaymentMethodID = new JLabel("Payment Method ID:");
		lblPaymentMethodID.setBounds(36, 149, 121, 16);
		add(lblPaymentMethodID);
		
		txtPaymentMethodID = new JTextField();
		txtPaymentMethodID.setColumns(10);
		txtPaymentMethodID.setBounds(205, 146, 121, 22);
		add(txtPaymentMethodID);
		
		txtPaymentMethodName = new JTextField();
		txtPaymentMethodName.setColumns(10);
		txtPaymentMethodName.setBounds(205, 178, 121, 22);
		add(txtPaymentMethodName);
		
		JLabel lblPaymentMethodName = new JLabel("Payment Method Name:");
		lblPaymentMethodName.setBounds(36, 181, 144, 16);
		add(lblPaymentMethodName);
		
		txtChequeHolderName = new JTextField();
		txtChequeHolderName.setColumns(10);
		txtChequeHolderName.setBounds(205, 213, 121, 22);
		add(txtChequeHolderName);
		
		JLabel lblChequeHolderName = new JLabel("Cheque Holder Name:");
		lblChequeHolderName.setBounds(36, 216, 160, 16);
		add(lblChequeHolderName);
		
		JLabel lblChequeBankName = new JLabel("Cheque Bank Name:");
		lblChequeBankName.setBounds(36, 250, 160, 16);
		add(lblChequeBankName);
		
		txtChequeBankName = new JTextField();
		txtChequeBankName.setColumns(10);
		txtChequeBankName.setBounds(205, 247, 121, 22);
		add(txtChequeBankName);
		
		txtChequeDate = new JTextField();
		txtChequeDate.setColumns(10);
		txtChequeDate.setBounds(205, 317, 121, 22);
		add(txtChequeDate);
		
		JLabel lblChequeDate = new JLabel("Cheque Date:");
		lblChequeDate.setBounds(36, 320, 160, 16);
		add(lblChequeDate);
		
		

		
		//display the room panel 
		displayData();			
	}


	/*
	Validates the fields in the table for empty and invalid inputs & prompts proper error message
	
	@param actionName: whether the user is inserting, updating / deleting the record
	@param ID, ..., role : All the fields that undergo the validation
	@return errCounter: if the errCounter returns 0, then there are no error encountered 
	*/
	int fieldCheck(String actionName, String paymentMethodID, String paymentMethodName, String chequeholdername, String bankname, String chequeAmount, String chequeDate) {
		int errCounter = 0;
		
		if(paymentMethodID.equals("") || paymentMethodName.equals("") || chequeholdername.equals("") || bankname.equals("") || chequeAmount.equals("") || chequeDate.equals("")) {
			JOptionPane.showMessageDialog(null, "Please fill in all the fields!");
			errCounter ++;
		}else {
			if(!cheque.validateDouble(chequeAmount)) {
				JOptionPane.showMessageDialog(null, "Amount can only have digits & decimals! ");
				errCounter ++;
			}else{
				if(actionName.equals("Insert")) {
					if(cheque.isIdExist(chequeList, paymentMethodID)){
						JOptionPane.showMessageDialog(null, "Failed to add new cheque record as the same ID already exists!");
						errCounter ++;
					}
				}
							
				if(actionName.equals("Update")) {
					if(cheque.isRecordExist(chequeList, new CRUD_2(paymentMethodID, paymentMethodName, chequeholdername, bankname, Double.parseDouble(chequeAmount), chequeDate))){
						JOptionPane.showMessageDialog(null, " Failed to update cheque record as the exact record already exists!");
						errCounter ++;
					}else if(!cheque.isIdExist(chequeList, paymentMethodID)){
						JOptionPane.showMessageDialog(null, "Failed to update cheque record as no such ID is found!");
						errCounter ++;
					}
				}
			}

			if(actionName.equals("Delete")) {
				if(!cheque.isIdExist(chequeList, paymentMethodID)){
					JOptionPane.showMessageDialog(null, "Failed to update cheque record as no such ID is found!");
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
		Object[] columns = {"Payment Method ID", "Payment Method Name", "Cheque Holder Name", "Cheque Bank Name", "Cheque Amount", "Cheque Date" };
		
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
				
				txtPaymentMethodID.setText(model.getValueAt(i, 0).toString());
				txtPaymentMethodName.setText(model.getValueAt(i, 1).toString());
				txtChequeHolderName.setText(model.getValueAt(i, 2).toString());
				txtChequeBankName.setText(model.getValueAt(i, 3).toString());
				txtChequeAmount.setText(model.getValueAt(i, 4).toString());
				txtChequeDate.setText(model.getValueAt(i, 5).toString());
	
	
			}
			
		});
		
		
		
		try {
			Class.forName(db.getClassName());//the database driver 
			

			
			
			String paymentMethodID, paymentMethodName, chequeholdername, bankname, chequeDate;
			double chequeAmount;
		
			
			conn = DriverManager.getConnection(db.getURL(), db.getUserName(), db.getPassword()); //connect to the database
			conn.setAutoCommit(false);//do not auto commit the SQL statements
			stmt = conn.createStatement();//prepare the create a SQL statement
			rs = stmt.executeQuery("SELECT * from cheque ORDER BY LENGTH(paymentMethodID), paymentMethodID asc");//SQL select query to display the class type 
			
			//add the rows in the class type details database to the JTable
			Object[] row = new Object[7];
			while (rs.next()) { 
			
				paymentMethodID = rs.getString(1);
				paymentMethodName = rs.getString(2);
				chequeholdername = rs.getString(3);
				bankname = rs.getString(4);
				chequeAmount = rs.getDouble(5);
				chequeDate = rs.getString(6);
				
		

				cheque.addRecord(chequeList, new CRUD_2(paymentMethodID, paymentMethodName, chequeholdername, bankname, chequeAmount, chequeDate));
			
				
				row[0] = paymentMethodID;
				row[1] = paymentMethodName;
				row[2] = chequeholdername;
				row[3] = bankname;
				row[4] = chequeAmount;
				row[5] = chequeDate;
			

	
				
				model.addRow(row);
            } 
			cheque.RowFilterTest(table, model, txtSearch);
			
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

			CRUD_2 chequeRefer ;
			
			Object[] row = new Object[7];
			

			for(int i = 0; i< cheque.getCount(chequeList); i++) {
			
			
				chequeRefer = cheque.getIndex(chequeList, i);
			
		
				
				row[0] = cheque.getRecord(chequeList, chequeRefer.getPaymentMethodID()).getPaymentMethodID();
				row[1] = cheque.getRecord(chequeList, chequeRefer.getPaymentMethodID()).getPaymentMethodName();
				row[2] = cheque.getRecord(chequeList, chequeRefer.getPaymentMethodID()).getChequeHolderName();
				row[3] = cheque.getRecord(chequeList, chequeRefer.getPaymentMethodID()).getChequeBankName();
				row[4] = cheque.getRecord(chequeList, chequeRefer.getPaymentMethodID()).getChequeAmount();
				row[5] = cheque.getRecord(chequeList, chequeRefer.getPaymentMethodID()).getChequeDate();
		

				
				
				model.addRow(row);
			}
			
			
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
}
