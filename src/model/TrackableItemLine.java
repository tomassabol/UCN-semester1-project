package model;

import java.math.BigDecimal;
import java.util.ArrayList;

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

	public ArrayList<TrackableItem> getItems() {
		return items;
	}

	public void setItems(ArrayList<TrackableItem> items) {
		this.items = items;
	}

}
