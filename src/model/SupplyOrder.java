package model;

import java.time.LocalDateTime;

public class SupplyOrder {
    public final int ID;
    private LocalDateTime dateOrdered;
    private SupplyOffer supplyOffer;

    public SupplyOrder(int id, LocalDateTime dateOrdered, SupplyOffer supplyOffer) {
        this.ID = id;
        this.dateOrdered = dateOrdered;
        this.supplyOffer = supplyOffer;
    }

    // get/set methods for dateOrdered
    public LocalDateTime getDateOrdered() {
        return this.dateOrdered;
    }

    public void setDateOrdered(LocalDateTime dateOrdered) {
        this.dateOrdered = dateOrdered;
    }

}
