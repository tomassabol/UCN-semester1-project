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
	private JTable tableQuotes;
	
	QuoteController quoteCtrl;
	private JTextField txtSearch;
	private JTable tableItems;
	private JButton btnCreateQuote;
	private JButton btnPay;
	

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
			
			btnCreateQuote = new JButton("Create quote");
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
				quotesTableModel = new QuotesTableModel(quoteCtrl.getQuotes(customer));
				tableQuotes = new JTable();
				tableQuotes.setModel(quotesTableModel);
				tableQuotes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
				scrollPanel.setViewportView(tableQuotes);
		
		// ***** Bottom panel *****
		JPanel bottomPanel = new JPanel();
		contentPane.add(bottomPanel, BorderLayout.SOUTH);
					GridBagLayout gbl_bottomPanel = new GridBagLayout();
					gbl_bottomPanel.columnWidths = new int[]{430, 0, 0};
					gbl_bottomPanel.rowHeights = new int[]{0, 29, 29, 0, 0};
					gbl_bottomPanel.columnWeights = new double[]{1.0, 0.0, Double.MIN_VALUE};
					gbl_bottomPanel.rowWeights = new double[]{0.0, 1.0, 0.0, 1.0, Double.MIN_VALUE};
					bottomPanel.setLayout(gbl_bottomPanel);
					
					JLabel lblItemsInQuote = new JLabel("Items in quote");
					GridBagConstraints gbc_lblItemsInQuote = new GridBagConstraints();
					gbc_lblItemsInQuote.gridwidth = 2;
					gbc_lblItemsInQuote.insets = new Insets(0, 0, 5, 5);
					gbc_lblItemsInQuote.gridx = 0;
					gbc_lblItemsInQuote.gridy = 0;
					bottomPanel.add(lblItemsInQuote, gbc_lblItemsInQuote);
					
					JScrollPane scrollPane = new JScrollPane();
					GridBagConstraints gbc_scrollPane = new GridBagConstraints();
					gbc_scrollPane.gridwidth = 2;
					gbc_scrollPane.insets = new Insets(0, 0, 5, 0);
					gbc_scrollPane.fill = GridBagConstraints.BOTH;
					gbc_scrollPane.gridx = 0;
					gbc_scrollPane.gridy = 1;
					bottomPanel.add(scrollPane, gbc_scrollPane);
					
					tableItems = new JTable();
					scrollPane.setRowHeaderView(tableItems);
					
					btnPay = new JButton("Pay");
					btnPay.setEnabled(false);
					GridBagConstraints gbc_btnPay = new GridBagConstraints();
					gbc_btnPay.insets = new Insets(0, 0, 5, 0);
					gbc_btnPay.gridx = 1;
					gbc_btnPay.gridy = 2;
					bottomPanel.add(btnPay, gbc_btnPay);
		
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
		
		// When a row is selected/unselected:
		tableQuotes.getSelectionModel().addListSelectionListener(e -> {
			// ***** Not selected *****
			if (tableQuotes.getSelectionModel().isSelectionEmpty()) {
				// Disable pay button
				btnPay.setEnabled(false);
				
				
				// Reset item line table model
				tableItems.setModel(new DefaultTableModel());
			} else {
				// ***** Selected *****
				
				// Enable pay button
				btnPay.setEnabled(true);
				
				// Get selected quote, and show the item lines (by using ShoppingCartTableModel)
				Quote quote = quotesTableModel.getQuote(tableQuotes.getSelectedRow());
				itemTableModel = new QuotesItemTableModel(quote.getItemLines());
				tableItems.setModel(itemTableModel);
				tableItems.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
				//scrollPanel.setViewportView(mainTable);
				
			}
		});
		
	}
}
