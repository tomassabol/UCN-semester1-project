/**
 * 
 */
package model;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @author Daniels Kanepe
 *
 */
public class EmployeeCustomer extends AbstractPerson
implements IFCustomer, IFEmployee {
	
	private int CPRNumber;
	private ShoppingCart shoppingCart;
	/**
	 *  Constructor
	 */
	public EmployeeCustomer(int ID, int CPRNumber, String firstName, String lastName, String address, String mobile, LocalDateTime birthDate) {
		super(ID, firstName, lastName, address, mobile, birthDate);
		this.CPRNumber = CPRNumber;
		this.shoppingCart = new ShoppingCart();
	}

	public ShoppingCart getShoppingCart() {
		return shoppingCart;
	}
	

}
