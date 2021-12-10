package view;

import java.time.LocalDate;
import java.time.LocalDateTime;

import controller.CustomerController;
import model.Customer;
import model.CustomerType;

public class MenuUpdateCustomer extends GenericMenuInterface{
    
    private int customerId;
    private CustomerController customerCtrl;

    public MenuUpdateCustomer(GenericMenuInterface previousInterface,
    		int id){
        super(previousInterface);

        customerCtrl = new CustomerController();
        Customer customer = customerCtrl.findCustomerByID(id);
        customerId = id;

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
        Terminal terminal = Terminal.getInstance();
        terminal.clearScreen();

        String newFirstName = terminal.getStringInput("New first name");
        customerCtrl.updateFirstName(customerId, newFirstName);
        super.show("The first name was successfully updated");
    }

    /**
     * Updates the last name of the customer
     */
    private void updateLastName(){
        Terminal terminal = Terminal.getInstance();
        terminal.clearScreen();

        String newLastName = terminal.getStringInput("New last name");
        customerCtrl.updateLastName(customerId, newLastName);
        super.show("The last name was successfully updated");
    }

    /**
     * Updates the address of the customer
     */
    private void updateAddress(){
        Terminal terminal = Terminal.getInstance();
        terminal.clearScreen();

        String newAddress = terminal.getStringInput("The new address");
        customerCtrl.updateAddress(customerId, newAddress);
        super.show("The address was successfully updated");
    }

    /**
     * Updates the phone number of the customer
     */
    private void updateMobile(){
        Terminal terminal = Terminal.getInstance();
        terminal.clearScreen();

        String newMobile = terminal.getStringInput("The new phone number");
        customerCtrl.updateMobile(customerId, newMobile);
        super.show("The phone number was successfully updated");
    }

    /**
     * Updates the customer type
     */
    private void updateCustomerType(){
        Terminal terminal = Terminal.getInstance();
        terminal.clearScreen();
        
        CustomerType customerType = customerCtrl.findCustomerTypeById(terminal.getIntegerInput("Customer Type id"));
        customerCtrl.updateCustomerType(customerId, customerType);
        super.show("The customer type was successfully updated");
    }

    /**
     * Updates the birth date
     */
    private void updateBirthDate(){
        Terminal terminal = Terminal.getInstance();
        terminal.clearScreen();

        LocalDate birthDate = terminal.getDateInput("The new birth date");
        customerCtrl.updateBirthDate(customerId, birthDate);
        super.show("The birth date was successfully updated");
    }
}
