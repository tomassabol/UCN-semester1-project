package gui;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.CustomerController;
import model.CustomerType;
import model.PrimaryKey;

import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import javax.swing.JTextField;
import java.awt.Insets;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import javax.swing.JButton;

/**
 * @author Daniels Kanepe
 *
 */
public class AddCustomerUI extends JDialog {

	private JPanel contentPane;
	private JTextField txtID;
	private JTextField txtFirstName;
	private JTextField txtLastName;
	private JTextField txtAddress;
	private JTextField txtPhone;
	private JTextField txtBirth;
	private JButton btnOk;
	private JPanel typePanel;
	private JTextField txtType;
	private JButton btnChooseType;
	
	CustomerType customerType;
	CustomerController customerCtrl;



	/**
	 * Create the dialog.
	 */
	public AddCustomerUI() {

		customerCtrl = new CustomerController();
		
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
		
		
		JLabel lblFirstName = new JLabel("First Name *");
		GridBagConstraints gbc_lblFirstName = new GridBagConstraints();
		gbc_lblFirstName.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblFirstName.insets = new Insets(0, 0, 5, 0);
		gbc_lblFirstName.gridx = 1;
		gbc_lblFirstName.gridy = 0;
		contentPane.add(lblFirstName, gbc_lblFirstName);
		
		
		txtID = new JTextField();
		txtID.setEnabled(false);
		GridBagConstraints gbc_txtID = new GridBagConstraints();
		gbc_txtID.insets = new Insets(0, 0, 5, 5);
		gbc_txtID.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtID.gridx = 0;
		gbc_txtID.gridy = 1;
		contentPane.add(txtID, gbc_txtID);
		txtID.setColumns(10);
		
		
		txtFirstName = new JTextField();
		GridBagConstraints gbc_txtFirstName = new GridBagConstraints();
		gbc_txtFirstName.insets = new Insets(0, 0, 5, 0);
		gbc_txtFirstName.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtFirstName.gridx = 1;
		gbc_txtFirstName.gridy = 1;
		contentPane.add(txtFirstName, gbc_txtFirstName);
		txtFirstName.setColumns(10);
		
		
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
		
		
		txtLastName = new JTextField();
		GridBagConstraints gbc_txtLastName = new GridBagConstraints();
		gbc_txtLastName.insets = new Insets(0, 0, 5, 5);
		gbc_txtLastName.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtLastName.gridx = 0;
		gbc_txtLastName.gridy = 3;
		contentPane.add(txtLastName, gbc_txtLastName);
		txtLastName.setColumns(10);
		
		
		txtAddress = new JTextField();
		GridBagConstraints gbc_txtAddress = new GridBagConstraints();
		gbc_txtAddress.insets = new Insets(0, 0, 5, 0);
		gbc_txtAddress.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtAddress.gridx = 1;
		gbc_txtAddress.gridy = 3;
		contentPane.add(txtAddress, gbc_txtAddress);
		txtAddress.setColumns(10);
		
		
		JLabel lblPhone = new JLabel("Mobile *");
		GridBagConstraints gbc_lblPhone = new GridBagConstraints();
		gbc_lblPhone.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblPhone.insets = new Insets(0, 0, 5, 5);
		gbc_lblPhone.gridx = 0;
		gbc_lblPhone.gridy = 4;
		contentPane.add(lblPhone, gbc_lblPhone);
		
		
		JLabel lblType = new JLabel("Type *");
		GridBagConstraints gbc_lblType = new GridBagConstraints();
		gbc_lblType.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblType.insets = new Insets(0, 0, 5, 0);
		gbc_lblType.gridx = 1;
		gbc_lblType.gridy = 4;
		contentPane.add(lblType, gbc_lblType);
		
		
		txtPhone = new JTextField();
		GridBagConstraints gbc_txtPhone = new GridBagConstraints();
		gbc_txtPhone.insets = new Insets(0, 0, 5, 5);
		gbc_txtPhone.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtPhone.gridx = 0;
		gbc_txtPhone.gridy = 5;
		contentPane.add(txtPhone, gbc_txtPhone);
		txtPhone.setColumns(10);
		
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
		
		txtType = new JTextField();
		txtType.setEnabled(true);
		GridBagConstraints gbc_txtType = new GridBagConstraints();
		gbc_txtType.insets = new Insets(0, 0, 0, 5);
		gbc_txtType.fill = GridBagConstraints.BOTH;
		gbc_txtType.gridx = 0;
		gbc_txtType.gridy = 0;
		typePanel.add(txtType, gbc_txtType);
		txtType.setColumns(10);

		btnChooseType = new JButton("Choose...");
		GridBagConstraints gbc_btnChooseType = new GridBagConstraints();
		gbc_btnChooseType.fill = GridBagConstraints.BOTH;
		gbc_btnChooseType.gridx = 1;
		gbc_btnChooseType.gridy = 0;
		typePanel.add(btnChooseType, gbc_btnChooseType);
		
		
		JLabel lblBirth = new JLabel("Birth ("+ Common.getDateFormat() + ") *");
		GridBagConstraints gbc_lblBirth = new GridBagConstraints();
		gbc_lblBirth.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblBirth.insets = new Insets(0, 0, 5, 5);
		gbc_lblBirth.gridx = 0;
		gbc_lblBirth.gridy = 6;
		contentPane.add(lblBirth, gbc_lblBirth);
		
		
		txtBirth = new JTextField();
		GridBagConstraints gbc_txtBirth = new GridBagConstraints();
		gbc_txtBirth.insets = new Insets(0, 0, 0, 5);
		gbc_txtBirth.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtBirth.gridx = 0;
		gbc_txtBirth.gridy = 7;
		contentPane.add(txtBirth, gbc_txtBirth);
		txtBirth.setColumns(10);
		
		
		btnOk = new JButton("OK");
		GridBagConstraints gbc_btnOk = new GridBagConstraints();
		gbc_btnOk.anchor = GridBagConstraints.NORTHEAST;
		gbc_btnOk.gridx = 1;
		gbc_btnOk.gridy = 7;
		contentPane.add(btnOk, gbc_btnOk);

		addEventHandlers();
        txtID.setEnabled(false);
	
	}

