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
	
	private ShoppingCart shoppingCart;

	/**
	 * Constructor
	 */
	public Customer(int ID, String firstName, String lastName, String address, String mobil, LocalDateTime birthDate) {
		super(ID, firstName, lastName, address, mobil, birthDate);
		this.shoppingCart = new ShoppingCart();
	}
	
	public ShoppingCart getShoppingCart() {
		return shoppingCart;
	}
	
}
