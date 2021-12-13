package view;

import java.time.LocalDate;

import controller.EmployeeController;
import model.Employee;

public class MenuUpdateEmployee extends GenericMenuInterface{
    
    private int employeeId;
    private EmployeeController employeeCtrl;

    public MenuUpdateEmployee(GenericMenuInterface previousInterface, int id) {
        super(previousInterface);

        employeeCtrl = new EmployeeController();
        Employee employee = employeeCtrl.getEmployeeByID(id);
        employeeId = id;

        super.setTitle("Update employee " + employee.getFirstName() + " " + employee.getLastName());
        super.addMenuOption("1", new GenericMenuOption("Update employee fisrt name", () -> updateFirstName()));
        super.addMenuOption("2", new GenericMenuOption("Update last name", () -> updateLastName()));
        super.addMenuOption("3", new GenericMenuOption("Update address", () -> updateAddress()));
        super.addMenuOption("4", new GenericMenuOption("Update birth date", () -> updateBirthDate()));
        super.addMenuOption("5", new GenericMenuOption("Update CPR number", () -> updateCPRNumber()));
        super.addMenuOptionGoBack("0");
    }

    public void updateFirstName() {
        Terminal terminal = getTerminal();
        terminal.clearScreen();

        String  firstName = terminal.getStringInput("New first name");
        employeeCtrl.updateFirstName(employeeId, firstName);
        super.show("First name was successfully updated to: " + firstName);
    }

    public void updateLastName() {
        Terminal terminal = getTerminal();
        terminal.clearScreen();

        String lastName = terminal.getStringInput("New last name");
        employeeCtrl.updateLastName(employeeId, lastName);
        super.show("Last name was successfully updated to: " + lastName);
    }

    public void updateAddress() {
        Terminal terminal = getTerminal();
        terminal.clearScreen();

        String address = terminal.getStringInput("New address");
        employeeCtrl.updateAddress(employeeId, address);
        super.show("Address was successfully updated to: " + address);
    }

    public void updateBirthDate() {
        Terminal terminal = getTerminal();
        terminal.clearScreen();

        LocalDate birthDate = terminal.getDateInput("New birth date");
        employeeCtrl.updataBirthDate(employeeId, birthDate);
        super.show("Birth date was successfully updated to: " + birthDate);
    }

    public void updateCPRNumber() {
        Terminal terminal = getTerminal();
        terminal.clearScreen();

        String CPRNumber = terminal.getStringInput("Newe CPR number");
        employeeCtrl.updateCPRNumber(employeeId, CPRNumber);
        super.show("CPR number was successfully updated to: " + CPRNumber);
    }
}
