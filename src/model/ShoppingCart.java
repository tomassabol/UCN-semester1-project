package model;

import java.math.BigDecimal;
import java.util.ArrayList;

public class ShoppingCart {
    private ArrayList<ShoppingItemLine> itemLines;

    /*
     * Constructor
     */
    public ShoppingCart() {
    	itemLines = new ArrayList<>();
    }
    
    /*
     * Clear the shopping cart
     */
    public void clear() {
    	this.itemLines.clear();
    }
    
    /**
     * Add an ItemLine to the shopping cart
     *
     * @param itemLine the itemLine to add
     * 
     * @exception IllegalArgumentException when adding an item that doesn't have a buy price
     */
    public void add(ShoppingItemLine itemLine) {
    	if (itemLine.PRODUCT.getLatestSellingPrice() == null) {
    		throw new IllegalArgumentException("Cannot add itemLine to a shopping cart which doesn't have a buy price!");
    	}
    	this.itemLines.add(itemLine);
    }
    
    /**
     * Removes an ItemLine from the shopping cart
     *
     * @param itemLine the itemLine to remove
     */
    public void remove(ShoppingItemLine itemLine) {
    	this.itemLines.remove(itemLine);
    }
    
    public boolean isEmpty() {
    	return this.itemLines.isEmpty();
    }

	public ArrayList<ShoppingItemLine> getItemLines() {
		return itemLines;
	}

	public void setItemLines(ArrayList<ShoppingItemLine> itemLines) {
		this.itemLines = itemLines;
	}
	
	/**
	 * Calculate the total price with bulk discounts, 
	 * but without any other discounts (e.g. customer type discount)
	 *
	 * @return the big decimal
	 */
	public BigDecimal calculateTotalPriceWithBulkDiscounts() {
		BigDecimal totalPrice = BigDecimal.ZERO;
		for(ShoppingItemLine itemLine: this.itemLines) {
			totalPrice = totalPrice.add(itemLine.getCurrentPriceWithoutBulkDiscount());
		}
		return totalPrice;
	}

}
