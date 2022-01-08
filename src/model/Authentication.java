package model;

import org.mindrot.jbcrypt.BCrypt;

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
	
	/**
	 * Check credentials.
	 *
	 * @param pw1 The unhashed candidate password
	 * @param pw2 the hashed employee's password to compare to
	 * @return true, if successful
	 */
	public boolean checkCredentials(String pw1, String pw2) {
		return BCrypt.checkpw(pw1, pw2);
	}
	
	/**
	 * Log out
	 */
	public void Logout() {
		this.loggedInUser = null;
	}
	
}
