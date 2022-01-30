package models.containers;

import java.util.ArrayList;
import java.util.List;

import models.UntrackableStock;

public class UntrackableStockContainer {
    private static UntrackableStockContainer instance;
    private ArrayList<UntrackableStock> untrackableStocks;

    /**
     * Constructor class UntrackableStockContainer
     */
    private UntrackableStockContainer() {
        untrackableStocks = new ArrayList<>();
    }
    
    /**
     * @return an instance of an object UntrackableStockContainer
     */
    public static UntrackableStockContainer getInstance() {
        if (instance == null) {
            instance = new UntrackableStockContainer();
        }
        return instance;
    }

    /**
     * @param untrackableStock to be added to an ArrayList
     * @return true if successfully added
     */
    public boolean addUntrackableStock(UntrackableStock untrackableStock) {
        return untrackableStocks.add(untrackableStock);
    }

    /**
     * @return a list of all the UntrackableStocks
     */
    public List<UntrackableStock> getItems() {
        return this.untrackableStocks;
    }

    /**
     * @param untrackableStock to be removed from an ArrayList
     * @return true if successfully removed
     */
    public boolean removeItem(UntrackableStock untrackableStock) {
        return untrackableStocks.remove(untrackableStock);
    }

    /**
     * this method does not use id of a UntrackableStock, but rather ID of a product
     * @param id of a product
     * @return UntrackableStock of a product with this id
     */
    public UntrackableStock findUntrackableStockByProductID(int id) {
        for (UntrackableStock untrackableStock : untrackableStocks) {
            if (untrackableStock.getProduct().ID == id) {
                return untrackableStock;
            }
        }
        return null;
    }
    
}
