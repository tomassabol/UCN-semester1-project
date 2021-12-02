package controller;

import java.time.LocalDateTime;
import java.util.List;

import model.Customer;
import model.CustomerContainer;
import model.PrimaryKey;

public class CustomerController {

	public CustomerController() {
	}
	
	/**
	 * Creates the customer.
	 *
	 * @param ID the id
	 * @param firstName the first name
	 * @param lastName the last name
	 * @param address the address
	 * @param mobile the mobile number
	 * @param birthDate the birth date
	 * @return the newly created Customer object
	 */
	public Customer createCustomer(String firstName, String lastName, String address, String mobile, LocalDateTime birthDate) {
		Customer customer = new Customer(PrimaryKey.getNextCustomerID(), firstName, lastName, address, mobile, birthDate);
		CustomerContainer.getInstance().addCustomer(customer);
		return customer;
	}
	
	/**
	 * Find a customer by ID.
	 *
	 * @param id the id
	 * @return the customer
	 */
	public Customer findCustomerByID(int id) {
		return CustomerContainer.getInstance().findCustomerById(id);
	}
	
	
	/**
	 * Gets all customers.
	 *
	 * @return A list of the customers
	 */
	public List<Customer> getCustomers() {
		return CustomerContainer.getInstance().getCustomers();
	}
	
	public void getShoppingCart(Customer customer) {
		customer.getShoppingCart().getItemLines();
	}

}
