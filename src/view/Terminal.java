package view;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import controller.*;
import model.*;

/**
 * The Class Terminal.
 */
public class Terminal {
	
	/** The go back cmd. */
	private final String GO_BACK_CMD = "-back";
  
  /** The scanner. */
  private Scanner scanner;
  
  /** The customer ctrl. */
  CustomerController customerCtrl;
  
  /** The product ctrl. */
  ProductController productCtrl;
  
  /** The contractor ctrl. */
  ContractorController contractorCtrl;
  
  /** The stock ctrl. */
  StockController stockCtrl;
  
  /** The supply ctrl. */
  SupplyController supplyCtrl;
  
  /** The quote ctrl. */
  QuoteController quoteCtrl;
  
  /** The order ctrl. */
  OrderController orderCtrl;
  
  /** The employee ctrl. */
  EmployeeController employeeCtrl;
  
  /** The current interface. */
  GenericMenuInterface currentInterface;
  

  /** The Constant DATE_FORMAT. */
  private static final String DATE_FORMAT = "dd/MM/yyyy";

  /**
   * Constructor for the Parser class.
   *
   * @param currentInterface the current interface
   */
  public Terminal(GenericMenuInterface currentInterface) {
	this.currentInterface = currentInterface;
    scanner = new Scanner(System.in);

    customerCtrl = new CustomerController();
    productCtrl = new ProductController();
    contractorCtrl = new ContractorController();
    stockCtrl = new StockController();
    supplyCtrl = new SupplyController();
    quoteCtrl = new QuoteController();
    orderCtrl = new OrderController();
    employeeCtrl = new EmployeeController();
  }
  
  /**
   * Prompt the user to identify a customer by ID.
   *
   * @param prompt the prompt
   * @return the customer
   * Note: Runs until valid ID is entered
   */
  public Customer getCustomer(String prompt) {
	  this.printCustomers(customerCtrl.getCustomers());
	  System.out.println();
	  Customer customer = null;
	  do {
		  int id = this.getIntegerInput(prompt);
		  customer = customerCtrl.findCustomerByID(id);
	  } while (customer == null);
	  
	  return customer;
  }
  public Customer getCustomer() {
	  return this.getCustomer("Choose a customer");
  }
  /**
   * Prints customers
   */
  public void printCustomers(List<Customer> customers) {
	  System.out.println("*** Customers ***");
	  System.out.println();
	  for (Customer customer: customers) {
		  String printLine = "(%d) %s %s";
		  System.out.println(String.format(printLine, 
				  customer.ID,
				  customer.getFirstName(),
				  customer.getLastName()));
          System.out.println("Address: " + String.format("%s", customer.getAddress()));
          System.out.println("Phone Number: " + String.format("%s", customer.getMobile()));
          System.out.println("Type: " + String.format("%s", customer.getCustomerType().getName()));
          System.out.println("Birth date: " + String.format("%s", customer.getBirthDate()));
          System.out.println();
	  }
  }
  

  /**
   * Prints all products and prompt the user to identify a product by ID.
   *
   * @param prompt the prompt
   * @return the product
   */
  public Product getProduct(String prompt) {
	  printProductsInDetail(productCtrl.getProducts());
	  Product product = null;
	  do {
		  int id = this.getIntegerInput(prompt);
		  product = productCtrl.findProductByID(id);
	  } while (product == null);
	  
	  return product;
  }
  
  /**
   * Prints all products and prompt the user to identify a product by ID.
   *
   * @param prompt the prompt
   * @return the product
   */
  public Product getBuyableProduct(String prompt) {
	  printProductsInDetail(productCtrl.getBuyableProducts());
	  Product product = null;
	  do {
		  int id = this.getIntegerInput(prompt);
		  product = productCtrl.findProductByID(id);
	  } while (product == null);
	  
	  return product;
  }
  
