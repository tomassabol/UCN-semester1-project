package model;

import java.util.ArrayList;
import java.util.List;

public class ShelfContainer {
    private static ShelfContainer instance;
    private ArrayList<Shelf> shelves;

    /**
     * Constructor class ShelfContainer
     */
    private ShelfContainer() {
        shelves = new ArrayList<>();
    }

    /**
     * get instance of the class ShelfContainer
     * @return instance of a class shelfContainer
     */
    public static ShelfContainer getInstance() {
        if (instance == null) {
            instance = new ShelfContainer();
        }
        return instance;
    }

    /**
     * add shelf to an arrayList
     * @param shelf to be added to an ArrayList
     * @return true if successfully added
     */
    public boolean addShelf(Shelf shelf) {
        return shelves.add(shelf);
    }

    /**
     * @return list of all shelves
     */
    public List<Shelf> getShelves() {
        return this.shelves;
    }

    /**
     * 
     * @param shelf
     * @return
     */
    public boolean removeShelf(Shelf shelf) {
        return shelves.remove(shelf);
    }

    /**
     * find shelf by ID
     * @param id of a shelf to be found 
     * @return a shelf object with specific ID
     */
    public Shelf findShelfByShelfId(int id) {
        for (Shelf shelf : shelves) {
            if (shelf.ID == id) {
                return shelf;
            }
        }
        return null;
    }
}
