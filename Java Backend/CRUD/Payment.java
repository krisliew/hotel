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

public class Payment extends Services {

    public Payment(ArrayList<CRUD_2> payment) {
    	super(payment);
    }
    
    //Retrieve the Records
    public CRUD_2 getRecord(ArrayList<CRUD_2> payment, String paymentId, String serviceId, String reservationId, String orderId, String roomId, String guestId){ 
        int index=-1; 
        for(int i=0;i<payment.size();i++){
            if(payment.get(i).getPaymentID().equals(paymentId) && payment.get(i).getServiceID().equals(serviceId) && payment.get(i).getReservationID().equals(reservationId) && payment.get(i).getOrderID().equals(orderId) && payment.get(i).getRoomID().equals(roomId) && payment.get(i).getGuestID().equals(guestId))
                index=i;
        }
        if(index>-1)
            return payment.get(index);
        else
            return null;
    }
    
    //Get the record of the current position from the Records
    public CRUD_2 getIndex(ArrayList<CRUD_2> payment, int pos){
      return payment.get(pos);
    }
    
    //Insert into the Records
    public void addRecord(ArrayList<CRUD_2> payment, CRUD_2 target){
    	payment.add(target);
    }
    
    //Update the Records
    public void setRecord(ArrayList<CRUD_2> payment, CRUD_2 target){
    	CRUD_2 current = getRecord(payment, target.getPaymentID(), target.getServiceID(), target.getReservationID(), target.getOrderID(), target.getRoomID(), target.getGuestID());
        if(current!=null){
        	current.setSubtotalAmount(target.getSubtotalAmount());
        	current.setTipsAmount(target.getTipsAmount());
        	current.setTotalAmount(target.getTotalAmount());
        	current.setPaymentDate(target.getPaymentDate());
        	current.setPaymentStatus(target.getPaymentStatus());
        }
    }
    
    //Deleting the Records using the ID
    public void deleteRecord(ArrayList<CRUD_2> payment, String paymentId, String serviceId, String reservationId, String orderId, String roomId, String guestId){
        int index=-1;
        for(int i=0;i<payment.size();i++){
            if(payment.get(i).getPaymentID().equals(paymentId) && payment.get(i).getServiceID().equals(serviceId) && payment.get(i).getReservationID().equals(reservationId) && payment.get(i).getOrderID().equals(orderId) && payment.get(i).getRoomID().equals(roomId) && payment.get(i).getGuestID().equals(guestId))
                index=i;

        }
        if(index>-1)
        	payment.remove(index);
    }
    
    //Get the size of the ArrayList
    public int getCount(ArrayList<CRUD_2> payment){
        return payment.size();
    }
    
    //Check if the ID Exist in the Records
    public boolean isIdExist(ArrayList<CRUD_2> payment, String paymentId, String serviceId, String reservationId, String orderId, String roomId, String guestId){
        Boolean found=false;
        for(int i=0;i<payment.size();i++){
            if(payment.get(i).getPaymentID().equals(paymentId) && payment.get(i).getServiceID().equals(serviceId) && payment.get(i).getReservationID().equals(reservationId) && payment.get(i).getOrderID().equals(orderId) && payment.get(i).getRoomID().equals(roomId) && payment.get(i).getGuestID().equals(guestId))
                found=true;
        }
        return found;
    }
    
    //Check if the same record exists in the Records 
    public boolean isRecordExist(ArrayList<CRUD_2> payment, CRUD_2 target){
       Boolean found=false;
        for(int i=0;i<payment.size();i++){
            if(payment.get(i).equalsPayment(target))
                found=true;
        }
        return found; 
    }
    

}
