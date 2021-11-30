package model;
import java.time.LocalDateTime;


enum OrderStatus{
	Offered,Paid,Dispatched,Delivered,Cancelled

}

public class Order {
	 
	OrderStatus status;
	LocalDateTime creationDate;
	public Order() {
		status = OrderStatus.Offered;
		
	}
	public OrderStatus getStatus() {
		return status;
	}
	public void setStatus(OrderStatus status) {
		this.status = status;
	}
	public LocalDateTime getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(LocalDateTime creationDate) {
		this.creationDate = creationDate;
	}
	
	
	

}
