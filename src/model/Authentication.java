package model;

import controller.EmployeeController;

public class Authentication {
	
	private IFEmployee loggedInUser;

	public Authentication() {
		this.loggedInUser = null;
	}
	
	/**
	 * Logs in as an employee
	 *
	 * @param employee the employee
	 */
	public void login(IFEmployee employee) {
		this.loggedInUser = employee;
	}
	
	/**
	 * Gets the logged in user.
	 *
	 * @return the logged in user, or null if not logged in
	 */
	public IFEmployee getLoggedInUser( ) {
		return loggedInUser;
	}
	
	// TODO: Check credentials using Bcrypt
	public boolean checkCredentials(String pw1, String pw2) {
		if (pw1.equals(pw2)) {
			return true;
		}
		return false;
	}
	
}
