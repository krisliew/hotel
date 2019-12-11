package CRUD;

public class CRUD_2 {
	
	//variable for services
	String serviceID, serviceName, serviceDescription, serviceOperationStartTime, serviceOperationEndTime, serviceRules;
	double servicePrice;
		
	//variable for services_usage_log
	String roomID, guestID, usageStartTime, usageEndTime, remarks;
	double servicesTotalPrice;
	
	//variable for housekeeping
	String housekeepingID, housekeepingName;
	
	//variable for housekeeping_log
	int quantityUsed;
	String dateOfLog, housekeepingStatus;
	
	//variable for payment
	String paymentID, reservationID, orderID, paymentDate, paymentStatus;
	double subtotalAmount, tipsAmount, totalAmount;
	
	//variable for payment method
	String paymentMethodID;
	double paymentAmount;
	
	//variable for cash
	String paymentMethodName;
	
	//variable for cheque
	String chequeHolderName, chequeBankName, chequeDate;
	double chequeAmount;
	
	//variable for credit_card
	String creditCardHolderName, creditCardBankName;
	double creditCardBalance;
	int creditCardExpiryMonth, creditCardExpiryYear;
		
		
	//services Constructor
	public CRUD_2(String serviceID, String serviceName, String serviceDescription, double servicePrice, String serviceOperationStartTime, String serviceOperationEndTime, String serviceRules) {
		this.serviceID = serviceID;
		this.serviceName = serviceName;	
		this.serviceDescription = serviceDescription;
		this.servicePrice = servicePrice;	
		this.serviceOperationStartTime = serviceOperationStartTime;
		this.serviceOperationEndTime = serviceOperationEndTime;	
		this.serviceRules = serviceRules;	
	}
		
	//services_usage_log Constructor
	public CRUD_2(String serviceID, String roomID, String guestID, String usageStartTime, String usageEndTime, double servicesTotalPrice, String remarks) {
		this.serviceID = serviceID;
		this.roomID = roomID;	
		this.guestID = guestID;
		this.usageStartTime = usageStartTime;
		this.usageEndTime = usageEndTime;
		this.servicesTotalPrice = servicesTotalPrice;
		this.remarks = remarks;
	}
	
	
	
	//housekeeping Constructor
	public CRUD_2(String housekeepingID, String housekeepingName, String remarks) {
		this.housekeepingID = housekeepingID;
		this.housekeepingName = housekeepingName;	
		this.remarks = remarks;
	}
	
	
	
	//housekeeping_log Constructor
	public CRUD_2(String housekeepingID, String roomID, int quantityUsed, String dateOfLog, String housekeepingStatus, String remarks) {
		this.housekeepingID = housekeepingID;
		this.roomID = roomID;	
		this.quantityUsed = quantityUsed;
		this.dateOfLog = dateOfLog;
		this.housekeepingStatus = housekeepingStatus;
		this.remarks = remarks;
	}
	
	
	
	//payment Constructor
	public CRUD_2(String paymentID, String serviceID, String reservationID, String orderID, String roomID, String guestID, double subtotalAmount, double tipsAmount, double totalAmount, String paymentDate, String paymentStatus) {
		this.paymentID = paymentID;
		this.serviceID = serviceID;	
		this.reservationID = reservationID;
		this.orderID = orderID;	
		this.roomID = roomID;
		this.guestID = guestID;	
		this.subtotalAmount = subtotalAmount;
		this.tipsAmount = tipsAmount;	
		this.totalAmount = totalAmount;
		this.paymentDate = paymentDate;	
		this.paymentStatus = paymentStatus;	
	}
		
		
		
	//payment_method Constructor
	public CRUD_2(String paymentMethodID, String paymentID, String guestID, double paymentAmount) {
		this.paymentMethodID = paymentMethodID;
		this.paymentID = paymentID;	
		this.guestID = guestID;
		this.paymentAmount = paymentAmount;	
	}
	
		
		
