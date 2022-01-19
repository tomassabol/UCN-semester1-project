package gui;



import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import model.Customer;
import model.StockBatch;

/**
 * @author Daniels Kanepe
 *
 */
public class StockBatchTableModel extends AbstractTableModel {

	private static final long serialVersionUID = -2367962812947993282L;

	protected static final String[] COLUMN_NAMES = {
        "Delivered", "Product", "Quantity"
    };

    private List<StockBatch> stockBatches;

    
    /**
     * Instantiates a new Stock Batch table model.
     *
     * @param stockBatches The stock batches
     */
    public StockBatchTableModel(List<StockBatch> stockBatches) {
        // Prevent possible mutation
        this.stockBatches = new ArrayList<>(stockBatches);
    }
    

    @Override
    public int getRowCount() {
        return stockBatches.size();
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
    	StockBatch stockBatch = stockBatches.get(rowIndex);
        switch (columnIndex) {
        	case 0: return Common.datetimeToString(stockBatch.getDelivered());
            case 1: return stockBatch.getProduct();
            case 2: return stockBatch.getTotalQuantity();
            default: return "Error retrieving column name";
        }
    }
    
    // Make cells uneditable
    @Override
    public boolean isCellEditable(int row, int column) {       
        return false;
    }
    
    /**
     * Gets the Stock Batch object by row
     *
     * @param row the row
     * @return the Stock Batch
     */
    public StockBatch getObj(int row) {
    	return stockBatches.get(row);
    }
    
  
    /**
     * Adds a Stock Batch to the table
     *
     * @param StockBatch the stock batch
     */
    public void add(StockBatch stockBatch) {
        this.stockBatches.add(stockBatch);
        fireTableRowsInserted(this.getRowCount(), this.getRowCount());
    }
    
    /**
     * Removes the Stock Batch from the table by row
     *
     * @param row the row
     */
    public void remove(int row) {
    	this.stockBatches.remove(row);
    	this.fireTableRowsDeleted(row, row);
    }

}