  /**
   * Prints the products in detail.
   *
   * @param products the products
   */
  public void printProductsInDetail(List<Product> products){
	  	System.out.println("*** Products ***");
	    for (Product product : products){
	      System.out.println();
			  System.out.println("[" + product.getName() + "]");
			  System.out.println("ID: " + product.ID);
			  System.out.println("Description: " + product.getDescription());
			  System.out.println("Loanable items in stock: " + stockCtrl.getLoanableQuantityInStock(product));
	          System.out.println("Loaning price: " + String.format("%.2f",product.getLatestLoaningPrice()));
			  System.out.println("Purchaseable items in stock: " + stockCtrl.getBuyableQuantityInStock(product));
	          System.out.println("Selling price: " + String.format("%.2f",product.getLatestSellingPrice()));
	          System.out.println("Date added: " + String.format("%s)",product.getDateAdded()));
	          System.out.println("Max Stock: " + String.format("%d",product.getMaxStock()));
	          System.out.println("Min Stock: " + String.format("%d",product.getMinStock()));
			  System.out.println();
	    }
	  }
  /**
   * Prints the buyable products.
   */
  public void printPurchaseableProducts(List<Product> products) {
	  for (Product product: products) {
      System.out.println();
		  System.out.println(String.format("(%s) %s",
				  product.ID,
				  product.getName()));
		  System.out.println(String.format("In stock: %d", 
				  Stock.getInstance().getBuyableQuantityInStock(product)));
		  System.out.println(String.format("Price: %.2f DKK",
				  product.getLatestSellingPrice()));
		  System.out.println("Description: " + product.getDescription());
		  System.out.println();
	  }
  }
  
  /**
   * Prints all employees and prompts the user to identify an employee by ID.
   *
   * @param prompt the prompt
   * @return the employee
   */
  public IFEmployee getEmployee(String prompt) {
	  printEmployees(employeeCtrl.getEmployees());
	  IFEmployee employee = null;
	  do {
		  int id = this.getIntegerInput(prompt);
		  employee = employeeCtrl.getEmployeeByID(id);
	  } while (employee == null);
	  
	  return employee;
  }
  public IFEmployee getEmployee() {
	  return getEmployee("Choose employee");
  }
  /**
   * Prints all employees
   */
  public void printEmployees(List<IFEmployee> employees) {
	  System.out.println("*** Employees ***");
	  System.out.println();
	  for (IFEmployee employee: employees) {
		  String printLine = "(%d) %s %s %s";
		  System.out.println(String.format(printLine, 
				  employee.getID(),
				  employee.getFirstName(),
				  employee.getLastName(),
				  employee.getBirthDate()));
	  }
  }
  
  /**
   * prompt the user to identify a Contractor by ID.
   *
   * @return contractor with entered contractor ID
   */
  public Contractor getContractor() {
	  this.printContractors(contractorCtrl.getContractors());
	  System.out.println();
	  Contractor contractor = null;
	  do {
		  int id = this.getIntegerInput("Choose contractor by");
		  contractor = contractorCtrl.findContractorByID(id);
	  } while (contractor == null);
	  
	  return contractor;
  }
  /**
   * Prints the contractors.
   */
  public void printContractors(List<Contractor> contractors) {
	  System.out.println("*** Contractors ***");
      for (Contractor contractor : contractors) {
    	System.out.println(String.format("(%d) %s", contractor.ID, contractor.getCompanyName()));
      }
    }
  
  
  /**
   * prompt the user to identify a customer, and then a quote.
   *
   * @param customerPrompt the customer prompt
   * @param quotePrompt the quote prompt
   * @return Quote The found quote belonging to the customer
   */
  public Quote getQuote(String quotePrompt) {
	  Customer customer = this.getCustomer();
	  this.clearScreen();
	  this.printQuotes(customer);
	  Quote quote = null;
	  do {
		  int quoteId = this.getIntegerInput(quotePrompt);
		  quote = quoteCtrl.findOrderByIdForCustomer(quoteId, customer);
	  } while (quote == null);
	  
	  return quote;
  }


  /**
   * Gets the quote.
   *
   * @return the quote
   */
  public Quote getQuote() {
	  return this.getQuote("Choose the quote");
  }
  
  /**
   * Gets the product.
   *
   * @return the product
   */
  public Product getProduct() {
	  return getProduct("Choose product");
  }
  


  /**
   * Gets the storage location.
   *
   * @return the storage location
   */
  public StorageLocation getStorageLocation() {
	  this.printStorageLocations();
	  StorageLocation storageLocation = null;
	  do {
		  int id = this.getIntegerInput("Choose Storage Location by ID");
		  storageLocation = stockCtrl.findStorageLocationById(id);
	  } while (storageLocation == null);
	  
	  return storageLocation;
  }
  
