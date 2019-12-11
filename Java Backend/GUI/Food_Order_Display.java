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
import CRUD.Food_Order;





public class Food_Order_Display extends JPanel {

	private JTextField txtSearch;
	private JTable table;
	private JTextField txtOrderID;
	private JTextField txtRoomID;
	private JTextField txtGuestID;
	private JTextField txtOrderTotalPrice;
	private JTextField txtQuantity;
	private JTextField txtRemarks;
	private JTextField txtMenuID;


	
	ArrayList<CRUD_1> foodOrderList = new ArrayList<CRUD_1>();


	Food_Order foodOrder = new Food_Order(foodOrderList);
	ConnectDatabase db = new ConnectDatabase();
	
	CRUD_1 foodOrders ;
	

	
	
	/**
	 * Create the panel.
	 */
	public Food_Order_Display() {

		JPanel panel = new JPanel();
    	panel.setBackground(Color.BLACK);
   	 	panel.setLayout(null);
		setLayout(null);
		
		
		
		JLabel lblRoomID = new JLabel("Room ID:");
		lblRoomID.setBounds(36, 168, 76, 16);
		add(lblRoomID);
		
		JLabel lblGuestID = new JLabel("Guest ID:");
		lblGuestID.setBounds(36, 199, 76, 16);
		add(lblGuestID);
		
		JLabel lblOrderTotalPrice = new JLabel("Order Total Price:");
		lblOrderTotalPrice.setBounds(36, 260, 144, 16);
		add(lblOrderTotalPrice);
		
		JLabel lblOrderID = new JLabel("Order ID:");
		lblOrderID.setBounds(36, 106, 121, 16);
		add(lblOrderID);
		
		txtOrderTotalPrice = new JTextField();
		txtOrderTotalPrice.setText("0.00");
		txtOrderTotalPrice.setColumns(10);
		txtOrderTotalPrice.setBounds(170, 257, 121, 22);
		add(txtOrderTotalPrice);


		
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

				if(fieldCheck("Insert", txtOrderID.getText(), txtMenuID.getText(), txtRoomID.getText(), txtGuestID.getText(),  txtQuantity.getText(),txtOrderTotalPrice.getText(),txtRemarks.getText()) == 0) {
					
					String query = "INSERT INTO food_order VALUES ('" +txtOrderID.getText()+ "','" +txtMenuID.getText()+ "','" +txtRoomID.getText()+ "','" +txtGuestID.getText()+ "','" + txtQuantity.getText()+ "','" + txtOrderTotalPrice.getText()+ "','" + txtRemarks.getText() + "')";
						
					foodOrder.addRecord(foodOrderList, new CRUD_1(txtOrderID.getText(), txtMenuID.getText(), txtRoomID.getText(), txtGuestID.getText(),  Integer.parseInt(txtQuantity.getText()),Double.parseDouble(txtOrderTotalPrice.getText()),txtRemarks.getText()));
					
						executeSQL(query, "Inserted");

				}

			}
		});
		btnAdd.setBounds(12, 427, 86, 39);
		add(btnAdd);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				if(fieldCheck("Update", txtOrderID.getText(), txtMenuID.getText(), txtRoomID.getText(), txtGuestID.getText(),  txtQuantity.getText(),txtOrderTotalPrice.getText(),txtRemarks.getText()) == 0) {
						
					String query = "UPDATE food_order SET quantity = '" +txtQuantity.getText()+"', orderTotalPrice ='" +txtOrderTotalPrice.getText()+"', remarks ='" +txtRemarks.getText()+"' WHERE orderID ='"+txtOrderID.getText()+ "' and menuID = '"+txtMenuID.getText()+ "' and roomID = '"+txtRoomID.getText()+ "' and guestID = '"+txtGuestID.getText()+"'";
					
					
					foodOrder.setRecord(foodOrderList, new CRUD_1(txtOrderID.getText(), txtMenuID.getText(), txtRoomID.getText(), txtGuestID.getText(),  Integer.parseInt(txtQuantity.getText()),Double.parseDouble(txtOrderTotalPrice.getText()),txtRemarks.getText()));
					
					executeSQL(query, "Updated");
				}
			}
		});
		btnUpdate.setBounds(110, 427, 86, 39);
		add(btnUpdate);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(fieldCheck("Delete", txtOrderID.getText(), txtMenuID.getText(), txtRoomID.getText(), txtGuestID.getText(),  txtQuantity.getText(),txtOrderTotalPrice.getText(),txtRemarks.getText()) == 0) {
					
					String query = "DELETE FROM food_order WHERE orderID ='"+txtOrderID.getText()+ "' and menuID = '"+txtMenuID.getText()+ "' and roomID = '"+txtRoomID.getText()+ "' and guestID = '"+txtGuestID.getText()+"'";
					
					foodOrder.deleteRecord(foodOrderList, txtOrderID.getText(),txtMenuID.getText(),txtRoomID.getText(),txtGuestID.getText());
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
		
		JLabel lblQuantity = new JLabel("Quantity:");
		lblQuantity.setBounds(36, 228, 142, 16);
		add(lblQuantity);
		
		txtQuantity = new JTextField();
		txtQuantity.setColumns(10);
		txtQuantity.setBounds(170, 228, 121, 22);
		add(txtQuantity);
		
		JLabel lblRemarks = new JLabel("Remarks:");
		lblRemarks.setBounds(36, 290, 160, 16);
		add(lblRemarks);
		
		txtRemarks = new JTextField();
		txtRemarks.setColumns(10);
		txtRemarks.setBounds(170, 290, 121, 22);
		add(txtRemarks);
		
		txtOrderID = new JTextField();
		txtOrderID.setColumns(10);
		txtOrderID.setBounds(170, 103, 121, 22);
		add(txtOrderID);
		
		txtRoomID = new JTextField();
		txtRoomID.setColumns(10);
		txtRoomID.setBounds(170, 165, 121, 22);
		add(txtRoomID);
		
		txtGuestID = new JTextField();
		txtGuestID.setColumns(10);
		txtGuestID.setBounds(170, 196, 121, 22);
		add(txtGuestID);
		
		txtMenuID = new JTextField();
		txtMenuID.setColumns(10);
		txtMenuID.setBounds(170, 133, 121, 22);
		add(txtMenuID);
		
		JLabel lblMenuID = new JLabel("Menu ID:");
		lblMenuID.setBounds(36, 136, 121, 16);
		add(lblMenuID);
		
		

		
		//display the room panel 
		displayData();			
	}


	/*
	Validates the fields in the table for empty and invalid inputs & prompts proper error message
	
	@param actionName: whether the user is inserting, updating / deleting the record
	@param ID, ..., role : All the fields that undergo the validation
	@return errCounter: if the errCounter returns 0, then there are no error encountered 
	*/
	int fieldCheck(String actionName, String orderID, String menuID, String roomID, String guestID, String quantity, String orderTotalPrice, String remarks) {
		int errCounter = 0;
		
		if(orderID.equals("") || menuID.equals("") || roomID.equals("") || guestID.equals("") || quantity.equals("")  || orderTotalPrice.equals("") || remarks.equals("")) {
			JOptionPane.showMessageDialog(null, "Please fill in all the fields!");
			errCounter ++;
		}else {
			if(!foodOrder.validateDouble(orderTotalPrice) || !foodOrder.validateInt(quantity)) {
				JOptionPane.showMessageDialog(null, "Quantity can only have digits and Price can only have digits & decimals! ");
				errCounter ++;
			}else{
				if(actionName.equals("Insert")) {
					if(foodOrder.isIdExist(foodOrderList, orderID, menuID, roomID, guestID)){
						JOptionPane.showMessageDialog(null, "Failed to add new food order record as the same ID already exists!");
						errCounter ++;
					}
				}
							
				if(actionName.equals("Update")) {
//					if(foodOrder.isRecordExist(foodOrderList, new CRUD_1(orderID, menuID, roomID, guestID, Integer.parseInt(quantity), Double.parseDouble(orderTotalPrice), remarks ))){
//						JOptionPane.showMessageDialog(null, " Failed to update food order record as the exact record already exists!");
//						errCounter ++;
//					}else if(!foodOrder.isIdExist(foodOrderList, orderID, menuID, roomID, guestID)){
//						JOptionPane.showMessageDialog(null, "Failed to update food order record as no such ID is found!");
//						errCounter ++;
//					}
				}
			}

			if(actionName.equals("Delete")) {
				if(!foodOrder.isIdExist(foodOrderList, orderID, menuID, roomID, guestID)){
					JOptionPane.showMessageDialog(null, "Failed to update food order record as no such ID is found!");
					errCounter ++;
				}
			}
		}

		return errCounter;
	}


	
	void executeSQLUpdatePrice() {
		String query = "UPDATE food_order fo, menu m SET fo.orderTotalPrice = m.menuPrice * fo.quantity;";

		
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
		Object[] columns = {"Order ID", "Menu ID", "Room ID", "Guest ID", "Quantity", "Order Total", "Remarks"  };
		
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
				
				txtOrderID.setText(model.getValueAt(i, 0).toString());
				txtMenuID.setText(model.getValueAt(i, 1).toString());
				txtRoomID.setText(model.getValueAt(i, 2).toString());
				txtGuestID.setText(model.getValueAt(i, 3).toString());
				txtQuantity.setText(model.getValueAt(i, 4).toString());
				txtOrderTotalPrice.setText(model.getValueAt(i, 5).toString());
				txtRemarks.setText(model.getValueAt(i, 6).toString());
				
			}
			
		});
		
		
		
		try {
			Class.forName(db.getClassName());//the database driver 
			
			
			String orderID, menuID, roomID, guestID, remarks ;
			int quantity;
			double orderTotalPrice;
			
			conn = DriverManager.getConnection(db.getURL(), db.getUserName(), db.getPassword()); //connect to the database
			conn.setAutoCommit(false);//do not auto commit the SQL statements
			stmt = conn.createStatement();//prepare the create a SQL statement
			rs = stmt.executeQuery("SELECT * from food_order ORDER BY LENGTH(orderID), orderID asc");//SQL select query to display the class type // e.roleID,

			int i = 0;
			//add the rows in the class type details database to the JTable
			Object[] row = new Object[7];
			while (rs.next()) { 
			
				orderID = rs.getString(1);
				menuID = rs.getString(2);
				roomID = rs.getString(3);
				guestID = rs.getString(4);
				quantity = rs.getInt(5);
				orderTotalPrice = rs.getDouble(6);
				remarks = rs.getString(7);

	
				
				foodOrder.addRecord(foodOrderList, new CRUD_1(orderID, menuID, roomID, guestID, quantity, orderTotalPrice, remarks ));

				
				row[0] = orderID;
				row[1] = menuID;
				row[2] = roomID;
				row[3] = guestID;
				row[4] = quantity;
				row[5] = orderTotalPrice;
				row[6] = remarks;
	
				
				model.addRow(row);
            } 
			foodOrder.RowFilterTest(table, model, txtSearch);
			
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
		
		executeSQLUpdatePrice();
		
		try {
			
			

			DefaultTableModel model = (DefaultTableModel) table.getModel();

			CRUD_1 foodOrderRefer ;
			
			Object[] row = new Object[7];
			

			for(int i = 0; i< foodOrder.getCount(foodOrderList); i++) {
			
			
				foodOrderRefer = foodOrder.getIndex(foodOrderList, i);
			
			
				
				row[0] = foodOrder.getRecord(foodOrderList, foodOrderRefer.getOrderID(), foodOrderRefer.getMenuID(), foodOrderRefer.getRoomID(), foodOrderRefer.getGuestID()).getOrderID();
				row[1] = foodOrder.getRecord(foodOrderList, foodOrderRefer.getOrderID(), foodOrderRefer.getMenuID(), foodOrderRefer.getRoomID(), foodOrderRefer.getGuestID()).getMenuID();
				row[2] = foodOrder.getRecord(foodOrderList, foodOrderRefer.getOrderID(), foodOrderRefer.getMenuID(), foodOrderRefer.getRoomID(), foodOrderRefer.getGuestID()).getRoomID();
				row[3] = foodOrder.getRecord(foodOrderList, foodOrderRefer.getOrderID(), foodOrderRefer.getMenuID(), foodOrderRefer.getRoomID(), foodOrderRefer.getGuestID()).getGuestID();
				row[4] = foodOrder.getRecord(foodOrderList, foodOrderRefer.getOrderID(), foodOrderRefer.getMenuID(), foodOrderRefer.getRoomID(), foodOrderRefer.getGuestID()).getQuantity();
				row[5] = foodOrder.getRecord(foodOrderList, foodOrderRefer.getOrderID(), foodOrderRefer.getMenuID(), foodOrderRefer.getRoomID(), foodOrderRefer.getGuestID()).getOrderTotalPrice();
				row[6] = foodOrder.getRecord(foodOrderList, foodOrderRefer.getOrderID(), foodOrderRefer.getMenuID(), foodOrderRefer.getRoomID(), foodOrderRefer.getGuestID()).getRemarks();
		
				
				model.addRow(row);
			}
			
			
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
}
