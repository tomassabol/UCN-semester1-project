package model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class TrackableItemLine
implements IFItemLine{
	
	private ArrayList<TrackableItem> items;

	public TrackableItemLine() {
		items = new ArrayList<>();
	}
	
	@Override
	public BigDecimal calculatePrice() {
		BigDecimal totalPrice = BigDecimal.ZERO;
		for(TrackableItem item: this.items) {
			totalPrice.add(item.getProduct().getLatestSellingPrice());
		}
		return totalPrice;
	}

	@Override
	public int getQuantity() {
		return this.items.size();
	}

	public List<TrackableItem> getItems() {
		return items;
	}

	/**
	 * Adds a TrackableItem to a Line.
	 * Restriction: Only items of same type(product) can be added.
	 *
	 * @param item the item to add
	 * 
	 */
	public void addItem(TrackableItem item) {
		// If trying to add an item of different type(product), do nothing
		if (this.items.size() > 1) {
			if (this.items.get(0).getProduct() != item.getProduct()) {
				return;
			}
		}
		this.items.add(item);
	}

}
