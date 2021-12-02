/**
 * 
 */
package model;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @author Daniels Kanepe
 *
 */
public class EmployeeCustomer extends AbstractPerson
implements IFCustomer, IFEmployee {
	
	private Order currentOrder = null;
	private final int ID;
	private int CPRNumber;
	/**
	 *  Constructor
	 */
	public EmployeeCustomer(int ID, int CPRNumber, String firstName, String lastName, String address, String mobile, LocalDateTime birthDate) {
		super(firstName, lastName, address, mobile, birthDate);
		this.ID = ID;
		this.CPRNumber = CPRNumber;
	}
	
	public void setCurrentOrder(Order currentOrder) {
		this.currentOrder = currentOrder;
	}

	public Order getCurrentOrder() {
		return currentOrder;
	}

}
