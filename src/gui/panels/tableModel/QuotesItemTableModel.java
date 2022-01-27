package gui.panels.tableModel;



import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import model.QuoteItemLine;

/**
 * @author Daniels Kanepe
 *
 */
public class QuotesItemTableModel extends AbstractTableModel {

	private static final long serialVersionUID = -2367962812967993282L;

	protected static final String COLUMN_NAMES[] = {
        "ID", "Product", "Price", "Quantity", "Subtotal", "Bulk discount", "Total", "Description"
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
            case 2: return String.format("%.2f DKK", itemLine.getFIXED_PRODUCT_PRICE());
            case 3: return itemLine.getQuantity();
            case 4: return String.format("%.2f DKK", itemLine.getFixedPriceWithoutBulkDiscount());
            case 5: return itemLine.getFIXED_BULK_DISCOUNT() == null ? "0%" : 
        		String.format("%d%% (%d+)", 
        				itemLine.getFIXED_BULK_DISCOUNT().getDiscountPercentage(),
        				itemLine.getFIXED_BULK_DISCOUNT().getMinQuantity());
            case 6: return String.format("%.2f DKK", itemLine.getFixedPriceWithBulkDiscount());
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
     * Gets the quote item line object from a row
     *
     * @param row the row
     * @return the item line
     */
    public QuoteItemLine getItemLine(int row) {
    	return itemLines.get(row);
    }

}