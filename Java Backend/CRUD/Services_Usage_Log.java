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

public class Services_Usage_Log extends Services {

    public Services_Usage_Log(ArrayList<CRUD_2> service_usage_log) {
    	super(service_usage_log);
    }
    
    //Retrieve the Records
    public CRUD_2 getRecord(ArrayList<CRUD_2> service_usage_log, String serviceId, String roomId, String guestId){ 
        int index=-1; 
        for(int i=0;i<service_usage_log.size();i++){
            if(service_usage_log.get(i).getServiceID().equals(serviceId) && service_usage_log.get(i).getRoomID().equals(roomId) && service_usage_log.get(i).getGuestID()
            		.equals(guestId))
                index=i;
        }
        if(index>-1)
            return service_usage_log.get(index);
        else
            return null;
    }
    
    //Get the record of the current position from the Records
    public CRUD_2 getIndex(ArrayList<CRUD_2> service_usage_log, int pos){
      return service_usage_log.get(pos);
    }
    
    //Insert into the Records
    public void addRecord(ArrayList<CRUD_2> service_usage_log, CRUD_2 target){
    	service_usage_log.add(target);
    }
    
    //Update the Records
    public void setRecord(ArrayList<CRUD_2> service_usage_log, CRUD_2 target){
    	CRUD_2 current = getRecord(service_usage_log, target.getServiceID(), target.getRoomID(), target.getGuestID());
        if(current!=null){
        	current.setUsageStartTime(target.getUsageStartTime());
        	current.setUsageEndTime(target.getUsageEndTime());
        	current.setServicesTotalPrice(target.getServicesTotalPrice());
        	current.setRemarks(target.getRemarks());
        }
    }
    
    //Deleting the Records using the ID
    public void deleteRecord(ArrayList<CRUD_2> service_usage_log, String serviceId, String roomId, String guestId){
        int index=-1;
        for(int i=0;i<service_usage_log.size();i++){
            if(service_usage_log.get(i).getServiceID().equals(serviceId) && service_usage_log.get(i).getRoomID().equals(roomId) && service_usage_log.get(i).getGuestID()
            		.equals(guestId)) 
                index=i;

        }
        if(index>-1)
        	service_usage_log.remove(index);
    }
    
    //Get the size of the ArrayList
    public int getCount(ArrayList<CRUD_2> service_usage_log){
        return service_usage_log.size();
    }
    
    //Check if the ID Exist in the Records
    public boolean isIdExist(ArrayList<CRUD_2> service_usage_log, String serviceId, String roomId, String guestId){
        Boolean found=false;
        for(int i=0;i<service_usage_log.size();i++){
            if(service_usage_log.get(i).getServiceID().equals(serviceId) && service_usage_log.get(i).getRoomID().equals(roomId) && service_usage_log.get(i).getGuestID()
            		.equals(guestId))
                found=true;
        }
        return found;
    }
    
    //Check if the same record exists in the Records 
    public boolean isRecordExist(ArrayList<CRUD_2> service_usage_log, CRUD_2 target){
       Boolean found=false;
        for(int i=0;i<service_usage_log.size();i++){
            if(service_usage_log.get(i).equalsServices_usage_log(target))
                found=true;
        }
        return found; 
    }
    

}
