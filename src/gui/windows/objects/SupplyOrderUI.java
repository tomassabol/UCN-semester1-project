package gui.windows.objects;

import java.awt.Component;

import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.NumberFormatter;

import controller.AuthenticationController;
import controller.SupplyController;
import gui.Messages;
import gui.windows.ChooseSupplyOffer;
import model.PrimaryKey;
import model.Product;
import model.SupplyOffer;
import model.SupplyOrder;

import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import javax.swing.JTextField;
import java.awt.Insets;
import java.text.NumberFormat;

import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

public class SupplyOrderUI extends JDialog {
	
	public enum Mode {
		VIEW,
		CREATE
	}

	private JPanel contentPane;
	private JTextField txtID;
	private JTextField txtProduct;
	private JTextField txtSupplyOffer;
	private JFormattedTextField txtQuantity;
	private JButton btnSubmit;
	
	SupplyController supplyCtrl;
	Mode mode;
	AuthenticationController auth;
	SupplyOrder supplyOrder;
	SupplyOffer supplyOffer;
	int quantity;
	private JButton btnChooseSupplyOffer;
	Product product;
	private JLabel lblContractor;
	private JTextField txtContractor;


	/**
	 * Constructor for Create Supply Order
	 *
	 * @param auth the auth
	 */
	public SupplyOrderUI(AuthenticationController auth, Product product) {
		this(auth, null, product, Mode.CREATE);
		this.supplyOrder = null;
		this.product = product;
		this.supplyOffer = null;
	}

	/**
	 * Constructor for view/edit
	 *
	 * @param auth the auth
	 * @param supplyOrder the Supply Order
	 * @param product the product
	 * @param mode the mode
	 */
	public SupplyOrderUI(AuthenticationController auth, SupplyOrder supplyOrder, Product product, Mode mode) {
		this.mode = mode;
		this.auth = auth;
		this.product = product;
		
		supplyCtrl = new SupplyController();
		this.supplyOrder = supplyOrder;
		this.supplyOffer = supplyOrder!= null ? supplyOrder.getSupplyOffer() : null;
		
		setModal(true);
		setBounds(100, 100, 450, 341);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[]{0, 0, 0};
		gbl_contentPanel.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_contentPanel.columnWeights = new double[]{1.0, 0.0, Double.MIN_VALUE};
		gbl_contentPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPanel);
		
		JLabel lblID = new JLabel("ID");
		GridBagConstraints gbc_lblID = new GridBagConstraints();
		gbc_lblID.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblID.insets = new Insets(0, 0, 5, 5);
		gbc_lblID.gridx = 0;
		gbc_lblID.gridy = 0;
		contentPane.add(lblID, gbc_lblID);
		
		
		txtID = new JTextField();
		txtID.setEnabled(false);
		GridBagConstraints gbc_txtID = new GridBagConstraints();
		gbc_txtID.insets = new Insets(0, 0, 5, 5);
		gbc_txtID.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtID.gridx = 0;
		gbc_txtID.gridy = 1;
		contentPane.add(txtID, gbc_txtID);
		txtID.setColumns(10);
		
		
		JLabel lblSupplyOffer = new JLabel("Supply Offer");
		GridBagConstraints gbc_lblSupplyOffer = new GridBagConstraints();
		gbc_lblSupplyOffer.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblSupplyOffer.insets = new Insets(0, 0, 5, 5);
		gbc_lblSupplyOffer.gridx = 0;
		gbc_lblSupplyOffer.gridy = 2;
		contentPane.add(lblSupplyOffer, gbc_lblSupplyOffer);
		
		
		txtSupplyOffer = new JTextField();
		txtSupplyOffer.setEditable(false);
		GridBagConstraints gbc_txtSupplyOffer = new GridBagConstraints();
		gbc_txtSupplyOffer.insets = new Insets(0, 0, 5, 5);
		gbc_txtSupplyOffer.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtSupplyOffer.gridx = 0;
		gbc_txtSupplyOffer.gridy = 3;
		contentPane.add(txtSupplyOffer, gbc_txtSupplyOffer);
		txtSupplyOffer.setColumns(10);
		
