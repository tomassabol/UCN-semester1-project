package view;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import controller.CustomerController;
import controller.ProductController;
import model.Customer;
import model.CustomerContainer;
import model.IFItemLine;
import model.Product;
import model.SellingPrice;
import model.UntrackableItemLine;

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
    super.addMenuOption("2", new GenericMenuOption("Generate test data",
    		() -> generateTestData()));
    super.addMenuOption("3", new GenericMenuOption("Nested menu",
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
	System.out.println("Customer: " + customer.getFirstName() + " " + customer.getLastName());
	System.out.println("[Shopping cart]");
	for(IFItemLine itemLine: customer.getShoppingCart().getItemLines()) {
		System.out.println(itemLine.getQuantity() + "x: "
				+ itemLine.getProduct().getName() 
				+ " for " + itemLine.calculatePrice() + " DKK");
	}
	System.out.println("Total: " + customer.getShoppingCart().calculateTotalPrice());
	// Create the order
	if (terminal.confirmInput()) {
		System.out.println("Creating an order...");
		super.show("Successfully created a new order");
	} else {
		super.show("Order creation was cancelled!");
	}
    
  }
  
  /*
   * Generate test data
   * TODO: MOVE THE LOGIC TO CONTROLLER
   */
  public void generateTestData() {
	  CustomerController ctrl = new CustomerController();
	  Customer customer1 = ctrl.createCustomer("Attila", "Bako", "Rundvej 4", "+45 734123", LocalDateTime.now());
	  System.out.println("Generated customer data!");
	  
	  ProductController ctrl2 = new ProductController();
	  Product product1 = ctrl2.createProduct("Shovel", "A big, steel shovel", 0, 100);
	  SellingPrice sellingPrice = new SellingPrice(BigDecimal.valueOf(95), LocalDateTime.now());
	  product1.addSellingPrice(sellingPrice);
	  
	  // TODO: Implement itemController
	  // TODO: Implement itemLineController
	  // Note: both of them must support trackable & non-trackable items
	  UntrackableItemLine itemLine1 = new UntrackableItemLine(product1, 4);
	  UntrackableItemLine itemLine2 = new UntrackableItemLine(product1, 1);
	  
	  // also need to use a controller here
	  customer1.getShoppingCart().add(itemLine1);
	  customer1.getShoppingCart().add(itemLine2);
	  super.show("Generated test data!");
  }
  
  /**
   * Show nested menu
   */
  private void showNestedMenu() {
    MenuNested.getInstance().show();
  }
  
}
