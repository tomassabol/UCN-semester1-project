/**
 * 
 */
package models;

import java.time.LocalDate;

/**
 * @author Daniels Kanepe
 *
 */
public interface IFCustomer {
	
	public ShoppingCart getShoppingCart();
	public CustomerType getCustomerType();
	public void setCustomerType(CustomerType customerType);
	// These come from abstract person
	public String getFirstName();
	public void setFirstName(String firstName);
	public String getLastName();
	public void setLastName(String lastName);
	public String getAddress();
	public void setAddress(String address);
	public String getMobile();
	public void setMobile(String mobile);
	public LocalDate getBirthDate();
	public void setBirthDate(LocalDate birthDate);
	public int getID();
}
