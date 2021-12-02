package model;

import java.math.BigDecimal;

public class SupplyOffer {
    private BigDecimal costPrice;
    private boolean active;
    private boolean isTrackable;

    /**
     * Constructor class SupplyOffer
     * @param costPrice - price of a supply
     * @param active - true if offer is still active
     * @param isTrackable - true if supply is trackable
     */
    private SupplyOffer(BigDecimal costPrice, boolean active, boolean isTrackable) {
        this.costPrice = costPrice;
        this.active = active;
        this.isTrackable = isTrackable;
    }

    // get/set costPrice
    public BigDecimal getCostPrice() {
        return this.costPrice;
    }

    public void setCostPrice(BigDecimal costPrice) {
        this.costPrice = costPrice;
    }

    // get/set isActive
    public boolean isActive() {
        return this.active;
    }

    public boolean getActive() {
        return this.active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    // set/get isTrackable
    public boolean isIsTrackable() {
        return this.isTrackable;
    }

    public boolean getIsTrackable() {
        return this.isTrackable;
    }

    public void setIsTrackable(boolean isTrackable) {
        this.isTrackable = isTrackable;
    }

}
