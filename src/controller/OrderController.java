package controller;

import java.util.ArrayList;
import java.util.List;

import exception.OutOfStockException;
import models.*;
import models.containers.OrderContainer;
import models.containers.StockContainer;

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
	
	/**
	 * Takes a quote, gets specific items from stock and creates an order
	 * 
	 * @param quote the quote
	 * 
	 * @return Order
	 * @throws OutOfStockException
	 */
	public Order payForQuote(Quote quote) throws OutOfStockException {
		// Check if each item line is in stock
		for (models.QuoteItemLine itemLine: quote.getItemLines()) {
			int buyableQuantity = new StockController().getBuyableQuantityInStock(itemLine.getPRODUCT());
			if (itemLine.getQuantity() > buyableQuantity) {
				throw new OutOfStockException("Some of the items in this item line are out of stock!");
			}
		}
		
		IFCustomer customer = quote.getCustomer();
		IFEmployee employee = quote.getEmployee();
		ArrayList<OrderLine> orderLines = new ArrayList<>();
		for (models.QuoteItemLine itemLine: quote.getItemLines()) {
			
			orderLines.add(StockContainer.getInstance().stockToOrderlineBuyable(itemLine.getPRODUCT(), itemLine.getQuantity()));
		}
		Order order = new Order(PrimaryKey.getID(PrimaryKey.Keys.ORDER), 
				customer, employee, orderLines);
		new QuoteController().removeQuote(quote);
		OrderContainer.getInstance().addOrder(order);
		return order;
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
