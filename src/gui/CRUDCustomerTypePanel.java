package gui;

import java.awt.GridBagLayout;
import javax.swing.JLabel;

import java.awt.GridBagConstraints;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import java.awt.BorderLayout;
import javax.swing.JPanel;

import controller.CustomerController;
import model.CustomerType;

import javax.swing.ListSelectionModel;
import gui.JLink.COLORS;

/**
 * @author Daniels Kanepe
 *
 */
public class CRUDCustomerTypePanel extends JPanel {
	
	
	private JButton btnAddCustomerType;
	private CustomerController customerCtrl;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8329527605114016878L;
	private JTable tableMain;
	private CustomerTypeTableModel tableModel;
	private JLink btnView;
	private JLink btnEdit;
	private JLink btnDisable;

	/**
	 * Create the dialog.
	 */
	public CRUDCustomerTypePanel() {
		customerCtrl = new CustomerController();
		setLayout(new BorderLayout(0, 0));

		tableModel = new CustomerTypeTableModel(customerCtrl.getCustomerTypes());
		
		// ***** TOP PANEL *****
		JPanel topPanel = new JPanel();
		this.add(topPanel, BorderLayout.NORTH);
		GridBagLayout gbl_topPanel = new GridBagLayout();
		gbl_topPanel.columnWidths = new int[]{0, 0, 0};
		gbl_topPanel.rowHeights = new int[]{0, 0, 0, 0};
		gbl_topPanel.columnWeights = new double[]{1.0, 0.0, Double.MIN_VALUE};
		gbl_topPanel.rowWeights = new double[]{0.0, 0.0, 1.0, Double.MIN_VALUE};
		topPanel.setLayout(gbl_topPanel);
			// ***** Title *****
			JLabel lblTitle = new JLabel(
					String.format("Customer Types")
			);
			GridBagConstraints gbc_lblTitle = new GridBagConstraints();
			gbc_lblTitle.gridwidth = 2;
			gbc_lblTitle.insets = new Insets(0, 0, 5, 0);
			gbc_lblTitle.gridx = 0;
			gbc_lblTitle.gridy = 0;
			topPanel.add(lblTitle, gbc_lblTitle);
			
			// ***** button: Add product  *****
			btnAddCustomerType = new JButton("Add Customer Type");
			GridBagConstraints gbc_btnAddCustomerType = new GridBagConstraints();
			gbc_btnAddCustomerType.insets = new Insets(0, 0, 5, 0);
			gbc_btnAddCustomerType.gridx = 1;
			gbc_btnAddCustomerType.gridy = 1;
			topPanel.add(btnAddCustomerType, gbc_btnAddCustomerType);
		
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
			
			// ***** View button *****
			btnView = new JLink("View", COLORS.GREEN);
			GridBagConstraints gbc_btnView = new GridBagConstraints();
			gbc_btnView.insets = new Insets(0, 0, 0, 5);
			gbc_btnView.gridx = 1;
			gbc_btnView.gridy = 0;
			bottomPanel.add(btnView, gbc_btnView);
			
			// ***** Edit button *****
			btnEdit = new JLink("Edit", COLORS.INDIGO);
			GridBagConstraints gbc_btnEdit = new GridBagConstraints();
			gbc_btnEdit.insets = new Insets(0, 0, 0, 5);
			gbc_btnEdit.gridx = 2;
			gbc_btnEdit.gridy = 0;
			bottomPanel.add(btnEdit, gbc_btnEdit);
			
			
			// ***** Disable button *****
			btnDisable = new JLink("Delete", COLORS.RED);
			GridBagConstraints gbc_btnDisable = new GridBagConstraints();
			gbc_btnDisable.gridx = 3;
			gbc_btnDisable.gridy = 0;
			bottomPanel.add(btnDisable, gbc_btnDisable);
		
		// By default: all selection buttons disabled
		btnView.setEnabled(false);
		btnEdit.setEnabled(false);
		btnDisable.setEnabled(false);
		
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
	
	public CustomerTypeTableModel getTableModel() {
		return tableModel;
	}
	

	/**
	 * Select a customer in the CRUD table.
	 *
	 * @param customer the customer
	 * @return true, if successful
	 */
	public boolean selectCustomerType(CustomerType customerType) {
		int rows = tableModel.getRowCount();
		for (int i = 0; i < rows; i++) {
			CustomerType foundCustomerType = tableModel.getObj(i);
			if (foundCustomerType == customerType) {
				tableMain.getSelectionModel().setSelectionInterval(0, i);
				return true;
			}
		}
		return false;
	}

	/**
	 * 'Add customer' button code
	 */
	private void addCustomer() {
		AddCustomerUI frame = new AddCustomerUI();
		frame.setVisible(true);
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
				btnView.setEnabled(false);
				btnEdit.setEnabled(true);
				btnDisable.setEnabled(true);
			} else {
				// Selected
				btnView.setEnabled(true);
				btnEdit.setEnabled(true);
				btnDisable.setEnabled(true);
			}
		});
		
		// Delete customer
		btnDisable.addActionListener(e -> {
			int row = tableMain.getSelectedRow();
			CustomerType customerType = tableModel.getObj(row);
			if (Messages.confirm(this, String.format("Are you sure you wish to delete the customer '%s %s'?", customerType.getName()))) {
				//customerCtrl.removeCustomer(customerType);
				tableModel.remove(row);
			}
		});

		// View Customer
		btnView.addActionListener(e -> {
			int row = tableMain.getSelectedRow();
			CustomerType customerType = tableModel.getObj(row);
			//CustomerUI frame = new CustomerUI(auth, customer, CustomerUI.Mode.VIEW);
			//frame.setVisible(true);
		});

		// Edit customer
		btnEdit.addActionListener(e -> {
			int row = tableMain.getSelectedRow();
			CustomerType customerType = tableModel.getObj(row);
			//CustomerUI frame = new CustomerUI(auth, customer, CustomerUI.Mode.EDIT);
			//frame.setVisible(true);
			tableModel.fireTableRowsUpdated(row, row);
		});

		// 'ADD Customer Type' button
		btnAddCustomerType.addActionListener(e -> {
            System.out.println("add customer button");
			this.addCustomer();
		});
	}
}
