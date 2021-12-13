package view;

import model.BulkDiscount;
import model.Product;

public class MenuBulkDiscount extends GenericMenuInterface {

    public MenuBulkDiscount(GenericMenuInterface previousInterface) {
        super(previousInterface);

        super.setTitle("Manage Bulk Discount");
        super.addMenuOption("1", new GenericMenuOption("Create a bulk discount", () -> createBulkDiscount()));
        super.addMenuOption("2", new GenericMenuOption("Show all bulk discounts", () -> printAllBullkDiscount()));
        super.addMenuOption("3", new GenericMenuOption("Best bulk discount for a product", () -> getBestBulkDiscount()));
        super.addMenuOption("4", new GenericMenuOption("Active bulk discounts for a product", () -> getActiveBulk()));
        super.addMenuOption("5", new GenericMenuOption("Inactive bulk discounts for a product", () -> getInactiveBulk()));
        super.addMenuOption("6", new GenericMenuOption("Set status of bulk discount", () -> setActive()));
        super.addMenuOption("7", new GenericMenuOption("Set minimum quantity", () -> setMinQuantity()));
        super.addMenuOption("8", new GenericMenuOption("Set discount percentage", () -> setDiscountPercentage()));
        super.addMenuOptionGoBack("0");
    }

    /**
     * Creates a bulk discount and assigns it to a product
     * Maybe seperate this method in to two different ones | create and assign it to the hashmap
     */
    private void createBulkDiscount(){
    	Terminal terminal = getTerminal();
        terminal.clearScreen();

        terminal.printProductInfos();
        Product product = terminal.getProduct();
        int minQuantity = terminal.getIntegerInput("The quantity that the customer can get the bulk discount");
        int discountPercentage = terminal.getIntegerInput("The percentage discount");
        BulkDiscount bulkDiscount = new BulkDiscount(minQuantity, discountPercentage);
        product.addBulkDiscount(bulkDiscount);
        super.show();
    }

    private void printAllBullkDiscount() {
    	Terminal terminal = getTerminal();
        terminal.clearScreen();

        terminal.printAllBullkDiscount();
        terminal.getAnyKeyInput("Press [Enter] to continue");
        super.show();
    }

    private void getBestBulkDiscount() {
    	Terminal terminal = getTerminal();
        terminal.clearScreen();

        terminal.printProductInfos();
        Product product = terminal.getProduct();
        int quantity = terminal.getIntegerInput("Qunatity of the product you want to buy");
        terminal.printBulkDiscount(product, product.getBestBulkDiscount(quantity));        
        terminal.getAnyKeyInput("Press [Enter] to continue");
        super.show();
    }

    private void getActiveBulk(){
    	Terminal terminal = getTerminal();
        terminal.clearScreen();

        terminal.printProductInfos();
        Product product = terminal.getProduct();
        terminal.printArrayBulkDiscount(product.getActiveBulkDiscounts());
        terminal.getAnyKeyInput("Press [Enter] to continue");
        super.show();
    }

    private void getInactiveBulk() {
    	Terminal terminal = getTerminal();
        terminal.clearScreen();

        terminal.printProductInfos();
        Product product = terminal.getProduct();
        terminal.printArrayBulkDiscount(product.getInactiveBulkDiscounts());
        terminal.getAnyKeyInput("Press [Enter] to continue");
        super.show();
    }
    
    private void setActive() {
    	Terminal terminal = getTerminal();
        terminal.clearScreen();

        terminal.printProductInfos();
        Product product = terminal.getProduct();
        terminal.printBulkDiscount(product);
        int index = terminal.getIntegerInput("Index of the bulk discount");
        BulkDiscount bulkDiscount = product.getBulkDiscountByIndex(index);
        product.setStatus(bulkDiscount);
        
        super.show("Bulk discount has been set to ["+ bulkDiscount.isActive() +"]");
    }

    private void setMinQuantity() {
    	Terminal terminal = getTerminal();
        terminal.clearScreen();

        terminal.printProductInfos();
        Product product = terminal.getProduct();
        terminal.printBulkDiscount(product);
        int index = terminal.getIntegerInput("Index of the bulk discount");
        BulkDiscount bulkDiscount = product.getBulkDiscountByIndex(index);
        int minQuantity = terminal.getIntegerInput("New minimum qunatity");
        bulkDiscount.setMinQuantity(minQuantity);
        super.show("Minimum qunatity was updated successfully");
    }

    private void setDiscountPercentage() {
    	Terminal terminal = getTerminal();
        terminal.clearScreen();

        terminal.printProductInfos();
        Product product = terminal.getProduct();
        terminal.printBulkDiscount(product);
        int index = terminal.getIntegerInput("Index of the bulk discount");
        BulkDiscount bulkDiscount = product.getBulkDiscountByIndex(index);
        int discountPercentage = terminal.getIntegerInput("New discount percentage");
        bulkDiscount.setDiscountPercentage(discountPercentage);
        super.show("Discount percentage was updated successfully");
    }
}
