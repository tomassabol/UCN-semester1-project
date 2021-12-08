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
	private CustomerType customerType;

	/**
	 * Constructor
	 */
	public Customer(int ID, String firstName, String lastName, String address, String mobile, CustomerType customerType, LocalDateTime birthDate) {
		super(ID, firstName, lastName, address, mobile, birthDate);
		this.shoppingCart = new ShoppingCart(this);
		this.customerType = customerType;
	}
	
	public ShoppingCart getShoppingCart() {
		return shoppingCart;
	}
	
	public void clearShoppingCart() {
		shoppingCart.clear();
	}

	public CustomerType getCustomerType() {
		return customerType;
	}

	public void setCustomerType(CustomerType customerType) {
		this.customerType = customerType;
	}
	
}
