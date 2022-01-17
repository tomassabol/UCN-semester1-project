package model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class SupplyOffer {
    public final int ID;
    public Product product;
    public  BigDecimal pricePerItem;
    public int minQuantity;
    public final LocalDateTime DATE_ADDED;
    public Contractor contractor;
    private boolean active;


    /**
     * Instantiates a new supply offer.
     *
     * @param Id the id
     * @param pricePerItem the price per product
     * @param minQuantity the minimum order quantity
     * @param contractor The contractor supplying this offer
     * @param active whether the offer is active
     * @param dateAdded the date the offer was created
     */
    public SupplyOffer(int Id, BigDecimal pricePerItem,
    		int minQuantity, Contractor contractor,
    		boolean active, LocalDateTime dateAdded) {
        this.ID = Id;
        this.pricePerItem = pricePerItem;
        this.minQuantity = minQuantity;
        this.contractor = contractor;
        this.active = active;
        this.DATE_ADDED = dateAdded;
    }


	public boolean isActive() {
		return active;
	}


	public void setActive(boolean active) {
		this.active = active;
	}


	public int getID() {
		return ID;
	}

	public LocalDateTime getDateAdded() {
		return DATE_ADDED;
	}


	public BigDecimal getPricePerItem() {
		return pricePerItem;
	}


	public void setPricePerItem(BigDecimal pricePerItem) {
		this.pricePerItem = pricePerItem;
	}


	public int getMinQuantity() {
		return minQuantity;
	}


	public void setMinQuantity(int minQuantity) {
		this.minQuantity = minQuantity;
	}


	public Contractor getContractor() {
		return contractor;
	}


	public void setContractor(Contractor contractor) {
		this.contractor = contractor;
	}
	
	

}
