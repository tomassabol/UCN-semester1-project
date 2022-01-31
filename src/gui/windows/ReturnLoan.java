package gui.windows;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.AuthenticationController;
import controller.LoanController;
import controller.SupplyController;
import exception.IllegalModificationException;
import gui.Common;
import gui.JButtonPrimary;
import gui.Messages;
import gui.windows.ChooseShelf;
import models.Loan;
import models.Shelf;
import models.SupplyOrder;
import models.TrackableItem;

import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import javax.swing.JTextField;
import java.awt.Insets;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

import javax.swing.JButton;
import javax.swing.JRadioButton;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;

/**
 * @author Daniels Kanepe
 *
 */
public class ReturnLoan extends JDialog {

    private JPanel contentPane;
	private JButton btnSubmit;

	AuthenticationController auth;
    Loan loan;
    LoanController loanCtrl;
	private JLabel lblShelf;
	private JPanel shelfPanel;
	private JTextField txtShelfDisplay;
	private JButton btnChooseShelf;
	
    Shelf shelf = null;
    private JLabel lblDeliveryDate;
    private JTextField txtDeliveryDate;
    private JButton btnAutofill;
    private JLabel lblLoan;
    private JLabel lblCustomer;
    private JTextField txtCustomer;
    private JTextField txtLoan;

