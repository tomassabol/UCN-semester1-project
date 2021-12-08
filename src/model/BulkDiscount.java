package model;

public class BulkDiscount {
    private int minQuantity;
    private int discountPercentage;
    private boolean active;

    /**
     * Constructor class BulkDiscount
     * @param minQuantity - minimal quantity needed to get bulk discount
     * @param discountPercentage - % of a discount
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

    public void setMinQuantity(int minQuantity) {
        this.minQuantity = minQuantity;
    }

    // get/set discountPercentage
    public int getDiscountPercentage() {
        return this.discountPercentage;
    }

    public void setDiscountPercentage(int discountPercentage) {
        this.discountPercentage = discountPercentage;
    }


}
