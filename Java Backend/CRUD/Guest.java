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

public class Guest extends HotelDetails {

    public Guest(ArrayList<CRUD_1> guest) {
    	super(guest);
    }
    
    //Retrieve the Records
    public CRUD_1 getRecord(ArrayList<CRUD_1> guest, String id){ 
        int index=-1; 
        for(int i=0;i<guest.size();i++){
            if(guest.get(i).getGuestID().equals(id))
                index=i;
        }
        if(index>-1)
            return guest.get(index);
        else
            return null;
    }
    
    //Get the record of the current position from the Records
    public CRUD_1 getIndex(ArrayList<CRUD_1> guest, int pos){
      return guest.get(pos);
    }
    
    //Insert into the Records
    public void addRecord(ArrayList<CRUD_1> guest, CRUD_1 target){
    	guest.add(target);
    }
    
    //Update the Records
    public void setRecord(ArrayList<CRUD_1> guest, CRUD_1 target){
    	CRUD_1 current = getRecord(guest, target.getGuestID());
        if(current!=null){
        	current.setGuestName(target.getGuestName());
        	current.setGuestAddress(target.getGuestAddress());
        	current.setGuestPhoneNumber(target.getGuestPhoneNumber());
        	current.setGuestEmail(target.getGuestEmail());
        	current.setGuestGender(target.getGuestGender());
        }
    }
    
    //Deleting the Records using the ID
    public void deleteRecord(ArrayList<CRUD_1> guest, String id){
        int index=-1;
        for(int i=0;i<guest.size();i++){
            if(guest.get(i).getGuestID().equals(id)) 
                index=i;

        }
        if(index>-1)
        	guest.remove(index);
    }
    
    //Get the size of the ArrayList
    public int getCount(ArrayList<CRUD_1> guest){
        return guest.size();
    }
    
    //Check if the ID Exist in the Records
    public boolean isIdExist(ArrayList<CRUD_1> guest, String id){
        Boolean found=false;
        for(int i=0;i<guest.size();i++){
            if(guest.get(i).getGuestID().equals(id))
                found=true;
        }
        return found;
    }
    
    //Check if the same record exists in the Records 
    public boolean isRecordExist(ArrayList<CRUD_1> guest, CRUD_1 target){
       Boolean found=false;
        for(int i=0;i<guest.size();i++){
            if(guest.get(i).equalsGuest(target))
                found=true;
        }
        return found; 
    }
    

}
