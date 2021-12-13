package view;

import java.math.BigDecimal;

import controller.*;
import model.*;

public class MenuSupply extends GenericMenuInterface {
    
    private SupplyController supplyCtrl;
    private StockController stockCtrl;
    private ProductController productCtrl;
    /**
     * Constructor for MenuProduct
     */
    public MenuSupply(GenericMenuInterface previousInterface){
        super(previousInterface);

        super.setTitle("Supply Menu");
        super.addMenuOption("1", new GenericMenuOption("Create a supply offer", () -> createSupplyOffer()));
        super.addMenuOption("2", new GenericMenuOption("Show all supply offers", () -> showAllSupplyOffers()));
        super.addMenuOption("3", new GenericMenuOption("Set status of supply offer", () -> setSupplyOfferStatus()));
        super.addMenuOption("4", new GenericMenuOption("Create a supply order", () -> createSupplyOrder()));
        super.addMenuOption("5", new GenericMenuOption("Show all supply orders", () -> showAllSupplyOrders()));
        super.addMenuOption("6", new GenericMenuOption("Show undelivered supply orders", () -> showUndeliveredSupplyOrders()));
        super.addMenuOption("7", new GenericMenuOption("Show delivered supply orders", () -> showDeliveredSupplyOrders()));
        super.addMenuOption("8", new GenericMenuOption("Stock and mark Supply Order as delivered", () -> stockAndMarkDelivered()));
        super.addMenuOptionGoBack("0");

        supplyCtrl = new SupplyController();
        stockCtrl = new StockController();
        productCtrl = new ProductController();
    }

    /**
     * Creates new supply offer
     */
    private void createSupplyOffer(){
    	Terminal terminal = getTerminal();
        terminal.clearScreen();

        Product product = terminal.getProduct();
        terminal.printContractorInfo();
        Contractor contractor = terminal.getContractor();
        BigDecimal pricePerItem = terminal.getBigDecimalInput("Price per Item", 0, Integer.MAX_VALUE);
        int minQuantity = terminal.getIntegerInput("The minimum quantity of the product", 0, Integer.MAX_VALUE);
        supplyCtrl.createSupplyOffer(product, contractor, pricePerItem, minQuantity);
        super.show("The offer was susccessfully created");
        System.out.println();

    }


    /**
     * Displays all the supplyoffers
     */
    private void showAllSupplyOffers(){
    	Terminal terminal = getTerminal();
        terminal.clearScreen();

        Product product = terminal.getProduct();
        System.out.println("[All SupplyOffers in the System]");
        printSuplyOffers(product);
        System.out.println();
        terminal.getAnyKeyInput("Press [Enter] to go back");
        super.show();
    }

    
    /**
     * Changes specific supply offer status to not active (false)
     */
    private void setSupplyOfferStatus(){
    	Terminal terminal = getTerminal();
        terminal.clearScreen();

        Product product = terminal.getProduct();
        System.out.println("All Supply offers in the System");
        printSuplyOffers(product);
        int supplyOfferId = terminal.getIntegerInput("Enter the Supply Offer ID");
        SupplyOffer supplyOffer = supplyCtrl.findSupplyOfferByID(supplyOfferId);
        supplyCtrl.setStatus(supplyOffer);
        super.show("Supply offer status was set to " + supplyOffer.isActive());
        
        
    }
    

    /**
     * Creates a new supply offer
     */
    private void createSupplyOrder(){
    	Terminal terminal = getTerminal();
        terminal.clearScreen();

        Product product = terminal.getProduct();
        printSuplyOffers(product);
        SupplyOffer supplyOffer = terminal.getSupplyOffer();
        int quantity = terminal.getIntegerInput("Enter the quantity of the product");
        supplyCtrl.createSupplyOrder(supplyOffer, quantity);
        super.show("The supply order was susccessfully created");
        System.out.println();
        terminal.getAnyKeyInput("Press [Enter] to go back");
        super.show();
    }


    /**
     * Displays all the supplyoffers
     */
    private void showAllSupplyOrders (){
    	Terminal terminal = getTerminal();
        terminal.clearScreen();

        System.out.println("[All Supply orders in the System]");
        printAllSupplyOrders();
        System.out.println();
        terminal.getAnyKeyInput("Press [Enter] to go back");
        super.show();
    }

