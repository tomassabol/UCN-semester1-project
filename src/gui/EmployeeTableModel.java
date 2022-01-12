package gui;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import model.Employee;
import model.IFEmployee;

public class EmployeeTableModel extends AbstractTableModel {
	private static final String DATE_FORMAT = "yyyy-MM-dd";

	private static final long serialVersionUID = -2367962812947993282L;

	protected static final String[] COLUMN_NAMES = {
        "ID", "First name", "Last name", "Address", "Mobile", "Email", "Birth"
    };

    private List<IFEmployee> employees;

    
    /**
     * Instantiates a new employee table model.
     *
     * @param employees the employees
     */
    public EmployeeTableModel(List<IFEmployee> employees) {
        // Prevent possible mutation
        this.employees = new ArrayList<>(employees);
    }
    

    @Override
    public int getRowCount() {
        return employees.size();
    }

    @Override
    public int getColumnCount() {
        return COLUMN_NAMES.length;
    }

    @Override
    public String getColumnName(int column) {
        return COLUMN_NAMES[column];
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        switch (columnIndex) {
            default: return String.class;
        }
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
    	Employee employee = (Employee) employees.get(rowIndex);
        switch (columnIndex) {
            case 0: return "#" + employee.ID;
            case 1: return employee.getFirstName();
            case 2: return employee.getLastName();
            case 3: return employee.getAddress();
            case 4: return employee.getMobile();
            case 5: return employee.getEmail();
            case 6: return employee.getBirthDate().format(DateTimeFormatter.ofPattern(DATE_FORMAT));
            default: return "Error retrieving column name";
        }
    }
    
    // Make cells uneditable
    @Override
    public boolean isCellEditable(int row, int column) {       
        return false;
    }
    
    /**
     * Gets the employee object by row
     *
     * @param row the row
     * @return the employee
     */
    public IFEmployee getObj(int row) {
    	return employees.get(row);
    }
    
  
    /**
     * Adds an employee to the table
     *
     * @param employee the employee
     * @return the int
     */
    public int add(Employee employee) {
    	int row = employees.size();
        this.employees.add(employee);
        fireTableRowsInserted(row, row);
        return row;
    }
    
    /**
     * Removes the employee from the table by row
     *
     * @param row the row
     */
    public void remove(int row) {
    	this.employees.remove(row);
    	this.fireTableRowsDeleted(row, row);
    }
}
