package controller;

import java.util.List;

import model.Customer;
import model.CustomerContainer;

public class CustomerController {

	public CustomerController() {
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

}
