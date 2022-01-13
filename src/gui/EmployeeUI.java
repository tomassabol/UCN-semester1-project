package gui;

import java.awt.BorderLayout;
import java.awt.Component;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import model.IFEmployee;

import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import javax.swing.JTextField;
import java.awt.Insets;
import javax.swing.JButton;
import javax.swing.JTextArea;

public class EmployeeUI extends JDialog {
	
	public enum Mode {
		VIEW,
		EDIT
	}

	private final JPanel contentPanel = new JPanel();
	private JPanel contentPane;
	private JTextField txtID;
	private JTextField txtFirstName;
	private JTextField txtLastName;
	private JTextField txtAddress;
	private JTextField txtPhone;
	private JTextField txtEmail;
	private JTextField txtBirth;
	private JButton btnOk;



	/**
	 * Create the dialog.
	 */
	public EmployeeUI(IFEmployee employee, Mode mode) {
		setModal(true);
		setBounds(100, 100, 450, 341);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[]{0, 0, 0};
		gbl_contentPanel.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_contentPanel.columnWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
		gbl_contentPanel.rowWeights = new double[]{1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 0.0, 1.0, 1.0, Double.MIN_VALUE};
		contentPanel.setLayout(gbl_contentPanel);
		
		JLabel lblNewLabel_1 = new JLabel("ID");
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1.gridx = 0;
		gbc_lblNewLabel_1.gridy = 0;
		contentPanel.add(lblNewLabel_1, gbc_lblNewLabel_1);
		
		
		txtID = new JTextField(String.valueOf(employee.getID()));
		GridBagConstraints gbc_txtID = new GridBagConstraints();
		gbc_txtID.insets = new Insets(0, 0, 5, 5);
		gbc_txtID.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtID.gridx = 0;
		gbc_txtID.gridy = 1;
		contentPanel.add(txtID, gbc_txtID);
		txtID.setColumns(10);
		
		
		JLabel lblNewLabel_2 = new JLabel("First Name");
		GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
		gbc_lblNewLabel_2.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblNewLabel_2.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_2.gridx = 0;
		gbc_lblNewLabel_2.gridy = 2;
		contentPanel.add(lblNewLabel_2, gbc_lblNewLabel_2);
		
		
		JLabel lblNewLabel_3 = new JLabel("Last Name");
		GridBagConstraints gbc_lblNewLabel_3 = new GridBagConstraints();
		gbc_lblNewLabel_3.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblNewLabel_3.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel_3.gridx = 1;
		gbc_lblNewLabel_3.gridy = 2;
		contentPanel.add(lblNewLabel_3, gbc_lblNewLabel_3);
		
		
		txtFirstName = new JTextField(employee.getFirstName());
		GridBagConstraints gbc_txtFirstName = new GridBagConstraints();
		gbc_txtFirstName.insets = new Insets(0, 0, 5, 5);
		gbc_txtFirstName.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtFirstName.gridx = 0;
		gbc_txtFirstName.gridy = 3;
		contentPanel.add(txtFirstName, gbc_txtFirstName);
		txtFirstName.setColumns(10);
		
		
		txtLastName = new JTextField(employee.getLastName());
		GridBagConstraints gbc_txtLastName = new GridBagConstraints();
		gbc_txtLastName.insets = new Insets(0, 0, 5, 0);
		gbc_txtLastName.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtLastName.gridx = 1;
		gbc_txtLastName.gridy = 3;
		contentPanel.add(txtLastName, gbc_txtLastName);
		txtLastName.setColumns(10);
		
		
		JLabel lblNewLabel_4 = new JLabel("Address");
		GridBagConstraints gbc_lblNewLabel_4 = new GridBagConstraints();
		gbc_lblNewLabel_4.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblNewLabel_4.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_4.gridx = 0;
		gbc_lblNewLabel_4.gridy = 4;
		contentPanel.add(lblNewLabel_4, gbc_lblNewLabel_4);
		
		// missing getaddress method
		txtAddress = new JTextField();
		GridBagConstraints gbc_txtAddress = new GridBagConstraints();
		gbc_txtAddress.insets = new Insets(0, 0, 5, 5);
		gbc_txtAddress.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtAddress.gridx = 0;
		gbc_txtAddress.gridy = 5;
		contentPanel.add(txtAddress, gbc_txtAddress);
		txtAddress.setColumns(10);
		
		
		JLabel lblNewLabel_5 = new JLabel("Phone");
		GridBagConstraints gbc_lblNewLabel_5 = new GridBagConstraints();
		gbc_lblNewLabel_5.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblNewLabel_5.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_5.gridx = 0;
		gbc_lblNewLabel_5.gridy = 6;
		contentPanel.add(lblNewLabel_5, gbc_lblNewLabel_5);
		
		
		JLabel lblNewLabel_6 = new JLabel("Email");
		GridBagConstraints gbc_lblNewLabel_6 = new GridBagConstraints();
		gbc_lblNewLabel_6.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblNewLabel_6.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel_6.gridx = 1;
		gbc_lblNewLabel_6.gridy = 6;
		contentPanel.add(lblNewLabel_6, gbc_lblNewLabel_6);
		
		// missing get mobile method
		txtPhone = new JTextField();
		GridBagConstraints gbc_txtPhone = new GridBagConstraints();
		gbc_txtPhone.insets = new Insets(0, 0, 5, 5);
		gbc_txtPhone.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtPhone.gridx = 0;
		gbc_txtPhone.gridy = 7;
		contentPanel.add(txtPhone, gbc_txtPhone);
		txtPhone.setColumns(10);
		
		
		txtEmail = new JTextField(employee.getEmail());
		GridBagConstraints gbc_txtEmail = new GridBagConstraints();
		gbc_txtEmail.insets = new Insets(0, 0, 5, 0);
		gbc_txtEmail.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtEmail.gridx = 1;
		gbc_txtEmail.gridy = 7;
		contentPanel.add(txtEmail, gbc_txtEmail);
		txtEmail.setColumns(10);
		
		
		JLabel lblNewLabel_7 = new JLabel("Birth");
		GridBagConstraints gbc_lblNewLabel_7 = new GridBagConstraints();
		gbc_lblNewLabel_7.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblNewLabel_7.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_7.gridx = 0;
		gbc_lblNewLabel_7.gridy = 8;
		contentPanel.add(lblNewLabel_7, gbc_lblNewLabel_7);
		
		
		txtBirth = new JTextField(String.valueOf(employee.getBirthDate()));
		GridBagConstraints gbc_txtBirth = new GridBagConstraints();
		gbc_txtBirth.insets = new Insets(0, 0, 0, 5);
		gbc_txtBirth.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtBirth.gridx = 0;
		gbc_txtBirth.gridy = 9;
		contentPanel.add(txtBirth, gbc_txtBirth);
		txtBirth.setColumns(10);
		
		
		btnOk = new JButton("OK");
		GridBagConstraints gbc_btnOk = new GridBagConstraints();
		gbc_btnOk.anchor = GridBagConstraints.EAST;
		gbc_btnOk.gridx = 1;
		gbc_btnOk.gridy = 9;
		contentPanel.add(btnOk, gbc_btnOk);
		
		switch (mode) {
			case VIEW:
				// Set title
				setTitle("View Employee - " + employee.getFirstName() + " " + employee.getLastName());
				// Hide 'Update' button if in view mode
				btnOk.setVisible(false);
				// Disable fields
				this.disableFields();
				break;
			case EDIT: 
				// Set title
				setTitle("Edit Employee");
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
		txtID.setEnabled(false);
	}
	
		/*
	 * *******************************************************
	 * *******************  EVENT HANDLERS *******************
	 * *******************************************************
	 */
	private void addEventHandlers() {
		
		// 'update' button: Update the product
		btnOk.addActionListener(e -> {
			if (Messages.confirm(EmployeeUI.this, "Are you sure you want to update the changes to Employee?", "Update")) {
				
				// Validate First name
				String fname = txtFirstName.getText().strip();
				if (fname.isEmpty()) {
					Messages.error(this, "Employee First Name name cannot be empty!");
					return;
				}
	
				// Validate Last name
				String lname = txtFirstName.getText().strip();
				if (lname.isEmpty()) {
					Messages.error(this, "Employee Last Name name cannot be empty!");
					return;
				}
				
				// Validate address
				String address = txtAddress.getText().strip();
				if (address.isEmpty()) {
					Messages.error(this, "Employee Address cannot be empty!");
					return;
				}
				
				// Validate phone
				String phone = txtPhone.getText().strip();
				if (phone.isEmpty()) {
					Messages.error(this, "Employee Phone cannot be empty!");
					return;
				}
				
				// Validate Type
				String type = txtEmail.getText().strip();
				if (type.isEmpty()) {
					Messages.error(this, "Employee Type cannot be empty!");
					return;
				}
	
				// Validate Birthdate
				String birthdate = txtBirth.getText().strip();
				if (birthdate.isEmpty()) {
					Messages.error(this, "Employee Birth cannot be empty!");
					return;
				}
				
			}
			// Dispose of the window
			this.dispose();
		});
	}
}

