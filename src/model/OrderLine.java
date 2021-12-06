package model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class OrderLine {
	
	private HashSet<TrackableItem> items;
	public final Product PRODUCT;
	private int untrackableItemQuantity;

	/*
	 * Constructor for specific, trackable items
	 */
	public OrderLine(Set<TrackableItem> items) {
		if (items.isEmpty()) {
			throw new IllegalArgumentException("Must contain at least 1 item");
		}
		this.PRODUCT = items.iterator().next().getProduct();
		allItemsOfSameProduct(items);
		this.items = (HashSet<TrackableItem>) items;
		untrackableItemQuantity = 0;
	}
	
	/*
	 * Constructor for non-trackable items
	 */
	public OrderLine(Product product, int quantity) {
		if (quantity < 0) {
			throw new IllegalArgumentException("Quantity cannot be less than 0!");
		}
		this.PRODUCT = product;
	}
	
	/**
	 * Checks that all items are of the same type,
	 * else throws an exception
	 *
	 * @param items the items
	 */
	private void allItemsOfSameProduct(Set<TrackableItem> items) {
		for(TrackableItem item: items) {
			if(this.PRODUCT != item.getProduct()) {
				throw new IllegalArgumentException("All items must be of same type(product)");
			}
		}
	}
	
	/**
	 * Calculates the total price for all items in this ItemLine.
	 *
	 * @return the price as BigDecimal
	 *  or null if no price is set, or line's quantity is < 1
	 */
	public BigDecimal calculateCurrentPrice() {
		// if no selling price has been set, return 0
		if (this.PRODUCT.getLatestSellingPrice() == null) {
			return null;
		}
		// if quantity below 0, return null
		int quantity = getQuantity();
		if (quantity < 1) {
			return null;
		}
		// return calculated price
		return this.PRODUCT.getLatestSellingPrice().multiply(BigDecimal.valueOf(quantity));
	}

	/**
	 * @return the quantity of items in this ItemLine
	 */
	public int getQuantity() {
		return this.items.size() + this.untrackableItemQuantity;
	}

	public Set<TrackableItem> getItems() {
		return items;
	}

	/**
	 * Adds a TrackableItem to the Line.
	 * Restriction: Only items of same type(product) can be added.
	 *
	 * @param item the item to add
	 * 
	 */
	public boolean addItem(TrackableItem item) {
		// If trying to add an item of different type(product), do nothing
		if (this.PRODUCT != item.getProduct()) {
			return false;
		}
		return this.items.add(item);
	}

	public Product getProduct() {
		return this.PRODUCT;
	}

}
