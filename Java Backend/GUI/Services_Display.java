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
import CRUD.Services;

import javax.swing.SwingConstants;
import javax.swing.ComboBoxModel;
import javax.swing.JTextArea;


public class Services_Display extends JPanel {

	private JTextField txtSearch;
	private JTextField txtServiceID;
	private JTextField txtServiceName;
	private JTable table;
	private JTextField txtOperationStartTime;
	private JTextField txtOperationEndTime;
	private JTextField txtServiceDescription;
	private JTextField txtServicePrice;
	private JTextField txtServiceRules;


	
	ArrayList<CRUD_2> serviceList = new ArrayList<CRUD_2>();


	Services service = new Services(serviceList);
	ConnectDatabase db = new ConnectDatabase();

	CRUD_2 services ;
	

	
	
	/**
	 * Create the panel.
	 */
	public Services_Display() {

		JPanel panel = new JPanel();
    	panel.setBackground(Color.BLACK);
   	 	panel.setLayout(null);
		setLayout(null);
		
		
		
		JLabel lblServiceID = new JLabel("Service ID:");
		lblServiceID.setBounds(36, 107, 76, 16);
		add(lblServiceID);
		
		JLabel lblGuestAddress = new JLabel("Service Description:");
		lblGuestAddress.setBounds(36, 173, 132, 16);
		add(lblGuestAddress);
		
		JLabel lblServiceName = new JLabel("Service Name:");
		lblServiceName.setBounds(36, 138, 102, 16);
		add(lblServiceName);

		
		
		txtServiceID = new JTextField();
		txtServiceID.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char keyChar = e.getKeyChar();
			    if (Character.isLowerCase(keyChar)) {
			      e.setKeyChar(Character.toUpperCase(keyChar));
			    }
			}
		});
		txtServiceID.setBounds(180, 106, 121, 22);
		add(txtServiceID);
		txtServiceID.setColumns(10);
		
		txtServiceName = new JTextField();
		txtServiceName.setColumns(10);
		txtServiceName.setBounds(180, 137, 121, 22);
		add(txtServiceName);


		
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

				if(fieldCheck("Insert", txtServiceID.getText(), txtServiceName.getText() , txtServiceDescription.getText() , txtServicePrice.getText()  ,txtOperationStartTime.getText(),txtOperationEndTime.getText(), txtServiceRules.getText() ) == 0) {
					
					String query = "INSERT INTO services VALUES ('" +txtServiceID.getText()+ "','" +txtServiceName.getText()+ "','" +txtServiceDescription.getText()+ "','" +txtServicePrice.getText()+ "','" +txtOperationStartTime.getText()+ "','" + txtOperationEndTime.getText() + "','" + txtServiceRules.getText() +"')";
						
					service.addRecord(serviceList, new CRUD_2(txtServiceID.getText(), txtServiceName.getText(), txtServiceDescription.getText(), Double.parseDouble(txtServicePrice.getText()),  txtOperationStartTime.getText(),txtOperationEndTime.getText(), txtServiceRules.getText()));
					
						executeSQL(query, "Inserted");

				}

			}
		});
		btnAdd.setBounds(22, 381, 86, 39);
		add(btnAdd);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				if(fieldCheck("Update", txtServiceID.getText(), txtServiceName.getText() , txtServiceDescription.getText() , txtServicePrice.getText()  ,txtOperationStartTime.getText(),txtOperationEndTime.getText(), txtServiceRules.getText() ) == 0) {
						
					String query = "UPDATE services SET serviceName = '" +txtServiceName.getText()+ "', serviceDescription ='" +txtServiceDescription.getText()+ "', servicePrice ='" +Double.parseDouble(txtServicePrice.getText())+ "', serviceOperationStartTime ='" +txtOperationStartTime.getText()+ "', serviceOperationEndTime ='" +txtOperationEndTime.getText()+ "', serviceRules ='" +txtServiceRules.getText()+ "' WHERE serviceID ='"+txtServiceID.getText()+"'";
					
					
					service.setRecord(serviceList, new CRUD_2(txtServiceID.getText(), txtServiceName.getText(), txtServiceDescription.getText(), Double.parseDouble(txtServicePrice.getText()),  txtOperationStartTime.getText(),txtOperationEndTime.getText(), txtServiceRules.getText()));
					executeSQL(query, "Updated");
				}
			}
		});
		btnUpdate.setBounds(120, 381, 86, 39);
		add(btnUpdate);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(fieldCheck("Delete", txtServiceID.getText(), txtServiceName.getText() , txtServiceDescription.getText() , txtServicePrice.getText()  ,txtOperationStartTime.getText(),txtOperationEndTime.getText(), txtServiceRules.getText() ) == 0) {
					
					
					String query = "DELETE FROM services WHERE serviceID ='"+txtServiceID.getText()+"'";
					service.deleteRecord(serviceList, txtServiceID.getText());
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
		
		JLabel lblServicePrice = new JLabel("Service Price:");
		lblServicePrice.setBounds(36, 203, 115, 16);
		add(lblServicePrice);
		
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
		
		txtServiceDescription = new JTextField();
		txtServiceDescription.setColumns(10);
		txtServiceDescription.setBounds(180, 170, 121, 22);
		add(txtServiceDescription);
		
		JLabel lblOperationEndTime = new JLabel("Operation End Time:");
		lblOperationEndTime.setBounds(36, 269, 142, 16);
		add(lblOperationEndTime);
		
		txtServicePrice = new JTextField();
		txtServicePrice.setText("0.00");
		txtServicePrice.setColumns(10);
		txtServicePrice.setBounds(180, 200, 121, 22);
		add(txtServicePrice);
		
		JLabel lblServiceRules = new JLabel("Service Rules:");
		lblServiceRules.setBounds(36, 304, 132, 16);
		add(lblServiceRules);
		
		txtServiceRules = new JTextField();
		txtServiceRules.setColumns(10);
		txtServiceRules.setBounds(180, 301, 121, 22);
		add(txtServiceRules);

		
		//display the service panel 
		displayData();			
	}


	/*
	Validates the fields in the table for empty and invalid inputs & prompts proper error message
	
	@param actionName: whether the user is inserting, updating / deleting the record
	@param ID, ..., role : All the fields that undergo the validation
	@return errCounter: if the errCounter returns 0, then there are no error encountered 
	*/
	int fieldCheck(String actionName, String serviceID, String serviceName, String serviceDescription, String servicePrice, String serviceOperationStartTime, String serviceOperationEndTime, String serviceRules) {
		int errCounter = 0;
		
		if(serviceID.equals("") || serviceName.equals("") || serviceDescription.equals("") || servicePrice.equals("")  || serviceOperationStartTime.equals("") || serviceOperationEndTime.equals("") || serviceRules.equals("")) {
			JOptionPane.showMessageDialog(null, "Please fill in all the fields!");
			errCounter ++;
		}else {
			if(!service.validateDouble(servicePrice)) {
				JOptionPane.showMessageDialog(null, "Prices can only have digits & decimals! ");
				errCounter ++;
			}else{
				if(actionName.equals("Insert")) {
					if(service.isIdExist(serviceList, serviceID)){
						JOptionPane.showMessageDialog(null, "Failed to add new service record as the same ID already exists!");
						errCounter ++;
					}
				}
							
				if(actionName.equals("Update")) {
					if(service.isRecordExist(serviceList, new CRUD_2(serviceID, serviceName, serviceDescription, Double.parseDouble(servicePrice), serviceOperationStartTime, serviceOperationEndTime, serviceRules))){
						JOptionPane.showMessageDialog(null, " Failed to update service record as the exact record already exists!");
						errCounter ++;
					}else if(!service.isIdExist(serviceList, serviceID)) {
						JOptionPane.showMessageDialog(null, "Failed to update service record as no such ID is found!");
						errCounter ++;
					}
				}
			}

			if(actionName.equals("Delete")) {
				if(!service.isIdExist(serviceList, serviceID)) {
					JOptionPane.showMessageDialog(null, "Failed to update service record as no such ID is found!");
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
		Object[] columns = {"Service ID", "Service Name", "Service Description", "Service Price", "Operation Start Time", "Operation End Time", "Remarks" };
		
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
				
				txtServiceID.setText(model.getValueAt(i, 0).toString());
				txtServiceName.setText(model.getValueAt(i, 1).toString());
				txtServiceDescription.setText(model.getValueAt(i, 2).toString());
				txtServicePrice.setText(model.getValueAt(i, 3).toString());
				txtOperationStartTime.setText(model.getValueAt(i, 4).toString());
				txtOperationEndTime.setText(model.getValueAt(i, 5).toString());
				txtServiceRules.setText(model.getValueAt(i, 6).toString());
	
			}
			
		});
		
		
		
		try {
			Class.forName(db.getClassName());//the database driver 
			
			String serviceID, serviceName, serviceDescription, serviceOperationStartTime, serviceOperationEndTime, serviceRules ;
			double servicePrice;
			
			conn = DriverManager.getConnection(db.getURL(), db.getUserName(), db.getPassword()); //connect to the database
			conn.setAutoCommit(false);//do not auto commit the SQL statements
			stmt = conn.createStatement();//prepare the create a SQL statement
			rs = stmt.executeQuery("SELECT * from services ORDER BY LENGTH(serviceID), serviceID asc");//SQL select query to display the class type // e.roleID,
			
			//add the rows in the class type details database to the JTable
			Object[] row = new Object[7];
			while (rs.next()) { 
		
				
				serviceID = rs.getString(1);
				serviceName = rs.getString(2);
				serviceDescription = rs.getString(3);
				servicePrice = rs.getDouble(4); 
				serviceOperationStartTime = rs.getString(5);
				serviceOperationEndTime = rs.getString(6);
				serviceRules = rs.getString(7);
	
				
	
	
				service.addRecord(serviceList, new CRUD_2(serviceID, serviceName, serviceDescription, servicePrice, serviceOperationStartTime, serviceOperationEndTime, serviceRules ));
			
				
				row[0] = serviceID;
				row[1] = serviceName;
				row[2] = serviceDescription;
				row[3] = servicePrice;
				row[4] = serviceOperationStartTime;
				row[5] = serviceOperationEndTime;
				row[6] = serviceRules;
		
				
				model.addRow(row);
            } 
			service.RowFilterTest(table, model, txtSearch);
			
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

			CRUD_2 serviceRefer ;
			
			Object[] row = new Object[15];
			

			for(int i = 0; i< service.getCount(serviceList); i++) {
			
			
				serviceRefer = service.getIndex(serviceList, i);
			

				row[0] = service.getRecord(serviceList, serviceRefer.getServiceID()).getServiceID();
				row[1] = service.getRecord(serviceList, serviceRefer.getServiceID()).getServiceName();
				row[2] = service.getRecord(serviceList, serviceRefer.getServiceID()).getServiceDescription();
				row[3] = service.getRecord(serviceList, serviceRefer.getServiceID()).getServicePrice();
				row[4] = service.getRecord(serviceList, serviceRefer.getServiceID()).getServiceOperationStartTime();
				row[5] = service.getRecord(serviceList, serviceRefer.getServiceID()).getServiceOperationEndTime();
				row[6] = service.getRecord(serviceList, serviceRefer.getServiceID()).getServiceRules();


				model.addRow(row);
			}

		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
}
