/**
 * 
 */
package model;

/**
 * @author Daniels Kanepe
 *
 */
public class Customer extends AbstractPerson
implements IFCustomer {
	
	private Order currentOrder = null;

	/**
	 * Constructor
	 */
	public Customer() {
		// TODO Auto-generated constructor stub
	}
	
	public Order getCurrentOrder() {
		return currentOrder;
	}

	public void setCurrentOrder(Order currentOrder) {
		this.currentOrder = currentOrder;
	}
	
	

}
