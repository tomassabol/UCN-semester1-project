package gui.panels.tableModel;



import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import model.Contractor;

/**
 * @author Daniels Kanepe
 *
 */
public class ContractorTableModel extends AbstractTableModel {

	private static final long serialVersionUID = -2367962812947993282L;

	protected static final String[] COLUMN_NAMES = {
        "ID", "Company name"
    };

    private List<Contractor> contractors;

    
    /**
     * Instantiates a new customer table model.
     *
     * @param customers the customers
     */
    public ContractorTableModel(List<Contractor> contractors) {
        // Prevent possible mutation
        this.contractors = new ArrayList<>(contractors);
    }
    

    @Override
    public int getRowCount() {
        return contractors.size();
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
    	Contractor contractor = contractors.get(rowIndex);
        switch (columnIndex) {
            case 0: return "#" + contractor.ID;
            case 1: return contractor.getCompanyName();
            default: return "Error retrieving column name";
        }
    }
    
    // Make cells uneditable
    @Override
    public boolean isCellEditable(int row, int column) {       
        return false;
    }
    
    /**
     * Gets the contractor object by row
     *
     * @param row the row
     * @return the contractor
     */
    public Contractor getObj(int row) {
    	return contractors.get(row);
    }
    
  
    /**
     * Adds a contractor to the table
     *
     * @param contractor the contractor
     * @return the int
     */
    public void add(Contractor contractor) {
        this.contractors.add(contractor);
        fireTableRowsInserted(this.getRowCount() -1, this.getRowCount() -1);
    }
    
    /**
     * Removes the customer from the table by row
     *
     * @param row the row
     */
    public void remove(int row) {
    	this.contractors.remove(row);
    	this.fireTableRowsDeleted(row, row);
    }

}