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
	
	public Employee(int CPRNumber) {
		// TODO Auto-generated constructor stub
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
