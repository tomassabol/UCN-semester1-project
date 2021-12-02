package model;

import java.math.BigDecimal;

public interface IFItemLine {
	public BigDecimal calculatePrice();
	public int getQuantity();
	public Product getProduct();
}
