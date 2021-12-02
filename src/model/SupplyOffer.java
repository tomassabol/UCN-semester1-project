package model;

import java.math.BigDecimal;

public class SupplyOffer {
    public final int ID;
    private BigDecimal costPrice;
    private boolean active;
    private boolean isTrackable;

    /**
     * Constructor class SupplyOffer
     * @param costPrice - price of a supply
     * @param active - true if offer is still active
     * @param isTrackable - true if supply is trackable
     */
    private SupplyOffer(int id, BigDecimal costPrice, boolean active, boolean isTrackable, int contractorId) {
        this.ID = id;
        this.costPrice = costPrice;
        this.active = active;
        this.isTrackable = isTrackable;
        ContractorContainer.getInstance().findContractorById(contractorId);
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
