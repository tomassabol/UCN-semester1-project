package gui;



import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;
import model.CustomerType;

/**
 * @author Daniels Kanepe
 *
 */
public class CustomerTypeTableModel extends AbstractTableModel {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -2367962812947993282L;

	/** The Constant COLUMN_NAMES. */
	protected static final String[] COLUMN_NAMES = {
        "ID", "Name", "Discount Percentage"
    };

    /** The customer types. */
    private List<CustomerType> customerTypes;

    
    /**
     * Instantiates a new customer table model.
     *
     * @param customerTypes the customer types
     */
    public CustomerTypeTableModel(List<CustomerType> customerTypes) {
        // Prevent possible mutation
        this.customerTypes = new ArrayList<>(customerTypes);
    }
    

    /**
     * Gets the row count.
     *
     * @return the row count
     */
    @Override
    public int getRowCount() {
        return customerTypes.size();
    }

    /**
     * Gets the column count.
     *
     * @return the column count
     */
    @Override
    public int getColumnCount() {
        return COLUMN_NAMES.length;
    }

    /**
     * Gets the column name.
     *
     * @param column the column
     * @return the column name
     */
    @Override
    public String getColumnName(int column) {
        return COLUMN_NAMES[column];
    }

    /**
     * Gets the column class.
     *
     * @param columnIndex the column index
     * @return the column class
     */
    @Override
    public Class<?> getColumnClass(int columnIndex) {
        switch (columnIndex) {
            default: return String.class;
        }
    }

    /**
     * Gets the value at.
     *
     * @param rowIndex the row index
     * @param columnIndex the column index
     * @return the value at
     */
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
    
    /**
     * Checks if is cell editable.
     *
     * @param row the row
     * @param column the column
     * @return true, if is cell editable
     */
    // Make cells uneditable
    @Override
    public boolean isCellEditable(int row, int column) {       
        return false;
    }
    

    /**
     * Gets the customer type object by row.
     *
     * @param row the row
     * @return the obj
     */
    public CustomerType getObj(int row) {
    	return customerTypes.get(row);
    }
    
  

    /**
     * Add a customer type to the table.
     *
     * @param customerType the customer type
     * @return the int
     */
    public void add(CustomerType customerType) {
        this.customerTypes.add(customerType);
        fireTableRowsInserted(this.getRowCount(), this.getRowCount());
    }
    
    /**
     * Removes a customer type by table row.
     *
     * @param row the row
     */
    public void remove(int row) {
    	this.customerTypes.remove(row);
    	this.fireTableRowsDeleted(row, row);
    }

}