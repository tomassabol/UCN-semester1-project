package model;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

public class OrderLine {
	
	private Set<TrackableItem> trackableItems;
	public final Product PRODUCT;
	private int untrackableItemQuantity;
	private BigDecimal fixedProductPrice;
	private BulkDiscount bulkDiscount = null;

	public OrderLine(Product product, int untrackableItemQuantity,
			Set<TrackableItem> trackableItems, BigDecimal productPrice, 
			BulkDiscount bulkDiscount) {
		// set the product
		this.PRODUCT = product;
		// check that quantity is not below zero
		if (untrackableItemQuantity < 1) {
			throw new IllegalArgumentException("The orderline must contain at least 1 item");
		}
		this.untrackableItemQuantity = untrackableItemQuantity;
		
		// check that all trackable items are of same product
		for(TrackableItem item: trackableItems) {
			if(this.PRODUCT != item.getProduct()) {
				throw new IllegalArgumentException("All items must be of same type(product)");
			}
		}
		this.trackableItems = trackableItems;
		
		// If product price < 0, throw exception
		if (productPrice.compareTo(BigDecimal.ZERO) > 0) {
			throw new IllegalArgumentException("Product price cannot be below zero!");
		}
		
		// An orderline must contain at least one item
		if (trackableItems.size() == 0 && untrackableItemQuantity == 0) {
			throw new IllegalArgumentException("An orderline must contain at least one item!");
		}
		
		// set the bulk discount
		this.bulkDiscount = bulkDiscount;
		
	}
	
	
	/**
	 * Calculates the total price for all items in this ItemLine
	 * without applying the bulk discount (if exists)
	 *
	 * @return the price as BigDecimal
	 */
	public BigDecimal getTotalPriceWithoutBulkDiscount() {

		return this.fixedProductPrice.multiply(BigDecimal.valueOf(untrackableItemQuantity));
	}

	/**
	 * @return the quantity of items in this ItemLine
	 */
	public int getQuantity() {
		return this.trackableItems.size() + this.untrackableItemQuantity;
	}

	/**
	 * Gets the trackable items.
	 *
	 * @return the trackable items
	 */
	public Set<TrackableItem> getTrackableItems() {
		return trackableItems;
	}
	
	/**
	 * Sets the trackable items.
	 * BEWARE: It does not update the bulk discount.
	 *
	 * @param trackableItems the new trackable items
	 */
	public void setTrackableItems(Set<TrackableItem> trackableItems) {
		this.trackableItems = trackableItems;}
	
	/**
	 * Adds a trackable item.
	 * BEWARE: It does not update the bulk discount.
	 *
	 * @param trackableItem the trackable item
	 */
	public void addTrackableItem(TrackableItem trackableItem) {
		this.trackableItems.add(trackableItem);}

	public Product getProduct() {
		return this.PRODUCT;
	}


	public int getUntrackableItemQuantity() {
		return untrackableItemQuantity;
	}


	public void setUntrackableItemQuantity(int untrackableItemQuantity) {
		this.untrackableItemQuantity = untrackableItemQuantity;
	}


	public BigDecimal getFixedProductPrice() {
		return fixedProductPrice;
	}


	/**
	 * Sets the product price
	 * BEWARE: It does not update the bulk discount..
	 *
	 * @param fixedProductPrice the new fixed product price
	 */
	public void setFixedProductPrice(BigDecimal fixedProductPrice) {
		this.fixedProductPrice = fixedProductPrice;
	}


	public BulkDiscount getBulkDiscount() {
		return bulkDiscount;
	}


	public void setBulkDiscount(BulkDiscount bulkDiscount) {
		this.bulkDiscount = bulkDiscount;
	}


	public Product getPRODUCT() {
		return PRODUCT;
	}
	
	

}
