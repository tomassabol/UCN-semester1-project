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
  private static Terminal instance;
  private Scanner scanner;
  
  CustomerController customerCtrl;
  ProductController productCtrl;
  ContractorController contractorCtrl;
  StockController stockCtrl;
  SupplyController supplyCtrl;
  QuoteController quoteCtrl;

  private static final String DATE_FORMAT = "dd/MM/yyyy";

  /**
   * Constructor for the Parser class.
   */
  private Terminal() {
    scanner = new Scanner(System.in);
    
    customerCtrl = new CustomerController();
    productCtrl = new ProductController();
    contractorCtrl = new ContractorController();
    stockCtrl = new StockController();
    supplyCtrl = new SupplyController();
    quoteCtrl = new QuoteController();
  }

  /**
   * @return The instance of the Parser class
   */
  public static Terminal getInstance() {
    if (instance == null) {
      instance = new Terminal();
    }
    return instance;
  }
  
  /**
   * Prompt the user to identify a customer by ID
   *
   * @return the customer
   * Note: Runs until valid ID is entered
   */
  public Customer getCustomer() {
	  Customer customer = null;
	  do {
		  int id = this.getIntegerInput("Choose customer by ID");
		  customer = customerCtrl.findCustomerByID(id);
	  } while (customer == null);
	  
	  return customer;
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

  public StorageLocation getStorageLocation() {
	  StorageLocation storageLocation = null;
	  do {
		  int id = this.getIntegerInput("Choose Storage Location by ID");
		  storageLocation = stockCtrl.findStorageLocationById(id);
	  } while (storageLocation == null);
	  
	  return storageLocation;
  }

  
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

  public void printProductInfos(){
    for (Product product : productCtrl.getProducts()){
      System.out.println();
		  System.out.println("[" + product.getName() + "]");
		  System.out.println("ID: " + product.ID);
		  System.out.println("Description: " + product.getDescription());
		  System.out.println("In stock: " + "Unknown");
		  System.out.println("Price: " + product.getLatestSellingPrice() + " DKK");
		  System.out.println();
    }
  }
  
  public void printQuotes(Customer customer) {
		System.out.println(String.format("[Quotes for %s %s]", customer.getFirstName(), customer.getLastName()));
		for (Quote quote: quoteCtrl.getQuotes(customer)) {
			System.out.println("Quote: #" + quote.ID + " " + quote.CREATION_DATE);
		}
  }

  /**
   * @param prompt The text asking the user for input
   * @return String A string as input from the user
   */
  public String getStringInput(String prompt) {
    System.out.print(prompt + ": ");
    String userInput = scanner.nextLine();
    while(userInput.isEmpty()){
      System.out.println("Insert valid input");
      userInput = scanner.nextLine();
    }
    return userInput.trim();
  }

  /**
   * @param prompt The text asking the user for input
   * @return int An integer as input from the user
   * Note: Runs until a valid integer value is entered
   */
  public int getIntegerInput(String prompt) {
    int userInput;

    while (true) {
      try {
        userInput = Integer.parseInt(getStringInput(prompt));
        // If no errors, it means input is a valid int, so break.
        break;
      } catch (NumberFormatException e) {
        printError("Please enter a valid integer number");
        // Not a valid integer input. No continue required.
      }
    }

    return userInput;
  }

  public CustomerType getCustomerType(String prompt){
    CustomerType customerType;
    do {
      int id = getIntegerInput(prompt);
      customerType = customerCtrl.findCustomerTypeById(id);
    } while (customerType == null);
    return customerType;
  }

  public void printCustumerTypes(){
    List<CustomerType> customerTypes = customerCtrl.getCustomerTypes();
    for(CustomerType customerType : customerTypes){
      System.out.println("Customer type name: " + customerType.getName() + "\nCustomer type discount percantage: " +customerType.getDiscountPercentage() + "\nCustomer type id: " + customerType.ID);
      System.out.println();
    }
  }

  public void printContractorInfo() {
    List<Contractor> contractors = contractorCtrl.getContractors();
    for(Contractor contractor : contractors){
      System.out.println();
      System.out.println("Contractor's ID: " + contractor.ID);
      System.out.println("Contractor's company's name: " + contractor.getCompanyName());
      System.out.println();
    }
  }

  public void printArrayBulkDiscount(ArrayList<BulkDiscount> bulkDiscounts) {
      for (BulkDiscount bulkDiscount : bulkDiscounts) {
        System.out.println();
        System.out.println("Index of the bulk discount: " + bulkDiscounts.indexOf(bulkDiscount));
        System.out.println("Discount percantage: " + bulkDiscount.getDiscountPercentage());
        System.out.println("Minimum quantity: " + bulkDiscount.getMinQuantity());
        System.out.println();
      }
  }

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

  public void printBulkDiscount(Product product, BulkDiscount bulkDiscount) {
        System.out.println();
        System.out.println("Discount percantage: " + bulkDiscount.getDiscountPercentage());
        System.out.println("Minimum quantity" + bulkDiscount.getMinQuantity());
        System.out.println("Active: " + bulkDiscount.isActive());
        System.out.println();
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
    return;
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
	public static void printError(String message) {
		System.out.println("ERROR: " + message + "\n");
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

  public void printCustomerType() {
  }

  public void printAllStorageLocations() {
      for (StorageLocation storageLocation : stockCtrl.getStorageLocations()) {
        System.out.println("Storage Location ID: " + String.format(("%d"), storageLocation.ID));
        System.out.println("Name: " + String.format(("%s"), storageLocation.getName()));
        System.out.println("Address: " + String.format(("%s"), storageLocation.getAddress()));
        System.out.println("Store: " + String.format(("%s"), storageLocation.getIsAStore()));
        System.out.println();
      }
    }

}