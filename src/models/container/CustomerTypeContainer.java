package models.container;

import java.util.ArrayList;
import java.util.List;

import model.CustomerType;

public class CustomerTypeContainer {
    private static CustomerTypeContainer instance;
    private ArrayList<CustomerType> customerTypes;

    /**
     * Constructor class CustomerTypeContainer
     */
    private CustomerTypeContainer() {
        customerTypes = new ArrayList<>();
    }

    /**
     * @return instance of CustomerTypeContainer
     */
    public static CustomerTypeContainer getInstance() {
        if (instance == null) {
            instance = new CustomerTypeContainer();
        }
        return instance;
    }

    /**
     * @param customerType - customer type to be added to an ArrayList
     * @return true if customer type was successfully added
     */
    public boolean addCustomer(CustomerType customerType) {
        return customerTypes.add(customerType);
    }

    /**
     * @return list of all customer types
     */
    public List<CustomerType> getCustomerTypes() {
        return this.customerTypes;
    }

    /**
     * @param customerType - customer type to be removed from ArrayList
     * @return true if customer type was successfully removed
     */
    public boolean removeCustomerType(CustomerType customerType) {
        return customerTypes.remove(customerType);
    }

    /**
     * @param name - name of the customer type to be found
     * @return customer type if it was found
     */
    public CustomerType findCustomerTypeById(int id) {
        for (CustomerType customerType : customerTypes) {
            if (customerType.ID == id) {
                return customerType;
            }
        }
        return null;
    }
}
