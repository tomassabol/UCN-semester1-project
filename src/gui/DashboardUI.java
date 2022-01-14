package gui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import controller.AuthenticationController;
import model.Customer;

/**
 * @author Daniels Kanepe
 *
 */
public class DashboardUI extends JFrame {
	
	private Customer customer = null;
	

	private JPanel contentPane;
	private AuthenticationController auth;
	private JLabel lblGreeting;
	private JLink btnLogout;
	private JTabbedPane tabsPane;
	private JPanel sellPanel;
	private JPanel sellPaneTopPanel;
	private JPanel sellPaneBottomPanel;
	private JTextField txtCustomer;
	private JButton btnChooseCustomer;
	private JLabel lblSell;
	private JButton btnSellItems;
	private JLabel lblQuotes;
	private JLabel lblOrder;
	private JButton btnViewQuotes;
	private JButton btnViewOrders;
	private JPanel loanPanel;
	private JPanel statisticsPanel;
	private JButton btnEmployee;
	private JButton btnCustomer;
	private JLabel lblEmployee;
	private JLabel lblCustomer;
	private JPanel InventoryPanel;
	private JButton btnContractor;
	private JButton btnSupplyOrder;
	private JButton btnStock;
	private JButton btnSupplyOffer;
	private JLabel lblStockItems;
	private JLabel lblManage;
	private JButton btnStorageLocations;
	private JButton btnShelves;

	/**
	 * Create the frame.
	 */
	public DashboardUI(AuthenticationController auth) {
		setTitle("Vestbjerg Byggecenter System");
		this.auth = auth;
		
		// Window
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		// *Content pane
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
			// **Top panel (greeting & log out)
			JPanel topPanel = new JPanel();
			contentPane.add(topPanel, BorderLayout.NORTH);
			
			// ***** TOP PANEL *****
			GridBagLayout gbl_topPanel = new GridBagLayout();
			gbl_topPanel.columnWidths = new int[]{0, 0, 0, 0};
			gbl_topPanel.rowHeights = new int[]{0, 0, 0};
			gbl_topPanel.columnWeights = new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE};
			gbl_topPanel.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
			topPanel.setLayout(gbl_topPanel);
		
				// // ***** Greeting label *****
				lblGreeting = new JLabel("Hi, " + auth.getLoggedInUser().getFirstName());
				GridBagConstraints gbc_lblGreeting = new GridBagConstraints();
				gbc_lblGreeting.insets = new Insets(0, 0, 5, 5);
				gbc_lblGreeting.gridx = 0;
				gbc_lblGreeting.gridy = 0;
				topPanel.add(lblGreeting, gbc_lblGreeting);
		
				// // ***** Log out button *****
				btnLogout = new JLink("Log out");
				GridBagConstraints gbc_lblLogout = new GridBagConstraints();
				gbc_lblLogout.insets = new Insets(0, 0, 5, 0);
				gbc_lblLogout.gridx = 2;
				gbc_lblLogout.gridy = 0;
				topPanel.add(btnLogout, gbc_lblLogout);
		
			// ***** Tabs pane *****
			tabsPane = new JTabbedPane(JTabbedPane.TOP);
			contentPane.add(tabsPane, BorderLayout.CENTER);
		
				// ***** SELL tab *****
				this.initSellTab();
		
				// ***** LOANS tab *****
				this.initLoansTab();
				
				// ***** INVENTORY tab *****
				this.initInventoryTab();
				
				// ***** PEOPLE tab *****
				this.initPeopleTab();
				
				// ***** STATISTICS tab *****
				this.initStatisticsTab();
			
