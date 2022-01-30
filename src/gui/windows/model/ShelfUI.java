package gui.windows.model;

import java.awt.Component;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.AuthenticationController;
import controller.StockController;
import gui.Messages;
import gui.windows.ChooseStorageLocations;
import models.PrimaryKey;
import models.Shelf;
import models.StorageLocation;

import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import javax.swing.JTextField;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JTextArea;

/**
 * @author Daniels Kanepe
 *
 */
public class ShelfUI extends JDialog {
	
	public enum Mode {
		VIEW,
		EDIT,
		CREATE
	}

	private JPanel contentPane;
	private JTextField txtID;
	private JTextField txtName;
	private JButton btnSubmit;
	
	Shelf shelf;
	Mode mode;
	StockController stockCtrl;
	AuthenticationController auth;
    StorageLocation storageLocation;

	private JLabel lblStorageLocation;
	private JPanel storageLocationPanel;
	private JTextField txtStorageLocationDisplay;
	private JButton btnChooseStorageLoc;


	/**
	 * shelf for Create shelf
	 *
	 * @param auth the auth
	 */
	public ShelfUI(AuthenticationController auth) {
		this(auth, null, Mode.CREATE);
		this.shelf = null;
	}

	/**
	 * Constructor for View/Edit shelf
	 * @param auth the auth
	 * @param shelf the shelf
	 * @param mode the mode
     * @wbp.parser.constructor
	 */
	public ShelfUI(AuthenticationController auth, Shelf shelf, Mode mode) {
		this.mode = mode;
		this.shelf = shelf;
		this.auth = auth;

        stockCtrl = new StockController();

        this.storageLocation = storageLocation != null ? shelf.getStorageLocation() : null;
		
		setModal(true);
		setBounds(100, 100, 450, 341);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[]{273, 0};
		gbl_contentPanel.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0};
		gbl_contentPanel.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_contentPanel.rowWeights = new double[]{1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 0.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPanel);
		
		JLabel lblID = new JLabel("ID");
		GridBagConstraints gbc_lblID = new GridBagConstraints();
		gbc_lblID.anchor = GridBagConstraints.SOUTHWEST;
		gbc_lblID.insets = new Insets(0, 0, 5, 0);
		gbc_lblID.gridx = 0;
		gbc_lblID.gridy = 0;
		contentPane.add(lblID, gbc_lblID);
		
		
		txtID = new JTextField();
		txtID.setEnabled(false);
		GridBagConstraints gbc_txtID = new GridBagConstraints();
		gbc_txtID.anchor = GridBagConstraints.NORTH;
		gbc_txtID.insets = new Insets(0, 0, 5, 0);
		gbc_txtID.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtID.gridx = 0;
		gbc_txtID.gridy = 1;
		contentPane.add(txtID, gbc_txtID);
		txtID.setColumns(10);
		
		
		JLabel lblName = new JLabel("Name *");
		GridBagConstraints gbc_lblName = new GridBagConstraints();
		gbc_lblName.anchor = GridBagConstraints.SOUTHWEST;
		gbc_lblName.insets = new Insets(0, 0, 5, 0);
		gbc_lblName.gridx = 0;
		gbc_lblName.gridy = 2;
		contentPane.add(lblName, gbc_lblName);
		
		
		txtName = new JTextField();
		GridBagConstraints gbc_txtName = new GridBagConstraints();
		gbc_txtName.anchor = GridBagConstraints.NORTH;
		gbc_txtName.insets = new Insets(0, 0, 5, 0);
		gbc_txtName.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtName.gridx = 0;
		gbc_txtName.gridy = 3;
		contentPane.add(txtName, gbc_txtName);
		txtName.setColumns(10);
		
		lblStorageLocation = new JLabel("Storage Location *");
		GridBagConstraints gbc_lblStorageLocation = new GridBagConstraints();
		gbc_lblStorageLocation.anchor = GridBagConstraints.SOUTHWEST;
		gbc_lblStorageLocation.insets = new Insets(0, 0, 5, 0);
		gbc_lblStorageLocation.gridx = 0;
		gbc_lblStorageLocation.gridy = 4;
		contentPane.add(lblStorageLocation, gbc_lblStorageLocation);
		
		storageLocationPanel = new JPanel();
		storageLocationPanel.setBorder(null);
		GridBagConstraints gbc_storageLocationPanel = new GridBagConstraints();
		gbc_storageLocationPanel.anchor = GridBagConstraints.NORTH;
		gbc_storageLocationPanel.insets = new Insets(0, 0, 5, 0);
		gbc_storageLocationPanel.fill = GridBagConstraints.HORIZONTAL;
		gbc_storageLocationPanel.gridx = 0;
		gbc_storageLocationPanel.gridy = 5;
		contentPane.add(storageLocationPanel, gbc_storageLocationPanel);
		GridBagLayout gbl_storageLocationPanel = new GridBagLayout();
		gbl_storageLocationPanel.columnWidths = new int[]{0, 0, 0};
		gbl_storageLocationPanel.rowHeights = new int[]{0, 0};
		gbl_storageLocationPanel.columnWeights = new double[]{1.0, 0.0, Double.MIN_VALUE};
		gbl_storageLocationPanel.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		storageLocationPanel.setLayout(gbl_storageLocationPanel);
		
