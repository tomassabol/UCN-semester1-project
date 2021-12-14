package model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;

public class OrderLine {
	
	private final Set<TrackableItem> TRACKABLE_ITEMS;
	public final Product PRODUCT;
	private final int UNTRACKABLE_ITEM_QUANTITY;
	private final BigDecimal FIXED_PRODUCT_PRICE;
	private final BulkDiscount FIXED_BULK_DISCOUNT;
	private final LocalDateTime CREATION_DATE;

	public OrderLine(Product product, int untrackableItemQuantity,
			Set<TrackableItem> trackableItems, BigDecimal productPrice, 
			BulkDiscount bulkDiscount) {
		
		
		// check that all trackable items are of same product
		for(TrackableItem item: trackableItems) {
			if(product != item.getProduct()) {
				throw new IllegalArgumentException("All trackable items must be of same type(product)");
			}
		}
		
		
		// product price must not be null
		if (productPrice == null) {
			throw new IllegalArgumentException("Selling price cannot be null!");
		}
		
		
		// If product price < 0, throw exception
		if (productPrice.compareTo(BigDecimal.ZERO) < 0) {
			throw new IllegalArgumentException("Product price cannot be below zero!");
		}
		
		// An orderline must contain at least one item
		if (trackableItems.size() == 0 && untrackableItemQuantity == 0) {
			throw new IllegalArgumentException("An orderline must contain at least one item!");
		}
		
		this.PRODUCT = product;
		this.UNTRACKABLE_ITEM_QUANTITY = untrackableItemQuantity;
		this.TRACKABLE_ITEMS = trackableItems;
		this.FIXED_BULK_DISCOUNT = bulkDiscount;
		this.FIXED_PRODUCT_PRICE = productPrice;
		this.CREATION_DATE = LocalDateTime.now();
		
	}
	
	
	/**
	 * Calculates the total price for all items in this ItemLine
	 * without applying the bulk discount (if exists)
	 *
	 * @return the price as BigDecimal
	 */
	public BigDecimal getTotalPriceWithoutBulkDiscount() {

		return this.FIXED_PRODUCT_PRICE.multiply(BigDecimal.valueOf(UNTRACKABLE_ITEM_QUANTITY));
	}
	
	/**
	 * Gets the total price with bulk discount for all items in this ItemLine
	 * If no bulk discount is set, normal price is returned
	 *
	 * @return the total price with bulk discount
	 */
	public BigDecimal getTotalPriceWithBulkDiscount() {
		BigDecimal rawPrice = this.getTotalPriceWithoutBulkDiscount();
		if (this.getBulkDiscount() == null) {
			return rawPrice;
		}
		return rawPrice.multiply(BigDecimal.valueOf((100 - FIXED_BULK_DISCOUNT.getDiscountPercentage() / 100.0)));
	}

	/**
	 * @return the quantity of items in this ItemLine
	 */
	public int getQuantity() {
		return this.TRACKABLE_ITEMS.size() + this.UNTRACKABLE_ITEM_QUANTITY;
	}

	/**
	 * Gets the trackable items.
	 *
	 * @return the trackable items
	 */
	public Set<TrackableItem> getTrackableItems() {
		return TRACKABLE_ITEMS;
	}

	public Product getProduct() {
		return this.PRODUCT;
	}


	public int getUntrackableItemQuantity() {
		return UNTRACKABLE_ITEM_QUANTITY;
	}


	public BigDecimal getFixedProductPrice() {
		return FIXED_PRODUCT_PRICE;
	}


	public BulkDiscount getBulkDiscount() {
		return FIXED_BULK_DISCOUNT;
	}

	public Product getPRODUCT() {
		return PRODUCT;
	}


	public Set<TrackableItem> getTRACKABLE_ITEMS() {
		return TRACKABLE_ITEMS;
	}


	public int getUNTRACKABLE_ITEM_QUANTITY() {
		return UNTRACKABLE_ITEM_QUANTITY;
	}


	public BigDecimal getFIXED_PRODUCT_PRICE() {
		return FIXED_PRODUCT_PRICE;
	}


	public BulkDiscount getFIXED_BULK_DISCOUNT() {
		return FIXED_BULK_DISCOUNT;
	}


	public LocalDateTime getCREATION_DATE() {
		return CREATION_DATE;
	}
	
	
	

}