  /**
   * Prints the all shelves.
   */
  public void printShelf(Shelf shelf) {
      System.out.println("Shelf ID: " + String.format(("%d"), shelf.ID));
      System.out.println("Name: " + String.format(("%s"), shelf.getName()));
      System.out.println("Address: " + String.format(("%s"), shelf.getStorageLocation().getName()));
  }

  /**
   * Prints the all shelves.
   */
  public void printAllShelves() {
	  System.out.println("[Shelves]");
    for (Shelf shelf : stockCtrl.getShelves()) {
      this.printShelf(shelf);
      System.out.println();
    }
  }
  
  /**
   * Prints the all shelves.
   */
  public void printShelves(StorageLocation storageLocation) {
	  System.out.println("[Shelves for " + storageLocation.getName() +"]");
	  List<Shelf> shelves = stockCtrl.getShelves(storageLocation);
    for (int i = 0; i < shelves.size(); i++) {
    	Shelf shelf = shelves.get(i);
      System.out.println("(%d)" + String.format(("%d"), i));
      System.out.println("Name: " + String.format(("%s"), shelf.getName()));
      System.out.println("Address: " + String.format(("%s"), shelf.getStorageLocation().getName()));
      System.out.println();
    }
  }
  
  /**
   * Gets the shelf.
   *
   * @return the shelf
   */
  public Shelf getShelf(StorageLocation storageLocation) {
	  
	  this.printShelves(storageLocation);
	  Shelf shelf = null;
	  do {
		  int index = this.getIntegerInput("Choose Shelf by ID");
		  shelf = stockCtrl.findShelfByIndex(storageLocation, index);
	  } while (shelf == null);
	  
	  return shelf;
  }
  
  /**
   * print all products marked as buyable
   */
  public void printBuyableProducts() {
	  for (Product product: productCtrl.getBuyableProducts()) {
      System.out.println();
		  System.out.println(String.format("(%s) %s",
				  product.ID,
				  product.getName()));
		  System.out.println(String.format("In stock: %d", 
				  Stock.getInstance().getBuyableQuantityInStock(product)));
		  System.out.println(String.format("Price: %.2f DKK",
				  product.getLatestSellingPrice()));
		  System.out.println("Description: " + product.getDescription());
		  System.out.println();
	  }
  }
  
  /**
   * Prints the quotes.
   *
   * @param customer the customer
   */
  public void printQuotes(Customer customer) {
		System.out.println(String.format("[Quotes for %s %s]", customer.getFirstName(), customer.getLastName()));
		for (Quote quote: quoteCtrl.getQuotes(customer)) {
			System.out.println("\tQuote: #" + quote.ID + " " + quote.CREATION_DATE);
		}
  }
  
  /**
   * Prints the orders.
   *
   * @param customer the customer
   */
  public void printOrders(Customer customer) {
		System.out.println(String.format("[Orders for %s %s]", customer.getFirstName(), customer.getLastName()));
		for (Order order: orderCtrl.getOrders(customer)) {
			System.out.println("\torder: #" + order.ID + " " + order.CREATION_DATE);
		}
  }

  /**
   * Gets the string input.
   *
   * @param prompt The text asking the user for input
   * @return String A string as input from the user
   */
  public String getStringInput(String prompt) {
    System.out.print(prompt + ": ");
    String userInput = scanner.nextLine().trim();
    // if back cmd, go to current menu
    if (userInput.toLowerCase().contentEquals(GO_BACK_CMD.toLowerCase())) {
    	this.currentInterface.show();
    }
    while(userInput.isEmpty()){
      System.out.println("Please enter the requested info or type '"+ GO_BACK_CMD + "' to go back");
      userInput = scanner.nextLine();
    }
    return userInput;
  }

  /**
   * Gets the integer input.
   *
   * @param prompt The text asking the user for input
   * @return int An integer as input from the user
   * Note: Runs until a valid integer value is entered
   */
  public int getIntegerInput(String prompt, int min, int max) {
    int userInput;

    while (true) {
      try {
        userInput = Integer.parseInt(getStringInput(prompt));
        if (userInput < min || userInput > max) {
        	printError("Please choose a number between " + min + " and " + max);
        } else {
        	break;
        }
        
      } catch (NumberFormatException e) {
        printError("Please enter a valid integer number or type " + GO_BACK_CMD + " to go back");
        // Not a valid integer input. No continue required.
      }
    }

    return userInput;
  }
  public int getIntegerInput(String prompt) {
	  return getIntegerInput(prompt, Integer.MIN_VALUE, Integer.MAX_VALUE);
  }



