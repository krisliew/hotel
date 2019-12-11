package CRUD;

import java.util.ArrayList;

public class CRUD_1 {

	// variables for hotel
	private String hotelName, hotelAddress, hotelPhoneNumber, hotelEmail;
	
	//variables for roomType
	private String roomTypeID, roomTypeName;
	
	//variables for room
	String roomID, roomName, smoking, shower, wifi, aircon;
	int roomAvailability, roomAdultCapacity, roomChildCapacity, bedKingSizeQuantity, bedQueenSizeQuantity, bedSingleSizeQuantity;
	double leasingPrice, rentingPrice;
	
	//variable for guest
	String guestID, guestName, guestAddress, guestPhoneNumber, guestEmail, guestGender;
	
	//variable for amenities
	String amenityID, amenityName, amenityDescription, amenityOperationStartTime, amenityOperationEndTime, amenityRules;
	double amenityPrice;
	
	//variable for amenities_usage_log
	String usageStartTime, usageEndTime, remarks;
	double amenityTotalPrice;
	
	//variable for reservation
	String reservationID, checkInDate, arrivalTime, checkOutDate, reservationEmailStatus, specialRequest;
	int adultPax, childPax;
	double reservationTotalPrice;
	
	//variable for menu
	String menuID, menuName, menuDescription;
	double menuPrice;
	
	//variable for food_order
	String orderID;
	int quantity;
	double orderTotalPrice;
	
	//variable for check_in_form
	String checkInFormID;
	int signature;
	
	//variable for check_in
	String checkInID, paymentStatus;
	double depositAmount;
	
	//variable roomStatus
	String roomStatus;
	
	

	
	
	//hotel Constructor
	public CRUD_1(String hotelName, String hotelAddress, String hotelPhoneNumber, String hotelEmail){
		this.hotelName = hotelName;
		this.hotelAddress = hotelAddress;
		this.hotelPhoneNumber = hotelPhoneNumber;
		this.hotelEmail = hotelEmail;
	}

	

	//roomType Constructor
	public CRUD_1(String roomTypeID, String roomTypeName) {
		this.roomTypeID = roomTypeID;
		this.roomTypeName = roomTypeName;	
	}
	
	
	
	//room Constructor
	public CRUD_1(String roomID, String roomTypeID, String roomName, int roomAvailability, int roomAdultCapacity, int roomChildCapacity, int bedKingSizeQuantity, int bedQueenSizeQuantity,  int bedSingleSizeQuantity, String smoking, String shower, String wifi, String aircon, double leasingPrice, double rentingPrice ) {
		this.roomID = roomID;
		this.roomTypeID = roomTypeID;
		this.roomName = roomName;
		this.roomAvailability = roomAvailability;
		this.roomAdultCapacity = roomAdultCapacity;
		this.roomChildCapacity = roomChildCapacity;
		this.bedKingSizeQuantity = bedKingSizeQuantity;
		this.bedQueenSizeQuantity = bedQueenSizeQuantity;
		this.bedSingleSizeQuantity = bedSingleSizeQuantity;
		this.smoking = smoking;
		this.shower = shower;
		this.wifi = wifi;
		this.aircon = aircon;
		this.leasingPrice = leasingPrice;
		this.rentingPrice = rentingPrice;
	}
	
	
	
	//guest Constructor
	public CRUD_1(String guestID, String guestName, String guestAddress, String guestPhoneNumber, String guestEmail, String guestGender) {
		this.guestID = guestID;
		this.guestName = guestName;	
		this.guestAddress = guestAddress;
		this.guestPhoneNumber = guestPhoneNumber;
		this.guestEmail = guestEmail;
		this.guestGender = guestGender;
	}
	
	
	
	//amenities Constructor
	public CRUD_1(String amenityID, String amenityName, String amenityDescription, double amenityPrice, String amenityOperationStartTime, String amenityOperationEndTime, String amenityRules) {
		this.amenityID = amenityID;
		this.amenityName = amenityName;	
		this.amenityDescription = amenityDescription;
		this.amenityPrice = amenityPrice;
		this.amenityOperationStartTime = amenityOperationStartTime;
		this.amenityOperationEndTime = amenityOperationEndTime;
		this.amenityRules = amenityRules;
	}
		
		
		
	//amenities_usage_log Constructor
	public CRUD_1(String amenityID, String roomID, String guestID, String usageStartTime, String usageEndTime, double amenityTotalPrice, String remarks) {
		this.amenityID = amenityID;
		this.roomID = roomID;	
		this.guestID = guestID;
		this.usageStartTime = usageStartTime;
		this.usageEndTime = usageEndTime;
		this.amenityTotalPrice = amenityTotalPrice;
		this.remarks = remarks;
	}
	
	
	
