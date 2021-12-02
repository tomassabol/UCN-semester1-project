package view;

import controller.OrderController;
import model.Customer;
import model.IFItemLine;
import model.Order;

public class MenuOrders extends GenericMenuInterface {
  private static MenuOrders instance;
  
  private OrderController orderCtrl;

  /**
   * Constructor for MainMenuUI.
   */
  private MenuOrders() {
    super();

    super.setTitle("Orders");
    super.addMenuOption("1", new GenericMenuOption("Create an order",
    		() -> createOrder()));
    super.addMenuOption("2", new GenericMenuOption("Show all orders",
    		() -> showAllOrders()));
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
 * Create Order
 */
	private void createOrder() {
		Terminal terminal = Terminal.getInstance();
		terminal.clearScreen();
		
		System.out.println("[Customers]");
		terminal.printAllCustomers();
		Customer customer = terminal.getCustomer();
		System.out.println("You chose customer with ID: " + customer.ID);
		System.out.println("Customer: " + customer.getFirstName() + " " + customer.getLastName());
		System.out.println("[Shopping cart]");
		for(IFItemLine itemLine: customer.getShoppingCart().getItemLines()) {
			System.out.println(itemLine.getQuantity() + "x: "
					+ itemLine.getProduct().getName() 
					+ " for " + itemLine.calculatePrice() + " DKK");
		}
		System.out.println("Total: " + customer.getShoppingCart().calculateTotalPrice() + " DKK");
		// Create the order
		if (terminal.confirmInput("Create order?")) {
			orderCtrl.createOrder(customer, customer.getShoppingCart());
			super.show("Successfully created a new order");
		} else {
			super.show("Order creation was cancelled!");
		}
	  
	}

	public void showAllOrders() {
		Terminal terminal = Terminal.getInstance();
		terminal.clearScreen();
		
		System.out.println("[All Orders]");
		for (Order order: orderCtrl.getOrders()) {
			System.out.println("#" + order.ID + ": " + order.getStatus());
		}
		System.out.println();
		
		terminal.getStringInput("Press [Enter] to go back");
		super.show();
	}
}