		txtStorageLocationDisplay = new JTextField();
		txtStorageLocationDisplay.setEnabled(false);
		txtStorageLocationDisplay.setColumns(10);
		GridBagConstraints gbc_txtStorageLocationDisplay = new GridBagConstraints();
		gbc_txtStorageLocationDisplay.anchor = GridBagConstraints.NORTH;
		gbc_txtStorageLocationDisplay.insets = new Insets(0, 0, 0, 5);
		gbc_txtStorageLocationDisplay.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtStorageLocationDisplay.gridx = 0;
		gbc_txtStorageLocationDisplay.gridy = 0;
		storageLocationPanel.add(txtStorageLocationDisplay, gbc_txtStorageLocationDisplay);
		
		btnChooseStorageLoc = new JButton("Choose");
		GridBagConstraints gbc_btnChooseStorageLoc = new GridBagConstraints();
		gbc_btnChooseStorageLoc.gridx = 1;
		gbc_btnChooseStorageLoc.gridy = 0;
		storageLocationPanel.add(btnChooseStorageLoc, gbc_btnChooseStorageLoc);
		
		
		btnSubmit = new JButton("OK");
		GridBagConstraints gbc_btnOK = new GridBagConstraints();
		gbc_btnOK.anchor = GridBagConstraints.SOUTHEAST;
		gbc_btnOK.gridx = 0;
		gbc_btnOK.gridy = 6;
		contentPane.add(btnSubmit, gbc_btnOK);
		
		switch (mode) {
			case VIEW:
				// Set title
				setTitle("View shelf - " + shelf.getName());
				// Hide 'Update' button if in view mode
				btnSubmit.setVisible(false);
				// Disable fields
				this.disableFields();
				// disable choose buttons
				btnChooseStorageLoc.setEnabled(false);
				// Fill fields with content
				this.fillFields(shelf);
				break;
			case EDIT: 
				// Set title
				setTitle("Edit shelf - " + shelf.getName());
				// Enable fields for editing
				this.enableFields();
				// enable choose buttons
				btnChooseStorageLoc.setEnabled(true);
				// Fill fields with content
				this.fillFields(shelf);
				break;
			case CREATE:
				// Set title
				setTitle("Create new Shelf");
				// Change button text
				btnSubmit.setText("Create");
				// enable choose buttons
				btnChooseStorageLoc.setEnabled(true);
				// Enable fields for editing
				this.enableFields();
				// Peek ID
				txtID.setText(String.valueOf(PrimaryKey.peekID(PrimaryKey.Keys.SHELF)));
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
            btnChooseStorageLoc.setEnabled(false);
	}
	
	
	// Makes the text fields editable except ID
	private void enableFields() {
		for (Component c : this.getContentPane().getComponents()) {
			   if (c instanceof JTextField || c instanceof JTextArea) {
			      c.setEnabled(true);
			   }
			}
		txtID.setEnabled(false);
        btnChooseStorageLoc.setEnabled(true);
	}

	// FIll in the fields
	private void fillFields(Shelf shelf) {
		txtID.setText(String.valueOf(shelf.ID));
        txtName.setText(shelf.getName());
        txtStorageLocationDisplay.setText(shelf.getStorageLocation().getName());
	}

	/**
	 * @return shelf
	 */
	public Shelf getShelf() {
		return this.shelf;
	}
	
	/*
	 * *******************************************************
	 * *******************  EVENT HANDLERS *******************
	 * *******************************************************
	 */
	private void addEventHandlers() {
		
		// 'update' button: Update the customer
		btnSubmit.addActionListener(e -> {
			String message = "";
			if (mode == Mode.EDIT) {
				message = "Are you sure you want to update the shelf's details?";
			} else if (mode == Mode.CREATE) {
				message = "Create shelf?";
			}
			if (Messages.confirm(ShelfUI.this, message)) {
				
				// Validate that shelf name is not empty
				String name = txtName.getText().strip();
				if (name.isEmpty()) {
					Messages.error(this, "Shelf name cannot be empty!");
					return;
				}
				
				// if mode == view, update data
				if (mode == Mode.EDIT) {
					stockCtrl.updateShelfName(shelf, name);
					stockCtrl.updateShelfStorageLocation(shelf, storageLocation);
				} else if (mode == Mode.CREATE) {
					// if mode == Create, create a new customer
					this.shelf = stockCtrl.createShelf(name, this.storageLocation);
				}
			}
			// Dispose of the window
			this.dispose();
		});

		btnChooseStorageLoc.addActionListener(e -> {
			ChooseStorageLocations frame = new ChooseStorageLocations(auth);
			frame.setVisible(true);
			if (frame.getSelectedStorageLocation() != null) {
				this.storageLocation = frame.getSelectedStorageLocation();
				txtStorageLocationDisplay.setText(storageLocation.getName());
			}
		});
        
	}
}

