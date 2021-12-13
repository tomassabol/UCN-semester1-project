package view;

import controller.StockController;
import controller.SupplyController;
import model.Shelf;
import model.StorageLocation;
import model.SupplyOrder;

public class MenuStock extends GenericMenuInterface {

    private StockController stockCtrl;
    private SupplyController supplyCtrl;

    /**
     * Constructor class MenuStock
     */
    public MenuStock(GenericMenuInterface previousInterface) {
        super(previousInterface);

        super.setTitle("Stock Menu");
        super.addMenuOption("1", new GenericMenuOption("Create new Storage Location", () -> createStorageLocation()));
        super.addMenuOption("2", new GenericMenuOption("Create new Shelf", () -> createShelf()));
        super.addMenuOption("3", new GenericMenuOption("Show all Storage Locations", () -> showAllStorageLocations()));
        super.addMenuOption("4", new GenericMenuOption("Show all Shelves", () -> showAllShelves()));
        super.addMenuOption("5", new GenericMenuOption("Add items to stock", () -> stockAndMarkDelivered()));
        super.addMenuOptionGoBack("0");

        stockCtrl = new StockController();
        supplyCtrl = new SupplyController();
    }

    /**
     * create a storage location within the system
     */
    private void createStorageLocation() {
    	Terminal terminal = getTerminal();
        terminal.clearScreen();

        String name = terminal.getStringInput("Enter the name of a new Storage Location");
        String address = terminal.getStringInput("Enter the address of a new Storage Location");
        boolean isAStore = terminal.confirmInput("Is this Storage Location a store?");
        stockCtrl.createStorageLocation(name, address, isAStore);
        super.show("New storage location was successfully created");
        // TODO: Add confirmation
        
    }

    /**
     * create a shelf within the System
     */
    private void createShelf() {
    	Terminal terminal = getTerminal();
        terminal.clearScreen();

        String name = terminal.getStringInput("The shelf's name/identifier:");
        StorageLocation storageLocation = terminal.getStorageLocation();
        stockCtrl.createShelf(name, storageLocation);
        super.show("The Shelf was created successfully");
        // TODO: add confirmation
    }

    /**
     * prints all storage locations within the system and its information 
     */
    private void showAllStorageLocations() {
    	Terminal terminal = getTerminal();
        terminal.clearScreen();

        terminal.printStorageLocations();
        System.out.println();
        terminal.getAnyKeyInput("Press [Enter] to go back");
        super.show();
    }

    /**
     * prints all shelves within the system and its information
     */
    private void showAllShelves() {
    	Terminal terminal = getTerminal();
        terminal.clearScreen();

        StorageLocation storageLocation = terminal.getStorageLocation();
        terminal.clearScreen();
        
        terminal.printShelves(storageLocation);
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

        SupplyOrder supplyOrder = terminal.getSupplyOrder();
        terminal.clearScreen();
        
        StorageLocation storageLocation = terminal.getStorageLocation();
        terminal.clearScreen();
        
        Shelf shelf = terminal.getShelf(storageLocation);
        terminal.clearScreen();
        
        boolean trackable = terminal.confirmInput("Are the items trackable? ");
        supplyCtrl.StockAndMarkDelivered(supplyOrder, shelf, trackable);
        super.show("Success");
        System.out.println();
        terminal.getAnyKeyInput("Press [Enter] to go back");
        super.show();
        
        // TODO: ask the user to enter serial numbers manually or automatically gneerate
        // TODO: confirmation
    }

}
