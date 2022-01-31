package gui.windows.model;

import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import controller.AuthenticationController;
import controller.SupplyController;
import exception.IllegalModificationException;
import gui.Common;
import gui.JButtonPrimary;
import gui.Messages;
import gui.windows.ChooseContractor;
import gui.windows.ChooseProduct;
import gui.windows.ChooseSupplyOffers;
import models.Contractor;
import models.PrimaryKey;
import models.Product;
import models.SupplyOffer;
import models.SupplyOrder;

import javax.swing.JRadioButton;
import javax.swing.Box;
import javax.swing.JSeparator;

/**
 * @author Daniels Kanepe
 *
 */
public class SupplyOrderUI extends JDialog {
	
	public enum Mode {
		VIEW,
		EDIT,
		CREATE
	}

	private JPanel contentPane;
	private JTextField txtID;
	private JTextField txtPricePerItem;
	private JTextField txtQuantity;
	private JButton btnSubmit;
	
	SupplyOrder supplyOrder;
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
	private JLabel lblDate;
	private JLabel lblStocked;
	private JPanel stockedPanel;
	private JRadioButton rdbtnYes;
	private JRadioButton rdbtnNo;
	private JPanel orderDatePanel;
	private JTextField txtOrderDate;
	private JButton btnAutoFillOrderDate;
	private JLabel lblAutoFillData;
	private JButton btnFillWithSupplyOffer;


	/**
	 * Constructor for Creating a new supply order
	 *
	 * @param auth the auth
	 */
	public SupplyOrderUI(AuthenticationController auth) {
		this(auth, null, Mode.CREATE);
		this.supplyOrder = null;
	}

