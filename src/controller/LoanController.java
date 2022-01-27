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
import model.container.LoanContainer;

public class LoanController {

    StockController stockCtrl;
    
    public LoanController() {
        stockCtrl = new StockController();
    }

    public List<Loan> getLoans() {
        return LoanContainer.getInstance().getLoans();
    }

    public List<Loan> getLoans(Customer customer) {
        return LoanContainer.getInstance().getLoansByCustomer(customer);
    }

    public Loan findLoanById(int loanId) {
        return LoanContainer.getInstance().findLoanByID(loanId);
    }

    public Loan findLoanByIdForCustomer(int loanId, Customer customer) {
        Loan loan = findLoanById(loanId);
        if (loan != null && loan.getCustomer() == customer) {
            return loan;
        }
        return null;
    }

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

    public void removeLoan(Loan loan) {
        LoanContainer.getInstance().removeLoan(loan);
    }

    public void updateReturnDate(Loan loan, LocalDate returnDate) {
        loan.setReturnDate(returnDate);
    }
    
    public long getDaysOfLoan(Loan loan) {
        long days = loan.getCreationDate().toLocalDate().until(loan.getReturnDate(), ChronoUnit.DAYS);
        return days;    
    }

    public BigDecimal totalPrice(Loan loan) {
        return BigDecimal.valueOf((int)getDaysOfLoan(loan) * loan.getLoaningPrice().intValue());
    }

    public boolean validateReturnDate(LocalDate creationDate,LocalDate returnDate) {
        if (returnDate.isBefore(creationDate) || returnDate.isEqual(creationDate)) {
            return false;
        } return true; 
    }
}
