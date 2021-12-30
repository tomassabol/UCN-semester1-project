package gui;



import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import controller.ShoppingCartController;
import model.ShoppingItemLine;

public class ShoppingCartModel extends AbstractTableModel {

	private static final long serialVersionUID = -2367962812967993282L;

	protected static final String COLUMN_NAMES[] = {
        "ID", "Product", "Price", "Quantity"
    };

	private model.ShoppingCart shoppingCart;
    private List<ShoppingItemLine> itemLines;
    private ShoppingCartController shoppingCartCtrl;

    
    /**
     * Instantiates a new shopping cart table model.
     *
     * @param itemLines the item lines
     */
    public ShoppingCartModel(model.ShoppingCart shoppingCart) {
    	this.shoppingCart = shoppingCart;
    	this.shoppingCartCtrl = new ShoppingCartController();
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
            case 0: return Integer.class;
            default: return String.class;
        }
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
    	ShoppingItemLine itemLine = itemLines.get(rowIndex);
        switch (columnIndex) {
            case 0: return "#" + itemLine.getPRODUCT().ID;
            case 1: return itemLine.getPRODUCT().getName();
            case 2: return itemLine.getCurrentPriceWithoutBulkDiscount() + "DKK to "
            	+ itemLine.getCurrentPriceWithBulkDiscount() + " DKK";
            case 3: return itemLine.getQuantity();
            case 4: return itemLine.getPRODUCT().getDescription();
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
    	// update model
    	this.shoppingCartCtrl.clearCart(shoppingCart);
    	// update view
    	this.itemLines.clear();
    	this.fireTableDataChanged();
    }
    
    public ShoppingItemLine getModel(int row) {
    	return itemLines.get(row);
    }

}