package gui.panels.tableModels;



import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import model.Quote;

/**
 * @author Daniels Kanepe
 *
 */
public class QuotesTableModel extends AbstractTableModel {
	
	private final static String DATETIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

	private static final long serialVersionUID = -2367962812967993282L;

	protected static final String COLUMN_NAMES[] = {
        "ID", "Created", "subtotal", "customer type discount", "Price"
    };

    private List<Quote> quotes;

    

    /**
     * Instantiates a new quotes table model.
     *
     * @param quotes the quotes
     */
    public QuotesTableModel(List<Quote> quotes) {
        // Copying due to accidental mutation (theoretically all fields are constants),
    	//but also consistent order
        this.quotes = new ArrayList<Quote>(quotes);
    }
    

    @Override
    public int getRowCount() {
        return quotes.size();
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
    	Quote quote = quotes.get(rowIndex);
        switch (columnIndex) {
            case 0: return "#" + quote.ID;
            case 1: return quote.getCreationDate().format(DateTimeFormatter.ofPattern(DATETIME_FORMAT));
            case 2: return String.format("%.2f DKK", quote.getSubtotal());
            case 3: return String.format("%s: -%d%%", 
            		quote.getCUSTOMER_TYPE(),
            		quote.getCUSTOMER_TYPE_DISCOUNT_PERCENTAGE());
            case 4: return String.format("%.2f DKK", quote.getTotalPrice());
            default: return null;
        }
    }
    
    // Make cells uneditable
    @Override
    public boolean isCellEditable(int row, int column) {       
        return false;
    }
    
    /**
     * Gets the quote object by row
     *
     * @param row the row
     * @return the quote
     */
    public Quote getQuote(int row) {
    	return quotes.get(row);
    }
    
    /**
     * Adds a quote to the table.
     *
     * @param quote the quote
     * @return the row that the quote was inserted in
     */
    public int addQuote(Quote quote) {
    	int row = quotes.size();
        this.quotes.add(quote);
        fireTableRowsInserted(row, row);
        return row;
    }
    
    public void removeQuote(int row) {
    	this.quotes.remove(row);
    	this.fireTableRowsDeleted(row, row);
    }

}