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

public class Cash extends Services {

    public Cash(ArrayList<CRUD_2> cash) {
    	super(cash);
    }
    
    //Retrieve the Records
    public CRUD_2 getRecord(ArrayList<CRUD_2> cash, String payment_methodId){ 
        int index=-1; 
        for(int i=0;i<cash.size();i++){
            if(cash.get(i).getPaymentMethodID().equals(payment_methodId))
                index=i;
        }
        if(index>-1)
            return cash.get(index);
        else
            return null;
    }
    
    //Get the record of the current position from the Records
    public CRUD_2 getIndex(ArrayList<CRUD_2> cash, int pos){
      return cash.get(pos);
    }
    
    //Insert into the Records
    public void addRecord(ArrayList<CRUD_2> cash, CRUD_2 target){
    	cash.add(target);
    }
    
    //Update the Records
    public void setRecord(ArrayList<CRUD_2> cash, CRUD_2 target){
    	CRUD_2 current = getRecord(cash, target.getPaymentMethodID());
        if(current!=null){
        	current.setPaymentMethodName(target.getPaymentMethodName());
        }
    }
    
    //Deleting the Records using the ID
    public void deleteRecord(ArrayList<CRUD_2> cash, String payment_methodId){
        int index=-1;
        for(int i=0;i<cash.size();i++){
            if(cash.get(i).getPaymentMethodID().equals(payment_methodId))
                index=i;

        }
        if(index>-1)
        	cash.remove(index);
    }
    
    //Get the size of the ArrayList
    public int getCount(ArrayList<CRUD_2> cash){
        return cash.size();
    }
    
    //Check if the ID Exist in the Records
    public boolean isIdExist(ArrayList<CRUD_2> cash, String payment_methodId){
        Boolean found=false;
        for(int i=0;i<cash.size();i++){
            if(cash.get(i).getPaymentMethodID().equals(payment_methodId))
                found=true;
        }
        return found;
    }
    
    //Check if the same record exists in the Records 
    public boolean isRecordExist(ArrayList<CRUD_2> cash, CRUD_2 target){
       Boolean found=false;
        for(int i=0;i<cash.size();i++){
            if(cash.get(i).equalsCash(target))
                found=true;
        }
        return found; 
    }
    

}
