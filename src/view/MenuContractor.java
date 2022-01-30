package view;

import controller.ContractorController;
import models.Contractor;

public class MenuContractor extends GenericMenuInterface{

    private ContractorController contractorCtrl;
    
    /**
     * Constructor for MenuProduct
     */
    public MenuContractor(GenericMenuInterface previousInterface) {
        super(previousInterface);

        super.setTitle("Contractor Menu");
        super.addMenuOption("1", new GenericMenuOption("Create a Contractor", () -> createContractor()));
        super.addMenuOption("2", new GenericMenuOption("Show all Contractors", () -> showAllContractors()));
        super.addMenuOption("3", new GenericMenuOption("Delete a Contractor", () -> deleteContractor()));
        super.addMenuOption("4", new GenericMenuOption("Update Contractor's company name", () -> updateName()));
        super.addMenuOptionGoBack("0");

        contractorCtrl = new ContractorController();
    }

    /**
     * Creates a new Contractor
     */
    private void createContractor(){
    	Terminal terminal = getTerminal();
        terminal.clearScreen();
        String Name = terminal.getStringInput("Name of the contractor's company");
        if(terminal.confirmInput()){
            contractorCtrl.createContractor(Name);
            super.show("New contractor was successfully created!");
        }
        terminal.getAnyKeyInput("Press [Enter] to go back");
        super.show();
    }

    /**
     * Displays all the contractors
     */
    private void showAllContractors(){
    	Terminal terminal = getTerminal();
        terminal.clearScreen();
        
        terminal.printContractors(contractorCtrl.getContractors());
        System.out.println();

        terminal.getAnyKeyInput("Press [Enter] to go back");
        super.show();
    }

    /**
     * Deletes a contractor.
     */
    private void deleteContractor() {
    	Terminal terminal = getTerminal();
        terminal.clearScreen();

        Contractor contractor = terminal.getContractor();
        if (terminal.confirmInput("Are you sure you wish to delete the contractor: " + contractor.getCompanyName())) {
        	contractorCtrl.removeContractor(contractor);
        	super.show("The contractor was deleted!");
        }
        
        super.show();
    }



    public void updateName(){
        Terminal terminal = getTerminal();
        terminal.clearScreen();

        Contractor contractor = terminal.getContractor();
        terminal.clearScreen();
        
        System.out.println("Contractor: " + contractor.getCompanyName());
        String name = terminal.getStringInput("The new company name: ");
        
        if (terminal.confirmInput("Update " + contractor.getCompanyName() + " to " + name + "?")) {
        	contractorCtrl.updateCompanyName(contractor, name);
        	super.show("Successfully updated contractor's company name");
        }
        
        super.show();
        
        
    }

}