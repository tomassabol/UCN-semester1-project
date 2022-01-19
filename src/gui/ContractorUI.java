package gui;

import java.awt.Component;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.AuthenticationController;
import controller.ContractorController;
import model.Contractor;
import model.PrimaryKey;

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
public class ContractorUI extends JDialog {
	
	public enum Mode {
		VIEW,
		EDIT,
		CREATE
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
	 * Constructor for Create contractor
	 *
	 * @param auth the auth
	 */
	public ContractorUI(AuthenticationController auth) {
		this(auth, null, Mode.CREATE);
		this.contractor = null;
	}

	/**
	 * Constructor for View/Edit contractor
	 * @param auth the auth
	 * @param contractor the contractor
	 * @param mode the mode
	 * @wbp.parser.constructor
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
		
		
		JLabel lblCompanyName = new JLabel("Company Name *");
		GridBagConstraints gbc_lblCompanyName = new GridBagConstraints();
		gbc_lblCompanyName.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblCompanyName.insets = new Insets(0, 0, 5, 0);
		gbc_lblCompanyName.gridx = 1;
		gbc_lblCompanyName.gridy = 0;
		contentPane.add(lblCompanyName, gbc_lblCompanyName);
		
		
		txtID = new JTextField();
		txtID.setEnabled(false);
		GridBagConstraints gbc_txtID = new GridBagConstraints();
		gbc_txtID.insets = new Insets(0, 0, 5, 5);
		gbc_txtID.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtID.gridx = 0;
		gbc_txtID.gridy = 1;
		contentPane.add(txtID, gbc_txtID);
		txtID.setColumns(10);
		
		
		txtCompanyName = new JTextField();
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
				// Fill fields with content
				this.fillFields(contractor);
				break;
			case EDIT: 
				// Set title
				setTitle("Edit contractor - " + contractor.getCompanyName());
				// Enable fields for editing
				this.enableFields();
				// Fill fields with content
				this.fillFields(contractor);
				break;
			case CREATE:
				// Set title
				setTitle("Create new contractor");
				// Change button text
				btnUpdate.setText("Create");
				// Enable fields for editing
				this.enableFields();
				// Peek ID
				txtID.setText(String.valueOf(PrimaryKey.peekID(PrimaryKey.Keys.CONTRACTOR)));
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

	// FIll in the fields
	private void fillFields(Contractor contractor) {
		txtID.setText(String.valueOf(contractor.getID()));
		txtCompanyName.setText(contractor.getCompanyName());
	}

	/**
	 * @return contractor
	 */
	public Contractor getContractor() {
		return this.contractor;
	}
	
		/*
	 * *******************************************************
	 * *******************  EVENT HANDLERS *******************
	 * *******************************************************
	 */
	private void addEventHandlers() {
		
		// 'update' button: Update the contractor
		btnUpdate.addActionListener(e -> {
			if (Messages.confirm(ContractorUI.this, "Are you sure you want to update the contractor's details?", "Update")) {
				
				// Validate that contractor's company name is not empty
				String companyName = txtCompanyName.getText().strip();
				if (companyName.isEmpty()) {
					Messages.error(this, "Company name cannot be empty!");
					return;
				}
				
				// UPDATE
				if (mode == Mode.EDIT) {
					contractorCtrl.updateCompanyName(contractor, companyName);
				} else if (mode == Mode.CREATE) {

					this.contractor = contractorCtrl.createContractor(companyName);
				}
				
			}
			// Dispose of the window
			this.dispose();
		});
	}
}

