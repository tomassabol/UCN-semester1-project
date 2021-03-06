package controller;

import java.time.LocalDate;
import java.util.List;

import models.Customer;
import models.CustomerType;
import models.IFCustomer;
import models.PrimaryKey;
import models.containers.CustomerContainer;
import models.containers.CustomerTypeContainer;

public class CustomerController {

	
	/**
	 * Creates the customer
	 *
	 * @param ID the id
	 * @param firstName the first name
	 * @param lastName the last name
	 * @param address the address
	 * @param mobile the mobile number
	 * @param birthDate the birth date
	 * @return the newly created Customer object
	 */
	public Customer createCustomer(String firstName, String lastName, String address, String mobile, CustomerType customerType, LocalDate birthDate) {
		Customer customer = new Customer(PrimaryKey.getID(PrimaryKey.Keys.CUSTOMER), firstName, lastName, address, mobile, customerType, birthDate);
		CustomerContainer.getInstance().addCustomer(customer);
		return customer;
	}
	
	/**
	 * Creates a customer type
	 * 
	 * @param type the name of the customer type
	 * @param discountPercentage the discount the customer gets
	 * @return the newly created customer type
	 */
	public CustomerType createCustomerType(String type, int discountPercentage){
		CustomerType customerType = new CustomerType(PrimaryKey.getID(PrimaryKey.Keys.CUSTOMER), type, discountPercentage);
		CustomerTypeContainer.getInstance().addCustomer(customerType);
		return customerType;
	}

	/**
	 * Find a customer by ID.
	 *
	 * @param id - the id of the customer to be found
	 * @return the customer
	 */
	public Customer findCustomerByID(int id) {
		return CustomerContainer.getInstance().findCustomerById(id);
	}
	
	/**
	 * Remove a customer by ID.
	 * 
	 * @param id - the id of the customer to be removed
	 */
	public boolean removeCustomer(Customer customer){
		return CustomerContainer.getInstance().removeCustomer(customer);
	}
	/**
	 * Gets all customers.
	 *
	 * @return A list of the customers
	 */
	public List<Customer> getCustomers() {
		return CustomerContainer.getInstance().getCustomers();
	}
	
	/**
	 * Gets the shopping cart for a customer
	 *
	 * @param customer the customer
	 * @return the shopping cart
	 */
	public void getShoppingCart(Customer customer) {
		customer.getShoppingCart().getItemLines();
	}

	/**
	 * 
	 * @return a list of the customer types
	 */
	public List<CustomerType> getCustomerTypes(){
		return CustomerTypeContainer.getInstance().getCustomerTypes();
	}

	/**
	 * 
	 * @param name - name of the customer type to be found
	 * @return the customer type if it was found
	 */
	public CustomerType findCustomerTypeById(int id){
		return CustomerTypeContainer.getInstance().findCustomerTypeById(id);
	}
	
	/**
	 * 
	 * @param id - The id of the customer whose first name is to be updated
	 * @param firstName - the new first name the first name to be updated to
	 */
	public void updateFirstName(IFCustomer customer, String firstName){
		customer.setFirstName(firstName);
	}

	/**
	 * 
	 * @param id - The id of the customer whose first name is to be updated
	 * @param lastName - the new last name the last name to be updated to
	 */
	public void updateLastName(IFCustomer customer, String lastName){
		customer.setLastName(lastName);
	}

	/**
	 * 
	 * @param id - The id of the customer whose first name is to be updated
	 * @param address - the new address the address to be updated to
	 */
	public void updateAddress(IFCustomer customer, String address){
		customer.setAddress(address);
	}

	/**
	 * 
	 * @param id - The id of the customer whose first name is to be updated
	 * @param mobile - the new phone number the phone number to be updated to
	 */
	public void updateMobile(IFCustomer customer, String mobile){
		customer.setMobile(mobile);
	}

	/**
	 * 
	 * @param id - The id of the customer whose first name is to be updated
	 * @param customerType - the new customer type the customer type to be updated to
	 */
	public void updateCustomerType(IFCustomer customer, CustomerType customerType){
		customer.setCustomerType(customerType);
	}

	/**
	 * 
	 * @param id - The id of the customer whose first name is to be updated
	 * @param birthDate - the new birth date the birth date to be updated to
	 */
	public void updateBirthDate(IFCustomer customer, LocalDate birthDate){
		customer.setBirthDate(birthDate);
	}

	/**
	 * Remove customer type from the container
	 * @param name - The name of the customer type to be removed from the container
	 */
	public void deleteCustomerType(CustomerType customerType){
		CustomerTypeContainer.getInstance().removeCustomerType(customerType);
	}

	/**
     * 
     * @param name
     * @param newName
     */
    public void updateCustomerTypeName(CustomerType customerType, String newName){
		customerType.setName(newName);
    }

	/**
     * 
     * @param name
     * @param newDiscountPercantage
     */
    public void updateCustomerTypeDiscountPercantage(CustomerType customerType, int newDiscountPercantage){
		customerType.setDiscountPercentage(newDiscountPercantage);
    }
}
