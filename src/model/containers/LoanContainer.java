package model.containers;

import java.util.ArrayList;
import java.util.List;

import model.Customer;
import model.Loan;

public class LoanContainer {
	
	private static LoanContainer instance;
	private ArrayList<Loan> loans;

	/*
	 * Private constructor: singleton
	 */
	private LoanContainer() {
		this.loans = new ArrayList<>();
	}
	
    /**
     * @return instance of OrderContainer
     */
    public static LoanContainer getInstance(){
		if (instance == null) {
            instance = new LoanContainer();
        }
        return instance;
    }
    
    
    /*
     * @param order The order to add to the container
     * @return true if successful, else false
     */
    public boolean addLoan(Loan loan) {
    	return loans.add(loan);
    }
    
    /*
     * @param order The order to remove from the container
     * @return true if successful, else false
     */
    public boolean removeLoan(Loan loan) {
    	return loans.remove(loan);
    }
    
    /**
     * @return All orders
     */
    public List<Loan> getLoans() {
    	return this.loans;
    }
    
    /**
     * Find order by ID.
     *
     * @param orderID the order ID
     * @return the order
     */
    public Loan findLoanByID(int loanId) {
    	for (Loan loan: loans) {
    		if (loan.ID == loanId) {
    			return loan;
    		}
    	}
    	return null;
    }
    
    /**
     * Gets all orders for a specific customer
     *
     * @param customer the customer
     * @return the orders for the customer
     */
    public List<Loan> getLoansByCustomer(Customer customer) {
    	ArrayList<Loan> customerLoans = new ArrayList<>();
    	for (Loan loan: this.loans) {
    		if (loan.getCustomer().equals(customer)) {
    			customerLoans.add(loan);
    		}
    	}
    	return customerLoans;
    }

}
