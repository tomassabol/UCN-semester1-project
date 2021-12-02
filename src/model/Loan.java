/**
 * 
 */
package model;

import java.time.LocalDateTime;

public class Loan {
    /**
     * Fields for class loan
     */
    private LocalDateTime creationDate;
    private LocalDateTime returnDate;

    /**
     * Constructor for class Loan
     */
    public Loan(LocalDateTime creationDate){
        this.creationDate = creationDate;
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
    public LocalDateTime getReturnDate(){
        return returnDate;
    }

    /**
     * Setter for return date
     * @param returnDate The new date to set returnDatre to
     */
    public void setReturnDate(LocalDateTime returnDate){
        this.returnDate = returnDate;
    }
}
