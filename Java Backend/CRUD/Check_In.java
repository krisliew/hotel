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

public class Check_In extends HotelDetails {

    public Check_In(ArrayList<CRUD_1> check_in) {
    	super(check_in);
    }
    
    //Retrieve the Records
    public CRUD_1 getRecord(ArrayList<CRUD_1> check_in, String check_inId, String check_in_formId){ 
        int index=-1; 
        for(int i=0;i<check_in.size();i++){
            if(check_in.get(i).getCheckInID().equals(check_inId) && check_in.get(i).getCheckInFormID().equals(check_in_formId))
                index=i;
        }
        if(index>-1)
            return check_in.get(index);
        else
            return null;
    }
    
    //Get the record of the current position from the Records
    public CRUD_1 getIndex(ArrayList<CRUD_1> check_in, int pos){
      return check_in.get(pos);
    }
    
    //Insert into the Records
    public void addRecord(ArrayList<CRUD_1> check_in, CRUD_1 target){
    	check_in.add(target);
    }
    
    //Update the Records
    public void setRecord(ArrayList<CRUD_1> check_in, CRUD_1 target){
    	CRUD_1 current = getRecord(check_in, target.getCheckInID(), target.getCheckInFormID());
        if(current!=null){
        	current.setDepositAmount(target.getDepositAmount());
        	current.setPaymentStatus(target.getPaymentStatus());
        }
    }
    
    //Deleting the Records using the ID
    public void deleteRecord(ArrayList<CRUD_1> check_in, String check_inId, String check_in_formId){
        int index=-1;
        for(int i=0;i<check_in.size();i++){
            if(check_in.get(i).getCheckInID().equals(check_inId) && check_in.get(i).getCheckInFormID().equals(check_in_formId))
                index=i;

        }
        if(index>-1)
        	check_in.remove(index);
    }
    
    //Get the size of the ArrayList
    public int getCount(ArrayList<CRUD_1> check_in){
        return check_in.size();
    }
    
    //Check if the ID Exist in the Records
    public boolean isIdExist(ArrayList<CRUD_1> check_in, String check_inId, String check_in_formId){
        Boolean found=false;
        for(int i=0;i<check_in.size();i++){
            if(check_in.get(i).getCheckInID().equals(check_inId) && check_in.get(i).getCheckInFormID().equals(check_in_formId))
                found=true;
        }
        return found;
    }
    
    //Check if the same record exists in the Records 
    public boolean isRecordExist(ArrayList<CRUD_1> check_in, CRUD_1 target){
       Boolean found=false;
        for(int i=0;i<check_in.size();i++){
            if(check_in.get(i).equalsCheck_in(target))
                found=true;
        }
        return found; 
    }
    

}
