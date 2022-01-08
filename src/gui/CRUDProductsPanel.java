package gui;

import javax.swing.JDialog;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import java.awt.Color;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import controller.AuthenticationController;
import controller.ProductController;
import controller.QuoteController;
import controller.ShoppingCartController;
import controller.StockController;
import model.Customer;
import model.OutOfStockException;
import model.Product;
import model.Quote;
import model.ShoppingItemLine;

import javax.swing.ListSelectionModel;
import gui.JLink.COLORS;
import gui.ProductTableModel.Column;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * @author Daniels Kanepe
 *
 */
public class CRUDProductsPanel extends JPanel {
	
	public enum Mode {
		BUYABLE,
		LOANABLE,
		ALL;
	}
	
	private JButton btnAddItem;
	private ProductController productCtrl;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8329527605114016878L;
	private JTable tableMain;
	private ProductTableModel tableModel;
	private JLink btnView;
	private JLink btnEdit;
	private JLink btnDisable;

	/**
	 * Create the dialog.
	 */
	public CRUDProductsPanel(Mode shownColumns) {
		productCtrl = new ProductController();
		setLayout(new BorderLayout(0, 0));
		
		if (shownColumns == Mode.BUYABLE) {
			tableModel = new ProductTableModel(productCtrl.getBuyableProducts(), 
					Arrays.asList(
							Column.ID,
							Column.NAME,
							Column.BUYABLE_STOCK,
							Column.DESCRIPTION
							)
					);
		} else if (shownColumns == Mode.LOANABLE) {
			tableModel = new ProductTableModel(new ArrayList<>());
		} else {
			tableModel = new ProductTableModel(productCtrl.getProducts());
		}
		
		
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
					String.format("Products")
			);
			GridBagConstraints gbc_lblTitle = new GridBagConstraints();
			gbc_lblTitle.gridwidth = 2;
			gbc_lblTitle.insets = new Insets(0, 0, 5, 0);
			gbc_lblTitle.gridx = 0;
			gbc_lblTitle.gridy = 0;
			topPanel.add(lblTitle, gbc_lblTitle);
			
			// ***** button: Add product  *****
			btnAddItem = new JButton("Add Product");
			GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
			gbc_btnNewButton.insets = new Insets(0, 0, 5, 0);
			gbc_btnNewButton.gridx = 1;
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
			Product foundProduct = tableModel.getProduct(i);
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
				btnEdit.setEnabled(true);
				btnDisable.setEnabled(true);
			} else {
				// Selected
				btnView.setEnabled(true);
				btnEdit.setEnabled(true);
				btnDisable.setEnabled(true);
			}
		});
		
		// Disable product
		btnDisable.addActionListener(e -> {
			int row = tableMain.getSelectedRow();
			Product product = tableModel.getProduct(row);
			if (Messages.confirm(this, String.format("Are you sure you wish to disable the product '%s'?", product.getName()))) {
				productCtrl.setEnabled(product, false);
				tableModel.remove(row);
			}
		});
	}
}
