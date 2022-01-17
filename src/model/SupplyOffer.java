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
    public Product product;
    
    /** The price per item. */
    public BigDecimal pricePerItem;
    
    /** The min quantity. */
    public int minQuantity;
    
    /** The date added. */
    public final LocalDateTime DATE_ADDED;
    
    /** The contractor. */
    public Contractor contractor;
    
    /** The active. */
    private boolean active;


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
    	this.product = product;
        this.ID = Id;
        this.pricePerItem = pricePerItem;
        this.minQuantity = minQuantity;
        this.contractor = contractor;
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
		return pricePerItem;
	}


	/**
	 * Sets the price per item.
	 *
	 * @param pricePerItem the new price per item
	 */
	public void setPricePerItem(BigDecimal pricePerItem) {
		this.pricePerItem = pricePerItem;
	}


	/**
	 * Gets the min quantity.
	 *
	 * @return the min quantity
	 */
	public int getMinQuantity() {
		return minQuantity;
	}


	/**
	 * Sets the min quantity.
	 *
	 * @param minQuantity the new min quantity
	 */
	public void setMinQuantity(int minQuantity) {
		this.minQuantity = minQuantity;
	}


	/**
	 * Gets the contractor.
	 *
	 * @return the contractor
	 */
	public Contractor getContractor() {
		return contractor;
	}


	/**
	 * Sets the contractor.
	 *
	 * @param contractor the new contractor
	 */
	public void setContractor(Contractor contractor) {
		this.contractor = contractor;
	}
	
    public Product getProduct() {
		return product;
	}


	public void setProduct(Product product) {
		this.product = product;
	}
	
	

}
