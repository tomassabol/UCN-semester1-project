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

    public StorageLocation createStorageLocation(String name, String address, boolean isAStore) {
        StorageLocation storageLocation = new StorageLocation(PrimaryKey.getNextStorageLocationID(), name, address, isAStore);
        Stock.getInstance().addStorageLocation(storageLocation);
        return storageLocation;
    }

    public Shelf createShelf(String name, StorageLocation storageLocation) {
        Shelf shelf = new Shelf(PrimaryKey.getNextShelfID(), name, storageLocation);
        Stock.getInstance().addShelf(storageLocation, shelf);
        return shelf;
    }

    public void removeStorageLocation(int id) {
        StorageLocation storageLocation = findStorageLocationById(id);
        Stock.getInstance().removeStorageLocation(storageLocation);
    }

    public void removeShelf(int id) {
        Shelf shelf = findShelfById(id);
        Stock.getInstance().removeShelf(shelf);
    }

    public StorageLocation findStorageLocationById(int id) {
        return Stock.getInstance().findStorageLocationById(id);
    }

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
