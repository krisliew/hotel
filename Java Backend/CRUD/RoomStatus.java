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

public class RoomStatus extends HotelDetails {

    public RoomStatus(ArrayList<CRUD_1> roomStatus) {
    	super(roomStatus);
    }
    
    //Retrieve the Records
    public CRUD_1 getRecord(ArrayList<CRUD_1> roomStatus, String roomId){ 
        int index=-1; 
        for(int i=0;i<roomStatus.size();i++){
            if(roomStatus.get(i).getRoomID().equals(roomId))
                index=i;
        }
        if(index>-1)
            return roomStatus.get(index);
        else
            return null;
    }
    
    //Get the record of the current position from the Records
    public CRUD_1 getIndex(ArrayList<CRUD_1> roomStatus, int pos){
      return roomStatus.get(pos);
    }
    
    //Insert into the Records
    public void addRecord(ArrayList<CRUD_1> roomStatus, CRUD_1 target){
    	roomStatus.add(target);
    }
    
    //Update the Records
    public void setRecord(ArrayList<CRUD_1> roomStatus, CRUD_1 target){
    	CRUD_1 current = getRecord(roomStatus, target.getRoomID());
        if(current!=null){
        	current.setRoomStatus(target.getRoomStatus());
        	current.setRemarks(target.getRemarks());
        }
    }
    
    //Deleting the Records using the ID
    public void deleteRecord(ArrayList<CRUD_1> roomStatus, String roomId){
        int index=-1;
        for(int i=0;i<roomStatus.size();i++){
            if(roomStatus.get(i).getRoomID().equals(roomId))
                index=i;

        }
        if(index>-1)
        	roomStatus.remove(index);
    }
    
    //Get the size of the ArrayList
    public int getCount(ArrayList<CRUD_1> roomStatus){
        return roomStatus.size();
    }
    
    //Check if the ID Exist in the Records
    public boolean isIdExist(ArrayList<CRUD_1> roomStatus, String roomId){
        Boolean found=false;
        for(int i=0;i<roomStatus.size();i++){
            if(roomStatus.get(i).getRoomID().equals(roomId))
                found=true;
        }
        return found;
    }
    
    //Check if the same record exists in the Records 
    public boolean isRecordExist(ArrayList<CRUD_1> roomStatus, CRUD_1 target){
       Boolean found=false;
        for(int i=0;i<roomStatus.size();i++){
            if(roomStatus.get(i).equalsRoomStatus(target))
                found=true;
        }
        return found; 
    }
    

}
