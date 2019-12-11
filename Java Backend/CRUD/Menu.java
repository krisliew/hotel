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

public class Menu extends HotelDetails {

    public Menu(ArrayList<CRUD_1> menu) {
    	super(menu);
    }
    
    //Retrieve the Records
    public CRUD_1 getRecord(ArrayList<CRUD_1> menu, String id){ 
        int index=-1; 
        for(int i=0;i<menu.size();i++){
            if(menu.get(i).getMenuID().equals(id))
                index=i;
        }
        if(index>-1)
            return menu.get(index);
        else
            return null;
    }
    
    //Get the record of the current position from the Records
    public CRUD_1 getIndex(ArrayList<CRUD_1> menu, int pos){
      return menu.get(pos);
    }
    
    //Insert into the Records
    public void addRecord(ArrayList<CRUD_1> menu, CRUD_1 target){
    	menu.add(target);
    }
    
    //Update the Records
    public void setRecord(ArrayList<CRUD_1> menu, CRUD_1 target){
    	CRUD_1 current = getRecord(menu, target.getMenuID());
        if(current!=null){
        	current.setMenuName(target.getMenuName());
        	current.setMenuDescription(target.getMenuDescription());
        	current.setMenuPrice(target.getMenuPrice());
        }
    }
    
    //Deleting the Records using the ID
    public void deleteRecord(ArrayList<CRUD_1> menu, String id){
        int index=-1;
        for(int i=0;i<menu.size();i++){
            if(menu.get(i).getMenuID().equals(id)) 
                index=i;

        }
        if(index>-1)
        	menu.remove(index);
    }
    
    //Get the size of the ArrayList
    public int getCount(ArrayList<CRUD_1> menu){
        return menu.size();
    }
    
    //Check if the ID Exist in the Records
    public boolean isIdExist(ArrayList<CRUD_1> menu, String id){
        Boolean found=false;
        for(int i=0;i<menu.size();i++){
            if(menu.get(i).getMenuID().equals(id))
                found=true;
        }
        return found;
    }
    
    //Check if the same record exists in the Records 
    public boolean isRecordExist(ArrayList<CRUD_1> menu, CRUD_1 target){
       Boolean found=false;
        for(int i=0;i<menu.size();i++){
            if(menu.get(i).equalsMenu(target))
                found=true;
        }
        return found; 
    }
    

}
