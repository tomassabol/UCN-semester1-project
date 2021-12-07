package view;

import controller.StockController;
import model.StorageLocation;

public class MenuStock extends GenericMenuInterface {
    private static MenuStock instance;

    private StockController stockCtrl;

    private MenuStock() {
        super();

        super.setTitle("Stock Menu");
        super.addMenuOption("1", new GenericMenuOption("Create new Storage Location", () -> createStorageLocation()));
        super.addMenuOption("2", new GenericMenuOption("Create new Shelf", () -> createShelf()));
        super.addMenuOption("0", new GenericMenuOption("Go back to main menu", () -> MenuMain.getInstance().show()));

        stockCtrl = new StockController();
    }

    public static MenuStock getInstance() {
        if (instance == null) {
            instance = new MenuStock();
        }
        return instance;
    }

    private void createStorageLocation() {
        Terminal terminal = Terminal.getInstance();
        terminal.clearScreen();

        String name = terminal.getStringInput("Enter the name of a new Storage Location");
        String address = terminal.getStringInput("Enter the address of a new Storage Location");
        boolean isAStore = terminal.confirmInput("Is this Storage Location a store?");
        stockCtrl.createStorageLocation(name, address, isAStore);
        super.show("New storage location was successfully created");
        terminal.getStringInput("Press [Enter] to go Back");
        super.show();
    }

    private void createShelf() {
        Terminal terminal = Terminal.getInstance();
        terminal.clearScreen();

        String name = terminal.getStringInput("Enter name of a new Shelf");
        stockCtrl.printAllStorageLocationInfo();
        int storageLocationId = terminal.getIntegerInput("Enter the id of a Storage Location, where the shelf will be lcoated");
        StorageLocation storageLocation = stockCtrl.findStorageLocationById(storageLocationId);
        stockCtrl.createShelf(name, storageLocation);
        super.show("New Shelf was successfully created");
        terminal.getStringInput("Press [Enter] to go Back");
        super.show();
    }

}
