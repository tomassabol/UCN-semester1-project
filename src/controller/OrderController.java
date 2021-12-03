package controller;

import java.util.ArrayList;
import java.util.List;

import model.Customer;
import model.Order;
import model.OrderContainer;
import model.PrimaryKey;
import model.ShoppingCart;
import model.SpecificItemLine;

public class OrderController {

	public OrderController() {
	}

	
	/**
	 * @return all orders
	 */
	public List<Order> getOrders() {
		return OrderContainer.getInstance().getOrders();
	}
	
//    /*
//     * @param Customer
//     * @return true if successful, else false
//     */
//    public boolean createOrder(Customer customer, ArrayList<SpecificItemLine> itemLines) {
//    	Order order = new Order(PrimaryKey.getNextOrderID(), customer, itemLines);
//    	return OrderContainer.getInstance().addOrder(order);
//    }
//    
//    /*
//     * Overload: accept ShoppingCart instead of Array of IFItemLine
//     */
//    public boolean createOrder(Customer customer, ShoppingCart shoppingCart) {
//    	return createOrder(customer, shoppingCart.getItemLines());
//    }
    	
	
	
}
