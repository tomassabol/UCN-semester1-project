package model;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

public class StockBatch {
    private Product product;
    private SupplyOrder supplyOrder;
    private int quantity;
    private Set<TrackableItem> trackableItems;
    private LocalDateTime delivered;

    /**
     * Constructor class StockBatch for TrackableItem
     * @param trackableItems
     */
    public StockBatch(Set<TrackableItem> trackableItems) {
        if (trackableItems.isEmpty()) {
            throw new IllegalArgumentException("Must contain at least 1 product");
        }
        this.product = trackableItems.iterator().next().getProduct();
        allItemsOfSameProduct(trackableItems);
        this.trackableItems = (HashSet<TrackableItem>) trackableItems;
        quantity = 0;
    }

    /**
     * @param product  - untrackable product 
     * @param quantity - quantity of untrackableProduct
     */
    public StockBatch(Product product, int quantity) {
        if (quantity < 0) {
            throw new IllegalArgumentException("Quantity cannot be less than 0");
        }
        this.product = product;
    }

    /**
     * Checks that all items are the same type
     * Throws exception if not
     * @param trackableItems the trackableItems
     */
    private void allItemsOfSameProduct(Set<TrackableItem> trackableItems) {
		for(TrackableItem trackableItem: trackableItems) {
			if(this.product != trackableItem.getProduct()) {
				throw new IllegalArgumentException("All items must be of same type(product)");
			}
		}
	}

    // get/set product
    public Product getProduct() {
        return this.product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    // get/set supplyOrder
    public SupplyOrder getSupplyOrder() {
        return this.supplyOrder;
    }

    public void setSupplyOrder(SupplyOrder supplyOrder) {
        this.supplyOrder = supplyOrder;
    }

    // get/set quantity
    public int getquantity() {
        return this.quantity;
    }

    public void setquantity(int quantity) {
        this.quantity = quantity;
    }

    // get/set a set of TrackableItems
    public Set<TrackableItem> getTrackableItems() {
        return this.trackableItems;
    }

    public void setTrackableItems(Set<TrackableItem> trackableItems) {
        this.trackableItems = trackableItems;
    }

    // get/set 
    public LocalDateTime getDelivered() {
        return this.delivered;
    }

    public void setDelivered(LocalDateTime delivered) {
        this.delivered = delivered;
    }

}
