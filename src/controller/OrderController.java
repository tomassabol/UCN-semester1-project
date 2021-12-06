package controller;

import java.util.ArrayList;
import java.util.List;

import model.Customer;
import model.IFCustomer;
import model.IFEmployee;
import model.Order;
import model.OrderContainer;
import model.PrimaryKey;
import model.Quote;
import model.QuoteContainer;
import model.ShoppingCart;
import model.OrderLine;
import model.UnspecificItemLine;

/**
 * The Class OrderController.
 */
public class OrderController {

	/**
	 * Instantiates a new order controller.
	 */
	public OrderController() {
	}

	
	/**
	 * Gets the orders.
	 *
	 * @return all orders
	 */
	public List<Order> getOrders() {
		return OrderContainer.getInstance().getOrders();
	}
	
	/**
	 * Gets the quotes.
	 *
	 * @return all quotes
	 */
	public List<Quote> getQuotes() {
		return QuoteContainer.getInstance().getQuotes();
	}
	

    /**
     * Creates a quote and adds it to container.
     *
     * @param customer the customer
     * @param employee the employee
     * @param itemLines the unspecific item lines
     * @return true, if successful
     */
    public boolean createQuote(IFCustomer customer, IFEmployee employee, ArrayList<UnspecificItemLine> itemLines) {
    	Quote quote = new Quote(PrimaryKey.getNextOrderID(), customer, employee, itemLines);
    	return QuoteContainer.getInstance().addQuote(quote);
    }
    

    /**
     * Creates a quote from a shopping cart,
     *  adds it to container,
     *  clears the shopping cart
     *
     * @param customer the customer
     * @param employee the employee
     * @param itemLines the unspecific item lines
     * @return true, if successful
     */
    public boolean createQuote(IFCustomer customer, IFEmployee employee, ShoppingCart shoppingCart) {
    	// get itemLines
    	ArrayList<UnspecificItemLine> itemLines = shoppingCart.getItemLines();
    	// clear shopping cart
    	shoppingCart.clear();
    	return createQuote(customer, employee, itemLines);
    }
    	
	
	
}
