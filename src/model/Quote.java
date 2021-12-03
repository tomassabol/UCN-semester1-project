package model;

import java.time.LocalDateTime;
import java.util.ArrayList;

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
		this.itemLines = itemLines;
		
		this.CREATION_DATE = LocalDateTime.now();
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

	public ArrayList<UnspecificItemLine> getItemLines() {
		return itemLines;
	}

	public int getID() {
		return ID;
	}

	public LocalDateTime getCREATION_DATE() {
		return CREATION_DATE;
	}


}
