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
import CRUD.Menu;

import javax.swing.SwingConstants;
import javax.swing.ComboBoxModel;
import javax.swing.JTextArea;


public class Menu_Display extends JPanel {

	private JTextField txtSearch;
	private JTextField txtMenuID;
	private JTextField txtMenuName;
	private JTable table;
	private JTextField txtMenuDescription;
	private JTextField txtMenuPrice;
	


	
	ArrayList<CRUD_1> menuList = new ArrayList<CRUD_1>();


	Menu menu = new Menu(menuList);
	ConnectDatabase db = new ConnectDatabase();

	CRUD_1 menus ;
	

	
	
	/**
	 * Create the panel.
	 */
	public Menu_Display() {

		JPanel panel = new JPanel();
    	panel.setBackground(Color.BLACK);
   	 	panel.setLayout(null);
		setLayout(null);
		
		
		
		JLabel lblMenuID = new JLabel("Menu ID:");
		lblMenuID.setBounds(33, 160, 76, 16);
		add(lblMenuID);
		
		JLabel lblMenuName = new JLabel("Menu Name:");
		lblMenuName.setBounds(33, 191, 76, 16);
		add(lblMenuName);

		
		
		txtMenuID = new JTextField();
		txtMenuID.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char keyChar = e.getKeyChar();
			    if (Character.isLowerCase(keyChar)) {
			      e.setKeyChar(Character.toUpperCase(keyChar));
			    }
			}
		});
		txtMenuID.setBounds(177, 159, 121, 22);
		add(txtMenuID);
		txtMenuID.setColumns(10);
		
		txtMenuName = new JTextField();
		txtMenuName.setColumns(10);
		txtMenuName.setBounds(177, 190, 121, 22);
		add(txtMenuName);


		
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

				if(fieldCheck("Insert", txtMenuID.getText(), txtMenuName.getText() , txtMenuDescription.getText()  ,txtMenuPrice.getText()) == 0) {
					
					String query = "INSERT INTO menu VALUES ('" +txtMenuID.getText()+ "','" +txtMenuName.getText()+ "','" +txtMenuDescription.getText()+ "','" +txtMenuPrice.getText()+ "')";
						
					menu.addRecord(menuList, new CRUD_1(txtMenuID.getText(), txtMenuName.getText(), txtMenuDescription.getText(), Double.parseDouble(txtMenuPrice.getText())));
					
						executeSQL(query, "Inserted");

				}

			}
		});
		btnAdd.setBounds(22, 381, 86, 39);
		add(btnAdd);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				if(fieldCheck("Update", txtMenuID.getText(), txtMenuName.getText() , txtMenuDescription.getText(),txtMenuPrice.getText()) == 0) {
						
					String query = "UPDATE menu SET menuName = '" +txtMenuName.getText()+ "', menuDescription ='" +txtMenuDescription.getText()+ "', menuPrice ='" +txtMenuPrice.getText()+ "' WHERE menuID ='"+txtMenuID.getText()+"'";
					
					
					menu.setRecord(menuList, new CRUD_1(txtMenuID.getText(), txtMenuName.getText(), txtMenuDescription.getText(), Double.parseDouble(txtMenuPrice.getText())));
					executeSQL(query, "Updated");
				}
			}
		});
		btnUpdate.setBounds(120, 381, 86, 39);
		add(btnUpdate);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(fieldCheck("Delete", txtMenuID.getText(), txtMenuName.getText() , txtMenuDescription.getText(),txtMenuPrice.getText()) == 0) {
					
					
					String query = "DELETE FROM menu WHERE menuID ='"+txtMenuID.getText()+"'";
					menu.deleteRecord(menuList, txtMenuID.getText());
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
		
		JLabel lblMenuDescription = new JLabel("Menu Description: ");
		lblMenuDescription.setBounds(33, 221, 142, 16);
		add(lblMenuDescription);
		
		txtMenuDescription = new JTextField();
		txtMenuDescription.setColumns(10);
		txtMenuDescription.setBounds(177, 220, 121, 22);
		add(txtMenuDescription);
		
		JLabel lblMenuPrice = new JLabel("Menu Price:");
		lblMenuPrice.setBounds(33, 258, 142, 16);
		add(lblMenuPrice);
		
		txtMenuPrice = new JTextField();
		txtMenuPrice.setText("0.00");
		txtMenuPrice.setColumns(10);
		txtMenuPrice.setBounds(177, 255, 121, 22);
		add(txtMenuPrice);

		
		//display the menu panel 
		displayData();			
	}


	/*
	Validates the fields in the table for empty and invalid inputs & prompts proper error message
	
	@param actionName: whether the user is inserting, updating / deleting the record
	@param ID, ..., role : All the fields that undergo the validation
	@return errCounter: if the errCounter returns 0, then there are no error encountered 
	*/
	int fieldCheck(String actionName, String menuID, String menuName, String menuDescription, String menuPrice) {
		int errCounter = 0;
		
		if(menuID.equals("") || menuName.equals("") || menuDescription.equals("") || menuPrice.equals("") ) {
			JOptionPane.showMessageDialog(null, "Please fill in all the fields!");
			errCounter ++;
		}else {
			if(!menu.validateDouble(menuPrice)) {
				JOptionPane.showMessageDialog(null, "Prices can only have digits & decimals! ");
				errCounter ++;
			}else{
				if(actionName.equals("Insert")) {
					if(menu.isIdExist(menuList, menuID)){
						JOptionPane.showMessageDialog(null, "Failed to add new menu record as the same ID already exists!");
						errCounter ++;
					}
				}
							
				if(actionName.equals("Update")) {
					if(menu.isRecordExist(menuList, new CRUD_1(menuID, menuName, menuDescription, menuPrice))){
						JOptionPane.showMessageDialog(null, " Failed to update menu record as the exact record already exists!");
						errCounter ++;
					}else if(!menu.isIdExist(menuList, menuID)) {
						JOptionPane.showMessageDialog(null, "Failed to update menu record as no such ID is found!");
						errCounter ++;
					}
				}
			}

			if(actionName.equals("Delete")) {
				if(!menu.isIdExist(menuList, menuID)) {
					JOptionPane.showMessageDialog(null, "Failed to update menu record as no such ID is found!");
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
		Object[] columns = {"Menu ID", "Menu Name", "Menu Description", "Menu Price" };
		
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
				
				txtMenuID.setText(model.getValueAt(i, 0).toString());
				txtMenuName.setText(model.getValueAt(i, 1).toString());
				txtMenuDescription.setText(model.getValueAt(i, 2).toString());
				txtMenuPrice.setText(model.getValueAt(i, 3).toString());

			}
			
		});
		
		
		
		try {
			Class.forName(db.getClassName());//the database driver 
			
			String menuID, menuName, menuDescription;
			double menuPrice;
			
			
			conn = DriverManager.getConnection(db.getURL(), db.getUserName(), db.getPassword()); //connect to the database
			conn.setAutoCommit(false);//do not auto commit the SQL statements
			stmt = conn.createStatement();//prepare the create a SQL statement
			rs = stmt.executeQuery("SELECT * from menu ORDER BY LENGTH(menuID), menuID asc");//SQL select query to display the class type // e.roleID,
			
			//add the rows in the class type details database to the JTable
			Object[] row = new Object[6];
			while (rs.next()) { 
			
				menuID = rs.getString(1);
				menuName = rs.getString(2);
				menuDescription = rs.getString(3);
				menuPrice = rs.getDouble(4);
	
				
	
	
				menu.addRecord(menuList, new CRUD_1(menuID, menuName, menuDescription, menuPrice));
			
				
				row[0] = menuID;
				row[1] = menuName;
				row[2] = menuDescription;
				row[3] = menuPrice;
	
		
				
				model.addRow(row);
            } 
			menu.RowFilterTest(table, model, txtSearch);
			
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

			CRUD_1 menuRefer ;
			
			Object[] row = new Object[15];
			

			for(int i = 0; i< menu.getCount(menuList); i++) {
			
			
				menuRefer = menu.getIndex(menuList, i);
			

				row[0] = menu.getRecord(menuList, menuRefer.getMenuID()).getMenuID();
				row[1] = menu.getRecord(menuList, menuRefer.getMenuID()).getMenuName();
				row[2] = menu.getRecord(menuList, menuRefer.getMenuID()).getMenuDescription();
				row[3] = menu.getRecord(menuList, menuRefer.getMenuID()).getMenuPrice();


				model.addRow(row);
			}

		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
}
