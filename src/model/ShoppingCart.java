package model;

import java.math.BigDecimal;
import java.util.ArrayList;

public class ShoppingCart {
    private ArrayList<ShoppingItemLine> itemLines;
    private final IFCustomer CUSTOMER;

    public IFCustomer getCUSTOMER() {
		return CUSTOMER;
	}

	/*
     * Constructor
     */
    public ShoppingCart(IFCustomer customer) {
    	this.itemLines = new ArrayList<>();
    	this.CUSTOMER = customer;
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
	 * Calculate the total price with bulk discounts & customer type discount applied
	 *
	 * @return BigDecimal the calculated price
	 */
	public BigDecimal calculateTotalPriceWithDiscountsApplied() {
		BigDecimal totalPrice = BigDecimal.ZERO;
		// Count total price with bulk discounts applied
		for(ShoppingItemLine itemLine: this.itemLines) {
			totalPrice = totalPrice.add(itemLine.getCurrentPriceWithoutBulkDiscount());
		}
		// Apply customer type discount
		totalPrice.multiply(BigDecimal.valueOf((100 - CUSTOMER.getCustomerType().getDiscountPercentage()) / 100));
		
		return totalPrice;
	}

}
