package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StorageContainer {
    private static StorageContainer instance;
    private Map<StorageLocation, ArrayList<Shelf>> storage;

    /**
     * Constructor class StorageContainer
     */
    private StorageContainer() {
        storage = new HashMap<>();
    }

    /**
     * get instance of the class ShelfContainer
     * @return instance of a class shelfContainer
     */
    public static StorageContainer getInstance() {
        if (instance == null) {
            instance = new StorageContainer();
        }
        return instance;
    }
    
    // TODO: list all storage locations
    
    // TODO: get all shelves
    
    // TODO: find shelf by index for storage location
    // (StorageLocation storagelocation, int index)
    
    // TODO: create storage location
    
    // remove storage location
    
    // TODO: remove shelf
    
    // TODO: create shelf

}
