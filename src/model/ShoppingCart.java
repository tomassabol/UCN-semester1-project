package model;

import java.util.HashMap;

public class CurrentOrderContainer {
    private HashMap<Customer, Order> orders;
    private static CurrentOrderContainer instance;

    /*
     * Constructor
     */
    private CurrentOrderContainer() {
        orders = new HashMap<>();
    }

    public static CurrentOrderContainer getInstance() {
        if (instance == null) {
            instance = new CurrentOrderContainer();
        }
        return instance;
    }

    /**
     * Gets a specific customer's cart aka current order
     *
     * @param customer the customer
     * @return the current order
     */
    public Order getCurrentOrder(IFCustomer customer) {
        return this.orders.get(customer);
    }

}
