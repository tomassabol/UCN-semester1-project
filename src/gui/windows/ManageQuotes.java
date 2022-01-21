 package gui.windows;

import javax.swing.JDialog;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.JPanel;
import controller.AuthenticationController;
import controller.OrderController;
import controller.QuoteController;
import gui.Messages;
import gui.panels.tableModels.OrdersTableModel;
import gui.panels.tableModels.QuotesItemTableModel;
import gui.panels.tableModels.QuotesTableModel;
import model.Customer;
import model.Order;
import model.Quote;
import javax.swing.ListSelectionModel;
import javax.swing.JTextField;

import javax.swing.table.TableRowSorter;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.RowFilter;

/**
 * @author Daniels Kanepe
 *
 */
public class ManageQuotes extends JDialog {
	private AuthenticationController auth;
	private Customer customer;
	private JPanel contentPane;
	private QuotesTableModel quotesTableModel;
	private QuotesItemTableModel itemTableModel;
	
	QuoteController quoteCtrl;
	OrderController orderCtrl;
	
	private JTextField txtSearch;
	private JButton btnCreateQuote;
	private JButton btnPay;
	private JTable tableQuotes;
	private JTable tableItems;
	
	private TableRowSorter<TableModel> rowSorter;
	
	private boolean isSubmitPressed = false;
	private Order createdOrder = null;
	

	/**
	 * Create the dialog.
	 */
	public ManageQuotes(AuthenticationController auth, Customer customer) {
		this.auth = auth;
		setTitle("Quotes");
		if (customer == null) {
			throw new IllegalArgumentException("Customer cannot be null!");
		}
		this.customer = customer;
		
		quoteCtrl = new QuoteController();
		orderCtrl = new OrderController();
		
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
		
		rowSorter = new TableRowSorter<>(tableQuotes.getModel());
		tableQuotes.setRowSorter(rowSorter);
		
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
		tableItems.setEnabled(false);
		tableItems.setRowSelectionAllowed(false);
		tableItems.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane.setViewportView(tableItems);
		
		btnPay = new JButton("Pay");
		btnPay.setEnabled(false);
		GridBagConstraints gbc_btnPay = new GridBagConstraints();
		gbc_btnPay.anchor = GridBagConstraints.EAST;
		gbc_btnPay.gridx = 0;
		gbc_btnPay.gridy = 4;
		contentPane.add(btnPay, gbc_btnPay);
	
		// Attach event handlers
		this.addEventHandlers();
		
		// Automatically select latest quote, if any exist
		int row = quotesTableModel.getRowCount() - 1;
		if (row >= 0) {
			tableQuotes.setRowSelectionInterval(0, row);
		}
	}

	
	/*
	 * *******************************************************
	 * *******************  Methods *******************
	 * *******************************************************
	 */
	
	public boolean isSubmitPressed() {
		return this.isSubmitPressed;
	}
	
	public Order getCreatedOrder() {
		return this.createdOrder;
	}
	
	
	
	/*
	 * *******************************************************
	 * *******************  EVENT HANDLERS *******************
	 * *******************************************************
	 */
	public void addEventHandlers() {
		
		btnPay.addActionListener(e -> {
			if (!tableQuotes.getSelectionModel().isSelectionEmpty()) {
				if (Messages.confirm(this, "Has the customer paid for the quote?")) {
					this.isSubmitPressed = true;
					Quote quote = quotesTableModel.getQuote(tableQuotes.getSelectedRow());
					Order order = orderCtrl.payForQuote(quote);
					if (order != null) {
						quotesTableModel.removeQuote(tableQuotes.getSelectedRow());
						this.createdOrder = order;
						this.dispose();
					} else {
						Messages.error(this, "Could not create an order for this quote :(");
					}
				}

			}
		});
		
		// Create quote button -> redirect to shopping cart
		btnCreateQuote.addActionListener(e -> {
			ManageShoppingCart frame = new ManageShoppingCart(auth, customer);
			frame.setVisible(true);
			if (frame.isSubmitPressed()) {
				int row = quotesTableModel.addQuote(frame.getCreatedQuote());
				tableQuotes.setRowSelectionInterval(0, row);
			}
		});
		
		// If a quote is selected
		tableQuotes.getSelectionModel().addListSelectionListener(e -> {
			if (tableQuotes.getSelectionModel().isSelectionEmpty()) {
				// ***** NOT SELECTED *****
				
				// Disable pay button
				btnPay.setEnabled(false);
				
				// clear item lines
				tableItems.setModel(new DefaultTableModel());
			} else {
				// ***** SELECTED *****
				
				// Enable pay button
				btnPay.setEnabled(true);
				
				// Show the item lines
				int selectedRow = tableQuotes.getSelectedRow();
				Quote quote = quotesTableModel.getQuote(selectedRow);
				itemTableModel = new QuotesItemTableModel(quote.getItemLines());
				tableItems.setModel(itemTableModel);
			}
		});
		
		// Dynamic filter for searching
		txtSearch.getDocument().addDocumentListener(new DocumentListener(){
			
			 @Override
	         public void insertUpdate(DocumentEvent e) {
				String text = txtSearch.getText();
				
				if(text.trim().length() == 0) {
					rowSorter.setRowFilter(null);
				}else {
					rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
				}
			}
			
			@Override
			public void  removeUpdate(DocumentEvent e) {
				String text = txtSearch.getText();
				
				if (text.trim().length() == 0) {
                    rowSorter.setRowFilter(null);
                } else {
                    rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
                }
			}
			
			@Override
			public void changedUpdate(DocumentEvent e) {
				throw new UnsupportedOperationException("Not supported yet.");
			}
		});
		
	}
}
