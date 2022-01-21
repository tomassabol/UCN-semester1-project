package model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class SupplyOrder {
    public final int ID;
    private LocalDateTime dateOrdered;
    private boolean delivered;
    private int quantity;
    private Product product;
    private BigDecimal pricePerITem;
	public SupplyOffer supplyOffer;

    public SupplyOrder(int id, LocalDateTime dateOrdered,
    		Product product, int quantity, BigDecimal pricePerItem) {
        this.ID = id;
        this.dateOrdered = dateOrdered;
        this.product = product;
        this.pricePerITem = pricePerItem;
        this.quantity = quantity;
        this.delivered = false;
    }

	public SupplyOrder(int id, SupplyOffer supplyOffer, int quantity) {
		this.ID = id;
		this.supplyOffer = supplyOffer;
        this.dateOrdered = LocalDateTime.now();
        this.product = supplyOffer.getProduct();
        this.pricePerITem = supplyOffer.getPricePerItem();
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

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public BigDecimal getPricePerItem() {
		return pricePerITem;
	}

	public void setPricePerITem(BigDecimal pricePerITem) {
		this.pricePerITem = pricePerITem;
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

	public void setSupplyOffer(SupplyOffer supplyOffer)  {
		this.supplyOffer = supplyOffer;
	}

	public SupplyOffer getSupplyOffer()  {
		return supplyOffer;
	}

}
