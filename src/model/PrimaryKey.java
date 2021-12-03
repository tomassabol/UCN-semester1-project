package model;

public class PrimaryKey {
	// TODO: Implement with HashMap<Object, int>
	private static int orderID = 0;
	private static int loanID = 0;
	private static int productID = 0;
	private static int productGroupID = 0;
	private static int itemID = 0;
	private static int customerID = 0;
	private static int contractorID = 0;
	private static int supplyOfferID = 0;
	private static int shelfID = 0;
	private static int storageLocationID = 0;
	private static int employeeID = 0;
	private static int quoteID = 0;
	

	public PrimaryKey() {
		// TODO Auto-generated constructor stub
	}
	
	/*
	 * Generates a primary key for Order
	 */
	public static int getNextOrderID() {
		return orderID++;
	}
	
	/*
	 * Generates a primary key for Loan
	 */
	public static int getNextLoanID() {
		return loanID++;
	}

	/*
	 * Generates a primary key for Product
	 */
	public static int getNextProductID() {
		return productID++;
	}
	
	/*
	 * Generates a primary key for ProductGroup
	 */
	public static int getNextProductGroupID() {
		return productGroupID++;
	}
	
	/*
	 * Generates a primary key for Item
	 */
	public static int getNextItemGroupID() {
		return itemID++;
	}

	/**
	 * @return next customerID
	 */
	public static int getNextCustomerID() {
		return customerID++;
	}
	
	public static int getNextContractorID() {
		return contractorID++;
	}

	public static int getNextSupplyOfferID() {
		return supplyOfferID++;
	}

	public static int getNextShelfID() {
		return shelfID++;
	}

	public static int getNextStorageLocationID() {
		return storageLocationID++;
	}
	
	public static int getNextEmployeeID() {
		return employeeID++;
	}
	
	public static int getNextQuoteID() {
		return quoteID++;
	}

}
