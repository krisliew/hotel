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

public class Amenities_Usage_Log extends HotelDetails {

    public Amenities_Usage_Log(ArrayList<CRUD_1> amenity_usage_log) {
    	super(amenity_usage_log);
    }
    
    //Retrieve the Records
    public CRUD_1 getRecord(ArrayList<CRUD_1> amenity_usage_log, String amenityId, String roomId, String guestId){ 
        int index=-1; 
        for(int i=0;i<amenity_usage_log.size();i++){
            if(amenity_usage_log.get(i).getAmenityID().equals(amenityId) && amenity_usage_log.get(i).getRoomID().equals(roomId) && amenity_usage_log.get(i).getGuestID()
            		.equals(guestId))
                index=i;
        }
        if(index>-1)
            return amenity_usage_log.get(index);
        else
            return null;
    }
    
    //Get the record of the current position from the Records
    public CRUD_1 getIndex(ArrayList<CRUD_1> amenity_usage_log, int pos){
      return amenity_usage_log.get(pos);
    }
    
    //Insert into the Records
    public void addRecord(ArrayList<CRUD_1> amenity_usage_log, CRUD_1 target){
    	amenity_usage_log.add(target);
    }
    
    //Update the Records
    public void setRecord(ArrayList<CRUD_1> amenity_usage_log, CRUD_1 target){
    	CRUD_1 current = getRecord(amenity_usage_log, target.getAmenityID(), target.getRoomID(), target.getGuestID());
        if(current!=null){
        	current.setUsageStartTime(target.getUsageStartTime());
        	current.setUsageEndTime(target.getUsageEndTime());
        	current.setAmenityTotalPrice(target.getAmenityTotalPrice());
        	current.setRemarks(target.getRemarks());
        }
    }
    
    //Deleting the Records using the ID
    public void deleteRecord(ArrayList<CRUD_1> amenity_usage_log, String amenityId, String roomId, String guestId){
        int index=-1;
        for(int i=0;i<amenity_usage_log.size();i++){
            if(amenity_usage_log.get(i).getAmenityID().equals(amenityId) && amenity_usage_log.get(i).getRoomID().equals(roomId) && amenity_usage_log.get(i).getGuestID()
            		.equals(guestId)) 
                index=i;

        }
        if(index>-1)
        	amenity_usage_log.remove(index);
    }
    
    //Get the size of the ArrayList
    public int getCount(ArrayList<CRUD_1> amenity_usage_log){
        return amenity_usage_log.size();
    }
    
    //Check if the ID Exist in the Records
    public boolean isIdExist(ArrayList<CRUD_1> amenity_usage_log, String amenityId, String roomId, String guestId){
        Boolean found=false;
        for(int i=0;i<amenity_usage_log.size();i++){
            if(amenity_usage_log.get(i).getAmenityID().equals(amenityId) && amenity_usage_log.get(i).getRoomID().equals(roomId) && amenity_usage_log.get(i).getGuestID()
            		.equals(guestId))
                found=true;
        }
        return found;
    }
    
    //Check if the same record exists in the Records 
    public boolean isRecordExist(ArrayList<CRUD_1> amenity_usage_log, CRUD_1 target){
       Boolean found=false;
        for(int i=0;i<amenity_usage_log.size();i++){
            if(amenity_usage_log.get(i).equalsAmenities_usage_log(target))
                found=true;
        }
        return found; 
    }
    

}
