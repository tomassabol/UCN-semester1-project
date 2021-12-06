package model;

public class TrackableItem {
    private int serialNumber;
    private Product product;
    private TRACKABLE_ITEM_TYPE trackableItemType;

    public enum TRACKABLE_ITEM_TYPE {
        LOANABLE,
        BUYABLE
    }
    
    /**
     * Constructor class 
     * @param serialNumber of a TrackableItem
     * @param trackableItemType - type of the trackable item
     * @param product - what product is the trackableItem of
     */
    public TrackableItem(int serialNumber, TRACKABLE_ITEM_TYPE trackableItemType, Product product) {
        this.serialNumber = serialNumber;
        this.trackableItemType = trackableItemType;
        this.product = product;
    }

    public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

    public int getSerialNumber() {
        return this.serialNumber;
    }

    public void setSerialNumber(int serialNumber) {
        this.serialNumber = serialNumber;
    }

    public TRACKABLE_ITEM_TYPE getTrackableItemType() {
        return this.trackableItemType;
    }

    public void setTrackableItemType(TRACKABLE_ITEM_TYPE trackableItemType) {
        this.trackableItemType = trackableItemType;
    }

}
