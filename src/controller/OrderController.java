package controller;

import java.util.ArrayList;
import java.util.List;

import model.*;

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
	
	public boolean payForQuote(Quote quote) {
		IFCustomer customer = quote.getCustomer();
		IFEmployee employee = quote.getEmployee();
		ArrayList<OrderLine> orderLines = new ArrayList<>();
		for (model.QuoteItemLine itemLine: quote.getItemLines()) {
			orderLines.add(Stock.getInstance().stockToOrderlineBuyable(itemLine.getPRODUCT(), itemLine.getQuantity()));
		}
		Order order = new Order(PrimaryKey.getNextOrderID(), 
				customer, employee, orderLines);
		new QuoteController().removeQuote(quote);
		return true;
		// TODO: return false if not enough quantity (e.g. somebody else bought it), and put back in shelves
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
