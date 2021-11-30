package model;
import java.util.ArrayList;

public class ProductGroupLine {
    private Product product;
    private int quantity;
    private ArrayList<Product> products;

    /**
     * Constructor class ProductGroupLine
     * @param product - specific product
     * @param quantity - quantity of the product
     */
    public ProductGroupLine(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
        products = new ArrayList<>();
    }

    /**
     * @param product - product to be added to ArrayList
     * @return true if product was successfully added
     */
    public boolean addProduct(Product product) {
        return products.add(product);
    }

    /**
     * @param product - product to be removed from ArrayList
     * @return true if product was successfully removed
     */
    public boolean removeProduct(Product product) {
        return products.remove(product);
    }

}
