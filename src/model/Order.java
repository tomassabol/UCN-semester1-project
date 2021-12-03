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
	private IFEmployee employee;
	private ArrayList<SpecificItemLine> itemLines;
	
	/*
	 * Constructor
	 */
	public Order(int id, IFCustomer customer, IFEmployee employee, ArrayList<SpecificItemLine> itemLines) {
		this.ID = id;
		this.customer = customer;
		this.itemLines = itemLines;
		this.employee = employee;
		
		this.status = OrderStatus.PAID;
		this.CREATION_DATE = LocalDateTime.now();
		
	}
	public IFEmployee getEmployee() {
		return employee;
	}
	public void setEmployee(IFEmployee employee) {
		this.employee = employee;
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
	
	public List<SpecificItemLine> getItemLines() {
		return this.itemLines;
	}

}
