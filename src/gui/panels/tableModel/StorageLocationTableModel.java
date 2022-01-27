package gui.panels.tableModel;



import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.swing.table.AbstractTableModel;

import model.StorageLocation;

// TODO: Auto-generated Javadoc
/**
 * The Class StorageLocationTableModel.
 *
 * @author Daniels Kanepe
 */
public class StorageLocationTableModel extends AbstractTableModel {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -2367962812947993282L;

	/** The Constant COLUMN_NAMES. */
	protected static final String[] COLUMN_NAMES = {
        "ID", "Name", "Address", "Is a store?"
    };

    /** The storage locations. */
    private List<StorageLocation> storageLocations;

    

    /**
     * Instantiates a new storage location table model.
     *
     * @param storageLocations the storage locations
     */
    public StorageLocationTableModel(Set<StorageLocation> storageLocations) {
        // Prevent possible mutation
        this.storageLocations = new ArrayList<>(storageLocations);
    }
    

    /**
     * Gets the row count.
     *
     * @return the row count
     */
    @Override
    public int getRowCount() {
        return storageLocations.size();
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
    	StorageLocation storageLocation = storageLocations.get(rowIndex);
        switch (columnIndex) {
            case 0: return "#" + storageLocation.ID;
            case 1: return storageLocation.getName();
            case 2: return storageLocation.getAddress();
            case 3: return storageLocation.getIsAStore();
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
     * Gets the Storage Location object by row.
     *
     * @param row the row
     * @return the StorageLocation
     */
    public StorageLocation getObj(int row) {
    	return storageLocations.get(row);
    }
    
  
    /**
     * Adds a StorageLocation to the table.
     *
     * @param storageLocation the storage location
     */
    public void add(StorageLocation storageLocation) {
        this.storageLocations.add(storageLocation);
        fireTableRowsInserted(this.getRowCount() -1, this.getRowCount() -1);
    }
    
    /**
     * Removes the storage location from the table by row.
     *
     * @param row the row
     */
    public void remove(int row) {
    	this.storageLocations.remove(row);
    	this.fireTableRowsDeleted(row, row);
    }

}