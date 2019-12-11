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

public class Room extends HotelDetails {

    public Room(ArrayList<CRUD_1> room) {
    	super(room);
    }
    
    //Retrieve the Records
    public CRUD_1 getRecord(ArrayList<CRUD_1> room, String roomId, String roomTypeId){ 
        int index=-1; 
        for(int i=0;i<room.size();i++){
            if(room.get(i).getRoomID().equals(roomId) && room.get(i).getRoomTypeID().equals(roomTypeId))
                index=i;
        }
        if(index>-1)
            return room.get(index);
        else
            return null;
    }
    
    //Get the record of the current position from the Records
    public CRUD_1 getIndex(ArrayList<CRUD_1> room, int pos){
      return room.get(pos);
    }
    
    //Insert into the Records
    public void addRecord(ArrayList<CRUD_1> room, CRUD_1 target){
    	room.add(target);
    }
    
    //Update the Records
    public void setRecord(ArrayList<CRUD_1> room, CRUD_1 target){
    	CRUD_1 current = getRecord(room, target.getRoomID(), target.getRoomTypeID());
        if(current!=null){
        	current.setRoomName(target.getRoomName());
        	current.setRoomAvailability(target.getRoomAvailability());
        	current.setRoomAdultCapacity(target.getRoomAdultCapacity());
        	current.setRoomChildCapacity(target.getRoomChildCapacity());
        	current.setBedKingSizeQuantity(target.getBedKingSizeQuantity());
        	current.setBedQueenSizeQuantity(target.getBedQueenSizeQuantity());
        	current.setBedSingleSizeQuantity(target.getBedSingleSizeQuantity());
        	current.setSmoking(target.getSmoking());
        	current.setShower(target.getShower());
        	current.setWifi(target.getWifi());
        	current.setAircon(target.getAircon());
        	current.setLeasingPrice(target.getLeasingPrice());
        	current.setRentingPrice(target.getRentingPrice());
        }
    }
    
    //Deleting the Records using the ID
    public void deleteRecord(ArrayList<CRUD_1> room, String roomId, String roomTypeId){
        int index=-1;
        for(int i=0;i<room.size();i++){
            if(room.get(i).getRoomID().equals(roomId) && room.get(i).getRoomTypeID().equals(roomTypeId)) 
                index=i;

        }
        if(index>-1)
        	room.remove(index);
    }
    
    //Get the size of the ArrayList
    public int getCount(ArrayList<CRUD_1> room){
        return room.size();
    }
    
    //Check if the ID Exist in the Records
    public boolean isIdExist(ArrayList<CRUD_1> room, String roomId, String roomTypeId){
        Boolean found=false;
        for(int i=0;i<room.size();i++){
            if(room.get(i).getRoomID().equals(roomId) && room.get(i).getRoomTypeID().equals(roomTypeId))
                found=true;
        }
        return found;
    }
    
    //Check if the same record exists in the Records 
    public boolean isRecordExist(ArrayList<CRUD_1> room, CRUD_1 target){
       Boolean found=false;
        for(int i=0;i<room.size();i++){
            if(room.get(i).equalsRoom(target))
                found=true;
        }
        return found; 
    }
    

}
