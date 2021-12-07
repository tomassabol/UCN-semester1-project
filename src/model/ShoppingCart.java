package model;

import java.math.BigDecimal;
import java.util.ArrayList;

public class ShoppingCart {
    private ArrayList<UnspecificItemLine> itemLines;

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
    public void add(UnspecificItemLine itemLine) {
    	this.itemLines.add(itemLine);
    }
    
    /**
     * Removes an ItemLine from the shopping cart
     *
     * @param itemLine the itemLine to remove
     */
    public void remove(UnspecificItemLine itemLine) {
    	this.itemLines.remove(itemLine);
    }
    
    public boolean isEmpty() {
    	return this.itemLines.isEmpty();
    }

	public ArrayList<UnspecificItemLine> getItemLines() {
		return itemLines;
	}

	public void setItemLines(ArrayList<UnspecificItemLine> itemLines) {
		this.itemLines = itemLines;
	}
	
	public BigDecimal calculateTotalPrice() {
		BigDecimal totalPrice = BigDecimal.ZERO;
		for(UnspecificItemLine itemLine: this.itemLines) {
			totalPrice = totalPrice.add(itemLine.calculateCurrentPrice());
		}
		return totalPrice;
	}

}
