package model;

import java.math.BigDecimal;
import java.util.ArrayList;

public class UntrackableItemLine
implements IFItemLine{
	
	private Product product;
	private int quantity;
	
	@Override
	public BigDecimal calculatePrice() {
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
