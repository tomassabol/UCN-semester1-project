package model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class LoaningPrice {
    private BigDecimal pricePerHour;
    private LocalDateTime dateAdded;

    public LoaningPrice(BigDecimal pricePerHour, LocalDateTime dateAdded) {
    	// Check that price per hour is not below zero
    	if (pricePerHour.compareTo(BigDecimal.ZERO) > 0) {
    		throw new IllegalArgumentException("Price per hour shouldn't be below 0");
    	}
        this.pricePerHour = pricePerHour;
        this.dateAdded = dateAdded;
    }

    // get/set pricePerHour
    public BigDecimal getPricePerHour() {
        return this.pricePerHour;
    }

    public void setPricePerHour(BigDecimal pricePerHour) {
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
