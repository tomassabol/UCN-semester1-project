package controller;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

import exception.OutOfStockException;
import models.Customer;
import models.IFCustomer;
import models.IFEmployee;
import models.Loan;
import models.PrimaryKey;
import models.Product;
import models.Shelf;
import models.TrackableItem;
import models.container.LoanContainer;
import models.container.StockContainer;

/**
 * The Class LoanController.
 */
public class LoanController {

	/** The stock ctrl. */
	StockController stockCtrl;

	/**
	 * Public constructor class LoanController.
	 */
	public LoanController() {
		stockCtrl = new StockController();
	}

	/**
	 * Gets the loans.
	 *
	 * @return list of all loans in the system
	 */
	public List<Loan> getLoans() {
		return LoanContainer.getInstance().getLoans();
	}

	/**
	 * /**.
	 *
	 * @param customer - specific customer
	 * @return list of all loans of the given customer
	 */
	public List<Loan> getLoans(Customer customer) {
		return LoanContainer.getInstance().getLoansByCustomer(customer);
	}

	/**
	 * Find loan by id.
	 *
	 * @param loanId - id of a loan
	 * @return loan with this ID
	 */
	public Loan findLoanById(int loanId) {
		return LoanContainer.getInstance().findLoanByID(loanId);
	}

	/**
	 * Find loan by id for customer.
	 *
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
	 * Creates the loan.
	 *
	 * @param customer the customer
	 * @param employee the employee
	 * @param product the product
	 * @param proposedReturnDate the proposed return date
	 * @return the loan
	 * @throws OutOfStockException the out of stock exception
	 */
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
	 * Removes the loan.
	 *
	 * @param loan - loan to be removed
	 */
	public void removeLoan(Loan loan) {
		LoanContainer.getInstance().removeLoan(loan);
	}

	/**
	 * Returns a loan
	 *
	 * @param loan - loan to be returned
	 * @param returnDate - new return date
	 */
	public void returnLoan(Loan loan, LocalDateTime returnDate, Shelf shelf) {
		// Set return date
		loan.setReturnDate(returnDate);
		// Put back into stock (a shelf)
		stockCtrl.putItem(loan.getItem(), shelf);
	}

	/**
	 *  Update the datetime that the item is supposed to be returned at.
	 *
	 * @param loan - loan to be updated
	 * @param proposedReturnDate - the new proposed return date
	 */
	public void updateProposedReturnDate(Loan loan, LocalDateTime proposedReturnDate) {
		loan.setProposedReturnDate(proposedReturnDate);
	}

	/**
	 * Return date is valid.
	 *
	 * @param returnDate the return date
	 * @return true, if successful
	 */
	public boolean returnDateIsValid(LocalDateTime returnDate) {
		return returnDate.isAfter(returnDate);
	}
	
	/**
	 * Calculates the current price for yet to be loan.
	 *
	 * @param product the product
	 * @param customer the customer
	 * @param from the from
	 * @param to the to
	 * @return the price
	 */
	public BigDecimal getPrice(Product product, Customer customer, LocalDateTime from, LocalDateTime to) {
		BigDecimal pricePerMinute = product.getLatestLoaningPrice().divide(BigDecimal.valueOf(60), 10, RoundingMode.HALF_UP);
		long minutes = Duration.between(from, to).toMinutes();
		BigDecimal subtotal = pricePerMinute.multiply(BigDecimal.valueOf(minutes));
		// return with customer discount applied
		return subtotal.multiply(BigDecimal.valueOf((100 - customer.getCustomerType().getDiscountPercentage()) / 100.0));
	}
	
	/**
	 * Calculates the price for a loan with a specific return date
	 * 
	 * @param loan
	 * @param returnDate
	 * @return the price
	 */
	public BigDecimal getPrice(Loan loan, LocalDateTime returnDate) {
		// If return date is before proposed return date, return proposed price
		if (returnDate.isBefore(loan.getProposedReturnDate())) {
			return loan.getProposedPrice();
		}
		BigDecimal pricePerMinute = loan.getLoaningPricePerHour().divide(BigDecimal.valueOf(60), 10, RoundingMode.HALF_UP);
		long minutes = Duration.between(loan.getCreationDate(), returnDate).toMinutes();
		BigDecimal subtotal = pricePerMinute.multiply(BigDecimal.valueOf(minutes));
		// return with customer discount applied
		return subtotal.multiply(BigDecimal.valueOf((100 - loan.getCustomerTypeDiscountPercentage()) / 100.0));
	}
	
	/**
	 * Gets the difference between proposed price and actual (with the actual return date)
	 * 
	 * @return the price
	 */
	public BigDecimal getOverduePrice(Loan loan, LocalDateTime returnDate) {
		if (returnDate.isBefore(loan.getProposedReturnDate())) {
			return new BigDecimal(0);
		} else {
			return this.getPrice(loan, returnDate).subtract(loan.getProposedPrice());
		}
	}
}