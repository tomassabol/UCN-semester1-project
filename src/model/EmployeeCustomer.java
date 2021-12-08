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
	
	private String CPRNumber;
	private ShoppingCart shoppingCart;
	
	/**
	 *  Constructor
	 */
	public EmployeeCustomer(int ID, String CPRNumber, String firstName, String lastName, String address, String mobile, LocalDateTime birthDate) {
		super(ID, firstName, lastName, address, mobile, birthDate);
		this.CPRNumber = CPRNumber;
		this.shoppingCart = new ShoppingCart();
	}

	public ShoppingCart getShoppingCart() {
		return shoppingCart;
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
	
	public String getHashedPassword() {
		return "";
	}
	
	public String getUsername() {
		return "";
	}

}
