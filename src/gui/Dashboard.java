package gui;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.print.event.PrintJobListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;

import org.knowm.xchart.QuickChart;
import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.XChartPanel;
import org.knowm.xchart.XYChart;
import org.knowm.xchart.XYChartBuilder;
import org.knowm.xchart.XYSeries.XYSeriesRenderStyle;
import org.knowm.xchart.style.Styler.LegendPosition;

import controller.AuthenticationController;
import controller.OrderController;
import gui.statistics.charts.OrdersChart;
import gui.windows.ChooseCustomer;
import gui.windows.ChooseLoan;
import gui.windows.ManageContractor;
import gui.windows.ManageCustomerType;
import gui.windows.ManageCustomer;
import gui.windows.ManageEmployee;
import gui.windows.ManageLoans;
import gui.windows.ManageOrder;
import gui.windows.ManageProduct;
import gui.windows.ManageQuotes;
import gui.windows.ManageShelf;
import gui.windows.ManageStorageLocation;
import gui.windows.ManageSupplyOffer;
import gui.windows.ManageSupplyOrders;
import gui.windows.objects.LoanUI;
import gui.windows.ManageShoppingCart;
import model.Customer;
import model.Loan;
import model.Order;

import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.List;
import java.awt.event.ActionEvent;

/**
 * @author Daniels Kanepe
 *
 */
public class Dashboard extends JFrame {
	
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
	private JButton btnContractors;
	private JButton btnSupplyOrders;
	private JButton btnSupplyOffers;
	private JLabel lblRestockItems;
	private JLabel lblManage;
	private JButton btnStorageLocations;
	private JButton btnShelves;
	private JLabel lblInventoryManagePic;
	private JLabel lblInventoryRestockPic;
	private JButton btnProducts;
	private JButton btnCustomerTypes;
	private JLabel lblLoanImage;
	private JButton btnManageLoans;
	private JButton btnReturnLoan;
	private JTabbedPane stTabsPane;
	private JPanel stRevenuePanel;
	private JPanel stOrdersPanel;
	private XChartPanel<XYChart> stRevenueChartPanel;

	private JLabel noOrdersLabel;
	private JLabel label;

	/**
	 * Create the frame.
	 */
	public Dashboard(AuthenticationController auth) {
		setTitle("Vestbjerg Byggecenter System");
		this.auth = auth;
		
		// Window
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 350);
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
		sellPanel.setBorder(new EmptyBorder(15, 0, 0, 0));
		tabsPane.addTab("Sell", null, sellPanel, "Sell Products");
		sellPanel.setLayout(new BorderLayout(0, 0));
		
		sellPaneTopPanel = new JPanel();
		sellPaneTopPanel.setBorder(new EmptyBorder(0, 0, 15, 0));
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
		ImageIcon sellIcon = new ImageIcon("images/shopping-cart.png");
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
		ImageIcon orderIcon = new ImageIcon("images/orders.png");
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
		loanPanel.setBorder(new EmptyBorder(15, 0, 0, 0));
		tabsPane.addTab("Loans", null, loanPanel, null);
		GridBagLayout gbl_loanPanel = new GridBagLayout();
		gbl_loanPanel.columnWidths = new int[]{417, 0};
		gbl_loanPanel.rowHeights = new int[]{0, 0, 0, 0, 0, 0};
		gbl_loanPanel.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_loanPanel.rowWeights = new double[]{1.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		loanPanel.setLayout(gbl_loanPanel);
		
		lblLoanImage = new JLabel();
		ImageIcon loanIcon = new ImageIcon("images/loan.png");
		lblLoanImage.setIcon(loanIcon);
		GridBagConstraints gbc_lblLoanImage = new GridBagConstraints();
		gbc_lblLoanImage.insets = new Insets(0, 0, 5, 0);
		gbc_lblLoanImage.gridx = 0;
		gbc_lblLoanImage.gridy = 1;
		loanPanel.add(lblLoanImage, gbc_lblLoanImage);
		
		btnManageLoans = new JButton("Manage Loans");
		GridBagConstraints gbc_btnManageLoans = new GridBagConstraints();
		gbc_btnManageLoans.insets = new Insets(0, 0, 5, 0);
		gbc_btnManageLoans.gridx = 0;
		gbc_btnManageLoans.gridy = 2;
		loanPanel.add(btnManageLoans, gbc_btnManageLoans);
		
		btnReturnLoan = new JButton("Return Loan");
		GridBagConstraints gbc_btnReturnLoan = new GridBagConstraints();
		gbc_btnReturnLoan.insets = new Insets(0, 0, 5, 0);
		gbc_btnReturnLoan.gridx = 0;
		gbc_btnReturnLoan.gridy = 3;
		loanPanel.add(btnReturnLoan, gbc_btnReturnLoan);
	}

