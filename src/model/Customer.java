/**
 * 
 */
package model;

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
	/**
	 * Prints out all the information about a customer
	 */
	public void printAllCustomerInfo(){
		System.out.println("ID: " + super.ID);
		System.out.println("First name: " + super.getFirstName());
		System.out.println("Last name: " + super.getLastName());
		System.out.println("Address: " + super.getAddress());
		System.out.println("Phone number: " + super.getMobile());
		System.out.println("Customer Type: " + customerType);
		System.out.println("Date of birth: " + super.getBirthDate());
	}
	
}