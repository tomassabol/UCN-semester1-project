/**
 * 
 */
package models;

import java.time.LocalDate;

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
	public Customer(int ID, String firstName, String lastName, String address, String mobile, CustomerType customerType, LocalDate birthDate) {
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

	public String getFirstName() {
		return super.getFirstName();
	}

	public String getLastName() {
		return super.getLastName();
	}

	public String getAddress() {
		return super.getAddress();
	}

	public String getMobile() {
		return super.getMobile();
	}

	public LocalDate getBirthDate() {
		return super.getBirthDate();
	}
	
}