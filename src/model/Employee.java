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
	private String email;
	private String hashedPassword;
	
	public Employee(int ID, String CPRNumber, String email, String hashedPassword,  String firstName, String lastName, String address, String mobile, LocalDate birthDate) {
		super(ID, firstName, lastName, address, mobile, birthDate);
		this.CPRNumber = CPRNumber;
		this.email = email;
		this.hashedPassword = hashedPassword;
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
	
	
	public int getID() {
		return this.ID;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getHashedPassword() {
		return hashedPassword;
	}

	public void setHashedPassword(String hashedPassword) {
		this.hashedPassword = hashedPassword;
	}
	

}
