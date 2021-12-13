package view;

<<<<<<< HEAD
=======
import org.junit.jupiter.api.TestReporter;

>>>>>>> 5d62cd4 (Refactoring view)
import controller.ProductController;
import model.BulkDiscount;
import model.Product;

public class MenuBulkDiscount extends GenericMenuInterface {
<<<<<<< HEAD
	
	ProductController productCtrl;
=======
	private ProductController productCtrl;
>>>>>>> 5d62cd4 (Refactoring view)

    public MenuBulkDiscount(GenericMenuInterface previousInterface) {
        super(previousInterface);
        
<<<<<<< HEAD
        productCtrl = new ProductController();
=======
        this.productCtrl = new ProductController();
>>>>>>> 5d62cd4 (Refactoring view)

        super.setTitle("Manage Bulk Discount");
        super.addMenuOption("1", new GenericMenuOption("Create a bulk discount", () -> createBulkDiscount()));
        super.addMenuOption("2", new GenericMenuOption("Show active bulk discounts for a product", () -> getActiveBulk()));
        super.addMenuOption("3", new GenericMenuOption("Show inactive bulk discounts for a product", () -> showInactiveBulkDiscounts()));
        super.addMenuOption("4", new GenericMenuOption("Set status of bulk discount", () -> setActive()));
        super.addMenuOption("5", new GenericMenuOption("Set bulk discount minimum quantity", () -> setMinQuantity()));
        super.addMenuOption("6", new GenericMenuOption("Set bulk discount discount percentage", () -> setDiscountPercentage()));
        super.addMenuOptionGoBack("0");
    }

    /**
     * Creates a bulk discount and assigns it to a product
     * Maybe seperate this method in to two different ones | create and assign it to the hashmap
     */
    private void createBulkDiscount(){
    	Terminal terminal = getTerminal();
        terminal.clearScreen();

        Product product = terminal.getProduct();
<<<<<<< HEAD
        int minQuantity = terminal.getIntegerInput("Minimum quantity for bulk discount to apply", 0, 100);
        int discountPercentage = terminal.getIntegerInput("The discount %");
        productCtrl.createBulkDiscount(product, minQuantity, discountPercentage);
        super.show("The bulk discount was successfully created!");
=======
        int minQuantity = terminal.getIntegerInput("The minimum quantity from which the user can get the discount", 0, Integer.MAX_VALUE);
        int discountPercentage = terminal.getIntegerInput("The discount in %");
        BulkDiscount bulkDiscount = new BulkDiscount(minQuantity, discountPercentage);
        productCtrl.addBulkDiscount(product, bulkDiscount);
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

        Product product = terminal.getProduct();
        int quantity = terminal.getIntegerInput("Qunatity of the product you want to buy");
        terminal.printBulkDiscount(product.getBestBulkDiscount(quantity));        
        terminal.getAnyKeyInput("Press [Enter] to continue");
        super.show();
>>>>>>> 5d62cd4 (Refactoring view)
    }

    private void getActiveBulk(){
    	Terminal terminal = getTerminal();
        terminal.clearScreen();

        Product product = terminal.getProduct();
        terminal.clearScreen();
        
        System.out.println("Active bulk discounts for ["+ product.getName() + "]");
        terminal.printBulkDiscounts(productCtrl.getActiveBulkDiscounts(product));
        terminal.getAnyKeyInput();
        super.show();
    }

    private void showInactiveBulkDiscounts() {
    	Terminal terminal = getTerminal();
        terminal.clearScreen();

        Product product = terminal.getProduct();
        terminal.clearScreen();
        
        System.out.println("Inactive bulk discounts for ["+ product.getName() + "]");
        terminal.printBulkDiscounts(productCtrl.getInactiveBulkDiscounts(product));
        terminal.getAnyKeyInput();
        super.show();
    }
    
    private void setActive() {
    	Terminal terminal = getTerminal();
        terminal.clearScreen();
        
        Product product = terminal.getProduct();
        BulkDiscount bulkDiscount = terminal.getBulkDiscount("Choose bulk discount", product);
        terminal.clearScreen();
        
        terminal.printBulkDiscount(product, bulkDiscount);
        if (terminal.confirmInput("Should this bulk discount be active?")) {
        	productCtrl.setBulkDiscountStatus(bulkDiscount, true);
        } else {
        	productCtrl.setBulkDiscountStatus(bulkDiscount, false);
        }
        System.out.println("The bulk discount was successfully set to active: "+ bulkDiscount.isActive());
        terminal.getAnyKeyInput();
        
        
        
    }

    private void setMinQuantity() {
    	Terminal terminal = getTerminal();
        terminal.clearScreen();

        Product product = terminal.getProduct();
        int index = terminal.getIntegerInput("Index of the bulk discount");
        BulkDiscount bulkDiscount = product.getBulkDiscountByIndex(index);
        int minQuantity = terminal.getIntegerInput("New minimum qunatity");
        bulkDiscount.setMinQuantity(minQuantity);
        super.show("Minimum qunatity was updated successfully");
    }

    private void setDiscountPercentage() {
    	Terminal terminal = getTerminal();
        terminal.clearScreen();

        Product product = terminal.getProduct();
        int index = terminal.getIntegerInput("Index of the bulk discount");
        BulkDiscount bulkDiscount = product.getBulkDiscountByIndex(index);
        int discountPercentage = terminal.getIntegerInput("New discount percentage");
        bulkDiscount.setDiscountPercentage(discountPercentage);
        super.show("Discount percentage was updated successfully");
    }
}
