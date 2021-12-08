package model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Map;

public class Quote {
	public final int ID;
	public final LocalDateTime CREATION_DATE;
	private final IFCustomer CUSTOMER;
	private final IFEmployee EMPLOYEE;
	private final ArrayList<QuoteItemLine> ITEM_LINES;
	private final String CUSTOMER_TYPE;
	private final int CUSTOMER_TYPE_DISCOUNT_PERCENTAGE;

	public Quote(int Id, IFCustomer customer,
			IFEmployee employee, ArrayList<QuoteItemLine> itemLines) {
		this.ID = Id;
		this.CUSTOMER = customer;
		this.EMPLOYEE = employee;
		this.ITEM_LINES = itemLines;
		
		this.CREATION_DATE = LocalDateTime.now();
		this.CUSTOMER_TYPE = customer.getCustomerType().getName();
		this.CUSTOMER_TYPE_DISCOUNT_PERCENTAGE = customer.getCustomerType().getDiscountPercentage();
		
	}

	public String getCUSTOMER_TYPE() {
		return CUSTOMER_TYPE;
	}

	public int getCUSTOMER_TYPE_DISCOUNT_PERCENTAGE() {
		return CUSTOMER_TYPE_DISCOUNT_PERCENTAGE;
	}

	public IFCustomer getCustomer() {
		return CUSTOMER;
	}

	public IFEmployee getEmployee() {
		return EMPLOYEE;
	}


	/**
	 * Gets the item lines.
	 *
	 * @return the item lines as a map, with BulkDiscount or null as key
	 */
	public ArrayList<QuoteItemLine> getItemLines() {
		return ITEM_LINES;
	}
	
	public int getID() {
		return ID;
	}

	public LocalDateTime getCreationDate() {
		return CREATION_DATE;
	}


}
