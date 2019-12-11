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
import CRUD.Credit_Card;




public class Credit_Card_Display extends JPanel {

	private JTextField txtSearch;
	private JTable table;
	private JTextField txtCreditCardBalance;
	private JTextField txtPaymentMethodID;
	private JTextField txtPaymentMethodName;
	private JTextField txtCreditCardHolderName;
	private JTextField txtCreditCardBankName;
	private JTextField txtCreditCardExpiryMonth;
	private JTextField txtCreditCardExpiryYear;
	


	
	ArrayList<CRUD_2> creditCardList = new ArrayList<CRUD_2>();


	Credit_Card creditCard = new Credit_Card(creditCardList);
	ConnectDatabase db = new ConnectDatabase();
	
	CRUD_2 creditCards ;
	

	
	
	/**
	 * Create the panel.
	 */
	public Credit_Card_Display() {

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

				if(fieldCheck("Insert", txtPaymentMethodID.getText(), txtPaymentMethodName.getText(), txtCreditCardHolderName.getText(),  txtCreditCardBankName.getText(), txtCreditCardBalance.getText(), txtCreditCardExpiryMonth.getText(), txtCreditCardExpiryYear.getText()) == 0) {
					
					
					String query = "INSERT INTO credit_card VALUES ('" + txtPaymentMethodID.getText()+ "','" + txtPaymentMethodName.getText()+ "','" + txtCreditCardHolderName.getText()+ "','" + txtCreditCardBankName.getText()+ "','" + txtCreditCardBalance.getText()+ "','" + txtCreditCardExpiryMonth.getText()+ "','" + txtCreditCardExpiryYear.getText()+ "')";
						
					creditCard.addRecord(creditCardList, new CRUD_2(txtPaymentMethodID.getText(), txtPaymentMethodName.getText(), txtCreditCardHolderName.getText(), txtCreditCardBankName.getText(), Double.parseDouble(txtCreditCardBalance.getText()), Integer.parseInt(txtCreditCardExpiryMonth.getText()), Integer.parseInt(txtCreditCardExpiryYear.getText())));
					
						executeSQL(query, "Inserted");

				}

			}
		});
		btnAdd.setBounds(12, 427, 86, 39);
		add(btnAdd);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				if(fieldCheck("Update", txtPaymentMethodID.getText(), txtPaymentMethodName.getText(), txtCreditCardHolderName.getText(),  txtCreditCardBankName.getText(), txtCreditCardBalance.getText(), txtCreditCardExpiryMonth.getText(), txtCreditCardExpiryYear.getText()) == 0) {
						
					String query = "UPDATE credit_card SET paymentMethodName = '" +txtPaymentMethodName.getText()+ "', creditCardHolderName ='" +txtCreditCardHolderName.getText()+ "', creditCardBankName ='" +txtCreditCardBankName.getText()+ "', creditCardBalance ='" +txtCreditCardBalance.getText()+ "', creditCardExpiryMonth ='" +txtCreditCardExpiryMonth.getText()+ "', creditCardExpiryYear ='" +txtCreditCardExpiryYear.getText()+ "' WHERE paymentMethodID ='"+txtPaymentMethodID.getText()+"'";
					
					
					creditCard.setRecord(creditCardList, new CRUD_2(txtPaymentMethodID.getText(), txtPaymentMethodName.getText(), txtCreditCardHolderName.getText(), txtCreditCardBankName.getText(), Double.parseDouble(txtCreditCardBalance.getText()), Integer.parseInt(txtCreditCardExpiryMonth.getText()), Integer.parseInt(txtCreditCardExpiryYear.getText())));
					
					executeSQL(query, "Updated");
				}
			}
		});
		btnUpdate.setBounds(110, 427, 86, 39);
		add(btnUpdate);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(fieldCheck("Delete", txtPaymentMethodID.getText(), txtPaymentMethodName.getText(), txtCreditCardHolderName.getText(),  txtCreditCardBankName.getText(), txtCreditCardBalance.getText(), txtCreditCardExpiryMonth.getText(), txtCreditCardExpiryYear.getText()) == 0) {
					
					String query = "DELETE FROM credit_card WHERE paymentMethodID ='"+txtPaymentMethodID.getText()+"'";
					
					creditCard.deleteRecord(creditCardList, txtPaymentMethodID.getText());
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
		
		txtCreditCardBalance = new JTextField();
		txtCreditCardBalance.setText("0.00");
		txtCreditCardBalance.setColumns(10);
		txtCreditCardBalance.setBounds(205, 282, 121, 22);
		add(txtCreditCardBalance);
		
		JLabel lblCreditCardBalance = new JLabel("Credit Card Balance:");
		lblCreditCardBalance.setBounds(36, 285, 144, 16);
		add(lblCreditCardBalance);
		
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
		
		txtCreditCardHolderName = new JTextField();
		txtCreditCardHolderName.setColumns(10);
		txtCreditCardHolderName.setBounds(205, 213, 121, 22);
		add(txtCreditCardHolderName);
		
		JLabel lblCreditCardHolderName = new JLabel("Credit Card Holder Name:");
		lblCreditCardHolderName.setBounds(36, 216, 160, 16);
		add(lblCreditCardHolderName);
		
		JLabel lblCreditCardBankName = new JLabel("Credit Card Bank Name:");
		lblCreditCardBankName.setBounds(36, 250, 160, 16);
		add(lblCreditCardBankName);
		
		txtCreditCardBankName = new JTextField();
		txtCreditCardBankName.setColumns(10);
		txtCreditCardBankName.setBounds(205, 247, 121, 22);
		add(txtCreditCardBankName);
		
		txtCreditCardExpiryMonth = new JTextField();
		txtCreditCardExpiryMonth.setColumns(10);
		txtCreditCardExpiryMonth.setBounds(205, 317, 121, 22);
		add(txtCreditCardExpiryMonth);
		
		JLabel lblCreditCardExpiryMonth = new JLabel("Credit Card Expiry Month:");
		lblCreditCardExpiryMonth.setBounds(36, 320, 160, 16);
		add(lblCreditCardExpiryMonth);
		
		txtCreditCardExpiryYear = new JTextField();
		txtCreditCardExpiryYear.setColumns(10);
		txtCreditCardExpiryYear.setBounds(205, 348, 121, 22);
		add(txtCreditCardExpiryYear);
		
		JLabel lblCreditCardExpiryYear = new JLabel("Credit Card Expiry Year");
		lblCreditCardExpiryYear.setBounds(36, 351, 144, 16);
		add(lblCreditCardExpiryYear);
		
		

		
		//display the room panel 
		displayData();			
	}


	/*
	Validates the fields in the table for empty and invalid inputs & prompts proper error message
	
	@param actionName: whether the user is inserting, updating / deleting the record
	@param ID, ..., role : All the fields that undergo the validation
	@return errCounter: if the errCounter returns 0, then there are no error encountered 
	*/
	int fieldCheck(String actionName, String paymentMethodID, String paymentMethodName, String cardholdername, String bankname, String cardbalance, String cardmonthexpiry, String cardyearexpiry) {
		int errCounter = 0;
		
		if(paymentMethodID.equals("") || paymentMethodName.equals("") || cardholdername.equals("") || bankname.equals("") || cardbalance.equals("") || cardmonthexpiry.equals("") || cardyearexpiry.equals("")) {
			JOptionPane.showMessageDialog(null, "Please fill in all the fields!");
			errCounter ++;
		}else {
			if(!creditCard.validateDouble(cardbalance) || !creditCard.validateInt(cardmonthexpiry) || !creditCard.validateInt(cardyearexpiry)) {
				JOptionPane.showMessageDialog(null, "Expiry Month & Year can only have digits and Balance can only have digits & decimals! ");
				errCounter ++;
			}else{
				if(actionName.equals("Insert")) {
					if(creditCard.isIdExist(creditCardList, paymentMethodID)){
						JOptionPane.showMessageDialog(null, "Failed to add new credit card record as the same ID already exists!");
						errCounter ++;
					}
				}
							
				if(actionName.equals("Update")) {
					if(creditCard.isRecordExist(creditCardList, new CRUD_2(paymentMethodID, paymentMethodName, cardholdername, bankname, Double.parseDouble(cardbalance), Integer.parseInt(cardmonthexpiry), Integer.parseInt(cardyearexpiry)))){
						JOptionPane.showMessageDialog(null, " Failed to update credit card record as the exact record already exists!");
						errCounter ++;
					}else if(!creditCard.isIdExist(creditCardList, paymentMethodID)){
						JOptionPane.showMessageDialog(null, "Failed to update credit card record as no such ID is found!");
						errCounter ++;
					}
				}
			}

			if(actionName.equals("Delete")) {
				if(!creditCard.isIdExist(creditCardList, paymentMethodID)){
					JOptionPane.showMessageDialog(null, "Failed to update credit card record as no such ID is found!");
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
		Object[] columns = {"Payment Method ID", "Payment Method Name", "Card Holder Name", "Card Bank Name", "Card Balance", "Expiry (Month)", "Expiry (Year)" };
		
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
				
				txtPaymentMethodID.setText(model.getValueAt(i, 0).toString());
				txtPaymentMethodName.setText(model.getValueAt(i, 1).toString());
				txtCreditCardHolderName.setText(model.getValueAt(i, 2).toString());
				txtCreditCardBankName.setText(model.getValueAt(i, 3).toString());
				txtCreditCardBalance.setText(model.getValueAt(i, 4).toString());
				txtCreditCardExpiryMonth.setText(model.getValueAt(i, 5).toString());
				txtCreditCardExpiryYear.setText(model.getValueAt(i, 6).toString());
	
	
			}
			
		});
		
		
		
		try {
			Class.forName(db.getClassName());//the database driver 
			

			
			
			String paymentMethodID, paymentMethodName, cardholdername, bankname;
			double cardbalance;
			int cardmonthexpiry, cardyearexpiry;
			
			conn = DriverManager.getConnection(db.getURL(), db.getUserName(), db.getPassword()); //connect to the database
			conn.setAutoCommit(false);//do not auto commit the SQL statements
			stmt = conn.createStatement();//prepare the create a SQL statement
			rs = stmt.executeQuery("SELECT * from credit_card ORDER BY LENGTH(paymentMethodID), paymentMethodID asc");//SQL select query to display the class type 
			
			//add the rows in the class type details database to the JTable
			Object[] row = new Object[7];
			while (rs.next()) { 
			
				paymentMethodID = rs.getString(1);
				paymentMethodName = rs.getString(2);
				cardholdername = rs.getString(3);
				bankname = rs.getString(4);
				cardbalance = rs.getDouble(5);
				cardmonthexpiry = rs.getInt(6);
				cardyearexpiry = rs.getInt(7);
		

				creditCard.addRecord(creditCardList, new CRUD_2(paymentMethodID, paymentMethodName, cardholdername, bankname, cardbalance, cardmonthexpiry, cardyearexpiry));
			
				
				row[0] = paymentMethodID;
				row[1] = paymentMethodName;
				row[2] = cardholdername;
				row[3] = bankname;
				row[4] = cardbalance;
				row[5] = cardmonthexpiry;
				row[6] = cardyearexpiry;

	
				
				model.addRow(row);
            } 
			creditCard.RowFilterTest(table, model, txtSearch);
			
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

			CRUD_2 creditCardRefer ;
			
			Object[] row = new Object[7];
			

			for(int i = 0; i< creditCard.getCount(creditCardList); i++) {
			
			
				creditCardRefer = creditCard.getIndex(creditCardList, i);
			
		
				
				row[0] = creditCard.getRecord(creditCardList, creditCardRefer.getPaymentMethodID()).getPaymentMethodID();
				row[1] = creditCard.getRecord(creditCardList, creditCardRefer.getPaymentMethodID()).getPaymentMethodName();
				row[2] = creditCard.getRecord(creditCardList, creditCardRefer.getPaymentMethodID()).getCreditCardHolderName();
				row[3] = creditCard.getRecord(creditCardList, creditCardRefer.getPaymentMethodID()).getCreditCardBankName();
				row[4] = creditCard.getRecord(creditCardList, creditCardRefer.getPaymentMethodID()).getCreditCardBalance();
				row[5] = creditCard.getRecord(creditCardList, creditCardRefer.getPaymentMethodID()).getCreditCardExpiryMonth();
				row[6] = creditCard.getRecord(creditCardList, creditCardRefer.getPaymentMethodID()).getCreditCardExpiryYear();

				
				
				model.addRow(row);
			}
			
			
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
}
