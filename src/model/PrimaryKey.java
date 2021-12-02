package model;

public class PrimaryKey {
	
	private static int orderID = 0;
	private static int loanID = 0;
	private static int productID = 0;
	private static int productGroupID = 0;
	private static int itemID = 0;
	private static int customerID = 0;
	private static int contractorID = 0;
	

	public PrimaryKey() {
		// TODO Auto-generated constructor stub
	}
	
	/*
	 * Generates a primary key for Order
	 */
	public static int getNextOrderID() {
		orderID++;
		return orderID;
	}
	
	/*
	 * Generates a primary key for Loan
	 */
	public static int getNextLoanID() {
		loanID++;
		return loanID;
	}

	/*
	 * Generates a primary key for Product
	 */
	public static int getNextProductID() {
		productID++;
		return productID;
	}
	
	/*
	 * Generates a primary key for ProductGroup
	 */
	public static int getNextProductGroupID() {
		productGroupID++;
		return productGroupID;
	}
	
	/*
	 * Generates a primary key for Item
	 */
	public static int getNextItemGroupID() {
		itemID++;
		return itemID;
	}

	/**
	 * @return next customerID
	 */
	public static int getNextCustomerID() {
		customerID++;
		return customerID;
	}
	
	public static int getNextContractorID() {
		contractorID++;
		return contractorID;
	}
}
