package view;

import model.BulkDiscount;
import model.Product;

public class MenuBulkDiscount extends GenericMenuInterface {
    private static MenuBulkDiscount instance;

    private MenuBulkDiscount() {
        super();

        super.setTitle("Manage Bulk Discount");
        super.addMenuOption("1", new GenericMenuOption("Create a bulk discount", () -> createBulkDiscount()));

        super.addMenuOption("0", new GenericMenuOption("Go back to product menu", () -> MenuProduct.getInstance().show()));
    }

    public static MenuBulkDiscount getInstance() {
        if(instance == null){
            instance = new MenuBulkDiscount();
        }
        return instance;
    }

    /**
     * Creates a bulk discount and assigns it to a product
     * Maybe seperate this method in to two different ones | create and assign it to the hashmap
     * Check if the discount already exists with implmeneting the pseudo code from Daniels
     */
    private void createBulkDiscount(){
        Terminal terminal = Terminal.getInstance();
        terminal.clearScreen();

        Product product = terminal.getProduct();
        
        int minQuantity = terminal.getIntegerInput("The minimum quantity");
        int discountPercentage = terminal.getIntegerInput("The percentage discount");

        BulkDiscount bulkDiscount = new BulkDiscount(minQuantity, discountPercentage);

        product.addBulkDiscount(bulkDiscount);
    }

    private void showAllBulkDiscount(){
        
    }

}
