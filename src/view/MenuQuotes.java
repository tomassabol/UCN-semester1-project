package view;

import controller.EmployeeController;
import controller.QuoteController;
import model.Authentication;
import model.Customer;
import model.IFEmployee;
import model.Quote;

public class MenuQuotes extends GenericMenuInterface {
  private static MenuQuotes instance;
  
  private QuoteController quoteCtrl;
  private EmployeeController employeeCtrl;

  /**
   * Constructor for MainMenuUI.
   */
  private MenuQuotes() {
    super();

    super.setTitle("Quotes");
    super.addMenuOption("1", new GenericMenuOption("Create a quote",
    		() -> createQuote()));
    super.addMenuOption("2", new GenericMenuOption("Show quotes",
    		() -> showQuotes()));
    super.addMenuOption("0", new GenericMenuOption("Go back to main menu",
    		() -> MenuMain.getInstance().show()));
    
    quoteCtrl = new QuoteController();
    employeeCtrl = new EmployeeController();
  }

  /**
   * @return the instance of MainMenuUI
   */
  public static MenuQuotes getInstance() {
    if (instance == null) {
      instance = new MenuQuotes();
    }
    return instance;
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
		MenuShoppingCart.getInstance(customer).show();
	  
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
