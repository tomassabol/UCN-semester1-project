package model;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Order {
	
	public enum OrderStatus{
		PAID,
		DISPATCHED,
		DELIVERED,
		CANCELLED
	}
	 
	private OrderStatus status;
	private LocalDateTime creationDate;
	public final int ID;
	private IFCustomer customer;
	private ArrayList<SpecificItemLine> itemLines;
	
	/*
	 * Constructor
	 */
	public Order(int id, Customer customer, ArrayList<SpecificItemLine> itemLines) {
		this.ID = id;
		this.customer = customer;
		this.itemLines = itemLines;
		
		this.status = OrderStatus.PAID;
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
	
	public List<SpecificItemLine> getItemLines() {
		return this.itemLines;
	}
	public void setItemLines(ArrayList<SpecificItemLine> itemLines) {
		this.itemLines = itemLines;
	}
	
	public void addItemLine(SpecificItemLine itemLine) {
		this.itemLines.add(itemLine);
	}

}