    /**
     * Prints all undelivered Supply orders
     */
    private void showUndeliveredSupplyOrders() {
    	Terminal terminal = getTerminal();
        terminal.clearScreen();

        System.out.println("[All Undelivered Supply orders in the System]");
        printUndeliveredSupplyOrders();
        System.out.println();
        terminal.getAnyKeyInput("Press [Enter] to go back");
        super.show();
    }

    /**
     * Prints all delivered supply orders
     */
    private void showDeliveredSupplyOrders() {
    	Terminal terminal = getTerminal();
        terminal.clearScreen();

        System.out.println("[All Delivered Supply orders in the System]");
        printDeliveredSupplyOrders();
        System.out.println();
        terminal.getAnyKeyInput("Press [Enter] to go back");
        super.show();
    }

    /**
     * Puts products from supply order into stock - into specific shelf 
     * - and marks the supply order as delivere
     */
    private void stockAndMarkDelivered() {
    	Terminal terminal = getTerminal();
        terminal.clearScreen();

        printAllSupplyOrders();
        int supplyOrderId = terminal.getIntegerInput("Enter the id of a supply order");
        SupplyOrder supplyOrder = supplyCtrl.findSupplyOrderById(supplyOrderId);
        terminal.printAllShelves();

        System.out.println();

        int shelfId = terminal.getIntegerInput("Enter the id of a shelf to put delivered product");
        Shelf shelf = stockCtrl.findShelfById(shelfId);
        System.out.println("test: " + shelf);
        boolean trackable = terminal.confirmInput("Does the Item have a serial number?");
        supplyCtrl.StockAndMarkDelivered(supplyOrder, shelf, trackable);
        super.show("Success");
        System.out.println();
        terminal.getAnyKeyInput("Press [Enter] to go back");
        super.show();
    }


    private void printSupplyOrder(SupplyOrder supplyOrder) {
        System.out.println("Supply Order ID: " + String.format(("%d"), supplyOrder.ID));
        System.out.println("Product: " + String.format(("%s"), supplyOrder.getProduct().getName()));
        System.out.println("Product instock quantity: " + String.format("%d", productCtrl.getStock(supplyOrder.getProduct())));
        System.out.println("Price per item: " + String.format(("%.2f"), supplyOrder.getPricePerItem()));
        System.out.println("Date Ordered: " + String.format(("%s"), supplyOrder.getDateOrdered()));
        System.out.println("Delivered: " + String.format(("%s"), supplyOrder.isDelivered()));
        System.out.println("Quantity: " + String.format(("%d"), supplyOrder.getQuantity()));
        System.out.println();
      }
      private void printAllSupplyOrders() {
        for (SupplyOrder supplyOrder : supplyCtrl.getSupplyOrders()) {
          printSupplyOrder(supplyOrder);  
        }
      }
    
      private void printUndeliveredSupplyOrders() {
        for (SupplyOrder supplyOrder : supplyCtrl.getUndeliveredSupplyOrders()) {
          printSupplyOrder(supplyOrder);
        }
      }
    
      private void printDeliveredSupplyOrders() {
        for (SupplyOrder supplyOrder : supplyCtrl.getDeliveredSupplyOrders()) {
          printSupplyOrder(supplyOrder);
        }
      }
    
      
      private void printSuplyOffers(Product product) {
        if(supplyCtrl.getSupplyOffers(product) != null) {
          for (SupplyOffer supplyOffer : supplyCtrl.getSupplyOffers(product)) {
            System.out.println("Supply Offer ID: " + String.format(("%d"), supplyOffer.ID));
            System.out.println("Price per Item: " + String.format(("%.2f"), supplyOffer.getPRICE_PER_PRODUCT()));
            System.out.println("Min Quantity: " + String.format(("%d"), supplyOffer.MIN_QUANTITY));
            System.out.println("Date added: " + String.format(("%s"), supplyOffer.DATE_ADDED));
            System.out.println("Min Quantity: " + String.format(("%d"), supplyOffer.getMIN_QUANTITY()));
            System.out.println("Supply offer status active: " + String.format(("%s"), supplyOffer.isActive()));
            System.out.println();
          }
        }else {
          super.show("There is no supply offer for this product");
        }
      }
}
