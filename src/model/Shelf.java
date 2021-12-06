package model;

import java.util.ArrayList;
import java.util.HashMap;

// TODO: Auto-generated Javadoc
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
    	return this.stockBatches.get(product);
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
    		availableQuantity += stockBatch.getQuantity();
    		if (availableQuantity >= neededQuantity) {
    			return true;
    		}
    	}
    	return false;
    }
    
    /**
     * Gets the quantity of a product in the shelf.
     *
     * @param product the product
     * @return the quantity of product
     */
    public int getQuantityOfProduct(Product product) {
    	int availableQuantity = 0;
    	for (StockBatch stockBatch: this.getStockBatches(product)) {
    		availableQuantity += stockBatch.getQuantity();
    	}
    	return availableQuantity;
    }

}
