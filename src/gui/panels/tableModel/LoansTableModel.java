package gui.panels.tableModel;


import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import controller.LoanController;
import gui.Common;
import model.Loan;

public class LoansTableModel extends AbstractTableModel {

    LoanController loanCtrl;

	protected static final String COLUMN_NAMES[] = {
        "ID", "Customer", "From", "To", "Product","Discount", "Price", "Returned"
    };

    private List<Loan> loans;

    
    /**
     * Constructor
     */
    public LoansTableModel(List<Loan> loans) {
        // Copying due to accidental mutation & consistent order
        this.loans = new ArrayList<Loan>(loans);
        
        loanCtrl = new LoanController();
    }
    

    @Override
    public int getRowCount() {
        return loans.size();
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
    	Loan loan = loans.get(rowIndex);
        switch (columnIndex) {
            case 0: return "#" + loan.ID;
            case 1: return String.format("(%s) %s %s", 
            		loan.getCustomer().getID(),
            		loan.getCustomer().getFirstName(),
            		loan.getCustomer().getLastName());
            case 2: return Common.datetimeToString(loan.getCreationDate());
            case 3: return Common.datetimeToString(loan.getReturnDate());
            case 4: return String.format("(%s) %s", 
            		loan.getItem().getProduct().ID,
            		loan.getItem().getProduct().getName());
            case 5: return String.format("%s: -%d%%", 
                    loan.getCustomerType(),
                    loan.getCustomerTypeDiscountPercentage());
            case 6: return String.format("%.2f DKK", loan.getPrice());     
            case 7: return loan.isReturned() ? loan.getReturnDate() : "-";
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
    public Loan getObj(int row) {
    	return loans.get(row);
    }
    
    /**
     * Adds a order to the table.
     *
     * @param order the order
     */
    public void addLoan(Loan loan) {
    	this.loans.add(loan);
    	this.fireTableRowsInserted(this.getRowCount() - 1, this.getRowCount() -1);
    }
    
    public void removeLoan(int row) {
    	this.loans.remove(row);
    	this.fireTableRowsDeleted(row, row);
    }

}