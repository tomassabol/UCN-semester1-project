package gui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.EventQueue;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;

import controller.ProductController;
import model.Product;
import model.SellingPrice;

import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.GridBagConstraints;
import javax.swing.JTextField;
import java.awt.Insets;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Objects;
import java.awt.event.ActionEvent;

public class ProductUI extends JDialog {
	
	public enum Mode {
		VIEW,
		EDIT
	}

	private JPanel contentPane;
	private JTextField txtId;
	private JTextField txtName;
	private JTextField txtMax;
	private JTextField txtMin;
	private JTextField txtLoaning;
	private JTextField txtSelling;
	private JButton btnOk;
	private JTextArea txtDescription;
	private Product product;
	private String loaningPrice;
	private boolean view;
	ProductController productCtrl;
	private Mode mode;

	/**
	 * Create the frame.
	 */
	public ProductUI(Product product, Mode mode) {
		this.mode = mode;
		this.product = product;
		
		productCtrl = new ProductController();
		
		setModal(true);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 330);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{208, 208, 0};
		gbl_contentPane.rowHeights = new int[]{27, 19, 0, 0, 0, 0, 0, 19, 0, 0, 0};
		gbl_contentPane.columnWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		// Check if the product has a loaning price
		if(product.getLatestLoaningPrice() == null) {
			loaningPrice = "null";
		} else {
			loaningPrice = String.valueOf(product.getLatestLoaningPrice());
		}
		
		// Header
		JLabel lblTitle = new JLabel("Product: " + product.getName());
		lblTitle.setFont(new Font("Tahoma", Font.BOLD, 14));
		GridBagConstraints gbc_lblTitle = new GridBagConstraints();
		gbc_lblTitle.anchor = GridBagConstraints.WEST;
		gbc_lblTitle.insets = new Insets(0, 0, 5, 5);
		gbc_lblTitle.gridx = 0;
		gbc_lblTitle.gridy = 0;
		contentPane.add(lblTitle, gbc_lblTitle);
		
		JLabel lblId = new JLabel("Id");
		GridBagConstraints gbc_lblId = new GridBagConstraints();
		gbc_lblId.anchor = GridBagConstraints.WEST;
		gbc_lblId.insets = new Insets(0, 0, 5, 5);
		gbc_lblId.gridx = 0;
		gbc_lblId.gridy = 1;
		contentPane.add(lblId, gbc_lblId);
		
		txtId = new JTextField();
		txtId.setText(String.valueOf(product.ID));
		txtId.setColumns(10);
		GridBagConstraints gbc_txtId = new GridBagConstraints();
		gbc_txtId.anchor = GridBagConstraints.WEST;
		gbc_txtId.insets = new Insets(0, 0, 5, 5);
		gbc_txtId.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtId.gridx = 0;
		gbc_txtId.gridy = 2;
		contentPane.add(txtId, gbc_txtId);
		
		JLabel lblName = new JLabel("Name");
		GridBagConstraints gbc_lblName = new GridBagConstraints();
		gbc_lblName.anchor = GridBagConstraints.WEST;
		gbc_lblName.insets = new Insets(0, 0, 5, 0);
		gbc_lblName.gridx = 1;
		gbc_lblName.gridy = 1;
		contentPane.add(lblName, gbc_lblName);
		
		txtName = new JTextField();
		txtName.setText(product.getName());
		txtName.setColumns(10);
		GridBagConstraints gbc_txtName = new GridBagConstraints();
		gbc_txtName.anchor = GridBagConstraints.WEST;
		gbc_txtName.insets = new Insets(0, 0, 5, 0);
		gbc_txtName.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtName.gridx = 1;
		gbc_txtName.gridy = 2;
		contentPane.add(txtName, gbc_txtName);
		
		JLabel lblDescription = new JLabel("Description");
		GridBagConstraints gbc_lblDescription = new GridBagConstraints();
		gbc_lblDescription.anchor = GridBagConstraints.WEST;
		gbc_lblDescription.insets = new Insets(0, 0, 5, 5);
		gbc_lblDescription.gridx = 0;
		gbc_lblDescription.gridy = 3;
		contentPane.add(lblDescription, gbc_lblDescription);
		
