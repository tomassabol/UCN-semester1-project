package controller;

import java.time.LocalDateTime;
import java.util.List;

import model.Employee;
import model.EmployeeContainer;
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
	public Employee createEmployee(String CPRNumber, String firstName, String lastName, String address, String mobile, LocalDateTime birthDate) {
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
	public Employee getEmployeeByID(int id) {
		return EmployeeContainer.getInstance().findEmployeeById(id);
	}
	
	
	/**
	 * Gets all employees.
	 *
	 * @return A list of employees
	 */
	public List<Employee> getEmployees() {
		return EmployeeContainer.getInstance().getEmployees();
	}

}
