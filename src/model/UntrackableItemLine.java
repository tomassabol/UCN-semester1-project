package model;

import java.math.BigDecimal;
import java.util.ArrayList;

public class UntrackableItemLine
implements IFItemLine{
	
	private Product product;
	private int quantity;
	
	public UntrackableItemLine(Product product, int quantity) {
		this.product = product;
		this.quantity = quantity;
	}
	
	/**
	 * Calculates the total price for all items in this ItemLine
	 * @return the price as BigDecimal or null if no price is set.
	 */
	@Override
	public BigDecimal calculatePrice() {
		BigDecimal sellingPrice = product.getLatestSellingPrice();
		if (sellingPrice == null) {
			return null;
		}
		return product.getLatestSellingPrice().multiply(BigDecimal.valueOf(quantity));
	}

	@Override
	public int getQuantity() {
		return this.quantity;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

}
