package model;

import java.util.ArrayList;
import java.util.List;

public class StockContainer {
    private ArrayList<StorageLocation> storageLocations;

    /**
     * Constructor class StockContainer
     */
    public StockContainer() {
        storageLocations = new ArrayList<>();
    }

    /**
     * @param storageLocation to be added to an ArrayList
     * @return true if successfully added
     */
    public boolean addStorageLocation(StorageLocation storageLocation) {
        return storageLocations.add(storageLocation);
    }

    /**
     * @return list of all StorageLocations
     */
    public List<StorageLocation> getStorageLocations() {
        return this.storageLocations;
    }

    /**
     * @param storageLocation to be removed from an ArrayList
     * @return true if successfully removed
     */
    public boolean removeStorageLocation(StorageLocation storageLocation) {
        return storageLocations.remove(storageLocation);
    }

    /**
     * @param id - of a specific StorageLocation to be found
     * @return StorageLocation with specific id
     */
    public StorageLocation findStorageLocationById(int id) {
        for (StorageLocation storageLocation : storageLocations) {
            if (storageLocation.ID == id) {
                return storageLocation;
            }
        }
        return null;
    }
    
    public boolean isInStock(Product product, int quantity) {
    	// Ask each storage location if in stock
//    	for(shelf.)
    	return false;
    }

}
