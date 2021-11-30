package model;
import java.time.LocalDateTime;

public class Order {
	
	public enum OrderStatus{
		OFFERED,
		PAID,
		DISPATCHED,
		DELIVERED,
		CANCELLED
	}
	 
	OrderStatus status;
	LocalDateTime creationDate;
	public final int ID;
	
	public Order(int id) {
		this.status = OrderStatus.OFFERED;
		this.ID = id;
		
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
