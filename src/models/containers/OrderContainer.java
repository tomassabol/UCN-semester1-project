package models.containers;

import java.util.ArrayList;
import java.util.List;

import models.Customer;
import models.Order;
import models.OrderLine;

public class OrderContainer {
	
	private static OrderContainer instance;
	private ArrayList<Order> orders;

	/*
	 * Private constructor: singleton
	 */
	private OrderContainer() {
		this.orders = new ArrayList<>();
	}
	
    /**
     * @return instance of OrderContainer
     */
    public static OrderContainer getInstance(){
		if (instance == null) {
            instance = new OrderContainer();
        }
        return instance;
    }
    
    
    /*
     * @param order The order to add to the container
     * @return true if successful, else false
     */
    public boolean addOrder(Order order) {
    	return orders.add(order);
    }
    
    /*
     * @param order The order to remove from the container
     * @return true if successful, else false
     */
    public boolean removeOrder(Order order) {
    	return orders.remove(order);
    }
    
    /**
     * @return All orders
     */
    public List<Order> getOrders() {
    	return this.orders;
    }

    /**
     * returns list of orderlines
     * @return list of orderlines
     */
    public List<OrderLine> getOrderLines(Order order) {
        return order.getItemLines();
    }
    
    /**
     * Find order by ID.
     *
     * @param orderID the order ID
     * @return the order
     */
    public Order findOrderByID(int orderID) {
    	for (Order order: orders) {
    		if (order.ID == orderID) {
    			return order;
    		}
    	}
    	return null;
    }
    
    /**
     * Gets all orders for a specific customer
     *
     * @param customer the customer
     * @return the orders for the customer
     */
    public List<Order> getOrders(Customer customer) {
    	ArrayList<Order> customerOrders = new ArrayList<>();
    	for (Order order: this.orders) {
    		if (order.getCustomer().equals(customer)) {
    			customerOrders.add(order);
    		}
    	}
    	return customerOrders;
    }

}
