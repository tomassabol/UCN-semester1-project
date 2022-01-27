package controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import model.PrimaryKey;
import model.Product;
import model.Quote;
import model.Shelf;
import model.StockBatch;
import model.StorageLocation;
import models.container.StockContainer;

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
        StorageLocation storageLocation = new StorageLocation(PrimaryKey.getID(PrimaryKey.Keys.STORAGE_LOCATION),
        		name, address, isAStore);
        StockContainer.getInstance().addStorageLocation(storageLocation);
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
        Shelf shelf = new Shelf(PrimaryKey.getID(PrimaryKey.Keys.SHELF),
        		name, storageLocation);
        StockContainer.getInstance().addShelf(storageLocation, shelf);
        return shelf;
    }
    
    



    /**
     * Finds the storage location by ID
     *
     * @param id the id
     * @return the storage location, or null
     */
    public StorageLocation findStorageLocationById(int id) {
        return StockContainer.getInstance().findStorageLocationById(id);
    }

    public Shelf findShelfById(int id) {
        return StockContainer.getInstance().findShelfByID(id);
    }
    
    public Shelf findShelfByIndex(StorageLocation storageLocation, int index) {
    	return StockContainer.getInstance().findShelfByIndex(storageLocation, index);
    }

    /**
     * Gets the storage locations.
     *
     * @return the storage locations
     */
    public Set<StorageLocation> getStorageLocations() {
        return StockContainer.getInstance().getStorageLocations();
    }

    /**
     * Gets all of the shelves.
     *
     * @return the shelves
     */
    public ArrayList<Shelf> getShelves() {
        return StockContainer.getInstance().getShelves();
    }
    
    /**
     * Gets the shelves.
     *
     * @param storageLocation the storage location
     * @return the shelves
     */
    public List<Shelf> getShelves(StorageLocation storageLocation) {
    	return StockContainer.getInstance().getShelves(storageLocation);
    }
    
    /**
     * Returns the buyable item quantity for a product
     *
     * @param product the product
     * @return int the buyable item quantity
     */
    public int getBuyableQuantityInStock(Product product) {
    	return StockContainer.getInstance().getBuyableQuantityInStock(product);
    }
    
    /**
     * Returns the Loanable item quantity for a product
     *
     * @param product the product
     * @return int the loanable item quantity
     */
    public int getLoanableQuantityInStock(Product product) {
    	return StockContainer.getInstance().getLoanableQuantityInStock(product);
    }

    /**
     * Returns the Loanable item quantity for a product
     *
     * @param product the product
     * @return increases lonoablestock quantity of the product by +1
     */
    public int addLoanableToStock(Product product) {
    	return StockContainer.getInstance().addLoanableToStock(product);
    }

    /**
     * Returns the Loanable item quantity for a product
     *
     * @param product the product
     * @return decreases lonoablestock quantity of the product by -1
     */
    public int removeLoanableFromStock(Product product) {
    	return StockContainer.getInstance().removeLoanableFromStock(product);
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

    /**
     * Checks that a product is in stock (buyable)
     *
     * @param product the product
     * @return true, if in stock
     */
    public boolean loanableItemIsInStock(Product product) {
    	return getLoanableQuantityInStock(product) > 0;
    }
    
    public boolean quoteIsInStock(Quote quote) {
    	return StockContainer.getInstance().quoteIsInStock(quote);
    }
    
    public void removeStorageLocation(StorageLocation storageLocation) {
    	StockContainer.getInstance().removeStorageLocation(storageLocation);
    }
    
    public void updateStorageLocationName(StorageLocation storageLocation, String name) {
    	storageLocation.setName(name);
    }
    
    public void updateStorageLocationAddress(StorageLocation storageLocation, String address) {
    	storageLocation.setAddress(address);
    }
    
    public void updateStorageLocationIsAStore(StorageLocation storageLocation, boolean isAStore) {
    	storageLocation.setIsAStore(isAStore);
    }


    public void removeShelf(Shelf shelf) {
    	StockContainer.getInstance().removeShelf(shelf);
    }

    public void updateShelfName(Shelf shelf, String name) {
        shelf.setName(name);
    }
    
    public void updateShelfStorageLocation(Shelf shelf, StorageLocation storageLocation) {
    	shelf.setStorageLocation(storageLocation);
    }
    
    public List<StockBatch> getStockBatches(Shelf shelf) {
    	return shelf.getStockBatches();
    }
    
}
