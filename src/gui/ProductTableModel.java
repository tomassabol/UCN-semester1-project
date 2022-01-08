package gui;



import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import controller.StockController;
import model.Product;

/**
 * @author Daniels Kanepe
 *
 */
public class ProductTableModel extends AbstractTableModel {
	
	StockController stockCtrl;

	private static final long serialVersionUID = -2367962412967993282L;
	
	public enum Column {
		ID("ID"),
		NAME("Name"),
		DESCRIPTION("Description"),
		MIN_STOCK("Min stock"),
		MAX_STOCK("Max Stock"),
		BUYABLE_STOCK("Buyable stock"),
		LOANABLE_STOCK("Loanable stock"),
		DATE_ADDED("Added"),
		ENABLED("Enabled");
		
		private String value;
		
		Column(final String value) {
			this.value = value;
		}
		
		public String getValue() {
			return value;
		}
		
		@Override
		public String toString() {
			//return name().replace('_', ' ').substring(0,1).toUpperCase() + name().substring(1).toLowerCase();
			return this.getValue();
		}
	}

    private List<Product> products;
    private List<Column> columns;

    /**
     * Instantiates a new product table model.
     * 
     *
     * @param products the products
     * @param columns the columns to be displayed
     */
    public ProductTableModel(List<Product> products, List<Column> columns) {
    	stockCtrl = new StockController();
        this.columns = new ArrayList<Column>(columns);
        // Prevent possible external mutation
        this.products = new ArrayList<>(products);
    }
    
    /**
     * Instantiates a new product table model.
     * Note: This constructor shows all columns
     *
     * @param products the products
     */
    public ProductTableModel(List<Product> products) {
    	stockCtrl = new StockController();
    	this.columns = new ArrayList<Column>(Arrays.asList(Column.class.getEnumConstants()));
        // Prevent possible external mutation
        this.products = new ArrayList<>(products);
    }

    @Override
    public int getRowCount() {
        return products.size();
    }

    @Override
    public int getColumnCount() {
        return columns.size();
    }

    @Override
    public String getColumnName(int column) {
    	return this.columns.get(column).toString();
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
    	Column column = this.columns.get(columnIndex);
        switch (column) {
        case ENABLED: return Boolean.class;
            default: return String.class;
        }
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
    	Product product = products.get(rowIndex);
    	Column column = this.columns.get(columnIndex);
    	switch (column) {
		case BUYABLE_STOCK: return stockCtrl.getBuyableQuantityInStock(product);
		case DATE_ADDED: return product.getDateAdded();
		case DESCRIPTION: return product.getDescription();
		case ID: return product.ID;
		case LOANABLE_STOCK: return stockCtrl.getLoanableQuantityInStock(product);
		case MAX_STOCK: return product.getMinStock();
		case MIN_STOCK: return product.getMinStock();
		case NAME: return product.getName();
		case ENABLED: return product.isEnabled();
		default: return "Error retrieving column name";
    	}
    }
    
    // Make cells uneditable
    @Override
    public boolean isCellEditable(int row, int column) {       
        return false;
    }
    
    public Product getItemLine(int row) {
    	return products.get(row);
    }

}