package model;

import java.util.ArrayList;
import java.util.List;

public class OrderContainer {
	
	private static OrderContainer instance;
	private ArrayList<Order> orders;

	/*
	 * Private constructor: singleton
	 */
	private OrderContainer() {
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
    
    public List<Order> getOrders() {
    	return this.orders;
    }
    
    public Order findOrderByID(int orderID) {
    	for (Order order: orders) {
    		if (order.ID == orderID) {
    			return order;
    		}
    	}
    	return null;
    }

}
