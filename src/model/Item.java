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
	
	// ID is the barcode for the project
	public final int ID;
	private BigDecimal costPrice; 

	/**
	 * 
	 */
	public Item(int id, BigDecimal costPrice) {
		this.ID = id;
		this.costPrice = costPrice;
	}


	public BigDecimal getCostPrice() {
		return costPrice;
	}

	public void setCostPrice(BigDecimal costPrice) {
		this.costPrice = costPrice;
	}

}