		txtDescription = new JTextArea();
		txtDescription.setText(product.getDescription());
		txtDescription.setLineWrap(true);
		GridBagConstraints gbc_txtDescription = new GridBagConstraints();
		gbc_txtDescription.gridwidth = 2;
		gbc_txtDescription.insets = new Insets(0, 0, 5, 5);
		gbc_txtDescription.fill = GridBagConstraints.BOTH;
		gbc_txtDescription.gridx = 0;
		gbc_txtDescription.gridy = 4;
		contentPane.add(txtDescription, gbc_txtDescription);
		
		JLabel lblMin = new JLabel("Minimum Stock");
		GridBagConstraints gbc_lblMin = new GridBagConstraints();
		gbc_lblMin.anchor = GridBagConstraints.WEST;
		gbc_lblMin.insets = new Insets(0, 0, 5, 5);
		gbc_lblMin.gridx = 0;
		gbc_lblMin.gridy = 5;
		contentPane.add(lblMin, gbc_lblMin);
		
		txtMin = new JTextField();
		txtMin.setText(String.valueOf(product.getMinStock()));
		txtMin.setColumns(10);
		GridBagConstraints gbc_txtMin = new GridBagConstraints();
		gbc_txtMin.insets = new Insets(0, 0, 5, 5);
		gbc_txtMin.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtMin.gridx = 0;
		gbc_txtMin.gridy = 6;
		contentPane.add(txtMin, gbc_txtMin);
		
		JLabel lblMax = new JLabel("Maximum Stock");
		GridBagConstraints gbc_lblMax = new GridBagConstraints();
		gbc_lblMax.anchor = GridBagConstraints.WEST;
		gbc_lblMax.insets = new Insets(0, 0, 5, 0);
		gbc_lblMax.gridx = 1;
		gbc_lblMax.gridy = 5;
		contentPane.add(lblMax, gbc_lblMax);
		
		txtMax = new JTextField();
		txtMax.setText(String.valueOf(product.getMaxStock()));
		txtMax.setColumns(10);
		GridBagConstraints gbc_txtMax = new GridBagConstraints();
		gbc_txtMax.insets = new Insets(0, 0, 5, 0);
		gbc_txtMax.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtMax.gridx = 1;
		gbc_txtMax.gridy = 6;
		contentPane.add(txtMax, gbc_txtMax);
		
		JLabel lblSelling = new JLabel("Selling Price");
		GridBagConstraints gbc_lblSelling = new GridBagConstraints();
		gbc_lblSelling.anchor = GridBagConstraints.WEST;
		gbc_lblSelling.insets = new Insets(0, 0, 5, 5);
		gbc_lblSelling.gridx = 0;
		gbc_lblSelling.gridy = 7;
		contentPane.add(lblSelling, gbc_lblSelling);
		
		txtSelling = new JTextField(Objects.toString(product.getLatestSellingPrice(), ""));
		txtSelling.setColumns(10);
		GridBagConstraints gbc_txtSelling = new GridBagConstraints();
		gbc_txtSelling.insets = new Insets(0, 0, 5, 5);
		gbc_txtSelling.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtSelling.gridx = 0;
		gbc_txtSelling.gridy = 8;
		contentPane.add(txtSelling, gbc_txtSelling);
		
		JLabel lblLoaning = new JLabel("Loaning Price");
		GridBagConstraints gbc_lblLoaning = new GridBagConstraints();
		gbc_lblLoaning.anchor = GridBagConstraints.WEST;
		gbc_lblLoaning.insets = new Insets(0, 0, 5, 0);
		gbc_lblLoaning.gridx = 1;
		gbc_lblLoaning.gridy = 7;
		contentPane.add(lblLoaning, gbc_lblLoaning);
		
		
		txtLoaning = new JTextField(Objects.toString(product.getLatestLoaningPrice(), ""));
		txtLoaning.setColumns(10);
		GridBagConstraints gbc_txtLoaning = new GridBagConstraints();
		gbc_txtLoaning.insets = new Insets(0, 0, 5, 0);
		gbc_txtLoaning.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtLoaning.gridx = 1;
		gbc_txtLoaning.gridy = 8;
		contentPane.add(txtLoaning, gbc_txtLoaning);
		
		
		btnOk = new JButton("Update");
		GridBagConstraints gbc_btnOk = new GridBagConstraints();
		gbc_btnOk.anchor = GridBagConstraints.EAST;
		gbc_btnOk.gridx = 1;
		gbc_btnOk.gridy = 9;
		contentPane.add(btnOk, gbc_btnOk);
		
