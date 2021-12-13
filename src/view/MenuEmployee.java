package view;

import java.time.LocalDate;

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
    super.addMenuOption("1", new GenericMenuOption("Create Employee",
        () -> createEmployee()));
    super.addMenuOption("2", new GenericMenuOption("Show all employee", () -> showAllEmployees()));
    super.addMenuOption("3", new GenericMenuOption("Update information", () -> updateEmployee()));
    super.addMenuOption("4", new GenericMenuOption("Delete employee", () -> deleteEmployee()));
    super.addMenuOptionGoBack("0");
    
    employeeCtrl = new EmployeeController();
  }


	public void showAllEmployees() {
		Terminal terminal = getTerminal();
		terminal.clearScreen();
		
		terminal.printEmployees(employeeCtrl.getEmployees());
		terminal.getAnyKeyInput("Press [Enter] to go back");
		super.show();
  }
  
  public void createEmployee() {
    Terminal terminal = getTerminal();
    terminal.clearScreen();

    String CPRNumber = terminal.getStringInput("CPR number of the employee");
    String firstName = terminal.getStringInput("First name of the employee");
    String lastName = terminal.getStringInput("Last name of the employee");
    String address = terminal.getStringInput("Address of the employee");
    String mobile = terminal.getStringInput("Phone number of the employee");
    LocalDate birthDate = terminal.getDateInput("Birth date of the employee");

    if(terminal.confirmInput()) {
      employeeCtrl.createEmployee(CPRNumber, firstName, lastName, address, mobile, birthDate);
      super.show("Employee [" + firstName + " " + lastName + "] was successfully created");
    }else {
      super.show("New employee creation was discarded");
    }
  }

  public void updateEmployee() {
    Terminal terminal = getTerminal();
    terminal.clearScreen();

    terminal.printEmployees(employeeCtrl.getEmployees());
    int id = terminal.getIntegerInput("The id of the Employee you want to update");
    if(isEmpty(id)){
      super.show("There is no Employee with that id in the system");
    }else{
      MenuUpdateEmployee updateMenu = new MenuUpdateEmployee(this, id);
      updateMenu.show();
    }
    terminal.getAnyKeyInput("Press [Enter] to go back");
    super.show();
  }

  public void deleteEmployee() {
    Terminal terminal = getTerminal();
    terminal.clearScreen();

    terminal.printEmployees(employeeCtrl.getEmployees());
    int id = terminal.getIntegerInput("The id of the Employee you want to delete");
    
    if(isEmpty(id)){
      super.show("There is no Employee with that id in the system");
    }else{
      Employee employee = employeeCtrl.getEmployeeByID(id);
      if(terminal.confirmInput("Are you sure you want to delete " + employee.getFirstName() + " " +employee.getLastName())) {
        employeeCtrl.removeEmployee(employee);
        super.show("Employee was successfully deleted");
      }else {
        super.show("Employee deletion was discarded");
      }
    }
  }

  private boolean isEmpty(int id){
    if(employeeCtrl.getEmployeeByID(id) == null){
        return true;
    }else{
        return false;
    }
  } 
}
