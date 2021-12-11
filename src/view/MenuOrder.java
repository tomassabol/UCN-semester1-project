package view;

import model.*;
import controller.*;

public class MenuOrder extends GenericMenuInterface {
    OrderController orderCtrl;
    
    public MenuOrder(GenericMenuInterface previousInterface) {
        super.goToPreviousMenu();

        super.setTitle("Orders");
        super.addMenuOption("1", new GenericMenuOption("Create an Order", () -> createOrder()));
        super.addMenuOptionGoBack("0");

        orderCtrl = new OrderController();
    }

    public void createOrder() {
        Terminal terminal = Terminal.getInstance();
        terminal.clearScreen();

        terminal.printAllCustomers();
        Customer customer = terminal.getCustomer();
        // TODO: UNFINISHED
    }
}
