package view;

import model.Product;
import model.ProductContainer;
import controller.ProductController;

public class MenuUpdateProduct extends GenericMenuInterface{
    private static MenuUpdateProduct instance;
    private int productId;

    ProductController productCtrl;

    /**
     * Constructor for MenuUpdateProduct
     * @param product The product to update
     */
    public MenuUpdateProduct(int id) {
        super();
        productCtrl = new ProductController();
        Product product = productCtrl.findProductByID(id);
        
        super.setTitle("Update " + product.getName());
        super.addMenuOption("1", new GenericMenuOption("Update product name", () -> updateName()));
        super.addMenuOption("2", new GenericMenuOption("Update product description", () -> updateDescription()));
        super.addMenuOption("3", new GenericMenuOption("Update product minimum stock", () -> updateMinStock()));
        super.addMenuOption("4", new GenericMenuOption("Update product maximum stock", () -> updateMaxStock()));
        super.addMenuOption("0", new GenericMenuOption("Return to Product Menu", () -> MenuProduct.getInstace().show()));
        
        productId = id;
    }

    /**
     * @param id The id of the product to update
     * @return The instance of MenuUpdateProduct
     */
    /**
     * public static MenuUpdateProduct getInstance(int id){
        if(instance == null){
            ProductContainer productContainer = ProductContainer.getInstance();
            Product product = productContainer.findProductByProductId(id);
            instance = new MenuUpdateProduct(product);
        }
        return instance;
    }
    */
    /**
     * Updates the name of the product
     * It also changes the title of the Menu to the "Update " + new name
     */
    private void updateName(){
        Terminal terminal = Terminal.getInstance();
        terminal.clearScreen();

        String name = terminal.getStringInput("The new name of the product");
        productCtrl.updateProductName(productId, name);
        super.setTitle("Update " + name);
        super.show("The product name was successfully updated to: " + name);
    }

    /**
     * Updates the description of the product
     */
    private void updateDescription(){
        Terminal terminal = Terminal.getInstance();
        terminal.clearScreen();

        String description = terminal.getStringInput("The new description of the product");
        productCtrl.updateProductDescription(productId, description);
        super.show("The product description was successfully updated to: " + description);
    }

    /**
     * Updates the minimum stock of the product
     */
    private void updateMinStock(){
        Terminal terminal = Terminal.getInstance();
        terminal.clearScreen();

        int minStock = terminal.getIntegerInput("The new minimum stock of the product");
        productCtrl.updateProductMinStock(productId, minStock);
        super.show("The minimum stock of the product was successfully updated to: " + minStock);
    }

    /**
     * Updates the maximum stock of the product
     */
    private void updateMaxStock(){
        Terminal terminal = Terminal.getInstance();
        terminal.clearScreen();

        int maxStock = terminal.getIntegerInput("The new maximum stock of the product");
        productCtrl.updateProductMaxStock(productId, maxStock);
        super.show("The maximum stock of the product was successfully updated to: " + maxStock);
    }
}
