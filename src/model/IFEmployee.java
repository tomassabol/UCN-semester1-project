/**
 * 
 */
package model;

/**
 * @author Daniels Kanepe
 *
 */
public interface IFEmployee {
	
	public String getCPRnumber();
	public void setCPRNumber(String CPRNumber);
	public String getHashedPassword();
	public String getUsername();
	// these come from abstract person. can add more as needed
	public String getFirstName();
	public String getLastName();

}
