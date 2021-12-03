package model;

public class StorageLocation {
    public final int ID;
    private String name;
    private String address;
    private boolean isAStore;

    /**
     * Constructor Class StorageLocations
     * @param id of a storageLocation
     * @param name of the storageLocation
     * @param address storageLocation
     * @param isAStore if the location is a store
     */
    public StorageLocation(int id, String name, String address, boolean isAStore) {
        this.ID = id;
        this.name = name;
        this.address = address;
        this.isAStore = isAStore;
    }


    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public boolean getIsAStore() {
        return this.isAStore;
    }

    public void setIsAStore(boolean isAStore) {
        this.isAStore = isAStore;
    }


}
