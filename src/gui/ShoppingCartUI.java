package gui;

import java.awt.EventQueue;

import javax.swing.JDialog;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.Component;
import javax.swing.Box;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import java.awt.Color;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import controller.ShoppingCartController;
import model.Customer;
import model.ShoppingItemLine;

import javax.swing.ListSelectionModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ShoppingCartUI extends JDialog {
	private Customer customer;
	private JPanel contentPane;
	private JTable table;
	private JButton btnClear;
	private JButton btnAddItem;
	private ShoppingCartTableModel tableModel;
	private JButton btnRemove;
	private JTable mainTable;
	private JButton btnView;
	private JLink btnEditQuantity;
	
	ShoppingCartController shoppingCartCtrl;
	private JLabel lblDiscountValue;
	private JLabel lblTotalValue;
	private JLabel lblSubtotalValue;

	/**
	 * Create the dialog.
	 */
	public ShoppingCartUI(Customer customer) {
		setTitle("Shopping Cart");
		if (customer == null) {
			throw new IllegalArgumentException("Customer cannot be null!");
		}
		this.customer = customer;
		
		shoppingCartCtrl = new ShoppingCartController();
		
		setModal(true);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		// ***** TOP PANEL *****
		JPanel topPanel = new JPanel();
		getContentPane().add(topPanel, BorderLayout.NORTH);
		GridBagLayout gbl_topPanel = new GridBagLayout();
		gbl_topPanel.columnWidths = new int[]{0, 0, 0, 0};
		gbl_topPanel.rowHeights = new int[]{0, 0, 0, 0};
		gbl_topPanel.columnWeights = new double[]{1.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_topPanel.rowWeights = new double[]{0.0, 0.0, 1.0, Double.MIN_VALUE};
		topPanel.setLayout(gbl_topPanel);
			// ***** Title *****
			JLabel lblTitle = new JLabel(
					String.format("%s %s's shopping cart", 
							customer.getFirstName(), 
							customer.getLastName()
					)
			);
			GridBagConstraints gbc_lblTitle = new GridBagConstraints();
			gbc_lblTitle.gridwidth = 3;
			gbc_lblTitle.insets = new Insets(0, 0, 5, 0);
			gbc_lblTitle.gridx = 0;
			gbc_lblTitle.gridy = 0;
			topPanel.add(lblTitle, gbc_lblTitle);
			
			// ***** Button: add item *****
			btnClear = new JButton("Clear");
			GridBagConstraints gbc_btnTest_2 = new GridBagConstraints();
			gbc_btnTest_2.insets = new Insets(0, 0, 5, 5);
			gbc_btnTest_2.gridx = 1;
			gbc_btnTest_2.gridy = 1;
			topPanel.add(btnClear, gbc_btnTest_2);
			
			// ***** button: clear  *****
			btnAddItem = new JButton("Add item");
			GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
			gbc_btnNewButton.insets = new Insets(0, 0, 5, 0);
			gbc_btnNewButton.gridx = 2;
			gbc_btnNewButton.gridy = 1;
			topPanel.add(btnAddItem, gbc_btnNewButton);
			
		// ***** Middle panel *****
		JPanel middlePanel = new JPanel();
		getContentPane().add(middlePanel, BorderLayout.CENTER);
		middlePanel.setLayout(new BorderLayout(0, 0));
			
			// ***** Scroll panel *****
			JScrollPane scrollPanel = new JScrollPane();
			middlePanel.add(scrollPanel);
		
				// ***** Table *****
				tableModel = new ShoppingCartTableModel(this.customer.getShoppingCart());
				mainTable = new JTable();
				mainTable.setModel(tableModel);
				mainTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
				scrollPanel.setViewportView(mainTable);
		
		// ***** Bottom panel *****
		JPanel bottomPanel = new JPanel();
		contentPane.add(bottomPanel, BorderLayout.SOUTH);
		bottomPanel.setLayout(new BorderLayout(0, 0));
		
			// ***** Panel for table options at the bottom *****
			JPanel tableBottomOptionsPanel = new JPanel();
			bottomPanel.add(tableBottomOptionsPanel, BorderLayout.NORTH);
			GridBagLayout gbl_tableBottomOptionsPanel = new GridBagLayout();
			gbl_tableBottomOptionsPanel.columnWidths = new int[]{0, 0, 0, 0, 0};
			gbl_tableBottomOptionsPanel.rowHeights = new int[]{0, 0};
			gbl_tableBottomOptionsPanel.columnWeights = new double[]{1.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
			gbl_tableBottomOptionsPanel.rowWeights = new double[]{0.0, Double.MIN_VALUE};
			tableBottomOptionsPanel.setLayout(gbl_tableBottomOptionsPanel);
		
				// ***** Button: View *****
				btnView = new JLink("View", JLink.COLORS.GREEN);
				btnView.setEnabled(false);
				GridBagConstraints gbc_btnView = new GridBagConstraints();
				gbc_btnView.insets = new Insets(0, 0, 0, 5);
				gbc_btnView.gridx = 1;
				gbc_btnView.gridy = 0;
				tableBottomOptionsPanel.add(btnView, gbc_btnView);
			
				// ***** Button: edit quantity *****
				btnEditQuantity = new JLink("Edit Quantity", JLink.COLORS.INDIGO);
				btnEditQuantity.setEnabled(false);
				GridBagConstraints gbc_btnEditQuantity = new GridBagConstraints();
				gbc_btnEditQuantity.insets = new Insets(0, 0, 0, 5);
				gbc_btnEditQuantity.gridx = 2;
				gbc_btnEditQuantity.gridy = 0;
				tableBottomOptionsPanel.add(btnEditQuantity, gbc_btnEditQuantity);
			
				// ***** Button: Remove *****
				btnRemove = new JLink("Remove", JLink.COLORS.RED);
				btnRemove.setEnabled(false);
				GridBagConstraints gbc_btnRemove = new GridBagConstraints();
				gbc_btnRemove.gridx = 3;
				gbc_btnRemove.gridy = 0;
				tableBottomOptionsPanel.add(btnRemove, gbc_btnRemove);
		
			// ***** Panel for pricing and create quote button *****
			JPanel priceAndSubmitPanel = new JPanel();
			bottomPanel.add(priceAndSubmitPanel, BorderLayout.SOUTH);
			GridBagLayout gbl_priceAndSubmitPanel = new GridBagLayout();
			gbl_priceAndSubmitPanel.columnWidths = new int[]{0, 17, 0, 0, 0};
			gbl_priceAndSubmitPanel.rowHeights = new int[]{0, 0, 0, 0, 0};
			gbl_priceAndSubmitPanel.columnWeights = new double[]{0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
			gbl_priceAndSubmitPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
			priceAndSubmitPanel.setLayout(gbl_priceAndSubmitPanel);
		
				// ***** Subtotal label *****
				JLabel lblSubtotal = new JLabel("Subtotal:");
				GridBagConstraints gbc_lblSubtotal = new GridBagConstraints();
				gbc_lblSubtotal.anchor = GridBagConstraints.WEST;
				gbc_lblSubtotal.insets = new Insets(0, 0, 5, 5);
				gbc_lblSubtotal.gridx = 0;
				gbc_lblSubtotal.gridy = 0;
				priceAndSubmitPanel.add(lblSubtotal, gbc_lblSubtotal);
				
				// ***** Subtotal value *****
				lblSubtotalValue = new JLabel("N/A");
				lblSubtotalValue.setForeground(new Color(102, 102, 102));
				GridBagConstraints gbc_lblSubtotalValue = new GridBagConstraints();
				gbc_lblSubtotalValue.anchor = GridBagConstraints.WEST;
				gbc_lblSubtotalValue.insets = new Insets(0, 0, 5, 5);
				gbc_lblSubtotalValue.gridx = 2;
				gbc_lblSubtotalValue.gridy = 0;
				priceAndSubmitPanel.add(lblSubtotalValue, gbc_lblSubtotalValue);
				
				// ***** Customer type discount label *****
				JLabel lblDiscount = new JLabel(customer.getCustomerType().getName() + " Discount:");
				GridBagConstraints gbc_lblDiscount = new GridBagConstraints();
				gbc_lblDiscount.anchor = GridBagConstraints.WEST;
				gbc_lblDiscount.insets = new Insets(0, 0, 5, 5);
				gbc_lblDiscount.gridx = 0;
				gbc_lblDiscount.gridy = 1;
				priceAndSubmitPanel.add(lblDiscount, gbc_lblDiscount);
				
				// ***** Customer type discount value *****
				lblDiscountValue = new JLabel("N/A");
				lblDiscountValue.setForeground(new Color(0, 102, 0));
				GridBagConstraints gbc_lblDiscountValue = new GridBagConstraints();
				gbc_lblDiscountValue.anchor = GridBagConstraints.WEST;
				gbc_lblDiscountValue.insets = new Insets(0, 0, 5, 5);
				gbc_lblDiscountValue.gridx = 2;
				gbc_lblDiscountValue.gridy = 1;
				priceAndSubmitPanel.add(lblDiscountValue, gbc_lblDiscountValue);
				
				// ***** Total price label *****
				JLabel lblTotal = new JLabel("Total:");
				GridBagConstraints gbc_lblTotal = new GridBagConstraints();
				gbc_lblTotal.insets = new Insets(0, 0, 5, 5);
				gbc_lblTotal.anchor = GridBagConstraints.WEST;
				gbc_lblTotal.gridx = 0;
				gbc_lblTotal.gridy = 2;
				priceAndSubmitPanel.add(lblTotal, gbc_lblTotal);
				
				// ***** Total price value *****
				lblTotalValue = new JLabel("N/A");
				GridBagConstraints gbc_lblTotalValue = new GridBagConstraints();
				gbc_lblTotalValue.anchor = GridBagConstraints.WEST;
				gbc_lblTotalValue.insets = new Insets(0, 0, 5, 5);
				gbc_lblTotalValue.gridx = 2;
				gbc_lblTotalValue.gridy = 2;
				priceAndSubmitPanel.add(lblTotalValue, gbc_lblTotalValue);
				
				// ***** Create quote button *****
				JButton btnCreateQuote = new JButton("Create quote");
				GridBagConstraints gbc_btnCreateQuote = new GridBagConstraints();
				gbc_btnCreateQuote.anchor = GridBagConstraints.EAST;
				gbc_btnCreateQuote.gridx = 3;
				gbc_btnCreateQuote.gridy = 3;
				priceAndSubmitPanel.add(btnCreateQuote, gbc_btnCreateQuote);
		
		// Set price
		this.refreshPriceSection();
		
		// Attach event handler
		this.addEventHandlers();
	}
	
	/*
	 * *******************************************************
	 * *******************  Methods *******************
	 * *******************************************************
	 */
	
	/**
	 * recalculate the subtotal, total price, customer type discount percentage
	 * and update view
	 */
	public void refreshPriceSection() {
		lblSubtotalValue.setText(String.format("%.2f DKK", 
				customer.getShoppingCart().calculateSubtotal()));
		lblDiscountValue.setText(String.format("%d%%",
				customer.getCustomerType().getDiscountPercentage()));
		lblTotalValue.setText(String.format("%.2f DKK", 
				customer.getShoppingCart().calculateTotal()));
	}
	
	
	/*
	 * *******************************************************
	 * *******************  EVENT HANDLERS *******************
	 * *******************************************************
	 */
	public void addEventHandlers() {
		// Refresh price on table change
		mainTable.getModel().addTableModelListener(e -> {
			this.refreshPriceSection();
		});
		
		// toggle bottom table buttons depending on whether a table row is selected
		mainTable.getSelectionModel().addListSelectionListener(e -> {
			if (mainTable.getSelectionModel().isSelectionEmpty()) {
				btnView.setEnabled(false);
				btnEditQuantity.setEnabled(false);
				btnRemove.setEnabled(false);
			} else {
				btnView.setEnabled(true);
				btnEditQuantity.setEnabled(true);
				btnRemove.setEnabled(true);
			}
		});
		
		// Clear button press: Clear shopping cart
		btnClear.addActionListener(e -> {
			tableModel.clear();
			shoppingCartCtrl.clearCart(customer.getShoppingCart());
		});
		
		// Remove item button press: remove the item from data model & table model
		btnRemove.addActionListener(e -> {
			tableModel.remove(mainTable.getSelectedRow());
		});
	}
}
