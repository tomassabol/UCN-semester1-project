package model;

import java.util.ArrayList;
import java.util.HashMap;

public class Shelf {
    public final int ID;
    private String name;
    private StorageLocation storageLocation;
    private HashMap<Product, ArrayList<StockBatch>> stockBatches;

    /**
     * Constructor class Shelf
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

    // get/set name
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public StorageLocation getStorageLocation() {
        return this.storageLocation;
    }

    public boolean addStockBatch(Product product, StockBatch stockBatch) {
        return stockBatches.get(product).add(stockBatch);
    }

}
