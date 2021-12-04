package model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class SupplyOffer {
    public final int ID;
    public final BigDecimal PRICE_PER_ITEM;
    public final int MIN_QUANTITY;
    public final LocalDateTime DATE_ADDED;
    public final Contractor CONTRACTOR;
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
        this.PRICE_PER_ITEM = pricePerItem;
        this.MIN_QUANTITY = minQuantity;
        this.CONTRACTOR = contractor;
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


	public BigDecimal getPRICE_PER_PRODUCT() {
		return PRICE_PER_ITEM;
	}


	public int getMIN_QUANTITY() {
		return MIN_QUANTITY;
	}


	public LocalDateTime getDATE_ADDED() {
		return DATE_ADDED;
	}


	public Contractor getCONTRACTOR() {
		return CONTRACTOR;
	}



}
