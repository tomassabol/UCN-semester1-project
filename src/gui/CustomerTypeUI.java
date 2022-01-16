package gui;

import java.awt.Component;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.AuthenticationController;
import controller.CustomerController;
import controller.ProductController;
import model.CustomerType;
import model.Product;

import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import javax.swing.JTextField;
import java.awt.Insets;
import javax.swing.JButton;
import javax.swing.JTextArea;
import java.math.BigDecimal;
import java.util.Objects;

/**
 * @author Daniels Kanepe
 *
 */
public class CustomerTypeUI extends JDialog {
	
	public enum Mode {
		VIEW,
		EDIT,
		CREATE
	}

	private JPanel contentPane;
	private JTextField txtId;
	private JTextField txtName;
	private JButton btnSubmit;
	private CustomerController customerCtrl;
	private Mode mode;
	private AuthenticationController auth;
	private CustomerType customerType;

	/**
	 * Constructor: create new product
	 *
	 * @param auth the auth controller 
	 */
	public CustomerTypeUI(AuthenticationController auth) {
		this(auth, null, Mode.CREATE);
	}
	
	public CustomerTypeUI(AuthenticationController auth, CustomerType customerType, Mode mode) {
		this.auth = auth;
		this.mode = mode;
		this.customerType = customerType;
		
		customerCtrl = new CustomerController();
		
		setModal(true);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 330);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{208, 208, 0};
		gbl_contentPane.rowHeights = new int[]{19, 0, 0, 0};
		gbl_contentPane.columnWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 1.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		JLabel lblId = new JLabel("Id");
		GridBagConstraints gbc_lblId = new GridBagConstraints();
		gbc_lblId.anchor = GridBagConstraints.WEST;
		gbc_lblId.insets = new Insets(0, 0, 5, 5);
		gbc_lblId.gridx = 0;
		gbc_lblId.gridy = 0;
		contentPane.add(lblId, gbc_lblId);
		
		txtId = new JTextField();
		txtId.setColumns(10);
		GridBagConstraints gbc_txtId = new GridBagConstraints();
		gbc_txtId.anchor = GridBagConstraints.WEST;
		gbc_txtId.insets = new Insets(0, 0, 5, 5);
		gbc_txtId.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtId.gridx = 0;
		gbc_txtId.gridy = 1;
		contentPane.add(txtId, gbc_txtId);
		
		JLabel lblName = new JLabel("Customer Type name");
		GridBagConstraints gbc_lblName = new GridBagConstraints();
		gbc_lblName.anchor = GridBagConstraints.WEST;
		gbc_lblName.insets = new Insets(0, 0, 5, 0);
		gbc_lblName.gridx = 1;
		gbc_lblName.gridy = 0;
		contentPane.add(lblName, gbc_lblName);
		
		txtName = new JTextField();
		txtName.setColumns(10);
		GridBagConstraints gbc_txtName = new GridBagConstraints();
		gbc_txtName.anchor = GridBagConstraints.WEST;
		gbc_txtName.insets = new Insets(0, 0, 5, 0);
		gbc_txtName.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtName.gridx = 1;
		gbc_txtName.gridy = 1;
		contentPane.add(txtName, gbc_txtName);
		
		
		btnSubmit = new JButton("Submit");
		GridBagConstraints gbc_btnOk = new GridBagConstraints();
		gbc_btnOk.anchor = GridBagConstraints.SOUTHEAST;
		gbc_btnOk.gridx = 1;
		gbc_btnOk.gridy = 2;
		contentPane.add(btnSubmit, gbc_btnOk);
		
		
		switch (mode) {
			case VIEW:
				// Set title
				setTitle("View customer type - " + customerType.getName());
				// Hide 'Update' button if in view mode
				btnSubmit.setVisible(false);
				// Disable fields
				this.disableFields();
				// Fill fields with content
				this.fillFields(customerType);
				break;
			case EDIT: 
				// Set title
				setTitle("Edit customer type");
				// Change submit button text to 'Update'
				btnSubmit.setText("Update");
				// Enable fields for editing
				this.enableFields();
				// Fill fields with content
				this.fillFields(customerType);
				break;
			case CREATE:
				// Set title
				setTitle("Add new customer type");
				// Change submit button text to 'Create'
				btnSubmit.setText("Create");
				// Enable fields
				this.enableFields();
		}
		
		
		
		addEventHandlers();
	}
	
	/*
	 * *******************************************************
	 * *******************  Methods *******************
	 * *******************************************************
	 */
	
	/**
	 * Gets the product.
	 * Useful for Create mode (to get the created product)
	 *
	 * @return the product
	 */
	public CustomerType getProduct() {
		return this.customerType;
	}
	
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
		txtId.setEnabled(false);
	}
	
	// FIll in the fields
	private void fillFields(CustomerType customerType) {
		txtId.setText(String.valueOf(customerType.ID));
		txtName.setText(customerType.getName());
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
				message = "Are you sure you want to update the changes to product?";
			} else if (mode == Mode.CREATE) {
				message = "Create product?";
			}
			if (Messages.confirm(CustomerTypeUI.this, message)) {
				
				// Validate customer type name
				String name = txtName.getText().strip();
				if (name.isEmpty()) {
					Messages.error(this, "Customer type name cannot be empty!");
					return;
				}
				
				
				// if mode == view, update data
				if (mode == Mode.EDIT) {
					
					customerCtrl.updateCustomerTypeName(customerType, name);
				} else if (mode == Mode.CREATE) {
					// if mode == Create, create a new product
					customerCtrl.createCustomerType(name, 0);
				}


				
			}
			// Dispose of the window
			this.dispose();
		});
	}
}
