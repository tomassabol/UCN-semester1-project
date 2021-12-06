package model;

import java.time.LocalDateTime;

public class SupplyOrder {
    public final int ID;
    private LocalDateTime dateOrdered;
    private SupplyOffer supplyOffer;
    private boolean delivered;
    private int quantity;

    public SupplyOrder(int id, LocalDateTime dateOrdered,
    		SupplyOffer supplyOffer, int quantity) {
        this.ID = id;
        this.dateOrdered = dateOrdered;
        this.supplyOffer = supplyOffer;
        this.quantity = quantity;
        
        this.delivered = false;
    }

    public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	// get/set methods for dateOrdered
    public LocalDateTime getDateOrdered() {
        return this.dateOrdered;
    }

    public void setDateOrdered(LocalDateTime dateOrdered) {
        this.dateOrdered = dateOrdered;
    }

	public SupplyOffer getSupplyOffer() {
		return supplyOffer;
	}

	public void setSupplyOffer(SupplyOffer supplyOffer) {
		this.supplyOffer = supplyOffer;
	}

	public boolean isDelivered() {
		return delivered;
	}

	public void setDelivered(boolean delivered) {
		this.delivered = delivered;
	}

	public int getID() {
		return ID;
	}
    
	public void printAllSupplyOrderInfo() {
		System.out.println("ID: " + ID);
        System.out.println("Date ordered: " + dateOrdered);
        System.out.println("Supply Offer: " + supplyOffer.ID);
        System.out.println("Quantity: " + quantity);
        System.out.println("Delivered: " + delivered);
	}
    

}
