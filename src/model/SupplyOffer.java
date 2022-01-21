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
    
    /** The contractor. */
    public Contractor contractor;
    
    
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
	 * Gets the price per item.
	 *
	 * @return the price per item
	 */
	public BigDecimal getPricePerItem() {
		return pricePerItem;
	}




	public void setProduct(Product product) {
		this.product = product;
	}

	public void setPricePerItem(BigDecimal pricePerItem) {
		this.pricePerItem = pricePerItem;
	}

	public void setMinQuantity(int minQuantity) {
		this.minQuantity = minQuantity;
	}

	public void setContractor(Contractor contractor) {
		this.contractor = contractor;
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
	 * Gets the contractor.
	 *
	 * @return the contractor
	 */
	public Contractor getContractor() {
		return contractor;
	}

	
    public Product getProduct() {
		return product;
	}
	

}
