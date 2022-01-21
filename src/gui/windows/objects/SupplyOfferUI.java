package gui.windows.objects;

import java.awt.Component;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.AuthenticationController;
import controller.SupplyController;
import gui.Messages;
import gui.windows.ChooseContractor;
import gui.windows.ChooseProduct;
import model.Contractor;
import model.PrimaryKey;
import model.Product;
import model.SupplyOffer;

import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import javax.swing.JTextField;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JTextArea;
import java.math.BigDecimal;

import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;

/**
 * @author Daniels Kanepe
 *
 */
public class SupplyOfferUI extends JDialog {
	
	public enum Mode {
		VIEW,
		EDIT,
		CREATE
	}

	private JPanel contentPane;
	private JTextField txtID;
	private JTextField txtPricePerItem;
	private JTextField txtMinQuantity;
	private JButton btnSubmit;
	
	SupplyOffer supplyOffer;
	SupplyController supplyCtrl;
	Mode mode;
	AuthenticationController auth;
	private JPanel productPanel;
	private JTextField txtProductDisplay;
	private JButton btnChooseProduct;
	private JPanel contractorPanel;
	private JTextField txtContractorDisplay;
	private JButton btnChooseContractor;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	
	private Contractor contractor;
	private Product product;


	/**
	 * Constructor for Creating a new supply offer
	 *
	 * @param auth the auth
	 */
	public SupplyOfferUI(AuthenticationController auth) {
		this(auth, null, Mode.CREATE);
		this.supplyOffer = null;
	}

	/**
	 * Constructor for view/edit supply offer
	 *
	 * @param auth the auth
	 * @param supplyOffer
	 * @param mode the mode (VIEW, EDIT)
	 * @wbp.parser.constructor
	 */
	public SupplyOfferUI(AuthenticationController auth, SupplyOffer supplyOffer, Mode mode) {
		this.mode = mode;
		this.auth = auth;
		
		supplyCtrl = new SupplyController();
		this.supplyOffer = supplyOffer;
		
		setModal(true);
		setBounds(100, 100, 450, 341);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[]{0, 0, 0};
		gbl_contentPanel.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0};
		gbl_contentPanel.columnWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
		gbl_contentPanel.rowWeights = new double[]{1.0, 0.0, 1.0, 1.0, 1.0, 1.0, 0.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPanel);
		
		JLabel lblID = new JLabel("ID");
		GridBagConstraints gbc_lblID = new GridBagConstraints();
		gbc_lblID.anchor = GridBagConstraints.SOUTH;
		gbc_lblID.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblID.insets = new Insets(0, 0, 5, 5);
		gbc_lblID.gridx = 0;
		gbc_lblID.gridy = 0;
		contentPane.add(lblID, gbc_lblID);
		
		
		JLabel lblProduct = new JLabel("Product *");
		GridBagConstraints gbc_lblProduct = new GridBagConstraints();
		gbc_lblProduct.anchor = GridBagConstraints.SOUTH;
		gbc_lblProduct.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblProduct.insets = new Insets(0, 0, 5, 0);
		gbc_lblProduct.gridx = 1;
		gbc_lblProduct.gridy = 0;
		contentPane.add(lblProduct, gbc_lblProduct);
		
		
		txtID = new JTextField();
		txtID.setEnabled(false);
		GridBagConstraints gbc_txtID = new GridBagConstraints();
		gbc_txtID.insets = new Insets(0, 0, 5, 5);
		gbc_txtID.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtID.gridx = 0;
		gbc_txtID.gridy = 1;
		contentPane.add(txtID, gbc_txtID);
		txtID.setColumns(10);
		
