package controller;

import java.time.LocalDate;
import java.util.List;

import model.Employee;
import model.EmployeeContainer;
import model.IFEmployee;
import model.PrimaryKey;

public class EmployeeController {

	public EmployeeController() {}
	

	/**
	 * Creates an employee and adds i to the EmployeeContainer
	 *
	 * @param ID the id
	 * @param CPRNumber the CPR number
	 * @param firstName the first name
	 * @param lastName the last name
	 * @param address the address
	 * @param mobile the mobile number
	 * @param birthDate the birth date
	 * @return the employee
	 */
	public Employee createEmployee(String CPRNumber, String firstName, String lastName, String address, String mobile, LocalDate birthDate) {
		Employee employee = new Employee(PrimaryKey.getNextEmployeeID(), CPRNumber, firstName, lastName, address, mobile, birthDate);
		EmployeeContainer.getInstance().addEmployee(employee);
		return employee;
	}
	

	/**
	 * get employee by ID.
	 *
	 * @param id the id
	 * @return the employee
	 */
	public IFEmployee getEmployeeByID(int id) {
		return EmployeeContainer.getInstance().findEmployeeById(id);
	}
	
	
	/**
	 * Gets all employees.
	 *
	 * @return A list of employees
	 */
	public List<IFEmployee> getEmployees() {
		return EmployeeContainer.getInstance().getEmployees();
	}

	public void updateFirstName(IFEmployee employee, String firstName) {
		employee.setFirstName(firstName);
	}

	public void updateLastName(IFEmployee employee, String lastName) {
		employee.setLastName(lastName);
	}

	public void updateAddress(IFEmployee employee, String address) {
		employee.setAddress(address);
	}

	public void updataBirthDate(IFEmployee employee, LocalDate birthDate) {
		employee.setBirthDate(birthDate);
	}

	public void updateCPRNumber(IFEmployee employee, String CPRNumber) {
		employee.setCPRNumber(CPRNumber);
	}

	public void removeEmployee(IFEmployee employee) {
		EmployeeContainer.getInstance().removeEmployee(employee);
	}

}
