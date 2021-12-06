package view;

import java.math.BigDecimal;

import controller.SupplyController;
import model.Contractor;
import model.Product;

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
     * TODO: If there is no product and contractor in the system the user shouldn't be able to use this method
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
}
