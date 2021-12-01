/**
 * 
 */
package model;

/**
 * @author Daniels Kanepe
 *
 */
public class Employee extends AbstractPerson
implements IFEmployee {

	/**
	 * Fields for class Employee
	 */
	private int CPRNumber;
	
	public Employee(int CPRNumber, String firstName, String lastName, String address, int mobil, String birthDate) {
		// TODO Auto-generated constructor stub#
		super(firstName, lastName, address, mobil, birthDate);
		this.CPRNumber = CPRNumber;
	}

	/**
	 * Getter for CPRnumber
	 */
	public int getCPRnumber(){
		return CPRNumber;
	}

	/**
	 * Setter for CPRNumber
	 * @param CPRNumber The new CPR number
	 */
	public void setCPRNumber(int CPRNumber){
		this.CPRNumber = CPRNumber;
	}

}