	/**
	 * @param auth the auth
	 * @param loan the loan
     * @wbp.parser.constructor
	 */
	public ReturnLoan(AuthenticationController auth, Loan loan) {
		this.loan = loan;
		this.auth = auth;

        loanCtrl = new LoanController();
		
		setModal(true);
		setBounds(100, 100, 600, 450);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[]{273, 0, 0};
		gbl_contentPanel.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_contentPanel.columnWeights = new double[]{1.0, 0.0, Double.MIN_VALUE};
		gbl_contentPanel.rowWeights = new double[]{1.0, 0.0, 0.0, 0.0, 0.0, 1.0, 1.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPanel);
		
		JLabel lblTitle = new JLabel("Return Loan");
		GridBagConstraints gbc_lblTitle = new GridBagConstraints();
		gbc_lblTitle.gridwidth = 2;
		gbc_lblTitle.anchor = GridBagConstraints.SOUTH;
		gbc_lblTitle.insets = new Insets(0, 0, 5, 0);
		gbc_lblTitle.gridx = 0;
		gbc_lblTitle.gridy = 0;
		contentPane.add(lblTitle, gbc_lblTitle);
		
		lblLoan = new JLabel("Loan");
		GridBagConstraints gbc_lblLoan = new GridBagConstraints();
		gbc_lblLoan.anchor = GridBagConstraints.SOUTHWEST;
		gbc_lblLoan.insets = new Insets(0, 0, 5, 5);
		gbc_lblLoan.gridx = 0;
		gbc_lblLoan.gridy = 1;
		contentPane.add(lblLoan, gbc_lblLoan);
		
		txtLoan = new JTextField();
		txtLoan.setEditable(false);
		GridBagConstraints gbc_txtLoan = new GridBagConstraints();
		gbc_txtLoan.gridwidth = 2;
		gbc_txtLoan.insets = new Insets(0, 0, 5, 0);
		gbc_txtLoan.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtLoan.gridx = 0;
		gbc_txtLoan.gridy = 2;
		contentPane.add(txtLoan, gbc_txtLoan);
		txtLoan.setColumns(10);
		
		lblCustomer = new JLabel("Customer");
		GridBagConstraints gbc_lblCustomer = new GridBagConstraints();
		gbc_lblCustomer.anchor = GridBagConstraints.SOUTHWEST;
		gbc_lblCustomer.gridwidth = 2;
		gbc_lblCustomer.insets = new Insets(0, 0, 5, 5);
		gbc_lblCustomer.gridx = 0;
		gbc_lblCustomer.gridy = 3;
		contentPane.add(lblCustomer, gbc_lblCustomer);
		
		txtCustomer = new JTextField();
		txtCustomer.setEditable(false);
		GridBagConstraints gbc_txtCustomer = new GridBagConstraints();
		gbc_txtCustomer.gridwidth = 2;
		gbc_txtCustomer.insets = new Insets(0, 0, 5, 0);
		gbc_txtCustomer.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtCustomer.gridx = 0;
		gbc_txtCustomer.gridy = 4;
		contentPane.add(txtCustomer, gbc_txtCustomer);
		txtCustomer.setColumns(10);
		
		lblShelf = new JLabel("Shelf (to put back) *");
		GridBagConstraints gbc_lblShelf = new GridBagConstraints();
		gbc_lblShelf.gridwidth = 2;
		gbc_lblShelf.anchor = GridBagConstraints.SOUTHWEST;
		gbc_lblShelf.insets = new Insets(0, 0, 5, 0);
		gbc_lblShelf.gridx = 0;
		gbc_lblShelf.gridy = 5;
		contentPane.add(lblShelf, gbc_lblShelf);
		
		shelfPanel = new JPanel();
		GridBagConstraints gbc_shelfPanel = new GridBagConstraints();
		gbc_shelfPanel.anchor = GridBagConstraints.NORTH;
		gbc_shelfPanel.gridwidth = 2;
		gbc_shelfPanel.insets = new Insets(0, 0, 5, 0);
		gbc_shelfPanel.fill = GridBagConstraints.HORIZONTAL;
		gbc_shelfPanel.gridx = 0;
		gbc_shelfPanel.gridy = 6;
		contentPane.add(shelfPanel, gbc_shelfPanel);
		shelfPanel.setLayout(new BoxLayout(shelfPanel, BoxLayout.X_AXIS));
		
		txtShelfDisplay = new JTextField();
		txtShelfDisplay.setEnabled(false);
		txtShelfDisplay.setEditable(false);
		txtShelfDisplay.setColumns(10);
		shelfPanel.add(txtShelfDisplay);
		
		btnChooseShelf = new JButton("Choose");
		shelfPanel.add(btnChooseShelf);
		
		lblDeliveryDate = new JLabel("Return date (" + Common.getDateTimeFormat() + ") *");
		GridBagConstraints gbc_lblDeliveryDate = new GridBagConstraints();
		gbc_lblDeliveryDate.anchor = GridBagConstraints.SOUTHWEST;
		gbc_lblDeliveryDate.insets = new Insets(0, 0, 5, 5);
		gbc_lblDeliveryDate.gridx = 0;
		gbc_lblDeliveryDate.gridy = 7;
		contentPane.add(lblDeliveryDate, gbc_lblDeliveryDate);
		
		txtDeliveryDate = new JTextField();
		GridBagConstraints gbc_txtDeliveryDate = new GridBagConstraints();
		gbc_txtDeliveryDate.anchor = GridBagConstraints.NORTH;
		gbc_txtDeliveryDate.insets = new Insets(0, 0, 5, 5);
		gbc_txtDeliveryDate.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtDeliveryDate.gridx = 0;
		gbc_txtDeliveryDate.gridy = 8;
		contentPane.add(txtDeliveryDate, gbc_txtDeliveryDate);
		txtDeliveryDate.setColumns(10);
		
		btnAutofill = new JButton("Auto fill");
		GridBagConstraints gbc_btnAutofill = new GridBagConstraints();
		gbc_btnAutofill.anchor = GridBagConstraints.NORTH;
		gbc_btnAutofill.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnAutofill.insets = new Insets(0, 0, 5, 0);
		gbc_btnAutofill.gridx = 1;
		gbc_btnAutofill.gridy = 8;
		contentPane.add(btnAutofill, gbc_btnAutofill);
		
		
		btnSubmit = new JButtonPrimary("Put Into Stock");
		GridBagConstraints gbc_btnOK = new GridBagConstraints();
		gbc_btnOK.anchor = GridBagConstraints.SOUTHEAST;
		gbc_btnOK.gridx = 1;
		gbc_btnOK.gridy = 9;
		contentPane.add(btnSubmit, gbc_btnOK);
		
		addEventHandlers();
	
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
		
		// Choose shelf button
		btnChooseShelf.addActionListener(e -> {
			ChooseShelf frame = new ChooseShelf(auth);
			frame.setVisible(true);
			if (frame.getSelectedShelf() != null) {
				this.shelf = frame.getSelectedShelf();
				txtShelfDisplay.setText(shelf.getName());
			}
		});
		
		// Autofill delivery date button
		btnAutofill.addActionListener(e -> {
			txtDeliveryDate.setText(Common.datetimeToString(LocalDateTime.now()));
		});
		
		// 'Submit' button - return loan
		btnSubmit.addActionListener(e -> {
			
			// Validate shelf: a shelf has been chosen
			if (this.shelf == null) {
				Messages.error(this, "Please choose a shelf to put the delivered items into");
				return;
			}
			
			// Validate return date: not empty
			String returnDateString = txtDeliveryDate.getText().strip();
			if (returnDateString.isEmpty()) {
				Messages.error(this, "Return date cannot be empty!");
				return;
			}
			// Parse return date
			LocalDateTime returnDate;
			try {
				returnDate = Common.stringToDateTime(returnDateString);
			} catch (DateTimeParseException e1) {
				Messages.error(this, "Please enter a return date in the format of: " + Common.getDateTimeFormat());
				return;
			}
			// Validate Return date: not < order date
			if (returnDate.isBefore(this.loan.getCreationDate())) {
				Messages.error(this, "Return date cannot be before order date!");
			}

			// Overdue? - ask for extra payment
			String msg;
			if (returnDate.isAfter(loan.getProposedReturnDate())) {
				BigDecimal fees = loanCtrl.getOverduePrice(loan, returnDate);
				msg = String.format("The customer has to pay an extra %.2f DKK for this return. Return loan?",
						fees);
			} else {
				msg = "There is nothing extra to pay. Return loan?";
			}
			
			if (Messages.confirm(ReturnLoan.this, msg)) {
				
				loanCtrl.returnLoan(loan, returnDate, shelf);
				
				// Dispose of the window
				this.dispose();

			}
		});
        
	}
}

