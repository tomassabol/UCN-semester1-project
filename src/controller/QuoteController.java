package controller;

import java.util.ArrayList;
import java.util.List;

import model.*;

/**
 * The Class OrderController.
 */
public class QuoteController {

	/**
	 * Constructor for QuoteController
	 */
	public QuoteController() {
	}

	

	/**
	 * Gets the quotes (for everyone!)
	 *
	 * @return all quotes
	 */
	public List<Quote> getQuotes() {
		return QuoteContainer.getInstance().getQuotes();
	}
	
	/**
	 * Gets the quotes for a specific customer
	 *
	 * @return the quotes
	 */
	public List<Quote> getQuotes(Customer customer) {
		return QuoteContainer.getInstance().getQuotes(customer);
	}
	

    /**
     * Creates a quote and adds it to container.
     * BEWARE: This method does not clear the shopping cart!
     *
     * @param customer the customer
     * @param employee the employee
     * @param itemLines the shopping item lines
     * @return true, if successful
     */
    public boolean createQuote(IFCustomer customer, IFEmployee employee, ArrayList<ShoppingItemLine> itemLines) {
    	// Convert shopping item lines to quote item lines (fixed price & bulk discount)
    	ArrayList<QuoteItemLine> quoteItemLines = new ArrayList<>();
    	for (ShoppingItemLine shopItemLine: itemLines) {
    		quoteItemLines.add(new QuoteItemLine(shopItemLine.PRODUCT, shopItemLine.getQuantity()));
    	}
    	// Create the quote and add to container
    	Quote quote = new Quote(PrimaryKey.getNextOrderID(), customer, employee, quoteItemLines);
    	return QuoteContainer.getInstance().addQuote(quote);
    }
    

    /**
     * Creates a quote from a shopping cart & adds it to container
     *  NOTE: Clears the shopping cart
     *
     * @param customer the customer
     * @param employee the employee
     * @param itemLines the shopping item lines
     * @return Quote the newly created quote
     */
    public Quote createQuote(IFCustomer customer, IFEmployee employee) {
    	ShoppingCart shoppingCart = customer.getShoppingCart();
    	// get itemLines
    	ArrayList<ShoppingItemLine> shoppingItemLines = shoppingCart.getItemLines();
    	// convert shopping item lines to quote item lines
    	ArrayList<QuoteItemLine> quoteItemLines = new ArrayList<>();
    	for (ShoppingItemLine shoppingItemLine: shoppingItemLines) {
    		quoteItemLines.add(
    				new QuoteItemLine(
    						shoppingItemLine.PRODUCT,
    						shoppingItemLine.getQuantity()));
    	}
    	// clear shopping cart 
    	shoppingCart.clear();
    	// create quote
    	Quote quote = new Quote(PrimaryKey.getNextQuoteID(), customer, employee, quoteItemLines);
    	//add quote to container
    	QuoteContainer.getInstance().addQuote(quote);
    	return quote;
    }

	public boolean removeQuote(Quote quote) {
		return QuoteContainer.getInstance().removeQuote(quote);
	}

	public Quote findQuoteById(int id) {
		return QuoteContainer.getInstance().findQuoteByID(id);
	}
	
	/**
	 * Find quote, but restrict it to a specific customer
	 *
	 * @return the quote
	 */
	public Quote findOrderByIdForCustomer(int quoteID, Customer customer) {
		Quote quote = QuoteContainer.getInstance().findQuoteByID(quoteID);
		// if belongs to customer, return
		if (quote != null && quote.getCustomer() == customer) {
			return quote;
		}
		return null;
	}
	
}
