/**
 * 
 */
package model;

import java.math.BigDecimal;

/**
 * @author Daniels Kanepe
 *
 */
public class BuyableItem extends Item {
	
	private BigDecimal sellingPrice;

	/**
	 * 
	 */
	public BuyableItem(int barcode, BigDecimal costPrice, BigDecimal sellingPrice) {
		super(barcode, costPrice);
		this.sellingPrice = sellingPrice;
	}

	public BigDecimal getSellingPrice() {
		return sellingPrice;
	}

	public void setSellingPrice(BigDecimal sellingPrice) {
		this.sellingPrice = sellingPrice;
	}
	
	
		

}
