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
     * Gets the shelves.
     *
     * @param storageLocation the storage location
     * @return the shelves
     */
    public ArrayList<Shelf> getShelves(StorageLocation storageLocation) {
    	return storage.get(storageLocation);
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
    
    // need this for buyable and loanable separately!
//    /**
//     * Checks if a product and specific quantity is in stock,
//     *
//     * @param product the product
//     * @param quantity the quantity
//     * @return true, if is in stock
//     */
//    public boolean isInStock(Product product, int quantity) {
//    	int totalQuantity = 0;
//    	for(Shelf shelf: this.getShelves()) {
//    		totalQuantity += shelf.getQuantityOfProduct(product);
//    		if (totalQuantity > quantity) {
//    			return true;
//    		}
//    	}
//    	return false;
//    }
    
    /**
     * Gets the quantity of a product in stock.
     *
     * @param product the product
     * @return the quantity in stock
     */
    public int getBuyableQuantityInStock(Product product) {
    	int quantityInStock = 0;
    	for(Shelf shelf: this.getShelves()) {
    		quantityInStock += shelf.getBuyableQuantity(product);
    	}
    	return quantityInStock;
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
     * takes a product & quantity, retrieves specific items,
     * then removes them from stock and returns an orderline
     *
     * @param product the product
     * @param quantity the quantity
     * @return the array list
     */
    public OrderLine removeFromstock(Product product, int quantity) {
    	int removedUntrackableItemQuantity = 0;
    	Set<TrackableItem> removedTrackableItems = new HashSet<>();
    	for (Shelf shelf: this.getShelves()) {
    		Iterator<StockBatch> shelfIt = shelf.getStockBatches(product).iterator();
    		while (shelfIt.hasNext()) {
    			// If Stock Batch doesn't cover the remaining quantity needed,
    			// or is equal:
    			// take the quantity and remove the stockBatch from shelf
    			StockBatch stockBatch = shelfIt.next();
    			if (stockBatch.getTotalQuantity() <= (quantity - (removedUntrackableItemQuantity + removedTrackableItems.size()))) {
    				removedUntrackableItemQuantity += stockBatch.getTotalQuantity();
    				removedTrackableItems.addAll(stockBatch.getTrackableItems());
    			} else {
    				// The untrackable items cover the needeed quantity
    				if (stockBatch.getUntrackableItemQuantity() >= (quantity - (removedUntrackableItemQuantity + removedTrackableItems.size()))) {
    					removedUntrackableItemQuantity = quantity;
    					stockBatch.setUntrackableItemQuantity((quantity - (removedUntrackableItemQuantity + removedTrackableItems.size())));
    				} else {
    					// Untrackable items don't cover needed quantity, 
    					// so start popping trackable items
    					removedUntrackableItemQuantity += stockBatch.getTotalQuantity();
    					removedTrackableItems.addAll(stockBatch.popTrackableItems(quantity - (removedUntrackableItemQuantity + removedTrackableItems.size())));
    					
    				}
    			}
    		}

    	}
    	// Create an orderline from the removed stock items and return it
    	return new OrderLine(product, removedUntrackableItemQuantity, 
    			removedTrackableItems, product.getLatestSellingPrice(), 
    			DiscountContainer.getInstance().getBestBulkDiscount(product, quantity));

 
    }

    // TODO: Remove these. don't print in model layer!
    public void printAllShelvesInfo() {
        for (Shelf shelf : getShelves()) {
            shelf.printShelfInfo();
        }
    }
    

}
