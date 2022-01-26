package gui.windows;

import javax.swing.JDialog;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.JPanel;
import controller.AuthenticationController;
import controller.OrderController;
import gui.panels.tableModels.OrdersItemTableModel;
import gui.panels.tableModels.OrdersTableModel;
import model.Customer;
import model.Order;

import javax.swing.ListSelectionModel;
import javax.swing.RowFilter;
import javax.swing.JTextField;

/**
 * @author Daniels Kanepe
 *
 */
public class ManageOrder extends JDialog {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8989354887857964136L;
	private AuthenticationController auth;
	private Customer customer;
	private JPanel contentPane;
	private OrdersTableModel ordersTableModel;
	private OrdersItemTableModel itemTableModel;
	
	private TableRowSorter<TableModel> rowSorter;
	
	OrderController orderCtrl;
	private JTextField txtSearch;
	private JButton btnCreateOrder;
	private JTable tableOrders;
	private JTable tableItems;
	

	/**
	 * Create the dialog.
	 */
	public ManageOrder(AuthenticationController auth, Customer customer) {
		this.auth = auth;
		setTitle("Orders");
		if (customer == null) {
			throw new IllegalArgumentException("Customer cannot be null!");
		}
		this.customer = customer;
		
		orderCtrl = new OrderController();
		
		setModal(true);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 430, 406);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{451, 0};
		gbl_contentPane.rowHeights = new int[]{50, 84, 0, 49, 0};
		gbl_contentPane.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 1.0, 0.0, 1.0, Double.MIN_VALUE};
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
				String.format("%s %s's orders", 
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
		
		btnCreateOrder = new JButton("Create order");
		GridBagConstraints gbc_btnCreateOrder = new GridBagConstraints();
		gbc_btnCreateOrder.insets = new Insets(0, 0, 5, 0);
		gbc_btnCreateOrder.gridx = 2;
		gbc_btnCreateOrder.gridy = 1;
		topPanel.add(btnCreateOrder, gbc_btnCreateOrder);
		
		
		JScrollPane scrollPanel = new JScrollPane();
		GridBagConstraints gbc_scrollPanel = new GridBagConstraints();
		gbc_scrollPanel.insets = new Insets(0, 0, 5, 0);
		gbc_scrollPanel.fill = GridBagConstraints.BOTH;
		gbc_scrollPanel.gridx = 0;
		gbc_scrollPanel.gridy = 1;
		contentPane.add(scrollPanel, gbc_scrollPanel);
		
		tableOrders = new JTable();
		tableOrders.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		ordersTableModel = new OrdersTableModel(orderCtrl.getOrders(customer));
		tableOrders.setModel(ordersTableModel);
		scrollPanel.setViewportView(tableOrders);
		
		rowSorter = new TableRowSorter<>(tableOrders.getModel());
		tableOrders.setRowSorter(rowSorter);
		
		JLabel lblItemsInOrder = new JLabel("Items in Order");
		GridBagConstraints gbc_lblItemsInOrder = new GridBagConstraints();
		gbc_lblItemsInOrder.insets = new Insets(0, 0, 5, 0);
		gbc_lblItemsInOrder.gridx = 0;
		gbc_lblItemsInOrder.gridy = 2;
		contentPane.add(lblItemsInOrder, gbc_lblItemsInOrder);
		
		JScrollPane scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 3;
		contentPane.add(scrollPane, gbc_scrollPane);
		
		tableItems = new JTable();
		tableItems.setEnabled(false);
		tableItems.setRowSelectionAllowed(false);
		tableItems.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane.setViewportView(tableItems);
	
		// Attach event handlers
		this.addEventHandlers();
		
		// Automatically select latest order, if any exist
		int row = ordersTableModel.getRowCount() - 1;
		if (row >= 0) {
			tableOrders.setRowSelectionInterval(0, row);
		}
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
		
		// Create order button -> show quotes window
		btnCreateOrder.addActionListener(e -> {
			ManageQuotes frame = new ManageQuotes(auth, customer);
			frame.setVisible(true);
			if (frame.isSubmitPressed() && frame.getCreatedOrder() != null) {
				int row = ordersTableModel.addOrder(frame.getCreatedOrder());
				tableOrders.setRowSelectionInterval(0, row);
			}
		});
		
		// If an order is selected
		tableOrders.getSelectionModel().addListSelectionListener(e -> {
			if (tableOrders.getSelectionModel().isSelectionEmpty()) {
				// ***** NOT SELECTED *****
				

				
				// clear item lines
				tableItems.setModel(new DefaultTableModel());
			} else {
				// ***** SELECTED *****
				
				
				// Show the item lines
				int selectedRow = tableOrders.getSelectedRow();
				Order order = ordersTableModel.getOrder(selectedRow);
				itemTableModel = new OrdersItemTableModel(order.getItemLines());
				tableItems.setModel(itemTableModel);
			}
		});
		
		// Searches for an order with dynamic filter
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
