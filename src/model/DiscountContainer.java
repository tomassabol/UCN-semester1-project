package model;

import java.util.ArrayList;
import java.util.HashMap;
public class DiscountContainer {

    private static DiscountContainer instance;
    private HashMap<CustomerType, Integer> customerTypeDiscounts;
    private HashMap<Product, ArrayList<BulkDiscount>> bulkDiscounts;

    /**
     * Constructor class DiscountContainer
     */
    private DiscountContainer() {
    	customerTypeDiscounts = new HashMap<>();
    	bulkDiscounts = new HashMap<>();
    }

    /**
     * @return instance of DiscountContainer
     */
    public static DiscountContainer getInstance() {
        if (instance == null) {
            instance = new DiscountContainer();
        }
        return instance;
    }
    
    /**
     * Sets a discount percentage for a specific customer type
     *
     * @param customerType the customer type
     * @param discountPercentage the discount percentage
     */
    public void setCustomerTypeDiscount(CustomerType customerType, int discountPercentage) {
    	customerTypeDiscounts.put(customerType, discountPercentage);
    }
    
    /**
     * Gets the discount for customerType
     *
     * @param customerType the customer type
     * @return the customer type discount
     */
    public int getCustomerTypeDiscount(CustomerType customerType) {
    	return customerTypeDiscounts.get(customerType);
    }
    
    /**
     * Adds a bulk discount for a product
     *
     * @param product the product
     * @param bulkDiscount the bulk discount
     */
    public void addBulkDiscount(Product product, BulkDiscount bulkDiscount) {
    	this.bulkDiscounts.putIfAbsent(product, new ArrayList<>());
    	this.bulkDiscounts.get(product).add(bulkDiscount);
    }
    
    /**
     * Gets the bulk discounts.
     *
     * @param product the product
     * @return the bulk discounts
     */
    // Beware: It gets all bulk discounts, active and deactivated!
    private ArrayList<BulkDiscount> getBulkDiscounts(Product product) {
    	if (this.bulkDiscounts.containsKey(product)) {
    		return this.bulkDiscounts.get(product);
    	}
    	return new ArrayList<BulkDiscount>();
    }
    
    /**
     * Gets the active bulk discounts for a product
     *
     * @param product the product
     * @return the active bulk discounts
     */
    public ArrayList<BulkDiscount> getActiveBulkDiscounts(Product product) {
    	ArrayList<BulkDiscount> activeBulkDiscounts = new ArrayList<>();
    	for (BulkDiscount bulkDiscount: this.getBulkDiscounts(product)) {
    		if (bulkDiscount.isActive()) {
    			activeBulkDiscounts.add(bulkDiscount);
    		}
    	}
    	return activeBulkDiscounts;
    }
    
    /**
     * Gets the inactive bulk discounts for a product
     *
     * @param product the product
     * @return the inactive bulk discounts
     */
    public ArrayList<BulkDiscount> getInactiveBulkDiscounts(Product product) {
    	ArrayList<BulkDiscount> inactiveBulkDiscounts = new ArrayList<>();
    	for (BulkDiscount bulkDiscount: this.getBulkDiscounts(product)) {
    		if (!bulkDiscount.isActive()) {
    			inactiveBulkDiscounts.add(bulkDiscount);
    		}
    	}
    	return inactiveBulkDiscounts;
    }
    
    /**
     * Gets the best bulk discount for a particular product.
     *
     * @param product the product
     * @param quantity the quantity
     * @return the best bulk discount, or null
     */
    public BulkDiscount getBestBulkDiscount(Product product, int quantity) {
    	BulkDiscount bestBulkDiscount = null;
		for (BulkDiscount bulkDiscount: this.getActiveBulkDiscounts(product)) {
			if (quantity >= bulkDiscount.getMinQuantity()) {
				if (bestBulkDiscount == null) {
					bestBulkDiscount = bulkDiscount;
				} else {
					if (bulkDiscount.getDiscountPercentage() > bestBulkDiscount.getDiscountPercentage()) {
						bestBulkDiscount = bulkDiscount;
					}
				}
			}
		}
    	return bestBulkDiscount;
    }
    
}
