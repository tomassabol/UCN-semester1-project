/**
 * 
 */
package model;

/**
 * @author Daniels Kanepe
 *
 */
public class EmployeeCustomer extends AbstractPerson
implements IFCustomer, IFEmployee {
	
	private Order currentOrder = null;

	/**
	 *  Constructor
	 */
	public EmployeeCustomer() {
		// TODO Auto-generated constructor stub
	}
	
	public void setCurrentOrder(Order currentOrder) {
		this.currentOrder = currentOrder;
	}

	public Order getCurrentOrder() {
		return currentOrder;
	}

}