	/*
	 * *******************************************************
	 * *******************  Methods *******************
	 * *******************************************************
	 */
	
	
	/*
	 * *******************************************************
	 * *******************  EVENT HANDLERS *******************
	 * *******************************************************
	 */
	private void addEventHandlers() {
		
		// 'update' button: Update the product
		btnOk.addActionListener(e -> {
			if (Messages.confirm(AddCustomerUI.this, "Are you sure you want to create this customer?", "Add Customer")) {
				
				// Validate First name
				String fname = txtFirstName.getText().strip();
				if (fname.isEmpty()) {
					Messages.error(this, "First Name name cannot be empty!");
					return;
				}
	
				// Validate Last name
				String lname = txtLastName.getText().strip();
				if (lname.isEmpty()) {
					Messages.error(this, "Last Name name cannot be empty!");
					return;
				}
				
				// Validate address
				String address = txtAddress.getText().strip();
				if (address.isEmpty()) {
					Messages.error(this, "Address cannot be empty!");
					return;
				}
				
				// Validate mobile
				String mobile = txtPhone.getText().strip();
				if (mobile.isEmpty()) {
					Messages.error(this, "Mobile cannot be empty!");
					return;
				}
				
				// Validate Type - no validation needed!
				
	
				// Validate Birth date
				String birthDateString = txtBirth.getText().strip();
				if (birthDateString.isEmpty()) {
					Messages.error(this, "Birth Date cannot be empty!");
					return;
				}
				// Parse birth date
				LocalDate birthDate;
				try {
					birthDate = Common.stringToDate(birthDateString);
				} catch (DateTimeParseException e1) {
					Messages.error(this, "Please enter a valid birth date in the format of: " + Common.getDateFormat());
					return;
				}
				
                // generating customer type, just so the program doesnt crash
                // needs better solution  
                CustomerType cType = new CustomerType(PrimaryKey.getNextCustomerTypeID(), "VIP", 20);
                //create customer in controller
                customerCtrl.createCustomer(fname, lname, address, mobile, cType, birthDate);
                Messages.info(this, "New customer was successfully created!");
				
			}
			// Dispose of the window
			this.dispose();
		}); 

		btnChooseType.addActionListener(e -> {
			CRUDCustomerTypePanel frame = new CRUDCustomerTypePanel();
			frame.setVisible(true);
		});
	}
}

