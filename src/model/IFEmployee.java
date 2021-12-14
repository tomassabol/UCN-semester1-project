/**
 * 
 */
package model;

import java.time.LocalDate;

/**
 * @author Daniels Kanepe
 *
 */
public interface IFEmployee {
	
	public int getID();
	public String getCPRnumber();
	public void setCPRNumber(String CPRNumber);
	public String getHashedPassword();
	public String getUsername();
	// these come from abstract person. can add more as needed
	public String getFirstName();
	public String getLastName();
	public void setFirstName(String firstName);
	public void setLastName(String lastName);
	public void setAddress(String address);
	public void setBirthDate(LocalDate birthDate);
	public LocalDate getBirthDate();

}