	//reservation Constructor
	public CRUD_1(String reservationID, String roomID, String guestID, String checkInDate, String arrivalTime, String checkOutDate, int adultPax, int childPax, String reservationEmailStatus, double reservationTotalPrice, String specialRequest) {
		this.reservationID = reservationID;
		this.roomID = roomID;
		this.guestID = guestID;
		this.checkInDate = checkInDate;
		this.arrivalTime = arrivalTime;
		this.checkOutDate = checkOutDate;
		this.adultPax = adultPax;	
		this.childPax = childPax;
		this.reservationEmailStatus = reservationEmailStatus;
		this.reservationTotalPrice = reservationTotalPrice;
		this.specialRequest = specialRequest;
	}
	
	
	
	//menu Constructor
	public CRUD_1(String menuID, String menuName, String menuDescription, double menuPrice) {
		this.menuID = menuID;
		this.menuName = menuName;	
		this.menuDescription = menuDescription;
		this.menuPrice = menuPrice;	
	}
	
	
	
	//food_order Constructor
	public CRUD_1(String orderID, String menuID, String roomID, String guestID, int quantity, double orderTotalPrice, String remarks) {
		this.orderID = orderID;
		this.menuID = menuID;	
		this.roomID = roomID;
		this.guestID = guestID;	
		this.quantity = quantity;
		this.orderTotalPrice = orderTotalPrice;	
		this.remarks = remarks;
	}
	
	
	
	//check_in_form Constructor
	public CRUD_1(String checkInFormID, String reservationID, int signature) {
		this.checkInFormID = checkInFormID;
		this.reservationID = reservationID;	
		this.signature = signature;
	}
	
	
	
	//check_in Constructor
	public CRUD_1(String checkInID, String checkInFormID, double depositAmount, String paymentStatus) {
		this.checkInID = checkInID;
		this.checkInFormID = checkInFormID;	
		this.depositAmount = depositAmount;
		this.paymentStatus = paymentStatus;	
	}
	
	
	
	//roomStatus Constructor
	public CRUD_1(String roomID, String roomStatus, String remarks) {
		this.roomID = roomID;
		this.roomStatus = roomStatus;	
		this.remarks = remarks;
	}
	
	
	
	



	//Used to compare the object passed in with the existing records to see of they are equal
	
	public boolean equalHotelDetails(CRUD_1 target){
        if(this.hotelName.equals(target.getHotelName()) && 
    		this.hotelAddress.equals(target.getHotelAddress()) && 
    		this.hotelPhoneNumber.equals(target.getHotelPhoneNumber()) && 
    		this.hotelEmail.equals(target.getHotelEmail()))
            return true;
        else
            return false;
    }


	public boolean equalsRoomType(CRUD_1 target){
        if(this.roomTypeID.equals(target.getRoomTypeID()) && 
    		this.roomTypeName.equals(target.getRoomTypeName()))
            return true;
        else
            return false;
    }



	public boolean equalsRoom(CRUD_1 target){
        if(this.roomID.equals(target.getRoomID()) && 
    		this.roomTypeID.equals(target.getRoomTypeID()) && 
    		this.roomName.equals(target.getRoomName()) && 
    		this.roomAvailability == target.getRoomAvailability() && 
    		this.roomAdultCapacity == target.getRoomAdultCapacity() && 
    		this.roomChildCapacity == target.getRoomChildCapacity() && 
    		this.bedKingSizeQuantity == target.getBedKingSizeQuantity() && 
			this.bedQueenSizeQuantity == target.getBedQueenSizeQuantity() && 
			this.bedSingleSizeQuantity == target.getBedSingleSizeQuantity() && 
			this.smoking.equals(target.getSmoking()) && 
    		this.shower.equals(target.getShower()) && 
    		this.wifi.equals(target.getWifi()) && 
    		this.aircon.equals(target.getAircon()) && 
    		this.leasingPrice == target.getLeasingPrice() && 
			this.rentingPrice == target.getRentingPrice())
            return true;
        else
            return false;
    }

	public boolean equalsGuest(CRUD_1 target){
        if(this.guestID.equals(target.getGuestID()) && 
    		this.guestName.equals(target.getGuestName())&& 
    		this.guestAddress.equals(target.getGuestAddress())&& 
    		this.guestPhoneNumber.equals(target.getGuestPhoneNumber())&& 
    		this.guestEmail.equals(target.getGuestEmail())&& 
    		this.guestGender.equals(target.getGuestGender())) 
            return true;
        else
            return false;
    }
	
