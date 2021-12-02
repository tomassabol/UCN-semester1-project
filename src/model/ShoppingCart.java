package model;

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

    /**
     * Gets a specific customer's cart aka current order
     *
     * @param customer the customer
     * @return the current order
     */
    public ArrayList<IFItemLine> getItemLines(IFCustomer customer) {
        return this.itemLines;
    }
    
    /*
     * Clear shopping cart
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

}
