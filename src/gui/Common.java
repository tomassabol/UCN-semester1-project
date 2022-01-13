/**
 * 
 */
package gui;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * @author Daniels Kanepe
 *
 */
public class Common {
	
	  /** The DATE_FORMAT. */
	  public static final String DATE_FORMAT = "dd/MM/yyyy";
	
	  /**
	   * Convert string to date.
	   *
	   * @param dateString the date string
	   * @return localDate the parsed data
	   * 
	   * @throws DateTimeParseException when cannot parse the date
	   */
	  public static LocalDate stringToDate(String dateString) {
	    DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMAT);
	    return LocalDate.parse(dateString, formatter);
	  }
	  
	  public static String dateToString(LocalDate date) {
		  return date.format(DateTimeFormatter.ofPattern(DATE_FORMAT));
	  }
	  
	  public static String getDateFormat() {
		  return DATE_FORMAT.toLowerCase();
	  }

}
