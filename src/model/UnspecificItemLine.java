package model;

import java.math.BigDecimal;
import java.util.ArrayList;

public class UnspecificItemLine {
	
	public final Product PRODUCT;
	private int quantity;
	
	public UnspecificItemLine(Product product, int quantity) {
		if (quantity < 0) {
			throw new IllegalArgumentException("Quantity cannot be less than 0!");
		}
		this.PRODUCT = product;
		this.quantity = quantity;
	}
	
	/**
	 * Calculates the total price for all items in this ItemLine
	 * @return the price as BigDecimal or null if no price is set.
	 */
	public BigDecimal calculateCurrentPrice() {
		// if no selling price has been set, return 0
		if (this.PRODUCT.getLatestSellingPrice() == null) {
			return null;
		}
		// if quantity below 0, return null
		int quantity = getQuantity();
		if (quantity < 1) {
			return null;
		}
		return PRODUCT.getLatestSellingPrice().multiply(BigDecimal.valueOf(quantity));
	}

	public int getQuantity() {
		return this.quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	

	/**
	 * Adds items to the line
	 *
	 * @param quantity the quantity
	 * @return true, if successful. False if quantity < 1
	 */
	public boolean addItems(int quantity) {
		if (quantity < 1) {
			return false;
		}
		
		this.quantity += quantity;
		return true;
	}

}
