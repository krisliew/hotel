package GUI;


import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
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

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import CRUD.CRUD_2;
import CRUD.ConnectDatabase;
import CRUD.Guest;
import CRUD.Payment;




public class Payment_Display extends JPanel {

	private JTextField txtSearch;
	private JTable table;
	private JTextField txtReservationID;
	private JTextField txtRoomID;
	private JTextField txtGuestID;
	private JTextField txtTotalAmount;
	private JTextField txtEmpCode;
	private JTextField textField;
	private static int selectedRow;
	


	
	ArrayList<CRUD_2> paymentList = new ArrayList<CRUD_2>();


	Payment payment = new Payment(paymentList);
	ConnectDatabase db = new ConnectDatabase();
	
	CRUD_2 payments ;
	private JTextField txtPaymentStatus;
	private JTextField txtPaymentDate;
	private JTextField txtTipsAmount;
	private JTextField txtSubtotalAmount;
	private JTextField txtServiceID;
	private JTextField txtOrderID;
	private JTextField txtPaymentID;

	
	
	/**
	 * Create the panel.
	 */
	public Payment_Display() {

		JPanel panel = new JPanel();
    	panel.setBackground(Color.BLACK);
   	 	panel.setLayout(null);
		setLayout(null);
		
		
		
		JLabel lblRoomID = new JLabel("Room ID:");
		lblRoomID.setBounds(36, 185, 76, 16);
		add(lblRoomID);
		
		JLabel lblGuestID = new JLabel("Guest ID:");
		lblGuestID.setBounds(36, 216, 76, 16);
		add(lblGuestID);
		
		JLabel lblTotalAmount = new JLabel("Total Amount:");
		lblTotalAmount.setBounds(36, 316, 144, 16);
		add(lblTotalAmount);
		
		JLabel lblReservationID = new JLabel("Reservation ID:");
		lblReservationID.setBounds(36, 121, 121, 16);
		add(lblReservationID);
		
		txtTotalAmount = new JTextField();
		txtTotalAmount.setText("0.00");
		txtTotalAmount.setColumns(10);
		txtTotalAmount.setBounds(205, 313, 121, 22);
		add(txtTotalAmount);


		
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

				if(fieldCheck("Insert", txtPaymentID.getText(), txtServiceID.getText(), txtReservationID.getText(), txtOrderID.getText(),txtRoomID.getText(), txtGuestID.getText(),  txtSubtotalAmount.getText(),txtTipsAmount.getText(),txtTotalAmount.getText(),txtPaymentDate.getText(), txtPaymentStatus.getText()) == 0) {
					
					
					String query = "INSERT INTO payment VALUES ('" +txtPaymentID.getText()+ "','" + txtServiceID.getText()+ "','" +  txtReservationID.getText()+ "','" +  txtOrderID.getText()+ "','" + txtRoomID.getText()+ "','" +  txtGuestID.getText()+ "','" + txtSubtotalAmount.getText()+ "','" + txtTipsAmount.getText()+ "','" + txtTotalAmount.getText()+ "','" + txtPaymentDate.getText()+ "','" +  txtPaymentStatus.getText()+"')";
						
					payment.addRecord(paymentList, new CRUD_2(txtPaymentID.getText(), txtServiceID.getText(), txtReservationID.getText(), txtOrderID.getText(),txtRoomID.getText(), txtGuestID.getText(),  Double.parseDouble(txtSubtotalAmount.getText()) ,Double.parseDouble(txtTipsAmount.getText()), Double.parseDouble(txtTotalAmount.getText()), txtPaymentDate.getText(), txtPaymentStatus.getText()));
					
						executeSQL(query, "Inserted");

				}

			}
		});
		btnAdd.setBounds(12, 427, 86, 39);
		add(btnAdd);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				if(fieldCheck("Update", txtPaymentID.getText(), txtServiceID.getText(), txtReservationID.getText(), txtOrderID.getText(),txtRoomID.getText(), txtGuestID.getText(),  txtSubtotalAmount.getText(),txtTipsAmount.getText(),txtTotalAmount.getText(),txtPaymentDate.getText(), txtPaymentStatus.getText()) == 0) {
						
					String query = "UPDATE payment SET subtotalAmount = '" +txtSubtotalAmount.getText()+"', tipsAmount ='" +txtTipsAmount.getText()+"', totalAmount ='" +txtTotalAmount.getText()+"', paymentDate ='" +txtPaymentDate.getText()+"', paymentStatus ='" +txtPaymentStatus.getText()+"' WHERE paymentID ='"+txtPaymentID.getText()+ "' and serviceID = '"+txtServiceID.getText()+ "' and reservationID = '"+txtReservationID.getText()+ "' and orderID = '"+txtOrderID.getText()+ "' and roomID = '"+txtRoomID.getText()+ "' and guestID = '"+txtGuestID.getText()+"'";
					
					
					payment.setRecord(paymentList, new CRUD_2(txtPaymentID.getText(), txtServiceID.getText(), txtReservationID.getText(), txtOrderID.getText(),txtRoomID.getText(), txtGuestID.getText(),  Double.parseDouble(txtSubtotalAmount.getText()) ,Double.parseDouble(txtTipsAmount.getText()), Double.parseDouble(txtTotalAmount.getText()), txtPaymentDate.getText(), txtPaymentStatus.getText()));
					
					executeSQL(query, "Updated");
				}
			}
		});
		btnUpdate.setBounds(110, 427, 86, 39);
		add(btnUpdate);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(fieldCheck("Delete", txtPaymentID.getText(), txtServiceID.getText(), txtReservationID.getText(), txtOrderID.getText(),txtRoomID.getText(), txtGuestID.getText(),  txtSubtotalAmount.getText(),txtTipsAmount.getText(),txtTotalAmount.getText(),txtPaymentDate.getText(), txtPaymentStatus.getText()) == 0) {
					
					String query = "DELETE FROM payment WHERE paymentID ='"+txtPaymentID.getText()+ "' and serviceID = '"+txtServiceID.getText()+ "' and reservationID = '"+txtReservationID.getText()+ "' and orderID = '"+txtOrderID.getText()+ "' and roomID = '"+txtRoomID.getText()+ "' and guestID = '"+txtGuestID.getText()+"'";
					
					payment.deleteRecord(paymentList, txtPaymentID.getText(), txtServiceID.getText(), txtReservationID.getText(), txtOrderID.getText(), txtRoomID.getText(), txtGuestID.getText());
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
		
		txtReservationID = new JTextField();
		txtReservationID.setColumns(10);
		txtReservationID.setBounds(205, 118, 121, 22);
		add(txtReservationID);
		
		txtRoomID = new JTextField();
		txtRoomID.setColumns(10);
		txtRoomID.setBounds(205, 182, 121, 22);
		add(txtRoomID);
		
		txtGuestID = new JTextField();
		txtGuestID.setColumns(10);
		txtGuestID.setBounds(205, 213, 121, 22);
		add(txtGuestID);
		
		txtPaymentStatus = new JTextField();
		txtPaymentStatus.setColumns(10);
		txtPaymentStatus.setBounds(205, 379, 121, 22);
		add(txtPaymentStatus);
		
		JLabel lblPaymentStatus = new JLabel("Payment Status:");
		lblPaymentStatus.setBounds(36, 379, 142, 16);
		add(lblPaymentStatus);
		
		txtPaymentDate = new JTextField();
		txtPaymentDate.setColumns(10);
		txtPaymentDate.setBounds(205, 345, 121, 22);
		add(txtPaymentDate);
		
		JLabel lblPaymentDate = new JLabel("Payment Date:");
		lblPaymentDate.setBounds(36, 345, 121, 16);
		add(lblPaymentDate);
		
		txtTipsAmount = new JTextField();
		txtTipsAmount.setText("0.00");
		txtTipsAmount.setColumns(10);
		txtTipsAmount.setBounds(205, 281, 121, 22);
		add(txtTipsAmount);
		
		JLabel lblTipsAmount = new JLabel("Tips Amount:");
		lblTipsAmount.setBounds(36, 284, 144, 16);
		add(lblTipsAmount);
		
		txtSubtotalAmount = new JTextField();
		txtSubtotalAmount.setText("0.00");
		txtSubtotalAmount.setColumns(10);
		txtSubtotalAmount.setBounds(205, 248, 121, 22);
		add(txtSubtotalAmount);
		
		JLabel lblSubtotalAmount = new JLabel("Subtotal Amount:");
		lblSubtotalAmount.setBounds(36, 251, 144, 16);
		add(lblSubtotalAmount);
		
		JLabel lblOrderID = new JLabel("Order ID:");
		lblOrderID.setBounds(36, 153, 76, 16);
		add(lblOrderID);
		
		JLabel lblServiceID = new JLabel("Service ID:");
		lblServiceID.setBounds(36, 89, 76, 16);
		add(lblServiceID);
		
		txtServiceID = new JTextField();
		txtServiceID.setColumns(10);
		txtServiceID.setBounds(205, 86, 121, 22);
		add(txtServiceID);
		
		txtOrderID = new JTextField();
		txtOrderID.setColumns(10);
		txtOrderID.setBounds(205, 150, 121, 22);
		add(txtOrderID);
		
		txtPaymentID = new JTextField();
		txtPaymentID.setColumns(10);
		txtPaymentID.setBounds(205, 51, 121, 22);
		add(txtPaymentID);
		
		JLabel lblPaymentID = new JLabel("Payment ID:");
		lblPaymentID.setBounds(36, 54, 76, 16);
		add(lblPaymentID);
		
		
		
		JButton btnPrintBill = new JButton("Print Bill");
		btnPrintBill.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String fileName = "";
				fileName = JOptionPane.showInputDialog("File Name to save the chart: ");
				
				File tmpDir = new File("./"+fileName + ".pdf");
				boolean exists = tmpDir.exists();
				
				while(exists == true) {
					fileName = JOptionPane.showInputDialog("Please choose another file name as the file already exist: ");
					tmpDir = new File("./"+fileName + ".pdf");
					exists = tmpDir.exists();
				}
		
				try { 
					if(!fileName.equals(null) && exists == false  ) {
						printJTable(table, fileName, selectedRow);
						JOptionPane.showMessageDialog(null, "Saved successfully!");
					}            
		        } 
		        catch(NullPointerException ne) { 
		            System.out.print("Caught NullPointerException"); 
		        }
				
			}
		});
		btnPrintBill.setBounds(79, 483, 145, 39);
		add(btnPrintBill);
		
		

		
		//display the room panel 
		displayData();			
	}


	/*
	Validates the fields in the table for empty and invalid inputs & prompts proper error message
	
	@param actionName: whether the user is inserting, updating / deleting the record
	@param ID, ..., role : All the fields that undergo the validation
	@return errCounter: if the errCounter returns 0, then there are no error encountered 
	*/
	int fieldCheck(String actionName, String paymentID, String serviceID, String reservationID, String orderID, String roomID, String guestID, String subtotalAmount, String tipsAmount, String totalAmount, String paymentDate, String paymentStatus) {
		int errCounter = 0;
		
		if(paymentID.equals("") || serviceID.equals("") || reservationID.equals("") || orderID.equals("")  || roomID.equals("") || guestID.equals("") || subtotalAmount.equals("")|| tipsAmount.equals("")  || totalAmount.equals("") || paymentDate.equals("") || paymentStatus.equals("") ) {
			JOptionPane.showMessageDialog(null, "Please fill in all the fields!");
			errCounter ++;
		}else {
			if(!payment.validateDouble(subtotalAmount) || !payment.validateDouble(tipsAmount) || !payment.validateDouble(totalAmount)) {
				JOptionPane.showMessageDialog(null, "Amounts can only have digits & decimals! ");
				errCounter ++;
			}else{
				if(actionName.equals("Insert")) {
					if(payment.isIdExist(paymentList, paymentID, serviceID, reservationID , orderID, roomID , guestID)){
						JOptionPane.showMessageDialog(null, "Failed to add new payment record as the same ID already exists!");
						errCounter ++;
					}
				}
							
				if(actionName.equals("Update")) {
					if(payment.isRecordExist(paymentList, new CRUD_2(paymentID, serviceID, reservationID, orderID, roomID, guestID, Double.parseDouble(subtotalAmount), Double.parseDouble(tipsAmount), Double.parseDouble(totalAmount), paymentDate, paymentStatus ))){
						JOptionPane.showMessageDialog(null, " Failed to update payment record as the exact record already exists!");
						errCounter ++;
					}else if(!payment.isIdExist(paymentList, paymentID, serviceID, reservationID , orderID, roomID , guestID)){
						JOptionPane.showMessageDialog(null, "Failed to update payment record as no such ID is found!");
						errCounter ++;
					}
				}
			}

			if(actionName.equals("Delete")) {
				if(!payment.isIdExist(paymentList, paymentID, serviceID, reservationID , orderID, roomID , guestID)){
					JOptionPane.showMessageDialog(null, "Failed to update payment record as no such ID is found!");
					errCounter ++;
				}
			}
		}

		return errCounter;
	}


	
	void executeSQLUpdateSubtotal() {
		String query = "UPDATE payment p, amenities_usage_log aul, services_usage_log sul, food_order fo, reservation r SET p.subtotalAmount = aul.amenityTotalPrice + sul.servicesTotalPrice + fo.orderTotalPrice + r.reservationTotalPrice;";

		
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

	               
	               System.out.println("Can Update");
	           }else{
	               System.out.println("Cannot Update");
	           }
	       }catch(Exception ex){
	           ex.printStackTrace();
	       }
	}
	
	
	void executeSQLUpdateTotal() {
		String query = "UPDATE payment SET totalAmount = subtotalAmount + tipsAmount;";

		
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

	               
	               System.out.println("Can Update");
	           }else{
	               System.out.println("Cannot Update");
	           }
	       }catch(Exception ex){
	           ex.printStackTrace();
	       }
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
		Object[] columns = {"Payment ID", "Service ID", "Reservation ID", "Order ID", "Room ID", "Guest ID", "Subtotal Amount", "Tips Amount", "Total Amount", "Payment Date", "Payment Status"  };
		
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
		
		for(int i = 0; i < 11; i++) {
				table.getColumnModel().getColumn(i).setCellRenderer(Renderer);
		}
		
		//allow the selected row values to be displayed in the JTextField
		table.addMouseListener(new MouseAdapter() {
			
			public void mouseClicked(MouseEvent e) {
				int i = table.getSelectedRow();
				
				if (table.getRowSorter()!=null) {
				    i = table.getRowSorter().convertRowIndexToModel(i);
				}
				
				txtPaymentID.setText(model.getValueAt(i, 0).toString());
				txtServiceID.setText(model.getValueAt(i, 1).toString());
				txtReservationID.setText(model.getValueAt(i, 2).toString());
				txtOrderID.setText(model.getValueAt(i, 3).toString());
				txtRoomID.setText(model.getValueAt(i, 4).toString());
				txtGuestID.setText(model.getValueAt(i, 5).toString());
				txtSubtotalAmount.setText(model.getValueAt(i, 6).toString());
				txtTipsAmount.setText(model.getValueAt(i, 7).toString());
				txtTotalAmount.setText(model.getValueAt(i, 8).toString());
				txtPaymentDate.setText(model.getValueAt(i, 9).toString());
				txtPaymentStatus.setText(model.getValueAt(i, 10).toString());
				
				selectedRow = i;
	
			}
			
		});
		
		
		
		try {
			Class.forName(db.getClassName());//the database driver 
			

			
			
			String paymentID, serviceID, reservationID, orderID, roomID, guestID, paymentDate, paymentStatus;
			double subtotalAmount, tipsAmount, totalAmount;
			
			conn = DriverManager.getConnection(db.getURL(), db.getUserName(), db.getPassword()); //connect to the database
			conn.setAutoCommit(false);//do not auto commit the SQL statements
			stmt = conn.createStatement();//prepare the create a SQL statement
			rs = stmt.executeQuery("SELECT * from payment ORDER BY LENGTH(paymentID), paymentID asc");//SQL select query to display the class type // e.roleID,
			
			//add the rows in the class type details database to the JTable
			Object[] row = new Object[11];
			while (rs.next()) { 
			
				paymentID = rs.getString(1);
				serviceID = rs.getString(2);
				reservationID = rs.getString(3);
				orderID = rs.getString(4);
				roomID = rs.getString(5);
				guestID = rs.getString(6);
				subtotalAmount = rs.getDouble(7);
				tipsAmount = rs.getDouble(8);
				totalAmount = rs.getDouble(9);
				paymentDate = rs.getString(10);
				paymentStatus = rs.getString(11);
				
				
	
	
				payment.addRecord(paymentList, new CRUD_2(paymentID, serviceID, reservationID, orderID, roomID, guestID, subtotalAmount, tipsAmount, totalAmount, paymentDate, paymentStatus ));
			
				
				row[0] = paymentID;
				row[1] = serviceID;
				row[2] = reservationID;
				row[3] = orderID;
				row[4] = roomID;
				row[5] = guestID;
				row[6] = subtotalAmount;
				row[7] = tipsAmount;
				row[8] = totalAmount;
				row[9] = paymentDate;
				row[10] = paymentStatus;
	
				
				model.addRow(row);
            } 
			payment.RowFilterTest(table, model, txtSearch);
			
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
		
		executeSQLUpdateSubtotal();
		executeSQLUpdateTotal();
		
		try {

			DefaultTableModel model = (DefaultTableModel) table.getModel();

			CRUD_2 paymentRefer ;
			
			Object[] row = new Object[11];
			

			for(int i = 0; i< payment.getCount(paymentList); i++) {
			
			
				paymentRefer = payment.getIndex(paymentList, i);
			
		
				
				row[0] = payment.getRecord(paymentList, paymentRefer.getPaymentID(), paymentRefer.getServiceID(), paymentRefer.getReservationID(), paymentRefer.getOrderID(), paymentRefer.getRoomID(), paymentRefer.getGuestID()).getPaymentID();
				row[1] = payment.getRecord(paymentList, paymentRefer.getPaymentID(), paymentRefer.getServiceID(), paymentRefer.getReservationID(), paymentRefer.getOrderID(), paymentRefer.getRoomID(), paymentRefer.getGuestID()).getServiceID();
				row[2] = payment.getRecord(paymentList, paymentRefer.getPaymentID(), paymentRefer.getServiceID(), paymentRefer.getReservationID(), paymentRefer.getOrderID(), paymentRefer.getRoomID(), paymentRefer.getGuestID()).getReservationID();
				row[3] = payment.getRecord(paymentList, paymentRefer.getPaymentID(), paymentRefer.getServiceID(), paymentRefer.getReservationID(), paymentRefer.getOrderID(), paymentRefer.getRoomID(), paymentRefer.getGuestID()).getOrderID();
				row[4] = payment.getRecord(paymentList, paymentRefer.getPaymentID(), paymentRefer.getServiceID(), paymentRefer.getReservationID(), paymentRefer.getOrderID(), paymentRefer.getRoomID(), paymentRefer.getGuestID()).getRoomID();
				row[5] = payment.getRecord(paymentList, paymentRefer.getPaymentID(), paymentRefer.getServiceID(), paymentRefer.getReservationID(), paymentRefer.getOrderID(), paymentRefer.getRoomID(), paymentRefer.getGuestID()).getGuestID();
				row[6] = payment.getRecord(paymentList, paymentRefer.getPaymentID(), paymentRefer.getServiceID(), paymentRefer.getReservationID(), paymentRefer.getOrderID(), paymentRefer.getRoomID(), paymentRefer.getGuestID()).getSubtotalAmount();
				row[7] = payment.getRecord(paymentList, paymentRefer.getPaymentID(), paymentRefer.getServiceID(), paymentRefer.getReservationID(), paymentRefer.getOrderID(), paymentRefer.getRoomID(), paymentRefer.getGuestID()).getTipsAmount();
				row[8] = payment.getRecord(paymentList, paymentRefer.getPaymentID(), paymentRefer.getServiceID(), paymentRefer.getReservationID(), paymentRefer.getOrderID(), paymentRefer.getRoomID(), paymentRefer.getGuestID()).getTotalAmount();
				row[9] = payment.getRecord(paymentList, paymentRefer.getPaymentID(), paymentRefer.getServiceID(), paymentRefer.getReservationID(), paymentRefer.getOrderID(), paymentRefer.getRoomID(), paymentRefer.getGuestID()).getPaymentDate();
				row[10] = payment.getRecord(paymentList, paymentRefer.getPaymentID(), paymentRefer.getServiceID(), paymentRefer.getReservationID(), paymentRefer.getOrderID(), paymentRefer.getRoomID(), paymentRefer.getGuestID()).getPaymentStatus();
				
				
				model.addRow(row);
			}
			
			
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
	public void printJTable(JTable table, String fileName, int row) {


    	/*
		 * 
		 * Website: Stackoverflow
		 * Title: Fitting a JTable in an iText PDF Document
		 * Author: JWizard
		 * Released Date: 11/02/15
		 * Referred Date: 14/04/19
		 * URL: https://stackoverflow.com/questions/28448377/fitting-a-jtable-in-an-itext-pdf-document
		 * 
		 * */
    	try {
            Document doc = new Document();
            PdfWriter.getInstance(doc, new FileOutputStream(fileName + ".pdf"));
            doc.open();
            

            /*
    		 * 
    		 * Website: vogella
    		 * Title: Creating PDF with Java and iText - Tutorial
    		 * Author: Lars Vogel
    		 * Released Date: 26/09/16
    		 * Referred Date: 14/04/19
    		 * URL: https://www.vogella.com/tutorials/JavaPDF/article.html
    		 * 
    		 * */
            
           /* Supplier_Interface s = new Supplier_Interface();
            Employee_Interface e = new Employee_Interface();
            Invoice_Interface in = new Invoice_Interface();
            String productID = null ;
            */
            
            Guest_Display g = new Guest_Display();
            
            Guest guest = new Guest(g.guestList);
            guest.getRecord(g.guestList, table.getModel().getValueAt(row, 4).toString());
 
            Paragraph subPara = new Paragraph("");
          /*  subPara.add(new Paragraph("Invoice ID: " + invoiceID ));
            subPara.add(new Paragraph("Supplier: " + supplierID + " ( " + s.supp.getRecord(s.suppList, supplierID).getSuppName() + " )"));
            subPara.add(new Paragraph("Employee: " +  employeeID + " ( " + e.emp.getRecord(e.empList, employeeID).getFullName() + " )"));
            subPara.add(new Paragraph("Total Price (RM) : " + in.inv.getRecord(in.invList, invoiceID).getTotalPrice()));
            subPara.add(new Paragraph("Invoice Date: " + in.inv.getRecord(in.invList, invoiceID).getDate()));*/
            subPara.add(new Paragraph("Guest: " + guest.getRecord(g.guestList, table.getModel().getValueAt(row, 5).toString()).getGuestName() ));
            subPara.add(new Paragraph("Subtotal Amount: " + table.getModel().getValueAt(row, 6).toString() ));
            subPara.add(new Paragraph("Tips Amount: " + table.getModel().getValueAt(row, 7).toString() ));
            subPara.add(new Paragraph("Total Amount: " + table.getModel().getValueAt(row, 8).toString() ));
        
            subPara.add(new Paragraph(""));

            doc.add(subPara);
            

            doc.close();
            System.out.println("done");
        } catch (DocumentException ex) {
        	 System.out.print("Caught DocumentException"); 
        } catch (FileNotFoundException ex) {
        	System.out.print("Caught FileNotFoundException"); 
        }
    }
}