  /**
   * Gets the customer type.
   *
   * @param prompt the prompt
   * @return the customer type
   */
  public CustomerType getCustomerType(String prompt){
	printCustumerTypes(customerCtrl.getCustomerTypes());
    CustomerType customerType = null;
    do {
      int id = getIntegerInput(prompt);
      customerType = customerCtrl.findCustomerTypeById(id);
    } while (customerType == null);
    return customerType;
  }
  public CustomerType getCustomerType() {
	  return getCustomerType("Choose customer type");
  }

  /**
   * Prints the custumer types.
   */
  public void printCustumerTypes(List<CustomerType> customerTypes){
	System.out.println("*** Customer types ***");
    for(CustomerType customerType : customerTypes){
      String printline = "(%d) %s with %d%% discount";
      System.out.println(String.format(printline, 
    		  customerType.ID,
    		  customerType.getName(),
    		  customerType.getDiscountPercentage()));
      System.out.println();
    }
  }

  /**
   * Gets a specific bulk discount for a specific product by index in list
   *
   * @param product the product
   * @return the bulk discount
   */
  public BulkDiscount getBulkDiscount(String prompt, Product product) {
	  System.out.println("*** Bulk Discounts for " + product.getName() + " ***");
	  printBulkDiscounts(product.getBulkDiscounts());
	  BulkDiscount bulkDiscount = null;
	  do {
		  int index = this.getIntegerInput(prompt);
		  bulkDiscount = productCtrl.getBulkDiscountByIndex(product, index);
	  }  while (bulkDiscount == null);
	  return bulkDiscount;
  }

	/**
	* print bulk discount info for specific product
	* @param product to print bulk discount info for
	*/
	public void printBulkDiscounts(ArrayList<BulkDiscount> bulkDiscounts){
		for (int i = 0; i < bulkDiscounts.size(); i++) {
			BulkDiscount bulkDiscount = bulkDiscounts.get(i);
		    System.out.println();
		    System.out.println("ID: " + i);
		    System.out.println("Discount percentage: " + bulkDiscount.getDiscountPercentage());
		    System.out.println("Minimum quantity: " + bulkDiscount.getMinQuantity());
		    System.out.println("Active: " + bulkDiscount.isActive());
		    System.out.println();
		}

	}

  /**
   * prints info about specific bulkdiscount
   * @param product 
   * @param bulkDiscount
   */
  public void printBulkDiscount(Product product, BulkDiscount bulkDiscount) {
        System.out.println("Bulk discount for: "+ product.getName());
        System.out.println("Discount percentage: " + bulkDiscount.getDiscountPercentage());
        System.out.println("Min quantity: " + bulkDiscount.getMinQuantity());
  }
  /**
   * Prints the bulk discount.
   *
   * @param product the product
   */
  public void printBulkDiscount(Product product){
      ArrayList<BulkDiscount> bulkDiscounts = product.getBulkDiscounts();
      for(BulkDiscount bulkDiscount : bulkDiscounts) {
        System.out.println();
        System.out.println("Index of the bulk discount: " + bulkDiscounts.indexOf(bulkDiscount));
        System.out.println("Discount percantage: " + bulkDiscount.getDiscountPercentage());
        System.out.println("Minimum quantity: " + bulkDiscount.getMinQuantity());
        System.out.println("Active: " + bulkDiscount.isActive());
        System.out.println();
      }
  }

  /**
   * Prints the all bullk discount.
   */
  public void printAllBullkDiscount() {
    for (Product product : productCtrl.getProducts()) {
      ArrayList<BulkDiscount> bulkDiscounts = product.getBulkDiscounts();
      for (BulkDiscount bulkDiscount : bulkDiscounts) {
        System.out.println();
        System.out.println("For ["+ product.getName() +"] product");
        System.out.println("Index of the bulk discount: " + bulkDiscounts.indexOf(bulkDiscount));
        System.out.println("Discount percantage: " + bulkDiscount.getDiscountPercentage());
        System.out.println("Minimum quantity: " + bulkDiscount.getMinQuantity());
        System.out.println("Active: " + bulkDiscount.isActive());
        System.out.println();
      }
    }
  }

