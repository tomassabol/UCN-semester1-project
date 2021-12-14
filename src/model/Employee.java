/**
 * 
 */
package model;

import java.time.LocalDate;

/**
 * @author Daniels Kanepe
 *
 */
public class Employee extends AbstractPerson
implements IFEmployee {

	/**
	 * Fields for class Employee
	 */
	private String CPRNumber;
	
	public Employee(int ID, String CPRNumber, String firstName, String lastName, String address, String mobile, LocalDate birthDate) {
		super(ID, firstName, lastName, address, mobile, birthDate);
		this.CPRNumber = CPRNumber;
	}

	/**
	 * Getter for CPRnumber
	 */
	public String getCPRnumber(){
		return CPRNumber;
	}

	/**
	 * Setter for CPRNumber
	 * @param CPRNumber The new CPR number
	 */
	public void setCPRNumber(String CPRNumber){
		this.CPRNumber = CPRNumber;
	}
	
	public String getHashedPassword() {
		return "";
	}
	
	public String getUsername() {
		return "";
	}
	
	public int getID() {
		return this.ID;
	}
	

}
