package model;

public abstract class AbstractPerson {
	/**
	 * Fields for AbstractPerson abstract class
	 */
	private String firstName;
	private String lastName;
	private String address;
	private int mobil;
	private String birthDate;

	public AbstractPerson(String firstName, String lastName, String address, int mobil, String birthDate){
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
		this.mobil = mobil;
		this.birthDate = birthDate;
	}

}
