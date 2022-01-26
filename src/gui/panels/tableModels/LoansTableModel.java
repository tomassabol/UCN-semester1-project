package gui.panels.tableModels;


import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import controller.LoanController;
import gui.Common;
import model.Loan;

public class LoansTableModel extends AbstractTableModel {

    LoanController loanCtrl;

    //private static final String DATE_FORMAT = "yyyy-MM-dd";

	protected static final String COLUMN_NAMES[] = {
        "ID", "Created", "Return date", "Product", "Customer","customer discount", "Period", "Price /day", "Total Price", "Returned"
    };

    private List<Loan> loans;

    
    /**
     * Constructor
     */
    public LoansTableModel(List<Loan> loans) {
        // Copying due to accidental mutation (theoretically all fields are constants),
    	//but also consistent order
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
            case 1: return Common.datetimeToString(loan.getCreationDate());
            case 2: return Common.dateToString(loan.getReturnDate());
            case 3: return loan.getProduct().getName();
            case 4: return String.format("ID: %d", loan.getCustomer().getID());
            case 5: return String.format("%s: -%d%%", 
                    loan.getCustomerType(),
                    loan.getCustomerTypeDiscountPercentage());
            case 6: return String.format("%d days", loanCtrl.getDaysOfLoan(loan));     
            case 7: return String.format("%.2f DKK", loan.getLoaningPrice());
            case 8: return String.format("%.2f DKK", loanCtrl.totalPrice(loan));
            case 9: return loan.isReturned();
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
     * @return the row that the order was inserted in
     */
    public int addLoan(Loan loan) {
    	int row = loans.size();
        this.loans.add(loan);
        fireTableRowsInserted(row, row);
        return row;
    }
    
    public void removeLoan(int row) {
    	this.loans.remove(row);
    	this.fireTableRowsDeleted(row, row);
    }

}