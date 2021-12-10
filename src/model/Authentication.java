package model;


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
	public boolean login(IFEmployee employee) {
		this.loggedInUser = employee;
		return true;
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
