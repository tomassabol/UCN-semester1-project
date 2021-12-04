package view;

import controller.ContractorController;

public class MenuContractor extends GenericMenuInterface{

private static MenuContractor instance;
private ContractorController contractorCtrl;
/**
 * Constructor for MenuProduct
 */
private MenuContractor(){
    super();

    super.setTitle("Contractor Menu");
    super.addMenuOption("1", new GenericMenuOption("Create a Contractor", () -> createContractor()));
    super.addMenuOption("2", new GenericMenuOption("Show all Contractor", () -> showAllContractors()));
    super.addMenuOption("3", new GenericMenuOption("Delete a Contractor", () -> deleteContractor()));
    super.addMenuOption("0", new GenericMenuOption("Go back to main menu", () -> MenuMain.getInstance().show()));

    contractorCtrl = new ContractorController();
}

/**
 * @return the instance of MenuContractor
 */
public static MenuContractor getInstace() {
    if(instance == null){
        instance = new MenuContractor();
    }
    return instance;
}

/**
 * Creates a new Contractor
 */
private void createContractor(){
    Terminal terminal = Terminal.getInstance();
    terminal.clearScreen();
    String cName = terminal.getStringInput("Name of the contractor");
    if(terminal.confirmInput()){
    	contractorCtrl.createContractor(cName);
        super.show("New contractor was successfully created!");
    }else{
        super.show("New contractor creation was discarded!");
    }
}

/**
 * Displays all the contractors
 */
private void showAllContractors(){
    Terminal terminal = Terminal.getInstance();
    terminal.clearScreen();

    System.out.println("[All Contractors in the System]");
    contractorCtrl.printAllContractors();
    terminal.getStringInput("Press [Enter] to go back");
    super.show();
}

/**
 * Deletes a contractor
 */
private void deleteContractor(){
    Terminal terminal = Terminal.getInstance();
    terminal.clearScreen();

    int id = terminal.getIntegerInput("The id of the contractor you want to delete");
    contractorCtrl.removeContractor(id);
    super.show("The product was deleted!");
}




}