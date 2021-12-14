package view;

import java.math.BigDecimal;

import controller.*;
import model.*;

public class MenuSupply extends GenericMenuInterface {
    
    private SupplyController supplyCtrl;
    private StockController stockCtrl;
    private ProductController productCtrl;
    private ContractorController contractorCtrl;
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
        super.addMenuOptionGoBack("0");

        supplyCtrl = new SupplyController();
        stockCtrl = new StockController();
        productCtrl = new ProductController();
        contractorCtrl = new ContractorController();
    }

    /**
     * Creates new supply offer
     */
    private void createSupplyOffer(){
    	Terminal terminal = getTerminal();
        terminal.clearScreen();

        Product product = terminal.getProduct();
        Contractor contractor = terminal.getContractor();
        BigDecimal pricePerItem = terminal.getBigDecimalInput("Price per Item", 0, Integer.MAX_VALUE);
        int minQuantity = terminal.getIntegerInput("Minimum quantity that must be bought", 0, Integer.MAX_VALUE);
        
        // TODO: Customize confirmation
        if (terminal.confirmInput()) {
        	supplyCtrl.createSupplyOffer(product, contractor, pricePerItem, minQuantity);
        	super.show("The offer was susccessfully created");
        }
        super.show();
       

    }


    /**
     * Displays all the supplyoffers
     */
    private void showAllSupplyOffers(){
    	Terminal terminal = getTerminal();
        terminal.clearScreen();

        Product product = terminal.getProduct();
        terminal.printSupplyOffers(product);
        System.out.println();
        terminal.getAnyKeyInput();
        super.show();
    }

    
    /**
     * Changes specific supply offer status to not active (false)
     */
    private void setSupplyOfferStatus(){
    	Terminal terminal = getTerminal();
        terminal.clearScreen();

        Product product = terminal.getProduct();
        terminal.clearScreen();
        
        SupplyOffer supplyOffer = terminal.getSupplyOffer(product);
        
        boolean status = terminal.confirmInput("Should this supply offer be active?");
        supplyCtrl.setStatus(supplyOffer, status);
        
        super.show("Supply offer status was set to " + supplyOffer.isActive());
        
        
    }
    

    /**
     * Creates a new supply offer
     */
    private void createSupplyOrder(){
    	Terminal terminal = getTerminal();
        terminal.clearScreen();

        Product product = terminal.getProduct();
        SupplyOffer supplyOffer = terminal.getSupplyOffer(product);
        int quantity = terminal.getIntegerInput("Enter the quantity of the product", 1, Integer.MAX_VALUE);
        supplyCtrl.createSupplyOrder(supplyOffer, quantity);
        super.show("The supply order was susccessfully created");
        System.out.println();
        terminal.getAnyKeyInput("Press [Enter] to go back");
        super.show();
        // TODO: add confirmation
    }


    /**
     * Displays all the supplyoffers
     */
    private void showAllSupplyOrders (){
    	Terminal terminal = getTerminal();
        terminal.clearScreen();

        Product product = terminal.getProduct();
        terminal.printSupplyOffers(product);
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

        terminal.printUndeliveredSupplyOrders();
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

        terminal.printDeliveredSupplyOrders();
        System.out.println();
        terminal.getAnyKeyInput("Press [Enter] to go back");
        super.show();
    }


}
