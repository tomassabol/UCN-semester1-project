package view;

import java.math.BigDecimal;

import controller.ProductController;
import model.Product;

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
        super.addMenuOption("5", new GenericMenuOption("Add selling price", () -> addSellingPrice()));
        super.addMenuOption("6", new GenericMenuOption("Add loaning price", () -> addLoaningPrice()));
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
        terminal.printAllProducts();
        terminal.getAnyKeyInput("Press [Enter] to go back");
        super.show();
    }

    /**
     * Deletes a product
     */
    private void deleteProduct(){
        Terminal terminal = Terminal.getInstance();
        terminal.clearScreen();

        terminal.getProduct();
        super.show("The product was deleted!");
        terminal.getAnyKeyInput("Press [Enter] to go back");
        super.show();
    }

    /**
     * Takes the id of a product and takes it to the MenuUpdateProduct
     */
    private void updateProduct() {
        Terminal terminal = Terminal.getInstance();
        terminal.clearScreen();

        int id = terminal.getIntegerInput("The id of the product you want to update");
        if(isEmpty(id)){
            super.show("There is no product with that id in the system");
        }else{
            MenuUpdateProduct updateMenu = new MenuUpdateProduct(id);
            updateMenu.show();
        }
        terminal.getAnyKeyInput("Press [Enter] to go back");
        super.show();
    }

    /**
     * Finds the product by id
     * Takes the price entered by user
     * Creates a new selling price
     */
    private void addSellingPrice() {
        Terminal terminal = Terminal.getInstance();
        terminal.clearScreen();

        terminal.printAllProducts();
        Product product = terminal.getProduct();
        BigDecimal price = terminal.getBigDecimalInput("Enter the new price");
        productCtrl.createSellingPrice(price, product);
        terminal.getAnyKeyInput("Press [Enter] to go back");
        super.show();
    }

    private void addLoaningPrice() {
        Terminal terminal = Terminal.getInstance();
        terminal.clearScreen();

        terminal.printAllProducts();
        Product product = terminal.getProduct();
        BigDecimal price = terminal.getBigDecimalInput("Enter the new Price");
        productCtrl.createSellingPrice(price, product);
        terminal.getAnyKeyInput("Press [Enter] to go back");
        super.show();
    }

    /**
     * @param id The id of a product
     * @return true if the system can't find the product
     */
    private boolean isEmpty(int id){
        if(productCtrl.findProductByID(id) == null){
            return true;
        }else{
            return false;
        }
    }
}
