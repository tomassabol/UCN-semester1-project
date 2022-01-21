package model;

import java.time.LocalDateTime;

public class Loan {
    /**
     * Fields for class loan
     */
    public final int ID;
    private LocalDateTime creationDate;
    private LocalDateTime returnDate;
    private boolean returned;
    IFCustomer customer;
    IFEmployee employee;
    Product product;

    /**
     * Constructor for class Loan
     */
    public Loan(int id, LocalDateTime creationDate, IFCustomer customer, IFEmployee employee, Product product){
        this.ID = id;
        this.creationDate = creationDate;
        this.returned = false;
        this.customer = customer;
        this.employee = employee;
        this.product = product;
        returnDate = null;
    }

    /**
     * Getter for creationDate
     * @return the time the loan was created
     */
    public LocalDateTime getCreationDate(){
        return creationDate;
    }

    /**
     * Setter for creationDate
     * @param creationDate the new date to set creationDate to
     */
    public void setCreationDate(LocalDateTime creationDate){
        this.creationDate = creationDate;
    }

    /**
     * Getter for return date
     * @return the date the item was retured
     */
    public LocalDateTime getReturnDate() {
        return returnDate;
    }

    /**
     * Setter for return date
     * @param returnDate The new date to set returnDatre to
     */
    public void setReturnDate(LocalDateTime returnDate) {
        this.returnDate = returnDate;
    }

    public IFCustomer getCustomer() {
        return this.customer;
    }

    public void setCustomer(IFCustomer customer) {
        this.customer = customer;
    }


    public IFEmployee getEmployee() {
        return this.employee;
    }

    public void setEmployee(IFEmployee employee) {
        this.employee = employee;
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

}
