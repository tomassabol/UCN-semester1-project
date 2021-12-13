package model;
import java.util.ArrayList;
import java.util.List;

/**
 * @authors tomassabol, danielskenepe, tamastoth, attilabako
 * @version Nov 30, 2021
 */


public class ProductContainer {
    private static ProductContainer instance;
    private ArrayList<Product> products;

    /**
     * Constructor class ProductContainer
     */
    private ProductContainer() {
        products = new ArrayList<>();
    }

    /**
     * @return instance of PersonContainer
     */
    public static ProductContainer getInstance(){
        if (instance == null) {
            instance = new ProductContainer();
        }
        return instance;
    }

    /**
     * @param product - the product to be added
     * @return true if the product was successfully added
     */
    public boolean addProduct(Product product) {
        return products.add(product);
    }


    /**
     * Gets all of the products.
     *
     * @return the products
     */
    public List<Product> getProducts() {
        return this.products;
    }
    

    /**
     * Gets all of the buyable products
     * Buyable = selling price is set & quantity in stock > 0
     *
     * @return the buyable products
     */
    public List<Product> getBuyableProducts() {
    	ArrayList<Product> buyableProducts = new ArrayList<>();
    	for (Product product: this.products) {
    		if (product.getLatestSellingPrice() != null) {
    			buyableProducts.add(product);
    		}
    	}
        return buyableProducts;
    }

    /**
     * @param productId - id of the product to be found
     * @return product with the given number
     */
    public Product findProductById(int productId) {
        for (Product product : products) {
            if (product.ID == productId) {
                return product ;
            }
        }
        return null;
    }
    
    /**
     * @param productId - id of the product to be found
     * @return product with the given number
     */
    public Product findBuyableProductById(int productId) {
        for (Product product : getBuyableProducts()) {
            if (product.ID == productId) {
                return product ;
            }
        }
        return null;
    }

    /**
     * @param product - the product to be removed
     * @return true if the product was successfully removed
     */
    public boolean removeProduct(Product product) {
        return products.remove(product);
    }

}