	public boolean equalsAmenities(CRUD_1 target){
        if(this.amenityID.equals(target.getAmenityID()) && 
    		this.amenityName.equals(target.getAmenityName())&& 
    		this.amenityDescription.equals(target.getAmenityDescription())&& 
    		this.amenityPrice == target.getAmenityPrice() && 
    		this.amenityOperationStartTime.equals(target.getAmenityOperationStartTime())&& 
    		this.amenityOperationEndTime.equals(target.getAmenityOperationEndTime())&& 
    		this.amenityRules.equals(target.getAmenityRules())) 
            return true;
        else
            return false;
    }
	
	public boolean equalsAmenities_usage_log(CRUD_1 target){
        if(this.amenityID.equals(target.getAmenityID()) && 
    		this.roomID.equals(target.getRoomID())&& 
    		this.guestID.equals(target.getGuestID())&& 
    		this.usageStartTime.equals(target.getUsageStartTime())&& 
    		this.usageEndTime.equals(target.getUsageEndTime())&& 
    		this.amenityTotalPrice == target.getAmenityTotalPrice()	&& 
    		this.remarks.equals(target.getRemarks())) 
            return true;
        else
            return false;
    }
	
	public boolean equalsReservation(CRUD_1 target){
        if(this.reservationID.equals(target.getReservationID()) && 
    		this.roomID.equals(target.getRoomID())&& 
    		this.guestID.equals(target.getGuestID())&& 
    		this.checkInDate.equals(target.getCheckInDate()) && 
    		this.arrivalTime.equals(target.getArrivalTime())&& 
    		this.checkOutDate.equals(target.getCheckOutDate())&& 
    		this.adultPax == target.getAdultPax() && 
			this.childPax == target.getChildPax() && 
    		this.reservationEmailStatus.equals(target.getReservationEmailStatus())&& 
    		this.reservationTotalPrice == target.getReservationTotalPrice() && 
    		this.specialRequest.equals(target.getSpecialRequest())) 
            return true;
        else
            return false;
    }
	
	public boolean equalsMenu(CRUD_1 target){
        if(this.menuID.equals(target.getMenuID()) && 
    		this.menuName.equals(target.getMenuName())&& 
    		this.menuDescription.equals(target.getMenuDescription())&& 
    		this.menuPrice == target.getMenuPrice()) 
            return true;
        else
            return false;
    }
	
	public boolean equalsFood_order(CRUD_1 target){
        if(this.orderID.equals(target.getOrderID()) && 
    		this.menuID.equals(target.getMenuID())&& 
    		this.roomID.equals(target.getRoomID())&& 
    		this.guestID.equals(target.getGuestID())&&
    		this.quantity == target.getQuantity() && 
    		this.orderTotalPrice == target.getOrderTotalPrice() && 
    		this.remarks.equals(target.getRemarks())) 
            return true;
        else
            return false;
    }
	
	public boolean equalsCheck_in_form(CRUD_1 target){
        if(this.checkInFormID.equals(target.getCheckInFormID()) && 
    		this.reservationID.equals(target.getReservationID())&& 
    		this.signature == target.getSignature() ) 
            return true;
        else
            return false;
    }
	
	public boolean equalsCheck_in(CRUD_1 target){
        if(this.checkInID.equals(target.getCheckInFormID()) && 
    		this.checkInFormID.equals(target.getCheckInID())&& 
    		this.depositAmount == target.getDepositAmount() &&
    		this.paymentStatus.equals(target.getPaymentStatus())) 
            return true;
        else
            return false;
    }
	
	public boolean equalsRoomStatus (CRUD_1 target){
        if(this.roomID.equals(target.getRoomID()) && 
    		this.roomStatus.equals(target.getRoomStatus())&& 
    		this.remarks.equals(target.getRemarks())) 
            return true;
        else
            return false;
    }
	
	
	
	//setters and getters

	public String getHotelName() {
		return hotelName;
	}



	public void setHotelName(String hotelName) {
		this.hotelName = hotelName;
	}



	public String getHotelAddress() {
		return hotelAddress;
	}



	public void setHotelAddress(String hotelAddress) {
		this.hotelAddress = hotelAddress;
	}



	public String getHotelPhoneNumber() {
		return hotelPhoneNumber;
	}



	public void setHotelPhoneNumber(String hotelPhoneNumber) {
		this.hotelPhoneNumber = hotelPhoneNumber;
	}



	public String getHotelEmail() {
		return hotelEmail;
	}



