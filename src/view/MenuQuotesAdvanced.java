package view;

import controller.QuoteController;
import model.Customer;
import model.Quote;

public class MenuQuotesAdvanced extends GenericMenuInterface {
    private Quote quote;
    QuoteController quoteCtrl;

    public MenuQuotesAdvanced(GenericMenuInterface previousInterface) {
        super(previousInterface);
        quoteCtrl = new QuoteController();

        this.setTitle("Advanced Quote Menu");

        Terminal terminal = Terminal.getInstance();
        terminal.clearScreen();

        terminal.printAllCustomers();
        Customer customer = terminal.getCustomer();
        terminal.clearScreen();
        terminal.printQuotes(customer);
        quote = quoteCtrl.findQuoteById(terminal.getIntegerInput("Enter the QuoteID"));
        System.out.println("ID: " + quote.ID + "Creation date: " + quote.getCreationDate());

        super.addMenuOption("1", new GenericMenuOption("Pay for quote", () -> function()));
        super.addMenuOption("2", new GenericMenuOption("Delete quote", () -> deleteQuote()));
        super.addMenuOptionGoBack("0");
    }


    public void function() {}
    public void deleteQuote() {
        Terminal terminal = Terminal.getInstance();
        terminal.clearScreen();

        quoteCtrl.removeQuote(quote);
        terminal.confirmInput("Are you sure you want to remove quote " + quote.ID + " ?");
        super.show("Quote successfully deleted");
        terminal.getAnyKeyInput("Press [Enter] to go back");
        super.show();
    }

}
