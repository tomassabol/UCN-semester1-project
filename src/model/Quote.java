package model;

import java.math.BigDecimal;
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
	
	/**
	 * Calculate the total price with bulk discounts & customer type discount applied
	 *
	 * @return BigDecimal the calculated price
	 */
	public BigDecimal calculateTotalPriceWithDiscountsApplied() {
		BigDecimal totalPrice = BigDecimal.ZERO;
		// Count total price with bulk discounts applied
		for(QuoteItemLine itemLine: this.ITEM_LINES) {
			totalPrice = totalPrice.add(itemLine.getFixedPriceWithBulkDiscount());
		}
		// Apply customer type discount
		totalPrice = totalPrice.multiply(BigDecimal.valueOf((100 - CUSTOMER.getCustomerType().getDiscountPercentage()) / 100));
		
		return totalPrice;
	}


}
