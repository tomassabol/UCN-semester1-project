package model;

import java.util.HashMap;

public class CurrentOrderContainer {
    // contains
    private HashMap<Customer, Order> orders;
    private static CurrentOrderContainer instance;

    private CurrentOrderContainer() {
        orders = new HashMap<>();
    }

    public static CurrentOrderContainer getInstance() {
        if (instance == null) {
            instance = new CurrentOrderContainer();
        }
        return instance;
    }

    public Order getCurrentOrder(Order order) {
        return this.orders.get(order);
    }

}
