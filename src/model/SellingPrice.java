package model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class SellingPrice {
    private BigDecimal price;
    private LocalDateTime dateAdded;

    /**
     * Instantiates a new selling price.
     *
     * @param price the price
     * @param dateAdded the date added
     * 
	 * @exception IllegalArgumentException If price < 0
     */
    public SellingPrice(BigDecimal price, LocalDateTime dateAdded) {
    	setPrice(price);
        this.price = price;
        this.dateAdded = dateAdded;
    }

    // get/set price
    public BigDecimal getPrice() {
        return this.price;
    }

    /**
     * Sets the price.
     *
     * @param price the new price
     * 
     * @exception IllegalArgumentException If price < 0
     */
    public void setPrice(BigDecimal price) {
    	// if below zero, throw exception
    	if (price.compareTo(BigDecimal.valueOf(0)) == -1) {
    		throw new IllegalArgumentException("Price cannot be below zero!");
    	}
        this.price = price;
    }

    // get/set dateAdded
    public LocalDateTime getDateAdded() {
        return this.dateAdded;
    }

    public void setDateAdded(LocalDateTime dateAdded) {
        this.dateAdded = dateAdded;
    }

}
