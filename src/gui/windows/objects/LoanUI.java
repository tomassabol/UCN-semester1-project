package gui.windows.objects;

import java.awt.Component;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.AuthenticationController;
import controller.LoanController;
import controller.StockController;
import exceptions.OutOfStockException;
import gui.Common;
import gui.Messages;
import gui.windows.ChooseProduct;
import model.Customer;
import model.Employee;
import model.Loan;
import model.PrimaryKey;
import model.Product;

import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import javax.swing.JTextField;
import java.awt.Insets;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

import javax.swing.JButton;
import javax.swing.JTextArea;

public class LoanUI extends JDialog {
	
	public enum Mode {
		VIEW,
		EDIT,
		CREATE,
		RETURN
	}

	private JPanel contentPane;
	private JTextField txtID;
	private JTextField txtCreationDate;
	private JButton btnSubmit;
	
	Loan loan;
	Mode mode;
	LoanController loanCtrl;
	StockController stockCtrl;
	AuthenticationController auth;
	Customer customer;
	Employee employee;
	Product product;

	private JLabel lblReturnDate;
	private JTextField txtReturnDate;
	private JLabel lblProduct;
	private JTextField txtProduct;
	private JButton btnChooseProduct;
	private JLabel lblPrice;


	/**
	 * constructr for Create shelf
	 *
	 * @param auth the auth
	 * @param customer - the customer
	 * @param mode - the mode
	 */
	public LoanUI(AuthenticationController auth, Customer customer, Mode mode) {
		this(auth, null, customer, Mode.CREATE);
		this.loan = null;
	}

	/**
	 * Constructor for View/Edit/Return loan
	 * 
	 * @param auth the auth
	 * @param loan the loan
	 * @param customer  the customer
	 * @param mode the mode
	 */
	public LoanUI(AuthenticationController auth, Loan loan, Customer customer, Mode mode) {
		this.mode = mode;
		this.loan = loan;
		this.auth = auth;

		this.customer = customer;

        loanCtrl = new LoanController();
		stockCtrl = new StockController();


        setModal(true);
		setBounds(100, 100, 450, 341);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[]{273, 0, 0};
		gbl_contentPanel.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_contentPanel.columnWeights = new double[]{1.0, 0.0, Double.MIN_VALUE};
		gbl_contentPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 1.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPanel);
		
		JLabel lblID = new JLabel("ID");
		GridBagConstraints gbc_lblID = new GridBagConstraints();
		gbc_lblID.anchor = GridBagConstraints.SOUTHWEST;
		gbc_lblID.insets = new Insets(0, 0, 5, 5);
		gbc_lblID.gridx = 0;
		gbc_lblID.gridy = 0;
		contentPane.add(lblID, gbc_lblID);
		
		
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
		
		
		JLabel lblCreationDate = new JLabel("Creation date");
		GridBagConstraints gbc_lblCreationDate = new GridBagConstraints();
		gbc_lblCreationDate.anchor = GridBagConstraints.SOUTHWEST;
		gbc_lblCreationDate.insets = new Insets(0, 0, 5, 5);
		gbc_lblCreationDate.gridx = 0;
		gbc_lblCreationDate.gridy = 2;
		contentPane.add(lblCreationDate, gbc_lblCreationDate);
		
		
		txtCreationDate = new JTextField();
		txtCreationDate.setEditable(false);
		GridBagConstraints gbc_txtCreationDate = new GridBagConstraints();
		gbc_txtCreationDate.anchor = GridBagConstraints.NORTH;
		gbc_txtCreationDate.insets = new Insets(0, 0, 5, 5);
		gbc_txtCreationDate.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtCreationDate.gridx = 0;
		gbc_txtCreationDate.gridy = 3;
		contentPane.add(txtCreationDate, gbc_txtCreationDate);
		txtCreationDate.setColumns(10);
		
		lblReturnDate = new JLabel("Return date");
		GridBagConstraints gbc_lblReturnDate = new GridBagConstraints();
		gbc_lblReturnDate.anchor = GridBagConstraints.SOUTHWEST;
		gbc_lblReturnDate.insets = new Insets(0, 0, 5, 5);
		gbc_lblReturnDate.gridx = 0;
		gbc_lblReturnDate.gridy = 4;
		contentPane.add(lblReturnDate, gbc_lblReturnDate);
		
