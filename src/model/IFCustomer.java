/**
 * 
 */
package model;

/**
 * @author Daniels Kanepe
 *
 */
public interface IFCustomer {
	
	public ShoppingCart getShoppingCart();
	public CustomerType getCustomerType();
	public void setCustomerType(CustomerType customerType);
}
