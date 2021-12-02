package model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;

public class ShoppingCart {
    private ArrayList<IFItemLine> itemLines;

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
     */
    public void add(IFItemLine itemLine) {
    	this.itemLines.add(itemLine);
    }
    
    /**
     * Removes an ItemLine from the shopping cart
     *
     * @param itemLine the itemLine to remove
     */
    public void remove(IFItemLine itemLine) {
    	this.itemLines.remove(itemLine);
    }
    
    public boolean isEmpty() {
    	return this.itemLines.isEmpty();
    }

	public ArrayList<IFItemLine> getItemLines() {
		return itemLines;
	}

	public void setItemLines(ArrayList<IFItemLine> itemLines) {
		this.itemLines = itemLines;
	}
	
	public BigDecimal calculateTotalPrice() {
		BigDecimal totalPrice = BigDecimal.ZERO;
		for(IFItemLine itemLine: this.itemLines) {
			totalPrice = totalPrice.add(itemLine.calculatePrice());
		}
		return totalPrice;
	}

}
