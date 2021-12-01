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

	public final int ID;
	private String firstName;
	private String secondName;
	/**
	 * Constructor
	 */
	public Customer(int id, String firstName, String secondName) {
		this.ID = id;
		this.firstName = firstName;
		this.secondName = secondName;
	}

	// set/get firstname
	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	// set/get secondName
	public String getSecondName() {
		return this.secondName;
	}

	public void setSecondName(String secondName) {
		this.secondName = secondName;
	}
	
	public Order getCurrentOrder() {
		return currentOrder;
	}

	public void setCurrentOrder(Order currentOrder) {
		this.currentOrder = currentOrder;
	}
	
	

}
