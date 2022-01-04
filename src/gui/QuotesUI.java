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

import controller.AuthenticationController;
import controller.QuoteController;
import controller.ShoppingCartController;
import model.Customer;
import model.Quote;
import model.ShoppingItemLine;

import javax.swing.ListSelectionModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;

public class QuotesUI extends JDialog {
	private AuthenticationController auth;
	private Customer customer;
	private JPanel contentPane;
	private JTable table;
	private QuotesTableModel quotesTableModel;
	private QuotesItemTableModel itemTableModel;
	
	QuoteController quoteCtrl;
	private JTextField txtSearch;
	private JButton btnCreateQuote;
	private JButton btnPay;
	private JTable tableQuotes;
	private JTable tableItems;
	

	/**
	 * Create the dialog.
	 */
	public QuotesUI(AuthenticationController auth, Customer customer) {
		this.auth = auth;
		setTitle("Quotes");
		if (customer == null) {
			throw new IllegalArgumentException("Customer cannot be null!");
		}
		this.customer = customer;
		
		quoteCtrl = new QuoteController();
		
		setModal(true);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 430, 406);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{451, 0};
		gbl_contentPane.rowHeights = new int[]{50, 84, 0, 49, 0, 0};
		gbl_contentPane.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 1.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		// ***** TOP PANEL *****
		JPanel topPanel = new JPanel();
		GridBagConstraints gbc_topPanel = new GridBagConstraints();
		gbc_topPanel.anchor = GridBagConstraints.NORTH;
		gbc_topPanel.fill = GridBagConstraints.HORIZONTAL;
		gbc_topPanel.insets = new Insets(0, 0, 5, 0);
		gbc_topPanel.gridx = 0;
		gbc_topPanel.gridy = 0;
		getContentPane().add(topPanel, gbc_topPanel);
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
		
		btnCreateQuote = new JButton("Create quote");
		GridBagConstraints gbc_btnCreateQuote = new GridBagConstraints();
		gbc_btnCreateQuote.insets = new Insets(0, 0, 5, 0);
		gbc_btnCreateQuote.gridx = 2;
		gbc_btnCreateQuote.gridy = 1;
		topPanel.add(btnCreateQuote, gbc_btnCreateQuote);
		
		
		JScrollPane scrollPanel = new JScrollPane();
		GridBagConstraints gbc_scrollPanel = new GridBagConstraints();
		gbc_scrollPanel.insets = new Insets(0, 0, 5, 0);
		gbc_scrollPanel.fill = GridBagConstraints.BOTH;
		gbc_scrollPanel.gridx = 0;
		gbc_scrollPanel.gridy = 1;
		contentPane.add(scrollPanel, gbc_scrollPanel);
		
		tableQuotes = new JTable();
		tableQuotes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		quotesTableModel = new QuotesTableModel(quoteCtrl.getQuotes(customer));
		tableQuotes.setModel(quotesTableModel);
		scrollPanel.setViewportView(tableQuotes);
		
		JLabel lblItemsInQuote = new JLabel("Items in quote");
		GridBagConstraints gbc_lblItemsInQuote = new GridBagConstraints();
		gbc_lblItemsInQuote.insets = new Insets(0, 0, 5, 0);
		gbc_lblItemsInQuote.gridx = 0;
		gbc_lblItemsInQuote.gridy = 2;
		contentPane.add(lblItemsInQuote, gbc_lblItemsInQuote);
		
		JScrollPane scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.insets = new Insets(0, 0, 5, 0);
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 3;
		contentPane.add(scrollPane, gbc_scrollPane);
		
		tableItems = new JTable();
		tableItems.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane.setViewportView(tableItems);
		
		btnPay = new JButton("Pay");
		btnPay.setEnabled(false);
		GridBagConstraints gbc_btnPay = new GridBagConstraints();
		gbc_btnPay.anchor = GridBagConstraints.EAST;
		gbc_btnPay.gridx = 0;
		gbc_btnPay.gridy = 4;
		contentPane.add(btnPay, gbc_btnPay);
		
		
		
		// Attach event handler
		this.addEventHandlers();
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
	public void addEventHandlers() {
		
		// Create quote button -> redirect to shopping cart
		btnCreateQuote.addActionListener(e -> {
			ShoppingCartUI frame = new ShoppingCartUI(auth, customer);
			frame.setVisible(true);
			if (frame.isSubmitPressed()) {
				int row = quotesTableModel.addRow(frame.getCreatedQuote());
				tableQuotes.setRowSelectionInterval(0, row);
			}
		});
		
		// If a quote is selected
		tableQuotes.getSelectionModel().addListSelectionListener(e -> {
			if (tableQuotes.getSelectionModel().isSelectionEmpty()) {
				// ***** NOT SELECTED *****
				
				// Disable pay button
				btnPay.setEnabled(false);
			} else {
				// ***** SELECTED *****
				
				// Enable pay button
				btnPay.setEnabled(true);
				
				// Show the item lines
				int selectedRow = tableQuotes.getSelectedRow();
				Quote quote = quotesTableModel.getQuote(selectedRow);
				itemTableModel = new QuotesItemTableModel(quote.getItemLines());
				tableItems.setModel(itemTableModel);;
			}
		});
		
		
		
	}
}
