package model;

import java.math.BigDecimal;
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
	
	public BigDecimal getProposedPrice() {
		BigDecimal pricePerMinute = this.LOANING_PRICE_PER_HOUR.divide(BigDecimal.valueOf(60));
		long minutes = Duration.between(this.CREATION_DATE, this.proposedReturnDate).toMinutes();
		return pricePerMinute.multiply(BigDecimal.valueOf(minutes));
	}
	
	public BigDecimal getActualPrice() {
		if (returnDate == null) {
			return null;
		}
		BigDecimal pricePerMinute = this.LOANING_PRICE_PER_HOUR.divide(BigDecimal.valueOf(60));
		long minutes = Duration.between(this.returnDate, this.proposedReturnDate).toMinutes();
		return pricePerMinute.multiply(BigDecimal.valueOf(minutes));
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


	public int getCustomerTypeDiscocuntPercentage() {
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
