package gui.panels;

import java.awt.GridBagLayout;
import javax.swing.JLabel;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.util.Arrays;
import java.util.regex.Pattern;

import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import java.awt.BorderLayout;
import javax.swing.JPanel;

import controller.AuthenticationController;
import controller.ProductController;

import javax.swing.ListSelectionModel;
import javax.swing.RowFilter;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import gui.JLink;
import gui.Messages;
import gui.JLink.COLORS;
import gui.panels.tableModels.ProductTableModel;
import gui.panels.tableModels.ProductTableModel.Column;
import gui.windows.model.ProductUI;
import models.Product;

import javax.swing.JTextField;
/**
 * @author Daniels Kanepe
 *
 */
public class CRUDProducts extends JPanel {
	
	public enum Mode {
		BUYABLE,
		LOANABLE,
		ALL;
	}
	
	private JButton btnAddItem;
	private ProductController productCtrl;
	private TableRowSorter<TableModel> rowSorter;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8329527605114016878L;
	private JTable tableMain;
	private ProductTableModel tableModel;
	private JLink btnView;
	private JLink btnEdit;
	private JLink btnDisable;
	private AuthenticationController auth;
	private JTextField txtSearch;

	/**
	 * Create the dialog.
	 */
	public CRUDProducts(AuthenticationController auth, Mode shownColumns) {
		this.auth = auth;
		productCtrl = new ProductController();
		setLayout(new BorderLayout(0, 0));
		
		if (shownColumns == Mode.BUYABLE) {
			tableModel = new ProductTableModel(productCtrl.getBuyableProducts(), 
					Arrays.asList(
							Column.ID,
							Column.NAME,
							Column.BUY_PRICE,
							Column.BUYABLE_STOCK,
							Column.DESCRIPTION,
							Column.ENABLED
							)
					);
		} else if (shownColumns == Mode.LOANABLE) {
			tableModel = new ProductTableModel(productCtrl.getLoanableProducts(), 
					Arrays.asList(
							Column.ID,
							Column.NAME,
							Column.LOAN_PRICE,
							Column.LOANABLE_STOCK,
							Column.DESCRIPTION,
							Column.ENABLED
							)
					);
		} else {
			tableModel = new ProductTableModel(productCtrl.getProducts());
		}
		
		
		
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
			String.format("Products")
		);
		GridBagConstraints gbc_lblTitle = new GridBagConstraints();
		gbc_lblTitle.insets = new Insets(0, 0, 5, 0);
		gbc_lblTitle.gridx = 1;
		gbc_lblTitle.gridy = 0;
		topPanel.add(lblTitle, gbc_lblTitle);
			
		txtSearch = new JTextField();
		GridBagConstraints gbc_txtSearch = new GridBagConstraints();
		gbc_txtSearch.insets = new Insets(0, 0, 5, 5);
		gbc_txtSearch.gridx = 0;
		gbc_txtSearch.gridy = 1;
		topPanel.add(txtSearch, gbc_txtSearch);
		txtSearch.setColumns(10);
			
		// ***** button: Add product  *****
		btnAddItem = new JButton("Add Product");
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.insets = new Insets(0, 0, 5, 0);
		gbc_btnNewButton.gridx = 2;
		gbc_btnNewButton.gridy = 1;
		topPanel.add(btnAddItem, gbc_btnNewButton);
		
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
		btnDisable = new JLink("Disable", COLORS.RED);
		GridBagConstraints gbc_btnDisable = new GridBagConstraints();
		gbc_btnDisable.gridx = 3;
		gbc_btnDisable.gridy = 0;
		bottomPanel.add(btnDisable, gbc_btnDisable);
		
		// By default: all selection buttons disabled
		btnView.setEnabled(false);
		btnEdit.setEnabled(false);
		btnDisable.setEnabled(false);
		
		// Add filtering
		rowSorter = new TableRowSorter<TableModel>(tableModel);
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
	
	public ProductTableModel getTableModel() {
		return tableModel;
	}
	
	/**
	 * Select a product in the CRUD table.
	 *
	 * @param product the product
	 * @return true, if successful
	 */
	public boolean selectProduct(Product product) {
		int rows = tableModel.getRowCount();
		for (int i = 0; i < rows; i++) {
			Product foundProduct = tableModel.getObj(i);
			if (foundProduct == product) {
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
				btnDisable.setEnabled(false);
			} else {
				// Selected
				int row = tableMain.getSelectedRow();
				Product product = tableModel.getObj(row);
				btnView.setEnabled(true);
				btnEdit.setEnabled(true);
				btnDisable.setEnabled(true);
				if (product.isEnabled()) {
					btnDisable.setText("Disable");
				} else {
					btnDisable.setText("Enable");
				}

			}
		});
		
		// Disable product
		btnDisable.addActionListener(e -> {
			int row = tableMain.convertRowIndexToModel(tableMain.getSelectedRow());
			Product product = tableModel.getObj(row);
			String keyword = product.isEnabled() ? "disable" : "enable";
			if (Messages.confirm(this, String.format("Are you sure you wish to %s the product '%s'?",
					keyword,
					product.getName()))) {
				productCtrl.setEnabled(product, !product.isEnabled());
				tableModel.fireTableRowsUpdated(row, row);
				tableMain.getSelectionModel().clearSelection();
			}
		});
		
		// View product
		btnView.addActionListener(e -> {
			int row = tableMain.convertRowIndexToModel(tableMain.getSelectedRow());
			Product product = tableModel.getObj(row);
			ProductUI frame = new ProductUI(auth, product, ProductUI.Mode.VIEW);
			frame.setVisible(true);
		});
		
		// Edit product
		btnEdit.addActionListener(e -> {
			int row = tableMain.convertRowIndexToModel(tableMain.getSelectedRow());
			Product product = tableModel.getObj(row);
			ProductUI frame = new ProductUI(auth, product, ProductUI.Mode.EDIT);
			frame.setVisible(true);
			tableModel.fireTableRowsUpdated(row, row);
			// Refresh selection (e.g. in case sell price is now set to nothing)
			tableMain.clearSelection();
			tableMain.getSelectionModel().setSelectionInterval(0, row);
		});
		
		// Create product
		btnAddItem.addActionListener(e -> {
			ProductUI frame = new ProductUI(auth);
			frame.setVisible(true);
			if (frame.getProduct() != null) {
				tableModel.add(frame.getProduct());
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
