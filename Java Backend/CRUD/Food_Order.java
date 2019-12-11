package CRUD;

import java.util.ArrayList;

public class Food_Order extends HotelDetails {

    public Food_Order(ArrayList<CRUD_1> food_order) {
    	super(food_order);
    }
    
    //Retrieve the Records
    public CRUD_1 getRecord(ArrayList<CRUD_1> food_order, String food_orderId, String menuId, String roomId, String guestId){ 
        int index=-1; 
        for(int i=0;i<food_order.size();i++){
            if(food_order.get(i).getOrderID().equals(food_orderId) && food_order.get(i).getMenuID().equals(menuId) && food_order.get(i).getRoomID().equals(roomId) && food_order.get(i).getGuestID()
            		.equals(guestId))
                index=i;
        }
        if(index>-1)
            return food_order.get(index);
        else
            return null;
    }
    
    //Get the record of the current position from the Records
    public CRUD_1 getIndex(ArrayList<CRUD_1> food_order, int pos){
      return food_order.get(pos);
    }
    
    //Insert into the Records
    public void addRecord(ArrayList<CRUD_1> food_order, CRUD_1 target){
    	food_order.add(target);
    }
    
    //Update the Records
    public void setRecord(ArrayList<CRUD_1> food_order, CRUD_1 target){
    	System.out.println("Working final");
    	CRUD_1 current = getRecord(food_order, target.getOrderID(), target.getMenuID(), target.getRoomID(), target.getGuestID());
        if(current!=null){
        	current.setQuantity(target.getQuantity());
        	current.setOrderTotalPrice(target.getOrderTotalPrice());
        	current.setRemarks(target.getRemarks());
        }
    }
    
    //Deleting the Records using the ID
    public void deleteRecord(ArrayList<CRUD_1> food_order, String food_orderId, String menuId, String roomId, String guestId){
        int index=-1;
        for(int i=0;i<food_order.size();i++){
            if(food_order.get(i).getMenuID().equals(food_orderId) && food_order.get(i).getMenuID().equals(menuId) && food_order.get(i).getRoomID().equals(roomId) && food_order.get(i).getGuestID()
            		.equals(guestId))
                index=i;

        }
        if(index>-1)
        	food_order.remove(index);
    }
    
    //Get the size of the ArrayList
    public int getCount(ArrayList<CRUD_1> food_order){
        return food_order.size();
    }
    
    //Check if the ID Exist in the Records
    public boolean isIdExist(ArrayList<CRUD_1> food_order, String food_orderId, String menuId, String roomId, String guestId){
        Boolean found=false;
        for(int i=0;i<food_order.size();i++){
            if(food_order.get(i).getMenuID().equals(food_orderId) && food_order.get(i).getMenuID().equals(menuId) && food_order.get(i).getRoomID().equals(roomId) && food_order.get(i).getGuestID()
            		.equals(guestId))
                found=true;
        }
        return found;
    }
    
    //Check if the same record exists in the Records 
    public boolean isRecordExist(ArrayList<CRUD_1> food_order, CRUD_1 target){
       Boolean found=false;
        for(int i=0;i<food_order.size();i++){
            if(food_order.get(i).equalsFood_order(target))
                found=true;
        }
        return found; 
    }
    

}
