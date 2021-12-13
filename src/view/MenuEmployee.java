package view;

import controller.EmployeeController;
import model.Employee;

public class MenuEmployee extends GenericMenuInterface {
  
  private EmployeeController employeeCtrl;

  /**
   * Constructor for MainMenuUI.
   */
  public MenuEmployee(GenericMenuInterface previousInterface) {
    super(previousInterface);

    super.setTitle("Employees");
    super.addMenuOption("1", new GenericMenuOption("Show all employees",
    		() -> showAllEmployees()));
    super.addMenuOptionGoBack("0");
    
    employeeCtrl = new EmployeeController();
  }


	public void showAllEmployees() {
		Terminal terminal = getTerminal();
		terminal.clearScreen();
		
		terminal.printEmployees(employeeCtrl.getEmployees());
		terminal.clearScreen();
		
		terminal.getAnyKeyInput("Press [Enter] to go back");
		super.show();
	}
}
