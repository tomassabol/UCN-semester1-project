package gui.panel;

import java.awt.GridBagLayout;
import javax.swing.JLabel;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.util.regex.Pattern;

import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import java.awt.BorderLayout;
import javax.swing.JPanel;

import controller.AuthenticationController;
import controller.SupplyController;
import model.SupplyOrder;

import javax.swing.ListSelectionModel;
import javax.swing.RowFilter;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import gui.JLink;
import gui.Messages;
import gui.JLink.COLORS;
import gui.panels.tableModel.SupplyOrderTableModel;
import gui.windows.model.SupplyOfferUI;
import gui.windows.model.SupplyOrderUI;

import javax.swing.JTextField;

public class CRUDSupplyOrders extends JPanel {
	
	
	private JButton btnAddSupplyOrder;
	private SupplyController supplyCtrl;
	private static final long serialVersionUID = -8329527605114016878L;
	private JTable tableMain;
	private SupplyOrderTableModel tableModel;
	private JLink btnEdit;
	private JLink btnDelete;
	AuthenticationController auth;
	private JLink btnView;
	private JTextField txtSearch;
	private TableRowSorter<TableModel> rowSorter;

	/**
	 * Create the dialog.
	 * Constructor class CRUDCustomersPanel
	 */
	public CRUDSupplyOrders(AuthenticationController auth) {
		this.auth = auth;
		supplyCtrl = new SupplyController();
		setLayout(new BorderLayout(0, 0));
		
		tableModel = new SupplyOrderTableModel(supplyCtrl.getSupplyOrders());
		
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
					String.format("Supply Orders")
			);
			GridBagConstraints gbc_lblTitle = new GridBagConstraints();
			gbc_lblTitle.gridwidth = 3;
			gbc_lblTitle.insets = new Insets(0, 0, 5, 0);
			gbc_lblTitle.gridx = 0;
			gbc_lblTitle.gridy = 0;
			topPanel.add(lblTitle, gbc_lblTitle);
			
			txtSearch = new JTextField();
			GridBagConstraints gbc_txtSearch = new GridBagConstraints();
			gbc_txtSearch.insets = new Insets(0, 0, 5, 5);
			gbc_txtSearch.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtSearch.gridx = 0;
			gbc_txtSearch.gridy = 1;
			topPanel.add(txtSearch, gbc_txtSearch);
			txtSearch.setColumns(10);
			
			// ***** button: Add customer  *****
			btnAddSupplyOrder = new JButton("Add Supply Order");
			GridBagConstraints gbc_btnAddSupplyOrder = new GridBagConstraints();
			gbc_btnAddSupplyOrder.insets = new Insets(0, 0, 5, 0);
			gbc_btnAddSupplyOrder.gridx = 2;
			gbc_btnAddSupplyOrder.gridy = 1;
			topPanel.add(btnAddSupplyOrder, gbc_btnAddSupplyOrder);
		
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
			btnDelete = new JLink("Delete", COLORS.RED);
			GridBagConstraints gbc_btnDisable = new GridBagConstraints();
			gbc_btnDisable.gridx = 3;
			gbc_btnDisable.gridy = 0;
			bottomPanel.add(btnDelete, gbc_btnDisable);
			
		// Disable CRUD selection options by default
		btnView.setEnabled(false);
		btnEdit.setEnabled(false);
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
	
	/**
	 * @return JTable tableMain
	 */
	public JTable getTable() {
		return tableMain;
	}
	
	/**
	 * @return CustomerTableModel tableModel
	 */
	public SupplyOrderTableModel getTableModel() {
		return tableModel;
	}
	

	/**
	 * Select a customer in the CRUD table.
	 *
	 * @param customer the customer
	 * @return true, if successful
	 */
	public boolean selectCustomer(SupplyOrder supplyOrder) {
		int rows = tableModel.getRowCount();
		for (int i = 0; i < rows; i++) {
			SupplyOrder foundSupplyOrder = tableModel.getObj(i);
			if (foundSupplyOrder == supplyOrder) {
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
				btnView.setEnabled(false);
				btnEdit.setEnabled(false);
				btnDelete.setEnabled(false);
			} else {
				// Get supply order
				int row = tableMain.convertRowIndexToModel(tableMain.getSelectedRow());
				SupplyOrder supplyOrder = tableModel.getObj(row);
				
				// Selected
				btnView.setEnabled(true);
				if (!supplyOrder.isStocked()) {
					btnEdit.setEnabled(true);
					btnDelete.setEnabled(true);
				} else {
					btnEdit.setEnabled(false);
					btnDelete.setEnabled(false);
				}
			}
		});
		
		// Delete supply offer
		btnDelete.addActionListener(e -> {
			int row = tableMain.convertRowIndexToModel(tableMain.getSelectedRow());
			SupplyOrder supplyOrder = tableModel.getObj(row);
			if (Messages.confirm(this, String.format("Are you sure you wish to remove the supply order with the ID '%s'?",
					supplyOrder.getID()))) {
				supplyCtrl.removeSupplyOrder(supplyOrder);
				tableModel.remove(row);
			}
		});

		
		// View supply order
		btnView.addActionListener(e -> {
			int row = tableMain.convertRowIndexToModel(tableMain.getSelectedRow());
			SupplyOrder supplyOrder = tableModel.getObj(row);
			SupplyOrderUI frame = new SupplyOrderUI(auth, supplyOrder, SupplyOrderUI.Mode.VIEW);
			frame.setVisible(true);
		});
		
		// Edit supply order
		btnEdit.addActionListener(e -> {
			int modelIndex = tableMain.convertRowIndexToModel(tableMain.getSelectedRow());
			SupplyOrder supplyOrder = tableModel.getObj(modelIndex);
			SupplyOrderUI frame = new SupplyOrderUI(auth, supplyOrder, SupplyOrderUI.Mode.EDIT);
			frame.setVisible(true);
			tableModel.fireTableRowsUpdated(modelIndex, modelIndex);
		});

		// 'ADD supply order' button
		btnAddSupplyOrder.addActionListener(e -> {
			SupplyOrderUI frame = new SupplyOrderUI(auth);
			frame.setVisible(true);
			if (frame.getSupplyOrder() != null) {
				tableModel.add(frame.getSupplyOrder());
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
