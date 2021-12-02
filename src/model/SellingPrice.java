package model;

import java.time.LocalDateTime;

public class SellingPrice {
    private int price;
    private LocalDateTime dateAdded;

    public SellingPrice(int price, LocalDateTime dateAdded) {
        this.price = price;
        this.dateAdded = dateAdded;
    }

    // get/set price
    public int getPrice() {
        return this.price;
    }

    public void setPrice(int price) {
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
