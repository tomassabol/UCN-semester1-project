package view;

import java.time.LocalDate;

import controller.EmployeeController;
import model.Employee;
import model.IFEmployee;

public class MenuUpdateEmployee extends GenericMenuInterface{
    
    private IFEmployee employee;
    private EmployeeController employeeCtrl;

    public MenuUpdateEmployee(GenericMenuInterface previousInterface, IFEmployee employee) {
        super(previousInterface);

        employeeCtrl = new EmployeeController();
        this.employee = employee;

        super.setTitle("Update employee " + employee.getFirstName() + " " + employee.getLastName());
        super.addMenuOption("1", new GenericMenuOption("Update first name", () -> updateFirstName()));
        super.addMenuOption("2", new GenericMenuOption("Update last name", () -> updateLastName()));
        super.addMenuOption("3", new GenericMenuOption("Update address", () -> updateAddress()));
        super.addMenuOption("4", new GenericMenuOption("Update birth date", () -> updateBirthDate()));
        super.addMenuOption("5", new GenericMenuOption("Update CPR number", () -> updateCPRNumber()));
        super.addMenuOptionGoBack("0");
    }

    public void updateFirstName() {
        Terminal terminal = getTerminal();
        terminal.clearScreen();

        String firstName = terminal.getStringInput("New first name");
        employeeCtrl.updateFirstName(employee, firstName);
        super.show("First name was successfully updated to: " + firstName);
        // TODO: Add confirmation
    }

    public void updateLastName() {
        Terminal terminal = getTerminal();
        terminal.clearScreen();

        String lastName = terminal.getStringInput("New last name");
        employeeCtrl.updateLastName(employee, lastName);
        super.show("Last name was successfully updated to: " + lastName);
        
     // TODO: Add confirmation
    }

    public void updateAddress() {
        Terminal terminal = getTerminal();
        terminal.clearScreen();

        String address = terminal.getStringInput("New address");
        employeeCtrl.updateAddress(employee, address);
        super.show("Address was successfully updated to: " + address);
        
     // TODO: Add confirmation
    }

    public void updateBirthDate() {
        Terminal terminal = getTerminal();
        terminal.clearScreen();

        LocalDate birthDate = terminal.getDateInput("New birth date");
        employeeCtrl.updataBirthDate(employee, birthDate);
        super.show("Birth date was successfully updated to: " + birthDate);
        
     // TODO: Add confirmation
    }

    public void updateCPRNumber() {
        Terminal terminal = getTerminal();
        terminal.clearScreen();

        String CPRNumber = terminal.getStringInput("Newe CPR number");
        employeeCtrl.updateCPRNumber(employee, CPRNumber);
        super.show("CPR number was successfully updated to: " + CPRNumber);
        
     // TODO: Add confirmation
    }
}
