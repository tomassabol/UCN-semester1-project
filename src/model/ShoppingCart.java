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
	 * Calculates the total price
	 * includes: Bulk discounts & customer type discounts
	 *
	 * @return BigDecimal the calculated price
	 */
	public BigDecimal calculateTotal() {
		BigDecimal totalPrice = BigDecimal.ZERO;
		// Count total price with bulk discounts applied
		for(ShoppingItemLine itemLine: this.itemLines) {
			totalPrice = totalPrice.add(itemLine.getCurrentPriceWithBulkDiscount());
		}
		// Apply customer type discount
		totalPrice = totalPrice.multiply(BigDecimal.valueOf((100 - CUSTOMER.getCustomerType().getDiscountPercentage()) / 100.0));
		
		return totalPrice;
	}
	
	/**
	 * Calculates the sub-total price.
	 * Includes: Bulk discounts
	 * Does not include: Customer type discount
	 *
	 * @return the big decimal
	 */
	public BigDecimal calculateSubtotal() {
		BigDecimal totalPrice = BigDecimal.ZERO;
		// Count total price with bulk discounts applied
		for(ShoppingItemLine itemLine: this.itemLines) {
			totalPrice = totalPrice.add(itemLine.getCurrentPriceWithBulkDiscount());
		}
		return totalPrice;
	}
	
	/**
	 * Gets the quantity of a product in the shopping cart
	 *
	 * @param product the product
	 * @return the quantity
	 */
	public int getQuantity(Product product) {
		int quantity = 0;
		for (ShoppingItemLine itemLine: this.getItemLines()) {
			if (product == itemLine.PRODUCT) {
				quantity += itemLine.getQuantity();
			}
		}
		return quantity;
	}
	

}
