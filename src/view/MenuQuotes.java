package view;

import controller.OrderController;
import controller.StockController;
import model.*;

public class MenuQuotes extends GenericMenuInterface {
	
	StockController stockCtrl;
	OrderController orderCtrl;

  /**
   * Constructor for MainMenuUI.
   */
  public MenuQuotes(GenericMenuInterface previousInterface) {
    super(previousInterface);

    super.setTitle("Quotes");
    super.addMenuOption("1", new GenericMenuOption("Create a quote",
    		() -> createQuote()));
    super.addMenuOption("2", new GenericMenuOption("Pay for a quote (Quote -> Order)",
    		() -> PayForQuote()));
    super.addMenuOption("3", new GenericMenuOption("View customer orders",
    		() -> showOrders()));
    super.addMenuOptionGoBack("0");
    
    stockCtrl = new StockController();
    orderCtrl = new OrderController();

  }

/**
 * Create Quote
 */
	private void createQuote() {
		Terminal terminal = getTerminal();
		terminal.clearScreen();
		Customer customer = terminal.getCustomer();
		new MenuShoppingCart(this, customer).show();
	}

	/**
	 * Pay for a quote
	 */
	public void PayForQuote() {
		Terminal terminal = getTerminal();
		terminal.clearScreen();
		// identifies a customer & a quote
		Quote quote = terminal.getQuote();
		if (!stockCtrl.quoteIsInStock(quote)) {
			System.out.println("Error: Some the items in the quote are not in stock anymore!");
		} else {
			System.out.println("All of the items in the quote are in stock. ");
			if (terminal.confirmInput("Has the customer paid for the order?")) {
				 orderCtrl.payForQuote(quote);
			 }
		}

		
		System.out.println();
		
		terminal.getAnyKeyInput("Press [Enter] to go back");
		super.show();
	}
	
	/**
	 * Prints all orders for a specific customer
	 */
	public void showOrders() {
		Terminal terminal = getTerminal();
		terminal.clearScreen();
		Customer customer = terminal.getCustomer();
		terminal.clearScreen();
		terminal.printOrders(customer);
		System.out.println();
		
		terminal.getAnyKeyInput("Press [Enter] to go back");
		super.show();
	}

}
