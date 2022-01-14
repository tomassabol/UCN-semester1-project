package model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class LoaningPrice {
    private BigDecimal pricePerHour;
    private LocalDateTime dateAdded;

    /**
     * Instantiates a new loaning price.
     *
     * @param pricePerHour the price per hour
     * @param dateAdded the date added
     * 
     * @exception IllegalArgumentException if pricePerHour < 0
     */
    public LoaningPrice(BigDecimal pricePerHour, LocalDateTime dateAdded) {
    	if (pricePerHour != null && (pricePerHour.compareTo(BigDecimal.valueOf(0)) < 0)) {
        	// if not null and price below zero, throw exception
        	throw new IllegalArgumentException("Price per hour cannot be below zero!");
    	}
        this.pricePerHour = pricePerHour;
        this.dateAdded = dateAdded;
    }

    /**
     * Gets the price per hour. Note: it can be null!
     *
     * @return the price per hour
     */
    public BigDecimal getPricePerHour() {
        return this.pricePerHour;
    }

    // No set price by design!!!! If you want to edit the price, add a new one to the container!!!
    private void setPricePerHour() {};

    public LocalDateTime getDateAdded() {
        return this.dateAdded;
    }

    public void setDateAdded(LocalDateTime dateAdded) {
        this.dateAdded = dateAdded;
    }

}
