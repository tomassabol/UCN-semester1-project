package model;

import java.time.LocalDate;
import java.time.LocalDateTime;

public abstract class AbstractPerson {
	/**
	 * Fields for AbstractPerson abstract class
	 */
	private String firstName;
	private String lastName;
	private String address;
	private String mobil;
	private LocalDateTime birthDate;

	public AbstractPerson(String firstName, String lastName, String address, String mobil, LocalDateTime birthDate){
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
		this.mobil = mobil;
		this.birthDate = birthDate;
	}

}
