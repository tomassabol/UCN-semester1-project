package view;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import controller.CustomerController;
import controller.OrderController;
import controller.ProductController;
import model.Customer;
import model.CustomerContainer;
import model.Product;
import model.SellingPrice;
import model.UnspecificItemLine;

public class MenuMain extends GenericMenuInterface {
  private static MenuMain instance;
  
  private OrderController orderCtrl;

  /**
   * Constructor for MainMenuUI.
   */
  private MenuMain() {
    super();

    super.setTitle("Main Menu");
    super.addMenuOption("1", new GenericMenuOption("Generate test data",
    		() -> generateTestData()));
    super.addMenuOption("2", new GenericMenuOption("Orders",
    		() -> showOrdersMenu()));
    super.addMenuOption("3", new GenericMenuOption("Products",
        () -> showProductsMenu()));
    super.addMenuOption("0", new GenericMenuOption("Quit the program",
    		() -> Terminal.quit()));
    
    orderCtrl = new OrderController();
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
	  UnspecificItemLine itemLine1 = new UnspecificItemLine(product1, 4);
	  UnspecificItemLine itemLine2 = new UnspecificItemLine(product1, 1);
	  
	  // also need to use a controller here
	  customer1.getShoppingCart().add(itemLine1);
	  customer1.getShoppingCart().add(itemLine2);
	  
//	  // Add orders to customer1
//	  orderCtrl.createOrder(customer1, customer1.getShoppingCart());
//	  super.show("Generated test data!");
  }
  
  /**
   * Show nested menu
   */
  private void showOrdersMenu() {
    MenuOrders.getInstance().show();
  }

  private void showProductsMenu() {
    MenuProduct.getInstace().show();
  }
  
}
