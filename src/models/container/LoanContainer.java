package models.container;

import java.util.ArrayList;
import java.util.List;

import models.Customer;
import models.Loan;

public class LoanContainer {
	
	private static LoanContainer instance;
	private ArrayList<Loan> loans;

	/*
	 * Private constructor class LoanContainer
	 */
	private LoanContainer() {
		this.loans = new ArrayList<>();
	}
	
    /**
     * @return instance of LoanContainer
     */
    public static LoanContainer getInstance(){
		if (instance == null) {
            instance = new LoanContainer();
        }
        return instance;
    }
    
    
    /**
     * @param loan - loan to be added to the container
     * @return true if successful, else false
     */
    public boolean addLoan(Loan loan) {
    	return loans.add(loan);
    }
    
    /**
     * @param loan loan to be removed from the container
     * @return true if successful, else false
     */
    public boolean removeLoan(Loan loan) {
    	return loans.remove(loan);
    }
    
    /**
     * @return all loans
     */
    public List<Loan> getLoans() {
    	return this.loans;
    }
    
    /**
     * Find loan by its ID.
     *
     * @param loanID the loan ID
     * @return the loan
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
     * Gets all loans for a specific customer
     *
     * @param customer - specific customer
     * @return all loans for the customer
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
