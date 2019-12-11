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

public class RoomType extends HotelDetails {

    public RoomType(ArrayList<CRUD_1> roomType) {
    	super(roomType);
    }
    
    //Retrieve the Records
    public CRUD_1 getRecord(ArrayList<CRUD_1> roomType, String id){ 
        int index=-1; 
        for(int i=0;i<roomType.size();i++){
            if(roomType.get(i).getRoomTypeID().equals(id))
                index=i;
        }
        if(index>-1)
            return roomType.get(index);
        else
            return null;
    }
    
    //Get the record of the current position from the Records
    public CRUD_1 getIndex(ArrayList<CRUD_1> roomType, int pos){
      return roomType.get(pos);
    }
    
    //Insert into the Records
    public void addRecord(ArrayList<CRUD_1> roomType, CRUD_1 target){
    	roomType.add(target);
    }
    
    //Update the Records
    public void setRecord(ArrayList<CRUD_1> roomType, CRUD_1 target){
    	CRUD_1 current = getRecord(roomType, target.getRoomTypeID());
        if(current!=null){
        	current.setRoomTypeName(target.getRoomTypeName());
        }
    }
    
    //Deleting the Records using the ID
    public void deleteRecord(ArrayList<CRUD_1> roomType, String id){
        int index=-1;
        for(int i=0;i<roomType.size();i++){
            if(roomType.get(i).getRoomTypeID().equals(id)) 
                index=i;

        }
        if(index>-1)
        	roomType.remove(index);
    }
    
    //Get the size of the ArrayList
    public int getCount(ArrayList<CRUD_1> roomType){
        return roomType.size();
    }
    
    //Check if the ID Exist in the Records
    public boolean isIdExist(ArrayList<CRUD_1> roomType, String id){
        Boolean found=false;
        for(int i=0;i<roomType.size();i++){
            if(roomType.get(i).getRoomTypeID().equals(id))
                found=true;
        }
        return found;
    }
    
    //Check if the same record exists in the Records 
    public boolean isRecordExist(ArrayList<CRUD_1> roomType, CRUD_1 target){
       Boolean found=false;
        for(int i=0;i<roomType.size();i++){
            if(roomType.get(i).equalsRoomType(target))
                found=true;
        }
        return found; 
    }
    

}
