package gui.panels;

import java.awt.GridBagLayout;
import javax.swing.JLabel;

import java.awt.GridBagConstraints;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import java.awt.BorderLayout;
import javax.swing.JPanel;

import controller.AuthenticationController;
import controller.SupplyController;
import model.Product;
import model.SupplyOrder;

import javax.swing.ListSelectionModel;

import gui.JLink;
import gui.JLink.COLORS;
import gui.panels.tableModels.SupplyOrderTableModel;
import gui.windows.objects.SupplyOrderUI;

public class CRUDSupplyOrder extends JPanel {
	
	
	private JButton btnAddSupplyOrder;
	private SupplyController supplyCtrl;
	private static final long serialVersionUID = -8329527605114016878L;
	private JTable tableMain;
	private SupplyOrderTableModel tableModel;
	private JLink btnView;
	AuthenticationController auth;
	Product product;

	/**
	 * Create the dialog.
	 * Constructor class CRUDCustomersPanel
	 */
	public CRUDSupplyOrder(AuthenticationController auth, Product product) {
		this.auth = auth;
		this.product = product;
		supplyCtrl = new SupplyController();
		setLayout(new BorderLayout(0, 0));
		
		tableModel = new SupplyOrderTableModel(supplyCtrl.getSupplyOrders());
		
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
					String.format("Supply Orders")
			);
			GridBagConstraints gbc_lblTitle = new GridBagConstraints();
			gbc_lblTitle.gridwidth = 2;
			gbc_lblTitle.insets = new Insets(0, 0, 5, 0);
			gbc_lblTitle.gridx = 0;
			gbc_lblTitle.gridy = 0;
			topPanel.add(lblTitle, gbc_lblTitle);
			
			// ***** button: Add customer  *****
			btnAddSupplyOrder = new JButton("Add Supply Order");
			GridBagConstraints gbc_btnAddSupplyOrder = new GridBagConstraints();
			gbc_btnAddSupplyOrder.insets = new Insets(0, 0, 5, 0);
			gbc_btnAddSupplyOrder.gridx = 1;
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
		
		// ***** View button *****
		btnView = new JLink("View", COLORS.GREEN);
		GridBagConstraints gbc_btnView = new GridBagConstraints();
		gbc_btnView.gridx = 3;
		gbc_btnView.gridy = 0;
		bottomPanel.add(btnView, gbc_btnView);
		
		// By default: all selection buttons disabled
		btnView.setEnabled(false);
		
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
			} else {
				// Selected
				btnView.setEnabled(true);
			}
		});

		// View supply order
		btnView.addActionListener(e -> {
			int row = tableMain.getSelectedRow();
			SupplyOrder supplyOrder = tableModel.getObj(row);
			SupplyOrderUI frame = new SupplyOrderUI(auth, supplyOrder, product, SupplyOrderUI.Mode.VIEW);
			frame.setVisible(true);
		});

		// 'ADD supply order' button
		btnAddSupplyOrder.addActionListener(e -> {
			SupplyOrderUI frame = new SupplyOrderUI(auth, product);
			frame.setVisible(true);
			if (frame.getSupplyOrder() != null) {
				tableModel.add(frame.getSupplyOrder());
			}
		});
	}
}
