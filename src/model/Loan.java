package model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class Loan {
    /**
     * Fields for class loan
     */
    public final int ID;
    private final LocalDateTime CREATION_DATE;
    private LocalDate returnDate;
    private boolean returned;
    private final IFCustomer CUSTOMER;
    private final IFEmployee EMPLOYEE;
    Product product;

	public final String CUSTOMER_TYPE;
	public final int CUSTOMER_TYPE_DISCOUNT_PERCENTAGE;
	public final BigDecimal LOANING_PRICE;

    /**
     * Constructor for class Loan
     */
    public Loan(int id, LocalDate returnDate, IFCustomer customer, IFEmployee employee, Product product){
        this.ID = id;
        this.CREATION_DATE = LocalDateTime.now();
        this.returnDate = returnDate;
        this.returned = false;
        this.CUSTOMER = customer;
        this.EMPLOYEE = employee;
        this.product = product;

        this.CUSTOMER_TYPE = customer.getCustomerType().getName();
        this.CUSTOMER_TYPE_DISCOUNT_PERCENTAGE = customer.getCustomerType().getDiscountPercentage();
        this.LOANING_PRICE = product.getLatestLoaningPrice();
    }

    /**
     * Getter for creationDate
     * @return the time the loan was created
     */
    public LocalDateTime getCreationDate(){
        return CREATION_DATE;
    }

    /**
     * Getter for returnDate
     * @return the date of the loan return
     */
    public LocalDate getReturnDate(){
        return returnDate;
    }

    /**
     * Setter for return date
     * @param returnDate The new date to set returnDatre to
     */
    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }

    public IFCustomer getCustomer() {
        return this.CUSTOMER;
    }


    public IFEmployee getEmployee() {
        return this.EMPLOYEE;
    }

    public Product getProduct() {
        return this.product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getID() {
        return ID;
    }

    public boolean isReturned() {
        return returned;
    }

    public boolean returnLoan() {
        returned = !false;
        return returned;
    }

    public BigDecimal getLoaningPrice() {
        return product.getLatestLoaningPrice();
    }

    public String getCustomerType() {
        return CUSTOMER_TYPE;
    }
    public int getCustomerTypeDiscountPercentage() {
        return CUSTOMER_TYPE_DISCOUNT_PERCENTAGE;
    }

}
