package gui;

import java.awt.EventQueue;

import javax.swing.JDialog;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.Component;
import javax.swing.Box;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import java.awt.Color;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import controller.QuoteController;
import controller.ShoppingCartController;
import model.Customer;
import model.ShoppingItemLine;

import javax.swing.ListSelectionModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;

public class QuotesUI extends JDialog {
	private Customer customer;
	private JPanel contentPane;
	private JTable table;
	private QuotesTableModel tableModel;
	private JTable mainTable;
	private JButton btnViewInDetail;
	
	QuoteController quoteCtrl;
	private JLabel lblDiscountValue;
	private JLabel lblTotalValue;
	private JLabel lblSubtotalValue;
	private JTextField txtSearch;

	/**
	 * Create the dialog.
	 */
	public QuotesUI(Customer customer) {
		setTitle("Quotes");
		if (customer == null) {
			throw new IllegalArgumentException("Customer cannot be null!");
		}
		this.customer = customer;
		
		quoteCtrl = new QuoteController();
		
		setModal(true);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		// ***** TOP PANEL *****
		JPanel topPanel = new JPanel();
		getContentPane().add(topPanel, BorderLayout.NORTH);
		GridBagLayout gbl_topPanel = new GridBagLayout();
		gbl_topPanel.columnWidths = new int[]{0, 0, 0, 0};
		gbl_topPanel.rowHeights = new int[]{0, 0, 0, 0};
		gbl_topPanel.columnWeights = new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_topPanel.rowWeights = new double[]{0.0, 0.0, 1.0, Double.MIN_VALUE};
		topPanel.setLayout(gbl_topPanel);
			// ***** Title *****
			JLabel lblTitle = new JLabel(
					String.format("%s %s's quotes", 
							customer.getFirstName(), 
							customer.getLastName()
					)
			);
			GridBagConstraints gbc_lblTitle = new GridBagConstraints();
			gbc_lblTitle.gridwidth = 3;
			gbc_lblTitle.insets = new Insets(0, 0, 5, 5);
			gbc_lblTitle.gridx = 0;
			gbc_lblTitle.gridy = 0;
			topPanel.add(lblTitle, gbc_lblTitle);
			
			txtSearch = new JTextField();
			txtSearch.setToolTipText("Search");
			GridBagConstraints gbc_txtSearch = new GridBagConstraints();
			gbc_txtSearch.insets = new Insets(0, 0, 5, 5);
			gbc_txtSearch.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtSearch.gridx = 0;
			gbc_txtSearch.gridy = 1;
			topPanel.add(txtSearch, gbc_txtSearch);
			txtSearch.setColumns(10);
			
			JButton btnCreateQuote = new JButton("Create quote");
			GridBagConstraints gbc_btnCreateQuote = new GridBagConstraints();
			gbc_btnCreateQuote.insets = new Insets(0, 0, 5, 0);
			gbc_btnCreateQuote.gridx = 2;
			gbc_btnCreateQuote.gridy = 1;
			topPanel.add(btnCreateQuote, gbc_btnCreateQuote);
			
		// ***** Middle panel *****
		JPanel middlePanel = new JPanel();
		getContentPane().add(middlePanel, BorderLayout.CENTER);
		middlePanel.setLayout(new BorderLayout(0, 0));
			
			// ***** Scroll panel *****
			JScrollPane scrollPanel = new JScrollPane();
			middlePanel.add(scrollPanel);
		
				// ***** Table *****
				tableModel = new QuotesTableModel(quoteCtrl.getQuotes(customer));
				mainTable = new JTable();
				mainTable.setModel(tableModel);
				mainTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
				scrollPanel.setViewportView(mainTable);
		
		// ***** Bottom panel *****
		JPanel bottomPanel = new JPanel();
		contentPane.add(bottomPanel, BorderLayout.SOUTH);
		bottomPanel.setLayout(new BorderLayout(0, 0));
		
			// ***** Panel for table options at the bottom *****
			JPanel tableBottomOptionsPanel = new JPanel();
			bottomPanel.add(tableBottomOptionsPanel, BorderLayout.NORTH);
			GridBagLayout gbl_tableBottomOptionsPanel = new GridBagLayout();
			gbl_tableBottomOptionsPanel.columnWidths = new int[]{0, 0, 0};
			gbl_tableBottomOptionsPanel.rowHeights = new int[]{0, 0};
			gbl_tableBottomOptionsPanel.columnWeights = new double[]{1.0, 0.0, Double.MIN_VALUE};
			gbl_tableBottomOptionsPanel.rowWeights = new double[]{0.0, Double.MIN_VALUE};
			tableBottomOptionsPanel.setLayout(gbl_tableBottomOptionsPanel);
				
						// ***** Button: View *****
						btnViewInDetail = new JLink("View", JLink.COLORS.GREEN);
						btnViewInDetail.setEnabled(false);
						GridBagConstraints gbc_btnViewInDetail = new GridBagConstraints();
						gbc_btnViewInDetail.gridx = 1;
						gbc_btnViewInDetail.gridy = 0;
						tableBottomOptionsPanel.add(btnViewInDetail, gbc_btnViewInDetail);
		
			// ***** Panel for pricing and create quote button *****
			JPanel priceAndPayPanel = new JPanel();
			bottomPanel.add(priceAndPayPanel, BorderLayout.SOUTH);
			GridBagLayout gbl_priceAndPayPanel = new GridBagLayout();
			gbl_priceAndPayPanel.columnWidths = new int[]{0, 17, 0, 0, 0};
			gbl_priceAndPayPanel.rowHeights = new int[]{0, 0, 0, 0, 0};
			gbl_priceAndPayPanel.columnWeights = new double[]{0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
			gbl_priceAndPayPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
			priceAndPayPanel.setLayout(gbl_priceAndPayPanel);
		
				// ***** Subtotal label *****
				JLabel lblSubtotal = new JLabel("Subtotal:");
				GridBagConstraints gbc_lblSubtotal = new GridBagConstraints();
				gbc_lblSubtotal.anchor = GridBagConstraints.WEST;
				gbc_lblSubtotal.insets = new Insets(0, 0, 5, 5);
				gbc_lblSubtotal.gridx = 0;
				gbc_lblSubtotal.gridy = 0;
				priceAndPayPanel.add(lblSubtotal, gbc_lblSubtotal);
				
				// ***** Subtotal value *****
				lblSubtotalValue = new JLabel("N/A");
				lblSubtotalValue.setForeground(new Color(102, 102, 102));
				GridBagConstraints gbc_lblSubtotalValue = new GridBagConstraints();
				gbc_lblSubtotalValue.anchor = GridBagConstraints.WEST;
				gbc_lblSubtotalValue.insets = new Insets(0, 0, 5, 5);
				gbc_lblSubtotalValue.gridx = 2;
				gbc_lblSubtotalValue.gridy = 0;
				priceAndPayPanel.add(lblSubtotalValue, gbc_lblSubtotalValue);
				
				// ***** Customer type discount label *****
				JLabel lblDiscount = new JLabel(customer.getCustomerType().getName() + " Discount:");
				GridBagConstraints gbc_lblDiscount = new GridBagConstraints();
				gbc_lblDiscount.anchor = GridBagConstraints.WEST;
				gbc_lblDiscount.insets = new Insets(0, 0, 5, 5);
				gbc_lblDiscount.gridx = 0;
				gbc_lblDiscount.gridy = 1;
				priceAndPayPanel.add(lblDiscount, gbc_lblDiscount);
				
				// ***** Customer type discount value *****
				lblDiscountValue = new JLabel("N/A");
				lblDiscountValue.setForeground(new Color(0, 102, 0));
				GridBagConstraints gbc_lblDiscountValue = new GridBagConstraints();
				gbc_lblDiscountValue.anchor = GridBagConstraints.WEST;
				gbc_lblDiscountValue.insets = new Insets(0, 0, 5, 5);
				gbc_lblDiscountValue.gridx = 2;
				gbc_lblDiscountValue.gridy = 1;
				priceAndPayPanel.add(lblDiscountValue, gbc_lblDiscountValue);
				
				// ***** Total price label *****
				JLabel lblTotal = new JLabel("Total:");
				GridBagConstraints gbc_lblTotal = new GridBagConstraints();
				gbc_lblTotal.insets = new Insets(0, 0, 5, 5);
				gbc_lblTotal.anchor = GridBagConstraints.WEST;
				gbc_lblTotal.gridx = 0;
				gbc_lblTotal.gridy = 2;
				priceAndPayPanel.add(lblTotal, gbc_lblTotal);
				
				// ***** Total price value *****
				lblTotalValue = new JLabel("N/A");
				GridBagConstraints gbc_lblTotalValue = new GridBagConstraints();
				gbc_lblTotalValue.anchor = GridBagConstraints.WEST;
				gbc_lblTotalValue.insets = new Insets(0, 0, 5, 5);
				gbc_lblTotalValue.gridx = 2;
				gbc_lblTotalValue.gridy = 2;
				priceAndPayPanel.add(lblTotalValue, gbc_lblTotalValue);
				
				// ***** Create quote button *****
				JButton btnPay = new JButton("Pay");
				GridBagConstraints gbc_btnPay = new GridBagConstraints();
				gbc_btnPay.anchor = GridBagConstraints.EAST;
				gbc_btnPay.gridx = 3;
				gbc_btnPay.gridy = 3;
				priceAndPayPanel.add(btnPay, gbc_btnPay);
		
		// Set price
		this.setPricing();
		
		// Attach event handler
		this.addEventHandlers();
	}
	
	/*
	 * *******************************************************
	 * *******************  Methods *******************
	 * *******************************************************
	 */
	
	/**
	 * Calculate the subtotal, total price, customer type discount percentage
	 * and update view
	 */
	// TODO: REFRESH PRICING DEPENDING ON WHICH QUOTE IS SELECTED
	public void setPricing() {
		lblSubtotalValue.setText(String.format("%.2f DKK", 
				customer.getShoppingCart().calculateSubtotal()));
		lblDiscountValue.setText(String.format("%d%%",
				customer.getCustomerType().getDiscountPercentage()));
		lblTotalValue.setText(String.format("%.2f DKK", 
				customer.getShoppingCart().calculateTotal()));
	}
	
	
	/*
	 * *******************************************************
	 * *******************  EVENT HANDLERS *******************
	 * *******************************************************
	 */
	public void addEventHandlers() {
		
		// toggle bottom table buttons depending on whether a table row is selected
		mainTable.getSelectionModel().addListSelectionListener(e -> {
			if (mainTable.getSelectionModel().isSelectionEmpty()) {
				btnViewInDetail.setEnabled(false);
			} else {
				btnViewInDetail.setEnabled(true);
			}
		});
		
		// Create quote button -> redirect to shopping cart window
		
	}
}
