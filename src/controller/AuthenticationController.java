package controller;

import model.Authentication;
import model.IFEmployee;

public class AuthenticationController {
	
	Authentication auth;

	public AuthenticationController() {
		auth = new Authentication();
	}
	
	/**
	 * Login.
	 *
	 * @param employee the employee
	 * @return true, if successful
	 */
	public boolean login(String email, String password) {
		IFEmployee employee = new EmployeeController().getEmployeeByEmail(email);
		if (employee == null) {
			return false;
		}
		// TODO: Hash password before checking
		String hashedPassword = password;
		// Check login details
		if (auth.checkCredentials(hashedPassword, employee.getHashedPassword())) {
			auth.login(employee);
			return true;
		}
		
		return false;
		
	}
	
	/**
	 * Gets the logged in user.
	 *
	 * @return the logged in user, or null if not logged in
	 */
	public IFEmployee getLoggedInUser() {
		return auth.getLoggedInUser();
	}

}
