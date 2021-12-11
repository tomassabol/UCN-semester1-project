package controller;

import java.util.List;

import model.Customer;
import model.Order;
import model.OrderContainer;

/**
 * The Class OrderController.
 */
public class OrderController {

	/**
	 * Instantiates a new order controller.
	 */
	public OrderController() {}

	
	/**
	 * Gets the orders.
	 *
	 * @return all orders
	 */
	public List<Order> getOrders() {
		return OrderContainer.getInstance().getOrders();
	}

	public List<Order> getOrders(Customer customer) {
		return OrderContainer.getInstance().getOrders(customer);
	}
	
}
