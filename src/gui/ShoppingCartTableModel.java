package gui;



import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import controller.ShoppingCartController;
import model.ShoppingItemLine;

public class ShoppingCartTableModel extends AbstractTableModel {

	private static final long serialVersionUID = -2367962812967993282L;

	protected static final String COLUMN_NAMES[] = {
        "ID", "Product", "Price", "Quantity", "Subtotal", "Bulk Discount", "Total", "Description"
    };

    private List<ShoppingItemLine> itemLines;

    
    /**
     * Instantiates a new shopping cart table model.
     *
     * @param itemLines the item lines
     */
    public ShoppingCartTableModel(model.ShoppingCart shoppingCart) {
        // Prevent possible external mutation
        this.itemLines = new ArrayList<>(shoppingCart.getItemLines());
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
    	ShoppingItemLine itemLine = itemLines.get(rowIndex);
    	System.out.println("X: " + rowIndex + " Y: " + columnIndex);
        switch (columnIndex) {
            case 0: return "#" + itemLine.getPRODUCT().ID;
            case 1: return itemLine.getPRODUCT().getName();
            case 2: return String.format("%.2f DKK", itemLine.PRODUCT.getLatestSellingPrice());
            case 3: return itemLine.getQuantity();
            case 4: return String.format("%.2f DKK", itemLine.getCurrentPriceWithoutBulkDiscount());
            case 5: return itemLine.getBulkDiscount() == null ? "0%" : 
        		String.format("%d%% (%d+)", 
        				itemLine.getBulkDiscount().getDiscountPercentage(),
        				itemLine.getBulkDiscount().getMinQuantity());
            case 6: return String.format("%.2f DKK", itemLine.getCurrentPriceWithBulkDiscount());
            case 7: return itemLine.getPRODUCT().getDescription();
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
    
    /**
     * Removes a row from the table model
     *
     * @param row the row
     */
    public void remove(int row) {
    	ShoppingItemLine itemLine = this.itemLines.get(row);
    	if (itemLine != null) {
        	// update this model's itemLine copies
        	this.itemLines.remove(itemLine);
        	// Update the rendered table
        	this.fireTableRowsDeleted(row, row);
        	
    	}

    }
    
    /**
     * Gets the ShoppingItemLine object for a particular row
     *
     * @param row the row
     * @return the item line
     */
    public ShoppingItemLine getItemLine(int row) {
    	return itemLines.get(row);
    }

}