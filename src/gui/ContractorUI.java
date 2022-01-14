package gui;

import java.awt.BorderLayout;
import java.awt.Component;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.AuthenticationController;
import controller.ContractorController;
import controller.CustomerController;
import model.Contractor;
import model.Customer;
import model.CustomerType;
import model.IFCustomer;

import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import javax.swing.JTextField;
import java.awt.Insets;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import javax.swing.JButton;
import javax.swing.JTextArea;

public class ContractorUI extends JDialog {
	
	public enum Mode {
		VIEW,
		EDIT
	}

	private JPanel contentPane;
	private JTextField txtID;
	private JTextField txtCompanyName;
	private JButton btnUpdate;
	
	Contractor contractor;
	Mode mode;
	ContractorController contractorCtrl;
	AuthenticationController auth;



	/**
	 * Create the dialog.
	 */
	public ContractorUI(AuthenticationController auth, Contractor contractor, Mode mode) {
		this.mode = mode;
		this.contractor = contractor;
		this.auth = auth;
		contractorCtrl = new ContractorController();
		
		setModal(true);
		setBounds(100, 100, 450, 341);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[]{0, 0, 0};
		gbl_contentPanel.rowHeights = new int[]{0, 0, 0, 0};
		gbl_contentPanel.columnWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
		gbl_contentPanel.rowWeights = new double[]{0.0, 0.0, 1.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPanel);
		
		JLabel lblID = new JLabel("ID");
		GridBagConstraints gbc_lblID = new GridBagConstraints();
		gbc_lblID.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblID.insets = new Insets(0, 0, 5, 5);
		gbc_lblID.gridx = 0;
		gbc_lblID.gridy = 0;
		contentPane.add(lblID, gbc_lblID);
		
		
		JLabel lblCompanyName = new JLabel("First Name *");
		GridBagConstraints gbc_lblCompanyName = new GridBagConstraints();
		gbc_lblCompanyName.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblCompanyName.insets = new Insets(0, 0, 5, 0);
		gbc_lblCompanyName.gridx = 1;
		gbc_lblCompanyName.gridy = 0;
		contentPane.add(lblCompanyName, gbc_lblCompanyName);
		
		
		txtID = new JTextField(String.valueOf(contractor.getID()));
		txtID.setEnabled(false);
		GridBagConstraints gbc_txtID = new GridBagConstraints();
		gbc_txtID.insets = new Insets(0, 0, 5, 5);
		gbc_txtID.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtID.gridx = 0;
		gbc_txtID.gridy = 1;
		contentPane.add(txtID, gbc_txtID);
		txtID.setColumns(10);
		
		
		txtCompanyName = new JTextField(contractor.getCompanyName());
		GridBagConstraints gbc_txtCompanyName = new GridBagConstraints();
		gbc_txtCompanyName.insets = new Insets(0, 0, 5, 0);
		gbc_txtCompanyName.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtCompanyName.gridx = 1;
		gbc_txtCompanyName.gridy = 1;
		contentPane.add(txtCompanyName, gbc_txtCompanyName);
		txtCompanyName.setColumns(10);
		
		
		btnUpdate = new JButton("Update");
		GridBagConstraints gbc_btnUpdate = new GridBagConstraints();
		gbc_btnUpdate.anchor = GridBagConstraints.SOUTHEAST;
		gbc_btnUpdate.gridx = 1;
		gbc_btnUpdate.gridy = 2;
		contentPane.add(btnUpdate, gbc_btnUpdate);
		
		switch (mode) {
			case VIEW:
				// Set title
				setTitle("View Contractor - " + contractor.getCompanyName());
				// Hide 'Update' button if in view mode
				btnUpdate.setVisible(false);
				// Disable fields
				this.disableFields();
				break;
			case EDIT: 
				// Set title
				setTitle("Edit contractor - " + contractor.getCompanyName());
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
	
	
	// Makes the text fields editable except ID
	private void enableFields() {
		for (Component c : this.getContentPane().getComponents()) {
			   if (c instanceof JTextField || c instanceof JTextArea) {
			      c.setEnabled(true);
			   }
			}
		txtID.setEnabled(false);
	}
	
		/*
	 * *******************************************************
	 * *******************  EVENT HANDLERS *******************
	 * *******************************************************
	 */
	private void addEventHandlers() {
		
		// 'update' button: Update the product
		btnUpdate.addActionListener(e -> {
			if (Messages.confirm(ContractorUI.this, "Are you sure you want to update the contractor's details?", "Update")) {
				
				// Validate that contractor's company name is not empty
				String companyName = txtCompanyName.getText().strip();
				if (companyName.isEmpty()) {
					Messages.error(this, "Company name cannot be empty!");
					return;
				}
				
				// UPDATE
				contractorCtrl.updateCompanyName(contractor, companyName);
				
			}
			// Dispose of the window
			this.dispose();
		});
	}
}

