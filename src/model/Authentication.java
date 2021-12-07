package model;

import java.time.LocalDateTime;

import controller.EmployeeController;
import jbcrypt.BCrypt;

public class Authentication {
	
	private static Authentication instance;
	private static IFEmployee loggedInUser;

	private Authentication() {
	}
	
	public static Authentication getInstance() {
		if (instance == null) {
			instance = new Authentication();
			// default admin user
			EmployeeController employeeCtrl = new EmployeeController();
			IFEmployee employee = employeeCtrl.createEmployee("12323", "Admin", "Admin", "Admin street 4", "991", LocalDateTime.now());
			instance.login(employee);
		}
		return instance;
		
	}
	
	public boolean login(IFEmployee employee) {
		Authentication.loggedInUser = employee;
		return true;
	}
	
	public IFEmployee getLoggedInUser( ) {
		return loggedInUser;
	}
	
}
