package model;

import java.util.ArrayList;
import java.util.List;

public class EmployeeContainer {
    private static EmployeeContainer instance;
    private ArrayList<IFEmployee> employees;

    /**
     * Constructor class EmployeeContainer
     */
    private EmployeeContainer() {
        employees = new ArrayList<>();
    }

    /**
     * @return instance of EmployeeContainer
     */
    public static EmployeeContainer getInstance() {
        if (instance == null) {
            instance = new EmployeeContainer();
        }
        return instance;
    }

    /**
     * Adds the employee.
     *
     * @param employee the employee
     * @return true, if successful
     */
    public boolean addEmployee(IFEmployee employee) {
        return employees.add(employee);
    }

    /**
     * Gets the employees.
     *
     * @return list of all employees
     */
    public List<IFEmployee> getEmployees() {
        return this.employees;
    }


    /**
     * Removes the employee.
     *
     * @param employee the employee
     * @return true, if successful
     */
    public boolean removeEmployee(IFEmployee employee) {
        return employees.remove(employee);
    }

    /**
     * @param employeeID - id of an employee to be found
     * @return employee if the employee was found
     */
    public IFEmployee findEmployeeById(int employeeID) {
        for (IFEmployee employee : employees) {
            if (employee.getID() == employeeID) {
                return employee;
            }
        }
        return null;
    } 
    
    

}