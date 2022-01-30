/**
 * 
 */
package models;

import java.time.LocalDate;

/**
 * @author Daniels Kanepe
 *
 */
public class EmployeeCustomer extends AbstractPerson
implements IFCustomer, IFEmployee {
	
	private String CPRNumber;
	private ShoppingCart shoppingCart;
	private CustomerType customerType;
	private String email;
	private String hashedPassword;
	
	/**
	 *  Constructor
	 */
	public EmployeeCustomer(int ID, String CPRNumber, String email, String hashedPassword, String firstName, String lastName, String address, String mobile, CustomerType customertype, LocalDate birthDate) {
		super(ID, firstName, lastName, address, mobile, birthDate);
		this.CPRNumber = CPRNumber;
		this.shoppingCart = new ShoppingCart(this);
		this.customerType = customertype;
		this.email = email;
		this.hashedPassword = hashedPassword;
	}
	
	/**
	 * Getter for CPRnumber
	 */
	public String getCPRnumber(){
		return CPRNumber;
	}

	/**
	 * Setter for CPRNumber
	 * @param CPRNumber The new CPR number
	 */
	public void setCPRNumber(String CPRNumber){
		this.CPRNumber = CPRNumber;
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
	
	public int getID() {
		return this.ID;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getHashedPassword() {
		return hashedPassword;
	}

	public void setHashedPassword(String hashedPassword) {
		this.hashedPassword = hashedPassword;
	}

}
