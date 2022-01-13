package gui;



import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import model.Customer;
import gui.Common;

/**
 * @author Daniels Kanepe
 *
 */
public class CustomerTableModel extends AbstractTableModel {

	private static final long serialVersionUID = -2367962812947993282L;

	protected static final String[] COLUMN_NAMES = {
        "ID", "First name", "Last name", "Address", "Mobile", "Type", "Birth"
    };

    private List<Customer> customers;

    
    /**
     * Instantiates a new customer table model.
     *
     * @param customers the customers
     */
    public CustomerTableModel(List<Customer> customers) {
        // Prevent possible mutation
        this.customers = new ArrayList<>(customers);
    }
    

    @Override
    public int getRowCount() {
        return customers.size();
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
    	Customer customer = customers.get(rowIndex);
        switch (columnIndex) {
            case 0: return "#" + customer.ID;
            case 1: return customer.getFirstName();
            case 2: return customer.getLastName();
            case 3: return customer.getAddress();
            case 4: return customer.getMobile();
            case 5: return String.format("%s (%d%% discount)", 
            		customer.getCustomerType().getName(),
            		customer.getCustomerType().getDiscountPercentage());
            case 6: return Common.dateToString(customer.getBirthDate());
            default: return "Error retrieving column name";
        }
    }
    
    // Make cells uneditable
    @Override
    public boolean isCellEditable(int row, int column) {       
        return false;
    }
    
    /**
     * Gets the customer object by row
     *
     * @param row the row
     * @return the customer
     */
    public Customer getObj(int row) {
    	return customers.get(row);
    }
    
  
    /**
     * Adds a customer to the table
     *
     * @param customer the customer
     * @return the int
     */
    public int add(Customer customer) {
    	int row = customers.size();
        this.customers.add(customer);
        fireTableRowsInserted(row, row);
        return row;
    }
    
    /**
     * Removes the customer from the table by row
     *
     * @param row the row
     */
    public void remove(int row) {
    	this.customers.remove(row);
    	this.fireTableRowsDeleted(row, row);
    }

}