	public void setHotelEmail(String hotelEmail) {
		this.hotelEmail = hotelEmail;
	}



	public String getRoomTypeID() {
		return roomTypeID;
	}



	public void setRoomTypeID(String roomTypeID) {
		this.roomTypeID = roomTypeID;
	}



	public String getRoomTypeName() {
		return roomTypeName;
	}



	public void setRoomTypeName(String roomTypeName) {
		this.roomTypeName = roomTypeName;
	}



	public String getRoomID() {
		return roomID;
	}



	public void setRoomID(String roomID) {
		this.roomID = roomID;
	}



	public String getRoomName() {
		return roomName;
	}



	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}



	public String getSmoking() {
		return smoking;
	}



	public void setSmoking(String smoking) {
		this.smoking = smoking;
	}



	public String getShower() {
		return shower;
	}



	public void setShower(String shower) {
		this.shower = shower;
	}



	public String getWifi() {
		return wifi;
	}



	public void setWifi(String wifi) {
		this.wifi = wifi;
	}



	public String getAircon() {
		return aircon;
	}



	public void setAircon(String aircon) {
		this.aircon = aircon;
	}



	public int getRoomAvailability() {
		return roomAvailability;
	}



	public void setRoomAvailability(int roomAvailability) {
		this.roomAvailability = roomAvailability;
	}



	public int getRoomAdultCapacity() {
		return roomAdultCapacity;
	}



	public void setRoomAdultCapacity(int roomAdultCapacity) {
		this.roomAdultCapacity = roomAdultCapacity;
	}



	public int getRoomChildCapacity() {
		return roomChildCapacity;
	}



	public void setRoomChildCapacity(int roomChildCapacity) {
		this.roomChildCapacity = roomChildCapacity;
	}



	public int getBedKingSizeQuantity() {
		return bedKingSizeQuantity;
	}



	public void setBedKingSizeQuantity(int bedKingSizeQuantity) {
		this.bedKingSizeQuantity = bedKingSizeQuantity;
	}



	public int getBedQueenSizeQuantity() {
		return bedQueenSizeQuantity;
	}



	public void setBedQueenSizeQuantity(int bedQueenSizeQuantity) {
		this.bedQueenSizeQuantity = bedQueenSizeQuantity;
	}



	public int getBedSingleSizeQuantity() {
		return bedSingleSizeQuantity;
	}



	public void setBedSingleSizeQuantity(int bedSingleSizeQuantity) {
		this.bedSingleSizeQuantity = bedSingleSizeQuantity;
	}



	public double getLeasingPrice() {
		return leasingPrice;
	}



	public void setLeasingPrice(double leasingPrice) {
		this.leasingPrice = leasingPrice;
	}



	public double getRentingPrice() {
		return rentingPrice;
	}



	public void setRentingPrice(double rentingPrice) {
		this.rentingPrice = rentingPrice;
	}



	public String getGuestID() {
		return guestID;
	}



	public void setGuestID(String guestID) {
		this.guestID = guestID;
	}



	public String getGuestName() {
		return guestName;
	}



	public void setGuestName(String guestName) {
		this.guestName = guestName;
	}



	public String getGuestAddress() {
		return guestAddress;
	}



	public void setGuestAddress(String guestAddress) {
		this.guestAddress = guestAddress;
	}



	public String getGuestPhoneNumber() {
		return guestPhoneNumber;
	}



	public void setGuestPhoneNumber(String guestPhoneNumber) {
		this.guestPhoneNumber = guestPhoneNumber;
	}



	public String getGuestEmail() {
		return guestEmail;
	}



	public void setGuestEmail(String guestEmail) {
		this.guestEmail = guestEmail;
	}



	public String getGuestGender() {
		return guestGender;
	}



	public void setGuestGender(String guestGender) {
		this.guestGender = guestGender;
	}



	public String getAmenityID() {
		return amenityID;
	}



	public void setAmenityID(String amenityID) {
		this.amenityID = amenityID;
	}



	public String getAmenityName() {
		return amenityName;
	}



	public void setAmenityName(String amenityName) {
		this.amenityName = amenityName;
	}



	public String getAmenityDescription() {
		return amenityDescription;
	}



	public void setAmenityDescription(String amenityDescription) {
		this.amenityDescription = amenityDescription;
	}



	public String getAmenityOperationStartTime() {
		return amenityOperationStartTime;
	}



	public void setAmenityOperationStartTime(String amenityOperationStartTime) {
		this.amenityOperationStartTime = amenityOperationStartTime;
	}



	public String getAmenityOperationEndTime() {
		return amenityOperationEndTime;
	}



	public void setAmenityOperationEndTime(String amenityOperationEndTime) {
		this.amenityOperationEndTime = amenityOperationEndTime;
	}



	public String getAmenityRules() {
		return amenityRules;
	}



	public void setAmenityRules(String amenityRules) {
		this.amenityRules = amenityRules;
	}



	public double getAmenityPrice() {
		return amenityPrice;
	}



	public void setAmenityPrice(double amenityPrice) {
		this.amenityPrice = amenityPrice;
	}



	public String getUsageStartTime() {
		return usageStartTime;
	}



	public void setUsageStartTime(String usageStartTime) {
		this.usageStartTime = usageStartTime;
	}



	public String getUsageEndTime() {
		return usageEndTime;
	}



	public void setUsageEndTime(String usageEndTime) {
		this.usageEndTime = usageEndTime;
	}



	public String getRemarks() {
		return remarks;
	}



	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}



	public double getAmenityTotalPrice() {
		return amenityTotalPrice;
	}



	public void setAmenityTotalPrice(double amenityTotalPrice) {
		this.amenityTotalPrice = amenityTotalPrice;
	}



	public String getReservationID() {
		return reservationID;
	}



	public void setReservationID(String reservationID) {
		this.reservationID = reservationID;
	}



	public String getCheckInDate() {
		return checkInDate;
	}



	public void setCheckInDate(String checkInDate) {
		this.checkInDate = checkInDate;
	}



	public String getArrivalTime() {
		return arrivalTime;
	}



	public void setArrivalTime(String arrivalTime) {
		this.arrivalTime = arrivalTime;
	}



	public String getCheckOutDate() {
		return checkOutDate;
	}



	public void setCheckOutDate(String checkOutDate) {
		this.checkOutDate = checkOutDate;
	}



	public String getReservationEmailStatus() {
		return reservationEmailStatus;
	}



	public void setReservationEmailStatus(String reservationEmailStatus) {
		this.reservationEmailStatus = reservationEmailStatus;
	}



	public String getSpecialRequest() {
		return specialRequest;
	}



	public void setSpecialRequest(String specialRequest) {
		this.specialRequest = specialRequest;
	}



	public int getAdultPax() {
		return adultPax;
	}



	public void setAdultPax(int adultPax) {
		this.adultPax = adultPax;
	}



	public int getChildPax() {
		return childPax;
	}



	public void setChildPax(int childPax) {
		this.childPax = childPax;
	}



	public double getReservationTotalPrice() {
		return reservationTotalPrice;
	}



	public void setReservationTotalPrice(double reservationTotalPrice) {
		this.reservationTotalPrice = reservationTotalPrice;
	}



	public String getMenuID() {
		return menuID;
	}



	public void setMenuID(String menuID) {
		this.menuID = menuID;
	}



	public String getMenuName() {
		return menuName;
	}



	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}



	public String getMenuDescription() {
		return menuDescription;
	}



	public void setMenuDescription(String menuDescription) {
		this.menuDescription = menuDescription;
	}



	public double getMenuPrice() {
		return menuPrice;
	}



	public void setMenuPrice(double menuPrice) {
		this.menuPrice = menuPrice;
	}



	public String getOrderID() {
		return orderID;
	}



	public void setOrderID(String orderID) {
		this.orderID = orderID;
	}



	public int getQuantity() {
		return quantity;
	}



	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}



	public double getOrderTotalPrice() {
		return orderTotalPrice;
	}



	public void setOrderTotalPrice(double orderTotalPrice) {
		this.orderTotalPrice = orderTotalPrice;
	}



	public String getCheckInFormID() {
		return checkInFormID;
	}



	public void setCheckInFormID(String checkInFormID) {
		this.checkInFormID = checkInFormID;
	}



	public int getSignature() {
		return signature;
	}



	public void setSignature(int signature) {
		this.signature = signature;
	}



	public String getCheckInID() {
		return checkInID;
	}



	public void setCheckInID(String checkInID) {
		this.checkInID = checkInID;
	}



	public String getPaymentStatus() {
		return paymentStatus;
	}



	public void setPaymentStatus(String paymentStatus) {
		this.paymentStatus = paymentStatus;
	}



	public double getDepositAmount() {
		return depositAmount;
	}



	public void setDepositAmount(double depositAmount) {
		this.depositAmount = depositAmount;
	}



	public String getRoomStatus() {
		return roomStatus;
	}



	public void setRoomStatus(String roomStatus) {
		this.roomStatus = roomStatus;
	}



	
}