		productPanel = new JPanel();
		GridBagConstraints gbc_productPanel = new GridBagConstraints();
		gbc_productPanel.anchor = GridBagConstraints.NORTH;
		gbc_productPanel.insets = new Insets(0, 0, 5, 0);
		gbc_productPanel.fill = GridBagConstraints.HORIZONTAL;
		gbc_productPanel.gridx = 1;
		gbc_productPanel.gridy = 1;
		contentPane.add(productPanel, gbc_productPanel);
		GridBagLayout gbl_productPanel = new GridBagLayout();
		gbl_productPanel.columnWidths = new int[]{0, 0, 0};
		gbl_productPanel.rowHeights = new int[]{0, 0};
		gbl_productPanel.columnWeights = new double[]{1.0, 0.0, Double.MIN_VALUE};
		gbl_productPanel.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		productPanel.setLayout(gbl_productPanel);
		
		txtProductDisplay = new JTextField();
		txtProductDisplay.setEnabled(false);
		txtProductDisplay.setColumns(10);
		GridBagConstraints gbc_txtProductDisplay = new GridBagConstraints();
		gbc_txtProductDisplay.fill = GridBagConstraints.BOTH;
		gbc_txtProductDisplay.insets = new Insets(0, 0, 0, 5);
		gbc_txtProductDisplay.gridx = 0;
		gbc_txtProductDisplay.gridy = 0;
		productPanel.add(txtProductDisplay, gbc_txtProductDisplay);
		
		btnChooseProduct = new JButton("Choose...");
		GridBagConstraints gbc_btnChooseProduct = new GridBagConstraints();
		gbc_btnChooseProduct.fill = GridBagConstraints.BOTH;
		gbc_btnChooseProduct.gridx = 1;
		gbc_btnChooseProduct.gridy = 0;
		productPanel.add(btnChooseProduct, gbc_btnChooseProduct);
		
		
		JLabel lblPricePerItem = new JLabel("Price per item *");
		GridBagConstraints gbc_lblPricePerItem = new GridBagConstraints();
		gbc_lblPricePerItem.anchor = GridBagConstraints.SOUTH;
		gbc_lblPricePerItem.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblPricePerItem.insets = new Insets(0, 0, 5, 5);
		gbc_lblPricePerItem.gridx = 0;
		gbc_lblPricePerItem.gridy = 2;
		contentPane.add(lblPricePerItem, gbc_lblPricePerItem);
		
		
		JLabel lblAddress = new JLabel("Min order quantity");
		GridBagConstraints gbc_lblAddress = new GridBagConstraints();
		gbc_lblAddress.anchor = GridBagConstraints.SOUTH;
		gbc_lblAddress.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblAddress.insets = new Insets(0, 0, 5, 0);
		gbc_lblAddress.gridx = 1;
		gbc_lblAddress.gridy = 2;
		contentPane.add(lblAddress, gbc_lblAddress);
		
		
		txtPricePerItem = new JTextField();
		GridBagConstraints gbc_txtPricePerItem = new GridBagConstraints();
		gbc_txtPricePerItem.insets = new Insets(0, 0, 5, 5);
		gbc_txtPricePerItem.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtPricePerItem.gridx = 0;
		gbc_txtPricePerItem.gridy = 3;
		contentPane.add(txtPricePerItem, gbc_txtPricePerItem);
		txtPricePerItem.setColumns(10);
		
		
		txtMinQuantity = new JTextField();
		GridBagConstraints gbc_txtMinQuantity = new GridBagConstraints();
		gbc_txtMinQuantity.insets = new Insets(0, 0, 5, 0);
		gbc_txtMinQuantity.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtMinQuantity.gridx = 1;
		gbc_txtMinQuantity.gridy = 3;
		contentPane.add(txtMinQuantity, gbc_txtMinQuantity);
		txtMinQuantity.setColumns(10);
		
		
		JLabel lblContractor = new JLabel("Contractor *");
		GridBagConstraints gbc_lblContractor = new GridBagConstraints();
		gbc_lblContractor.anchor = GridBagConstraints.SOUTH;
		gbc_lblContractor.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblContractor.insets = new Insets(0, 0, 5, 5);
		gbc_lblContractor.gridx = 0;
		gbc_lblContractor.gridy = 4;
		contentPane.add(lblContractor, gbc_lblContractor);
		
