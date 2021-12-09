package view;

import controller.EmployeeController;
import model.Employee;

public class MenuEmployee extends GenericMenuInterface {
  private static MenuEmployee instance;
  
  private EmployeeController employeeCtrl;

  /**
   * Constructor for MainMenuUI.
   */
  private MenuEmployee() {
    super();

    super.setTitle("Employees");
//    super.addMenuOption("1", new GenericMenuOption("Create an employee",
//    		() -> createEmployee()));
    super.addMenuOption("2", new GenericMenuOption("Show all employees",
    		() -> showAllEmployees()));
    super.addMenuOption("0", new GenericMenuOption("Go back to main menu",
    		() -> MenuMain.getInstance().show()));
    
    employeeCtrl = new EmployeeController();
  }

  /**
   * @return the instance of MainMenuUI
   */
  public static MenuEmployee getInstance() {
    if (instance == null) {
      instance = new MenuEmployee();
    }
    return instance;
  }
  

  // TODO: needs to adapt to QuoteItemLine (currently it is to unspecific item line)
/**
* Create Employee
*/
//	private void createEmployee() {
//		Terminal terminal = Terminal.getInstance();
//		terminal.clearScreen();
//		
//		System.out.println("[Customers]");
//		terminal.printAllCustomers();
//		Customer customer = terminal.getCustomer();
//		System.out.println("You chose customer with ID: " + customer.ID);
//		System.out.println("Customer: " + customer.getFirstName() + " " + customer.getLastName());
//		System.out.println("[Shopping cart]");
//		for(UnspecificItemLine itemLine: customer.getShoppingCart().getItemLines()) {
//			System.out.println(itemLine.getQuantity() + "x: "
//					+ itemLine.PRODUCT.getName() 
//					+ " for " + itemLine.calculateCurrentPrice() + " DKK");
//		}
//		System.out.println("Total: " + customer.getShoppingCart().calculateTotalPrice() + " DKK");
//		// Create the order
//		IFEmployee employee = new Employee(0, 0, "", "", "", "", null);
//		if (terminal.confirmInput("Checkout?")) {		
//			orderCtrl.createQuote(customer, employee, customer.getShoppingCart());
//			super.show("Successfully created a new quote");
//		} else {
//			super.show("Quote creation was cancelled!");
//		}
//	  
//	}

	public void showAllEmployees() {
		Terminal terminal = Terminal.getInstance();
		terminal.clearScreen();
		
		System.out.println("[All employees]");
		for (Employee employee: employeeCtrl.getEmployees()) {
			System.out.println("Employee: " + employee.getFirstName() + " " + employee.getLastName());
		}
		System.out.println();
		
		terminal.getAnyKeyInput("Press [Enter] to go back");
		super.show();
	}
}
