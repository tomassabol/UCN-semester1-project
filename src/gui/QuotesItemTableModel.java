package gui;



import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import controller.ShoppingCartController;
import model.QuoteItemLine;
import model.ShoppingItemLine;

public class QuotesItemTableModel extends AbstractTableModel {

	private static final long serialVersionUID = -2367962812967993282L;

	protected static final String COLUMN_NAMES[] = {
        "ID", "Product", "Price", "Quantity", "Total", "Description"
    };

    private List<QuoteItemLine> itemLines;

    /**
     * Instantiates a new shopping cart table model.
     *
     * @param itemLines the item lines
     */
    public QuotesItemTableModel(List<QuoteItemLine> itemLines) {
        // Prevent possible external mutation
        this.itemLines = new ArrayList<>(itemLines);
    }

    @Override
    public int getRowCount() {
        return itemLines.size();
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
    	QuoteItemLine itemLine = itemLines.get(rowIndex);
        switch (columnIndex) {
            case 0: return "#" + itemLine.getPRODUCT().ID;
            case 1: return itemLine.getPRODUCT().getName();
            case 2: return itemLine.getFIXED_PRODUCT_PRICE() + " DKK";
            case 3: return itemLine.getQuantity();
            case 4: return itemLine.getFixedPriceWithoutBulkDiscount() + "DKK to "
                	+ itemLine.getFixedPriceWithBulkDiscount() + " DKK";
            case 5: return itemLine.getPRODUCT().getDescription();
            default: return null;
        }
    }
    
    // Make cells uneditable
    @Override
    public boolean isCellEditable(int row, int column) {       
        return false;
    }
    
    /**
     * Clear the shopping cart
     */
    public void clear() {
    	// update this model's itemLine copies
    	this.itemLines.clear();
    	// Update the rendered table
    	this.fireTableDataChanged();
    }
    
    public void remove(int row) {
    	QuoteItemLine itemLine = this.itemLines.get(row);
    	if (itemLine != null) {
        	// update this model's itemLine copies
        	this.itemLines.remove(itemLine);
        	// Update the rendered table
        	this.fireTableRowsDeleted(row, row);
        	
    	}

    }
    
    public QuoteItemLine getItemLine(int row) {
    	return itemLines.get(row);
    }

}