package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * The Class StorageContainer.
 */
public class Stock {
    
    /** The instance. */
    private static Stock instance;
    
    /** The storage. */
    private Map<StorageLocation, ArrayList<Shelf>> storage;

    /**
     * Constructor class StorageContainer.
     */
    private Stock() {
        storage = new HashMap<>();
    }
   

    /**
     * get instance of the class ShelfContainer.
     *
     * @return instance of a class shelfContainer
     */
    public static Stock getInstance() {
        if (instance == null) {
            instance = new Stock();
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
    public ArrayList<Shelf> getShelves(Stock storageContainer) {
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
    
    public StorageLocation findStorageLocationById(int id) {
        for (StorageLocation storageLocation : this.storage.keySet()) {
            if (storageLocation.ID == id) {
                return storageLocation;
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
    
    /**
     * Checks if a product and specific quantity is in stock,
     *
     * @param product the product
     * @param quantity the quantity
     * @return true, if is in stock
     */
    public boolean isInStock(Product product, int quantity) {
    	int totalQuantity = 0;
    	for(Shelf shelf: this.getShelves()) {
    		totalQuantity = shelf.getQuantityOfProduct(product);
    		if (totalQuantity > quantity) {
    			return true;
    		}
    	}
    	return false;
    }
    
    /**
     * Gets All stock batches for a particular product
     *
     * @param product the product
     * @return the stock batches
     */
    public ArrayList<StockBatch> getStockBatches(Product product) {
    	ArrayList<StockBatch> stockBatches = new ArrayList<>();
    	for(Shelf shelf: this.getShelves()) {
    		stockBatches.addAll(shelf.getStockBatches(product));
    	}
    	return stockBatches;
    	
    }
    
    /**
     * takes an unspecific item line, retrieves specific items,
     * then removes them from stock and returns an orderline
     *
     * @param product the product
     * @param quantity the quantity
     * @return the array list
     */
    public OrderLine removeFromstock(Product product, int quantity) {
    	int removedUntrackableItems = 0;
    	Set<TrackableItem> removedTrackableItems = new HashSet<>();
    	for (Shelf shelf: this.getShelves()) {
    		Iterator<StockBatch> shelfIt = shelf.getStockBatches(product).iterator();
    		while (shelfIt.hasNext()) {
    			// If Stock Batch doesn't cover the remaining quantity needed,
    			// or is equal:
    			// take the quantity and remove the stockBatch from shelf
    			StockBatch stockBatch = shelfIt.next();
    			if (stockBatch.getQuantity() <= (quantity - (removedUntrackableItems + removedTrackableItems.size()))) {
    				removedUntrackableItems += stockBatch.getQuantity();
    				removedTrackableItems.addAll(stockBatch.getTrackableItems());
    			} else {
    				// The untrackable items cover the needeed quantity
    				if (stockBatch.getUntrackableItemquantity() >= (quantity - (removedUntrackableItems + removedTrackableItems.size()))) {
    					removedUntrackableItems = quantity;
    					stockBatch.setUntrackableItemquantity((quantity - (removedUntrackableItems + removedTrackableItems.size())));
    				} else {
    					// Untrackable items don't cover needed quantity, 
    					// so start popping trackable items
    					removedUntrackableItems += stockBatch.getQuantity();
    					removedTrackableItems.addAll(stockBatch.popTrackableItems(quantity - (removedUntrackableItems + removedTrackableItems.size())));
    					
    				}
    			}
    		}

    	}
    	OrderLine orderLine = new OrderLine(product, removedUntrackableItems);
    	orderLine.setTrackableItems(removedTrackableItems);
    	// set discount
    	orderLine.setBulkDiscount(DiscountContainer.getInstance().getBestBulkDiscount(product, quantity));
    	return orderLine;
    }

    /**
     * prints out all storage lcoation and its info
     */
    public void printAllStorageLocationInfo() {
        for (StorageLocation storageLocation : this.storage.keySet()) {
            storageLocation.printStorageLocationInfo();
        }
    }

    /**
     * prints out all shelves and its info
     */
    public void printAllShelvesInfo() {
        for (Shelf shelf : getShelves()) {
            shelf.printShelfInfo();
        }
    }
    

}