package gui.panels.tableModels;



import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import gui.Common;
import model.SupplyOrder;

/**
 * @author Daniels Kanepe
 *
 */
public class SupplyOrderTableModel extends AbstractTableModel {

	private static final long serialVersionUID = -2367962812947993282L;

	protected static final String[] COLUMN_NAMES = {
        "ID", "Date Ordered", "Delivered", "Product", "Quantity", "Price per Item", "Contractor"
    };

    private List<SupplyOrder> supplyOrders;

     
    /**
     * Instantiates a new customer table model.
     *
     * @param customers the customers
     */
    public SupplyOrderTableModel(List<SupplyOrder> supplyOrders) {
        // Prevent possible mutation
        this.supplyOrders = new ArrayList<>(supplyOrders);
    }
    

    @Override
    public int getRowCount() {
        return supplyOrders.size();
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
    	SupplyOrder supplyOrder = supplyOrders.get(rowIndex);
        switch (columnIndex) {
            case 0: return "#" + supplyOrder.ID;
            case 1: return Common.datetimeToString(supplyOrder.getDateOrdered());
            case 2: return supplyOrder.isStocked() ? Common.datetimeToString(supplyOrder.getDelivered()) : "No";
            case 3: return supplyOrder.getProduct().getName();
            case 4: return String.valueOf(supplyOrder.getQuantity());
            case 5: return supplyOrder.getPricePerItem() + " DKK";
            case 6: return supplyOrder.getContractor().getCompanyName();
            default: return "Error retrieving column name";
        }
    }
    
    // Make cells uneditable
    @Override
    public boolean isCellEditable(int row, int column) {       
        return false;
    }
    
    /**
     * Gets the customer object by row
     *
     * @param row the row
     * @return the customer
     */
    public SupplyOrder getObj(int row) {
    	return supplyOrders.get(row);
    }
    
  
    /**
     * Adds a customer to the table
     *
     * @param customer the customer
     */
    public void add(SupplyOrder supplyOrder) {
        this.supplyOrders.add(supplyOrder);
        fireTableRowsInserted(this.getRowCount() -1, this.getRowCount() -1);
    }
    
    /**
     * Removes the customer from the table by row
     *
     * @param row the row
     */
    public void remove(int row) {
    	this.supplyOrders.remove(row);
    	this.fireTableRowsDeleted(row, row);
    }

}