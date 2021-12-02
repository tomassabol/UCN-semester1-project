package model;

import java.time.LocalDateTime;

public class LoaningPrice {
    private int pricePerHour;
    private LocalDateTime dateAdded;

    public LoaningPrice(int pricePerHour, LocalDateTime dateAdded) {
        this.pricePerHour = pricePerHour;
        this.dateAdded = dateAdded;
    }

    // get/set pricePerHour
    public int getPricePerHour() {
        return this.pricePerHour;
    }

    public void setPricePerHour(int pricePerHour) {
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
