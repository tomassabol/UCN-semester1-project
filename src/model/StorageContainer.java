package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

// TODO: Auto-generated Javadoc
/**
 * The Class StorageContainer.
 */
public class StorageContainer {
    
    /** The instance. */
    private static StorageContainer instance;
    
    /** The storage. */
    private Map<StorageLocation, ArrayList<Shelf>> storage;

    /**
     * Constructor class StorageContainer.
     */
    private StorageContainer() {
        storage = new HashMap<>();
    }
   

    /**
     * get instance of the class ShelfContainer.
     *
     * @return instance of a class shelfContainer
     */
    public static StorageContainer getInstance() {
        if (instance == null) {
            instance = new StorageContainer();
        }
        return instance;
    }
    
    
    /**
     * Gets the storage locations.
     *
     * @return the storage locations
     */
    public Set<StorageLocation> getStorageLocations() {
    	return storage.keySet();
    }
    
    /**
     * Gets all shelves
     *
     * @return the shelves
     */
    public ArrayList<Shelf> getShelves() {
    	ArrayList<Shelf> allShelves = new ArrayList<>();
    	for (StorageLocation storageLocation: storage.keySet()) {
    		allShelves.addAll(storage.get(storageLocation));
    	}
    	return allShelves;
    }
    
    /**
     * Adds storage location to the container.
     *
     * @param storageLocation the storage location
     */
    public void addStorageLocation(StorageLocation storageLocation) {
    	storage.put(storageLocation, new ArrayList<Shelf>());
    }
    
    /**
     * Adds shelf to a storage location.
     *
     * @param storageLocation the storage location
     * @param shelf the shelf
     */
    public boolean addShelf(StorageLocation storageLocation, Shelf shelf) {
    	return storage.get(storageLocation).add(shelf);
    }
    
    
    /**
     * Gets the shelves for a storage location.
     *
     * @param storageContainer the storage container
     * @return the shelves
     */
    public ArrayList<Shelf> getShelves(StorageContainer storageContainer) {
    	return storage.get(storageContainer);
    }
    
    /**
     * Find shelf by index.
     *
     * @param storageLocation the storage location
     * @param index the index
     * @return Shelf the shelf, else null
     * Complexity: O(n)
     */
    public Shelf findShelfByIndex(StorageLocation storageLocation, int index) {
    	try {
    		return this.storage.get(storageLocation).get(index);
    	} catch (IndexOutOfBoundsException e) {
    		return null;
    	}
    }
    
    /**
     * Find shelf by its ID.
     *
     * @param storageLocation the storage location
     * @param id the id
     * @return the shelf
     * Complexity O(n)
     */
    public Shelf findShelfByID(StorageLocation storageLocation, int id) {
    	for (Shelf shelf: storage.get(storageLocation)) {
    		if (shelf.ID == id) {
    			return shelf;
    		}
    	}
    	return null;
    } 
    
    /**
     * Find shelf by its ID.
     *
     * @param id the id
     * @return the shelf
     * Complexity O(n^2)
     */
    public Shelf findShelfByID(int id) {
    	for (Shelf shelf: this.getShelves()) {
    		if (shelf.ID == id) {
    			return shelf;
    		}
    	}
    	return null;
    } 
    
    

    /**
     * remove storageLocation
     * @param storageLocation - to be removed
     */
    public void removeStorageLocation(StorageLocation storageLocation) {
    	storage.remove(storageLocation);
    }
    
    /**
     * remove specific shelf
     * @param shelf to be removed
     */
    public void removeShelf(Shelf shelf) {
        this.getShelves().remove(shelf);
    }
    
    /**
     * remove specific shelf from a specific storageLocation
     * @param storageLocation - specific storageLocation
     * @param shelf - specific shelf to be removed
     */
    public void removeShelf(StorageLocation storageLocation, Shelf shelf) {
        storage.get(storageLocation).remove(shelf);
    }
    

}
