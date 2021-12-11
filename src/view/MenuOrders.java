package view;

import model.*;

import controller.*;

public class MenuOrders extends GenericMenuInterface {
    OrderController orderCtrl;
    
    public MenuOrders(GenericMenuInterface previousInterface) {
        super(previousInterface);

        super.setTitle("Orders");
        super.addMenuOption("1", new GenericMenuOption("Create an Order", () -> createOrder()));
        super.addMenuOption("2", new GenericMenuOption("Show all Orders", () -> showAllOrders()));
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

    public void showAllOrders() {
        Terminal terminal = Terminal.getInstance();
        terminal.clearScreen();

        for (Order order : orderCtrl.getOrders()) {
            System.out.println("Order ID: " + String.format(("%d"), order.ID));
            System.out.println("Creation date: " + String.format("%s", order.getCREATION_DATE()));
            System.out.println("Order status: " + String.format("%s", order.getStatus()));
            System.out.println("Customer: " + String.format("%s", order.getCustomer().getCustomerType().getName()));
            System.out.println("Employee: " + String.format("%s", order.getEmployee().getLastName()));
            System.out.println();

        }
        terminal.getAnyKeyInput("Press [Enter] to go back");
        super.show();
    }
}
