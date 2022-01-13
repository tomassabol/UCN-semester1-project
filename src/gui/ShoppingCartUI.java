package gui;

import javax.swing.JDialog;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import controller.AuthenticationController;

import controller.QuoteController;
import controller.ShoppingCartController;
import controller.StockController;
import model.Customer;
import model.OutOfStockException;
import model.Product;
import model.Quote;
import model.ShoppingItemLine;

import javax.swing.ListSelectionModel;

/**
 * @author Daniels Kanepe
 *
 */
public class ShoppingCartUI extends JDialog {
	private AuthenticationController auth;
	
	private Customer customer;
	private JPanel contentPane;
	private JTable table;
	private JButton btnClear;
	private JButton btnAddItem;
	private ShoppingCartTableModel tableModel;
	private JButton btnRemove;
	private JTable tableMain;
	private JButton btnView;
	private JLink btnEditQuantity;
	private JLabel lblDiscountValue;
	private JLabel lblTotalValue;
	private JLabel lblSubtotalValue;
	
	ShoppingCartController shoppingCartCtrl;
	StockController stockCtrl;
	QuoteController quoteCtrl;
	private boolean submitPressed = false;
	private Quote createdQuote = null;
	private JButton btnCreateQuote;

	/**
	 * Create the dialog.
	 */
	public ShoppingCartUI(AuthenticationController auth, Customer customer) {
		this.auth = auth;
		setTitle("Shopping Cart");
		if (customer == null) {
			throw new IllegalArgumentException("Customer cannot be null!");
		}
		this.customer = customer;
		
		shoppingCartCtrl = new ShoppingCartController();
		quoteCtrl = new QuoteController();
		stockCtrl = new StockController();
		
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
				tableMain = new JTable();
				tableMain.setModel(tableModel);
				tableMain.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
				scrollPanel.setViewportView(tableMain);
		
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
				btnCreateQuote = new JButton("Create quote");
				GridBagConstraints gbc_btnCreateQuote = new GridBagConstraints();
				gbc_btnCreateQuote.anchor = GridBagConstraints.EAST;
				gbc_btnCreateQuote.gridx = 3;
				gbc_btnCreateQuote.gridy = 3;
				priceAndSubmitPanel.add(btnCreateQuote, gbc_btnCreateQuote);
		
		// Set price
		this.refreshPriceSection();
		
		// Toggle create quote (if needed)
		this.toggleCreateQuote();
		
		// Attach event handler
		this.addEventHandlers();
	}
	
	/*
	 * *******************************************************
	 * *******************  Methods *******************
	 * *******************************************************
	 */
	
	// This method allows the parent UI to check if 'create quote' button was pressed
	public boolean isSubmitPressed() {
		return this.submitPressed;
	}
	
	/**
	 * Gets the created quote
	 *
	 * @return the created quote, or null
	 */
	public Quote getCreatedQuote() {
		return this.createdQuote;
	}
	
	/**
	 * recalculate the subtotal, total price, customer type discount percentage
	 * and update view
	 */
	private void refreshPriceSection() {
		lblSubtotalValue.setText(String.format("%.2f DKK", 
				customer.getShoppingCart().calculateSubtotal()));
		lblDiscountValue.setText(String.format("%d%%",
				customer.getCustomerType().getDiscountPercentage()));
		lblTotalValue.setText(String.format("%.2f DKK", 
				customer.getShoppingCart().calculateTotal()));
	}
	
	/**
	 * Enables create quote if shopping cart is not empty, else disables
	 */
	private void toggleCreateQuote() {
		if (customer.getShoppingCart().isEmpty()) {
			btnCreateQuote.setEnabled(false);
		} else {
			btnCreateQuote.setEnabled(true);
		}
	}
	
	/**
	 * 'Add item' button code
	 */
	private void addItem() {
		AddProductToCartUI frame = new AddProductToCartUI();
		frame.setVisible(true);
		
		if (frame.isProductSelected()) {
			Product product = frame.getSelectedProduct();
			int quantity = frame.getSelectedQuantity();
			try {
				ShoppingItemLine itemLine = shoppingCartCtrl.addProduct(customer.getShoppingCart(),
						product,
						quantity);
				tableModel.add(itemLine);
			} catch (OutOfStockException e1) {
				Messages.error(this, 
						String.format("There are only %d items in stock. You were trying to add %d items while you already have %d in your shopping cart", 
								stockCtrl.getBuyableQuantityInStock(product), 
								quantity,
								customer.getShoppingCart().getQuantity(product)));
				// execute itself, again
				this.addItem();
			}
			
			// Enable 'create quote button'
			this.toggleCreateQuote();
		}
	}
	
