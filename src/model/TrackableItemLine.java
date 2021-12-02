package model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class TrackableItemLine
implements IFItemLine{
	
	private HashSet<TrackableItem> items;

	/*
	 * COnstructor
	 */
	public TrackableItemLine(Set<TrackableItem> items) {
		if (items.isEmpty()) {
			throw new IllegalArgumentException("Must contain at least 1 item");
		}
		allItemsOfSameProduct(items);
		this.items = (HashSet<TrackableItem>) items;
	}
	
	/**
	 * Checks that all items are of the same type
	 *
	 * @param items the items
	 */
	private void allItemsOfSameProduct(Set<TrackableItem> items) {
		for(TrackableItem item: items) {
			if(items.iterator().next().getProduct() != item.getProduct()) {
				throw new IllegalArgumentException("All items must be of same type(product)");
			}
		}
	}
	
	/**
	 * Calculates the total price for all items in this ItemLine
	 *
	 * @return the price as BigDecimal or null if no price is set.
	 */
	@Override
	public BigDecimal calculatePrice() {
		// TODO: RETURN null if no selling price is set
		BigDecimal totalPrice = BigDecimal.ZERO;
		for(TrackableItem item: this.items) {
			totalPrice = totalPrice.add(item.getProduct().getLatestSellingPrice());
		}
		return totalPrice;
	}

	/**
	 * @return the quantity of items in this ItemLine
	 */
	@Override
	public int getQuantity() {
		return this.items.size();
	}

	public Set<TrackableItem> getItems() {
		return items;
	}

	/**
	 * Adds a TrackableItem to a Line.
	 * Restriction: Only items of same type(product) can be added.
	 *
	 * @param item the item to add
	 * 
	 */
	public boolean addItem(TrackableItem item) {
		// If trying to add an item of different type(product), do nothing
		if (this.items.iterator().next().getProduct() != item.getProduct()) {
			return false;
		}
		return this.items.add(item);
	}

	@Override
	public Product getProduct() {
		return this.items.iterator().next().getProduct();
	}

}
