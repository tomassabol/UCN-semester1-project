package controller;

import java.util.ArrayList;
import java.util.List;

import model.IFCustomer;
import model.IFEmployee;
import model.Order;
import model.OrderContainer;
import model.PrimaryKey;
import model.Quote;
import model.QuoteContainer;
import model.QuoteItemLine;
import model.ShoppingCart;
import model.ShoppingItemLine;

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
    
	
}
