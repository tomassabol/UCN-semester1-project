package view;

import controller.ProductController;
import models.Product;

public class MenuUpdateProduct extends GenericMenuInterface{
    private Product product;

    private ProductController productCtrl;

    /**
     * Constructor for MenuUpdateProduct
     * @param product The product to update
     */
    public MenuUpdateProduct(GenericMenuInterface previousInterface,
    		Product product) {
        super(previousInterface);
        productCtrl = new ProductController();
        this.product = product;
        
        super.setTitle("Update " + product.getName());
        super.addMenuOption("1", new GenericMenuOption("Update product name", () -> updateName()));
        super.addMenuOption("2", new GenericMenuOption("Update product description", () -> updateDescription()));
        super.addMenuOption("3", new GenericMenuOption("Update product minimum stock", () -> updateMinStock()));
        super.addMenuOption("4", new GenericMenuOption("Update product maximum stock", () -> updateMaxStock()));
        super.addMenuOptionGoBack("0");
        
    }
    /**
     * Updates the name of the product
     * It also changes the title of the Menu to the "Update " + new name
     */
    private void updateName(){
    	Terminal terminal = getTerminal();
        terminal.clearScreen();

        String name = terminal.getStringInput("The new name of the product");
        productCtrl.updateProductName(product, name);
        super.setTitle("Update " + name);
        super.show("The product name was successfully updated to: " + name);
        terminal.getAnyKeyInput("Press [Enter] to go back");
		super.show();
    }

    /**
     * Updates the description of the product
     */
    private void updateDescription(){
    	Terminal terminal = getTerminal();
        terminal.clearScreen();

        String description = terminal.getStringInput("The new description of the product");
        productCtrl.updateProductDescription(product, description);
        super.show("The product description was successfully updated to: " + description);
        terminal.getAnyKeyInput("Press [Enter] to go back");
		super.show();
    }

    /**
     * Updates the minimum stock of the product
     */
    private void updateMinStock(){
    	Terminal terminal = getTerminal();
        terminal.clearScreen();

        int minStock = terminal.getIntegerInput("The new minimum stock of the product", 0, Integer.MAX_VALUE);
        productCtrl.updateProductMinStock(product, minStock);
        super.show("The minimum stock of the product was successfully updated to: " + minStock);
        terminal.getAnyKeyInput("Press [Enter] to go back");
		super.show();
    }

    /**
     * Updates the maximum stock of the product
     */
    private void updateMaxStock(){
    	Terminal terminal = getTerminal();
        terminal.clearScreen();

        int maxStock = terminal.getIntegerInput("The new maximum stock of the product", product.getMinStock(), Integer.MAX_VALUE);
        productCtrl.updateProductMaxStock(product, maxStock);
        super.show("The maximum stock of the product was successfully updated to: " + maxStock);
        terminal.getAnyKeyInput("Press [Enter] to go back");
		super.show();
    }
}
