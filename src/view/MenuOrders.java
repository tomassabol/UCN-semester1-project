package view;

import controller.OrderController;
import model.Customer;
import model.Employee;
import model.IFEmployee;
import model.Order;
import model.UnspecificItemLine;

public class MenuOrders extends GenericMenuInterface {
  private static MenuOrders instance;
  
  private OrderController orderCtrl;

  /**
   * Constructor for MainMenuUI.
   */
  private MenuOrders() {
    super();

    super.setTitle("Orders");
    super.addMenuOption("1", new GenericMenuOption("Create a quote",
    		() -> createQuote()));
    super.addMenuOption("2", new GenericMenuOption("Show all quotes",
    		() -> showAllQuotes()));
    super.addMenuOption("0", new GenericMenuOption("Go back to main menu",
    		() -> MenuMain.getInstance().show()));
    
    orderCtrl = new OrderController();
  }

  /**
   * @return the instance of MainMenuUI
   */
  public static MenuOrders getInstance() {
    if (instance == null) {
      instance = new MenuOrders();
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
		System.out.println("You chose customer with ID: " + customer.ID);
		System.out.println("Customer: " + customer.getFirstName() + " " + customer.getLastName());
		System.out.println("[Shopping cart]");
		for(UnspecificItemLine itemLine: customer.getShoppingCart().getItemLines()) {
			System.out.println(itemLine.getQuantity() + "x: "
					+ itemLine.PRODUCT.getName() 
					+ " for " + itemLine.calculateCurrentPrice() + " DKK");
		}
		System.out.println("Total: " + customer.getShoppingCart().calculateTotalPrice() + " DKK");
		// Create the order
		IFEmployee employee = new Employee(0, 0, "", "", "", "", null);
		if (terminal.confirmInput("Checkout?")) {		
			orderCtrl.createQuote(customer, employee, customer.getShoppingCart());
			super.show("Successfully created a new quote");
		} else {
			super.show("Quote creation was cancelled!");
		}
	  
	}

	public void showAllQuotes() {
		Terminal terminal = Terminal.getInstance();
		terminal.clearScreen();
		
		System.out.println("[All Quotes]");
		for (Order order: orderCtrl.getOrders()) {
			System.out.println("#" + order.ID + ": " + order.getStatus());
		}
		System.out.println();
		
		terminal.getStringInput("Press [Enter] to go back");
		super.show();
	}
}
