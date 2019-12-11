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

public class Payment_Method extends Services {

    public Payment_Method(ArrayList<CRUD_2> payment_method) {
    	super(payment_method);
    }
    
    //Retrieve the Records
    public CRUD_2 getRecord(ArrayList<CRUD_2> payment_method, String payment_methodId, String paymentId, String guestId){ 
        int index=-1; 
        for(int i=0;i<payment_method.size();i++){
            if(payment_method.get(i).getPaymentMethodID().equals(payment_methodId) && payment_method.get(i).getPaymentID().equals(paymentId) && payment_method.get(i).getGuestID().equals(guestId))
                index=i;
        }
        if(index>-1)
            return payment_method.get(index);
        else
            return null;
    }
    
    //Get the record of the current position from the Records
    public CRUD_2 getIndex(ArrayList<CRUD_2> payment_method, int pos){
      return payment_method.get(pos);
    }
    
    //Insert into the Records
    public void addRecord(ArrayList<CRUD_2> payment_method, CRUD_2 target){
    	payment_method.add(target);
    }
    
    //Update the Records
    public void setRecord(ArrayList<CRUD_2> payment_method, CRUD_2 target){
    	CRUD_2 current = getRecord(payment_method, target.getPaymentMethodID(), target.getPaymentID(), target.getGuestID());
        if(current!=null){
        	current.setPaymentAmount(target.getPaymentAmount());
        }
    }
    
    //Deleting the Records using the ID
    public void deleteRecord(ArrayList<CRUD_2> payment_method, String payment_methodId, String paymentId, String guestId){
        int index=-1;
        for(int i=0;i<payment_method.size();i++){
            if(payment_method.get(i).getPaymentMethodID().equals(payment_methodId) && payment_method.get(i).getPaymentID().equals(paymentId) && payment_method.get(i).getGuestID().equals(guestId))
                index=i;

        }
        if(index>-1)
        	payment_method.remove(index);
    }
    
    //Get the size of the ArrayList
    public int getCount(ArrayList<CRUD_2> payment_method){
        return payment_method.size();
    }
    
    //Check if the ID Exist in the Records
    public boolean isIdExist(ArrayList<CRUD_2> payment_method, String payment_methodId, String paymentId, String guestId){
        Boolean found=false;
        for(int i=0;i<payment_method.size();i++){
            if(payment_method.get(i).getPaymentMethodID().equals(payment_methodId) && payment_method.get(i).getPaymentID().equals(paymentId) && payment_method.get(i).getGuestID().equals(guestId))
                found=true;
        }
        return found;
    }
    
    //Check if the same record exists in the Records 
    public boolean isRecordExist(ArrayList<CRUD_2> payment_method, CRUD_2 target){
       Boolean found=false;
        for(int i=0;i<payment_method.size();i++){
            if(payment_method.get(i).equalsPayment_method(target))
                found=true;
        }
        return found; 
    }
    

}
