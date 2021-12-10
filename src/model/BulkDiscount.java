package model;

public class BulkDiscount {
    private int minQuantity;
    private int discountPercentage;
    private boolean active;

    /**
     * Constructor class BulkDiscount
     * @param minQuantity - minimal quantity needed to get bulk discount
     * @param discountPercentage - % of a discount
     * 
     * @exception IllegalArgumentException if minQuantity < 0
     * @exception discountPercentage < 0
     * @exception discountPercentage > 100
     */
    public BulkDiscount(int minQuantity, int discountPercentage) {
        this.minQuantity = minQuantity;
        this.discountPercentage = discountPercentage;
        this.active = true;
    }
    
    public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	// get/set minQuantity
    public int getMinQuantity() {
        return this.minQuantity;
    }

    /**
     * Sets the min quantity for when bulk discount gets applied.
     *
     * @param minQuantity the new min quantity
     * 
     * @exception discountPercentage < 0
     * @exception discountPercentage > 100
     */
    public void setMinQuantity(int minQuantity) {
    	if (minQuantity < 0) {
    		throw new IllegalArgumentException("Bulk discount's minimum quantity cannot be below 0");
    	}
        this.minQuantity = minQuantity;
    }

    // get/set discountPercentage
    public int getDiscountPercentage() {
        return this.discountPercentage;
    }

    /**
     * Sets the discount percentage for when bulk discount gets applied
     *
     * @param discountPercentage the discount percentage
     * 
     * @exception discountPercentage < 0
     * @exception discountPercentage > 100
     */
    public void setDiscountPercentage(int discountPercentage) {
    	if (discountPercentage < 0 || discountPercentage > 100) {
    		throw new IllegalArgumentException("Discount percentage must be between 0 and 100");
    	}
        this.discountPercentage = discountPercentage;
    }


}
