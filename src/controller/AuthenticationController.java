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
		return auth.login(email, password);
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
