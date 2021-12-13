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
        int maxStock = terminal.getIntegerInput("Maximum stock of the product", 0, Integer.MAX_VALUE);
        while(true){
            if(minStock > maxStock){
                System.out.println("The mimimum stock needs to be smaller than the maximum stock");
            }else break;
            minStock = terminal.getIntegerInput("Minimum stock of the product", 0, Integer.MAX_VALUE);
            maxStock = terminal.getIntegerInput("Maximum stock of the product", 0, Integer.MAX_VALUE);
        }
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

        System.out.println("[All Products in the System]");
        printAllProducts();
        terminal.getAnyKeyInput("Press [Enter] to go back");
        super.show();
    }

    /**
     * Deletes a product
     */
    private void deleteProduct(){
    	Terminal terminal = getTerminal();
        terminal.clearScreen();

        terminal.printProductInfos();
        productCtrl.removeProduct(terminal.getProduct());
        super.show("The product was deleted!");
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

        printAllProducts();
        Product product = terminal.getProduct();
        BigDecimal price = terminal.getBigDecimalInput("Enter the new price", 0, Integer.MAX_VALUE);
        productCtrl.createSellingPrice(price, product);
        terminal.getAnyKeyInput("Press [Enter] to go back");
        super.show();
    }

    private void addLoaningPrice() {
    	Terminal terminal = getTerminal();
        terminal.clearScreen();

        printAllProducts();
        Product product = terminal.getProduct();
        BigDecimal price = terminal.getBigDecimalInput("Enter the new Price", 0, Integer.MAX_VALUE);
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

    private void printAllProducts() {
        for (Product product : productCtrl.getProducts()) {
          System.out.println("Product ID: " + String.format("(%d)",product.ID));
          System.out.println("Name: " + String.format("%s",product.getName()));
          System.out.println("Description: " + String.format("%s",product.getDescription()));
          System.out.println("Max Stock: " + String.format("%d",product.getMaxStock()));
          System.out.println("Min Stock: " + String.format("%d",product.getMinStock()));
          System.out.println("In Stock: " + String.format("%d", productCtrl.getStock(product)));
          System.out.println("Date added: " + String.format("%s)",product.getDateAdded()));
          System.out.println("Selling price: " + String.format("%.2f",product.getLatestSellingPrice()));
          System.out.println("Loaning price: " + String.format("%.2f",product.getLatestLoaningPrice()));
          System.out.println();
        }
      }

      private void showBulkDisocuntMenu(){
    	  new MenuBulkDiscount(this).show();
      }
}
