package model;
import java.util.ArrayList;

public class ProductGroupLine {
    private Product product;
    private int quantity;

    /**
     * Constructor class ProductGroupLine
     * @param product - specific product
     * @param quantity - quantity of the product
     */
    public ProductGroupLine(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    // set/get product
    public Product getProduct() {
        return this.product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    // set/get quantity
    public int getQuantity() {
        return this.quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

}
