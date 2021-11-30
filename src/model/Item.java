/**
 * 
 */
package model;

import java.math.BigDecimal;

/**
 * @author Daniels Kanepe
 *
 */
public class Item {
	
	private int barcode;
	private BigDecimal costPrice; 

	/**
	 * 
	 */
	public Item(int barcode, BigDecimal costPrice) {
		this.barcode = barcode;
		this.costPrice = costPrice;
	}

	public int getBarcode() {
		return barcode;
	}

	public void setBarcode(int barcode) {
		this.barcode = barcode;
	}

	public BigDecimal getCostPrice() {
		return costPrice;
	}

	public void setCostPrice(BigDecimal costPrice) {
		this.costPrice = costPrice;
	}

}
