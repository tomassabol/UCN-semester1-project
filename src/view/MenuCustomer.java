package view;

import java.time.LocalDateTime;

import controller.CustomerController;
import model.Customer;
import model.CustomerType;

public class MenuCustomer extends GenericMenuInterface{
    
    private static MenuCustomer instance;

    private CustomerController customerCtrl;

    private MenuCustomer(){
        super();

        super.setTitle("Customer menu");
        super.addMenuOption("1", new GenericMenuOption("Add new Customer", () -> createCustomer()));
        super.addMenuOption("2", new GenericMenuOption("List all Customers in the system", () -> showAllCustomers()));
        super.addMenuOption("3", new GenericMenuOption("Update customer information", () -> updateCustomerInfo()));
        super.addMenuOption("4", new GenericMenuOption("Delete Customer from the system", () -> deleteCustomer()));
        super.addMenuOption("5", new GenericMenuOption("Handle Cusotmer Types", () -> showMenuCustomerType()));
        super.addMenuOption("0", new GenericMenuOption("Go back to main menu", () -> MenuMain.getInstance().show()));

        customerCtrl = new CustomerController();
    }

    public static MenuCustomer getInstance(){
        if(instance == null){
            instance = new MenuCustomer();
        }
        return instance;
    }

    private void createCustomer(){
        Terminal terminal = Terminal.getInstance();
        terminal.clearScreen();

        String firstName = terminal.getStringInput("First name of the new customer");
        String lastName = terminal.getStringInput("Last name of the new customer");
        String address = terminal.getStringInput("Address of the new customer");
        String mobile = terminal.getStringInput("Phone number of the new customer");
        CustomerType customerType = customerCtrl.findCustomerTypeById(terminal.getIntegerInput("The id of the customer type"));
        LocalDateTime birthDate = terminal.getDateInput("Birth date of the new customer");

        customerCtrl.createCustomer(firstName, lastName, address, mobile, customerType, birthDate);
        super.show("Customer was succsesfully created!");
    }

    // may be needed in the future
    /*
    private String getCustomerTypeNames() {
        for (CustomerType customerType : customerCtrl.getCustomerTypes()) {
            customerType.getName();
        }
        return null;
    }
    */

    /**
     * Prints out all the customers informations
     */
    private void showAllCustomers(){
        Terminal terminal = Terminal.getInstance();
        terminal.clearScreen();

        System.out.println("[All Customers in the System]");
        printCustomers();
        terminal.getAnyKeyInput("Press [Enter] to go back");
        super.show();
    }

    /**
     * Updates customer info
     */
    private void updateCustomerInfo(){
        Terminal terminal = Terminal.getInstance();
        terminal.clearScreen();

        int id = terminal.getIntegerInput("The id of the Customer you want to update");
        if(isEmpty(id)){
            super.show("There is no Customer with that id in the system");
        }else{
            MenuUpdateCustomer updateMenu = new MenuUpdateCustomer(id);
            updateMenu.show();
        }
        terminal.getAnyKeyInput("Press [Enter] to go back");
        super.show();
    }

    /**
     * Removes a customer from the customers ArrayList
     */
    private void deleteCustomer(){
        Terminal terminal = Terminal.getInstance();
        terminal.clearScreen();

        int id = terminal.getIntegerInput("The Id of the customer to be removed");
        customerCtrl.removeCustomer(id);
    }

    /**
    * Show CustomerType menu.
    */
    private void showMenuCustomerType(){
        MenuCustomerType.getInstance().show();
    }

    /**
     * @param id The id of a product
     * @return true if the system can't find the product
     */
    private boolean isEmpty(int id){
        if(customerCtrl.findCustomerByID(id) == null){
            return true;
        }else{
            return false;
        }
    }

    private void printCustomers() {
        for (Customer customer  : customerCtrl.getCustomers()) {
            System.out.println("Customer ID: " + String.format("(%d)",customer.ID));
            System.out.println("First name: " + String.format("%s", customer.getFirstName()));
            System.out.println("Last Name: " + String.format("%s", customer.getLastName()));
            System.out.println("Address: " + String.format("%s", customer.getAddress()));
            System.out.println("Phone Number: " + String.format("%s", customer.getMobile()));
            System.out.println("Customer type: " + String.format("%s", customer.getCustomerType().getName()));
            System.out.println("Birthdate: " + String.format("%s", customer.getBirthDate()));
            System.out.println();
        }
    }

}
