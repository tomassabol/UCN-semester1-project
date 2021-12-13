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

public class Terminal {
	private final String GO_BACK_CMD = "-back";
  private Scanner scanner;
  
  CustomerController customerCtrl;
  ProductController productCtrl;
  ContractorController contractorCtrl;
  StockController stockCtrl;
  SupplyController supplyCtrl;
  QuoteController quoteCtrl;
  OrderController orderCtrl;
  GenericMenuInterface currentInterface;

  private static final String DATE_FORMAT = "dd/MM/yyyy";

  /**
   * Constructor for the Parser class.
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
  }
  
  /**
   * Prompt the user to identify a customer by ID
   *
   * @return the customer
   * Note: Runs until valid ID is entered
   */
  public Customer getCustomer(String prompt) {
	  Customer customer = null;
	  do {
		  int id = this.getIntegerInput(prompt);
		  customer = customerCtrl.findCustomerByID(id);
	  } while (customer == null);
	  
	  return customer;
  }
  public Customer getCustomer() {
	  return this.getCustomer("Choose a customer by ID");
  }
  
  

  /**
   * prompt the user to identify a product by ID
   * @return specific product with entered product ID
   */
  public Product getProduct(String prompt) {
	  Product product = null;
	  do {
		  int id = this.getIntegerInput(prompt);
		  product = productCtrl.findProductByID(id);
	  } while (product == null);
	  
	  return product;
  }
  
  /**
   * prompt the user to identify a customer, and then a quote
   * @return Quote The found quote belonging to the customer
   */
  public Quote getQuote(String customerPrompt, String quotePrompt) {
	  Customer customer = this.getCustomer("Choose a customer to show the quotes for");
	  this.clearScreen();
	  System.out.println("[Quotes]");
	  this.printQuotes(customer);
	  Quote quote = null;
	  do {
		  int quoteId = this.getIntegerInput(quotePrompt);
		  quote = quoteCtrl.findOrderByIdForCustomer(quoteId, customer);
	  } while (quote == null);
	  
	  return quote;
  }
  public Quote getQuote() {
	  return this.getQuote("Choose a customer to show the quotes for",
			  "Choose the quote");
  }
  
  public Product getProduct() {
	  return getProduct("Choose product");
  }
  
  /**
   * prompt the user to identify a Contractor by ID
   * @return contractor with entered contructor ID
   */
  public Contractor getContractor() {
	  Contractor contractor = null;
	  do {
		  int id = this.getIntegerInput("Choose contractor by ID");
		  contractor = contractorCtrl.findContractorByID(id);
	  } while (contractor == null);
	  
	  return contractor;
  }

  /**
   * @return object of a storage location with specific id
   */
  public StorageLocation getStorageLocation() {
	  StorageLocation storageLocation = null;
	  do {
		  int id = this.getIntegerInput("Choose Storage Location by ID");
		  storageLocation = stockCtrl.findStorageLocationById(id);
	  } while (storageLocation == null);
	  
	  return storageLocation;
  }

  /**
   * @return object of a class shelf with specific ID
   */
  public Shelf getShelf() {
	  Shelf shelf = null;
	  do {
		  int id = this.getIntegerInput("Choose Shelf by ID");
		  shelf = stockCtrl.findShelfById(id);
	  } while (shelf == null);
	  
	  return shelf;
  }
  
