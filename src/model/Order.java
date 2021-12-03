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
	private final LocalDateTime CREATION_DATE;
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
		this.CREATION_DATE = LocalDateTime.now();
		
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
	
	public LocalDateTime getCREATION_DATE() {
		return CREATION_DATE;
	}
	public int getID() {
		return ID;
	}
	public void setItemLines(ArrayList<SpecificItemLine> itemLines) {
		this.itemLines = itemLines;
	}
	public List<SpecificItemLine> getItemLines() {
		return this.itemLines;
	}

}
