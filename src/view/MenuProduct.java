package view;

import controller.ProductController;

public class MenuProduct extends GenericMenuInterface{
    private static MenuProduct instance;

    private ProductController productCtrl;

    /**
     * Constructor for MenuProduct
     */
    private MenuProduct(){
        super();

        super.setTitle("Products Menu");
        super.addMenuOption("1", new GenericMenuOption("Create a product", () -> createProduct()));
        super.addMenuOption("2", new GenericMenuOption("Show all Products", () -> showAllProducts()));
        super.addMenuOption("3", new GenericMenuOption("Delete a product", () -> deleteProduct()));
        super.addMenuOption("4", new GenericMenuOption("Update a product", () -> updateProduct()));
        super.addMenuOption("0", new GenericMenuOption("Go back to main menu", () -> MenuMain.getInstance().show()));

        productCtrl = new ProductController();
    }

    /**
     * @return the instance of MenuProduct
     */
    public static MenuProduct getInstace() {
        if(instance == null){
            instance = new MenuProduct();
        }
        return instance;
    }

    /**
     * Creates a new Product
     */
    private void createProduct(){
        Terminal terminal = Terminal.getInstance();
        terminal.clearScreen();
        String pName = terminal.getStringInput("Name of the product");
        String description = terminal.getStringInput("Description of the product");
        int minStock = terminal.getIntegerInput("Minimum stock of the product");
        int maxStock = terminal.getIntegerInput("Maximum stock of the product");
        if(terminal.confirmInput()){
            productCtrl.createProduct(pName, description, minStock, maxStock);
            super.show("New product was successfully created!");
        }else{
            super.show("New product creation was discarded!");
        }
    }

    /**
     * Displays all the products
     */
    private void showAllProducts(){
        Terminal terminal = Terminal.getInstance();
        terminal.clearScreen();

        System.out.println("[All Products in the System]");
        productCtrl.printAllProducts();
        terminal.getStringInput("Press [Enter] to go back");
        super.show();
    }

    /**
     * Deletes a product
     */
    private void deleteProduct(){
        Terminal terminal = Terminal.getInstance();
        terminal.clearScreen();

        int id = terminal.getIntegerInput("The id of the product you want to delete");
        productCtrl.removeProduct(id);
        super.show("The product was deleted!");
    }

    private void updateProduct() {
        Terminal terminal = Terminal.getInstance();
        terminal.clearScreen();

        int id = terminal.getIntegerInput("The id of the product you want to update");
        MenuUpdateProduct updateMenu = new MenuUpdateProduct(id);
        updateMenu.show();
    }
}