		btnChooseSupplyOffer = new JButton("Choose");
		GridBagConstraints gbc_btnChooseSupplyOffer = new GridBagConstraints();
		gbc_btnChooseSupplyOffer.insets = new Insets(0, 0, 5, 0);
		gbc_btnChooseSupplyOffer.gridx = 1;
		gbc_btnChooseSupplyOffer.gridy = 3;
		contentPane.add(btnChooseSupplyOffer, gbc_btnChooseSupplyOffer);
		
		lblContractor = new JLabel("Contractor");
		GridBagConstraints gbc_lblContractor = new GridBagConstraints();
		gbc_lblContractor.anchor = GridBagConstraints.WEST;
		gbc_lblContractor.insets = new Insets(0, 0, 5, 5);
		gbc_lblContractor.gridx = 0;
		gbc_lblContractor.gridy = 4;
		contentPane.add(lblContractor, gbc_lblContractor);
		
		txtContractor = new JTextField();
		txtContractor.setEditable(false);
		txtContractor.setColumns(10);
		GridBagConstraints gbc_txtContractor = new GridBagConstraints();
		gbc_txtContractor.insets = new Insets(0, 0, 5, 5);
		gbc_txtContractor.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtContractor.gridx = 0;
		gbc_txtContractor.gridy = 5;
		contentPane.add(txtContractor, gbc_txtContractor);
		
		
		JLabel lblProduct = new JLabel("Product");
		GridBagConstraints gbc_lblProduct = new GridBagConstraints();
		gbc_lblProduct.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblProduct.insets = new Insets(0, 0, 5, 5);
		gbc_lblProduct.gridx = 0;
		gbc_lblProduct.gridy = 6;
		contentPane.add(lblProduct, gbc_lblProduct);
		
		
		txtProduct = new JTextField();
		txtProduct.setEditable(false);
		GridBagConstraints gbc_txtProduct = new GridBagConstraints();
		gbc_txtProduct.insets = new Insets(0, 0, 5, 5);
		gbc_txtProduct.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtProduct.gridx = 0;
		gbc_txtProduct.gridy = 7;
		contentPane.add(txtProduct, gbc_txtProduct);
		txtProduct.setColumns(10);
		
		
		JLabel lblQuantity = new JLabel("Quanity");
		GridBagConstraints gbc_lblQuantity = new GridBagConstraints();
		gbc_lblQuantity.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblQuantity.insets = new Insets(0, 0, 5, 5);
		gbc_lblQuantity.gridx = 0;
		gbc_lblQuantity.gridy = 8;
		contentPane.add(lblQuantity, gbc_lblQuantity);
		
		// restrict txtDiscount input field to only numbers
		NumberFormat format = NumberFormat.getInstance();
		NumberFormatter formatter = new NumberFormatter(format);
		formatter.setValueClass(Integer.class);
		formatter.setMinimum(0);
		formatter.setMaximum(Integer.MAX_VALUE);
		formatter.setAllowsInvalid(false);
		//
		txtQuantity = new JFormattedTextField(formatter);
		GridBagConstraints gbc_txtQuantity = new GridBagConstraints();
		gbc_txtQuantity.insets = new Insets(0, 0, 5, 5);
		gbc_txtQuantity.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtQuantity.gridx = 0;
		gbc_txtQuantity.gridy = 9;
		contentPane.add(txtQuantity, gbc_txtQuantity);
		txtQuantity.setColumns(10);
		
		
		btnSubmit = new JButton("Update");
		btnSubmit.setVerticalAlignment(SwingConstants.BOTTOM);
		GridBagConstraints gbc_btnOk = new GridBagConstraints();
		gbc_btnOk.anchor = GridBagConstraints.SOUTHEAST;
		gbc_btnOk.gridx = 1;
		gbc_btnOk.gridy = 10;
		contentPane.add(btnSubmit, gbc_btnOk);
		
