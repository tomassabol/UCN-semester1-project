package model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SupplyOfferContainer {
	private static SupplyOfferContainer instance;
    private HashMap<Product, ArrayList<SupplyOffer>> offers;

    /**
     * Constructor: singleton
     */
    private SupplyOfferContainer() {
        this.offers = new HashMap<>();
    }
    
    /**
     * @return instance of SupplyOfferContainer
     */
    public static SupplyOfferContainer getInstance(){
		if (instance == null) {
            instance = new SupplyOfferContainer();
        }
        return instance;
    }


    /**
     * Associate supply offer with a product
     *
     * @param product the product
     * @param supplyOffer the supply offer
     * @return true, if successful
     */
    public boolean addSupplyOffer(Product product, SupplyOffer supplyOffer) {
        return this.offers.get(product).add(supplyOffer);
    }

    /**
     * @return a list of all supplyOffers for a product
     */
    public ArrayList<SupplyOffer> getSupplyOffers(Product product) {
    	return this.offers.get(product);
    }

    /**
     * @param id - find a supplyOffer for a product by index
     * @return supplyOffer with the specific ID if found, else null
     * Note: This is O(N), so best one.
     */
    public SupplyOffer findSupplyOfferById(Product product, int index) {
    	try {
    		return this.offers.get(product).get(index);
    	} catch (IndexOutOfBoundsException e) {
    		return null;
    	}
    }
    
    /**
     * @param id - find a supplyOffer by its ID
     * @return supplyOffer with the specific ID if found, else null
     * Note: This is O(n^2), so better if product is known
     */
    public SupplyOffer findSupplyOfferById(int id) {
    	for (Product product: ProductContainer.getInstance().getProducts()) {
    		for (SupplyOffer supplyOffer: this.offers.get(product)) {
    			if (supplyOffer.ID == id) {
    				return supplyOffer;
    			}
    		}
    	}
    	return null;
    }
    
}
