package view;

import java.time.LocalDate;

import controller.CustomerController;
import model.Customer;
import model.CustomerType;

public class MenuCustomer extends GenericMenuInterface{

    private CustomerController customerCtrl;

    public MenuCustomer(GenericMenuInterface previousInterface){
        super(previousInterface);

        super.setTitle("Customer menu");
        super.addMenuOption("1", new GenericMenuOption("Add new Customer", () -> createCustomer()));
        super.addMenuOption("2", new GenericMenuOption("Show all customers", () -> showAllCustomers()));
        super.addMenuOption("3", new GenericMenuOption("Update customer information", () -> updateCustomerInfo()));
        super.addMenuOption("4", new GenericMenuOption("Delete Customer", () -> deleteCustomer()));
        super.addMenuOption("5", new GenericMenuOption("Manage Customer Types", () -> showMenuCustomerType()));
        super.addMenuOptionGoBack("0");

        customerCtrl = new CustomerController();
    }

    private void createCustomer(){
    	Terminal terminal = getTerminal();
        terminal.clearScreen();

        String firstName = terminal.getStringInput("First name of the new customer");
        String lastName = terminal.getStringInput("Last name of the new customer");
        String address = terminal.getStringInput("Address of the new customer");
        String mobile = terminal.getStringInput("Phone number of the new customer");
        CustomerType customerType = terminal.getCustomerType("The customer type");
        LocalDate birthDate = terminal.getDateInput("Birth date of the new customer");

        if (terminal.confirmInput("All details entered. Create customer?")) {
        	customerCtrl.createCustomer(firstName, lastName, address, mobile, customerType, birthDate);
        	super.show("Customer was succsesfully created!");
        }
        
        super.show();
    }

    /**
     * Prints out all the customers informations
     */
    private void showAllCustomers(){
    	Terminal terminal = getTerminal();
        terminal.clearScreen();

        terminal.printCustomers(customerCtrl.getCustomers());
        
        terminal.getAnyKeyInput("Press [Enter] to go back");
        super.show();
    }

    /**
     * Updates customer info
     */
    private void updateCustomerInfo(){
    	Terminal terminal = getTerminal();
        terminal.clearScreen();

        Customer customer = terminal.getCustomer();
        new MenuUpdateCustomer(this, customer).show();
        
        super.show();
    }

    /**
     * Removes a customer
     */
    private void deleteCustomer(){
    	Terminal terminal = getTerminal();
        terminal.clearScreen();

        Customer customer = terminal.getCustomer();
        
        if (terminal.confirmInput("Delete customer: " + customer.getFirstName())) {
        	super.show("Successfully deleted customer: " + customer.getFirstName());
        	customerCtrl.removeCustomer(customer);
        }
        
        super.show();
        
    }

    /**
    * Show CustomerType menu.
    */
    private void showMenuCustomerType(){
    	new MenuCustomerType(this).show();
    }

}
