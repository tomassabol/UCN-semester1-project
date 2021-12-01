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
	/**
	 * Constructor
	 */
	public Customer(int id, String firstName, String lastName, String address, int mobil, String birthDate) {
		super(firstName, lastName, address, mobil, birthDate);
		this.ID = id;
	}
	
}