		// Attach event handler
		addEventHandlers();
	}
	
	/*
	 * -------------------------------------------------------
	 * ----------------------  Sell tab ----------------------
	 * -------------------------------------------------------
	 */
	public void initSellTab() {
		sellPanel = new JPanel();
		sellPanel.setToolTipText("");
		sellPanel.setBorder(null);
		tabsPane.addTab("Sell", null, sellPanel, "Sell Products");
		sellPanel.setLayout(new BorderLayout(0, 0));
		
		sellPaneTopPanel = new JPanel();
		sellPanel.add(sellPaneTopPanel, BorderLayout.NORTH);
		sellPaneTopPanel.setLayout(new BorderLayout(0, 0));
		
		btnChooseCustomer = new JButton("Choose Customer");
		sellPaneTopPanel.add(btnChooseCustomer, BorderLayout.NORTH);
		
		txtCustomer = new JTextField();
		txtCustomer.setEditable(false);
		sellPaneTopPanel.add(txtCustomer, BorderLayout.SOUTH);
		txtCustomer.setColumns(10);
		
		sellPaneBottomPanel = new JPanel();
		sellPanel.add(sellPaneBottomPanel, BorderLayout.CENTER);
		GridBagLayout gbl_sellPaneBottomPanel = new GridBagLayout();
		gbl_sellPaneBottomPanel.columnWidths = new int[]{0, 0, 0, 0};
		gbl_sellPaneBottomPanel.rowHeights = new int[]{0, 0, 0};
		gbl_sellPaneBottomPanel.columnWeights = new double[]{1.0, 1.0, 1.0, Double.MIN_VALUE};
		gbl_sellPaneBottomPanel.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		sellPaneBottomPanel.setLayout(gbl_sellPaneBottomPanel);
		
		lblSell = new JLabel();
		ImageIcon sellIcon = new ImageIcon("images/coins.png");
		lblSell.setIcon(sellIcon);
		GridBagConstraints gbc_lblSell = new GridBagConstraints();
		gbc_lblSell.fill = GridBagConstraints.VERTICAL;
		gbc_lblSell.insets = new Insets(0, 0, 5, 5);
		gbc_lblSell.gridx = 0;
		gbc_lblSell.gridy = 0;
		sellPaneBottomPanel.add(lblSell, gbc_lblSell);
		
		lblQuotes = new JLabel("");
		ImageIcon quoteIcon = new ImageIcon("images/quotes.png");
		lblQuotes.setIcon(quoteIcon);
		GridBagConstraints gbc_lblQuotes = new GridBagConstraints();
		gbc_lblQuotes.fill = GridBagConstraints.VERTICAL;
		gbc_lblQuotes.insets = new Insets(0, 0, 5, 5);
		gbc_lblQuotes.gridx = 1;
		gbc_lblQuotes.gridy = 0;
		sellPaneBottomPanel.add(lblQuotes, gbc_lblQuotes);
		
		lblOrder = new JLabel();
		ImageIcon orderIcon = new ImageIcon("images/order.png");
		lblOrder.setIcon(orderIcon);
		GridBagConstraints gbc_lblOrder = new GridBagConstraints();
		gbc_lblOrder.fill = GridBagConstraints.VERTICAL;
		gbc_lblOrder.insets = new Insets(0, 0, 5, 0);
		gbc_lblOrder.gridx = 2;
		gbc_lblOrder.gridy = 0;
		sellPaneBottomPanel.add(lblOrder, gbc_lblOrder);
		
		
		btnSellItems = new JButton("Sell items");
		GridBagConstraints gbc_btnSellItems = new GridBagConstraints();
		gbc_btnSellItems.insets = new Insets(0, 0, 0, 5);
		gbc_btnSellItems.gridx = 0;
		gbc_btnSellItems.gridy = 1;
		sellPaneBottomPanel.add(btnSellItems, gbc_btnSellItems);
		
		btnViewQuotes = new JButton("Quotes");
		btnViewQuotes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		GridBagConstraints gbc_btnViewQuotes = new GridBagConstraints();
		gbc_btnViewQuotes.insets = new Insets(0, 0, 0, 5);
		gbc_btnViewQuotes.gridx = 1;
		gbc_btnViewQuotes.gridy = 1;
		sellPaneBottomPanel.add(btnViewQuotes, gbc_btnViewQuotes);
		
		btnViewOrders = new JButton("Orders");
		GridBagConstraints gbc_btnViewOrders = new GridBagConstraints();
		gbc_btnViewOrders.gridx = 2;
		gbc_btnViewOrders.gridy = 1;
		sellPaneBottomPanel.add(btnViewOrders, gbc_btnViewOrders);
	}
	
	/*
	 * -------------------------------------------------------
	 * ----------------------  Loans tab ---------------------
	 * -------------------------------------------------------
	 */
	public void initLoansTab() {
		loanPanel = new JPanel();
		tabsPane.addTab("Loans", null, loanPanel, null);
	}

	/*
	 * -------------------------------------------------------
	 * --------------------  Inventory tab -------------------
	 * -------------------------------------------------------
	 */
	public void initInventoryTab() {
		InventoryPanel = new JPanel();
		InventoryPanel.setToolTipText("");
		InventoryPanel.setBorder(null);
		tabsPane.addTab("Inventory", null, InventoryPanel, "Inventory");
		InventoryPanel.setLayout(new BorderLayout(0, 0));
		
		JPanel contractorPaneTopPanel = new JPanel();
		InventoryPanel.add(contractorPaneTopPanel, BorderLayout.NORTH);
		contractorPaneTopPanel.setLayout(new BorderLayout(0, 0));
		
		
		txtCustomer = new JTextField();
		txtCustomer.setEditable(false);
		sellPaneTopPanel.add(txtCustomer, BorderLayout.SOUTH);
		txtCustomer.setColumns(10); 
		
		Container contractorPaneBottomPanel = new JPanel();
		InventoryPanel.add(contractorPaneBottomPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contractorPaneBottomPanel = new GridBagLayout();
		gbl_contractorPaneBottomPanel.columnWidths = new int[]{0, 0, 0};
		gbl_contractorPaneBottomPanel.rowHeights = new int[]{0, 0, 0, 0, 0, 0};
		gbl_contractorPaneBottomPanel.columnWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
		gbl_contractorPaneBottomPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		contractorPaneBottomPanel.setLayout(gbl_contractorPaneBottomPanel);
		
		lblSell = new JLabel();
		ImageIcon sellIcon = new ImageIcon("images/coins.png");
		lblSell.setIcon(sellIcon);
		GridBagConstraints gbc_lblSell = new GridBagConstraints();
		gbc_lblSell.fill = GridBagConstraints.VERTICAL;
		gbc_lblSell.insets = new Insets(0, 0, 5, 5);
		gbc_lblSell.gridx = 0;
		gbc_lblSell.gridy = 0;
		sellPaneBottomPanel.add(lblSell, gbc_lblSell);
		
		lblManage = new JLabel("Manage");
		GridBagConstraints gbc_lblManage = new GridBagConstraints();
		gbc_lblManage.insets = new Insets(0, 0, 5, 5);
		gbc_lblManage.gridx = 0;
		gbc_lblManage.gridy = 1;
		contractorPaneBottomPanel.add(lblManage, gbc_lblManage);
		
		lblStockItems = new JLabel("Re-stock items");
		GridBagConstraints gbc_lblStockItems = new GridBagConstraints();
		gbc_lblStockItems.insets = new Insets(0, 0, 5, 0);
		gbc_lblStockItems.gridx = 1;
		gbc_lblStockItems.gridy = 1;
		contractorPaneBottomPanel.add(lblStockItems, gbc_lblStockItems);
		
		
		
		
		
		
		btnContractor = new JButton("Contractors");
		GridBagConstraints gbc_btnContractor = new GridBagConstraints();
		gbc_btnContractor.insets = new Insets(0, 0, 5, 5);
		gbc_btnContractor.gridx = 0;
		gbc_btnContractor.gridy = 2;
		contractorPaneBottomPanel.add(btnContractor, gbc_btnContractor);
		
		btnSupplyOffer = new JButton("Supply Offers");
		GridBagConstraints gbc_btnSupplyOffer = new GridBagConstraints();
		gbc_btnSupplyOffer.insets = new Insets(0, 0, 5, 0);
		gbc_btnSupplyOffer.gridx = 1;
		gbc_btnSupplyOffer.gridy = 2;
		contractorPaneBottomPanel.add(btnSupplyOffer, gbc_btnSupplyOffer);
		
		btnSupplyOrder = new JButton("Supply Orders");
		btnSupplyOrder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		
		btnStorageLocations = new JButton("Storage locations");
		GridBagConstraints gbc_btnStorageLocations = new GridBagConstraints();
		gbc_btnStorageLocations.insets = new Insets(0, 0, 5, 5);
		gbc_btnStorageLocations.gridx = 0;
		gbc_btnStorageLocations.gridy = 3;
		contractorPaneBottomPanel.add(btnStorageLocations, gbc_btnStorageLocations);
		GridBagConstraints gbc_btnSupplyOrder = new GridBagConstraints();
		gbc_btnSupplyOrder.insets = new Insets(0, 0, 5, 0);
		gbc_btnSupplyOrder.gridx = 1;
		gbc_btnSupplyOrder.gridy = 3;
		contractorPaneBottomPanel.add(btnSupplyOrder, gbc_btnSupplyOrder);
		
		btnShelves = new JButton("Shelves");
		GridBagConstraints gbc_btnShelves = new GridBagConstraints();
		gbc_btnShelves.insets = new Insets(0, 0, 0, 5);
		gbc_btnShelves.gridx = 0;
		gbc_btnShelves.gridy = 4;
		contractorPaneBottomPanel.add(btnShelves, gbc_btnShelves);
		
		btnStock = new JButton("Stock a supply order");
		GridBagConstraints gbc_btnStock = new GridBagConstraints();
		gbc_btnStock.gridx = 1;
		gbc_btnStock.gridy = 4;
		contractorPaneBottomPanel.add(btnStock, gbc_btnStock);
		
		
	}
	
	
	/*
	 * -------------------------------------------------------
	 * ---------------------  People tab ---------------------
	 * -------------------------------------------------------
	 */
	public void initPeopleTab() {
		JPanel peoplePanel = new JPanel();
		peoplePanel.setBorder(new EmptyBorder(5, 0, 0, 0));
		tabsPane.addTab("People", null, peoplePanel, null);
		GridBagLayout gbl_peoplePanel = new GridBagLayout();
		gbl_peoplePanel.columnWidths = new int[]{0, 0, 0};
		gbl_peoplePanel.rowHeights = new int[]{0, 0, 0};
		gbl_peoplePanel.columnWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
		gbl_peoplePanel.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		peoplePanel.setLayout(gbl_peoplePanel);
		
		lblEmployee = new JLabel();
		ImageIcon employeeIcon = new ImageIcon("images/employee.png", "");
		lblEmployee.setIcon(employeeIcon);
		GridBagConstraints gbc_lblEmployee = new GridBagConstraints();
		gbc_lblEmployee.insets = new Insets(0, 0, 5, 5);
		gbc_lblEmployee.gridx = 0;
		gbc_lblEmployee.gridy = 0;
		peoplePanel.add(lblEmployee, gbc_lblEmployee);
		
		lblCustomer = new JLabel();
		ImageIcon customerIcon = new ImageIcon("images/customer.png", "");
		lblCustomer.setIcon(customerIcon);
		GridBagConstraints gbc_lblCustomer = new GridBagConstraints();
		gbc_lblCustomer.insets = new Insets(0, 0, 5, 0);
		gbc_lblCustomer.gridx = 1;
		gbc_lblCustomer.gridy = 0;
		peoplePanel.add(lblCustomer, gbc_lblCustomer);
		
		btnEmployee = new JButton("Employees");
		GridBagConstraints gbc_btnEmployee = new GridBagConstraints();
		gbc_btnEmployee.insets = new Insets(0, 0, 0, 5);
		gbc_btnEmployee.gridx = 0;
		gbc_btnEmployee.gridy = 1;
		peoplePanel.add(btnEmployee, gbc_btnEmployee);
		
		btnCustomer = new JButton("Customers");
		GridBagConstraints gbc_btnCustomer = new GridBagConstraints();
		gbc_btnCustomer.gridx = 1;
		gbc_btnCustomer.gridy = 1;
		peoplePanel.add(btnCustomer, gbc_btnCustomer);
	}
	
	/*
	 * -------------------------------------------------------
	 * -------------------  Statistics tab -------------------
	 * -------------------------------------------------------
	 */
	public void initStatisticsTab() {
		statisticsPanel = new JPanel();
		tabsPane.addTab("Statistics", null, statisticsPanel, null);
	}
	
	/*
	 * *******************************************************
	 * *******************  EVENT HANDLERS *******************
	 * *******************************************************
	 */
	
	public void addEventHandlers() {
		// ***** Log out button *****
		btnLogout.addActionListener(e -> {
	    	if (Messages.confirm(DashboardUI.this, "Are you sure you want to log out?", "Log Out?")) {
		    	auth.Logout();
				Login frame = new Login();
				frame.setVisible(true);
		    	// free up memory by destroying the current dashboard
		    	DashboardUI.this.dispose();
	    	}
		});
		
		// ***** Choose customer button *****
		btnChooseCustomer.addActionListener(e -> {
			ChooseCustomerUI frame = new ChooseCustomerUI(auth);
			frame.setVisible(true);
			if (frame.isCustomerSelected()) {
				customer = frame.getSelectedCustomer();
				if (customer != null) {
					txtCustomer.setText(String.format("(%s) %s %s", 
							customer.ID,
							customer.getFirstName(),
							customer.getLastName()));
				}
			}
		});
		
		// ***** Sell items button *****
		btnSellItems.addActionListener(e -> {
			if (this.customer == null) {
				Messages.info(this, "Please choose a customer", "Choose a customer");
			} else {
				ShoppingCartUI frame = new ShoppingCartUI(auth, customer);
				frame.setVisible(true);
				if (frame.isSubmitPressed()) {
					QuotesUI quoteFrame = new QuotesUI(auth, customer);
					quoteFrame.setVisible(true);
					if (quoteFrame.isSubmitPressed()) {
						OrdersUI orderFrame = new OrdersUI(auth, customer);
						orderFrame.setVisible(true);
					}
				}
			}

		});
		
		btnViewQuotes.addActionListener(e -> {
			if (this.customer == null) {
				Messages.info(this, "Please choose a customer", "Choose a customer");
			} else {
				QuotesUI frame = new QuotesUI(auth, customer);
				frame.setVisible(true);
				if (frame.isSubmitPressed()) {
					OrdersUI orderFrame = new OrdersUI(auth, customer);
					orderFrame.setVisible(true);
				}
			}
		});
		
		btnViewOrders.addActionListener(e -> {
			if (this.customer == null) {
				Messages.info(this, "Please choose a customer", "Choose a customer");
			} else {
				OrdersUI orderFrame = new OrdersUI(auth, customer);
				orderFrame.setVisible(true);
			}
		});
		
		///////////////////////////////////////////////////////
		////////////////     Inventory     //////////////////
		/////////////////////////////////////////////////////
		
		// ***** Manage Contractors *****
		btnContractor.addActionListener(e -> {
			ManageContractorUI frame = new ManageContractorUI(auth);
			frame.setVisible(true);
		});
				
		///////////////////////////////////////////////////////
		////////////////     People tab     //////////////////
		/////////////////////////////////////////////////////
		
		
		// ***** Customer button *****
		btnCustomer.addActionListener(e -> {
			ManageCustomerUI frame = new ManageCustomerUI(auth);
			frame.setVisible(true);
		});
		
		// we need to create CRUDEmployeesPanel
		btnEmployee.addActionListener(e -> {
			ManageEmployeeUI frame = new ManageEmployeeUI(auth);
			frame.setVisible(true);
		});
		
		
		
		
		
	} // end of event handlers
}
