package gui;

import java.awt.Component;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.AuthenticationController;
import controller.StockController;
import model.Product;
import model.Shelf;
import model.StorageLocation;

import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import javax.swing.JTextField;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

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
	private JButton btnOK;
	
	Shelf shelf;
	Mode mode;
	StockController stockCtrl;
	AuthenticationController auth;
    StorageLocation storageLocation;
    Product product;

	private JLabel lblStorageLocation;
	private JTextField txtStorageLocation;
	private JButton btnChooseStorageLoc;
	private JLabel lblProducts;
	private JTextField txtProducts;
	private JButton btnChooseProducts;


	/**
	 * Constructor for Create contractor
	 *
	 * @param auth the auth
	 */
	public ShelfUI(AuthenticationController auth) {
		this(auth, null, Mode.CREATE);
		this.shelf = null;
	}

	/**
	 * Constructor for View/Edit contractor
	 * @param auth the auth
	 * @param contractor the contractor
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
		gbl_contentPanel.columnWidths = new int[]{273, 0, 0};
		gbl_contentPanel.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_contentPanel.columnWeights = new double[]{1.0, 0.0, Double.MIN_VALUE};
		gbl_contentPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
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
		
		
		JLabel lblName = new JLabel("Name");
		GridBagConstraints gbc_lblName = new GridBagConstraints();
		gbc_lblName.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblName.insets = new Insets(0, 0, 5, 5);
		gbc_lblName.gridx = 0;
		gbc_lblName.gridy = 2;
		contentPane.add(lblName, gbc_lblName);
		
		
		txtName = new JTextField();
		GridBagConstraints gbc_txtName = new GridBagConstraints();
		gbc_txtName.insets = new Insets(0, 0, 5, 5);
		gbc_txtName.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtName.gridx = 0;
		gbc_txtName.gridy = 3;
		contentPane.add(txtName, gbc_txtName);
		txtName.setColumns(10);
		
		lblStorageLocation = new JLabel("Storage Location");
		GridBagConstraints gbc_lblStorageLocation = new GridBagConstraints();
		gbc_lblStorageLocation.insets = new Insets(0, 0, 5, 5);
		gbc_lblStorageLocation.gridx = 0;
		gbc_lblStorageLocation.gridy = 4;
		contentPane.add(lblStorageLocation, gbc_lblStorageLocation);
		
		txtStorageLocation = new JTextField();
		GridBagConstraints gbc_txtStorageLocation = new GridBagConstraints();
		gbc_txtStorageLocation.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtStorageLocation.insets = new Insets(0, 0, 5, 5);
		gbc_txtStorageLocation.gridx = 0;
		gbc_txtStorageLocation.gridy = 5;
		contentPane.add(txtStorageLocation, gbc_txtStorageLocation);
		txtStorageLocation.setColumns(10);
		
		btnChooseStorageLoc = new JButton("Choose");
		btnChooseStorageLoc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		GridBagConstraints gbc_btnChooseStorageLoc = new GridBagConstraints();
		gbc_btnChooseStorageLoc.insets = new Insets(0, 0, 5, 0);
		gbc_btnChooseStorageLoc.gridx = 1;
		gbc_btnChooseStorageLoc.gridy = 5;
		contentPane.add(btnChooseStorageLoc, gbc_btnChooseStorageLoc);
		
		lblProducts = new JLabel("Products");
		GridBagConstraints gbc_lblProducts = new GridBagConstraints();
		gbc_lblProducts.insets = new Insets(0, 0, 5, 5);
		gbc_lblProducts.gridx = 0;
		gbc_lblProducts.gridy = 6;
		contentPane.add(lblProducts, gbc_lblProducts);
		
		txtProducts = new JTextField();
		GridBagConstraints gbc_txtProducts = new GridBagConstraints();
		gbc_txtProducts.insets = new Insets(0, 0, 5, 5);
		gbc_txtProducts.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtProducts.gridx = 0;
		gbc_txtProducts.gridy = 7;
		contentPane.add(txtProducts, gbc_txtProducts);
		txtProducts.setColumns(10);
		
		btnChooseProducts = new JButton("Choose");
		GridBagConstraints gbc_btnChooseProducts = new GridBagConstraints();
		gbc_btnChooseProducts.insets = new Insets(0, 0, 5, 0);
		gbc_btnChooseProducts.gridx = 1;
		gbc_btnChooseProducts.gridy = 7;
		contentPane.add(btnChooseProducts, gbc_btnChooseProducts);
		
		
		btnOK = new JButton("OK");
		GridBagConstraints gbc_btnOK = new GridBagConstraints();
		gbc_btnOK.anchor = GridBagConstraints.SOUTH;
		gbc_btnOK.gridx = 1;
		gbc_btnOK.gridy = 8;
		contentPane.add(btnOK, gbc_btnOK);
		
		switch (mode) {
			case VIEW:
				// Set title
				setTitle("View Contractor - " + "contractor.getCompanyName()");
				// Hide 'Update' button if in view mode
				btnOK.setVisible(false);
				// Disable fields
				this.disableFields();
				// Fill fields with content
				this.fillFields(shelf);
				break;
			case EDIT: 
				// Set title
				setTitle("Edit contractor - " + "contractor.getCompanyName()");
				// Enable fields for editing
				this.enableFields();
				// Fill fields with content
				this.fillFields(shelf);
				break;
			case CREATE:
				// Set title
				setTitle("Create new Shelf");
				// Change button text
				btnOK.setText("Create");
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
            btnChooseStorageLoc.setEnabled(false);
            btnChooseProducts.setEnabled(false);
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
        btnChooseProducts.setEnabled(false);
	}

	// FIll in the fields
	private void fillFields(Shelf shelf) {
		txtID.setText(String.valueOf(shelf.ID));
        txtName.setText(shelf.getName());
        txtStorageLocation.setText(String.valueOf(shelf.getStorageLocation().getName()));
        txtProducts.setText("implement");
	}

	/**
	 * @return contractor
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
		
		// 'update' button: Update the contractor
		btnOK.addActionListener(e -> {
			if (Messages.confirm(ShelfUI.this, "Are you sure you want to update the shelf's details?", "Update")) {
				
				// Validate that contractor's company name is not empty
				String name = txtName.getText().strip();
				if (name.isEmpty()) {
					Messages.error(this, "Shelf name cannot be empty!");
					return;
				}
				
				// UPDATE
				if (mode == Mode.EDIT) {
					stockCtrl.updateShalfName(shelf, name);
				} else if (mode == Mode.CREATE) {

                    StorageLocation storageLocation = stockCtrl.createStorageLocation("test location", "test address", true);
					this.shelf = stockCtrl.createShelf(name, storageLocation);
				}
				
			}
			// Dispose of the window
			this.dispose();
		});

        btnChooseStorageLoc.addActionListener(e -> {
			ChooseStorageLocationUI frame = new ChooseStorageLocationUI(auth);
			frame.setVisible(true);
			if (frame.getSelectedStorageLocation() != null) {
				this.storageLocation = frame.getSelectedStorageLocation();
				txtStorageLocation.setText(storageLocation.getName());
			}
		});

        
        /*
        btnChooseProducts.addActionListener(e -> {
			ChooseProductUI frame = new ChooseProductUI(auth);
			frame.setVisible(true);
			if (frame.getSelectedProduct() != null) {
				this.products = frame.getSelectedProduct();
				txtStorageLocation.setText(storageLocation.getName());
			}
		});
        */
	}
}

