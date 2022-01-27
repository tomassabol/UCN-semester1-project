package controller;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

import exception.OutOfStockException;
import model.Customer;
import model.IFCustomer;
import model.IFEmployee;
import model.Loan;
import model.PrimaryKey;
import model.Product;
import models.container.LoanContainer;

public class LoanController {

    StockController stockCtrl;
    
    /**
	 * Public constructor class LoanController
	 */
    public LoanController() {
        stockCtrl = new StockController();
    }

    /**
     * @return list of all loans in the system
	 */
    public List<Loan> getLoans() {
        return LoanContainer.getInstance().getLoans();
    }

    /**
     * /**
	 * @param customer - specific customer
     * @return list of all loans of the given customer
	 */
    public List<Loan> getLoans(Customer customer) {
        return LoanContainer.getInstance().getLoansByCustomer(customer);
    }

    /**
     * @param loanId - id of a loan
     * @return loan with this ID
	 */
    public Loan findLoanById(int loanId) {
        return LoanContainer.getInstance().findLoanByID(loanId);
    }

    /**
     * @param loanId - id of a loan
     * @param customer - specific customer
     * @return loan with this ID linked to the customer
	 */
    public Loan findLoanByIdForCustomer(int loanId, Customer customer) {
        Loan loan = findLoanById(loanId);
        if (loan != null && loan.getCustomer() == customer) {
            return loan;
        }
        return null;
    }

    /**
     * @param customer - the customer
     * @param employee - employee
     * @param product - product
     * @param returnDate - return date
     * 
     * @return the newly created loan
	 */
    public Loan createLoan(IFCustomer customer, IFEmployee employee, Product product, LocalDate returnDate) throws OutOfStockException {
        int loanableQuantity = stockCtrl.getLoanableQuantityInStock(product);
        if (loanableQuantity <= 0) {
            throw new OutOfStockException("This product is out of stock");
        }

        Loan loan = new Loan(PrimaryKey.getID(PrimaryKey.Keys.LOAN), returnDate, customer, employee, product);
        loanableQuantity =- 1;
        LoanContainer.getInstance().addLoan(loan);
        return loan;
    }

    /**
     * @param loan - loan to be removed
	 */
    public void removeLoan(Loan loan) {
        LoanContainer.getInstance().removeLoan(loan);
    }

    /**
     * @param loan - loan to be updated
     * @param returnDate - new return date
	 */
    public void updateReturnDate(Loan loan, LocalDate returnDate) {
        loan.setReturnDate(returnDate);
    }
    
    /**
     * this method calculates number of days between 
     * the loan creation date and return days
     * 
     * @param loan - loan
     * @return number of days of loan
	 */
    public long getDaysOfLoan(Loan loan) {
        long days = loan.getCreationDate().toLocalDate().until(loan.getReturnDate(), ChronoUnit.DAYS);
        return days;    
    }

    /**
     * this method calculates the total price of the loan
     * by substracting days of the loan by loaning price (price/day)
     * 
     * @param loan - loan
     * @return total price of the loan
	 */
    public BigDecimal totalPrice(Loan loan) {
        return BigDecimal.valueOf((int)getDaysOfLoan(loan) * loan.getLoaningPrice().intValue());
    }

    /**
     * this method checks if the return date is the same as the 
     * creation date or if it is before the creation date
     * 
     * @param creationDate - creation date of the loan
     * @param returnDate - return date of the loan
     * 
     * @return true if everything is okay
     * @return false if not
	 */
    public boolean validateReturnDate(LocalDate creationDate,LocalDate returnDate) {
        if (returnDate.isBefore(creationDate) || returnDate.isEqual(creationDate)) {
            return false;
        } return true; 
    }
}
