package gui;



import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;
import model.CustomerType;


public class CustomerTypeTableModel extends AbstractTableModel {

	private static final long serialVersionUID = -2367962812947993282L;

	protected static final String[] COLUMN_NAMES = {
        "ID", "Name", "Discount Percentage"
    };

    private List<CustomerType> customerTypes;

    
    /**
     * Instantiates a new customer table model.
     *
     * @param customers the customers
     */
    public CustomerTypeTableModel(List<CustomerType> customerTypes) {
        // Prevent possible mutation
        this.customerTypes = new ArrayList<>(customerTypes);
    }
    

    @Override
    public int getRowCount() {
        return customerTypes.size();
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
    	CustomerType customerType = customerTypes.get(rowIndex);
        switch (columnIndex) {
            case 0: return "#" + customerType.ID;
            case 1: return customerType.getName();
            case 2: return customerType.getDiscountPercentage();
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
    public CustomerType getObj(int row) {
    	return customerTypes.get(row);
    }
    
  
    /**
     * Adds a customer to the table
     *
     * @param customer the customer
     * @return the int
     */
    public int add(CustomerType customerType) {
    	int row = customerTypes.size();
        this.customerTypes.add(customerType);
        fireTableRowsInserted(row, row);
        return row;
    }
    
    /**
     * Removes the customer from the table by row
     *
     * @param row the row
     */
    public void remove(int row) {
    	this.customerTypes.remove(row);
    	this.fireTableRowsDeleted(row, row);
    }

}