		txtReturnDate = new JTextField();
		txtReturnDate.setColumns(10);
		GridBagConstraints gbc_txtReturnDate = new GridBagConstraints();
		gbc_txtReturnDate.insets = new Insets(0, 0, 5, 5);
		gbc_txtReturnDate.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtReturnDate.gridx = 0;
		gbc_txtReturnDate.gridy = 5;
		contentPane.add(txtReturnDate, gbc_txtReturnDate);
		
		lblProduct = new JLabel("Product");
		GridBagConstraints gbc_lblProduct = new GridBagConstraints();
		gbc_lblProduct.anchor = GridBagConstraints.WEST;
		gbc_lblProduct.insets = new Insets(0, 0, 5, 5);
		gbc_lblProduct.gridx = 0;
		gbc_lblProduct.gridy = 6;
		contentPane.add(lblProduct, gbc_lblProduct);
		
		txtProduct = new JTextField();
		txtProduct.setEditable(false);
		txtProduct.setColumns(10);
		GridBagConstraints gbc_txtProduct = new GridBagConstraints();
		gbc_txtProduct.insets = new Insets(0, 0, 5, 5);
		gbc_txtProduct.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtProduct.gridx = 0;
		gbc_txtProduct.gridy = 7;
		contentPane.add(txtProduct, gbc_txtProduct);
		
		btnChooseProduct = new JButton("Choose");
		GridBagConstraints gbc_btnChooseProduct = new GridBagConstraints();
		gbc_btnChooseProduct.insets = new Insets(0, 0, 5, 0);
		gbc_btnChooseProduct.gridx = 1;
		gbc_btnChooseProduct.gridy = 7;
		contentPane.add(btnChooseProduct, gbc_btnChooseProduct);
		
		lblPrice = new JLabel("Price: ");
		GridBagConstraints gbc_lblPrice = new GridBagConstraints();
		gbc_lblPrice.anchor = GridBagConstraints.SOUTHWEST;
		gbc_lblPrice.insets = new Insets(0, 0, 0, 5);
		gbc_lblPrice.gridx = 0;
		gbc_lblPrice.gridy = 9;
		contentPane.add(lblPrice, gbc_lblPrice);
		
		
		btnSubmit = new JButton("OK");
		GridBagConstraints gbc_btnOK = new GridBagConstraints();
		gbc_btnOK.anchor = GridBagConstraints.SOUTHEAST;
		gbc_btnOK.gridx = 1;
		gbc_btnOK.gridy = 9;
		contentPane.add(btnSubmit, gbc_btnOK);
		
		switch (mode) {
			case VIEW:
				// Set title
				setTitle("View Loan - " + loan.getID());
				// Hide 'Update' button if in view mode
				btnSubmit.setVisible(false);
				// Disable fields
				this.disableFields();
				// disable choose buttons
				btnChooseProduct.setEnabled(false);
				// Fill fields with content
				this.fillFields(loan);
				// fill label with total price
				lblPrice.setText(String.format("Total price: %.2f DKK", loanCtrl.totalPrice(loan)));
				break;
			case EDIT: 
				// Set title
				setTitle("Edit Loan - " + loan.getID());
				// Enable fields for editing
				this.enableFields();
				// enable choose buttons
				btnChooseProduct.setEnabled(false);
				// Fill fields with content
				this.fillFields(loan);
				// fill label with total price
				lblPrice.setText(String.format("Total price: %.2f DKK", loanCtrl.totalPrice(loan)));
				break;
			case CREATE:
				// Set title
				setTitle("Create new Loan");
				// Change button text
				btnSubmit.setText("Create");
				// enable choose buttons
				btnChooseProduct.setEnabled(true);
				// Enable fields for editing
				this.enableFields();
				// fill label with total price
				txtID.setText(String.valueOf(PrimaryKey.peekID(PrimaryKey.Keys.LOAN)));
				break;
			case RETURN:
				// set title
				setTitle("Return Loan");
				// change button text
				btnSubmit.setText("Return");
				// disable buttons
				btnChooseProduct.setEnabled(false);
				// fill fields
				this.fillFields(loan);
				// fill label with total price
				lblPrice.setText(String.format("Total price: %.2f DKK", loanCtrl.totalPrice(loan)));
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
            btnChooseProduct.setEnabled(false);
	}
	
	
	// Makes the text fields editable except ID
	private void enableFields() {
		for (Component c : this.getContentPane().getComponents()) {
			   if (c instanceof JTextField || c instanceof JTextArea) {
			      c.setEnabled(true);
			   }
			}
		// disable ID field, as it is assigned by a class PrimaryKey
		txtID.setEnabled(false);
		// enable choose product button
        btnChooseProduct.setEnabled(true);
	}