	//cash Constructor
	public CRUD_2(String paymentMethodID, String paymentMethodName) {
		this.paymentMethodID = paymentMethodID;
		this.paymentMethodName = paymentMethodName;	
	}
	
	
	
	//cheque Constructor
	public CRUD_2(String paymentMethodID, String paymentMethodName, String chequeHolderName, String chequeBankName, double chequeAmount, String chequeDate) {
		this.paymentMethodID = paymentMethodID;
		this.paymentMethodName = paymentMethodName;	
		this.chequeHolderName = chequeHolderName;
		this.chequeBankName = chequeBankName;
		this.chequeAmount = chequeAmount;
		this.chequeDate = chequeDate;
	}
	
	
	
	//credit_card Constructor
	public CRUD_2(String paymentMethodID, String paymentMethodName, String creditCardHolderName, String creditCardBankName, double creditCardBalance, int  creditCardExpiryMonth, int creditCardExpiryYear) {
		this.paymentMethodID = paymentMethodID;
		this.paymentMethodName = paymentMethodName;	
		this.creditCardHolderName = creditCardHolderName;
		this.creditCardBankName = creditCardBankName;
		this.creditCardBalance = creditCardBalance;
		this.creditCardExpiryMonth = creditCardExpiryMonth;
		this.creditCardExpiryYear = creditCardExpiryYear;
	}

	
	//Used to compare the object passed in with the existing records to see of they are equal
	
	public boolean equalsServices(CRUD_2 target){
        if(this.serviceID.equals(target.getServiceID()) && 
    		this.serviceName.equals(target.getServiceName())&& 
    		this.serviceDescription.equals(target.getServiceDescription())&& 
    		this.servicePrice == target.getServicePrice() && 
    		this.serviceOperationStartTime.equals(target.getServiceOperationStartTime())&& 
    		this.serviceOperationEndTime.equals(target.getServiceOperationEndTime())&& 
    		this.serviceRules.equals(target.getServiceRules())) 
            return true;
        else
            return false;
    }
	
	public boolean equalsServices_usage_log(CRUD_2 target){
        if(this.serviceID.equals(target.getServiceID()) && 
    		this.roomID.equals(target.getRoomID())&& 
    		this.guestID.equals(target.getGuestID())&& 
    		this.usageStartTime == target.getUsageStartTime() && 
			this.usageEndTime == target.getUsageEndTime() && 
			this.servicesTotalPrice == target.getServicesTotalPrice() && 
    		this.remarks.equals(target.getRemarks())) 
            return true;
        else
            return false;
    }
	
	public boolean equalsHousekeeping(CRUD_2 target){
        if(this.housekeepingID.equals(target.getHousekeepingID()) && 
    		this.housekeepingName.equals(target.getHousekeepingName())&& 
    		this.remarks.equals(target.getRemarks())) 
            return true;
        else
            return false;
    }
	
	public boolean equalsHousekeeping_log(CRUD_2 target){
        if(this.housekeepingID.equals(target.getHousekeepingID()) && 
    		this.roomID.equals(target.getRoomID())&&
    		this.quantityUsed == target.getQuantityUsed() && 
    		this.dateOfLog.equals(target.getDateOfLog())&& 
    		this.housekeepingStatus.equals(target.getHousekeepingStatus())&& 
    		this.remarks.equals(target.getRemarks())) 
            return true;
        else
            return false;
    }
	
	public boolean equalsPayment(CRUD_2 target){
        if(this.paymentID.equals(target.getPaymentID()) && 
    		this.serviceID.equals(target.getServiceID())&& 
    		this.reservationID.equals(target.getReservationID())&& 
    		this.orderID.equals(target.getOrderID()) && 
    		this.roomID.equals(target.getRoomID())&& 
    		this.guestID.equals(target.getGuestID())&&
    		this.subtotalAmount == target.getSubtotalAmount() && 
			this.tipsAmount == target.getTipsAmount() && 
			this.totalAmount == target.getTotalAmount() && 
    		this.paymentDate.equals(target.getPaymentDate())&& 
    		this.paymentStatus.equals(target.getPaymentStatus())) 
            return true;
        else
            return false;
    }
	
