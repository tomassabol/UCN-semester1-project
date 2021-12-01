/**
 * 
 */
package model;

/**
 * @author Daniels Kanepe
 *
 */
public class Customer extends AbstractPerson
implements IFCustomer {

	public final int ID;
	private String firstName;
	private String secondName;
	/**
	 * 
	 */
	public Customer(int id, String firstName, String secondName) {
		this.ID = id;
		this.firstName = firstName;
		this.secondName = secondName;
	}

	// set/get firstname
	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	// set/get secondName
	public String getSecondName() {
		return this.secondName;
	}

	public void setSecondName(String secondName) {
		this.secondName = secondName;
	}

}
