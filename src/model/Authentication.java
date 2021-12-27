package model;

import controller.EmployeeController;

public class Authentication {
	
	private IFEmployee loggedInUser;

	public Authentication() {
		this.loggedInUser = null;
	}
	
	/**
	 * Login.
	 *
	 * @param employee the employee
	 * @return true, if successful
	 */
	public boolean login(String email, String password) {
		// Check login details
		if (true) {
			IFEmployee employee = new EmployeeController().getEmployeeByID(0);
			this.loggedInUser = employee;
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * Gets the logged in user.
	 *
	 * @return the logged in user, or null if not logged in
	 */
	public IFEmployee getLoggedInUser( ) {
		return loggedInUser;
	}
	
}