  /**
   * Prints the bulk discount.
   *
   * @param bulkDiscount the bulk discount
   */
  public void printBulkDiscount(BulkDiscount bulkDiscount) {
        System.out.println();
        System.out.println("Discount percantage: " + bulkDiscount.getDiscountPercentage());
        System.out.println("Minimum quantity: " + bulkDiscount.getMinQuantity());
        System.out.println("Active: " + bulkDiscount.isActive());
  }

  /**
   * Gets the supply order.
   *
   * @param prompt the prompt
   * @return the supply order
   */
  public SupplyOrder getSupplyOrder(String prompt){
	this.printSupplyOrders();
    SupplyOrder supplyOrder;
    do {
      int id = getIntegerInput(prompt);
      supplyOrder = supplyCtrl.findSupplyOrderById(id);
    } while (supplyOrder == null);
    return supplyOrder;
  }
  public SupplyOrder getSupplyOrder() {
	  return getSupplyOrder("Choose supply order");
  }
  
  public void printSupplyOrders() {
	  System.out.println("[Supply Orders[");
	  for(SupplyOrder supplyOrder: supplyCtrl.getSupplyOrders()) {
		  this.printSupplyOrder(supplyOrder);
	  }
  }

  /**
   * Gets the any key input.
   *
   * @param prompt the prompt
   * @return the any key input
   */
  public void getAnyKeyInput(String prompt){
    System.out.print(prompt + ": ");
    String userInput = scanner.nextLine();
  }
  public void getAnyKeyInput() {
	  getAnyKeyInput("Press [Enter] to go back...");
  }

  /**
   * Gets the big decimal input.
   *
   * @param prompt the prompt
   * @return the big decimal input
   */
  public BigDecimal getBigDecimalInput(String prompt, int min, int max){
    return BigDecimal.valueOf(getIntegerInput(prompt, min, max));
  }

  /**
   * Gets the date time input.
   *
   * @param prompt The text asking the user for input
   * @return LocalDateTime as input from the user
   * Note: Runs until valid dateTime is entered
   */
  public LocalDateTime getDateTimeInput(String prompt) {
    LocalDateTime userInput;
    
    while (true) {
      try {
        String dateString = getStringInput(prompt + " (" + DATE_FORMAT.toLowerCase() + ")");
        userInput = convertStringToDateTime(dateString);
        // success. So, valid input & breaking out.
        break;
      } catch (DateTimeParseException e) {
        printError("Please enter a valid date in the format of " + DATE_FORMAT.toLowerCase());
        // No continue required.
      }
    }
    
    return userInput;
  }
  
  /**
   * Gets the date input.
   *
   * @param prompt The text asking the user for input
   * @return LocalDate as input from the user
   * Note: Runs until valid date is entered
   */
  public LocalDate getDateInput(String prompt) {
    LocalDate userInput;
    
    while (true) {
      try {
        String dateString = getStringInput(prompt + " (" + DATE_FORMAT.toLowerCase() + ")");
        userInput = convertStringToDate(dateString);
        // success. So, valid input & breaking out.
        break;
      } catch (DateTimeParseException e) {
        printError("Please enter a valid date in the format of " + DATE_FORMAT.toLowerCase());
        // No continue required.
      }
    }
    
    return userInput;
  }

  /**
   * Confirm input.
   *
   * @param prompt The text asking the user for input
   * @return boolean True if user confirmed input, else false
   * Note: Runs until the user enters a valid confrmation value
   */
  public boolean confirmInput(String prompt) {

    while (true) {
      String userInput = getStringInput(prompt + " (yes/no)");
      userInput = userInput.toLowerCase();

      if (userInput.equals("y") || userInput.equals("yes")) {
        return true;
      } else if (userInput.equals("n") || userInput.equals("no")) {
        return false;
      } else {
        printError("Please enter a valid response: yes or no");
      }
    }

  }
  
  /**
   * Confirm input.
   *
   * @return true, if successful
   */
  /*
   * Confirm input with default prompt.
   * @param prompt The text asking the user for input
   * @return boolean True if user confirmed input, else false
   */
  public boolean confirmInput() {
	  return confirmInput("All details have been entered. Are you sure they are correct?");
  }


