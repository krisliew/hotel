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

public class Amenities extends HotelDetails {

    public Amenities(ArrayList<CRUD_1> amenity) {
    	super(amenity);
    }
    
    //Retrieve the Records
    public CRUD_1 getRecord(ArrayList<CRUD_1> amenity, String id){ 
        int index=-1; 
        for(int i=0;i<amenity.size();i++){
            if(amenity.get(i).getAmenityID().equals(id))
                index=i;
        }
        if(index>-1)
            return amenity.get(index);
        else
            return null;
    }
    
    //Get the record of the current position from the Records
    public CRUD_1 getIndex(ArrayList<CRUD_1> amenity, int pos){
      return amenity.get(pos);
    }
    
    //Insert into the Records
    public void addRecord(ArrayList<CRUD_1> amenity, CRUD_1 target){
    	amenity.add(target);
    }
    
    //Update the Records
    public void setRecord(ArrayList<CRUD_1> amenity, CRUD_1 target){
    	CRUD_1 current = getRecord(amenity, target.getAmenityID());
        if(current!=null){
        	current.setAmenityName(target.getAmenityName());
        	current.setAmenityDescription(target.getAmenityDescription());
        	current.setAmenityPrice(target.getAmenityPrice());
        	current.setAmenityOperationStartTime(target.getAmenityOperationStartTime());
        	current.setAmenityOperationEndTime(target.getAmenityOperationEndTime());
        	current.setAmenityRules(target.getAmenityRules());
        }
    }
    
    //Deleting the Records using the ID
    public void deleteRecord(ArrayList<CRUD_1> amenity, String id){
        int index=-1;
        for(int i=0;i<amenity.size();i++){
            if(amenity.get(i).getAmenityID().equals(id)) 
                index=i;

        }
        if(index>-1)
        	amenity.remove(index);
    }
    
    //Get the size of the ArrayList
    public int getCount(ArrayList<CRUD_1> amenity){
        return amenity.size();
    }
    
    //Check if the ID Exist in the Records
    public boolean isIdExist(ArrayList<CRUD_1> amenity, String id){
        Boolean found=false;
        for(int i=0;i<amenity.size();i++){
            if(amenity.get(i).getAmenityID().equals(id))
                found=true;
        }
        return found;
    }
    
    //Check if the same record exists in the Records 
    public boolean isRecordExist(ArrayList<CRUD_1> amenity, CRUD_1 target){
       Boolean found=false;
        for(int i=0;i<amenity.size();i++){
            if(amenity.get(i).equalsAmenities(target))
                found=true;
        }
        return found; 
    }
    

}