	public boolean equalsPayment_method (CRUD_2 target){
        if(this.paymentMethodID.equals(target.getPaymentMethodID()) && 
    		this.paymentID.equals(target.getPaymentID())&& 
    		this.guestID.equals(target.getGuestID())&& 
    		this.paymentAmount == target.getPaymentAmount()) 
            return true;
        else
            return false;
    }
	
	public boolean equalsCash (CRUD_2 target){
        if(this.paymentMethodID.equals(target.getPaymentMethodID()) && 
    		this.paymentMethodName.equals(target.getPaymentMethodName())) 
            return true;
        else
            return false;
    }
	
	public boolean equalsCheque (CRUD_2 target){
        if(this.paymentMethodID.equals(target.getPaymentMethodID()) && 
    		this.paymentMethodName.equals(target.getPaymentMethodName())&& 
    		this.chequeHolderName.equals(target.getChequeHolderName())&& 
    		this.chequeBankName.equals(target.getChequeBankName())&& 
    		this.chequeAmount == target.getChequeAmount() &&
    		this.chequeDate.equals(target.getChequeDate())) 
            return true;
        else
            return false;
    }
	
	public boolean equalsCredit_card (CRUD_2 target){
        if(this.paymentMethodID.equals(target.getPaymentMethodID()) && 
    		this.paymentMethodName.equals(target.getPaymentMethodName())&& 
    		this.creditCardHolderName.equals(target.getCreditCardHolderName())&& 
    		this.creditCardBankName.equals(target.getCreditCardBankName())&& 
    		this.creditCardBalance == target.getCreditCardBalance() &&
    		this.creditCardExpiryMonth == target.getCreditCardExpiryMonth() && 
    		this.creditCardExpiryYear == target.getCreditCardExpiryYear() ) 
            return true;
        else
            return false;
    }
	
	
	//setters and getters
	
	public String getServiceID() {
		return serviceID;
	}

	public void setServiceID(String serviceID) {
		this.serviceID = serviceID;
	}

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	public String getServiceDescription() {
		return serviceDescription;
	}

	public void setServiceDescription(String serviceDescription) {
		this.serviceDescription = serviceDescription;
	}

	public String getServiceOperationStartTime() {
		return serviceOperationStartTime;
	}

	public void setServiceOperationStartTime(String serviceOperationStartTime) {
		this.serviceOperationStartTime = serviceOperationStartTime;
	}

	public String getServiceOperationEndTime() {
		return serviceOperationEndTime;
	}

	public void setServiceOperationEndTime(String serviceOperationEndTime) {
		this.serviceOperationEndTime = serviceOperationEndTime;
	}

	public String getServiceRules() {
		return serviceRules;
	}

	public void setServiceRules(String serviceRules) {
		this.serviceRules = serviceRules;
	}

	public double getServicePrice() {
		return servicePrice;
	}

	public void setServicePrice(double servicePrice) {
		this.servicePrice = servicePrice;
	}

	public String getRoomID() {
		return roomID;
	}

	public void setRoomID(String roomID) {
		this.roomID = roomID;
	}

	public String getGuestID() {
		return guestID;
	}

