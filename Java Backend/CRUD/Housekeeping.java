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

public class Housekeeping extends Services {

    public Housekeeping(ArrayList<CRUD_2> housekeeping) {
    	super(housekeeping);
    }
    
    //Retrieve the Records
    public CRUD_2 getRecord(ArrayList<CRUD_2> housekeeping, String id){ 
        int index=-1; 
        for(int i=0;i<housekeeping.size();i++){
            if(housekeeping.get(i).getHousekeepingID().equals(id))
                index=i;
        }
        if(index>-1)
            return housekeeping.get(index);
        else
            return null;
    }
    
    //Get the record of the current position from the Records
    public CRUD_2 getIndex(ArrayList<CRUD_2> housekeeping, int pos){
      return housekeeping.get(pos);
    }
    
    //Insert into the Records
    public void addRecord(ArrayList<CRUD_2> housekeeping, CRUD_2 target){
    	housekeeping.add(target);
    }
    
    //Update the Records
    public void setRecord(ArrayList<CRUD_2> housekeeping, CRUD_2 target){
    	CRUD_2 current = getRecord(housekeeping, target.getHousekeepingID());
        if(current!=null){
        	current.setHousekeepingName(target.getHousekeepingName());
        	current.setRemarks(target.getRemarks());
        }
    }
    
    //Deleting the Records using the ID
    public void deleteRecord(ArrayList<CRUD_2> housekeeping, String id){
        int index=-1;
        for(int i=0;i<housekeeping.size();i++){
            if(housekeeping.get(i).getHousekeepingID().equals(id)) 
                index=i;

        }
        if(index>-1)
        	housekeeping.remove(index);
    }
    
    //Get the size of the ArrayList
    public int getCount(ArrayList<CRUD_2> housekeeping){
        return housekeeping.size();
    }
    
    //Check if the ID Exist in the Records
    public boolean isIdExist(ArrayList<CRUD_2> housekeeping, String id){
        Boolean found=false;
        for(int i=0;i<housekeeping.size();i++){
            if(housekeeping.get(i).getHousekeepingID().equals(id))
                found=true;
        }
        return found;
    }
    
    //Check if the same record exists in the Records 
    public boolean isRecordExist(ArrayList<CRUD_2> housekeeping, CRUD_2 target){
       Boolean found=false;
        for(int i=0;i<housekeeping.size();i++){
            if(housekeeping.get(i).equalsHousekeeping(target))
                found=true;
        }
        return found; 
    }
    

}
