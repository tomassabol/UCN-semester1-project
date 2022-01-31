package gui.windows.model;

import java.awt.Component;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.AuthenticationController;
import controller.StockController;
import gui.Messages;
import models.PrimaryKey;
import models.StorageLocation;

import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import javax.swing.JTextField;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;

/**
 * @author Daniels Kanepe
 *
 */
public class StorageLocationUI extends JDialog {
	
	public enum Mode {
		VIEW,
		EDIT,
		CREATE
	}

	private JPanel contentPane;
	private JTextField txtID;
	private JTextField txtName;
	private JTextField txtAddress;
	private JButton btnSubmit;
	
	StorageLocation storageLocation;
	StockController stockCtrl;
	Mode mode;
	AuthenticationController auth;
	private JPanel isAStorePanel;
	private JRadioButton rdbtnStoreYes;
	private JRadioButton rdbtnStoreNo;
	private final ButtonGroup buttonGroup = new ButtonGroup();


	/**
	 * Constructor for Creating a storage location
	 *
	 * @param auth the auth
	 */
	public StorageLocationUI(AuthenticationController auth) {
		this(auth, null, Mode.CREATE);
		this.storageLocation = null;
	}

	/**
	 * Constructor for view/edit storage location
	 *
	 * @param auth
	 * @param StorageLocation
	 * @param mode the mode (create/view/edit)
	 * @wbp.parser.constructor
	 */
	public StorageLocationUI(AuthenticationController auth, StorageLocation storageLocation, Mode mode) {
		this.mode = mode;
		this.auth = auth;
		
		stockCtrl = new StockController();
		this.storageLocation = storageLocation;
		
		setModal(true);
		setBounds(100, 100, 600, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[]{0, 0, 0};
		gbl_contentPanel.rowHeights = new int[]{0, 0, 0, 0, 0, 0};
		gbl_contentPanel.columnWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
		gbl_contentPanel.rowWeights = new double[]{1.0, 1.0, 1.0, 1.0, 0.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPanel);
		
		JLabel lblID = new JLabel("ID");
		GridBagConstraints gbc_lblID = new GridBagConstraints();
		gbc_lblID.anchor = GridBagConstraints.SOUTH;
		gbc_lblID.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblID.insets = new Insets(0, 0, 5, 5);
		gbc_lblID.gridx = 0;
		gbc_lblID.gridy = 0;
		contentPane.add(lblID, gbc_lblID);
		
		
		JLabel lblName = new JLabel("Name *");
		GridBagConstraints gbc_lblName = new GridBagConstraints();
		gbc_lblName.anchor = GridBagConstraints.SOUTH;
		gbc_lblName.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblName.insets = new Insets(0, 0, 5, 0);
		gbc_lblName.gridx = 1;
		gbc_lblName.gridy = 0;
		contentPane.add(lblName, gbc_lblName);
		
		
		txtID = new JTextField();
		txtID.setEnabled(false);
		GridBagConstraints gbc_txtID = new GridBagConstraints();
		gbc_txtID.anchor = GridBagConstraints.NORTH;
		gbc_txtID.insets = new Insets(0, 0, 5, 5);
		gbc_txtID.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtID.gridx = 0;
		gbc_txtID.gridy = 1;
		contentPane.add(txtID, gbc_txtID);
		txtID.setColumns(10);
		
		
		txtName = new JTextField();
		GridBagConstraints gbc_txtName = new GridBagConstraints();
		gbc_txtName.anchor = GridBagConstraints.NORTH;
		gbc_txtName.insets = new Insets(0, 0, 5, 0);
		gbc_txtName.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtName.gridx = 1;
		gbc_txtName.gridy = 1;
		contentPane.add(txtName, gbc_txtName);
		txtName.setColumns(10);
		
		
		JLabel lblAddress = new JLabel("Address *");
		GridBagConstraints gbc_lblAddress = new GridBagConstraints();
		gbc_lblAddress.anchor = GridBagConstraints.SOUTH;
		gbc_lblAddress.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblAddress.insets = new Insets(0, 0, 5, 5);
		gbc_lblAddress.gridx = 0;
		gbc_lblAddress.gridy = 2;
		contentPane.add(lblAddress, gbc_lblAddress);
		
		
		JLabel lblStore = new JLabel("Is a store?");
		GridBagConstraints gbc_lblStore = new GridBagConstraints();
		gbc_lblStore.anchor = GridBagConstraints.SOUTH;
		gbc_lblStore.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblStore.insets = new Insets(0, 0, 5, 0);
		gbc_lblStore.gridx = 1;
		gbc_lblStore.gridy = 2;
		contentPane.add(lblStore, gbc_lblStore);
		
		
		txtAddress = new JTextField();
		GridBagConstraints gbc_txtAddress = new GridBagConstraints();
		gbc_txtAddress.anchor = GridBagConstraints.NORTH;
		gbc_txtAddress.insets = new Insets(0, 0, 5, 5);
		gbc_txtAddress.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtAddress.gridx = 0;
		gbc_txtAddress.gridy = 3;
		contentPane.add(txtAddress, gbc_txtAddress);
		txtAddress.setColumns(10);
		
		isAStorePanel = new JPanel();
		GridBagConstraints gbc_isAStorePanel = new GridBagConstraints();
		gbc_isAStorePanel.insets = new Insets(0, 0, 5, 0);
		gbc_isAStorePanel.fill = GridBagConstraints.BOTH;
		gbc_isAStorePanel.gridx = 1;
		gbc_isAStorePanel.gridy = 3;
		contentPane.add(isAStorePanel, gbc_isAStorePanel);
		GridBagLayout gbl_isAStorePanel = new GridBagLayout();
		gbl_isAStorePanel.columnWidths = new int[]{0, 0, 0};
		gbl_isAStorePanel.rowHeights = new int[]{0, 0};
		gbl_isAStorePanel.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gbl_isAStorePanel.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		isAStorePanel.setLayout(gbl_isAStorePanel);
		
		rdbtnStoreYes = new JRadioButton("Yes");
		rdbtnStoreYes.setSelected(true);
		buttonGroup.add(rdbtnStoreYes);
		GridBagConstraints gbc_rdbtnStoreYes = new GridBagConstraints();
		gbc_rdbtnStoreYes.anchor = GridBagConstraints.WEST;
		gbc_rdbtnStoreYes.insets = new Insets(0, 0, 0, 5);
		gbc_rdbtnStoreYes.gridx = 0;
		gbc_rdbtnStoreYes.gridy = 0;
		isAStorePanel.add(rdbtnStoreYes, gbc_rdbtnStoreYes);
		
		rdbtnStoreNo = new JRadioButton("No");
		buttonGroup.add(rdbtnStoreNo);
		GridBagConstraints gbc_rdbtnStoreNo = new GridBagConstraints();
		gbc_rdbtnStoreNo.anchor = GridBagConstraints.WEST;
		gbc_rdbtnStoreNo.gridx = 1;
		gbc_rdbtnStoreNo.gridy = 0;
		isAStorePanel.add(rdbtnStoreNo, gbc_rdbtnStoreNo);
		
		
		btnSubmit = new JButton("Update");
		GridBagConstraints gbc_btnOk = new GridBagConstraints();
		gbc_btnOk.anchor = GridBagConstraints.NORTHEAST;
		gbc_btnOk.gridx = 1;
		gbc_btnOk.gridy = 4;
		contentPane.add(btnSubmit, gbc_btnOk);
		
		switch (mode) {
			case VIEW:
				// Set title
				setTitle("View Storage Location - " + storageLocation.getName());
				// Hide 'Update' button if in view mode
				btnSubmit.setVisible(false);
				// Disable fields
				this.disableFields();
				// Fill fields with content
				this.fillFields(storageLocation);
				break;
			case EDIT: 
				// Set title
				setTitle("Edit Storage Location");
				// Enable fields for editing
				this.enableFields();
				// Fill fields with content
				this.fillFields(storageLocation);
				break;
			case CREATE:
				// Set title
				setTitle("Add New Storage Location");
				// Change submit button text to 'Create'
				btnSubmit.setText("Create");
				// Enable fields
				this.enableFields();
				// Peek ID
				txtID.setText(String.valueOf(PrimaryKey.peekID(PrimaryKey.Keys.STORAGE_LOCATION)));
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
			   if (c instanceof JTextField || c instanceof JTextArea || c instanceof JRadioButton) {
				      c.setEnabled(false);
				   }
			}
		rdbtnStoreYes.setEnabled(false);
		rdbtnStoreNo.setEnabled(false);
	}
	
	
	// Makes the text fields editable except ID & Type field
	private void enableFields() {
		for (Component c : this.getContentPane().getComponents()) {
			   if (c instanceof JTextField || c instanceof JTextArea || c instanceof JPanel) {
			      c.setEnabled(true);
			   }
			}
		txtID.setEnabled(false);
		rdbtnStoreYes.setEnabled(true);
		rdbtnStoreNo.setEnabled(true);
	}
	
	// FIll in the fields
	private void fillFields(StorageLocation storageLocation) {
		txtID.setText(String.valueOf(storageLocation.ID));
		txtName.setText(storageLocation.getName());
		txtAddress.setText(storageLocation.getAddress());
		if (storageLocation.getIsAStore()) {
			rdbtnStoreYes.setSelected(true);
		} else {
			rdbtnStoreNo.setSelected(true);
		}
	}
	
	/**
	 * Gets the storage location used in view/edit, or one created in 'create' mode. Can be null!
	 *
	 * @return the StorageLocation
	 */
	public StorageLocation getStorageLocation() {
		return this.storageLocation;
	}
 	
		/*
	 * *******************************************************
	 * *******************  EVENT HANDLERS *******************
	 * *******************************************************
	 */
	private void addEventHandlers() {
		
		// 'update' button: Update the storage location
		btnSubmit.addActionListener(e -> {
			String message = "";
			if (mode == Mode.EDIT) {
				message = "Are you sure you want to update the storage location's details?";
			} else if (mode == Mode.CREATE) {
				message = "Create storage location?";
			}
			if (Messages.confirm(StorageLocationUI.this, message)) {
				
				// Validate name
				String name = txtName.getText().strip();
				if (name.isEmpty()) {
					Messages.error(this, "Name cannot be empty!");
					return;
				}
	
				// Validate address
				String address = txtAddress.getText().strip();
				if (address.isEmpty()) {
					Messages.error(this, "Address cannot be empty!");
					return;
				}
				
				// Is a store: no validation needed
				boolean isAStore = rdbtnStoreYes.isSelected() ? true : false;
				
				// if mode == view, update data
				if (mode == Mode.EDIT) {
					stockCtrl.updateStorageLocationName(storageLocation, name);
					stockCtrl.updateStorageLocationAddress(storageLocation, address);
					stockCtrl.updateStorageLocationIsAStore(storageLocation, isAStore);
				} else if (mode == Mode.CREATE) {
					// if mode == Create, create a new storage location
					this.storageLocation = stockCtrl.createStorageLocation(name, address, isAStore);
				}
			}
			// Dispose of the window
			this.dispose();
		});
	}
}

