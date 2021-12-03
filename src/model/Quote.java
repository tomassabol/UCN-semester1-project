package model;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Quote {
	public final int ID;
	private LocalDateTime creationDate;
	private IFCustomer customer;
	private IFEmployee employee;
	private ArrayList<UnspecificItemLine> itemLines;

	public Quote(int Id, IFCustomer customer,
			IFEmployee employee, ArrayList<UnspecificItemLine> itemLines) {
		this.ID = Id;
		this.customer = customer;
		this.employee = employee;
		this.itemLines = itemLines;
		
		this.creationDate = LocalDateTime.now();
	}

	public LocalDateTime getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(LocalDateTime creationDate) {
		this.creationDate = creationDate;
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

	public void setItemLines(ArrayList<UnspecificItemLine> itemLines) {
		this.itemLines = itemLines;
	}

}
