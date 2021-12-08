package model;

import java.math.BigDecimal;

public class UnspecificItemLine {
	
	public final Product PRODUCT;
	private int quantity;
	private BulkDiscount fixedBulkDiscount = null;
	private BigDecimal fixedTotalPrice = null;
	
	public UnspecificItemLine(Product product, int quantity) {
		if (quantity < 0) {
			throw new IllegalArgumentException("Quantity cannot be less than 0!");
		}
		this.PRODUCT = product;
		this.quantity = quantity;
		
		fixedBulkDiscount = DiscountContainer.getInstance().getBestBulkDiscount(PRODUCT, quantity);
		fixedTotalPrice = this.calculateCurrentPrice();
	}
	
	/**
	 * Calculates the current total price for all items in this ItemLine
	 * @return the price as BigDecimal or null if no price is set.
	 * 
	 * Note: In quotes, use fixed price, not current.
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
		// If changing quantity, change fixed price
		fixedBulkDiscount = DiscountContainer.getInstance().getBestBulkDiscount(PRODUCT, quantity);
		fixedTotalPrice = this.calculateCurrentPrice();
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
	
	public BulkDiscount getBulkDiscount() {
		return DiscountContainer.getInstance().getBestBulkDiscount(PRODUCT, quantity);
	}

}
