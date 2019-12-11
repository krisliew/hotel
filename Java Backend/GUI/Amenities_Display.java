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
import CRUD.Amenities;

import javax.swing.SwingConstants;
import javax.swing.ComboBoxModel;
import javax.swing.JTextArea;


public class Amenities_Display extends JPanel {

	private JTextField txtSearch;
	private JTextField txtAmenityID;
	private JTextField txtAmenityName;
	private JTable table;
	private JTextField txtOperationStartTime;
	private JTextField txtOperationEndTime;
	


	
	ArrayList<CRUD_1> amenityList = new ArrayList<CRUD_1>();


	Amenities amenity = new Amenities(amenityList);
	ConnectDatabase db = new ConnectDatabase();

	CRUD_1 amenities ;
	private JTextField txtAmenityDescription;
	private JTextField txtAmenityPrice;
	private JTextField txtAmenityRules;

	
	
	/**
	 * Create the panel.
	 */
	public Amenities_Display() {

		JPanel panel = new JPanel();
    	panel.setBackground(Color.BLACK);
   	 	panel.setLayout(null);
		setLayout(null);
		
		
		
		JLabel lblAmenityID = new JLabel("Amenity ID:");
		lblAmenityID.setBounds(36, 107, 76, 16);
		add(lblAmenityID);
		
		JLabel lblGuestAddress = new JLabel("Amenity Description:");
		lblGuestAddress.setBounds(36, 173, 132, 16);
		add(lblGuestAddress);
		
		JLabel lblAmenityName = new JLabel("Amenity Name:");
		lblAmenityName.setBounds(36, 138, 102, 16);
		add(lblAmenityName);

		
		
		txtAmenityID = new JTextField();
		txtAmenityID.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char keyChar = e.getKeyChar();
			    if (Character.isLowerCase(keyChar)) {
			      e.setKeyChar(Character.toUpperCase(keyChar));
			    }
			}
		});
		txtAmenityID.setBounds(180, 106, 121, 22);
		add(txtAmenityID);
		txtAmenityID.setColumns(10);
		
		txtAmenityName = new JTextField();
		txtAmenityName.setColumns(10);
		txtAmenityName.setBounds(180, 137, 121, 22);
		add(txtAmenityName);


		
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

				if(fieldCheck("Insert", txtAmenityID.getText(), txtAmenityName.getText() , txtAmenityDescription.getText() , txtAmenityPrice.getText()  ,txtOperationStartTime.getText(),txtOperationEndTime.getText(), txtAmenityRules.getText() ) == 0) {
					
					String query = "INSERT INTO amenities VALUES ('" +txtAmenityID.getText()+ "','" +txtAmenityName.getText()+ "','" +txtAmenityDescription.getText()+ "','" +txtAmenityPrice.getText()+ "','" +txtOperationStartTime.getText()+ "','" + txtOperationEndTime.getText() + "','" + txtAmenityRules.getText() +"')";
						
					amenity.addRecord(amenityList, new CRUD_1(txtAmenityID.getText(), txtAmenityName.getText(), txtAmenityDescription.getText(), Double.parseDouble(txtAmenityPrice.getText()),  txtOperationStartTime.getText(),txtOperationEndTime.getText(), txtAmenityRules.getText()));
					
						executeSQL(query, "Inserted");

				}

			}
		});
		btnAdd.setBounds(22, 381, 86, 39);
		add(btnAdd);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				if(fieldCheck("Update", txtAmenityID.getText(), txtAmenityName.getText() , txtAmenityDescription.getText() , txtAmenityPrice.getText()  ,txtOperationStartTime.getText(),txtOperationEndTime.getText(), txtAmenityRules.getText() ) == 0) {
						
					String query = "UPDATE amenities SET amenityName = '" +txtAmenityName.getText()+ "', amenityDescription ='" +txtAmenityDescription.getText()+ "', amenityPrice ='" +Double.parseDouble(txtAmenityPrice.getText())+ "', amenityOperationStartTime ='" +txtOperationStartTime.getText()+ "', amenityOperationEndTime ='" +txtOperationEndTime.getText()+ "', amenityRules ='" +txtAmenityRules.getText()+ "' WHERE amenityID ='"+txtAmenityID.getText()+"'";
					
					
					amenity.setRecord(amenityList, new CRUD_1(txtAmenityID.getText(), txtAmenityName.getText(), txtAmenityDescription.getText(), Double.parseDouble(txtAmenityPrice.getText()),  txtOperationStartTime.getText(),txtOperationEndTime.getText(), txtAmenityRules.getText()));
					executeSQL(query, "Updated");
				}
			}
		});
		btnUpdate.setBounds(120, 381, 86, 39);
		add(btnUpdate);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(fieldCheck("Delete", txtAmenityID.getText(), txtAmenityName.getText() , txtAmenityDescription.getText() , txtAmenityPrice.getText()  ,txtOperationStartTime.getText(),txtOperationEndTime.getText(), txtAmenityRules.getText() ) == 0) {
					
					
					String query = "DELETE FROM amenities WHERE amenityID ='"+txtAmenityID.getText()+"'";
					amenity.deleteRecord(amenityList, txtAmenityID.getText());
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
		
		JLabel lblAmenityPrice = new JLabel("Amenity Price:");
		lblAmenityPrice.setBounds(36, 203, 115, 16);
		add(lblAmenityPrice);
		
		txtOperationStartTime = new JTextField();
		txtOperationStartTime.setColumns(10);
		txtOperationStartTime.setBounds(180, 230, 121, 22);
		add(txtOperationStartTime);
		
		JLabel lblOperationStartTime = new JLabel("Operation Start Time:");
		lblOperationStartTime.setBounds(36, 233, 142, 16);
		add(lblOperationStartTime);
		
		txtOperationEndTime = new JTextField();
		txtOperationEndTime.setColumns(10);
		txtOperationEndTime.setBounds(180, 266, 121, 22);
		add(txtOperationEndTime);
		
		txtAmenityDescription = new JTextField();
		txtAmenityDescription.setColumns(10);
		txtAmenityDescription.setBounds(180, 170, 121, 22);
		add(txtAmenityDescription);
		
		JLabel lblOperationEndTime = new JLabel("Operation End Time:");
		lblOperationEndTime.setBounds(36, 269, 142, 16);
		add(lblOperationEndTime);
		
		txtAmenityPrice = new JTextField();
		txtAmenityPrice.setText("0.00");
		txtAmenityPrice.setColumns(10);
		txtAmenityPrice.setBounds(180, 200, 121, 22);
		add(txtAmenityPrice);
		
		JLabel lblAmenityRules = new JLabel("Amenity Rules:");
		lblAmenityRules.setBounds(36, 304, 132, 16);
		add(lblAmenityRules);
		
		txtAmenityRules = new JTextField();
		txtAmenityRules.setColumns(10);
		txtAmenityRules.setBounds(180, 301, 121, 22);
		add(txtAmenityRules);

		
		//display the amenity panel 
		displayData();			
	}


	/*
	Validates the fields in the table for empty and invalid inputs & prompts proper error message
	
	@param actionName: whether the user is inserting, updating / deleting the record
	@param ID, ..., role : All the fields that undergo the validation
	@return errCounter: if the errCounter returns 0, then there are no error encountered 
	*/
	int fieldCheck(String actionName, String amenityID, String amenityName, String amenityDescription, String amenityPrice, String amenityOperationStartTime, String amenityOperationEndTime, String amenityRules) {
		int errCounter = 0;
		
		if(amenityID.equals("") || amenityName.equals("") || amenityDescription.equals("") || amenityPrice.equals("")  || amenityOperationStartTime.equals("") || amenityOperationEndTime.equals("") || amenityRules.equals("")) {
			JOptionPane.showMessageDialog(null, "Please fill in all the fields!");
			errCounter ++;
		}else {
			if(!amenity.validateDouble(amenityPrice)) {
				JOptionPane.showMessageDialog(null, "Prices can only have digits & decimals! ");
				errCounter ++;
			}else{
				if(actionName.equals("Insert")) {
					if(amenity.isIdExist(amenityList, amenityID)){
						JOptionPane.showMessageDialog(null, "Failed to add new amenity record as the same ID already exists!");
						errCounter ++;
					}
				}
							
				if(actionName.equals("Update")) {
					if(amenity.isRecordExist(amenityList, new CRUD_1(amenityID, amenityName, amenityDescription, Double.parseDouble(amenityPrice), amenityOperationStartTime, amenityOperationEndTime, amenityRules))){
						JOptionPane.showMessageDialog(null, " Failed to update amenity record as the exact record already exists!");
						errCounter ++;
					}else if(!amenity.isIdExist(amenityList, amenityID)) {
						JOptionPane.showMessageDialog(null, "Failed to update amenity record as no such ID is found!");
						errCounter ++;
					}
				}
			}

			if(actionName.equals("Delete")) {
				if(!amenity.isIdExist(amenityList, amenityID)) {
					JOptionPane.showMessageDialog(null, "Failed to update amenity record as no such ID is found!");
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
		Object[] columns = {"Amenity ID", "Amenity Name", "Amenity Description", "Amenity Price", "Operation Start Time", "Operation End Time", "Remarks" };
		
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
				
				txtAmenityID.setText(model.getValueAt(i, 0).toString());
				txtAmenityName.setText(model.getValueAt(i, 1).toString());
				txtAmenityDescription.setText(model.getValueAt(i, 2).toString());
				txtAmenityPrice.setText(model.getValueAt(i, 3).toString());
				txtOperationStartTime.setText(model.getValueAt(i, 4).toString());
				txtOperationEndTime.setText(model.getValueAt(i, 5).toString());
				txtAmenityRules.setText(model.getValueAt(i, 6).toString());
	
			}
			
		});
		
		
		
		try {
			Class.forName(db.getClassName());//the database driver 
			
			String amenityID, amenityName, amenityDescription, amenityOperationStartTime, amenityOperationEndTime, amenityRules ;
			double amenityPrice;
			
			conn = DriverManager.getConnection(db.getURL(), db.getUserName(), db.getPassword()); //connect to the database
			conn.setAutoCommit(false);//do not auto commit the SQL statements
			stmt = conn.createStatement();//prepare the create a SQL statement
			rs = stmt.executeQuery("SELECT * from amenities ORDER BY LENGTH(amenityID), amenityID asc");//SQL select query to display the class type // e.roleID,
			
			//add the rows in the class type details database to the JTable
			Object[] row = new Object[7];
			while (rs.next()) { 
		
				
				amenityID = rs.getString(1);
				amenityName = rs.getString(2);
				amenityDescription = rs.getString(3);
				amenityPrice = rs.getDouble(4); 
				amenityOperationStartTime = rs.getString(5);
				amenityOperationEndTime = rs.getString(6);
				amenityRules = rs.getString(7);
	
				
	
	
				amenity.addRecord(amenityList, new CRUD_1(amenityID, amenityName, amenityDescription, amenityPrice, amenityOperationStartTime, amenityOperationEndTime, amenityRules ));
			
				
				row[0] = amenityID;
				row[1] = amenityName;
				row[2] = amenityDescription;
				row[3] = amenityPrice;
				row[4] = amenityOperationStartTime;
				row[5] = amenityOperationEndTime;
				row[6] = amenityRules;
		
				
				model.addRow(row);
            } 
			amenity.RowFilterTest(table, model, txtSearch);
			
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

			CRUD_1 amenityRefer ;
			
			Object[] row = new Object[15];
			

			for(int i = 0; i< amenity.getCount(amenityList); i++) {
			
			
				amenityRefer = amenity.getIndex(amenityList, i);
			

				row[0] = amenity.getRecord(amenityList, amenityRefer.getAmenityID()).getAmenityID();
				row[1] = amenity.getRecord(amenityList, amenityRefer.getAmenityID()).getAmenityName();
				row[2] = amenity.getRecord(amenityList, amenityRefer.getAmenityID()).getAmenityDescription();
				row[3] = amenity.getRecord(amenityList, amenityRefer.getAmenityID()).getAmenityPrice();
				row[4] = amenity.getRecord(amenityList, amenityRefer.getAmenityID()).getAmenityOperationStartTime();
				row[5] = amenity.getRecord(amenityList, amenityRefer.getAmenityID()).getAmenityOperationEndTime();
				row[6] = amenity.getRecord(amenityList, amenityRefer.getAmenityID()).getAmenityRules();


				model.addRow(row);
			}

		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
}
