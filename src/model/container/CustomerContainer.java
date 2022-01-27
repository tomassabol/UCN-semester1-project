package model.container;

import java.util.ArrayList;
import java.util.List;

import model.Customer;

public class CustomerContainer {
    private static CustomerContainer instance;
    private ArrayList<Customer> customers;

    /**
     * Constructor class CustomerContainer
     */
    private CustomerContainer() {
        customers = new ArrayList<>();
    }

    /**
     * @return instance of CustomerContainer
     */
    public static CustomerContainer getInstance() {
        if (instance == null) {
            instance = new CustomerContainer();
        }
        return instance;
    }

    /**
     * @param customer - customer to be added to an ArrayList
     * @return true if customer was successfully added
     */
    public boolean addCustomer(Customer customer) {
        return customers.add(customer);
    }

    /**
     * @return list of all customers
     */
    public List<Customer> getCustomers() {
        return this.customers;
    }

    /**
     * @param customer - customer to be removed from ArrayList
     * @return true if customer was successfully removed
     */
    public boolean removeCustomer(Customer customer) {
        return customers.remove(customer);
    }

    /**
     * @param customerId - id of a customer to be found
     * @return customer if the customer was found
     */
    public Customer findCustomerById(int customerId) {
        for (Customer customer : customers) {
            if (customer.ID == customerId) {
                return customer;
            }
        }
        return null;
    }
}