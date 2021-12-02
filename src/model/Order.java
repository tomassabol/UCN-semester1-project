package model;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Order {
	
	public enum OrderStatus{
		OFFERED,
		PAID,
		DISPATCHED,
		DELIVERED,
		CANCELLED
	}
	 
	private OrderStatus status;
	private LocalDateTime creationDate;
	public final int ID;
	private IFCustomer customer;
	
	/*
	 * Constructor
	 */
	public Order(int id, Customer customer) {
		this.ID = id;
		this.customer = customer;
		
		this.status = OrderStatus.OFFERED;
		this.creationDate = LocalDateTime.now();
		
	}
	public IFCustomer getCustomer() {
		return customer;
	}
	public void setCustomer(IFCustomer customer) {
		this.customer = customer;
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
