package model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * The Class StockBatch.
 */
public class StockBatch {
    
    /** The product. */
    private final Product PRODUCT;
    
    /** The supply order. */
    private SupplyOrder supplyOrder;
    
    /** The untrackable item quantity. */
    private int untrackableItemQuantity;
    
    /** The trackable items. */
    private Set<TrackableItem> trackableItems;
    
    /** The delivered. */
    private LocalDateTime delivered;

    /**
     * Constructor class StockBatch for TrackableItem.
     *
     * @param trackableItems the trackable items
     */
    public StockBatch(Set<TrackableItem> trackableItems) {
        if (trackableItems.isEmpty()) {
            throw new IllegalArgumentException("Must contain at least 1 product");
        }
        this.PRODUCT = trackableItems.iterator().next().getProduct();
        allItemsOfSameProduct(trackableItems);
        this.trackableItems = (HashSet<TrackableItem>) trackableItems;
        untrackableItemQuantity = 0;
    }

    /**
     * Instantiates a new stock batch.
     *
     * @param product  - untrackable product
     * @param quantity - quantity of untrackableProduct
     */
    public StockBatch(Product product, int quantity) {
        if (quantity < 0) {
            throw new IllegalArgumentException("Quantity cannot be less than 0");
        }
        this.untrackableItemQuantity = quantity;
        this.PRODUCT = product;
    }

    /**
     * Checks that all items are the same type
     * Throws exception if not.
     *
     * @param trackableItems the trackableItems
     */
    private void allItemsOfSameProduct(Set<TrackableItem> trackableItems) {
		for(TrackableItem trackableItem: trackableItems) {
			if(this.PRODUCT != trackableItem.getProduct()) {
				throw new IllegalArgumentException("All items must be of same type(product)");
			}
		}
	}

    /**
     * Gets the product.
     *
     * @return the product
     */
    // get/set product
    public Product getProduct() {
        return this.PRODUCT;
    }

    /**
     * Gets the supply order.
     *
     * @return the supply order
     */
    // get/set supplyOrder
    public SupplyOrder getSupplyOrder() {
        return this.supplyOrder;
    }

    /**
     * Sets the supply order.
     *
     * @param supplyOrder the new supply order
     */
    public void setSupplyOrder(SupplyOrder supplyOrder) {
        this.supplyOrder = supplyOrder;
    }

    /**
     * Gets the untrackable itemquantity.
     *
     * @return the untrackable itemquantity
     */
    // get/set quantity
    public int getUntrackableItemQuantity() {
        return this.untrackableItemQuantity;
    }

    /**
     * Sets the untrackable itemquantity.
     *
     * @param quantity the new untrackable itemquantity
     */
    public void setUntrackableItemQuantity(int quantity) {
    	if (quantity < 0) {
    		throw new IllegalArgumentException("Untrackable item quanity cannot be < 0");
    	}
        this.untrackableItemQuantity = quantity;
    }

    /**
     * Gets the trackable items.
     *
     * @return the trackable items
     */
    // get/set a set of TrackableItems
    public Set<TrackableItem> getTrackableItems() {
        return this.trackableItems;
    }

    /**
     * Sets the trackable items.
     *
     * @param trackableItems the new trackable items
     */
    public void setTrackableItems(Set<TrackableItem> trackableItems) {
        this.trackableItems = trackableItems;
    }

    /**
     * Gets the delivered.
     *
     * @return the delivered
     */
    // get/set 
    public LocalDateTime getDelivered() {
        return this.delivered;
    }

    /**
     * Sets the delivered.
     *
     * @param delivered the new delivered
     */
    public void setDelivered(LocalDateTime delivered) {
        this.delivered = delivered;
    }
    
	/**
	 * Gets the quantity of buyable & loanable items
	 *
	 * @return the quantity of all items in the stock batch
	 */
	public int getTotalQuantity() {
		return this.trackableItems.size() + this.untrackableItemQuantity;
	}
	
	public int getBuyableItemQuantity() {
		int buyableItemQuantity = 0;
		for (TrackableItem trackableItem: this.trackableItems) {
			if (trackableItem.getTrackableItemType() == TrackableItem.TRACKABLE_ITEM_TYPE.BUYABLE) {
				buyableItemQuantity += 1;
			}
		}
		return buyableItemQuantity + this.untrackableItemQuantity;
	}
	
	public int getLoanableItemQuantity() {
		int loanableItemQuantity = 0;
		for (TrackableItem trackableItem: this.trackableItems) {
			if (trackableItem.getTrackableItemType() == TrackableItem.TRACKABLE_ITEM_TYPE.LOANABLE) {
				loanableItemQuantity += 1;
			}
		}
		return loanableItemQuantity;
	}
	
	/**
	 * Gets the trackable item quantity.
	 *
	 * @return the trackable item quantity
	 */
	private int getTrackableItemQuantity() {
		return this.trackableItems.size();
	}
    

    /**
     * Pop n amount of trackable items
     *
     * @param quantity the quantity of items to pop
     * @return the array list
     */
    public ArrayList<TrackableItem> popTrackableItems(int quantity) {
    	
    	ArrayList<TrackableItem> trackableItems = new ArrayList<>();
    	int i = 0;
    	Iterator<TrackableItem> iter = this.trackableItems.iterator();
    	while (i < quantity  && iter.hasNext()) {
    	 TrackableItem trackableItem = iter.next();
    	 trackableItems.add(trackableItem);
    	 iter.remove();
    	 i++;
    	}
    	return trackableItems;
    	
    }
    
	/**
	 * Calculates the price for this stock batch.
	 *
	 * @return the price as BigDecimal
	 *  or null if no price is set, or line's quantity is < 1
	 */
	public BigDecimal getTotalPrice() {
		// if no selling price has been set, return 0
		if (this.PRODUCT.getLatestSellingPrice() == null) {
			return null;
		}
		// if quantity below 0, return null
		int quantity = getTotalQuantity();
		if (quantity < 1) {
			return null;
		}
		// return calculated price
		return this.PRODUCT.getLatestSellingPrice().multiply(BigDecimal.valueOf(quantity));
	}

}
