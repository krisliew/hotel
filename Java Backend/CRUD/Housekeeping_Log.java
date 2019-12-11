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

public class Housekeeping_Log extends Services {

    public Housekeeping_Log(ArrayList<CRUD_2> housekeeping_log) {
    	super(housekeeping_log);
    }
    
    //Retrieve the Records
    public CRUD_2 getRecord(ArrayList<CRUD_2> housekeeping_log, String housekeepingId, String roomId){ 
        int index=-1; 
        for(int i=0;i<housekeeping_log.size();i++){
            if(housekeeping_log.get(i).getHousekeepingID().equals(housekeepingId) && housekeeping_log.get(i).getRoomID().equals(roomId))
                index=i;
        }
        if(index>-1)
            return housekeeping_log.get(index);
        else
            return null;
    }
    
    //Get the record of the current position from the Records
    public CRUD_2 getIndex(ArrayList<CRUD_2> housekeeping_log, int pos){
      return housekeeping_log.get(pos);
    }
    
    //Insert into the Records
    public void addRecord(ArrayList<CRUD_2> housekeeping_log, CRUD_2 target){
    	housekeeping_log.add(target);
    }
    
    //Update the Records
    public void setRecord(ArrayList<CRUD_2> housekeeping_log, CRUD_2 target){
    	CRUD_2 current = getRecord(housekeeping_log, target.getHousekeepingID(), target.getRoomID());
        if(current!=null){
        	current.setQuantityUsed(target.getQuantityUsed());
        	current.setDateOfLog(target.getDateOfLog());
        	current.setHousekeepingStatus(target.getHousekeepingStatus());
        	current.setRemarks(target.getRemarks());
        }
    }
    
    //Deleting the Records using the ID
    public void deleteRecord(ArrayList<CRUD_2> housekeeping_log, String housekeepingId, String roomId){
        int index=-1;
        for(int i=0;i<housekeeping_log.size();i++){
            if(housekeeping_log.get(i).getHousekeepingID().equals(housekeepingId) && housekeeping_log.get(i).getRoomID().equals(roomId))
                index=i;

        }
        if(index>-1)
        	housekeeping_log.remove(index);
    }
    
    //Get the size of the ArrayList
    public int getCount(ArrayList<CRUD_2> housekeeping_log){
        return housekeeping_log.size();
    }
    
    //Check if the ID Exist in the Records
    public boolean isIdExist(ArrayList<CRUD_2> housekeeping_log, String housekeepingId, String roomId){
        Boolean found=false;
        for(int i=0;i<housekeeping_log.size();i++){
            if(housekeeping_log.get(i).getHousekeepingID().equals(housekeepingId) && housekeeping_log.get(i).getRoomID().equals(roomId))
                found=true;
        }
        return found;
    }
    
    //Check if the same record exists in the Records 
    public boolean isRecordExist(ArrayList<CRUD_2> housekeeping_log, CRUD_2 target){
       Boolean found=false;
        for(int i=0;i<housekeeping_log.size();i++){
            if(housekeeping_log.get(i).equalsHousekeeping_log(target))
                found=true;
        }
        return found; 
    }
    

}
