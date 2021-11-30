/**
 * 
 */
package model;

/**
 * @author Daniels Kanepe
 *
 */
public class OrderLine {

	
	private BuyableItem item;
	private int quantity;

	/**
	 *  Constructor
	 */
	public OrderLine(BuyableItem item, int quantity) {
		this.item = item;
		this.quantity = quantity;
	}
	
	public Item getItem() {
		return item;
	}

	public void setItem(BuyableItem item) {
		this.item = item;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

}
