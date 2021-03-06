package models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * The Class Shelf.
 */
public class Shelf {
    
    /** The id. */
    public final int ID;
    
    /** The name. */
    private String name;
    
    /** The storage location. */
    private StorageLocation storageLocation;
    
    /** The stock batches. */
    private HashMap<Product, ArrayList<StockBatch>> stockBatches;

    /**
     * Constructor class Shelf.
     *
     * @param id of a new shelf
     * @param name of the shelf
     * @param storageLocation of the shelf
     */
    public Shelf(int id, String name, StorageLocation storageLocation) {
        this.ID = id;
        this.name = name;
        this.storageLocation = storageLocation;

        stockBatches = new HashMap<>();
    }
    
    public boolean removeStockBatch(Product product, StockBatch removableStockBatch) {
    	Iterator<StockBatch> stockBatchIt = this.getStockBatches(product).iterator();
    	while (stockBatchIt.hasNext()) {
    		StockBatch stockBatch = stockBatchIt.next();
    		if (stockBatch == removableStockBatch) {
    			stockBatchIt.remove();
    			return true;
    		}
    	}
    	return false;
    }

    /**
     * Gets the name.
     *
     * @return the name
     */
    // get/set name
    public String getName() {
        return this.name;
    }

    /**
     * Sets the name.
     *
     * @param name the new name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the storage location.
     *
     * @return the storage location
     */
    public StorageLocation getStorageLocation() {
        return this.storageLocation;
    }
    
    public void setStorageLocation(StorageLocation storageLocation) {
    	this.storageLocation = storageLocation;
    }

    /**
     * Adds the stock batch to a shelf.
     *
     * @param product the product
     * @param stockBatch the stock batch
     * @return true, if successful
     */
    public boolean addStockBatch(Product product, StockBatch stockBatch) {
    	this.stockBatches.putIfAbsent(product, new ArrayList<>());
        return stockBatches.get(product).add(stockBatch);
    }
    
    /**
     * Gets the stock batches.
     *
     * @param product the product
     * @return the stock batches
     */
    public ArrayList<StockBatch> getStockBatches(Product product) {
    	if (stockBatches.containsKey(product))
    		return this.stockBatches.get(product);
    	return new ArrayList<StockBatch>();
    }
    
    /**
     * Checks if a product and specific quantity is in stock.
     *
     * @param product the product
     * @param neededQuantity the needed quantity
     * @return true, if is in stock
     */
    public boolean isInStock(Product product, int neededQuantity) {
    	int availableQuantity = 0;
    	for (StockBatch stockBatch: this.getStockBatches(product)) {
    		availableQuantity += stockBatch.getTotalQuantity();
    		if (availableQuantity >= neededQuantity) {
    			return true;
    		}
    	}
    	return false;
    }
    
    /**
     * Gets the buyable quantity in stock
     *
     * @param product the product
     * @return the buyable quantity
     */
    public int getBuyableQuantity(Product product) {
		int availableQuantity = 0;
		for (StockBatch stockBatch: this.getStockBatches(product)) {
			availableQuantity += stockBatch.getBuyableItemQuantity();
		}
		return availableQuantity;
    }
    
    /**
     * Gets the loanable quantity in stock
     *
     * @param product the product
     * @return the loanable quantity
     */
    public int getLoanableQuantity(Product product) {
		int availableQuantity = 0;
		for (StockBatch stockBatch: this.getStockBatches(product)) {
			availableQuantity += stockBatch.getLoanableItemQuantity();
		}
		return availableQuantity;
    }

    public List<StockBatch> getStockBatches() {
    	return 
		    this.stockBatches.values().stream()
		        .flatMap(List::stream)
		        .collect(Collectors.toList());
    }
}
