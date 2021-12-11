package model;
import java.math.BigDecimal;
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
	public final LocalDateTime CREATION_DATE;
	public final int ID;
	public final IFCustomer CUSTOMER;
	public final IFEmployee EMPLOYEE;
	public final ArrayList<OrderLine> ITEM_LINES;
	public final String CUSTOMER_TYPE;
	public final int CUSTOMER_TYPE_DISCOUNT_PERCENTAGE;
	
	/*
	 * Constructor
	 */
	public Order(int id, IFCustomer customer, IFEmployee employee, ArrayList<OrderLine> itemLines) {
		this.ID = id;
		this.CUSTOMER = customer;
		this.ITEM_LINES = itemLines;
		this.EMPLOYEE = employee;
		
		this.status = OrderStatus.PAID;
		this.CREATION_DATE = LocalDateTime.now();
		this.CUSTOMER_TYPE = customer.getCustomerType().getName();
		this.CUSTOMER_TYPE_DISCOUNT_PERCENTAGE = customer.getCustomerType().getDiscountPercentage();
		
	}
	public IFEmployee getEmployee() {
		return EMPLOYEE;
	}
	public IFCustomer getCustomer() {
		return CUSTOMER;
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
	
	public String getCUSTOMER_TYPE() {
		return CUSTOMER_TYPE;
	}
	public int getCUSTOMER_TYPE_DISCOUNT_PERCENTAGE() {
		return CUSTOMER_TYPE_DISCOUNT_PERCENTAGE;
	}
	public List<OrderLine> getItemLines() {
		return this.ITEM_LINES;
	}
	
	/**
	 * Calculate the total price with bulk discounts & customer type discount applied
	 *
	 * @return BigDecimal the calculated price
	 */
	public BigDecimal calculateTotalPriceWithDiscountsApplied() {
		BigDecimal totalPrice = BigDecimal.ZERO;
		// Count total price with bulk discounts applied
		for(OrderLine itemLine: this.ITEM_LINES) {
			totalPrice = totalPrice.add(itemLine.getTotalPriceWithBulkDiscount());
		}
		// Apply customer type discount
		totalPrice = totalPrice.multiply(BigDecimal.valueOf((100 - CUSTOMER.getCustomerType().getDiscountPercentage()) / 100.0));
		
		return totalPrice;
	}

}
