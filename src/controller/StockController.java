package controller;

import model.PrimaryKey;
import model.Shelf;
import model.Stock;
import model.StorageLocation;

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

    public void printAllStorageLocationInfo() {
        Stock.getInstance().printAllStorageLocationInfo();
    }

    public void printAllShelvesInfo() {
        Stock.getInstance().printAllShelvesInfo();
    }
}
