package gui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.time.format.DateTimeParseException;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import controller.AuthenticationController;
import controller.ContractorController;
import controller.CustomerController;
import gui.CustomerUI.Mode;
import model.CustomerType;
import model.Contractor;





public class ContractorUI extends JDialog {
	
	public enum Mode {
		VIEW,
		EDIT
	}

	

	private JPanel contentPane;
	private JTextField txtID;
	private JTextField txtCompanyName;
	
	private JButton btnOk;
	
	private JTextField txtType;
	private JTextField txtType1;
	private JPanel typePanel;
	
	
	ContractorController contractorCtrl;
	
	Mode mode;
	AuthenticationController auth;
	private Contractor contractor; 
	
	



	/**
	 * Create the dialog.
	 */
	public ContractorUI(AuthenticationController auth, Contractor contractor, Mode mode) {
		this.mode = mode;
		this.contractor = contractor;
		this.auth = auth;
	{
		
		contractorCtrl = new ContractorController();
		// This can be changed with the 'choose' button
		
		
		setModal(true);
		setBounds(100, 100, 450, 341);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[]{0, 0, 0};
		gbl_contentPanel.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_contentPanel.columnWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
		gbl_contentPanel.rowWeights = new double[]{1.0, 1.0, 1.0, 1.0, 1.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPanel);
		
		JLabel lblID = new JLabel("ID");
		GridBagConstraints gbc_lblID = new GridBagConstraints();
		gbc_lblID.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblID.insets = new Insets(0, 0, 5, 5);
		gbc_lblID.gridx = 0;
		gbc_lblID.gridy = 0;
		contentPane.add(lblID, gbc_lblID);
		
		
		JLabel lblCompanyName = new JLabel("Company Name *");
		GridBagConstraints gbc_lblCompanyName = new GridBagConstraints();
		gbc_lblCompanyName.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblCompanyName.insets = new Insets(0, 0, 5, 0);
		gbc_lblCompanyName.gridx = 1;
		gbc_lblCompanyName.gridy = 0;
		contentPane.add(lblCompanyName, gbc_lblCompanyName);
		
		
		txtID = new JTextField(String.valueOf(contractor.ID));
		txtID.setEnabled(false);
		GridBagConstraints gbc_txtID = new GridBagConstraints();
		gbc_txtID.insets = new Insets(0, 0, 5, 5);
		gbc_txtID.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtID.gridx = 0;
		gbc_txtID.gridy = 1;
		contentPane.add(txtID, gbc_txtID);
		txtID.setColumns(10);
		
		
		txtCompanyName = new JTextField(contractor.getCompanyName());
		GridBagConstraints gbc_txtFirstName = new GridBagConstraints();
		gbc_txtFirstName.insets = new Insets(0, 0, 5, 0);
		gbc_txtFirstName.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtFirstName.gridx = 1;
		gbc_txtFirstName.gridy = 1;
		contentPane.add(txtCompanyName, txtCompanyName);
		txtCompanyName.setColumns(10);
		
		
		JLabel lblLastName = new JLabel("Last Name *");
		GridBagConstraints gbc_lblLastName = new GridBagConstraints();
		gbc_lblLastName.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblLastName.insets = new Insets(0, 0, 5, 5);
		gbc_lblLastName.gridx = 0;
		gbc_lblLastName.gridy = 2;
		contentPane.add(lblLastName, gbc_lblLastName);
		
		
		JLabel lblAddress = new JLabel("Address *");
		GridBagConstraints gbc_lblAddress = new GridBagConstraints();
		gbc_lblAddress.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblAddress.insets = new Insets(0, 0, 5, 0);
		gbc_lblAddress.gridx = 1;
		gbc_lblAddress.gridy = 2;
		contentPane.add(lblAddress, gbc_lblAddress);
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		typePanel = new JPanel();
		GridBagConstraints gbc_typePanel = new GridBagConstraints();
		gbc_typePanel.insets = new Insets(0, 0, 5, 0);
		gbc_typePanel.fill = GridBagConstraints.BOTH;
		gbc_typePanel.gridx = 1;
		gbc_typePanel.gridy = 5;
		contentPane.add(typePanel, gbc_typePanel);
		GridBagLayout gbl_typePanel = new GridBagLayout();
		gbl_typePanel.columnWidths = new int[]{0, 0, 0};
		gbl_typePanel.rowHeights = new int[]{0, 0};
		gbl_typePanel.columnWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
		gbl_typePanel.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		typePanel.setLayout(gbl_typePanel);
		
		
		
		
		
		
		JLabel lblBirth = new JLabel("Birth ("+ Common.getDateFormat() + ") *");
		GridBagConstraints gbc_lblBirth = new GridBagConstraints();
		gbc_lblBirth.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblBirth.insets = new Insets(0, 0, 5, 5);
		gbc_lblBirth.gridx = 0;
		gbc_lblBirth.gridy = 6;
		contentPane.add(lblBirth, gbc_lblBirth);
		
		
		
		
		
		btnOk = new JButton("Update");
		GridBagConstraints gbc_btnOk = new GridBagConstraints();
		gbc_btnOk.anchor = GridBagConstraints.NORTHEAST;
		gbc_btnOk.gridx = 1;
		gbc_btnOk.gridy = 7;
		contentPane.add(btnOk, gbc_btnOk);
		
		
		switch (mode) {
			case VIEW:
				// Set title
				setTitle("View Customer - " + contractor.getCompanyName());
				// Hide 'Update' button if in view mode
				btnOk.setVisible(false);
				// Disable 'choose' button if in view mode.
				
				// Disable fields
				this.disableFields();
				break;
			case EDIT: 
				// Set title
				setTitle("Edit Contractor");
				// Enable fields for editing
				this.enableFields();
				break;
		}	

		addEventHandlers(); }
	
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
		txtType.setEnabled(false);
	}
	
		/*
	 * *******************************************************
	 * *******************  EVENT HANDLERS *******************
	 * *******************************************************
	 */
	private void addEventHandlers() {
		
		// 'update' button: Update the product
		btnOk.addActionListener(e -> {
			if (Messages.confirm(ContractorUI.this, "Are you sure you want to update the company's details?", "Update")) {
				
				// Validate First name
				String cname = txtCompanyName.getText().strip();
				if (cname.isEmpty()) {
					Messages.error(this, "Company Name name cannot be empty!");
					return;
				}
	
				
				
				
				
				
				
				
				
				// UPDATE
				contractorCtrl.updateContractorCompanyName(contractor, cname);
				
				
			}
			// Dispose of the window
			this.dispose();
		});
	}
}

