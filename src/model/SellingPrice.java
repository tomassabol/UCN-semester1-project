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
    	if (price != null && (price.compareTo(BigDecimal.valueOf(0)) < 0)) {
        	// if not null and price below zero, throw exception
        	throw new IllegalArgumentException("Price cannot be below zero!");
    	}
    	this.price = price;
        this.dateAdded = dateAdded;
    }

    /**
     * Gets the price. Note: price can be null!
     *
     *
     * @return the price
     */
    public BigDecimal getPrice() {
        return this.price;
    }
    
    public LocalDateTime getDateAdded() {
        return this.dateAdded;
    }

    public void setDateAdded(LocalDateTime dateAdded) {
        this.dateAdded = dateAdded;
    }

}
