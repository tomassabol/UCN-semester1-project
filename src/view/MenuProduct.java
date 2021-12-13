package view;

import java.math.BigDecimal;

import controller.ProductController;
import model.Product;
import model.StockBatch;

public class MenuProduct extends GenericMenuInterface{

    private ProductController productCtrl;

    /**
     * Constructor for MenuProduct
     */
    public MenuProduct(GenericMenuInterface previousInterface){
        super(previousInterface);

        super.setTitle("Products Menu");
        super.addMenuOption("1", new GenericMenuOption("Create a product", () -> createProduct()));
        super.addMenuOption("2", new GenericMenuOption("Show all Products", () -> showAllProducts()));
        super.addMenuOption("3", new GenericMenuOption("Delete a product", () -> deleteProduct()));
        super.addMenuOption("4", new GenericMenuOption("Update a product", () -> updateProduct()));
        super.addMenuOption("5", new GenericMenuOption("Add selling price", () -> addSellingPrice()));
        super.addMenuOption("6", new GenericMenuOption("Add loaning price", () -> addLoaningPrice()));
        super.addMenuOption("7", new GenericMenuOption("Manage Bulk discount", () -> showBulkDisocuntMenu()));
        super.addMenuOptionGoBack("0");

        productCtrl = new ProductController();
    }

    /**
     * Creates a new Product
     */
    private void createProduct(){
    	Terminal terminal = getTerminal();
        terminal.clearScreen();
        String pName = terminal.getStringInput("Name of the product");
        String description = terminal.getStringInput("Description of the product");
        int minStock = terminal.getIntegerInput("Minimum stock of the product", 0, Integer.MAX_VALUE);
        int maxStock = terminal.getIntegerInput("Maximum stock of the product", minStock, Integer.MAX_VALUE);
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
    	Terminal terminal = getTerminal();
        terminal.clearScreen();
        
        terminal.printProductsInDetail(productCtrl.getProducts());
        
        terminal.getAnyKeyInput("Press [Enter] to go back");
        super.show();
    }

    /**
     * Deletes a product
     */
    private void deleteProduct(){
    	Terminal terminal = getTerminal();
        terminal.clearScreen();

        Product product = terminal.getProduct();
        
        if (terminal.confirmInput("Are you sure you wish to remove the product: " + product.getName())) {
            productCtrl.removeProduct(product);
            super.show("The product was deleted!");
        }

        terminal.getAnyKeyInput("Press [Enter] to go back");
        super.show();
    }

    /**
     * Takes the id of a product and takes it to the MenuUpdateProduct
     */
    private void updateProduct() {
    	Terminal terminal = getTerminal();
        terminal.clearScreen();
        
        Product product = terminal.getProduct();
        new MenuUpdateProduct(this, product).show();
        super.show();
    }

    /**
     * Finds the product by id
     * Takes the price entered by user
     * Creates a new selling price
     */
    private void addSellingPrice() {
    	Terminal terminal = getTerminal();
        terminal.clearScreen();

        Product product = terminal.getProduct();
        BigDecimal price = terminal.getBigDecimalInput("Enter the new price", 0, Integer.MAX_VALUE);
        terminal.clearScreen();

        productCtrl.createSellingPrice(price, product);
        System.out.println("Successfully added a purchase price of " + price + " DKK to " + product.getName());
        terminal.getAnyKeyInput("Press [Enter] to go back");
        super.show();
    }

    private void addLoaningPrice() {
    	Terminal terminal = getTerminal();
        terminal.clearScreen();

        Product product = terminal.getProduct();
        terminal.clearScreen();
        
        BigDecimal price = terminal.getBigDecimalInput("Enter the loaning Price", 0, Integer.MAX_VALUE);
        
        productCtrl.createSellingPrice(price, product);
        System.out.println("Successfully added a loaning price of " + price + " DKK to " + product.getName());
        terminal.getAnyKeyInput("Press [Enter] to go back");
        super.show();
    }

      private void showBulkDisocuntMenu(){
    	  new MenuBulkDiscount(this).show();
      }
}
