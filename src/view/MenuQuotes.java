package view;

import model.*;

public class MenuQuotes extends GenericMenuInterface {

  /**
   * Constructor for MainMenuUI.
   */
  public MenuQuotes(GenericMenuInterface previousInterface) {
    super(previousInterface);

    super.setTitle("Quotes");
    super.addMenuOption("1", new GenericMenuOption("Create a quote",
    		() -> createQuote()));
    super.addMenuOption("2", new GenericMenuOption("Show quotes",
    		() -> showQuotes()));
    super.addMenuOptionGoBack("0");

  }

/**
 * Create Quote
 */
	private void createQuote() {
		
		Terminal terminal = Terminal.getInstance();
		terminal.clearScreen();
		System.out.println("[Customers]");
		terminal.printAllCustomers();
		Customer customer = terminal.getCustomer();
		new MenuShoppingCart(this, customer).show();
	  
	}

	/**
	 * Prints all guotes and quote info
	 */
	public void showQuotes() {
		Terminal terminal = Terminal.getInstance();
		terminal.clearScreen();
		System.out.println("[Customers]");
		terminal.printAllCustomers();
		Customer customer = Terminal.getInstance().getCustomer();
		terminal.clearScreen();
		terminal.printQuotes(customer);
		System.out.println();
		
		terminal.getAnyKeyInput("Press [Enter] to go back");
		super.show();
	}
}
