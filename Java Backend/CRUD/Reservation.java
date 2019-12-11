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

public class Reservation extends HotelDetails {

    public Reservation(ArrayList<CRUD_1> reservation) {
    	super(reservation);
    }
    
    //Retrieve the Records
    public CRUD_1 getRecord(ArrayList<CRUD_1> reservation, String reservationId, String roomId, String guestId){ 
        int index=-1; 
        for(int i=0;i<reservation.size();i++){
            if(reservation.get(i).getReservationID().equals(reservationId) && reservation.get(i).getRoomID().equals(roomId) && reservation.get(i).getGuestID()
            		.equals(guestId))
                index=i;
        }
        if(index>-1)
            return reservation.get(index);
        else
            return null;
    }
    
    //Get the record of the current position from the Records
    public CRUD_1 getIndex(ArrayList<CRUD_1> reservation, int pos){
      return reservation.get(pos);
    }
    
    //Insert into the Records
    public void addRecord(ArrayList<CRUD_1> reservation, CRUD_1 target){
    	reservation.add(target);
    }
    
    //Update the Records
    public void setRecord(ArrayList<CRUD_1> reservation, CRUD_1 target){
    	CRUD_1 current = getRecord(reservation, target.getReservationID(), target.getRoomID(), target.getGuestID());
        if(current!=null){
        	current.setCheckInDate(target.getCheckInDate());
        	current.setArrivalTime(target.getArrivalTime());
        	current.setCheckOutDate(target.getCheckOutDate());
        	current.setAdultPax(target.getAdultPax());
        	current.setChildPax(target.getChildPax());
        	current.setReservationEmailStatus(target.getReservationEmailStatus());
        	current.setReservationTotalPrice(target.getReservationTotalPrice());
        	current.setSpecialRequest(target.getSpecialRequest());
        }
    }
    
    //Deleting the Records using the ID
    public void deleteRecord(ArrayList<CRUD_1> reservation, String reservationId, String roomId, String guestId){
        int index=-1;
        for(int i=0;i<reservation.size();i++){
            if(reservation.get(i).getReservationID().equals(reservationId) && reservation.get(i).getRoomID().equals(roomId) && reservation.get(i).getGuestID()
            		.equals(guestId)) 
                index=i;

        }
        if(index>-1)
        	reservation.remove(index);
    }
    
    //Get the size of the ArrayList
    public int getCount(ArrayList<CRUD_1> reservation){
        return reservation.size();
    }
    
    //Check if the ID Exist in the Records
    public boolean isIdExist(ArrayList<CRUD_1> reservation, String reservationId, String roomId, String guestId){
        Boolean found=false;
        for(int i=0;i<reservation.size();i++){
            if(reservation.get(i).getReservationID().equals(reservationId) && reservation.get(i).getRoomID().equals(roomId) && reservation.get(i).getGuestID()
            		.equals(guestId))
                found=true;
        }
        return found;
    }
    
    //Check if the same record exists in the Records 
    public boolean isRecordExist(ArrayList<CRUD_1> reservation, CRUD_1 target){
       Boolean found=false;
        for(int i=0;i<reservation.size();i++){
            if(reservation.get(i).equalsReservation(target))
                found=true;
        }
        return found; 
    }
    

}