	public void setGuestID(String guestID) {
		this.guestID = guestID;
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

	public double getServicesTotalPrice() {
		return servicesTotalPrice;
	}

	public void setServicesTotalPrice(double servicesTotalPrice) {
		this.servicesTotalPrice = servicesTotalPrice;
	}

	public String getHousekeepingID() {
		return housekeepingID;
	}

	public void setHousekeepingID(String housekeepingID) {
		this.housekeepingID = housekeepingID;
	}

	public String getHousekeepingName() {
		return housekeepingName;
	}

	public void setHousekeepingName(String housekeepingName) {
		this.housekeepingName = housekeepingName;
	}

	public int getQuantityUsed() {
		return quantityUsed;
	}

	public void setQuantityUsed(int quantityUsed) {
		this.quantityUsed = quantityUsed;
	}

	public String getDateOfLog() {
		return dateOfLog;
	}

	public void setDateOfLog(String dateOfLog) {
		this.dateOfLog = dateOfLog;
	}

	public String getHousekeepingStatus() {
		return housekeepingStatus;
	}

	public void setHousekeepingStatus(String housekeepingStatus) {
		this.housekeepingStatus = housekeepingStatus;
	}

	public String getPaymentID() {
		return paymentID;
	}

	public void setPaymentID(String paymentID) {
		this.paymentID = paymentID;
	}

	public String getReservationID() {
		return reservationID;
	}

	public void setReservationID(String reservationID) {
		this.reservationID = reservationID;
	}

	public String getOrderID() {
		return orderID;
	}

	public void setOrderID(String orderID) {
		this.orderID = orderID;
	}

	public String getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(String paymentDate) {
		this.paymentDate = paymentDate;
	}

	public String getPaymentStatus() {
		return paymentStatus;
	}

	public void setPaymentStatus(String paymentStatus) {
		this.paymentStatus = paymentStatus;
	}

	public double getSubtotalAmount() {
		return subtotalAmount;
	}

	public void setSubtotalAmount(double subtotalAmount) {
		this.subtotalAmount = subtotalAmount;
	}

	public double getTipsAmount() {
		return tipsAmount;
	}

	public void setTipsAmount(double tipsAmount) {
		this.tipsAmount = tipsAmount;
	}

	public double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public String getPaymentMethodID() {
		return paymentMethodID;
	}

	public void setPaymentMethodID(String paymentMethodID) {
		this.paymentMethodID = paymentMethodID;
	}

	public double getPaymentAmount() {
		return paymentAmount;
	}

	public void setPaymentAmount(double paymentAmount) {
		this.paymentAmount = paymentAmount;
	}

	public String getPaymentMethodName() {
		return paymentMethodName;
	}

	public void setPaymentMethodName(String paymentMethodName) {
		this.paymentMethodName = paymentMethodName;
	}

	public String getChequeHolderName() {
		return chequeHolderName;
	}

	public void setChequeHolderName(String chequeHolderName) {
		this.chequeHolderName = chequeHolderName;
	}

	public String getChequeBankName() {
		return chequeBankName;
	}

	public void setChequeBankName(String chequeBankName) {
		this.chequeBankName = chequeBankName;
	}

	public String getChequeDate() {
		return chequeDate;
	}

	public void setChequeDate(String chequeDate) {
		this.chequeDate = chequeDate;
	}

	public double getChequeAmount() {
		return chequeAmount;
	}

	public void setChequeAmount(double chequeAmount) {
		this.chequeAmount = chequeAmount;
	}

	public String getCreditCardHolderName() {
		return creditCardHolderName;
	}

	public void setCreditCardHolderName(String creditCardHolderName) {
		this.creditCardHolderName = creditCardHolderName;
	}

	public String getCreditCardBankName() {
		return creditCardBankName;
	}

	public void setCreditCardBankName(String creditCardBankName) {
		this.creditCardBankName = creditCardBankName;
	}

	public double getCreditCardBalance() {
		return creditCardBalance;
	}

	public void setCreditCardBalance(double creditCardBalance) {
		this.creditCardBalance = creditCardBalance;
	}

	public int getCreditCardExpiryMonth() {
		return creditCardExpiryMonth;
	}

	public void setCreditCardExpiryMonth(int creditCardExpiryMonth) {
		this.creditCardExpiryMonth = creditCardExpiryMonth;
	}

	public int getCreditCardExpiryYear() {
		return creditCardExpiryYear;
	}

	public void setCreditCardExpiryYear(int creditCardExpiryYear) {
		this.creditCardExpiryYear = creditCardExpiryYear;
	}




	

	
	

}
