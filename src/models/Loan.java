package models;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Duration;
import java.time.LocalDateTime;

public class Loan {
	
    public final int ID;
    private final LocalDateTime CREATION_DATE;
    private final IFCustomer CUSTOMER;
    private final IFEmployee EMPLOYEE;
    private final TrackableItem ITEM;
	public final String CUSTOMER_TYPE;
	public final int CUSTOMER_TYPE_DISCOUNT_PERCENTAGE;
	public final BigDecimal LOANING_PRICE_PER_HOUR;
    private LocalDateTime proposedReturnDate;
    private LocalDateTime returnDate;
	
	public Loan(int id, LocalDateTime proposedReturnDate, IFCustomer customer, IFEmployee employee, TrackableItem item){
		this.ID = id;
        this.CREATION_DATE = LocalDateTime.now();
        this.proposedReturnDate = proposedReturnDate;
        this.CUSTOMER = customer;
        this.EMPLOYEE = employee;
        this.ITEM = item;

        this.CUSTOMER_TYPE = customer.getCustomerType().getName();
        this.CUSTOMER_TYPE_DISCOUNT_PERCENTAGE = customer.getCustomerType().getDiscountPercentage();
        this.LOANING_PRICE_PER_HOUR = item.getProduct().getLatestLoaningPrice();
        this.returnDate = null;
    }
	
	/**
	 * Gets the proposed price.
	 *
	 * @return the proposed price
	 */
	public BigDecimal getProposedPrice() {
		BigDecimal pricePerMinute = this.LOANING_PRICE_PER_HOUR.divide(BigDecimal.valueOf(60), 10, RoundingMode.HALF_UP);
		long minutes = Duration.between(this.CREATION_DATE, this.proposedReturnDate).toMinutes();
		BigDecimal subtotal = pricePerMinute.multiply(BigDecimal.valueOf(minutes));
		// return with customer discount applied
		return subtotal.multiply(BigDecimal.valueOf((100 - this.CUSTOMER_TYPE_DISCOUNT_PERCENTAGE) / 100.0));
	}
	
	/**
	 * Gets the price after the item has been returned
	 *
	 * @return the actual price
	 */
	public BigDecimal getActualPrice() {
		if (returnDate == null) {
			return null;
		}
		// If return date < proposedReturn date, return proposed return date's cost
		if (this.returnDate.isBefore(this.proposedReturnDate)) {
			return this.getProposedPrice();
		}
		BigDecimal pricePerMinute = this.LOANING_PRICE_PER_HOUR.divide(BigDecimal.valueOf(60), 10, RoundingMode.HALF_UP);
		long minutes = Duration.between(this.CREATION_DATE, this.returnDate).toMinutes();
		BigDecimal subtotal = pricePerMinute.multiply(BigDecimal.valueOf(minutes));
		// return with customer discount applied
		return subtotal.multiply(BigDecimal.valueOf((100 - this.CUSTOMER_TYPE_DISCOUNT_PERCENTAGE) / 100.0));
	}
	
	/**
	 * Gets the proposed price or actual price if returned
	 *
	 * @return the price
	 */
	public BigDecimal getPrice() {
		if (returnDate == null) {
			return this.getProposedPrice();
		} else {
			return this.getActualPrice();
		}
	}
	
	public boolean isReturned() {
		return this.returnDate != null;
	}


    public LocalDateTime getProposedReturnDate() {
		return proposedReturnDate;
	}


	public void setProposedReturnDate(LocalDateTime proposedReturnDate) {
		this.proposedReturnDate = proposedReturnDate;
	}


	public LocalDateTime getReturnDate() {
		return returnDate;
	}


	public void setReturnDate(LocalDateTime returnDate) {
		this.returnDate = returnDate;
	}


	public String getCustomerType() {
		return CUSTOMER_TYPE;
	}


	public int getCustomerTypeDiscountPercentage() {
		return CUSTOMER_TYPE_DISCOUNT_PERCENTAGE;
	}

	public BigDecimal getLoaningPricePerHour() {
		return LOANING_PRICE_PER_HOUR;
	}


	public int getID() {
		return ID;
	}


	public LocalDateTime getCreationDate() {
		return CREATION_DATE;
	}


	public IFCustomer getCustomer() {
		return CUSTOMER;
	}


	public IFEmployee getEmployee() {
		return EMPLOYEE;
	}


	public TrackableItem getItem() {
		return ITEM;
	}

}
