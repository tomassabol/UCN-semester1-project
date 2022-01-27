package model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class Loan {

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
     * @param id - loan id
     * @param returnDate - date when the loan is supposed to be returned
     * @param customer - customer to which is loan assigned
     * @param employee - employee that created this loan
     * you can loan only 1 item per loan, so quantity is always == 1
     * @param product - product which is loaned
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
     * @return the date and time when the loan was created
     */
    public LocalDateTime getCreationDate(){
        return CREATION_DATE;
    }

    /**
     * Getter for returnDate
     * @return the date when the loan is supposed to be returned
     */
    public LocalDate getReturnDate(){
        return returnDate;
    }

    /**
     * Setter for return date
     * @param returnDate The new date which replaces current returnDate
     */
    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }

    /**
     * Getter for CUSTOMER
     * @return the customer to which is this loan assigned
     */
    public IFCustomer getCustomer() {
        return this.CUSTOMER;
    }


    /**
     * Getter for EMPLOYEE
     * @return the employee which created this loan
     */
    public IFEmployee getEmployee() {
        return this.EMPLOYEE;
    }

    /**
     * Getter for product
     * @return the product which loaned to the customer
     * customer can loan only 1 item at a time
     */
    public Product getProduct() {
        return this.product;
    }

    /**
     * Setter for product
     * @return the product which loaned to the customer
     * customer can loan only 1 item at a time
     */
    public void setProduct(Product product) {
        this.product = product;
    }

    /**
     * Getter for ID
     * @return the ID of this loan
     */
    public int getID() {
        return ID;
    }

    /**
     * Getter for the status of the loan
     * @return the loan status
     * true == loan was returned
     * false == loan was not yet returned
     */
    public boolean isReturned() {
        return returned;
    }

    /**
     * This method changes status of variable isReturned to true
     * This method is used, after the loan was returned
     * @return true == loan was returned
     */
    public boolean returnLoan() {
        returned = !false;
        return returned;
    }

    /**
     * Getter for loaning price
     * @return the loaning price 
     * this price is a price per day
     * this price is the latest loaning price of a product, at a time this loan was created
     * change of loaning price will not affect the price of loan that was created before the price change
     */
    public BigDecimal getLoaningPrice() {
        return LOANING_PRICE;
    }

    /**
     * Getter for the customer type of the customer to which is this loan assigned
     * @return customer type
     */
    public String getCustomerType() {
        return CUSTOMER_TYPE;
    }

    /**
     * Getter for the discount percentage of the customer type
     * @return customer type
     */
    public int getCustomerTypeDiscountPercentage() {
        return CUSTOMER_TYPE_DISCOUNT_PERCENTAGE;
    }

}
