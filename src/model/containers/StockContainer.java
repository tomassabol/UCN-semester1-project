package model.containers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import model.OrderLine;
import model.Product;
import model.Quote;
import model.QuoteItemLine;
import model.Shelf;
import model.StockBatch;
import model.StorageLocation;
import model.TrackableItem;

/**
 * The Class StorageContainer.
 */
public class StockContainer {
    
    /** The instance. */
    private static StockContainer instance;
    
    /** The storage. */
    private Map<StorageLocation, ArrayList<Shelf>> storage;

    /**
     * Constructor class StorageContainer.
     */
    private StockContainer() {
        storage = new HashMap<>();
    }
   

    /**
     * get instance of the class ShelfContainer.
     *
     * @return instance of a class shelfContainer
     */
    public static StockContainer getInstance() {
        if (instance == null) {
            instance = new StockContainer();
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
    
    /**
     * Gets the buyable quantity of a product in stock.
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
     * Gets the loanable quantity of a product in stock.
     *
     * @param product the product
     * @return the quantity in stock
     */
    public int getLoanableQuantityInStock(Product product) {
    	int quantityInStock = 0;
    	for(Shelf shelf: this.getShelves()) {
    		quantityInStock += shelf.getLoanableQuantity(product);
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
     * @return OrderLine the order line with the removed items
     */
    public OrderLine stockToOrderlineBuyable(Product product, int quantity) {
    	int removedUntrackableItemQuantity = 0;
    	Set<TrackableItem> removedTrackableBuyableItems = new HashSet<>();
    	int removedTotalquantity = 0;
    	
    	for (Shelf shelf: this.getShelves()) {
    		Iterator<StockBatch> stockBatchIt = shelf.getStockBatches(product).iterator();
    		while (stockBatchIt.hasNext()) {
    			StockBatch stockBatch = stockBatchIt.next();
    			
    			// remove untrackable, buyable
    			if (removedTotalquantity < quantity) {
    				removedUntrackableItemQuantity += stockBatch.popUntrackableBuyableItems(quantity - removedTotalquantity);
    				// update the removed total quantity
    				removedTotalquantity = removedUntrackableItemQuantity + removedTrackableBuyableItems.size();
    			}
    			
    			// remove trackable, buyable
    			if (removedTotalquantity < quantity) {
    				removedTrackableBuyableItems.addAll(stockBatch.popTrackableBuyableItems(quantity - removedTotalquantity));
    				// update the removed total quantity
    				removedTotalquantity = removedUntrackableItemQuantity + removedTrackableBuyableItems.size();
    			}
    			
    			// remove stock batch if empty
    			if (stockBatch.getTotalQuantity() == 0) {
    				stockBatchIt.remove();
    			}
    			System.out.println();
    		}

    	}
    	
    	
    	
    	if (removedTotalquantity <= 0) {
    		return null;
    	}
    	// Create an orderline from the removed stock items and return it
    	return new OrderLine(product, removedUntrackableItemQuantity, 
    			removedTrackableBuyableItems, product.getLatestSellingPrice(), 
    			product.getBestBulkDiscount(quantity));
    	

 
    }
    
    public boolean quoteIsInStock(Quote quote) {
    	for(QuoteItemLine itemLine: quote.getItemLines()) {
    		if (this.getBuyableQuantityInStock(itemLine.getPRODUCT()) < itemLine.getQuantity()) {
    			return false;
    		}
    	}
    	return true;
    }
}
