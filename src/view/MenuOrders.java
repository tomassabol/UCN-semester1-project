package view;

import controller.EmployeeController;
import controller.OrderController;
import model.Customer;
import model.IFEmployee;
import model.Quote;
import model.ShoppingItemLine;

public class MenuOrders extends GenericMenuInterface {
  private static MenuOrders instance;
  
  private OrderController orderCtrl;
  private EmployeeController employeeCtrl;

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
    employeeCtrl = new EmployeeController();
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
		
		// TODO: Employee by default is id 0. Implement auth?
		IFEmployee employee = employeeCtrl.getEmployeeByID(0);
		Terminal terminal = Terminal.getInstance();
		terminal.clearScreen();
		
		System.out.println("[Customers]");
		terminal.printAllCustomers();
		Customer customer = terminal.getCustomer();
		terminal.clearScreen();
		System.out.println("Customer: " + customer.getFirstName() + " " + customer.getLastName());
		System.out.println("[Shopping cart]");
		for(ShoppingItemLine itemLine: customer.getShoppingCart().getItemLines()) {
			System.out.println(itemLine.getQuantity() + "x: "
					+ itemLine.PRODUCT.getName() 
					+ "\t" + itemLine.getCurrentPriceWithoutBulkDiscount() + " DKK");
			if (itemLine.getBulkDiscount() != null) {
				System.out.println("Bulk discount (" + itemLine.getBulkDiscount().getDiscountPercentage() + "): " + 
			itemLine.getCurrentPriceWithBulkDiscount() + " DKK");
			}
		}
		System.out.println("Total: " + customer.getShoppingCart().calculateTotalPriceWithBulkDiscounts()+ " DKK");
		if (terminal.confirmInput("Create a quote?")) {		
			orderCtrl.createQuote(customer, employee, customer.getShoppingCart());
			super.show("Successfully created a new quote");
		} else {
			super.show("Quote creation was cancelled!");
		}
	  
	}

	/**
	 * Prints all guotes and quote info
	 */
	public void showAllQuotes() {
		Terminal terminal = Terminal.getInstance();
		terminal.clearScreen();
		
		System.out.println("[All Quotes]");
		for (Quote quote: orderCtrl.getQuotes()) {
			System.out.println("Quote: #" + quote.ID + " " + quote.CREATION_DATE);
		}
		System.out.println();
		
		terminal.emptyStringInput("Press [Enter] to go back");
		super.show();
	}
}
