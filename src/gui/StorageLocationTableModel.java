package gui;



import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import model.StorageLocation;

/**
 * @author Daniels Kanepe
 *
 */
public class StorageLocationTableModel extends AbstractTableModel {

	private static final long serialVersionUID = -2367962812947993282L;

	protected static final String[] COLUMN_NAMES = {
        "ID", "Name", "Address", "Is a store?"
    };

    private List<StorageLocation> storageLocations;

    
    /**
     * Instantiates a new customer table model.
     *
     * @param customers the customers
     */
    public StorageLocationTableModel(List<StorageLocation> storageLocations) {
        // Prevent possible mutation
        this.storageLocations = new ArrayList<>(storageLocations);
    }
    

    @Override
    public int getRowCount() {
        return storageLocations.size();
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
    	StorageLocation storageLocation = storageLocations.get(rowIndex);
        switch (columnIndex) {
            case 0: return "#" + storageLocation.ID;
            case 1: return storageLocation.getName();
            case 2: return storageLocation.getAddress();
            case 3: return storageLocation.getIsAStore();
            default: return "Error retrieving column name";
        }
    }
    
    // Make cells uneditable
    @Override
    public boolean isCellEditable(int row, int column) {       
        return false;
    }
    
    /**
     * Gets the Storage Location object by row
     *
     * @param row the row
     * @return the StorageLocation
     */
    public StorageLocation getObj(int row) {
    	return storageLocations.get(row);
    }
    
  
    /**
     * Adds a StorageLocation to the table
     *
     * @param StorageLocation the storage location
     */
    public void add(StorageLocation storageLocation) {
        this.storageLocations.add(storageLocation);
        fireTableRowsInserted(this.getRowCount(), this.getRowCount());
    }
    
    /**
     * Removes the storage location from the table by row
     *
     * @param row the row
     */
    public void remove(int row) {
    	this.storageLocations.remove(row);
    	this.fireTableRowsDeleted(row, row);
    }

}