package view;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Parser {
  private static Parser instance;
  private Scanner scanner;

  private static final String DATE_FORMAT = "dd/MM/yyyy";

  /**
   * Constructor for the Parser class.
   */
  private Parser() {
    scanner = new Scanner(System.in);
  }

  /**
   * @return The instance of the Parser class
   */
  public static Parser getInstance() {
    if (instance == null) {
      instance = new Parser();
    }
    return instance;
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
  public void printError(String message) {
	  System.out.println("ERROR: " + message + "\n");
  }

}