	// FIll in the fields
	private void fillFields(Loan loan) {
		txtID.setText(String.valueOf(loan.ID));
        txtCreationDate.setText(Common.datetimeToString(loan.getCreationDate()));
        txtReturnDate.setText(Common.dateToString(loan.getReturnDate()));
		txtProduct.setText(loan.getProduct().getName());
	}

	/**
	 * @return loan
	 */
	public Loan getLoan() {
		return this.loan;
	}
	
	/*
	 * *******************************************************
	 * *******************  EVENT HANDLERS *******************
	 * *******************************************************
	 */
	private void addEventHandlers() {

		// "choose product" button: choose the loanable product
		btnChooseProduct.addActionListener(e -> {
			ChooseProduct frame = new ChooseProduct(auth, ChooseProduct.Mode.LOANABLE);
			frame.setVisible(true);
			if (frame.getSelectedProduct() != null) {
				this.product = frame.getSelectedProduct();
				txtProduct.setText(product.getName());
			}
		});
		
		// 'update' button: Update the customer
		btnSubmit.addActionListener(e -> {
			String message = "";
			if (mode == Mode.EDIT) {
				message = "Are you sure you want to update the loan's details?";
			} else if (mode == Mode.CREATE) {
				message = "Create loan?";
			} else if (mode == Mode.RETURN) {
				message = "Are you sure you want to return this loan?";
			}
			if (Messages.confirm(LoanUI.this, message)) {
				
				// Validate that shelf name is not empty
				String productText = txtProduct.getText().strip();
				if (productText.isEmpty()) {
					Messages.error(this, "Product field cannot be empty. Choose product");
					return;
				}

				// Validate return date
				String returnDateString = txtReturnDate.getText().strip();
				if (returnDateString.isEmpty()) {
					Messages.error(this, "Return Date cannot be empty!");
					return;
				}
				// Parse return date
				LocalDate rDate;
				try {
					rDate = Common.stringToDate(returnDateString);
				} catch (DateTimeParseException e1) {
					Messages.error(this, "Please enter a valid return date in the format of: " + Common.getDateFormat());
					return;
				}

				// Parse birth date
				LocalDate returnDate;
				LocalDateTime creationDate;
				try {
					returnDate = Common.stringToDate(txtReturnDate.getText());
					creationDate = Common.stringToDateTime(txtCreationDate.getText());
				} catch (DateTimeParseException e1) {
					Messages.error(this, "Please enter a valid return date in the format of: " + Common.getDateFormat());
					return;
				}
				LocalDate startDate = creationDate.toLocalDate();
				if (loanCtrl.validateReturnDate(startDate, returnDate) == false) {
					Messages.error(this, "Please enter a valid return date in the format of: " + Common.getDateFormat());
					return;
				} 
				
				// if mode == view, update data
				if (mode == Mode.EDIT) {
					// if mode == update, update loan (you can only update return date)
					loanCtrl.updateReturnDate(loan, returnDate);
				} else if (mode == Mode.CREATE) {
					// if mode == Create, create a new loan
					try {
						this.loan = loanCtrl.createLoan(customer, auth.getLoggedInUser(), product, rDate);
					} catch (OutOfStockException e1) {
						Messages.error(this, "Could not create loan. Selected product is out of stock");
					}
					stockCtrl.removeLoanableFromStock(product);
				} else if (mode == Mode.RETURN) {
					// if mode == return, return a loan
					loan.returnLoan();
					stockCtrl.addLoanableToStock(loan.getProduct());
				}
			}
			// Dispose of the window
			this.dispose();
		});
     
	}
}

