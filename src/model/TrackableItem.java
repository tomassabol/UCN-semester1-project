package model;

public class TrackableItem {
    private int serialNumber;
    private TrackableItemType trackableItemType;
    private Product product;
    private Shelf shelf;

    enum TrackableItemType {
        LOANABLE,
        BUYABLE
    }
    
    /**
     * Constructor class 
     * @param serialNumber of a TrackableItem
     * @param trackableItemType - type of the trackable item
     * @param product - what product is the trackableItem of
     * @param shelf - where is it stored
     */
    public TrackableItem(int serialNumber, TrackableItemType trackableItemType, Product product, Shelf shelf) {
        this.serialNumber = serialNumber;
        this.trackableItemType = trackableItemType;
        this.product = product;
        this.shelf = shelf;
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

    public Shelf getShelf() {
        return this.shelf;
    }

}
