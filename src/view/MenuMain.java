package view;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import controller.CustomerController;
import controller.EmployeeController;
import controller.QuoteController;
import controller.ProductController;
import model.BulkDiscount;
import model.Contractor;
import model.ContractorContainer;
import model.Customer;
import model.CustomerType;
import model.DiscountContainer;
import model.IFEmployee;
import model.PrimaryKey;
import model.Product;
import model.SellingPrice;
import model.ShoppingItemLine;

public class MenuMain extends GenericMenuInterface {
  private static MenuMain instance;
  
  private QuoteController orderCtrl;
  private EmployeeController employeeCtrl;

  /**
   * Constructor for MainMenuUI.
   */
  private MenuMain() {
    super();

    super.setTitle("Main Menu");
    super.addMenuOption("1", new GenericMenuOption("Generate test data",
    		    () -> generateTestData()));
    super.addMenuOption("2", new GenericMenuOption("Quotes",
    		    () -> showOrdersMenu()));
    super.addMenuOption("3", new GenericMenuOption("Products",
            () -> showProductsMenu()));
    super.addMenuOption("4", new GenericMenuOption("Employees",
            () -> showEmployeesMenu()));
    super.addMenuOption("5", new GenericMenuOption("Contractors",
            () -> showContractorMenu()));
    super.addMenuOption("6", new GenericMenuOption("Supplies",
            () -> showSupplyMenu()));
    super.addMenuOption("7", new GenericMenuOption("Stock",
            () -> showStockMenu()));
    super.addMenuOption("8", new GenericMenuOption("Customers", () -> showCustomerMenu()));
    super.addMenuOption("0", new GenericMenuOption("Quit the program",
    		    () -> Terminal.quit()));
    
    orderCtrl = new QuoteController();
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
	  // TODO: need a bulk discount controller
	  BulkDiscount bulkDiscount = new BulkDiscount(2, 20);
	  // Create customer type
	  // TODO: NEED A CUSTOMER TYPE CONTROLLER
	  CustomerType customerType = new CustomerType(PrimaryKey.getNextCustomerTypeID(), "Normal", 5);
	  // Create customers
	  CustomerController ctrl = new CustomerController();
	  Customer customer1 = ctrl.createCustomer("Attila", "Bako", "Rundvej 4", "+45 734123", customerType, LocalDate.now());
	  System.out.println("Generated customer data!");
	  // Create products
	  ProductController ctrl2 = new ProductController();
	  Product product1 = ctrl2.createProduct("Shovel", "A big, steel shovel", 0, 100);
	  DiscountContainer.getInstance().addBulkDiscount(product1, bulkDiscount);
	  // TODO: need a controller for price creation
	  SellingPrice sellingPrice = new SellingPrice(BigDecimal.valueOf(95), LocalDateTime.now());
	  product1.addSellingPrice(sellingPrice);
	  
	  // Create items
	  // TODO: Implement itemController, itemLineController...
	  ShoppingItemLine itemLine1 = new ShoppingItemLine(product1, 4);
	  ShoppingItemLine itemLine2 = new ShoppingItemLine(product1, 1);
    Contractor contractor = new Contractor(0, "TestSupplyCompany");
    ContractorContainer.getInstance().addContractor(contractor);
	  
	  // TODO: also need to use a controller here
	  System.out.println("SC: " + customer1.getShoppingCart().getItemLines());
	  customer1.getShoppingCart().add(itemLine1);
	  customer1.getShoppingCart().add(itemLine2);
	  System.out.println("SC: " + customer1.getShoppingCart().getItemLines());
	  
	  // Create employees
	  IFEmployee employee = employeeCtrl.createEmployee("080600-1111", "Daniels", "Kanepe", "Rundvej 8", "+45 11114567", LocalDate.now());
	  
	  // Add orders to customer1
	  orderCtrl.createQuote(customer1, employee, customer1.getShoppingCart());
	  
	  customer1.getShoppingCart().add(itemLine1);
	  
	  
	  super.show("Generated test data!");
  }
  
  /**
   * Show orders menu.
   */
  private void showOrdersMenu() {
    MenuQuotes.getInstance().show();
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
  /**
   * Show contractor menu.
   */
  private void showContractorMenu() {
	MenuContractor.getInstace().show();
  }
  /**
   * Show supply menu.
   */
  private void showSupplyMenu(){
    MenuSupply.getInstance().show();
  }
  /**
   * Show stock menu.
   */
  private void showStockMenu() {
    MenuStock.getInstance().show();
  }
  /**
   * Show customer menu.
   */
  private void showCustomerMenu(){
    MenuCustomer.getInstance().show();
  }

}
