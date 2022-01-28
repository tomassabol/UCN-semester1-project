package controller;

import java.time.LocalDateTime;
import java.util.List;

import exception.OutOfStockException;
import model.Customer;
import model.IFCustomer;
import model.IFEmployee;
import model.Loan;
import model.PrimaryKey;
import model.Product;
import model.TrackableItem;
import models.container.LoanContainer;
import models.container.StockContainer;

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

	public Loan createLoan(IFCustomer customer, IFEmployee employee,
			Product product, LocalDateTime proposedReturnDate) throws OutOfStockException {
		if (proposedReturnDate.isBefore(LocalDateTime.now())) {
			throw new IllegalArgumentException("Proposed return date cannot be in the past!");
		}

		// check stock
		int loanableQuantity = stockCtrl.getLoanableQuantityInStock(product);
		if (loanableQuantity<= 0) {
			throw new OutOfStockException("This product is out of stock");
		}
		// Fetch loanable item from stock
		TrackableItem item = StockContainer.getInstance().getLoanableItem(product);
		// create loan
		Loan loan = new Loan(PrimaryKey.getID(PrimaryKey.Keys.LOAN), proposedReturnDate, customer, employee, item);
		// add loan to container
		LoanContainer.getInstance().addLoan(loan);
		// return loan
		return loan;
	}

	/**
	 * @param loan - loan to be removed
	 */
	public void removeLoan(Loan loan) {
		LoanContainer.getInstance().removeLoan(loan);
	}

	/**
	 *  Update the datetime that the item was returned at
	 * 
	 * @param loan - loan to be updated
	 * @param returnDate - new return date
	 */
	public void updateReturnDate(Loan loan, LocalDateTime returnDate) {
		loan.setReturnDate(returnDate);
	}

	/**
	 *  Update the datetime that the item is supposed to be returned at
	 * 
	 * @param loan - loan to be updated
	 * @param proposedReturnDate - the new proposed return date
	 */
	public void updateProposedReturnDate(Loan loan, LocalDateTime proposedReturnDate) {
		loan.setProposedReturnDate(proposedReturnDate);
	}

	public boolean returnDateIsValid(LocalDateTime returnDate) {
		return returnDate.isAfter(returnDate);
	}
}