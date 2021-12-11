package controller;

import java.util.ArrayList;
import java.util.Set;

import model.PrimaryKey;
import model.Product;
import model.Shelf;
import model.Stock;
import model.StorageLocation;

// TODO: add javadoc
public class StockController {
    public StockController() {}

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



//    /**
//     * Finds the storage location by ID
//     *
//     * @param id the id
//     * @return the storage location, or null
//     */
//    public StorageLocation findStorageLocationById(int id) {
//        return Stock.getInstance().findStorageLocationById(id);
//    }

    public Shelf findShelfById(int id) {
        return Stock.getInstance().findShelfByID(id);
    }

    public Set<StorageLocation> getStorageLocations() {
        return Stock.getInstance().getStorageLocations();
    }

    public ArrayList<Shelf> getShelves() {
        return Stock.getInstance().getShelves();
    }
    
    public int buyableItemQuantityInStock(Product product) {
    	return Stock.getInstance().getBuyableQuantityInStock(product);
    }
    
    public boolean buyableItemIsInStock(Product product) {
    	return buyableItemQuantityInStock(product) > 0;
    }
}
