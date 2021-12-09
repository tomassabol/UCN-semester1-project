package model;

import java.util.ArrayList;
import java.util.List;

public class QuoteContainer {
	
	private static QuoteContainer instance;
	private ArrayList<Quote> quotes;

	/*
	 * Private constructor: singleton
	 */
	private QuoteContainer() {
		this.quotes = new ArrayList<>();
	}
	
    /**
     * @return instance of QuoteContainer
     */
    public static QuoteContainer getInstance(){
		if (instance == null) {
            instance = new QuoteContainer();
        }
        return instance;
    }
    
    
    /*
     * @param quote The quote to add to the container
     * @return true if successful, else false
     */
    public boolean addQuote(Quote quote) {
    	return quotes.add(quote);
    }
    
    /*
     * @param quote The quote to remove from the container
     * @return true if successful, else false
     */
    public boolean removeQuote(Quote quote) {
    	return quotes.remove(quote);
    }
    
    /**
     * @return All quotes, for everyone
     */
    public List<Quote> getQuotes() {
    	return this.quotes;
    }
    
    
    /**
     * Find quote by ID.
     *
     * @param quoteID the quote ID
     * @return the quote
     */
    public Quote findQuoteByID(int quoteID) {
    	for (Quote quote: quotes) {
    		if (quote.ID == quoteID) {
    			return quote;
    		}
    	}
    	return null;
    }
    
    /**
     * Gets all quotes for a specific customer
     *
     * @param customer the customer
     * @return the quotes for the customer
     */
    public List<Quote> getQuotes(Customer customer) {
    	ArrayList<Quote> customerQuotes = new ArrayList<>();
    	for (Quote quote: this.quotes) {
    		if (quote.getCustomer().equals(customer)) {
    			customerQuotes.add(quote);
    		}
    	}
    	return customerQuotes;
    }

}
