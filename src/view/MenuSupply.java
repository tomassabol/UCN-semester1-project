package view;

import java.math.BigDecimal;

import controller.ProductController;
import controller.SupplyController;
import model.Contractor;
import model.Product;
import model.SupplyOffer;

public class MenuSupply extends GenericMenuInterface{
    private static MenuSupply instance;
    
    private SupplyController supplyCtrl;
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
        super.addMenuOption("5", new GenericMenuOption("Show all supply offers", () -> showAllSupplyOrders()));
        super.addMenuOption("0", new GenericMenuOption("Go back to main menu", () -> MenuMain.getInstance().show()));

        supplyCtrl = new SupplyController();
    }
    /**
     * @return the instance of MenuProduct
     */
    public static MenuSupply getInstance(){
        if(instance == null){
            instance = new MenuSupply();
        }
        return instance;
    }

    /**
     * 
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
    }


    /**
     * Displays all the supplyoffers
     */
    private void showAllSupplyOffers(){
        Terminal terminal = Terminal.getInstance();
        terminal.clearScreen();

        System.out.println("[All SupplyOffers in the System]");
        supplyCtrl.printAllSupplyOffers();
        terminal.getStringInput("Press [Enter] to go back");
        super.show();
    }

    private void setSupplyOfferStatus(){
        Terminal terminal = Terminal.getInstance();
        terminal.clearScreen();
    }

    private void createSupplyOrder(){
        Terminal terminal = Terminal.getInstance();
        terminal.clearScreen();

        Product product = terminal.getProduct();
        int index = terminal.getIntegerInput("Enter the index of a supply offer");
        SupplyOffer supplyOffer = supplyCtrl.findSupplyOfferByID(product, index);
        int quantity = terminal.getIntegerInput("Enter the quantity of the product");
        supplyCtrl.createSupplyOrder(supplyOffer, quantity);
        super.show("The supply order was susccessfully created");
    }


    /**
     * Displays all the supplyoffers
     */
    private void showAllSupplyOrders(){
        Terminal terminal = Terminal.getInstance();
        terminal.clearScreen();

        System.out.println("[All Supply orders in the System]");
        supplyCtrl.printAllSupplyOrderInfo();
        terminal.getStringInput("Press [Enter] to go back");
        super.show();
    }
}