  /**
   * Prints all Customers
   */
  public void printAllCustomers() {
	  for (Customer customer: customerCtrl.getCustomers()) {
		  System.out.println("(" + customer.ID + ") " 
	  + customer.getFirstName() + " " + customer.getLastName());
	  }
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
   * prints all product and their info
   */
  public void printProductInfos(){
    for (Product product : productCtrl.getProducts()){
      System.out.println();
		  System.out.println("[" + product.getName() + "]");
		  System.out.println("ID: " + product.ID);
		  System.out.println("Description: " + product.getDescription());
		  System.out.println("In stock: " + productCtrl.getStock(product));
		  System.out.println("Price: " + product.getLatestSellingPrice() + " DKK");
		  System.out.println();
    }
  }
  
  /**
   * prints all quotes of a specific customer
   * @param customer to print quotes for
   */
  public void printQuotes(Customer customer) {
		System.out.println(String.format("[Quotes for %s %s]", customer.getFirstName(), customer.getLastName()));
		for (Quote quote: quoteCtrl.getQuotes(customer)) {
			System.out.println("\tQuote: #" + quote.ID + " " + quote.CREATION_DATE);
		}
  }
  
  public void printOrders(Customer customer) {
		System.out.println(String.format("[Orders for %s %s]", customer.getFirstName(), customer.getLastName()));
		for (Order order: orderCtrl.getOrders(customer)) {
			System.out.println("\torder: #" + order.ID + " " + order.CREATION_DATE);
		}
  }

  /**
   * @param prompt The text asking the user for input
   * @return String A string as input from the user
   */
  public String getStringInput(String prompt) {
    System.out.print(prompt + ": ");
    String userInput = scanner.nextLine().trim();
    if (userInput.toLowerCase().contentEquals(GO_BACK_CMD.toLowerCase())) {
    	this.currentInterface.goToPreviousMenu();
    }
    while(userInput.isEmpty()){
      System.out.println("Please enter the requested info or type '"+ GO_BACK_CMD + "' to go back");
      userInput = scanner.nextLine();
    }
    return userInput;
  }

  /**
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
        	this.printError("Please enter a number from " + min + " to " + max);
        	continue;
        }
        // If no errors, it means input is a valid int, so break.
        break;
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
   * 
   * @param prompt - message to be printed out
   * @return customerType object
   */
  public CustomerType getCustomerType(String prompt){
    CustomerType customerType;
    do {
      int id = getIntegerInput(prompt);
      customerType = customerCtrl.findCustomerTypeById(id);
    } while (customerType == null);
    return customerType;
  }

  /**
   * prints al customertypes and information
   */
  public void printCustumerTypes(){
    List<CustomerType> customerTypes = customerCtrl.getCustomerTypes();
    for(CustomerType customerType : customerTypes){
      System.out.println("Customer type name: " + customerType.getName() + "\nCustomer type discount percantage: " +customerType.getDiscountPercentage() + "\nCustomer type id: " + customerType.ID);
      System.out.println();
    }
  }

  /**
   * prints all contractors and its information
   */
  public void printContractorInfo() {
    List<Contractor> contractors = contractorCtrl.getContractors();
    for(Contractor contractor : contractors){
      System.out.println();
      System.out.println("Contractor's ID: " + contractor.ID);
      System.out.println("Contractor's company's name: " + contractor.getCompanyName());
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
        System.out.println("Bulk discount for ["+ product.getName() +"]");
        System.out.println("Discount percentage: " + bulkDiscount.getDiscountPercentage());
        System.out.println("Minimum quantity" + bulkDiscount.getMinQuantity());
        System.out.println("Active: " + bulkDiscount.isActive());
  }

  public SupplyOrder getSupplyOrder(String prompt){
    SupplyOrder supplyOrder;
    do {
      int id = getIntegerInput(prompt);
      supplyOrder = supplyCtrl.findSupplyOrderById(id);
    } while (supplyOrder == null);
    return supplyOrder;
  }

  public void getAnyKeyInput(String prompt){
    System.out.print(prompt + ": ");
    String userInput = scanner.nextLine();
  }
  public void getAnyKeyInput() {
	  getAnyKeyInput("Press [Enter] to go back...");
  }

  public BigDecimal getBigDecimalInput(String prompt){
    return BigDecimal.valueOf(getIntegerInput(prompt));
  }

  /**
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
  /*
   * Confirm input with default prompt.
   * @param prompt The text asking the user for input
   * @return boolean True if user confirmed input, else false
   */
  public boolean confirmInput() {
	  return confirmInput("All details have been entered. Are you sure they are correct?");
  }


  /**
   * @return localDateTime converted from String using the given format
   */
  private LocalDateTime convertStringToDateTime(String dateString) {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMAT);
    LocalDateTime localDateTime = LocalDate.parse(dateString, formatter).atStartOfDay();
    return localDateTime;
  }
  
  /**
   * @return localDate converted from String using the given format
   */
  private LocalDate convertStringToDate(String dateString) {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMAT);
    LocalDate localDate = LocalDate.parse(dateString, formatter);
    return localDate;
  }
  
  /*
   * Print an error message
   * @param String The error message to print.
   */
	public void printError(String message) {
		System.out.println("[ERROR]: " + message + "\n");
	}
  
	/*
	 * Quit program
	 */
	public static void quit() {
		System.out.println("Quitting...");
		System.exit(0);
	}

  public void printAllShelves() {
    for (Shelf shelf : stockCtrl.getShelves()) {
      System.out.println("Shelf ID: " + String.format(("%d"), shelf.ID));
      System.out.println("Name: " + String.format(("%s"), shelf.getName()));
      System.out.println("Address: " + String.format(("%s"), shelf.getStorageLocation().getName()));
      System.out.println();
    }
  }
	
	/*
	 * Clear terminal screen
	 */
	public void clearScreen() {
		for (int i = 0; i < 30; i++) {
			System.out.println();
		}
	}

  public void printAllStorageLocations() {
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

}