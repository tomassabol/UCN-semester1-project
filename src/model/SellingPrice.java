package model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class SellingPrice {
    private BigDecimal price;
    private LocalDateTime dateAdded;

    public SellingPrice(BigDecimal price, LocalDateTime dateAdded) {
        this.price = price;
        this.dateAdded = dateAdded;
    }

    // get/set price
    public BigDecimal getPrice() {
        return this.price;
    }

    public void setPrice(BigDecimal price) {
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
