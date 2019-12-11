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

public class Credit_Card extends Services {

    public Credit_Card(ArrayList<CRUD_2> credit_card) {
    	super(credit_card);
    }
    
    //Retrieve the Records
    public CRUD_2 getRecord(ArrayList<CRUD_2> credit_card, String payment_methodId){ 
        int index=-1; 
        for(int i=0;i<credit_card.size();i++){
            if(credit_card.get(i).getPaymentMethodID().equals(payment_methodId))
                index=i;
        }
        if(index>-1)
            return credit_card.get(index);
        else
            return null;
    }
    
    //Get the record of the current position from the Records
    public CRUD_2 getIndex(ArrayList<CRUD_2> credit_card, int pos){
      return credit_card.get(pos);
    }
    
    //Insert into the Records
    public void addRecord(ArrayList<CRUD_2> credit_card, CRUD_2 target){
    	credit_card.add(target);
    }
    
    //Update the Records
    public void setRecord(ArrayList<CRUD_2> credit_card, CRUD_2 target){
    	CRUD_2 current = getRecord(credit_card, target.getPaymentMethodID());
        if(current!=null){
        	current.setPaymentMethodName(target.getPaymentMethodName());
        	current.setCreditCardHolderName(target.getCreditCardHolderName());
        	current.setCreditCardBankName(target.getCreditCardBankName());
        	current.setCreditCardBalance(target.getCreditCardBalance());
        	current.setCreditCardExpiryMonth(target.getCreditCardExpiryMonth());
        	current.setCreditCardExpiryYear(target.getCreditCardExpiryYear());
        }
    }
    
    //Deleting the Records using the ID
    public void deleteRecord(ArrayList<CRUD_2> credit_card, String payment_methodId){
        int index=-1;
        for(int i=0;i<credit_card.size();i++){
            if(credit_card.get(i).getPaymentMethodID().equals(payment_methodId))
                index=i;

        }
        if(index>-1)
        	credit_card.remove(index);
    }
    
    //Get the size of the ArrayList
    public int getCount(ArrayList<CRUD_2> credit_card){
        return credit_card.size();
    }
    
    //Check if the ID Exist in the Records
    public boolean isIdExist(ArrayList<CRUD_2> credit_card, String payment_methodId){
        Boolean found=false;
        for(int i=0;i<credit_card.size();i++){
            if(credit_card.get(i).getPaymentMethodID().equals(payment_methodId))
                found=true;
        }
        return found;
    }
    
    //Check if the same record exists in the Records 
    public boolean isRecordExist(ArrayList<CRUD_2> credit_card, CRUD_2 target){
       Boolean found=false;
        for(int i=0;i<credit_card.size();i++){
            if(credit_card.get(i).equalsCredit_card(target))
                found=true;
        }
        return found; 
    }
    

}
