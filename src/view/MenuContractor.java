package view;

import controller.ContractorController;
import model.Contractor;

public class MenuContractor extends GenericMenuInterface{

    private ContractorController contractorCtrl;
    
    /**
     * Constructor for MenuProduct
     */
    public MenuContractor(GenericMenuInterface previousInterface) {
        super(previousInterface);

        super.setTitle("Contractor Menu");
        super.addMenuOption("1", new GenericMenuOption("Create a Contractor", () -> createContractor()));
        super.addMenuOption("2", new GenericMenuOption("Show all Contractor", () -> showAllContractors()));
        super.addMenuOption("3", new GenericMenuOption("Delete a Contractor", () -> deleteContractor()));
        super.addMenuOptionGoBack("0");

        contractorCtrl = new ContractorController();
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
        terminal.getAnyKeyInput("Press [Enter] to go back");
        super.show();
    }

    /**
     * Displays all the contractors
     */
    private void showAllContractors(){
        Terminal terminal = Terminal.getInstance();
        terminal.clearScreen();

        System.out.println("[All Contractors in the System]");
        printContractors();
        terminal.getAnyKeyInput("Press [Enter] to go back");
        super.show();
    }

    /**
     * Deletes a contractor
     */
    private void deleteContractor() {
        Terminal terminal = Terminal.getInstance();
        terminal.clearScreen();

        printContractors();
        int id = terminal.getIntegerInput("The id of the contractor you want to delete");
        contractorCtrl.removeContractor(id);
        super.show("The contractor was deleted!");
        terminal.getAnyKeyInput("Press [Enter] to go back");
        super.show();
    }

    public void printContractors() {
        for (Contractor contractor : contractorCtrl.getContractors()) {
          System.out.println("Contractor ID: " + String.format(("%d"), contractor.ID));
          System.out.println("Contractor Company name: " + String.format(("%d"), contractor.getCompanyName()));
          System.out.println();
        }
      }

}