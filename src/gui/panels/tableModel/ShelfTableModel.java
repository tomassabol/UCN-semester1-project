package gui.panels.tableModel;



import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import controller.ProductController;
import models.Shelf;

/**
 * @author Daniels Kanepe
 *
 */
public class ShelfTableModel extends AbstractTableModel {

	private static final long serialVersionUID = -2367962812947993282L;

	protected static final String[] COLUMN_NAMES = {
        "ID", "Name", "Storage Location"
    };

    private List<Shelf> shelves;

    ProductController productCtrl;

    

    /**
     * Instantiates a new shelf table model.
     *
     * @param shelves the shelves
     */
    public ShelfTableModel(List<Shelf> shelves) {
        // Prevent possible mutation
        this.shelves = new ArrayList<>(shelves);
        
        productCtrl = new ProductController();
    }
    

    @Override
    public int getRowCount() {
        return shelves.size();
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
    	Shelf shelf = shelves.get(rowIndex);
        switch (columnIndex) {
            case 0: return "#" + shelf.ID;
            case 1: return shelf.getName();
            case 2: return shelf.getStorageLocation().getName();
            default: return "Error retrieving column name";
        }
    }
    
    // Make cells uneditable
    @Override
    public boolean isCellEditable(int row, int column) {       
        return false;
    }
    
    /**
     * Gets the Shelf object by row
     *
     * @param row the row
     * @return the Shelf
     */
    public Shelf getObj(int row) {
    	return shelves.get(row);
    }
    
  
    /**
     * Adds a Shelf to the table
     *
     * @param Shelf
     */
    public void add(Shelf storageLocation) {
        this.shelves.add(storageLocation);
        fireTableRowsInserted(this.getRowCount() - 1, this.getRowCount() - 1);
    }
    
    /**
     * Removes the shelf from the table by row
     *
     * @param row the row
     */
    public void remove(int row) {
    	this.shelves.remove(row);
    	this.fireTableRowsDeleted(row, row);
    }

}