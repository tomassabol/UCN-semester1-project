package models;

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
        if (discountPercentage < 0 || discountPercentage > 20) {
    		throw new IllegalArgumentException("Discount percentage must be between 0 and 20");
    	}
        if (minQuantity < 0) {
            throw new IllegalArgumentException("Bulk discount's quantity cannot be below 0");
        }
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
    		throw new IllegalArgumentException("Bulk discount's quantity cannot be below 0");
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
    	if (discountPercentage < 0 || discountPercentage > 20) {
    		throw new IllegalArgumentException("Discount percentage must be between 0 and 20");
    	}
        this.discountPercentage = discountPercentage;
    }


}
