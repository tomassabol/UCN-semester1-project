package view;

import controller.CustomerController;

public class MenuCustomerType extends GenericMenuInterface{
    
    private CustomerController customerCtrl;

    public MenuCustomerType(GenericMenuInterface previousInterface){
        super(previousInterface);

        super.setTitle("Customer Type menu");
        super.addMenuOption("1", new GenericMenuOption("Add customer type", () -> createCustomerType()));
        super.addMenuOption("2", new GenericMenuOption("Show all customer types", () -> showAllCustomerTypes()));
        super.addMenuOption("3", new GenericMenuOption("Update customer type name", () -> updateCustomerTypeName()));
        super.addMenuOption("4", new GenericMenuOption("Update customer type discount percantage", () -> updateCustomerTypeDiscountPercantage()));
        super.addMenuOption("5", new GenericMenuOption("Delete customer type", () -> deleteCustomerType()));
        super.addMenuOptionGoBack("0");

        customerCtrl = new CustomerController();
    }

    /**
     * Creates a new customer type
     */
    private void createCustomerType(){
        Terminal terminal = Terminal.getInstance();
        terminal.clearScreen();

        String name = terminal.getStringInput("The name of the customer type");
        int discountPercentage = terminal.getIntegerInput("The discount percantage of the new customer type");
        customerCtrl.createCustomerType(name, discountPercentage);
        super.show("The customer type was successfully created");
    }

    private void showAllCustomerTypes(){
        Terminal terminal = Terminal.getInstance();
        terminal.clearScreen();
        terminal.printCustumerTypes();
        terminal.getAnyKeyInput("Press [Enter] to go back");
        super.show();     
    }

    private void updateCustomerTypeName(){
        Terminal terminal = Terminal.getInstance();
        terminal.clearScreen();

        int id = terminal.getIntegerInput("The id of the customer type to be updated");
        String newName = terminal.getStringInput("The new name of the customer type");
        customerCtrl.updateCustomerTypeName(id, newName);
        super.show("The name was successfully updated");
    }

    private void updateCustomerTypeDiscountPercantage(){
        Terminal terminal = Terminal.getInstance();
        terminal.clearScreen();

        int id = terminal.getIntegerInput("The id of the customer type to be updated");
        int newDiscountPercentage = terminal.getIntegerInput("The new discount percantage");
        customerCtrl.updateCustomerTypeDiscountPercantage(id, newDiscountPercentage);
        super.show("The discount percantage was successfully updated");
    }

    private void deleteCustomerType(){
        Terminal terminal = Terminal.getInstance();
        terminal.clearScreen();

        int id = terminal.getIntegerInput("The id of the customer type to be updated");
        customerCtrl.deleteCustomerType(id);
        super.show("The customer type was deleted");
    }
}