	/**
	 * Constructor for view/edit supply order
	 *
	 * @param auth the auth
	 * @param supplyOrder
	 * @param mode the mode (VIEW, EDIT)
	 * @wbp.parser.constructor
	 */
	public SupplyOrderUI(AuthenticationController auth, SupplyOrder supplyOrder, Mode mode) {
		this.mode = mode;
		this.auth = auth;
		
		supplyCtrl = new SupplyController();
		this.supplyOrder = supplyOrder;
		
		setModal(true);
		setBounds(100, 100, 600, 450);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[]{0, 0, 0};
		gbl_contentPanel.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_contentPanel.columnWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
		gbl_contentPanel.rowWeights = new double[]{1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPanel);
		
		lblAutoFillData = new JLabel("Fill with Supply Offer");
		GridBagConstraints gbc_lblAutoFillData = new GridBagConstraints();
		gbc_lblAutoFillData.anchor = GridBagConstraints.EAST;
		gbc_lblAutoFillData.insets = new Insets(0, 0, 5, 5);
		gbc_lblAutoFillData.gridx = 0;
		gbc_lblAutoFillData.gridy = 0;
		contentPane.add(lblAutoFillData, gbc_lblAutoFillData);
		
		btnFillWithSupplyOffer = new JButton("Choose...");
		GridBagConstraints gbc_btnFillWithSupplyOffer = new GridBagConstraints();
		gbc_btnFillWithSupplyOffer.anchor = GridBagConstraints.WEST;
		gbc_btnFillWithSupplyOffer.insets = new Insets(0, 0, 5, 0);
		gbc_btnFillWithSupplyOffer.gridx = 1;
		gbc_btnFillWithSupplyOffer.gridy = 0;
		contentPane.add(btnFillWithSupplyOffer, gbc_btnFillWithSupplyOffer);
		
		JLabel lblID = new JLabel("ID");
		GridBagConstraints gbc_lblID = new GridBagConstraints();
		gbc_lblID.anchor = GridBagConstraints.SOUTH;
		gbc_lblID.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblID.insets = new Insets(0, 0, 5, 5);
		gbc_lblID.gridx = 0;
		gbc_lblID.gridy = 2;
		contentPane.add(lblID, gbc_lblID);
		
		
		JLabel lblProduct = new JLabel("Product *");
		GridBagConstraints gbc_lblProduct = new GridBagConstraints();
		gbc_lblProduct.anchor = GridBagConstraints.SOUTH;
		gbc_lblProduct.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblProduct.insets = new Insets(0, 0, 5, 0);
		gbc_lblProduct.gridx = 1;
		gbc_lblProduct.gridy = 2;
		contentPane.add(lblProduct, gbc_lblProduct);
		
		
		txtID = new JTextField();
		txtID.setEnabled(false);
		GridBagConstraints gbc_txtID = new GridBagConstraints();
		gbc_txtID.insets = new Insets(0, 0, 5, 5);
		gbc_txtID.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtID.gridx = 0;
		gbc_txtID.gridy = 3;
		contentPane.add(txtID, gbc_txtID);
		txtID.setColumns(10);
		
		productPanel = new JPanel();
		GridBagConstraints gbc_productPanel = new GridBagConstraints();
		gbc_productPanel.anchor = GridBagConstraints.NORTH;
		gbc_productPanel.insets = new Insets(0, 0, 5, 0);
		gbc_productPanel.fill = GridBagConstraints.HORIZONTAL;
		gbc_productPanel.gridx = 1;
		gbc_productPanel.gridy = 3;
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
		gbc_lblPricePerItem.gridy = 4;
		contentPane.add(lblPricePerItem, gbc_lblPricePerItem);
		
		
		JLabel lblQuantity = new JLabel("Quantity *");
		GridBagConstraints gbc_lblQuantity = new GridBagConstraints();
		gbc_lblQuantity.anchor = GridBagConstraints.SOUTH;
		gbc_lblQuantity.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblQuantity.insets = new Insets(0, 0, 5, 0);
		gbc_lblQuantity.gridx = 1;
		gbc_lblQuantity.gridy = 4;
		contentPane.add(lblQuantity, gbc_lblQuantity);
		
		
		txtPricePerItem = new JTextField();
		GridBagConstraints gbc_txtPricePerItem = new GridBagConstraints();
		gbc_txtPricePerItem.insets = new Insets(0, 0, 5, 5);
		gbc_txtPricePerItem.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtPricePerItem.gridx = 0;
		gbc_txtPricePerItem.gridy = 5;
		contentPane.add(txtPricePerItem, gbc_txtPricePerItem);
		txtPricePerItem.setColumns(10);
		
		
		txtQuantity = new JTextField();
		GridBagConstraints gbc_txtQuantity = new GridBagConstraints();
		gbc_txtQuantity.insets = new Insets(0, 0, 5, 0);
		gbc_txtQuantity.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtQuantity.gridx = 1;
		gbc_txtQuantity.gridy = 5;
		contentPane.add(txtQuantity, gbc_txtQuantity);
		txtQuantity.setColumns(10);
		
		
		JLabel lblContractor = new JLabel("Contractor *");
		GridBagConstraints gbc_lblContractor = new GridBagConstraints();
		gbc_lblContractor.anchor = GridBagConstraints.SOUTH;
		gbc_lblContractor.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblContractor.insets = new Insets(0, 0, 5, 5);
		gbc_lblContractor.gridx = 0;
		gbc_lblContractor.gridy = 6;
		contentPane.add(lblContractor, gbc_lblContractor);
		
		lblStocked = new JLabel("Has been stocked?");
		GridBagConstraints gbc_lblStocked = new GridBagConstraints();
		gbc_lblStocked.anchor = GridBagConstraints.SOUTHWEST;
		gbc_lblStocked.insets = new Insets(0, 0, 5, 0);
		gbc_lblStocked.gridx = 1;
		gbc_lblStocked.gridy = 6;
		contentPane.add(lblStocked, gbc_lblStocked);
		
		contractorPanel = new JPanel();
		GridBagConstraints gbc_contractorPanel = new GridBagConstraints();
		gbc_contractorPanel.insets = new Insets(0, 0, 5, 5);
		gbc_contractorPanel.fill = GridBagConstraints.BOTH;
		gbc_contractorPanel.gridx = 0;
		gbc_contractorPanel.gridy = 7;
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
		
		stockedPanel = new JPanel();
		GridBagConstraints gbc_stockedPanel = new GridBagConstraints();
		gbc_stockedPanel.insets = new Insets(0, 0, 5, 0);
		gbc_stockedPanel.anchor = GridBagConstraints.NORTH;
		gbc_stockedPanel.fill = GridBagConstraints.HORIZONTAL;
		gbc_stockedPanel.gridx = 1;
		gbc_stockedPanel.gridy = 7;
		contentPane.add(stockedPanel, gbc_stockedPanel);
		GridBagLayout gbl_stockedPanel = new GridBagLayout();
		gbl_stockedPanel.columnWidths = new int[]{0, 0, 0};
		gbl_stockedPanel.rowHeights = new int[]{0, 0};
		gbl_stockedPanel.columnWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		gbl_stockedPanel.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		stockedPanel.setLayout(gbl_stockedPanel);
		
		rdbtnYes = new JRadioButton("Yes");
		buttonGroup.add(rdbtnYes);
		GridBagConstraints gbc_rdbtnYes = new GridBagConstraints();
		gbc_rdbtnYes.insets = new Insets(0, 0, 0, 5);
		gbc_rdbtnYes.gridx = 0;
		gbc_rdbtnYes.gridy = 0;
		stockedPanel.add(rdbtnYes, gbc_rdbtnYes);
		
		rdbtnNo = new JRadioButton("No");
		buttonGroup.add(rdbtnNo);
		GridBagConstraints gbc_rdbtnNo = new GridBagConstraints();
		gbc_rdbtnNo.gridx = 1;
		gbc_rdbtnNo.gridy = 0;
		stockedPanel.add(rdbtnNo, gbc_rdbtnNo);
		
		// Do not allow changing 'stocked' as it is done with a different UI
		rdbtnYes.setEnabled(false);
		rdbtnNo.setEnabled(false);
		
		lblDate = new JLabel("Order Date (" + Common.getDateTimeFormat() + ") *");
		GridBagConstraints gbc_lblDate = new GridBagConstraints();
		gbc_lblDate.gridwidth = 2;
		gbc_lblDate.anchor = GridBagConstraints.SOUTHWEST;
		gbc_lblDate.insets = new Insets(0, 0, 5, 0);
		gbc_lblDate.gridx = 0;
		gbc_lblDate.gridy = 8;
		contentPane.add(lblDate, gbc_lblDate);
		
		orderDatePanel = new JPanel();
		GridBagConstraints gbc_orderDatePanel = new GridBagConstraints();
		gbc_orderDatePanel.gridwidth = 2;
		gbc_orderDatePanel.insets = new Insets(0, 0, 5, 0);
		gbc_orderDatePanel.fill = GridBagConstraints.BOTH;
		gbc_orderDatePanel.gridx = 0;
		gbc_orderDatePanel.gridy = 9;
		contentPane.add(orderDatePanel, gbc_orderDatePanel);
		GridBagLayout gbl_orderDatePanel = new GridBagLayout();
		gbl_orderDatePanel.columnWidths = new int[]{0, 0, 0};
		gbl_orderDatePanel.rowHeights = new int[]{0, 0};
		gbl_orderDatePanel.columnWeights = new double[]{1.0, 0.0, Double.MIN_VALUE};
		gbl_orderDatePanel.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		orderDatePanel.setLayout(gbl_orderDatePanel);
		
		txtOrderDate = new JTextField();
		GridBagConstraints gbc_txtOrderDate = new GridBagConstraints();
		gbc_txtOrderDate.insets = new Insets(0, 0, 0, 5);
		gbc_txtOrderDate.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtOrderDate.gridx = 0;
		gbc_txtOrderDate.gridy = 0;
		orderDatePanel.add(txtOrderDate, gbc_txtOrderDate);
		txtOrderDate.setColumns(10);
		
		btnAutoFillOrderDate = new JButton("Auto fill");
		GridBagConstraints gbc_btnAutoFillOrderDate = new GridBagConstraints();
		gbc_btnAutoFillOrderDate.gridx = 1;
		gbc_btnAutoFillOrderDate.gridy = 0;
		orderDatePanel.add(btnAutoFillOrderDate, gbc_btnAutoFillOrderDate);
		
		
		btnSubmit = new JButtonPrimary("Submit");
		GridBagConstraints gbc_btnOk = new GridBagConstraints();
		gbc_btnOk.anchor = GridBagConstraints.SOUTHEAST;
		gbc_btnOk.gridx = 1;
		gbc_btnOk.gridy = 10;
		contentPane.add(btnSubmit, gbc_btnOk);
		
		switch (mode) {
			case VIEW:
				// Set title
				setTitle("Viewing a Supply Order");
				// Hide submit button if in view mode
				btnSubmit.setVisible(false);
				// Disable 'choose' buttons if in view mode.
				btnChooseContractor.setEnabled(false);
				btnChooseProduct.setEnabled(false);
				// Disable fields
				this.disableFields();
				// Fill fields with content
				this.fillFields(supplyOrder);
				break;
			case EDIT: 
				// Set title
				setTitle("Edit Supply Order");
				// Enable fields for editing
				this.enableFields();
				// Change submit button text to 'Update'
				btnSubmit.setText("Update");
				// Fill fields with content
				this.fillFields(supplyOrder);
				break;
			case CREATE:
				// Set title
				setTitle("Add New Supply Order");
				// Change submit button text to 'Create'
				btnSubmit.setText("Create");
				// Enable fields
				this.enableFields();
				// Peek ID
				txtID.setText(String.valueOf(PrimaryKey.peekID(PrimaryKey.Keys.SUPPLY_ORDER)));
				// Set 'stocked' to false
				rdbtnNo.setSelected(true);
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
		// Keep 'stocked' panel disabled and ID field disabled
		txtID.setEnabled(false);
		rdbtnYes.setEnabled(false);
		rdbtnNo.setEnabled(false);
	}
	
	// FIll in the fields
	private void fillFields(SupplyOrder supplyOrder) {
		txtID.setText(String.valueOf(supplyOrder.getID()));
		txtPricePerItem.setText(String.valueOf(supplyOrder.getPricePerItem()));
		txtQuantity.setText(String.valueOf(supplyOrder.getQuantity()));
		txtContractorDisplay.setText(supplyOrder.getContractor().getCompanyName());
		this.contractor = supplyOrder.getContractor();
		this.product = supplyOrder.getProduct();
		txtProductDisplay.setText(supplyOrder.getProduct().getName());
		txtOrderDate.setText(Common.datetimeToString(supplyOrder.getDateOrdered()));
		rdbtnYes.setSelected(supplyOrder.isStocked());
		rdbtnNo.setSelected(!supplyOrder.isStocked());
	}
	
	/**
	 * Gets the supply order used in view/edit, or one created in 'create' mode. Can be null!
	 *
	 * @return the SupplyOrder
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
		
		// 'submit' button: Update or create the supply order
		btnSubmit.addActionListener(e -> {
			String message = "";
			if (mode == Mode.EDIT) {
				message = "Are you sure you want to edit the details of this supply order?";
			} else if (mode == Mode.CREATE) {
				message = "Create supply order?";
			}
			if (Messages.confirm(SupplyOrderUI.this, message)) {
				
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
				
				// Validate order quantity
				String stringQuantity = txtQuantity.getText().strip();
				int quantity;
				try {
					quantity = Integer.parseInt(stringQuantity);
				} catch (NumberFormatException e2) {
					Messages.error(this, "Order quanity must be a whole, positive number");
					return;
				}
				// if less than zero
				if (quantity < 0) {
					Messages.error(this, "Order quanity must be a whole, positive number");
				}
				
				// Validate order date
				String orderDateString = txtOrderDate.getText().strip();
				if (orderDateString.isEmpty()) {
					Messages.error(this, "Order date cannot be empty!");
					return;
				}
				// Parse order date
				LocalDateTime dateOrdered;
				try {
					dateOrdered = Common.stringToDateTime(orderDateString);
				} catch (DateTimeParseException e1) {
					Messages.error(this, "Please enter a order date in the format of: " + Common.getDateFormat());
					return;
				}
	
				
				// if mode == view, update data
				if (mode == Mode.EDIT) {
					
					try {
						supplyCtrl.updateSupplyOrderContractor(supplyOrder, contractor);
						supplyCtrl.updateSupplyOrderPricePerItem(supplyOrder, pricePerItem);
						supplyCtrl.updateSupplyOrderProduct(supplyOrder, product);
						supplyCtrl.updateSupplyOrderQuantity(supplyOrder, quantity);
						supplyCtrl.updateSupplyOrderDateOrdered(supplyOrder, dateOrdered);
					} catch (IllegalModificationException e1) {
						Messages.error(this, "Cannot update a supply order that has already been stocked!");
						return;
					}
				} else if (mode == Mode.CREATE) {
					// if mode == Create, create a new supply order
					this.supplyOrder = supplyCtrl.createSupplyOrder(product, quantity, pricePerItem, contractor, dateOrdered);
				}
				
				// Dispose of the window
				this.dispose();
			}
		});
		
		btnChooseProduct.addActionListener(e -> {
			ChooseProduct frame = new ChooseProduct(auth, ChooseProduct.Mode.ALL);
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
		
		// Auto-fill order date button
		btnAutoFillOrderDate.addActionListener(e -> {
			txtOrderDate.setText(Common.datetimeToString(LocalDateTime.now()));
		});
		
		// Auto-fill data after choosing a supply offer
		btnFillWithSupplyOffer.addActionListener(e -> {
			ChooseSupplyOffers frame = new ChooseSupplyOffers(auth);
			frame.setVisible(true);
			if (frame.getSelectedSupplyOffer() != null) {
				SupplyOffer supplyOffer = frame.getSelectedSupplyOffer();
				// fill data
				this.txtContractorDisplay.setText(supplyOffer.getContractor().getCompanyName());
				this.contractor = supplyOffer.getContractor();
				this.txtID.setText(String.valueOf(supplyOffer.getID()));
				this.txtPricePerItem.setText(String.valueOf(supplyOffer.getPricePerItem()));
				this.txtProductDisplay.setText(supplyOffer.getProduct().getName());
				this.product = supplyOffer.getProduct();
				this.txtQuantity.setText(String.valueOf(supplyOffer.getMinQuantity()));
			}
		});
	}
}
