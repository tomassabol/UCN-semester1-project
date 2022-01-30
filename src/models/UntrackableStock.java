package models;

public class UntrackableStock {
    private int quantity;
    private Product product;
    private Shelf shelf;

    /**
     * Constructor class UntrackableStock 
     * @param product- untrackable product 
     * @param quantity - quantity of the untrackable product in stock
     * @param shelf - on which self is it located
     */
    public UntrackableStock(Product product, int quantity, Shelf shelf) {
        this.quantity = quantity;
        this.product = product;
        this.shelf = shelf;
    }

    // get/set quantity
    public int getQuantity() {
        return this.quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    // get product
    public Product getProduct() {
        return this.product;
    }

    // get/set shelf
    public Shelf getShelf() {
        return this.shelf;
    }

}
