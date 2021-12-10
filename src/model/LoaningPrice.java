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
    	this.setPricePerHour(pricePerHour);
        this.pricePerHour = pricePerHour;
        this.dateAdded = dateAdded;
    }

    // get/set pricePerHour
    public BigDecimal getPricePerHour() {
        return this.pricePerHour;
    }

    /**
     * Sets the price per hour.
     *
     * @param pricePerHour the new price per hour
     * 
     * @exception IllegalArgumentException if pricePerHour < 0
     */
    public void setPricePerHour(BigDecimal pricePerHour) {
    	// Check that price per hour is not below zero
    	if (pricePerHour.compareTo(BigDecimal.ZERO) < 0) {
    		throw new IllegalArgumentException("Price per hour shouldn't be below 0");
    	}
        this.pricePerHour = pricePerHour;
    }

    // get/set dateAdded
    public LocalDateTime getDateAdded() {
        return this.dateAdded;
    }

    public void setDateAdded(LocalDateTime dateAdded) {
        this.dateAdded = dateAdded;
    }

}
