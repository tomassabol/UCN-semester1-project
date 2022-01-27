package model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class SupplyOrder {
    public final int ID;
    private LocalDateTime dateOrdered;
    private int quantity;
    private Product product;
    private BigDecimal pricePerItem;
	private LocalDateTime delivered;
	private Contractor contractor;

    public SupplyOrder(int id, LocalDateTime dateOrdered,
    		Product product, int quantity, BigDecimal pricePerItem, Contractor contractor) {
        this.ID = id;
        this.dateOrdered = dateOrdered;
        this.product = product;
        this.pricePerItem = pricePerItem;
        this.quantity = quantity;
        this.contractor = contractor; 
        
        this.delivered = null;
    }
    public Contractor getContractor() {
		return contractor;
	}

	public void setContractor(Contractor contractor) {
		this.contractor = contractor;
	}

	public void setPricePerItem(BigDecimal pricePerItem) {
		this.pricePerItem = pricePerItem;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

    public LocalDateTime getDateOrdered() {
        return this.dateOrdered;
    }

    public void setDateOrdered(LocalDateTime dateOrdered) {
        this.dateOrdered = dateOrdered;
    }

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public BigDecimal getPricePerItem() {
		return pricePerItem;
	}

	public void setPricePerITem(BigDecimal pricePerITem) {
		this.pricePerItem = pricePerITem;
	}
	
	public boolean isStocked() {
		return delivered != null ? true: false;
	}

	public LocalDateTime getDelivered() {
		return delivered;
	}

	public void setDelivered(LocalDateTime delivered) {
		this.delivered = delivered;
	}

	public int getID() {
		return ID;
	}

}