		contractorPanel = new JPanel();
		GridBagConstraints gbc_contractorPanel = new GridBagConstraints();
		gbc_contractorPanel.insets = new Insets(0, 0, 5, 5);
		gbc_contractorPanel.fill = GridBagConstraints.BOTH;
		gbc_contractorPanel.gridx = 0;
		gbc_contractorPanel.gridy = 5;
		contentPane.add(contractorPanel, gbc_contractorPanel);
		GridBagLayout gbl_contractorPanel = new GridBagLayout();
		gbl_contractorPanel.columnWidths = new int[]{0, 0, 0};
		gbl_contractorPanel.rowHeights = new int[]{0, 0};
		gbl_contractorPanel.columnWeights = new double[]{1.0, 0.0, Double.MIN_VALUE};
		gbl_contractorPanel.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		contractorPanel.setLayout(gbl_contractorPanel);
		
		txtContractorDisplay = new JTextField();
		txtContractorDisplay.setEnabled(false);
		txtContractorDisplay.setColumns(10);
		GridBagConstraints gbc_txtContractorDisplay = new GridBagConstraints();
		gbc_txtContractorDisplay.fill = GridBagConstraints.BOTH;
		gbc_txtContractorDisplay.insets = new Insets(0, 0, 0, 5);
		gbc_txtContractorDisplay.gridx = 0;
		gbc_txtContractorDisplay.gridy = 0;
		contractorPanel.add(txtContractorDisplay, gbc_txtContractorDisplay);
		
		btnChooseContractor = new JButton("Choose...");
		GridBagConstraints gbc_btnChooseContractor = new GridBagConstraints();
		gbc_btnChooseContractor.fill = GridBagConstraints.BOTH;
		gbc_btnChooseContractor.gridx = 1;
		gbc_btnChooseContractor.gridy = 0;
		contractorPanel.add(btnChooseContractor, gbc_btnChooseContractor);
		
		
		btnSubmit = new JButton("Update");
		GridBagConstraints gbc_btnOk = new GridBagConstraints();
		gbc_btnOk.anchor = GridBagConstraints.NORTHEAST;
		gbc_btnOk.gridx = 1;
		gbc_btnOk.gridy = 6;
		contentPane.add(btnSubmit, gbc_btnOk);
		
