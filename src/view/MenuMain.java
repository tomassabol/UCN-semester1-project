package view;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import controller.CustomerController;
import controller.EmployeeController;
import controller.OrderController;
import controller.ProductController;
import model.Customer;
import model.CustomerContainer;
import model.Employee;
import model.IFEmployee;
import model.Product;
import model.SellingPrice;
import model.UnspecificItemLine;

public class MenuMain extends GenericMenuInterface {
  private static MenuMain instance;
  
  private OrderController orderCtrl;
  private EmployeeController employeeCtrl;

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
    super.addMenuOption("4", new GenericMenuOption("Employees",
            () -> showEmployeesMenu()));
    super.addMenuOption("0", new GenericMenuOption("Quit the program",
    		() -> Terminal.quit()));
    
    orderCtrl = new OrderController();
    employeeCtrl = new EmployeeController();
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
	  // Create customers
	  CustomerController ctrl = new CustomerController();
	  Customer customer1 = ctrl.createCustomer("Attila", "Bako", "Rundvej 4", "+45 734123", LocalDateTime.now());
	  System.out.println("Generated customer data!");
	  // Create products
	  ProductController ctrl2 = new ProductController();
	  Product product1 = ctrl2.createProduct("Shovel", "A big, steel shovel", 0, 100);
	  SellingPrice sellingPrice = new SellingPrice(BigDecimal.valueOf(95), LocalDateTime.now());
	  product1.addSellingPrice(sellingPrice);
	  
	  // Create items
	  // TODO: Implement itemController, itemLineController...
	  UnspecificItemLine itemLine1 = new UnspecificItemLine(product1, 4);
	  UnspecificItemLine itemLine2 = new UnspecificItemLine(product1, 1);
	  
	  // TODO: also need to use a controller here
	  customer1.getShoppingCart().add(itemLine1);
	  customer1.getShoppingCart().add(itemLine2);
	  
	  // Create employees
	  IFEmployee employee = employeeCtrl.createEmployee("080600-1111", "Daniels", "Kanepe", "Rundvej 8", "+45 11114567", LocalDateTime.now());
	  
	  // Add orders to customer1
	  
	  orderCtrl.createQuote(customer1, employee, customer1.getShoppingCart());
	  super.show("Generated test data!");
  }
  
  /**
   * Show orders menu.
   */
  private void showOrdersMenu() {
    MenuOrders.getInstance().show();
  }

  /**
   * Show products menu.
   */
  private void showProductsMenu() {
    MenuProduct.getInstace().show();
  }
  
  /**
   * Show employees menu.
   */
  private void showEmployeesMenu() {
    MenuEmployee.getInstance().show();
  }
  
}
