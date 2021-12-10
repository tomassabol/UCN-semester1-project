package view;

import java.time.LocalDate;

import controller.EmployeeController;
import model.IFEmployee;

public class App {
    public static void main(String[] args) {
    	// Create default employee
		EmployeeController employeeCtrl = new EmployeeController();
		IFEmployee employee = employeeCtrl.createEmployee("12323", "Admin", "Admin", "Admin street 4", "991", LocalDate.now());
    	// Create main menu
        MenuMain menuMain = new MenuMain();
        // login default employee
        menuMain.getAuth().login(employee);
        // show main menu
        menuMain.show();
    }
}
