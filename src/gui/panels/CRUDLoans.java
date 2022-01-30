package gui.panels;

import java.awt.GridBagLayout;
import javax.swing.JLabel;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.util.regex.Pattern;

import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import java.awt.BorderLayout;
import javax.swing.JPanel;

import controller.AuthenticationController;
import controller.LoanController;

import javax.swing.ListSelectionModel;
import javax.swing.RowFilter;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import gui.JLink;
import gui.Messages;
import gui.JLink.COLORS;
import gui.panels.tableModels.LoansTableModel;
import gui.windows.model.LoanCreateUI;
import models.Customer;
import models.Employee;
import models.Loan;

/**
 * @author Daniels Kanepe
 *
 */
public class CRUDLoans extends JPanel {
	
	
	private JButton btnAddLoan;
	private LoanController loanCtrl;
	
	private JTable tableMain;
	private LoansTableModel tableModel;
	private JLink btnDelete;
	private JTextField txtSearch;
	private TableRowSorter<TableModel> rowSorter;
	AuthenticationController auth;
	Customer customer;
	boolean returnLoan;

	/**
	 * Constructor class CRUDLoans
	 */
	public CRUDLoans(AuthenticationController auth, Customer customer) {
		this.auth = auth;
		this.customer = customer;
		
		loanCtrl = new LoanController();
		setLayout(new BorderLayout(0, 0));
		tableModel = new LoansTableModel(loanCtrl.getLoans(customer));
		
		// ***** TOP PANEL *****
		JPanel topPanel = new JPanel();
		this.add(topPanel, BorderLayout.NORTH);
		GridBagLayout gbl_topPanel = new GridBagLayout();
		gbl_topPanel.columnWidths = new int[]{0, 0, 0, 0};
		gbl_topPanel.rowHeights = new int[]{0, 0, 0, 0};
		gbl_topPanel.columnWeights = new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_topPanel.rowWeights = new double[]{0.0, 0.0, 1.0, Double.MIN_VALUE};
		topPanel.setLayout(gbl_topPanel);
			
		// ***** Title *****
		JLabel lblTitle = new JLabel(
			String.format("Loans")
		);
		GridBagConstraints gbc_lblTitle = new GridBagConstraints();
		gbc_lblTitle.gridwidth = 3;
		gbc_lblTitle.insets = new Insets(0, 0, 5, 0);
		gbc_lblTitle.gridx = 0;
		gbc_lblTitle.gridy = 0;
		topPanel.add(lblTitle, gbc_lblTitle);
		
		// ***** Search bar *****
		txtSearch = new JTextField();
		GridBagConstraints gbc_txtSearch = new GridBagConstraints();
		gbc_txtSearch.insets = new Insets(0, 0, 5, 5);
		gbc_txtSearch.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtSearch.gridx = 0;
		gbc_txtSearch.gridy = 1;
		topPanel.add(txtSearch, gbc_txtSearch);
		txtSearch.setColumns(10);
			
		// ***** button: Add customer type  *****
		btnAddLoan = new JButton("Add Loan");
		GridBagConstraints gbc_btnAddLoan = new GridBagConstraints();
		gbc_btnAddLoan.insets = new Insets(0, 0, 5, 0);
		gbc_btnAddLoan.gridx = 2;
		gbc_btnAddLoan.gridy = 1;
		topPanel.add(btnAddLoan, gbc_btnAddLoan);
		
		// ***** Middle panel: Scroll panel *****
		JScrollPane scrollPanel = new JScrollPane();
		add(scrollPanel, BorderLayout.CENTER);
		
		// ***** Table *****
		tableMain = new JTable();
		tableMain.setModel(tableModel);
		tableMain.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPanel.setViewportView(tableMain);
		
		// ***** Bottom panel *****
		JPanel bottomPanel = new JPanel();
		this.add(bottomPanel, BorderLayout.SOUTH);
		GridBagLayout gbl_bottomPanel = new GridBagLayout();
		gbl_bottomPanel.columnWidths = new int[]{271, 0, 0, 0, 0};
		gbl_bottomPanel.rowHeights = new int[]{21, 0};
		gbl_bottomPanel.columnWeights = new double[]{1.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_bottomPanel.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		bottomPanel.setLayout(gbl_bottomPanel);
				
		// ***** Disable button *****
		btnDelete = new JLink("Delete", COLORS.RED);
		GridBagConstraints gbc_btnDelete = new GridBagConstraints();
		gbc_btnDelete.gridx = 3;
		gbc_btnDelete.gridy = 0;
		bottomPanel.add(btnDelete, gbc_btnDelete);
		btnDelete.setEnabled(false);
		
		// Add filtering
		rowSorter = new TableRowSorter<>(tableModel);
		tableMain.setRowSorter(rowSorter);
		
		// Attach event handler
		this.addEventHandlers();
	}
	
	/*
	 * *******************************************************
	 * *******************  Methods *******************
	 * *******************************************************
	 */
	
	public JTable getTable() {
		return tableMain;
	}
	
	public LoansTableModel getTableModel() {
		return tableModel;
	}

	public void setTableModel(LoansTableModel tableModel) {
		this.tableMain.setModel(tableModel);
		this.tableModel = tableModel;
		// Update table row sorter
		rowSorter = new TableRowSorter<>(tableMain.getModel());
		tableMain.setRowSorter(rowSorter);
	}

	/**
	 * Select a loan in the CRUD table.
	 *
	 * @param loan the loan
	 * @return true, if successful
	 */
	public boolean selectLoan(Loan loan) {
		int rows = tableModel.getRowCount();
		for (int i = 0; i < rows; i++) {
			Loan foundLoan = tableModel.getObj(i);
			if (foundLoan == loan) {
				tableMain.getSelectionModel().setSelectionInterval(0, i);
				return true;
			}
		}
		return false;
	}


	
	/*
	 * *******************************************************
	 * *******************  EVENT HANDLERS *******************
	 * *******************************************************
	 */

	 private void addEventHandlers() {
		// Table row selection
		tableMain.getSelectionModel().addListSelectionListener(e -> {
			if (tableMain.getSelectionModel().isSelectionEmpty()) {
				// Not selected
				btnDelete.setEnabled(false);
			} else {
				int row = tableMain.convertRowIndexToModel(tableMain.getSelectedRow());
				Loan loan = tableModel.getObj(row);
				if (loan.isReturned()) {
					// Selected and returned
					btnDelete.setEnabled(true);
				} else {
					// selected and NOT returned
					btnDelete.setEnabled(false);
				}
			}
		});

		// Delete loan
		btnDelete.addActionListener(e -> {
			int row = tableMain.convertRowIndexToModel(tableMain.getSelectedRow());
			Loan loan = tableModel.getObj(row);
			if (Messages.confirm(this, String.format("Are you sure you wish to delete the loan '%s'?", loan.getID()))) {
				loanCtrl.removeLoan(loan);
				tableModel.removeLoan(row);
				setTableModel(tableModel);
			}
		});

		// Add loan
		btnAddLoan.addActionListener(e -> {
			LoanCreateUI frame = new LoanCreateUI(auth, customer);
			frame.setVisible(true);
			if (frame.getLoan() != null) {
				tableModel.addLoan(frame.getLoan());
				setTableModel(tableModel);
			}
		});
		
		// Search implementation
		txtSearch.getDocument().addDocumentListener(new DocumentListener(){
			
			private void search() {
				String text = txtSearch.getText();
				if(text.trim().length() == 0) {
					rowSorter.setRowFilter(null);
				} else {
					rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + Pattern.quote(text)));
				}
			}
													
			@Override
			public void insertUpdate(DocumentEvent e) {
				search();
			}
										
			@Override
			public void  removeUpdate(DocumentEvent e) {
				search();
			}
													
			@Override
			public void changedUpdate(DocumentEvent e) { /* Empty due to interface */ }
		});
	}
}
