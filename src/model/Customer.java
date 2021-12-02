/**
 * 
 */
package model;

import java.time.LocalDateTime;

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
	public Customer(int ID, String firstName, String lastName, String address, String mobil, LocalDateTime birthDate) {
		super(ID, firstName, lastName, address, mobil, birthDate);
	}
	
}
