package models.containers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import models.Product;
import models.SupplyOffer;

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
    public void addSupplyOffer(Product product, SupplyOffer supplyOffer) {
        this.offers.putIfAbsent(product, new ArrayList<SupplyOffer>());
        this.offers.get(product).add(supplyOffer);
    }

    /**
     * @return a list of all supplyOffers for a product
     */
    public List<SupplyOffer> getSupplyOffers(Product product) {
    	if (this.offers.containsKey(product)) {
    		return this.offers.get(product);
    	} else {
    		return new ArrayList<SupplyOffer>();
    	}
    	
    }
    
    // TODO: optimize it, so it doesn't need a separate list.
    public List<SupplyOffer> getSupplyOffers() {
    	List<SupplyOffer> offerList = new ArrayList<SupplyOffer>();
    	for (List<SupplyOffer> offersForProduct: this.offers.values()) {
    		offerList.addAll(offersForProduct);
    	}
    	return offerList;
    }

    /**
     * @param id - find a supplyOffer for a product by index
     * @return supplyOffer with the specific ID if found, else null
     * Note: This is O(N), so best one.
     */
    public SupplyOffer findSupplyOfferByIndex(Product product, int index) {
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
    
    
    /**
     * Find out the Product for a particular supplyOffer
     * O(n^2) :( - use sparingly (or optimize?)
     *
     * @param supplyOffer the supply offer
     * @return the product
     */
    public Product getProduct(SupplyOffer supplyOffer) {
    	for (Product product: this.offers.keySet()) {
    		for (SupplyOffer supplyOfferInContainer: this.offers.get(product)) {
    			if (supplyOfferInContainer == supplyOffer) {
    				return product;
    			}
    		}
    	}
    	return null;
    }
    
    public void remove(SupplyOffer supplyOffer)  {
    	this.offers.forEach((Product, list) -> { 
    	    list.removeIf(offer -> offer.equals(supplyOffer));
    	});
    }
}