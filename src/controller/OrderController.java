package controller;

import java.util.ArrayList;
import java.util.List;

import model.*;
import model.containers.OrderContainer;
import model.containers.Stock;

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
	
	/**
	 * Find order, but restrict it to a specific customer
	 *
	 * @return the order
	 */
	public Order findOrderByIdForCustomer(int orderID, Customer customer) {
		Order order = OrderContainer.getInstance().findOrderByID(orderID);
		// if belong sto customer, return
		if (order != null && order.getCustomer() == customer) {
			return order;
		}
		return null;
	}
	
	public Order payForQuote(Quote quote) {
		// TODO: Don't allow paying if not in stock
		IFCustomer customer = quote.getCustomer();
		IFEmployee employee = quote.getEmployee();
		ArrayList<OrderLine> orderLines = new ArrayList<>();
		for (model.QuoteItemLine itemLine: quote.getItemLines()) {
			
			orderLines.add(Stock.getInstance().stockToOrderlineBuyable(itemLine.getPRODUCT(), itemLine.getQuantity()));
		}
		Order order = new Order(PrimaryKey.getID(PrimaryKey.Keys.ORDER), 
				customer, employee, orderLines);
		new QuoteController().removeQuote(quote);
		OrderContainer.getInstance().addOrder(order);
		return order;
		// TODO: return null if items < than requested.
	}

	/**
	 * 
	 * @param order to get orderlines for
	 * @return list of orderlines per order
	 */
	public List<OrderLine> getOrderLines(Order order) {
		return OrderContainer.getInstance().getOrderLines(order);
	}
	
}
