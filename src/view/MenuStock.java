package view;

import controller.StockController;
import model.StorageLocation;

public class MenuStock extends GenericMenuInterface {

    private StockController stockCtrl;

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
        super.addMenuOptionGoBack("0");

        stockCtrl = new StockController();
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
        terminal.getAnyKeyInput("Press [Enter] to go Back");
        super.show();
    }

    /**
     * create a shelf within the System
     */
    private void createShelf() {
    	Terminal terminal = getTerminal();
        terminal.clearScreen();

        String name = terminal.getStringInput("Enter name of a new Shelf");
        terminal.printAllStorageLocations();
        StorageLocation storageLocation = terminal.getStorageLocation();
        stockCtrl.createShelf(name, storageLocation);
        super.show("New Shelf was successfully created");
        terminal.getAnyKeyInput("Press [Enter] to go Back");
        super.show();
    }

    /**
     * prints all storage locations within the system and its information 
     */
    private void showAllStorageLocations() {
    	Terminal terminal = getTerminal();
        terminal.clearScreen();

        System.out.println("[All Storage Locations]");
        terminal.printAllStorageLocations();
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

        System.out.println("[All Shelves]");
        terminal.printAllShelves();
        terminal.getAnyKeyInput("Press [Enter] to go back");
        super.show();
    }

}