  /**
   * Convert string to date time.
   *
   * @param dateString the date string
   * @return localDateTime converted from String using the given format
   */
  private LocalDateTime convertStringToDateTime(String dateString) {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMAT);
    LocalDateTime localDateTime = LocalDate.parse(dateString, formatter).atStartOfDay();
    return localDateTime;
  }
  
  /**
   * Convert string to date.
   *
   * @param dateString the date string
   * @return localDate converted from String using the given format
   */
  private LocalDate convertStringToDate(String dateString) {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMAT);
    LocalDate localDate = LocalDate.parse(dateString, formatter);
    return localDate;
  }
  
  /**
   * Prints the error.
   *
   * @param message the message
   */
  /*
   * Print an error message
   * @param String The error message to print.
   */
	public void printError(String message) {
		System.out.println("[ERROR]: " + message + "\n");
	}
	
	/**
	 * Clear screen.
	 */
	/*
	 * Clear terminal screen
	 */
	public void clearScreen() {
		for (int i = 0; i < 30; i++) {
			System.out.println();
		}
	}

  /**
   * Prints the all storage locations.
   */
  public void printStorageLocations() {
	  System.out.println("[Storage locations]");
	  if (stockCtrl.getStorageLocations().isEmpty()) {
		  System.out.println("There are no storage locations...");
		  return;
	  }
      for (StorageLocation storageLocation : stockCtrl.getStorageLocations()) {
        System.out.println("Storage Location ID: " + String.format(("%d"), storageLocation.ID));
        System.out.println("Name: " + String.format(("%s"), storageLocation.getName()));
        System.out.println("Address: " + String.format(("%s"), storageLocation.getAddress()));
        System.out.println("Store: " + String.format(("%s"), storageLocation.getIsAStore()));
        System.out.println();
      }
    }
  
  public void printSupplyOrder(SupplyOrder supplyOrder) {
      System.out.println("Supply Order ID: " + String.format(("%d"), supplyOrder.ID));
      System.out.println("Product: " + String.format(("%s"), supplyOrder.getProduct().getName()));
      System.out.println("Quantity: " + String.format(("%d"), supplyOrder.getQuantity()));
      System.out.println("Price per item: " + String.format(("%.2f"), supplyOrder.getPricePerItem()));
      System.out.println("Date Ordered: " + String.format(("%s"), supplyOrder.getDateOrdered()));
      System.out.println("Delivered: " + String.format(("%s"), supplyOrder.isDelivered()));
    }
  
  public void printAllSupplyOrders() {
	  System.out.println("[Supply orders]");
	  System.out.println();
      for (SupplyOrder supplyOrder : supplyCtrl.getSupplyOrders()) {
        printSupplyOrder(supplyOrder);  
      }
    }
  
  public void printUndeliveredSupplyOrders() {
	  System.out.println("[UnDelivered supply orders]");
	  System.out.println();
      for (SupplyOrder supplyOrder : supplyCtrl.getUndeliveredSupplyOrders()) {
        printSupplyOrder(supplyOrder);
      }
    }
  
  public void printDeliveredSupplyOrders() {
	  System.out.println("[Delivered supply orders]");
	  System.out.println();
      for (SupplyOrder supplyOrder : supplyCtrl.getDeliveredSupplyOrders()) {
        printSupplyOrder(supplyOrder);
      }
    }
  
    
  /**
   * Prints the all supply offers with index
   *
   * @param product the product
   */
  public void printSupplyOffers(Product product) {
	    System.out.println("[Supply Offers for" + product.getName() + "]");
	    System.out.println();
	    List<SupplyOffer> supplyOffers = supplyCtrl.getSupplyOffers(product);
        for (int i = 0; i < supplyOffers.size(); i++) {
        	SupplyOffer supplyOffer = supplyOffers.get(i);
          System.out.println("(%d): " + String.format(("%d"), i));
          System.out.println("Price per Item: " + String.format(("%.2f"), supplyOffer.getPRICE_PER_PRODUCT()));
          System.out.println("Min Quantity: " + String.format(("%d"), supplyOffer.MIN_QUANTITY));
          System.out.println("Date added: " + String.format(("%s"), supplyOffer.DATE_ADDED));
          System.out.println("Supply offer is active: " + String.format(("%s"), supplyOffer.isActive()));
          System.out.println();
        }
    }
  
  public SupplyOffer getSupplyOffer(Product product) {
	this.printSupplyOffers(product);
    SupplyOffer supplyOffer = null;
    do {
      int index = getIntegerInput("Choose supply offer");
      supplyOffer = supplyCtrl.findSupplyOfferByIndex(product, index);
    } while (supplyOffer == null);
    return supplyOffer;
  }

}