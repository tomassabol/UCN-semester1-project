package model;

import java.math.BigDecimal;

public class ShoppingItemLine {
	
	public final Product PRODUCT;
	private int quantity;
	
	/**
	 * Instantiates a new shopping item line.
	 *
	 * @param product the product
	 * @param quantity the quantity
	 * 
	 * @exception IllegalArgumentException When quantity <= 0
	 */
	public ShoppingItemLine(Product product, int quantity) {
		if (quantity <= 0) {
			throw new IllegalArgumentException("Quantity cannot be less than or equal to 0!");
		}
		this.PRODUCT = product;
		this.quantity = quantity;
		
	}
	
	/**
	 * Calculates the current total price for all items in this ItemLine
	 * @return the price as BigDecimal or null if no price is set.
	 * 
	 */
	public BigDecimal getCurrentPriceWithoutBulkDiscount() {
		return PRODUCT.getLatestSellingPrice().multiply(BigDecimal.valueOf(quantity));
	}
	
	/**
	 * Gets the current total price with bulk discount
	 * If no bulk discount applies, returns normal price
	 *
	 * @return the price as BigDecimal or null if no price is set.
	 */
	public BigDecimal getCurrentPriceWithBulkDiscount() {
		BigDecimal rawPrice = this.getCurrentPriceWithoutBulkDiscount();
		if (this.getBulkDiscount() == null) {
			return rawPrice;
		}
		return rawPrice.multiply(BigDecimal.valueOf((100 - this.getBulkDiscount().getDiscountPercentage()) / 100.0));
	}
	
	public BulkDiscount getBulkDiscount() {
		return PRODUCT.getBestBulkDiscount(quantity);
	}

	public int getQuantity() {
		return this.quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	public Product getPRODUCT() {
		return PRODUCT;
	}

	/**
	 * Adds items to the line
	 *
	 * @param quantity the quantity
	 * @return true, if successful. 
	 */
	public boolean addItems(int quantity) {
		if (quantity < 1) {
			return false;
		}
		
		this.quantity += quantity;
		
		return true;
	}
	
	/**
	 * Removes the items from the itemLine
	 *
	 * @param quantity the quantity
	 * @return true, if successful
	 */
	public boolean removeItems(int quantity) {
		if (quantity < this.quantity) {
			return false;
		}
		this.quantity -= quantity;
		return true;
	}

}