		switch (mode) {
			case VIEW:
				// Set title
				setTitle("View Supply Order - " + supplyOrder.getID());
				// Hide 'Update' button if in view mode
				btnSubmit.setVisible(false);
				// Disable 'choose' button if in view mode.
				btnChooseSupplyOffer.setEnabled(false);
				// Disable fields
				this.disableFields();
				// Fill fields with content
				this.fillFields(supplyOrder);
				break;
			case CREATE:
				// Set title
				setTitle("Add New Supply Order");
				txtProduct.setText(product.getName());
				// Change submit button text to 'Create'
				btnSubmit.setText("Create");
				// Enable fields
				this.enableFields();
				// Peek ID
				txtID.setText(String.valueOf(PrimaryKey.peekID(PrimaryKey.Keys.SUPPLY_ORDER)));
				
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
	
	
	// Makes the text fields editable except ID & Type field
	private void enableFields() {
		for (Component c : this.getContentPane().getComponents()) {
			   if (c instanceof JTextField || c instanceof JTextArea) {
			      c.setEnabled(true);
			   }
			}
		txtID.setEnabled(false);
		txtProduct.setEnabled(false);
		txtSupplyOffer.setEnabled(false);
	}
	
	// FIll in the fields
	private void fillFields(SupplyOrder supplyOrder) {
		txtID.setText(String.valueOf(supplyOrder.getID()));
		txtProduct.setText(product.getName());
		txtSupplyOffer.setText("ID: " + String.valueOf(supplyOrder.getSupplyOffer().getID()));
		txtContractor.setText(supplyOrder.getSupplyOffer().getContractor().getCompanyName());
		txtQuantity.setText(String.valueOf(supplyOrder.getQuantity()));
	}
	
	/**
	 * Gets the Supply Order used in view/edit, or one created in 'create' mode. Can be null!
	 *
	 * @return the supply order
	 */
	public SupplyOrder getSupplyOrder() {
		return this.supplyOrder;
	}
 	
		/*
	 * *******************************************************
	 * *******************  EVENT HANDLERS *******************
	 * *******************************************************
	 */
	private void addEventHandlers() {
		
		// 'update' button: Update the supply order
		btnSubmit.addActionListener(e -> {
			String message = "";
			if (mode == Mode.CREATE) {
				message = "Create Supply Order?";
			} 
			if (Messages.confirm(SupplyOrderUI.this, message)) {
				
				// Validate Supply offer
				String supplyOfferString = txtSupplyOffer.getText().strip();
				if (supplyOfferString.isEmpty()) {
					Messages.error(this, "Supply Offer field cannot be empty!");
					return;
				}

				// Validate quantity
				String quantityString = txtQuantity.getText().strip();
				if (quantityString.isEmpty()) {
					Messages.error(this, "Quantity field cannot be empty!");
					return;
				}
				
				// if mode == create, create new supply order
				if (mode == Mode.CREATE) {
					quantity = Integer.valueOf(txtQuantity.getText().strip());
					this.supplyOrder = supplyCtrl.createSupplyOrder(supplyOffer, quantity);
				} 
			}
			// Dispose of the window
			this.dispose();
		});

		// 'add supply order' button
		btnChooseSupplyOffer.addActionListener(e -> {
			ChooseSupplyOffer frame = new ChooseSupplyOffer(auth, product);
			frame.setVisible(true);
			if (frame.getSelectedSupplyOffer() != null) {
				this.supplyOffer = frame.getSelectedSupplyOffer();
				txtSupplyOffer.setText(String.valueOf(supplyOffer.getID()));
				txtContractor.setText(supplyOffer.getContractor().getCompanyName());
			}
		});
	}
}

