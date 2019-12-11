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

public class Check_In_Form extends HotelDetails {

    public Check_In_Form(ArrayList<CRUD_1> check_in_form) {
    	super(check_in_form);
    }
    
    //Retrieve the Records
    public CRUD_1 getRecord(ArrayList<CRUD_1> check_in_form, String check_in_formId, String reservationId){ 
        int index=-1; 
        for(int i=0;i<check_in_form.size();i++){
            if(check_in_form.get(i).getCheckInFormID().equals(check_in_formId) && check_in_form.get(i).getReservationID().equals(reservationId))
                index=i;
        }
        if(index>-1)
            return check_in_form.get(index);
        else
            return null;
    }
    
    //Get the record of the current position from the Records
    public CRUD_1 getIndex(ArrayList<CRUD_1> check_in_form, int pos){
      return check_in_form.get(pos);
    }
    
    //Insert into the Records
    public void addRecord(ArrayList<CRUD_1> check_in_form, CRUD_1 target){
    	check_in_form.add(target);
    }
    
    //Update the Records
    public void setRecord(ArrayList<CRUD_1> check_in_form, CRUD_1 target){
    	CRUD_1 current = getRecord(check_in_form, target.getCheckInFormID(), target.getReservationID());
        if(current!=null){
        	current.setSignature(target.getSignature());
        }
    }
    
    //Deleting the Records using the ID
    public void deleteRecord(ArrayList<CRUD_1> check_in_form, String check_in_formId, String reservationId){
        int index=-1;
        for(int i=0;i<check_in_form.size();i++){
            if(check_in_form.get(i).getCheckInFormID().equals(check_in_formId) && check_in_form.get(i).getReservationID().equals(reservationId))
                index=i;

        }
        if(index>-1)
        	check_in_form.remove(index);
    }
    
    //Get the size of the ArrayList
    public int getCount(ArrayList<CRUD_1> check_in_form){
        return check_in_form.size();
    }
    
    //Check if the ID Exist in the Records
    public boolean isIdExist(ArrayList<CRUD_1> check_in_form, String check_in_formId, String reservationId){
        Boolean found=false;
        for(int i=0;i<check_in_form.size();i++){
            if(check_in_form.get(i).getCheckInFormID().equals(check_in_formId) && check_in_form.get(i).getReservationID().equals(reservationId))
                found=true;
        }
        return found;
    }
    
    //Check if the same record exists in the Records 
    public boolean isRecordExist(ArrayList<CRUD_1> check_in_form, CRUD_1 target){
       Boolean found=false;
        for(int i=0;i<check_in_form.size();i++){
            if(check_in_form.get(i).equalsCheck_in_form(target))
                found=true;
        }
        return found; 
    }
    

}
