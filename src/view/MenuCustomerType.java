package view;

import controller.CustomerController;
import model.CustomerType;

public class MenuCustomerType extends GenericMenuInterface{
    
    private CustomerController customerCtrl;

    public MenuCustomerType(GenericMenuInterface previousInterface){
        super(previousInterface);

        super.setTitle("Customer Type menu");
        super.addMenuOption("1", new GenericMenuOption("Add customer type", () -> createCustomerType()));
        super.addMenuOption("2", new GenericMenuOption("Show all customer types", () -> showAllCustomerTypes()));
        super.addMenuOption("3", new GenericMenuOption("Update customer type title", () -> updateCustomerTypeName()));
        super.addMenuOption("4", new GenericMenuOption("Update customer type discount percentage", () -> updateCustomerTypeDiscountPercantage()));
        super.addMenuOption("5", new GenericMenuOption("Delete customer type", () -> deleteCustomerType()));
        super.addMenuOptionGoBack("0");

        customerCtrl = new CustomerController();
    }

    /**
     * Creates a new customer type
     */
    private void createCustomerType(){
    	Terminal terminal = getTerminal();
        terminal.clearScreen();

        String name = terminal.getStringInput("The name of the customer type");
        int discountPercentage = terminal.getIntegerInput("The discount percentage of the new customer type", 1, 100);
        if (terminal.confirmInput("Create customer type '" + name + "'" + " with " + discountPercentage + "% discount?")) {
        	customerCtrl.createCustomerType(name, discountPercentage);
        	super.show("The customer type was successfully created");
        }
        super.show();
        
    }

    /**
     * Show all customer types.
     */
    private void showAllCustomerTypes(){
        Terminal terminal = getTerminal();
        terminal.clearScreen();

        terminal.printCustumerTypes(customerCtrl.getCustomerTypes());
        terminal.getAnyKeyInput();
        super.show();

    }

    /**
     * Update customer type name.
     */
    private void updateCustomerTypeName(){
    	Terminal terminal = getTerminal();
        terminal.clearScreen();

        CustomerType customerType = terminal.getCustomerType();
        
        String newName = terminal.getStringInput("The new name for the customer type");
        if (terminal.confirmInput(String.format("Do you wish to change customer type name from '%s' to '%s'?", 
        		customerType.getName(), newName))) {
        	customerCtrl.updateCustomerTypeName(customerType, newName);
        	super.show("The name was successfully updated");
        }
        super.show();
    }

    /**
     * Update customer type discount percantage.
     */
    private void updateCustomerTypeDiscountPercantage(){
    	Terminal terminal = getTerminal();
        terminal.clearScreen();

        CustomerType customerType = terminal.getCustomerType();
        
        int newDiscountPercentage = terminal.getIntegerInput("The new discount percentage", 1, 100);
        if (terminal.confirmInput(String.format("Do you wish to change customer type '%s' discount percentage from '%d'%% to '%d'%%?", 
        		customerType.getName(), customerType.getDiscountPercentage(), newDiscountPercentage))) {
        	customerCtrl.updateCustomerTypeDiscountPercantage(customerType, newDiscountPercentage);
        	super.show("The discount percantage was successfully updated");
        }
        super.show();
    }

    /**
     * Delete customer type.
     */
    private void deleteCustomerType(){
    	Terminal terminal = getTerminal();
        terminal.clearScreen();

        CustomerType customerType = terminal.getCustomerType();
        
        if (terminal.confirmInput("Do you wish to delete the customer type: " + customerType.getName())) {
            customerCtrl.deleteCustomerType(customerType);
            super.show("The customer type was successfully deleted!");
        }
        super.show();
    }
    
}