		switch (mode) {
			case VIEW:
				// Set title
				setTitle("View product - " + product.getName());
				// Hide 'Update' button if in view mode
				btnOk.setVisible(false);
				// Disable fields
				this.disableFields();
				break;
			case EDIT: 
				// Set title
				setTitle("Edit product");
				// Enable fields for editing
				this.enableFields();
				break;
		}
		
		
		
		addEventHandlers();
	}
	
	/*
	 * *******************************************************
	 * *******************  Methods *******************
	 * *******************************************************
	 */
	
	
	// Makes the text fields uneditable
	private void disableFields() {
		for (Component c : this.getContentPane().getComponents()) {
			   if (c instanceof JTextField || c instanceof JTextArea) {
				      c.setEnabled(false);
				   }
			}
	}
	
	
	// Makes the text fields editable except ID field
	private void enableFields() {
		for (Component c : this.getContentPane().getComponents()) {
			   if (c instanceof JTextField || c instanceof JTextArea) {
			      c.setEnabled(true);
			   }
			}
		txtId.setEnabled(false);
	}
	
	/*
	 * *******************************************************
	 * *******************  EVENT HANDLERS *******************
	 * *******************************************************
	 */
	private void addEventHandlers() {
		
		// 'update' button: Update the product
		btnOk.addActionListener(e -> {
			if (Messages.confirm(ProductUI.this, "Are you sure you want to update the changes to product?", "Update")) {
				
				// Validate name
				String name = txtName.getText().strip();
				if (name.isEmpty()) {
					Messages.error(this, "Product name cannot be empty!");
					return;
				}
				
				// Validate description
				String desc = txtDescription.getText().strip();
				if (desc.isEmpty()) {
					Messages.error(this, "Product description cannot be empty!");
					return;
				}
				
				// Validate Min Stock
				int minStock;
				try {
					minStock = Integer.parseInt(txtMin.getText());
				} catch (NumberFormatException e1) {
					Messages.error(this, "Minimum stock must be a positive, whole number!");
					return;
				}
				
				// Validate Max Stock
				int maxStock;
				try {
					maxStock = Integer.parseInt(txtMax.getText());
				} catch (NumberFormatException e1) {
					Messages.error(this, "Maximum stock must be a positive, whole numbers!");
					return;
				}
				
				// Validate buy/sell price
				String stringBuyPrice = txtSelling.getText().strip();
				BigDecimal buyPrice;
				if (stringBuyPrice.isEmpty()) {
					buyPrice = null;
				} else {
					try {
						buyPrice = new BigDecimal(stringBuyPrice);
					} catch (NumberFormatException e1) {
						Messages.error(this, "Sell price must be a positive decimal number, separated by dots");
						return;
					}
				}
				// if less than zero
				if (buyPrice.compareTo(BigDecimal.ZERO) == -1) {
					Messages.error(this, "Sell price must be a positive decimal number, separated by dots");
				}
				
				
				// Validate loan price
				String stringLoanPrice = txtSelling.getText().strip();
				BigDecimal loanPrice;
				if (stringLoanPrice.isEmpty()) {
					loanPrice = null;
				} else {
					try {
						loanPrice = new BigDecimal(stringLoanPrice);
					} catch (NumberFormatException e1) {
						Messages.error(this, "Loan price must be a positive decimal number, separated by dots");
						return;
					}
				}
				// if less than zero
				if (buyPrice.compareTo(BigDecimal.ZERO) == -1) {
					Messages.error(this, "Loan price must be a positive decimal number, separated by dots");
				}
				
				// Update data
				productCtrl.updateProductName(this.product, name);
				productCtrl.updateProductDescription(this.product, desc);
				productCtrl.updateProductMinStock(this.product, minStock);
				productCtrl.updateProductMinStock(this.product, maxStock);
				// create new (thus update) buy price if not same as current
				if (buyPrice == product.getLatestSellingPrice()) {
					productCtrl.createSellingPrice(buyPrice, product);
				}
				// create new (thus update) loan price if not same as current
				if (buyPrice == product.getLatestLoaningPrice()) {
					productCtrl.createLoaningPrice(loanPrice, product);
				}
				
			}
			// Dispose of the window
			this.dispose();
		});
	}
}
