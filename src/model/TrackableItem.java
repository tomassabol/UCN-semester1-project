package model;

public class TrackableItem {
    private int serialNumber;
    private TrackableItemType trackableItemType;
    private Product product;

    enum TrackableItemType {
        LOANABLE,
        BUYABLE
    }
    
    /**
     * Constructor class 
     * @param serialNumber
     * @param trackableItemType
     */
    public TrackableItem(int serialNumber, TrackableItemType trackableItemType, Product product) {
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

    public TrackableItemType getTrackableItemType() {
        return this.trackableItemType;
    }

    public void setTrackableItemType(TrackableItemType trackableItemType) {
        this.trackableItemType = trackableItemType;
    }

}
