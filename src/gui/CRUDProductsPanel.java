package gui;

import javax.swing.JDialog;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.util.ArrayList;
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

/**
 * @author Daniels Kanepe
 *
 */
public class CRUDProductsPanel extends JPanel {
	
	public enum Display {
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

	/**
	 * Create the dialog.
	 */
	public CRUDProductsPanel(Display shownColumns) {
		productCtrl = new ProductController();
		setLayout(new BorderLayout(0, 0));
		
		// Table model
		ProductTableModel tableModel;
		if (shownColumns == Display.BUYABLE) {
			tableModel = new ProductTableModel(productCtrl.getBuyableProducts());
		} else if (shownColumns == Display.LOANABLE) {
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
			JTable tableMain = new JTable();
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
			JLink btnView = new JLink("View", COLORS.GREEN);
			GridBagConstraints gbc_btnView = new GridBagConstraints();
			gbc_btnView.insets = new Insets(0, 0, 0, 5);
			gbc_btnView.gridx = 1;
			gbc_btnView.gridy = 0;
			bottomPanel.add(btnView, gbc_btnView);
			
			// ***** Edit button *****
			JLink btnEditQuantity = new JLink("Edit", COLORS.INDIGO);
			GridBagConstraints gbc_btnEditQuantity = new GridBagConstraints();
			gbc_btnEditQuantity.insets = new Insets(0, 0, 0, 5);
			gbc_btnEditQuantity.gridx = 2;
			gbc_btnEditQuantity.gridy = 0;
			bottomPanel.add(btnEditQuantity, gbc_btnEditQuantity);
			
			// ***** Disable button *****
			JLink btnRemove = new JLink("Disable", COLORS.RED);
			GridBagConstraints gbc_btnRemove = new GridBagConstraints();
			gbc_btnRemove.gridx = 3;
			gbc_btnRemove.gridy = 0;
			bottomPanel.add(btnRemove, gbc_btnRemove);
		
		
		// Attach event handler
		this.addEventHandlers();
	}
	
	/*
	 * *******************************************************
	 * *******************  Methods *******************
	 * *******************************************************
	 */
	

	
	/*
	 * *******************************************************
	 * *******************  EVENT HANDLERS *******************
	 * *******************************************************
	 */
	private void addEventHandlers() {
		
	}
}
