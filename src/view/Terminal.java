package view;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

import controller.CustomerController;

import model.Customer;
import model.CustomerContainer;

public class Terminal {
  private static Terminal instance;
  private Scanner scanner;
  
  CustomerController customerCtrl;

  private static final String DATE_FORMAT = "dd/MM/yyyy";

  /**
   * Constructor for the Parser class.
   */
  private Terminal() {
    scanner = new Scanner(System.in);
    
    customerCtrl = new CustomerController();
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
  
  public void printAllCustomers() {
	  for (Customer customer: customerCtrl.getCustomers()) {
		  System.out.println("(" + customer.ID + ") " 
	  + customer.getFirstName() + " " + customer.getLastName());
	  }
  }

  /**
   * @param prompt The text asking the user for input
   * @return String A string as input from the user
   */
  public String getStringInput(String prompt) {
    System.out.print(prompt + ": ");
    String userInput = scanner.nextLine();
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

  /**
   * @param prompt The text asking the user for input
   * @return LocalDate as input from the user
   * Note: Runs until valid date is entered
   */
  public LocalDateTime getDateInput(String prompt) {
    LocalDateTime userInput;
    
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
  private LocalDateTime convertStringToDate(String dateString) {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMAT);
    LocalDateTime localDateTime = LocalDate.parse(dateString, formatter).atStartOfDay();
    return localDateTime;
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
	
	/*
	 * Clear terminal screen
	 */
	public void clearScreen() {
		for (int i = 0; i < 30; i++) {
			System.out.println();
		}
	}

}
