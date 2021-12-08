package model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Map;

public class Quote {
	public final int ID;
	public final LocalDateTime CREATION_DATE;
	private IFCustomer customer;
	private IFEmployee employee;
	private ArrayList<UnspecificItemLine> itemLines;

	public Quote(int Id, IFCustomer customer,
			IFEmployee employee, ArrayList<UnspecificItemLine> itemLines) {
		this.ID = Id;
		this.customer = customer;
		this.employee = employee;
		
		this.CREATION_DATE = LocalDateTime.now();
		
		this.itemLines = itemLines;
		
	}

	public IFCustomer getCustomer() {
		return customer;
	}

	public void setCustomer(IFCustomer customer) {
		this.customer = customer;
	}

	public IFEmployee getEmployee() {
		return employee;
	}

	public void setEmployee(IFEmployee employee) {
		this.employee = employee;
	}

	/**
	 * Gets the item lines.
	 *
	 * @return the item lines as a map, with BulkDiscount or null as key
	 */
	public ArrayList<UnspecificItemLine> getItemLines() {
		return itemLines;
	}
	
	// Note: quotes cannot be changed, so do not use this method!
	private void setItemLines() {
		//
	}

	public int getID() {
		return ID;
	}

	public LocalDateTime getCREATION_DATE() {
		return CREATION_DATE;
	}


}