		switch (mode) {
			case VIEW:
				// Set title
				setTitle("Viewing a supply offer");
				// Hide 'Update' button if in view mode
				btnSubmit.setVisible(false);
				// Disable 'choose' buttons if in view mode.
				btnChooseContractor.setEnabled(false);
				btnChooseProduct.setEnabled(false);
				// Disable fields
				this.disableFields();
				// Fill fields with content
				this.fillFields(supplyOffer);
				break;
			case EDIT: 
				// Set title
				setTitle("Edit Supply Offer");
				// Enable fields for editing
				this.enableFields();
				// Fill fields with content
				this.fillFields(supplyOffer);
				break;
			case CREATE:
				// Set title
				setTitle("Add New Supply Offer");
				// Change submit button text to 'Create'
				btnSubmit.setText("Create");
				// Enable fields
				this.enableFields();
				// Peek ID
				txtID.setText(String.valueOf(PrimaryKey.peekID(PrimaryKey.Keys.SUPPLY_OFFER)));
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
			   if (c instanceof JTextField || c instanceof JTextArea || c instanceof JPanel) {
				      c.setEnabled(false);
				   }
			}
	}
	
	
	// Makes the text fields editable except ID & 'choose' fields
	private void enableFields() {
		for (Component c : this.getContentPane().getComponents()) {
			   if (c instanceof JTextField || c instanceof JTextArea || c instanceof JPanel) {
			      c.setEnabled(true);
			   }
			}
		txtID.setEnabled(false);
	}
	
	// FIll in the fields
	private void fillFields(SupplyOffer supplyOffer) {
		txtID.setText(String.valueOf(supplyOffer.getID()));
		txtPricePerItem.setText(String.valueOf(supplyOffer.getPricePerItem()));
		txtMinQuantity.setText(String.valueOf(supplyOffer.getMinQuantity()));
		txtContractorDisplay.setText(supplyOffer.getContractor().getCompanyName());
		this.contractor = supplyOffer.getContractor();
		this.product = supplyOffer.getProduct();
		txtProductDisplay.setText(supplyOffer.getProduct().getName());
		product = supplyOffer.getProduct();
	}
	
	/**
	 * Gets the supply offer used in view/edit, or one created in 'create' mode. Can be null!
	 *
	 * @return the SupplyOffer
	 */
	public SupplyOffer getSupplyOffer() {
		return this.supplyOffer;
	}
 	
		/*
	 * *******************************************************
	 * *******************  EVENT HANDLERS *******************
	 * *******************************************************
	 */
	private void addEventHandlers() {
		
		// 'update' button: Update the product
		btnSubmit.addActionListener(e -> {
			String message = "";
			if (mode == Mode.EDIT) {
				message = "A new supply offer with these details will be created"
						+ " and the old one deactivated. Are you sure you want to do that?";
			} else if (mode == Mode.CREATE) {
				message = "Create supply offer?";
			}
			if (Messages.confirm(SupplyOfferUI.this, message)) {
				
				// Validate product
				if (this.product == null) {
					Messages.error(this, "You must choose a product!");
					return;
				}
	
				// Validate contractor
				if (this.contractor == null) {
					Messages.error(this, "You must choose a contractor!");
					return;
				}
				
				// Validate price per item
				String stringPricePerItem = txtPricePerItem.getText().strip();
				BigDecimal pricePerItem;
				if (stringPricePerItem.isEmpty()) {
					pricePerItem = null;
				} else {
					try {
						pricePerItem = new BigDecimal(stringPricePerItem);
					} catch (NumberFormatException e1) {
						Messages.error(this, "Price per item must be a positive decimal number,"
								+ " separated by dots");
						return;
					}
					// if less than zero
					if (pricePerItem.compareTo(BigDecimal.ZERO) == -1) {
						Messages.error(this, "Price per item must be a positive decimal number,"
								+ " separated by dots");
					}
				}
				
				// Validate min order quantity
				String stringMinQuantity = txtMinQuantity.getText().strip();
				int minQuantity;
				try {
					minQuantity = Integer.parseInt(stringMinQuantity);
				} catch (NumberFormatException e2) {
					Messages.error(this, "Min order quanity must be a whole, positive number");
					return;
				}
				// if less than zero
				if (minQuantity < 0) {
					Messages.error(this, "Min order quanity must be a whole, positive number");
				}
	
				
				// if mode == view, update data
				if (mode == Mode.EDIT) {
					supplyCtrl.updateSupplyOfferProduct(supplyOffer, product);
					supplyCtrl.updateSupplyOfferPricePerItem(supplyOffer, pricePerItem);
					supplyCtrl.updateSupplyOfferMinQuantity(supplyOffer, minQuantity);
					supplyCtrl.updateSupplyOfferContractor(supplyOffer, contractor);
				} else if (mode == Mode.CREATE) {
					// if mode == Create, create a new supply offer
					this.supplyOffer = supplyCtrl.createSupplyOffer(product, contractor, pricePerItem, minQuantity);
				}
			}
			// Dispose of the window
			this.dispose();
		});
		
		btnChooseProduct.addActionListener(e -> {
			ChooseProduct frame = new ChooseProduct(auth);
			frame.setVisible(true);
			if (frame.getSelectedProduct() != null) {
				this.product = frame.getSelectedProduct();
				txtProductDisplay.setText(product.getName());
			}
		});
		
		btnChooseContractor.addActionListener(e -> {
			ChooseContractor frame = new ChooseContractor(auth);
			frame.setVisible(true);
			if (frame.getSelectedContractor() != null) {
				this.contractor = frame.getSelectedContractor();
				txtContractorDisplay.setText(contractor.getCompanyName());
			}
		});
	}
}
