package view;

import model.Customer;
import model.CustomerContainer;

public class MenuMain extends GenericMenuInterface {
  private static MenuMain instance;

  /**
   * Constructor for MainMenuUI.
   */
  private MenuMain() {
    super();

    super.setTitle("Main Menu");
    super.addMenuOption("1", new GenericMenuOption("Create an order",
    		() -> createOrder()));
    super.addMenuOption("2", new GenericMenuOption("Nested menu",
    		() -> showNestedMenu()));
    super.addMenuOption("0", new GenericMenuOption("Quit the program",
    		() -> Terminal.quit()));
  }

  /**
   * @return the instance of MainMenuUI
   */
  public static MenuMain getInstance() {
    if (instance == null) {
      instance = new MenuMain();
    }
    return instance;
  }

  /**
   * Create Order
   */
  private void createOrder() {
	Terminal terminal = Terminal.getInstance();
	
	terminal.clearScreen();
	System.out.println("[Customers]");
	terminal.printAllCustomers();
	Customer customer = terminal.getCustomer();
	System.out.println("You chose customer with ID: " + customer.ID);
	// Create the order
	if (terminal.confirmInput()) {
		System.out.println("Creating an order...");
		super.show("Successfully created a new order");
	} else {
		super.show("Order creation was cancelled!");
	}
    
  }
  
  /**
   * Show nested menu
   */
  private void showNestedMenu() {
    MenuNested.getInstance().show();
  }
  
}
