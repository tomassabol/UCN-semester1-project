package controller;

import java.util.ArrayList;
import java.util.List;

import model.IFCustomer;
import model.IFEmployee;
import model.PrimaryKey;
import model.Quote;
import model.QuoteContainer;
import model.QuoteItemLine;
import model.ShoppingCart;
import model.ShoppingItemLine;

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
	 * Gets the quotes.
	 *
	 * @return all quotes
	 */
	public List<Quote> getQuotes() {
		return QuoteContainer.getInstance().getQuotes();
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
     * @return true, if successful
     */
    public boolean createQuote(IFCustomer customer, IFEmployee employee, ShoppingCart shoppingCart) {
    	// get itemLines
    	ArrayList<ShoppingItemLine> itemLines = shoppingCart.getItemLines();
    	// clear shopping cart
    	shoppingCart.clear();
    	return createQuote(customer, employee, itemLines);
    }
	
}
