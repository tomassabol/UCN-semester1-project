package gui.panels.tableModels;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import model.SupplyOffer;

public class SupplyOfferTableModel  extends AbstractTableModel {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -2367962812947993282L;

	/** The Constant COLUMN_NAMES. */
	protected static final String[] COLUMN_NAMES = {
        "ID", "Product", "Contractor", "Price per Item", "Minimum quantity"
    };

    /** The supply offers. */
    private List<SupplyOffer> supplyOffers;

    

    /**
     * Instantiates a new supply offer table model.
     *
     * @param supplyOffers the supply offers
     */
    public SupplyOfferTableModel(List<SupplyOffer> supplyOffers) {
        // Prevent possible mutation
        this.supplyOffers = new ArrayList<>(supplyOffers);
    }
    

    /**
     * Gets the row count.
     *
     * @return the row count
     */
    @Override
    public int getRowCount() {
        return supplyOffers.size();
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
    	SupplyOffer supplyOffer = supplyOffers.get(rowIndex);
        switch (columnIndex) {
            case 0: return "#" + supplyOffer.ID;
            case 1: return supplyOffer.getProduct().getName();
            case 2: return supplyOffer.getContractor().getCompanyName();
            case 3: return supplyOffer.getPricePerItem() + " DKK";
            case 4: return supplyOffer.getMinQuantity();
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
     * Gets the SupplyOffer object by row.
     *
     * @param row the row
     * @return the SupplyOffer
     */
    public SupplyOffer getObj(int row) {
    	return supplyOffers.get(row);
    }
    
  
    /**
     * Adds a SupplyOffer to the table.
     *
     * @param supplyOffer the supply offer
     */
    public void add(SupplyOffer supplyOffer) {
        this.supplyOffers.add(supplyOffer);
        fireTableRowsInserted(this.getRowCount(), this.getRowCount());
    }
    
    /**
     * Removes the supply offer from the table by row.
     *
     * @param row the row
     */
    public void remove(int row) {
    	System.out.println("Removing: " + row);
    	System.out.println(supplyOffers);
    	System.out.println(this.getRowCount());
    	this.supplyOffers.remove(row);
    	this.fireTableRowsDeleted(row, row);
    }

}