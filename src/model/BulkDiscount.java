package model;

public class BulkDiscount {
    private int minQuantity;
    private int percentageDiscount;
    private boolean active;

    /**
     * Constructor class BulkDiscount
     * @param minQuantity - minimal quantity needed to get bulk discount
     * @param percentageDiscount - % of a discount
     */
    public BulkDiscount(int minQuantity, int percentageDiscount) {
        this.minQuantity = minQuantity;
        this.percentageDiscount = percentageDiscount;
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

    // get/set percentageDiscount
    public int getPercentageDiscount() {
        return this.percentageDiscount;
    }

    public void setPercentageDiscount(int percentageDiscount) {
        this.percentageDiscount = percentageDiscount;
    }


}
