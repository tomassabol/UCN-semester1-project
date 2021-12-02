package model;

public class TrackableItem {
    private int serialNumber;
    private TrackableItemType trackableItemType;

    enum TrackableItemType {
        LOANABLE,
        BUYABLE
    }
    
    /**
     * Constructor class 
     * @param serialNumber
     * @param trackableItemType
     */
    public TrackableItem(int serialNumber, TrackableItemType trackableItemType) {
        this.serialNumber = serialNumber;
        this.trackableItemType = trackableItemType;
    }

    // get/set serialNumber
    public int getSerialNumber() {
        return this.serialNumber;
    }

    public void setSerialNumber(int serialNumber) {
        this.serialNumber = serialNumber;
    }

    // get/set trackableItemType
    public TrackableItemType getTrackableItemType() {
        return this.trackableItemType;
    }

    public void setTrackableItemType(TrackableItemType trackableItemType) {
        this.trackableItemType = trackableItemType;
    }

}
