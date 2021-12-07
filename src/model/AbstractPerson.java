package model;

import java.time.LocalDateTime;

public abstract class AbstractPerson {
	/**
	 * Fields for AbstractPerson class
	 */
	public final int ID;
	private String firstName;
	private String lastName;
	private String address;
	private String mobile;
	private LocalDateTime birthDate;

	/*
	 * Constructor
	 */
	protected AbstractPerson(int ID, String firstName, String lastName, String address, String mobile, LocalDateTime birthDate){
		this.ID = ID;
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
		this.mobile = mobile;
		this.birthDate = birthDate;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public LocalDateTime getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(LocalDateTime birthDate) {
		this.birthDate = birthDate;
	}
	
	

}
