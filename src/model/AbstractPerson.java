package model;

import java.time.LocalDate;
import java.time.LocalDateTime;

public abstract class AbstractPerson {
	/**
	 * Fields for AbstractPerson class
	 */
	public int ID;
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

}
