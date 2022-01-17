package model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * The Class SupplyOffer.
 */
public class SupplyOffer {
    
    /** The id. */
    public final int ID;
    
    /** The product. */
    public final Product PRODUCT;
    
    /** The price per item. */
    public final BigDecimal PRICE_PER_ITEM;
    
    /** The min quantity. */
    public final int MIN_QUANTITY;
    
    /** The date added. */
    public final LocalDateTime DATE_ADDED;
    
    /** The contractor. */
    public final Contractor CONTRACTOR;
    
    /** The active. */
    private boolean active;


    // NOTE: TO EDIT A SUPPLY OFFER, CREATE A NEW ONE!
    
	/**
     * Instantiates a new supply offer.
     *
     * @param Id the id
     * @param pricePerItem the price per item
     * @param minQuantity the min quantity
     * @param contractor the contractor
     * @param active the active
     * @param dateAdded the date added
     */
    public SupplyOffer(int Id, Product product, BigDecimal pricePerItem,
    		int minQuantity, Contractor contractor,
    		boolean active, LocalDateTime dateAdded) {
    	this.PRODUCT = product;
        this.ID = Id;
        this.PRICE_PER_ITEM = pricePerItem;
        this.MIN_QUANTITY = minQuantity;
        this.CONTRACTOR = contractor;
        this.active = active;
        this.DATE_ADDED = dateAdded;
    }


	/**
	 * Checks if is active.
	 *
	 * @return true, if is active
	 */
	public boolean isActive() {
		return active;
	}


	/**
	 * Sets the active.
	 *
	 * @param active the new active
	 */
	public void setActive(boolean active) {
		this.active = active;
	}


	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public int getID() {
		return ID;
	}

	/**
	 * Gets the date added.
	 *
	 * @return the date added
	 */
	public LocalDateTime getDateAdded() {
		return DATE_ADDED;
	}


	/**
	 * Gets the price per item.
	 *
	 * @return the price per item
	 */
	public BigDecimal getPricePerItem() {
		return PRICE_PER_ITEM;
	}




	/**
	 * Gets the min quantity.
	 *
	 * @return the min quantity
	 */
	public int getMinQuantity() {
		return MIN_QUANTITY;
	}




	/**
	 * Gets the contractor.
	 *
	 * @return the contractor
	 */
	public Contractor getContractor() {
		return CONTRACTOR;
	}

	
    public Product getProduct() {
		return PRODUCT;
	}
	

}
