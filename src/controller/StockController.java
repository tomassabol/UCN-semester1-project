package controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import model.PrimaryKey;
import model.Product;
import model.Quote;
import model.Shelf;
import model.Stock;
import model.StorageLocation;

public class StockController {

    /**
     * Creates the storage location and adds it to the singleton container
     *
     * @param name the name
     * @param address the address
     * @param isAStore the is A store
     * @return the storage location
     */
    public StorageLocation createStorageLocation(String name, String address, boolean isAStore) {
        StorageLocation storageLocation = new StorageLocation(PrimaryKey.getNextStorageLocationID(), name, address, isAStore);
        Stock.getInstance().addStorageLocation(storageLocation);
        return storageLocation;
    }

    /**
     * Creates the shelf  and adds it to the singleton container
     *
     * @param name the name
     * @param storageLocation the storage location
     * @return the shelf
     */
    public Shelf createShelf(String name, StorageLocation storageLocation) {
        Shelf shelf = new Shelf(PrimaryKey.getNextShelfID(), name, storageLocation);
        Stock.getInstance().addShelf(storageLocation, shelf);
        return shelf;
    }



    /**
     * Finds the storage location by ID
     *
     * @param id the id
     * @return the storage location, or null
     */
    public StorageLocation findStorageLocationById(int id) {
        return Stock.getInstance().findStorageLocationById(id);
    }

    public Shelf findShelfById(int id) {
        return Stock.getInstance().findShelfByID(id);
    }
    
    public Shelf findShelfByIndex(StorageLocation storageLocation, int index) {
    	return Stock.getInstance().findShelfByIndex(storageLocation, index);
    }

    /**
     * Gets the storage locations.
     *
     * @return the storage locations
     */
    public Set<StorageLocation> getStorageLocations() {
        return Stock.getInstance().getStorageLocations();
    }

    /**
     * Gets all of the shelves.
     *
     * @return the shelves
     */
    public ArrayList<Shelf> getShelves() {
        return Stock.getInstance().getShelves();
    }
    
    /**
     * Gets the shelves.
     *
     * @param storageLocation the storage location
     * @return the shelves
     */
    public List<Shelf> getShelves(StorageLocation storageLocation) {
    	return Stock.getInstance().getShelves(storageLocation);
    }
    
    /**
     * Returns the buyable item quantity for a product
     *
     * @param product the product
     * @return int the buyable item quantity
     */
    public int getBuyableQuantityInStock(Product product) {
    	return Stock.getInstance().getBuyableQuantityInStock(product);
    }
    
    /**
     * Returns the Loanable item quantity for a product
     *
     * @param product the product
     * @return int the loanable item quantity
     */
    public int getLoanableQuantityInStock(Product product) {
    	return Stock.getInstance().getLoanableQuantityInStock(product);
    }
    
    
    /**
     * Checks that a product is in stock (buyable)
     *
     * @param product the product
     * @return true, if in stock
     */
    public boolean buyableItemIsInStock(Product product) {
    	return getBuyableQuantityInStock(product) > 0;
    }
    
    public boolean quoteIsInStock(Quote quote) {
    	return Stock.getInstance().quoteIsInStock(quote);
    }
    
    public void removeStorageLocation(StorageLocation storageLocation) {
    	Stock.getInstance().removeStorageLocation(storageLocation);
    }
    
}