	/*
	 * *******************************************************
	 * *******************  EVENT HANDLERS *******************
	 * *******************************************************
	 */
	public void addEventHandlers() {
		// Refresh price on table change
		tableMain.getModel().addTableModelListener(e -> {
			this.refreshPriceSection();
		});
		
		// toggle bottom table buttons depending on whether a table row is selected
		tableMain.getSelectionModel().addListSelectionListener(e -> {
			if (tableMain.getSelectionModel().isSelectionEmpty()) {
				// NOT SELECTED
				btnView.setEnabled(false);
				btnEditQuantity.setEnabled(false);
				btnRemove.setEnabled(false);
			} else {
				// SELECTED
				btnView.setEnabled(true);
				btnEditQuantity.setEnabled(true);
				btnRemove.setEnabled(true);
			}
		});
		
		// 'ADD ITEM' button
		btnAddItem.addActionListener(e -> {
			this.addItem();
		});
		
		// Clear button: Clear the shopping cart
		btnClear.addActionListener(e -> {
			// Clear shopping cart
			if (Messages.confirm(this, "Are you sure you want to clear the shopping cart?")) {
				// Clear cart in data model
				shoppingCartCtrl.clearCart(customer.getShoppingCart());
				// Clear rendered table
				tableModel.clear();
				
			}
			// Disable 'create quote' button
			btnCreateQuote.setEnabled(false);
		});
		
		// Remove item button: remove the item from data model & table model
		btnRemove.addActionListener(e -> {
			tableModel.remove(tableMain.getSelectedRow());
		});
		
		// Create quote button: create quote & dispose of the frame
		// Note: it does not add it to the container. 
		// - Use getCreatedQuote() to do that where needed (e.g. in parent UI)
		btnCreateQuote.addActionListener(e -> {
			// Empty shopping cart
			if (customer.getShoppingCart().isEmpty()) {
				Messages.error(this, "Cannot create a quote! The shopping cart is empty.");
				return;
			}
			// Create quote
			if (Messages.confirm(this, "A quote cannot be changed. Are you sure you want to create a quote with these items?")) {
				try {
					this.createdQuote = quoteCtrl.createQuote(customer, auth.getLoggedInUser());
				} catch (OutOfStockException e1) {
					Messages.error(this, "Could not create a quote as some of the items are out of stock!");
				}
				this.submitPressed = true;
				this.dispose();
			}

		});
		
		// Edit quantity button
		btnEditQuantity.addActionListener(e -> {
			// Get selected item line
			int row = tableMain.getSelectedRow();
			if (row == -1) {
				return;
			}
			ShoppingItemLine itemLine = tableModel.getItemLine(row);
			if (itemLine == null) {
				return;
			}
			
			// Get quantity
	        Object inputDialogResponse = JOptionPane.showInputDialog(this,
	        		String.format("New quantity for '%s'", itemLine.getPRODUCT().getName()),
	        		"New Quantity",
	        		JOptionPane.PLAIN_MESSAGE,
	        		null,null,
	        		itemLine.getQuantity());
	        // If cancel not pressed:
	        if (inputDialogResponse != null) {
	        	String newQuantityString = String.valueOf(inputDialogResponse);
		        int newQuantity = 0;
		        
		        // Convert to int or throw exception
				try {
					newQuantity = Integer.parseInt(newQuantityString);
				} catch (NumberFormatException e1) {
					Messages.error(this, "The entered quantity must be a positive, whole number!");
					return;
				}
				// Throw error if quantity < 1
				if (newQuantity < 1) {
					Messages.error(this, "The entered quantity must be a positive number!");
					return;
				}
				// Update the item line's quantity
				try {
					// Update data model's quantity
					shoppingCartCtrl.updateQuantity(itemLine, newQuantity);
					// Update rendered table
					tableModel.fireTableRowsUpdated(row, row);
				} catch (OutOfStockException e2) {
					Messages.error(this,
							String.format("You entered %d, but there are only %d item(s) in stock", 
									newQuantity,
									stockCtrl.getBuyableQuantityInStock(itemLine.PRODUCT)));
				}
	        }
		});
		
		// View button
		btnView.addActionListener(e -> {
			Messages.info(this, "Not implemented yet!");
		});
		
		// Remove item line button
		btnRemove.addActionListener(e -> {
			// Get selected item line
			int row = tableMain.getSelectedRow();
			if (row == -1) {
				return;
			}
			ShoppingItemLine itemLine = tableModel.getItemLine(row);
			if (itemLine == null) {
				return;
			}
			
			// Remove the item line from shopping cart
			customer.getShoppingCart().remove(itemLine);
			// NOTE: how there is no need to update table, because selection change event does it for us.
			
			// Toggle create quote (if needed)
			this.toggleCreateQuote();
		});
	}
}