	/*
	 * -------------------------------------------------------
	 * --------------------  Inventory tab -------------------
	 * -------------------------------------------------------
	 */
	public void initInventoryTab() {
		InventoryPanel = new JPanel();
		InventoryPanel.setToolTipText("");
		InventoryPanel.setBorder(new EmptyBorder(15, 0, 0, 0));
		tabsPane.addTab("Inventory", null, InventoryPanel, "Inventory");
		GridBagLayout gbl_InventoryPanel = new GridBagLayout();
		gbl_InventoryPanel.columnWidths = new int[]{0, 0, 0};
		gbl_InventoryPanel.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_InventoryPanel.columnWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
		gbl_InventoryPanel.rowWeights = new double[]{1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		InventoryPanel.setLayout(gbl_InventoryPanel);
		
		lblInventoryManagePic = new JLabel();
		ImageIcon manage = new ImageIcon("images/manage.png");
		lblInventoryManagePic.setIcon(manage);
		GridBagConstraints gbc_lblInventoryManagePic = new GridBagConstraints();
		gbc_lblInventoryManagePic.insets = new Insets(0, 0, 5, 5);
		gbc_lblInventoryManagePic.gridx = 0;
		gbc_lblInventoryManagePic.gridy = 1;
		InventoryPanel.add(lblInventoryManagePic, gbc_lblInventoryManagePic);
		
		lblInventoryRestockPic = new JLabel();
		ImageIcon restock = new ImageIcon("images/restock.png");
		lblInventoryRestockPic.setIcon(restock);
		GridBagConstraints gbc_lblInventoryRestockPic = new GridBagConstraints();
		gbc_lblInventoryRestockPic.insets = new Insets(0, 0, 5, 0);
		gbc_lblInventoryRestockPic.gridx = 1;
		gbc_lblInventoryRestockPic.gridy = 1;
		InventoryPanel.add(lblInventoryRestockPic, gbc_lblInventoryRestockPic);
		
		lblManage = new JLabel("Manage");
		GridBagConstraints gbc_lblManage = new GridBagConstraints();
		gbc_lblManage.insets = new Insets(0, 0, 5, 5);
		gbc_lblManage.gridx = 0;
		gbc_lblManage.gridy = 2;
		InventoryPanel.add(lblManage, gbc_lblManage);
		
		lblRestockItems = new JLabel("Re-stock items");
		GridBagConstraints gbc_lblRestockItems = new GridBagConstraints();
		gbc_lblRestockItems.insets = new Insets(0, 0, 5, 0);
		gbc_lblRestockItems.gridx = 1;
		gbc_lblRestockItems.gridy = 2;
		InventoryPanel.add(lblRestockItems, gbc_lblRestockItems);
		
		btnProducts = new JButton("Products");
		GridBagConstraints gbc_btnProducts = new GridBagConstraints();
		gbc_btnProducts.insets = new Insets(0, 0, 5, 5);
		gbc_btnProducts.gridx = 0;
		gbc_btnProducts.gridy = 3;
		InventoryPanel.add(btnProducts, gbc_btnProducts);
		
		btnSupplyOffers = new JButton("Supply Offers");
		GridBagConstraints gbc_btnSupplyOffers = new GridBagConstraints();
		gbc_btnSupplyOffers.insets = new Insets(0, 0, 5, 0);
		gbc_btnSupplyOffers.gridx = 1;
		gbc_btnSupplyOffers.gridy = 3;
		InventoryPanel.add(btnSupplyOffers, gbc_btnSupplyOffers);
		
		btnSupplyOrders = new JButton("Supply Orders");
		
		btnContractors = new JButton("Contractors");
		GridBagConstraints gbc_btnContractors = new GridBagConstraints();
		gbc_btnContractors.insets = new Insets(0, 0, 5, 5);
		gbc_btnContractors.gridx = 0;
		gbc_btnContractors.gridy = 4;
		InventoryPanel.add(btnContractors, gbc_btnContractors);
		GridBagConstraints gbc_btnSupplyOrders = new GridBagConstraints();
		gbc_btnSupplyOrders.insets = new Insets(0, 0, 5, 0);
		gbc_btnSupplyOrders.gridx = 1;
		gbc_btnSupplyOrders.gridy = 4;
		InventoryPanel.add(btnSupplyOrders, gbc_btnSupplyOrders);
		
		btnStorageLocations = new JButton("Storage locations");
		GridBagConstraints gbc_btnStorageLocations = new GridBagConstraints();
		gbc_btnStorageLocations.insets = new Insets(0, 0, 5, 5);
		gbc_btnStorageLocations.gridx = 0;
		gbc_btnStorageLocations.gridy = 5;
		InventoryPanel.add(btnStorageLocations, gbc_btnStorageLocations);
		
		btnShelves = new JButton("Shelves");
		GridBagConstraints gbc_btnShelves = new GridBagConstraints();
		gbc_btnShelves.insets = new Insets(0, 0, 5, 5);
		gbc_btnShelves.gridx = 0;
		gbc_btnShelves.gridy = 6;
		InventoryPanel.add(btnShelves, gbc_btnShelves);
		
		
		txtCustomer = new JTextField();
		txtCustomer.setEditable(false);
		sellPaneTopPanel.add(txtCustomer, BorderLayout.SOUTH);
		txtCustomer.setColumns(10); 
		
		lblSell = new JLabel();
		ImageIcon sellIcon = new ImageIcon("images/shopping-cart.png");
		lblSell.setIcon(sellIcon);
		GridBagConstraints gbc_lblSell = new GridBagConstraints();
		gbc_lblSell.fill = GridBagConstraints.VERTICAL;
		gbc_lblSell.insets = new Insets(0, 0, 5, 5);
		gbc_lblSell.gridx = 0;
		gbc_lblSell.gridy = 0;
		sellPaneBottomPanel.add(lblSell, gbc_lblSell);
	}
	
	
	/*
	 * -------------------------------------------------------
	 * ---------------------  People tab ---------------------
	 * -------------------------------------------------------
	 */
	public void initPeopleTab() {
		JPanel peoplePanel = new JPanel();
		peoplePanel.setBorder(new EmptyBorder(10, 0, 0, 0));
		tabsPane.addTab("People", null, peoplePanel, null);
		GridBagLayout gbl_peoplePanel = new GridBagLayout();
		gbl_peoplePanel.columnWidths = new int[]{0, 0, 0};
		gbl_peoplePanel.rowHeights = new int[]{0, 0, 0, 0, 0, 0};
		gbl_peoplePanel.columnWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
		gbl_peoplePanel.rowWeights = new double[]{1.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		peoplePanel.setLayout(gbl_peoplePanel);
		
		lblEmployee = new JLabel();
		ImageIcon employeeIcon = new ImageIcon("images/employees.png", "");
		lblEmployee.setIcon(employeeIcon);
		GridBagConstraints gbc_lblEmployee = new GridBagConstraints();
		gbc_lblEmployee.insets = new Insets(0, 0, 5, 5);
		gbc_lblEmployee.gridx = 0;
		gbc_lblEmployee.gridy = 1;
		peoplePanel.add(lblEmployee, gbc_lblEmployee);
		
		lblCustomer = new JLabel();
		ImageIcon customerIcon = new ImageIcon("images/customers.png", "");
		lblCustomer.setIcon(customerIcon);
		GridBagConstraints gbc_lblCustomer = new GridBagConstraints();
		gbc_lblCustomer.insets = new Insets(0, 0, 5, 0);
		gbc_lblCustomer.gridx = 1;
		gbc_lblCustomer.gridy = 1;
		peoplePanel.add(lblCustomer, gbc_lblCustomer);
		
		btnEmployee = new JButton("Employees");
		GridBagConstraints gbc_btnEmployee = new GridBagConstraints();
		gbc_btnEmployee.insets = new Insets(0, 0, 5, 5);
		gbc_btnEmployee.gridx = 0;
		gbc_btnEmployee.gridy = 2;
		peoplePanel.add(btnEmployee, gbc_btnEmployee);
		
		btnCustomer = new JButton("Customers");
		GridBagConstraints gbc_btnCustomer = new GridBagConstraints();
		gbc_btnCustomer.insets = new Insets(0, 0, 5, 0);
		gbc_btnCustomer.gridx = 1;
		gbc_btnCustomer.gridy = 2;
		peoplePanel.add(btnCustomer, gbc_btnCustomer);
		
		btnCustomerTypes = new JButton("Customer types");
		GridBagConstraints gbc_btnCustomerTypes = new GridBagConstraints();
		gbc_btnCustomerTypes.insets = new Insets(0, 0, 5, 0);
		gbc_btnCustomerTypes.gridx = 1;
		gbc_btnCustomerTypes.gridy = 3;
		peoplePanel.add(btnCustomerTypes, gbc_btnCustomerTypes);
	}
	
	/*
	 * -------------------------------------------------------
	 * -------------------  Statistics tab -------------------
	 * -------------------------------------------------------
	 */
	public void initStatisticsTab() {
		statisticsPanel = new JPanel();
		statisticsPanel.setBorder(new EmptyBorder(15, 0, 0, 0));
		tabsPane.addTab("Statistics", null, statisticsPanel, null);
		GridBagLayout gbl_statisticsPanel = new GridBagLayout();
		gbl_statisticsPanel.columnWidths = new int[]{63, 0};
		gbl_statisticsPanel.rowHeights = new int[]{63, 0};
		gbl_statisticsPanel.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_statisticsPanel.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		statisticsPanel.setLayout(gbl_statisticsPanel);
		
		stTabsPane = new JTabbedPane(JTabbedPane.LEFT);
		GridBagConstraints gbc_stTabsPane = new GridBagConstraints();
		gbc_stTabsPane.fill = GridBagConstraints.BOTH;
		gbc_stTabsPane.gridx = 0;
		gbc_stTabsPane.gridy = 0;
		statisticsPanel.add(stTabsPane, gbc_stTabsPane);
		
		stRevenuePanel = new JPanel();
		stTabsPane.addTab("Revenue", null, stRevenuePanel, null);
		GridBagLayout gbl_stRevenuePanel = new GridBagLayout();
		gbl_stRevenuePanel.columnWidths = new int[]{0, 0};
		gbl_stRevenuePanel.rowHeights = new int[]{0, 0};
		gbl_stRevenuePanel.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_stRevenuePanel.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		stRevenuePanel.setLayout(gbl_stRevenuePanel);
		
		// Revenue chart
		List<Order> orders = new OrderController().getOrders();
		stRevenueChartPanel = new OrdersChart(orders).getChart();
		GridBagConstraints gbc_stRevenueChartPanel = new GridBagConstraints();
		gbc_stRevenueChartPanel.fill = GridBagConstraints.BOTH;
		gbc_stRevenueChartPanel.gridx = 0;
		gbc_stRevenueChartPanel.gridy = 0;
		stRevenuePanel.add(stRevenueChartPanel, gbc_stRevenueChartPanel);
		
		stOrdersPanel = new JPanel();
		stTabsPane.addTab("Orders", null, stOrdersPanel, null);
		GridBagLayout gbl_stOrdersPanel = new GridBagLayout();
		gbl_stOrdersPanel.columnWidths = new int[]{0};
		gbl_stOrdersPanel.rowHeights = new int[]{0};
		gbl_stOrdersPanel.columnWeights = new double[]{Double.MIN_VALUE};
		gbl_stOrdersPanel.rowWeights = new double[]{Double.MIN_VALUE};
		stOrdersPanel.setLayout(gbl_stOrdersPanel);
		
		// Disable 'orders' tab in statistics
		stTabsPane.setEnabledAt(1, false);
	}
	
	/*
	 * *******************************************************
	 * *******************  EVENT HANDLERS *******************
	 * *******************************************************
	 */
	
	public void addEventHandlers() {
		
		// ***** Log out button *****
		btnLogout.addActionListener(e -> {
	    	if (Messages.confirm(Dashboard.this, "Are you sure you want to log out?", "Log Out?")) {
		    	auth.Logout();
				Login frame = new Login();
				frame.setVisible(true);
		    	// free up memory by destroying the current dashboard
		    	Dashboard.this.dispose();
	    	}
		});
		
		// ***** Choose customer button *****
		btnChooseCustomer.addActionListener(e -> {
			ChooseCustomer frame = new ChooseCustomer(auth);
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
				ManageShoppingCart frame = new ManageShoppingCart(auth, customer);
				frame.setVisible(true);
				if (frame.isSubmitPressed()) {
					ManageQuotes quoteFrame = new ManageQuotes(auth, customer);
					quoteFrame.setVisible(true);
					if (quoteFrame.isSubmitPressed()) {
						ManageOrder orderFrame = new ManageOrder(auth, customer);
						orderFrame.setVisible(true);
					}
				}
			}

		});
		
		btnViewQuotes.addActionListener(e -> {
			if (this.customer == null) {
				Messages.info(this, "Please choose a customer", "Choose a customer");
			} else {
				ManageQuotes frame = new ManageQuotes(auth, customer);
				frame.setVisible(true);
				if (frame.isSubmitPressed()) {
					ManageOrder orderFrame = new ManageOrder(auth, customer);
					orderFrame.setVisible(true);
				}
			}
		});
		
		btnViewOrders.addActionListener(e -> {
			if (this.customer == null) {
				Messages.info(this, "Please choose a customer", "Choose a customer");
			} else {
				ManageOrder orderFrame = new ManageOrder(auth, customer);
				orderFrame.setVisible(true);
			}
		});

		///////////////////////////////////////////////////////
		////////////////     Loans     //////////////////
		/////////////////////////////////////////////////////

		btnManageLoans.addActionListener(e -> {
			if (this.customer == null) {
				Messages.info(this, "Please choose a customer", "Choose a customer");
			} else {
				ManageLoans frame = new ManageLoans(auth, customer);
				frame.setVisible(true);
			}
		});
		
		btnReturnLoan.addActionListener(e -> {
			if (this.customer == null) {
				Messages.info(this, "Please choose a customer", "Choose a customer");
			} else {
				ChooseLoan frame = new ChooseLoan(auth, customer);
				frame.setVisible(true);
				Loan loan = frame.getSelectedLoan();
				if (frame.getSelectedLoan() != null) {
					System.out.println(loan.isReturned());
					LoanUI loanFrame = new LoanUI(auth, frame.getSelectedLoan(), customer, LoanUI.Mode.RETURN);
					loanFrame.setVisible(true);
					System.out.println(loan.isReturned());
				}
			}
		});
		///////////////////////////////////////////////////////
		////////////////     Inventory     //////////////////
		/////////////////////////////////////////////////////
		
		// ***** Manage Contractors *****
		btnContractors.addActionListener(e -> {
			ManageContractor frame = new ManageContractor(auth);
			frame.setVisible(true);
		});
		
		// ***** Manage Storage locations *****
		btnStorageLocations.addActionListener(e -> {
			ManageStorageLocation frame = new ManageStorageLocation(auth);
			frame.setVisible(true);
		});
		
		// ***** Manage Products *****
		btnProducts.addActionListener(e -> {
			ManageProduct frame = new ManageProduct(auth);
			frame.setVisible(true);
		});

		//btnShelves
		btnShelves.addActionListener(e -> {
			ManageShelf frame = new ManageShelf(auth);
			frame.setVisible(true);
		});
		
		// ***** Manage Supply Offers *****
		btnSupplyOffers.addActionListener(e -> {
			ManageSupplyOffer frame = new ManageSupplyOffer(auth);
			frame.setVisible(true);
		});

		// ***** Manage Supply Orders *****
		btnSupplyOrders.addActionListener(e -> {
			ManageSupplyOrders frame = new ManageSupplyOrders(auth);
			frame.setVisible(true);
		});
		
		///////////////////////////////////////////////////////
		////////////////     People tab     //////////////////
		/////////////////////////////////////////////////////
		
		
		// ***** Manage customers *****
		btnCustomer.addActionListener(e -> {
			ManageCustomer frame = new ManageCustomer(auth);
			frame.setVisible(true);
		});
		
		// ***** Manage employees *****
		btnEmployee.addActionListener(e -> {
			ManageEmployee frame = new ManageEmployee(auth);
			frame.setVisible(true);
		});
		
		// ***** Manage customer types *****
		btnCustomerTypes.addActionListener(e -> {
			ManageCustomerType frame = new ManageCustomerType(auth);
			frame.setVisible(true);
		});
		
		///////////////////////////////////////////////////////
		////////////////     STATISTICS     //////////////////
		/////////////////////////////////////////////////////
		// Refresh revenue chart when you go to statistics tab.
		tabsPane.addChangeListener(e -> {
			// TODO: Hacky way. Improve it!
			if (tabsPane.getSelectedComponent().equals(statisticsPanel)) {
				List<Order> orders = new OrderController().getOrders();
				stRevenueChartPanel = new OrdersChart(orders).getChart();
				stRevenuePanel.removeAll();
				GridBagConstraints gbc_stRevenueChartPanel = new GridBagConstraints();
				gbc_stRevenueChartPanel.fill = GridBagConstraints.BOTH;
				gbc_stRevenueChartPanel.gridx = 0;
				gbc_stRevenueChartPanel.gridy = 0;
				stRevenuePanel.add(stRevenueChartPanel, gbc_stRevenueChartPanel);
				stRevenueChartPanel.revalidate();
				stRevenueChartPanel.repaint();
			}

		});
		
		
		
		
		
	} // end of event handlers
}
