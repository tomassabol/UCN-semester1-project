package model;

import java.math.BigDecimal;

public class QuoteItemLine {
	
	public final Product PRODUCT;
	private final int QUANTITY;
	private final BulkDiscount FIXED_BULK_DISCOUNT;
	private final BigDecimal FIXED_PRODUCT_PRICE;
	
	public QuoteItemLine(Product product, int quantity) {
		if (quantity < 0) {
			throw new IllegalArgumentException("Quantity cannot be less than 0!");
		}
		if (product == null) {
			throw new IllegalArgumentException("Product cannot be null!");
		}
		this.PRODUCT = product;
		this.QUANTITY = quantity;
		
		// fix the price & bulk discount
		FIXED_BULK_DISCOUNT = DiscountContainer.getInstance().getBestBulkDiscount(PRODUCT, quantity);
		FIXED_PRODUCT_PRICE = PRODUCT.getLatestSellingPrice();
	}
	

	/**
	 * Gets the fixed price without bulk discount applied.
	 *
	 * @return the fixed price without bulk discount
	 */
	public BigDecimal getFixedPriceWithoutBulkDiscount() {
		return FIXED_PRODUCT_PRICE.multiply(BigDecimal.valueOf(QUANTITY));
	}
	
	/**
	 * Gets the fixed price with bulk discount applied
	 *
	 * @return the fixed price with bulk discount applied
	 */
	public BigDecimal getFixedPriceWithBulkDiscount() {
		BigDecimal rawPrice = this.getFixedPriceWithoutBulkDiscount();
		return rawPrice.multiply(BigDecimal.valueOf((100 - this.FIXED_BULK_DISCOUNT.getDiscountPercentage() / 100)));
	
	}
	
	/**
	 * Gets the quantity of items in this item line
	 *
	 * @return the quantity of items
	 */
	public int getQuantity() {
		return this.QUANTITY;
	}
	
	/**
	 * Gets the fixed bulk discount.
	 *
	 * @return the fixed bulk discount
	 */
	public BulkDiscount getBulkDiscount() {
		return this.FIXED_BULK_DISCOUNT;
	}


	public Product getPRODUCT() {
		return PRODUCT;
	}


	public int getQUANTITY() {
		return QUANTITY;
	}


	public BulkDiscount getFIXED_BULK_DISCOUNT() {
		return FIXED_BULK_DISCOUNT;
	}


	public BigDecimal getFIXED_PRODUCT_PRICE() {
		return FIXED_PRODUCT_PRICE;
	}

	
}
