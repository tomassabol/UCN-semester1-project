package view;

import java.time.LocalDate;

import controller.CustomerController;
import model.Customer;
import model.CustomerType;

public class MenuUpdateCustomer extends GenericMenuInterface{
    
    private Customer customer;
    private CustomerController customerCtrl;

    public MenuUpdateCustomer(GenericMenuInterface previousInterface,
    		Customer customer){
        super(previousInterface);

        customerCtrl = new CustomerController();
        this.customer = customer;

        super.setTitle("Update " + customer.getFirstName() + " " + customer.getLastName() + "'s information");
        super.addMenuOption("1", new GenericMenuOption("Update first name", () -> updateFirstName()));
        super.addMenuOption("2", new GenericMenuOption("Update last name", () -> updateLastName()));
        super.addMenuOption("3", new GenericMenuOption("Update address", () -> updateAddress()));
        super.addMenuOption("4", new GenericMenuOption("Udpate phone number", () -> updateMobile()));
        super.addMenuOption("5", new GenericMenuOption("Update customer type", () -> updateCustomerType()));
        super.addMenuOption("6", new GenericMenuOption("Update birth date", () -> updateBirthDate()));
        super.addMenuOptionGoBack("0");
    }

    /**
     * Updates the first name of the customer
     */
    private void updateFirstName(){
    	Terminal terminal = getTerminal();
        terminal.clearScreen();

        String newFirstName = terminal.getStringInput("New first name" + customer.getFirstName());
        if (terminal.confirmInput("Update customer's name from " + customer.getFirstName() + " to " + newFirstName + " ")) {
        	super.show("Customer's name was updated from " + customer.getFirstName() + " to " + newFirstName);
        	customerCtrl.updateFirstName(customer, newFirstName);
        }
        
        super.show();
    }

    /**
     * Updates the last name of the customer
     */
    private void updateLastName(){
    	Terminal terminal = getTerminal();
        terminal.clearScreen();

        String newLastName = terminal.getStringInput("New last name" + customer.getFirstName());
        if (terminal.confirmInput("Update customer's last name from " + customer.getLastName() + " to " + newLastName + " ")) {
        	super.show("Customer's last name was updated from " + customer.getLastName() + " to " + newLastName);
        	customerCtrl.updateLastName(customer, newLastName);
        }
        
        super.show();
    }

    /**
     * Updates the address of the customer
     */
    private void updateAddress(){
    	Terminal terminal = getTerminal();
        terminal.clearScreen();

        String newAddress = terminal.getStringInput("New address for " + customer.getFirstName());
        if (terminal.confirmInput("Update customer's address from " + customer.getAddress() + " to " + newAddress + " ")) {
        	super.show("Customer's address was updated from " + customer.getAddress() + " to " + newAddress);
        	customerCtrl.updateAddress(customer, newAddress);
        }
        
        super.show();
    }

    /**
     * Updates the phone number of the customer
     */
    private void updateMobile(){
    	Terminal terminal = getTerminal();
        terminal.clearScreen();

        String newMobile = terminal.getStringInput("New mobile number for " + customer.getFirstName());
        if (terminal.confirmInput("Update customer's mobile number from " + customer.getMobile() + " to " + newMobile + " ")) {
        	super.show("Customer's mobile number was updated from " + customer.getMobile() + " to " + newMobile);
        	customerCtrl.updateMobile(customer, newMobile);
        }
        
        super.show();
    }
    
    /**
     * Updates the birth date
     */
    private void updateBirthDate(){
    	Terminal terminal = getTerminal();
        terminal.clearScreen();

        LocalDate newBirthDate = terminal.getDateInput("New birth date for " + customer.getFirstName());
        if (terminal.confirmInput("Update customer's birth date from " + customer.getBirthDate() + " to " + newBirthDate + " ")) {
        	super.show("Customer's birth date was updated from " + customer.getBirthDate() + " to " + newBirthDate);
        	customerCtrl.updateBirthDate(customer, newBirthDate);
        }
        
        super.show();
    }

    /**
     * Updates the customer type
     */
    private void updateCustomerType(){
    	Terminal terminal = getTerminal();
        terminal.clearScreen();
        
        Customer customer = terminal.getCustomer();
        
        CustomerType newCustomerType = terminal.getCustomerType("Choose customer type");
        if (terminal.confirmInput("Update customer type from " + customer.getCustomerType().getName() + " to " + newCustomerType.getName() + " ")) {
        	super.show("Customer's birth date was updated from " + customer.getCustomerType().getName() + " to " + newCustomerType.getName());
        	customerCtrl.updateCustomerType(customer, newCustomerType);
        }
        
        super.show();
    }

}
