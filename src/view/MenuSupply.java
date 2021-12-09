package view;

import java.math.BigDecimal;

import controller.StockController;
import controller.SupplyController;
import model.Contractor;
import model.Product;
import model.Shelf;
import model.SupplyOffer;
import model.SupplyOrder;

public class MenuSupply extends GenericMenuInterface {
    private static MenuSupply instance;
    
    private SupplyController supplyCtrl;
    private StockController stockCtrl;
    /**
     * Constructor for MenuProduct
     */
    private MenuSupply(){
        super();

        super.setTitle("Supply Menu");
        super.addMenuOption("1", new GenericMenuOption("Create a supply offer", () -> createSupplyOffer()));
        super.addMenuOption("2", new GenericMenuOption("Show all supply offers", () -> showAllSupplyOffers()));
        super.addMenuOption("3", new GenericMenuOption("Set status of supply offer", () -> setSupplyOfferStatus()));
        super.addMenuOption("4", new GenericMenuOption("Create a supply order", () -> createSupplyOrder()));
        super.addMenuOption("5", new GenericMenuOption("Show all supply orders", () -> showAllSupplyOrders()));
        super.addMenuOption("6", new GenericMenuOption("Show undelivered supply orders", () -> showUndeliveredSupplyOrders()));
        super.addMenuOption("7", new GenericMenuOption("Show delivered supply orders", () -> showDeliveredSupplyOrders()));
        super.addMenuOption("8", new GenericMenuOption("Stock and mark Supply Order as delivered", () -> stockAndMarkDelivered()));
        super.addMenuOption("0", new GenericMenuOption("Go back to main menu", () -> MenuMain.getInstance().show()));

        supplyCtrl = new SupplyController();
    }
    /**
     * @return the instance of MenuProduct
     */
    public static MenuSupply getInstance() {
        if (instance == null){
            instance = new MenuSupply();
        }
        return instance;
    }

    /**
     * Creates new supply offer
     */
    private void createSupplyOffer(){
        Terminal terminal = Terminal.getInstance();
        terminal.clearScreen();
        
        Product product = terminal.getProduct();
        Contractor contractor = terminal.getContractor();
        BigDecimal pricePerItem = terminal.getBigDecimalInput("Price per Item");
        int minQuantity = terminal.getIntegerInput("The minimum quantity of the product");
        supplyCtrl.createSupplyOffer(product, contractor, pricePerItem, minQuantity);
        super.show("The offer was susccessfully created");
        System.out.println();
        terminal.getAnyKeyInput("Press [Enter] to go back");
        super.show();
    }


    /**
     * Displays all the supplyoffers
     */
    private void showAllSupplyOffers(){
        Terminal terminal = Terminal.getInstance();
        terminal.clearScreen();

        Product product = terminal.getProduct();
        System.out.println("[All SupplyOffers in the System]");
        terminal.printSuplyOffers(product);
        System.out.println();
        terminal.getAnyKeyInput("Press [Enter] to go back");
        super.show();
    }

    
    /**
     * Changes specific supply offer status to not active (false)
     */
    private void setSupplyOfferStatus(){
        Terminal terminal = Terminal.getInstance();
        terminal.clearScreen();

        Product product = terminal.getProduct();
        System.out.println("All Supply offers in the System");
        terminal.printSuplyOffers(product);
        int supplyOfferId = terminal.getIntegerInput("Enter the Supply Offer ID");
        SupplyOffer supplyOffer = supplyCtrl.findSupplyOfferByID(supplyOfferId);
        supplyCtrl.setStatus(supplyOffer);
        super.show("Supply offer was set to " + supplyOffer.isActive());
        System.out.println();
        terminal.getAnyKeyInput("Press [Enter] to go back");
        super.show();
    }
    

    /**
     * Creates a new supply offer
     */
    private void createSupplyOrder(){
        Terminal terminal = Terminal.getInstance();
        terminal.clearScreen();

        Product product = terminal.getProduct();
        terminal.printSuplyOffers(product);
        int id = terminal.getIntegerInput("Enter the ID of a supply offer");
        SupplyOffer supplyOffer = supplyCtrl.findSupplyOfferByID(id);
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
        Terminal terminal = Terminal.getInstance();
        terminal.clearScreen();

        System.out.println("[All Supply orders in the System]");
        terminal.printAllSupplyOrders();
        System.out.println();
        terminal.getAnyKeyInput("Press [Enter] to go back");
        super.show();
    }

    /**
     * Prints all undelivered Supply orders
     */
    private void showUndeliveredSupplyOrders() {
        Terminal terminal = Terminal.getInstance();
        terminal.clearScreen();

        System.out.println("[All Undelivered Supply orders in the System]");
        terminal.printUndeliveredSupplyOrders();
        System.out.println();
        terminal.getAnyKeyInput("Press [Enter] to go back");
        super.show();
    }

    /**
     * Prints all delivered supply orders
     */
    private void showDeliveredSupplyOrders() {
        Terminal terminal = Terminal.getInstance();
        terminal.clearScreen();

        System.out.println("[All Delivered Supply orders in the System]");
        terminal.printDeliveredSupplyOrders();
        System.out.println();
        terminal.getAnyKeyInput("Press [Enter] to go back");
        super.show();
    }

    /**
     * Puts products from supply order into stock - into specific shelf 
     * - and marks the supply order as delivere
     */
    private void stockAndMarkDelivered() {
        Terminal terminal = Terminal.getInstance();
        terminal.clearScreen();

        terminal.printAllSupplyOrders();
        int supplyOrderId = terminal.getIntegerInput("Enter the id of a supply order");
        SupplyOrder supplyOrder = supplyCtrl.findSupplyOrderById(supplyOrderId);
        terminal.printAllShelves();

        System.out.println();

        int shelfId = terminal.getIntegerInput("Enter the id of a shelf to put delivered product");
        Shelf shelf = stockCtrl.findShelfById(shelfId);
        boolean trackable = terminal.confirmInput("Does the Item have a serial number?");
        supplyCtrl.StockAndMarkDelivered(supplyOrder, shelf, trackable);
        super.show("Success");
        System.out.println();
        terminal.getAnyKeyInput("Press [Enter] to go back");
        super.show();
    }

}
