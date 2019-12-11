package CRUD;

import java.util.ArrayList;
import java.util.regex.Pattern;

import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

public class Services {
	private ArrayList<CRUD_2> services;

    //Constructor that contains the ArrayList that is going to be manipulated
    public Services(ArrayList<CRUD_2> services) {
    	services=new ArrayList<>();
    }
    
    //Retrieve the Records
    public CRUD_2 getRecord(ArrayList<CRUD_2> services, String id){ 
        int index=-1; 
        for(int i=0;i<services.size();i++){
            if(services.get(i).getServiceID().equals(id))
                index=i;
        }
        if(index>-1)
            return services.get(index);
        else
            return null;
    }
    
    //Get the record of the current position from the Records
    public CRUD_2 getIndex(ArrayList<CRUD_2> services, int pos){
      return services.get(pos);
    }
    
    //Insert into the Records
    public void addRecord(ArrayList<CRUD_2> services, CRUD_2 target){
    	services.add(target);
    }
    
    //Update the Records
    public void setRecord(ArrayList<CRUD_2> services, CRUD_2 target){
    	CRUD_2 current = getRecord(services, target.getServiceID());
        if(current!=null){
        	current.setServiceName(target.getServiceName());
        	current.setServiceDescription(target.getServiceDescription());
        	current.setServicePrice(target.getServicePrice());
        	current.setServiceOperationStartTime(target.getServiceOperationStartTime());
        	current.setServiceOperationEndTime(target.getServiceOperationEndTime());
        	current.setServiceRules(target.getServiceRules());
        }
    }
    
    //Deleting the Records using the ID
    public void deleteRecord(ArrayList<CRUD_2> services, String id){
        int index=-1;
        for(int i=0;i<services.size();i++){
            if(services.get(i).getServiceID().equals(id)) 
                index=i;

        }
        if(index>-1)
        	services.remove(index);
    }
    
    //Get the size of the ArrayList
    public int getCount(ArrayList<CRUD_2> services){
        return services.size();
    }
    
    //Check if the ID Exist in the Records
    public boolean isIdExist(ArrayList<CRUD_2> services, String id){
        Boolean found=false;
        for(int i=0;i<services.size();i++){
            if(services.get(i).getServiceID().equals(id))
                found=true;
        }
        return found;
    }
    
    //Check if the same record exists in the Records 
    public boolean isRecordExist(ArrayList<CRUD_2> services, CRUD_2 target){
       Boolean found=false;
        for(int i=0;i<services.size();i++){
            if(services.get(i).equalsServices(target))
                found=true;
        }
        return found; 
    }
    
    
    //Validates the input to see if it is a double
    public boolean validateDouble(String value) {
    	try {
    		if (value.matches("[0-9]+(\\.){0,1}[0-9]*")){//regex for double
        		return true;
        	}else {
        		return false;
        	}
    		
    	}catch(NumberFormatException ex) {
    		return false;
    	}
    }
    
    
    //Validates the input to see if it is an integer
    public boolean validateInt(String value) {
    	try {
    		if (value.matches("\\-?\\d+")){ //regex for integer
        		return true;
        	}else {
        		return false;
        	}
    		
    	}catch(NumberFormatException ex) {
    		return false;
    	}
    }
    
    //Validates the input to see if it is a valid contact number
    public boolean validateContactNumber(String phoneNo) {
    	
    	/*
		 * 
		 * Website: JournalDev
		 * Title: Regular Expression Phone Number validation in Java
		 * Author: PANKAJ
		 * Released Date: -
		 * Referred Date: 15/04/19
		 * URL: https://www.journaldev.com/641/regular-expression-phone-number-validation-in-java
		 * 
		 * */
    	
    	
    	//validate phone numbers of format "1234567890"
		if (phoneNo.matches("\\d{10}")) return true;
		//validating phone number with -, . or spaces
		else if(phoneNo.matches("\\d{3}[-\\.\\s]\\d{3}[-\\.\\s]\\d{4}")) return true;
		//validating phone number with extension length from 3 to 5
		else if(phoneNo.matches("\\d{3}-\\d{3}-\\d{4}\\s(x|(ext))\\d{3,5}")) return true;
		//validating phone number where area code is in braces ()
		else if(phoneNo.matches("\\(\\d{3}\\)-\\d{3}-\\d{4}")) return true;
		//return false if nothing matches the input
		else return false;

		
	}

    //Validates the input to see if it is a valid email address
    public boolean validateEmail(String email){ 
    	/*
		 * 
		 * Website: GeeksforGeeks
		 * Title: Check if email address valid or not in Java
		 * Author: Pranav
		 * Released Date: -
		 * Referred Date: 15/04/19
		 * URL: https://www.geeksforgeeks.org/check-email-address-valid-not-java/
		 * 
		 * */
    	
    	
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+ 
                            "[a-zA-Z0-9_+&*-]+)*@" + 
                            "(?:[a-zA-Z0-9-]+\\.)+[a-z" + 
                            "A-Z]{2,7}$"; 
                              
        Pattern pat = Pattern.compile(emailRegex); 
        if (email == null) 
            return false; 
        return pat.matcher(email).matches(); 
    } 
    
    
    //Filters the rows in the JTable and only display the relevant records with the Search string
    public void RowFilterTest(JTable JTable, DefaultTableModel model, JTextField JTfFilter) {
		
		/*
		 * 
		 * Website: Stackoverflow
		 * Title: how to search an element in a JTable java?
		 * Author: Paul Samsotha
		 * Released Date: 27/02/14
		 * Referred Date: 05/04/19
		 * URL: https://stackoverflow.com/questions/22066387/how-to-search-an-element-in-a-jtable-java
		 * 
		 * */
		
		
		TableRowSorter<TableModel> rowSorter = new TableRowSorter<>(JTable.getModel());
		
		JTable.setRowSorter(rowSorter);

        JTfFilter.getDocument().addDocumentListener(new DocumentListener(){

            @Override
            public void insertUpdate(DocumentEvent e) {
                String text = JTfFilter.getText();

                if (text.trim().length() == 0) {
                    rowSorter.setRowFilter(null);
                    
                } else {
                    rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
                }
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                String text = JTfFilter.getText();

                if (text.trim().length() == 0) {
                    rowSorter.setRowFilter(null);
                } else {
                    rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
                } 
            }
            
           
            
            @Override
            public void changedUpdate(DocumentEvent e) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

        });
    }



}
