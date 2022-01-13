package gui;



import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import controller.OrderController;
import model.Order;

/**
 * @author Daniels Kanepe
 *
 */
public class OrdersTableModel extends AbstractTableModel {

	private static final long serialVersionUID = -2367962812967993282L;

	protected static final String COLUMN_NAMES[] = {
        "ID", "Created", "subtotal", "customer type discount", "Price", "status"
    };

    private List<Order> orders;
    private OrderController orderCtrl;

    
    /**
     * Constructor
     */
    public OrdersTableModel(List<Order> orders) {
    	this.orderCtrl = new OrderController();
        // Copying due to accidental mutation (theoretically all fields are constants),
    	//but also consistent order
        this.orders = new ArrayList<Order>(orders);
    }
    

    @Override
    public int getRowCount() {
        return orders.size();
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
    	Order order = orders.get(rowIndex);
        switch (columnIndex) {
            case 0: return "#" + order.ID;
            case 1: return Common.datetimeToString(order.getCreationDate());
            case 2: return String.format("%.2f DKK", order.getSubtotal());
            case 3: return String.format("%s: -%d%%", 
            		order.getCustomerType(),
            		order.getCustomerTypeDiscountPercentage());
            case 4: return String.format("%.2f DKK", order.getTotalPrice());
            case 5: return order.getStatus();
            default: return null;
        }
    }
    
    // Make cells uneditable
    @Override
    public boolean isCellEditable(int row, int column) {       
        return false;
    }
    
    /**
     * Gets the order object by row
     *
     * @param row the row
     * @return the order
     */
    public Order getOrder(int row) {
    	return orders.get(row);
    }
    
    /**
     * Adds a order to the table.
     *
     * @param order the order
     * @return the row that the order was inserted in
     */
    public int addOrder(Order order) {
    	int row = orders.size();
        this.orders.add(order);
        fireTableRowsInserted(row, row);
        return row;
    }
    
    public void removeOrder(int row) {
    	this.orders.remove(row);
    	this.fireTableRowsDeleted(row, row);
    }

}