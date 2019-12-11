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
import CRUD.Room;

import javax.swing.SwingConstants;
import javax.swing.ComboBoxModel;


public class Room_Display extends JPanel {

	private JTextField txtSearch;
	private JTextField txtRoomID;
	private JTextField txtRoomAvailability;
	private JTextField txtRoomName;
	private JTable table;
	private JComboBox cbRoomTypeID;
	private JTextField txtLeasingPrice;
	private JTextField txtEmpCode;
	private JTextField textField;
	private JTextField txtRoomCapacityAdult;
	private JTextField txtRoomCapacityChild;
	private JTextField txtBedQuantityKing;
	private JTextField txtBedQuantityQueen;
	private JTextField txtBedQuantitySingle;
	private JTextField txtRentingPrice;
	private JComboBox cbSmoking;
	private JComboBox cbShower;
	private JComboBox cbWifi;
	private JComboBox cbAircon;
	


	
	ArrayList<CRUD_1> roomList = new ArrayList<CRUD_1>();


	Room room = new Room(roomList);
	ConnectDatabase db = new ConnectDatabase();
	
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	DefaultComboBoxModel cbModel;
	RoomType_Display roomType = new RoomType_Display();
	CRUD_1 roomTypes ;
	Map< String,String> roomTypeMap =  new HashMap< String,String>();

	
	
	
	/**
	 * Create the panel.
	 */
	public Room_Display() {

		JPanel panel = new JPanel();
    	panel.setBackground(Color.BLACK);
   	 	panel.setLayout(null);
		setLayout(null);
		
		
		
		JLabel lblRoomID = new JLabel("Room ID:");
		lblRoomID.setBounds(36, 42, 76, 16);
		add(lblRoomID);
		
		JLabel lblRoomAvailability = new JLabel("Room Availability:");
		lblRoomAvailability.setBounds(36, 108, 102, 16);
		add(lblRoomAvailability);
		
		JLabel lblRoomName = new JLabel("Room Name:");
		lblRoomName.setBounds(36, 73, 76, 16);
		add(lblRoomName);
		
		JLabel lblLeasingPrice = new JLabel("Leasing Price:");
		lblLeasingPrice.setBounds(34, 431, 104, 16);
		add(lblLeasingPrice);
		
		JLabel lblRoomTypeID = new JLabel("Room Type:");
		lblRoomTypeID.setBounds(36, 11, 76, 16);
		add(lblRoomTypeID);

		
		
		txtRoomID = new JTextField();
		txtRoomID.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char keyChar = e.getKeyChar();
			    if (Character.isLowerCase(keyChar)) {
			      e.setKeyChar(Character.toUpperCase(keyChar));
			    }
			}
		});
		txtRoomID.setBounds(205, 42, 121, 22);
		add(txtRoomID);
		txtRoomID.setColumns(10);
		
		txtRoomAvailability = new JTextField();
		txtRoomAvailability.setColumns(10);
		txtRoomAvailability.setBounds(205, 108, 121, 22);
		add(txtRoomAvailability);
		
		txtRoomName = new JTextField();
		txtRoomName.setColumns(10);
		txtRoomName.setBounds(205, 73, 121, 22);
		add(txtRoomName);
		
		txtLeasingPrice = new JTextField();
		txtLeasingPrice.setText("0.00");
		txtLeasingPrice.setColumns(10);
		txtLeasingPrice.setBounds(205, 432, 121, 22);
		add(txtLeasingPrice);

		
		cbRoomTypeID = new JComboBox();
		cbModel = new DefaultComboBoxModel(new String[] {});
		cbModel.removeAllElements();
		
		//Add the available roles to a JCombobox for user to select
		for(int i =0; i< roomType.roomTypeList.size(); i++) {
			roomTypes = roomType.roomType.getIndex(roomType.roomTypeList, i);
			if(cbModel.getIndexOf(roomType.roomType.getRecord(roomType.roomTypeList, roomTypes.getRoomTypeID()).getRoomTypeID()) < 0) {
				cbModel.addElement(roomType.roomType.getRecord(roomType.roomTypeList, roomTypes.getRoomTypeID()).getRoomTypeID());
				roomTypeMap.put(roomType.roomType.getRecord(roomType.roomTypeList, roomTypes.getRoomTypeID()).getRoomTypeID(), roomType.roomType.getRecord(roomType.roomTypeList, roomTypes.getRoomTypeID()).getRoomTypeID());
				 
			}
		}
		
		
		cbRoomTypeID = new JComboBox(cbModel);
		cbRoomTypeID.setBounds(205, 12, 121, 20);
		add(cbRoomTypeID);


		
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

				if(fieldCheck("Insert", txtRoomID.getText(), cbRoomTypeID.getSelectedItem().toString(),txtRoomName.getText(), txtRoomAvailability.getText(),txtRoomCapacityAdult.getText(),txtRoomCapacityChild.getText(),txtBedQuantityKing.getText(),txtBedQuantityQueen.getText(),txtBedQuantitySingle.getText(),cbSmoking.getSelectedItem().toString(), cbShower.getSelectedItem().toString(), cbWifi.getSelectedItem().toString(), cbAircon.getSelectedItem().toString(), txtLeasingPrice.getText(),txtRentingPrice.getText()) == 0) {
					
					String query = "INSERT INTO room VALUES ('" +txtRoomID.getText()+ "','" +cbRoomTypeID.getSelectedItem().toString()+ "','" +txtRoomName.getText()+ "','" +txtRoomAvailability.getText()+ "','" + txtRoomCapacityAdult.getText()+ "','" +txtRoomCapacityChild.getText()+ "','" + txtBedQuantityKing.getText()+ "','" + txtBedQuantityQueen.getText()+ "','" + txtBedQuantitySingle.getText()+ "','" + cbSmoking.getSelectedItem().toString()+ "','" + cbShower.getSelectedItem().toString()+ "','" +cbWifi.getSelectedItem().toString()+ "','" + cbAircon.getSelectedItem().toString()+ "','" + txtLeasingPrice.getText()+ "','" + txtRentingPrice.getText() +"')";
						
					room.addRecord(roomList, new CRUD_1(txtRoomID.getText(),cbRoomTypeID.getSelectedItem().toString(),txtRoomName.getText(),Integer.parseInt(txtRoomAvailability.getText()),Integer.parseInt(txtRoomCapacityAdult.getText()),Integer.parseInt(txtRoomCapacityChild.getText()),Integer.parseInt(txtBedQuantityKing.getText()),Integer.parseInt(txtBedQuantityQueen.getText()),Integer.parseInt(txtBedQuantitySingle.getText()),cbSmoking.getSelectedItem().toString(),cbShower.getSelectedItem().toString(),cbWifi.getSelectedItem().toString(),cbAircon.getSelectedItem().toString(),Double.parseDouble(txtLeasingPrice.getText()),Double.parseDouble(txtRentingPrice.getText())));
					
						executeSQL(query, "Inserted");

				}

			}
		});
		btnAdd.setBounds(12, 523, 86, 39);
		add(btnAdd);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				if(fieldCheck("Update", txtRoomID.getText(), cbRoomTypeID.getSelectedItem().toString(),txtRoomName.getText(), txtRoomAvailability.getText(),txtRoomCapacityAdult.getText(),txtRoomCapacityChild.getText(),txtBedQuantityKing.getText(),txtBedQuantityQueen.getText(),txtBedQuantitySingle.getText(),cbSmoking.getSelectedItem().toString(), cbShower.getSelectedItem().toString(), cbWifi.getSelectedItem().toString(), cbAircon.getSelectedItem().toString(), txtLeasingPrice.getText(),txtRentingPrice.getText()) == 0) {
						
					String query = "UPDATE room SET roomName = '" +txtRoomName.getText()+ "', roomAvailability ='" +txtRoomAvailability.getText()+"', roomAdultCapacity ='" +txtRoomCapacityAdult.getText()+ "', roomChildCapacity ='" +txtRoomCapacityChild.getText()+ "', bedKingSizeQuantity ='" +txtBedQuantityKing.getText()+"', bedQueenSizeQuantity ='" +txtBedQuantityQueen.getText()+"', bedSingleSizeQuantity ='" +txtBedQuantitySingle.getText()+ "', smoking ='" +cbSmoking.getSelectedItem().toString()+ "', shower ='" +cbShower.getSelectedItem().toString()+ "', wifi ='" +cbWifi.getSelectedItem().toString()+"', aircon ='" +cbAircon.getSelectedItem().toString()+ "', leasingPrice ='" +txtLeasingPrice.getText()+"', rentingPrice ='" +txtRentingPrice.getText()+ "' WHERE roomID ='"+txtRoomID.getText()+"' and roomTypeID = '"+cbRoomTypeID.getSelectedItem().toString()+"'";
					
					
					room.setRecord(roomList, new CRUD_1(txtRoomID.getText(),cbRoomTypeID.getSelectedItem().toString(),txtRoomName.getText(),Integer.parseInt(txtRoomAvailability.getText()),Integer.parseInt(txtRoomCapacityAdult.getText()),Integer.parseInt(txtRoomCapacityChild.getText()),Integer.parseInt(txtBedQuantityKing.getText()),Integer.parseInt(txtBedQuantityQueen.getText()),Integer.parseInt(txtBedQuantitySingle.getText()),cbSmoking.getSelectedItem().toString(),cbShower.getSelectedItem().toString(),cbWifi.getSelectedItem().toString(),cbAircon.getSelectedItem().toString(),Double.parseDouble(txtLeasingPrice.getText()),Double.parseDouble(txtRentingPrice.getText())));
					executeSQL(query, "Updated");
				}
			}
		});
		btnUpdate.setBounds(110, 523, 86, 39);
		add(btnUpdate);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(fieldCheck("Delete", txtRoomID.getText(), cbRoomTypeID.getSelectedItem().toString(),txtRoomName.getText(), txtRoomAvailability.getText(),txtRoomCapacityAdult.getText(),txtRoomCapacityChild.getText(),txtBedQuantityKing.getText(),txtBedQuantityQueen.getText(),txtBedQuantitySingle.getText(),cbSmoking.getSelectedItem().toString(), cbShower.getSelectedItem().toString(), cbWifi.getSelectedItem().toString(), cbAircon.getSelectedItem().toString(), txtLeasingPrice.getText(),txtRentingPrice.getText()) == 0) {
					String query = "DELETE FROM room WHERE roomID ='"+txtRoomID.getText()+"' and roomTypeID = '"+cbRoomTypeID.getSelectedItem().toString()+"'";
					room.deleteRecord(roomList, txtRoomID.getText(), cbRoomTypeID.getSelectedItem().toString());
					executeSQL(query, "Deleted");
				}
			}
		});
		btnDelete.setBounds(213, 523, 86, 39);
		add(btnDelete);
		
		
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(338, 62, 886, 402);
		add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JLabel lblRoomCapacityadult = new JLabel("Room Capacity (Adult):");
		lblRoomCapacityadult.setBounds(36, 143, 142, 16);
		add(lblRoomCapacityadult);
		
		txtRoomCapacityAdult = new JTextField();
		txtRoomCapacityAdult.setColumns(10);
		txtRoomCapacityAdult.setBounds(205, 143, 121, 22);
		add(txtRoomCapacityAdult);
		
		JLabel lblRoomCapacitychild = new JLabel("Room Capacity (Child):");
		lblRoomCapacitychild.setBounds(36, 179, 142, 16);
		add(lblRoomCapacitychild);
		
		txtRoomCapacityChild = new JTextField();
		txtRoomCapacityChild.setColumns(10);
		txtRoomCapacityChild.setBounds(205, 179, 121, 22);
		add(txtRoomCapacityChild);
		
		JLabel lblBedQuantitykingsized = new JLabel("Bed Quantity (King-Sized):");
		lblBedQuantitykingsized.setBounds(36, 211, 160, 16);
		add(lblBedQuantitykingsized);
		
		txtBedQuantityKing = new JTextField();
		txtBedQuantityKing.setColumns(10);
		txtBedQuantityKing.setBounds(205, 211, 121, 22);
		add(txtBedQuantityKing);
		
		JLabel lblBedQuantitysinglesized = new JLabel("Bed Quantity (Single-Sized):");
		lblBedQuantitysinglesized.setBounds(36, 275, 176, 16);
		add(lblBedQuantitysinglesized);
		
		JLabel lblBedQuantityqueensized = new JLabel("Bed Quantity (Queen-Sized):");
		lblBedQuantityqueensized.setBounds(36, 243, 176, 16);
		add(lblBedQuantityqueensized);
		
		txtBedQuantityQueen = new JTextField();
		txtBedQuantityQueen.setColumns(10);
		txtBedQuantityQueen.setBounds(205, 243, 121, 22);
		add(txtBedQuantityQueen);
		
		txtBedQuantitySingle = new JTextField();
		txtBedQuantitySingle.setColumns(10);
		txtBedQuantitySingle.setBounds(205, 275, 121, 22);
		add(txtBedQuantitySingle);
		
		txtRentingPrice = new JTextField();
		txtRentingPrice.setText("0.00");
		txtRentingPrice.setColumns(10);
		txtRentingPrice.setBounds(205, 467, 121, 22);
		add(txtRentingPrice);
		
		JLabel lblRentingPrice = new JLabel("Renting Price:");
		lblRentingPrice.setBounds(34, 466, 93, 16);
		add(lblRentingPrice);
		
		JLabel lblSmoking = new JLabel("Smoking:");
		lblSmoking.setBounds(36, 307, 76, 16);
		add(lblSmoking);
		
		JLabel lblShower = new JLabel("Shower:");
		lblShower.setBounds(36, 339, 76, 16);
		add(lblShower);
		
		JLabel lblAirConditioner = new JLabel("Air Conditioner");
		lblAirConditioner.setBounds(36, 401, 117, 16);
		add(lblAirConditioner);
		
		JLabel lblWifi = new JLabel("Wi-Fi:");
		lblWifi.setBounds(36, 369, 76, 16);
		add(lblWifi);
		
		cbSmoking = new JComboBox(new DefaultComboBoxModel(new String[] {"Smoking", "Non-Smoking"}));
		cbSmoking.setBounds(205, 307, 121, 20);
		add(cbSmoking);
		
		cbShower = new JComboBox(new DefaultComboBoxModel(new String[] {"Standing Shower", "Shower with Bathtub"}));
		cbShower.setBounds(205, 339, 121, 20);
		add(cbShower);
		
		cbWifi = new JComboBox(new DefaultComboBoxModel(new String[] {"Available", "Not Available"}));
		cbWifi.setBounds(205, 368, 121, 20);
		add(cbWifi);
		
		cbAircon = new JComboBox(new DefaultComboBoxModel(new String[] {"Available", "Not Available"}));
		cbAircon.setBounds(205, 399, 121, 20);
		add(cbAircon);

		
		//display the room panel 
		displayData();			
	}


	/*
	Validates the fields in the table for empty and invalid inputs & prompts proper error message
	
	@param actionName: whether the user is inserting, updating / deleting the record
	@param ID, ..., role : All the fields that undergo the validation
	@return errCounter: if the errCounter returns 0, then there are no error encountered 
	*/
	int fieldCheck(String actionName, String roomID, String roomTypeID, String roomName, String roomAvailability, String roomAdultCapacity, String roomChildCapacity, String bedKingSizeQuantity, String bedQueenSizeQuantity, String bedSingleSizeQuantity, String smoking, String shower, String wifi, String aircon, String leasingPrice, String rentingPrice) {
		int errCounter = 0;
		
		if(roomID.equals("") || roomTypeID.equals("") || roomName.equals("") || roomAvailability.equals("")  || roomAdultCapacity.equals("") || roomChildCapacity.equals("") || bedKingSizeQuantity.equals("")  || bedQueenSizeQuantity.equals("") || bedSingleSizeQuantity.equals("") || smoking.equals("") || shower.equals("") || wifi.equals("") || aircon.equals("") || leasingPrice.equals("") || rentingPrice.equals("") ) {
			JOptionPane.showMessageDialog(null, "Please fill in all the fields!");
			errCounter ++;
		}else {
			if(!room.validateDouble(leasingPrice) || !room.validateDouble(rentingPrice)) {
				JOptionPane.showMessageDialog(null, "Prices can only have digits & decimals! ");
				errCounter ++;
			}else{
				if(actionName.equals("Insert")) {
					if(room.isIdExist(roomList, roomID, roomTypeID)){
						JOptionPane.showMessageDialog(null, "Failed to add new room record as the same ID already exists!");
						errCounter ++;
					}
				}
							
				if(actionName.equals("Update")) {
					if(room.isRecordExist(roomList, new CRUD_1(roomID, roomTypeID, roomName, Integer.parseInt(roomAvailability), Integer.parseInt(roomAdultCapacity), Integer.parseInt(roomChildCapacity), Integer.parseInt(bedKingSizeQuantity), Integer.parseInt(bedQueenSizeQuantity), Integer.parseInt(bedSingleSizeQuantity), smoking, shower, wifi, aircon, Double.parseDouble(leasingPrice), Double.parseDouble(rentingPrice)))){
						JOptionPane.showMessageDialog(null, " Failed to update room record as the exact record already exists!");
						errCounter ++;
					}else if(!room.isIdExist(roomList, roomID, roomTypeID)) {
						JOptionPane.showMessageDialog(null, "Failed to update room record as no such ID is found!");
						errCounter ++;
					}
				}
			}

			if(actionName.equals("Delete")) {
				if(!room.isIdExist(roomList, roomID, roomTypeID)) {
					JOptionPane.showMessageDialog(null, "Failed to update room record as no such ID is found!");
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
		Object[] columns = {"Room ID", "Room Type ID", "Room Name", "Availability", "Capacity (Adult)", "Capacity (Child)", "Bed (King)", "Bed (Queen)", "Bed (Single)", "Smoking", "Shower", "Wifi", "Air Conditioner", "Leasing Price", "Renting Price"  };
		
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
		
		for(int i = 0; i < 15; i++) {
				table.getColumnModel().getColumn(i).setCellRenderer(Renderer);
		}
		
		//allow the selected row values to be displayed in the JTextField
		table.addMouseListener(new MouseAdapter() {
			
			public void mouseClicked(MouseEvent e) {
				int i = table.getSelectedRow();
				
				if (table.getRowSorter()!=null) {
				    i = table.getRowSorter().convertRowIndexToModel(i);
				}
				
				txtRoomID.setText(model.getValueAt(i, 0).toString());
				cbRoomTypeID.setSelectedItem(model.getValueAt(i, 1).toString());
				txtRoomName.setText(model.getValueAt(i, 2).toString());
				txtRoomAvailability.setText(model.getValueAt(i, 3).toString());
				txtRoomCapacityAdult.setText(model.getValueAt(i, 4).toString());
				txtRoomCapacityChild.setText(model.getValueAt(i, 5).toString());
				txtBedQuantityKing.setText(model.getValueAt(i, 6).toString());
				txtBedQuantityQueen.setText(model.getValueAt(i, 7).toString());
				txtBedQuantitySingle.setText(model.getValueAt(i, 8).toString());
				
				cbSmoking.setSelectedItem(model.getValueAt(i, 9).toString());
				cbShower.setSelectedItem(model.getValueAt(i, 10).toString());
				cbWifi.setSelectedItem(model.getValueAt(i, 11).toString());
				cbAircon.setSelectedItem(model.getValueAt(i, 12).toString());
				
				txtLeasingPrice.setText(model.getValueAt(i, 13).toString());
				txtRentingPrice.setText(model.getValueAt(i, 14).toString());
				
			}
			
		});
		
		
		
		try {
			Class.forName(db.getClassName());//the database driver 
			
			String roomID, roomTypeID, roomName, smoking, shower, wifi, aircon;
			int roomAvailability, roomAdultCapacity, roomChildCapacity, bedKingSizeQuantity, bedQueenSizeQuantity, bedSingleSizeQuantity;
			double leasingPrice, rentingPrice;
			
			
			conn = DriverManager.getConnection(db.getURL(), db.getUserName(), db.getPassword()); //connect to the database
			conn.setAutoCommit(false);//do not auto commit the SQL statements
			stmt = conn.createStatement();//prepare the create a SQL statement
			rs = stmt.executeQuery("SELECT * from room ORDER BY LENGTH(roomID), roomID asc");//SQL select query to display the class type // e.roleID,
			
			//add the rows in the class type details database to the JTable
			Object[] row = new Object[15];
			while (rs.next()) { 
			
				roomID = rs.getString(1);
				roomTypeID = rs.getString(2);
				roomName = rs.getString(3);
				roomAvailability = rs.getInt(4);
				roomAdultCapacity = rs.getInt(5);
				roomChildCapacity = rs.getInt(6);
				bedKingSizeQuantity = rs.getInt(7);
				bedQueenSizeQuantity = rs.getInt(8);
				bedSingleSizeQuantity = rs.getInt(9);
				smoking = rs.getString(10);
				shower = rs.getString(11);
				wifi = rs.getString(12);
				aircon = rs.getString(13);
				leasingPrice = rs.getDouble(14);
				rentingPrice = rs.getDouble(15);
				
	
	
				room.addRecord(roomList, new CRUD_1(roomID, roomTypeID, roomName, roomAvailability, roomAdultCapacity, roomChildCapacity,bedKingSizeQuantity, bedQueenSizeQuantity, bedSingleSizeQuantity, smoking, shower, wifi, aircon, leasingPrice, rentingPrice));
			
				
				row[0] = roomID;
				row[1] = roomTypeID;
				row[2] = roomName;
				row[3] = roomAvailability;
				row[4] = roomAdultCapacity;
				row[5] = roomChildCapacity;
				row[6] = bedKingSizeQuantity;
				row[7] = bedQueenSizeQuantity;
				row[8] = bedSingleSizeQuantity;
				row[9] = smoking;
				row[10] = shower;
				row[11] = wifi;
				row[12] = aircon;
				row[13] = leasingPrice;
				row[14] = rentingPrice;
				
				model.addRow(row);
            } 
			room.RowFilterTest(table, model, txtSearch);
			
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

			CRUD_1 roomRefer ;
			
			Object[] row = new Object[15];
			

			for(int i = 0; i< room.getCount(roomList); i++) {
			
			
				roomRefer = room.getIndex(roomList, i);
			
			
				
				row[0] = room.getRecord(roomList, roomRefer.getRoomID(), roomRefer.getRoomTypeID()).getRoomID();
				row[1] = room.getRecord(roomList, roomRefer.getRoomID(), roomRefer.getRoomTypeID()).getRoomTypeID();
				row[2] = room.getRecord(roomList, roomRefer.getRoomID(), roomRefer.getRoomTypeID()).getRoomName();
				row[3] = room.getRecord(roomList, roomRefer.getRoomID(), roomRefer.getRoomTypeID()).getRoomAvailability();
				row[4] = room.getRecord(roomList, roomRefer.getRoomID(), roomRefer.getRoomTypeID()).getRoomAdultCapacity();
				row[5] = room.getRecord(roomList, roomRefer.getRoomID(), roomRefer.getRoomTypeID()).getRoomChildCapacity();
				row[6] = room.getRecord(roomList, roomRefer.getRoomID(), roomRefer.getRoomTypeID()).getBedKingSizeQuantity();
				row[7] = room.getRecord(roomList, roomRefer.getRoomID(), roomRefer.getRoomTypeID()).getBedQueenSizeQuantity();
				row[8] = room.getRecord(roomList, roomRefer.getRoomID(), roomRefer.getRoomTypeID()).getBedSingleSizeQuantity();
				row[9] = room.getRecord(roomList, roomRefer.getRoomID(), roomRefer.getRoomTypeID()).getSmoking();
				row[10] = room.getRecord(roomList, roomRefer.getRoomID(), roomRefer.getRoomTypeID()).getShower();
				row[11] = room.getRecord(roomList, roomRefer.getRoomID(), roomRefer.getRoomTypeID()).getWifi();
				row[12] = room.getRecord(roomList, roomRefer.getRoomID(), roomRefer.getRoomTypeID()).getAircon();
				row[13] = room.getRecord(roomList, roomRefer.getRoomID(), roomRefer.getRoomTypeID()).getLeasingPrice();
				row[14] = room.getRecord(roomList, roomRefer.getRoomID(), roomRefer.getRoomTypeID()).getRentingPrice();
				

				
				model.addRow(row);
			}
			
			
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
}
