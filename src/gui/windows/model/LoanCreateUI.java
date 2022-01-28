package gui.windows.model;

import java.awt.Component;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.AuthenticationController;
import controller.LoanController;
import controller.StockController;
import exception.OutOfStockException;
import gui.Common;
import gui.Messages;
import gui.window.ChooseProduct;
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
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

import javax.swing.JButton;
import javax.swing.JTextArea;

/**
 * @author Daniels Kanepe
 *
 */
public class LoanCreateUI extends JDialog {

	private JPanel contentPane;
	private JTextField txtID;
	private JButton btnSubmit;

	Loan loan;
	LoanController loanCtrl;
	StockController stockCtrl;
	AuthenticationController auth;
	Customer customer;
	Employee employee;
	Product product;

	private JLabel lblProposedReturnDate;
	private JTextField txtProposedReturnDate;
	private JLabel lblProduct;
	private JTextField txtProductDisplay;
	private JButton btnChooseProduct;

	/**
	 * Constructor
	 * 
	 * @param auth the auth
	 * @param loan the loan
	 * @param customer  the customer
	 * @param mode the mode
	 * @wbp.parser.constructor
	 */
	public LoanCreateUI(AuthenticationController auth, Customer customer) {
		this.loan = null;
		this.auth = auth;
		this.customer = customer;

		loanCtrl = new LoanController();
		stockCtrl = new StockController();

		setTitle("Create New Loan");

		setModal(true);
		setBounds(100, 100, 450, 341);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[] {
			273, 0, 0
		};
		gbl_contentPanel.rowHeights = new int[] {
			0, 0, 0, 0, 0, 0, 0, 0
		};
		gbl_contentPanel.columnWeights = new double[] {
			1.0, 0.0, Double.MIN_VALUE
		};
		gbl_contentPanel.rowWeights = new double[] {
			1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, Double.MIN_VALUE
		};
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

		lblProposedReturnDate = new JLabel("Return date (" + Common.getDateTimeFormat() + " )");
		GridBagConstraints gbc_lblProposedReturnDate = new GridBagConstraints();
		gbc_lblProposedReturnDate.anchor = GridBagConstraints.SOUTHWEST;
		gbc_lblProposedReturnDate.insets = new Insets(0, 0, 5, 5);
		gbc_lblProposedReturnDate.gridx = 0;
		gbc_lblProposedReturnDate.gridy = 2;
		contentPane.add(lblProposedReturnDate, gbc_lblProposedReturnDate);

		txtProposedReturnDate = new JTextField();
		txtProposedReturnDate.setColumns(10);
		GridBagConstraints gbc_txtProposedReturnDate = new GridBagConstraints();
		gbc_txtProposedReturnDate.insets = new Insets(0, 0, 5, 5);
		gbc_txtProposedReturnDate.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtProposedReturnDate.gridx = 0;
		gbc_txtProposedReturnDate.gridy = 3;
		contentPane.add(txtProposedReturnDate, gbc_txtProposedReturnDate);

		lblProduct = new JLabel("Product");
		GridBagConstraints gbc_lblProduct = new GridBagConstraints();
		gbc_lblProduct.anchor = GridBagConstraints.WEST;
		gbc_lblProduct.insets = new Insets(0, 0, 5, 5);
		gbc_lblProduct.gridx = 0;
		gbc_lblProduct.gridy = 4;
		contentPane.add(lblProduct, gbc_lblProduct);

		txtProductDisplay = new JTextField();
		txtProductDisplay.setEditable(false);
		txtProductDisplay.setColumns(10);
		GridBagConstraints gbc_txtProductDisplay = new GridBagConstraints();
		gbc_txtProductDisplay.insets = new Insets(0, 0, 5, 5);
		gbc_txtProductDisplay.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtProductDisplay.gridx = 0;
		gbc_txtProductDisplay.gridy = 5;
		contentPane.add(txtProductDisplay, gbc_txtProductDisplay);

		btnChooseProduct = new JButton("Choose");
		GridBagConstraints gbc_btnChooseProduct = new GridBagConstraints();
		gbc_btnChooseProduct.insets = new Insets(0, 0, 5, 0);
		gbc_btnChooseProduct.gridx = 1;
		gbc_btnChooseProduct.gridy = 5;
		contentPane.add(btnChooseProduct, gbc_btnChooseProduct);

		btnSubmit = new JButton("Create");
		GridBagConstraints gbc_btnOK = new GridBagConstraints();
		gbc_btnOK.anchor = GridBagConstraints.SOUTHEAST;
		gbc_btnOK.gridx = 1;
		gbc_btnOK.gridy = 6;
		contentPane.add(btnSubmit, gbc_btnOK);

		addEventHandlers();

	}

	/*
	 * *******************************************************
	 * *******************  Methods *******************
	 * *******************************************************
	 */

	/**
	 * Get created loan, or null if not created
	 * 
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

		// "choose product" button: choose a loanable product
		btnChooseProduct.addActionListener(e -> {
			ChooseProduct frame = new ChooseProduct(auth, ChooseProduct.Mode.LOANABLE);
			frame.setVisible(true);
			if (frame.getSelectedProduct() != null) {
				this.product = frame.getSelectedProduct();
				txtProductDisplay.setText(String.format("(%s) %s",
					this.product.ID,
					this.product.getName()));
			}
		});

		// 'Create' button: Create the loan
		btnSubmit.addActionListener(e -> {

				// Validation: product selected?
				if (product == null) {
					Messages.error(this, "Please choose a product!");
					return;
				}

				// Validate: proposed return date
				String returnDateString = txtProposedReturnDate.getText().strip();
				if (returnDateString.isEmpty()) {
					Messages.error(this, "Please fill out the proposed return date!");
					return;
				}
				// Parse proposed return date
				LocalDateTime proposedReturnDate;
				try {
					proposedReturnDate = Common.stringToDateTime(returnDateString);
				} catch (DateTimeParseException e1) {
					Messages.error(this, "Please enter a valid proposed return date in the format of: " + Common.getDateTimeFormat());
					return;
				}
				
				// Confirmation message
				BigDecimal price = loanCtrl.getPrice(product, customer, LocalDateTime.now(), proposedReturnDate);
				String msg = String.format("The loan will cost %.2f DKK. Create loan?", price);
				if (Messages.confirm(LoanCreateUI.this, msg)) {

					try {
						this.loan = loanCtrl.createLoan(customer, auth.getLoggedInUser(), product, proposedReturnDate);
					} catch (OutOfStockException e2) {
						Messages.error(this, "Could not create loan. Selected product is out of stock");
					}
					
					// Dispose of the window
					this.dispose();
				}

			});
			
	}
}