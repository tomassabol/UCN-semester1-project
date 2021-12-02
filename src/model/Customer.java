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

	public final int ID;
	/**
	 * Constructor
	 */
	public Customer(int id, String firstName, String lastName, String address, String mobil, LocalDateTime birthDate) {
		super(firstName, lastName, address, mobil, birthDate);
		this.ID = id;
	}
	
}
