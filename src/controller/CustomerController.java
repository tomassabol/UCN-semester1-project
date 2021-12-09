package controller;

import java.time.LocalDate;
import java.util.List;

import model.Customer;
import model.CustomerContainer;
import model.CustomerType;
import model.CustomerTypeContainer;
import model.PrimaryKey;

public class CustomerController {

	private CustomerTypeContainer customerTypeContainer;

	public CustomerController() {
		customerTypeContainer = CustomerTypeContainer.getInstance();
	}
	
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
		Customer customer = new Customer(PrimaryKey.getNextCustomerID(), firstName, lastName, address, mobile, customerType, birthDate);
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
		CustomerType customerType = new CustomerType(PrimaryKey.getNextCustomerTypeID(), type, discountPercentage);
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
	public boolean removeCustomer(int id){
		Customer customer = findCustomerByID(id);
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
		return customerTypeContainer.getCustomerTypes();
	}

	/**
	 * 
	 * @param name - name of the customer type to be found
	 * @return the customer type if it was found
	 */
	public CustomerType findCustomerTypeById(int id){
		return customerTypeContainer.findCustomerTypeById(id);
	}

	/**
	 * Prints out all the customers informations
	 */
	public void printAllCustomerInfo(){
		CustomerContainer.getInstance().printAllCustomerInfo();
	}

	/**
	 * 
	 * @param id - The id of the customer whose first name is to be updated
	 * @param firstName - the new first name the first name to be updated to
	 */
	public void updateFirstName(int id, String firstName){
		Customer customer = findCustomerByID(id);
		customer.setFirstName(firstName);
	}

	/**
	 * 
	 * @param id - The id of the customer whose first name is to be updated
	 * @param lastName - the new last name the last name to be updated to
	 */
	public void updateLastName(int id, String lastName){
		Customer customer = findCustomerByID(id);
		customer.setLastName(lastName);
	}

	/**
	 * 
	 * @param id - The id of the customer whose first name is to be updated
	 * @param address - the new address the address to be updated to
	 */
	public void updateAddress(int id, String address){
		Customer customer = findCustomerByID(id);
		customer.setAddress(address);
	}

	/**
	 * 
	 * @param id - The id of the customer whose first name is to be updated
	 * @param mobile - the new phone number the phone number to be updated to
	 */
	public void updateMobile(int id, String mobile){
		Customer customer = findCustomerByID(id);
		customer.setMobile(mobile);
	}

	/**
	 * 
	 * @param id - The id of the customer whose first name is to be updated
	 * @param customerType - the new customer type the customer type to be updated to
	 */
	public void updateCustomerType(int id, CustomerType customerType){
		Customer customer = findCustomerByID(id);
		customer.setCustomerType(customerType);
	}

	/**
	 * 
	 * @param id - The id of the customer whose first name is to be updated
	 * @param birthDate - the new birth date the birth date to be updated to
	 */
	public void updateBirthDate(int id, LocalDateTime birthDate){
		Customer customer = findCustomerByID(id);
		customer.setBirthDate(birthDate);
	}

	/**
	 * Remove customer type from the container
	 * @param name - The name of the customer type to be removed from the container
	 */
	public void deleteCustomerType(int id){
		customerTypeContainer.removeCustomerType(findCustomerTypeById(id));
	}

	/**
     * 
     * @param name
     * @param newName
     */
    public void updateCustomerTypeName(int id, String newName){
        CustomerType customerType = customerTypeContainer.findCustomerTypeById(id);
		customerType.setName(newName);
    }

	/**
     * 
     * @param name
     * @param newDiscountPercantage
     */
    public void updateCustomerTypeDiscountPercantage(int id, int newDiscountPercantage){
        CustomerType customerType = customerTypeContainer.findCustomerTypeById(id);
		customerType.setDiscountPercentage(newDiscountPercantage);
    }
}
