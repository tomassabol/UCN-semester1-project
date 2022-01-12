package gui;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.AuthenticationController;
import controller.CustomerController;
import model.Customer;

import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Insets;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTabbedPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;

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
		ImageIcon sellIcon = new ImageIcon("images/coins.png", "");
		lblSell.setIcon(sellIcon);
		GridBagConstraints gbc_lblSell = new GridBagConstraints();
		gbc_lblSell.fill = GridBagConstraints.VERTICAL;
		gbc_lblSell.insets = new Insets(0, 0, 5, 5);
		gbc_lblSell.gridx = 0;
		gbc_lblSell.gridy = 0;
		sellPaneBottomPanel.add(lblSell, gbc_lblSell);
		
		lblQuotes = new JLabel("");
		ImageIcon quoteIcon = new ImageIcon("images/quotes.png", "");
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
		JPanel inventoryPanel = new JPanel();
		tabsPane.addTab("Inventory", null, inventoryPanel, null);
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
			ChooseCustomerUI frame = new ChooseCustomerUI();
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
		////////////////     People tab     //////////////////
		/////////////////////////////////////////////////////
		
		// ***** Customer button *****
		btnCustomer.addActionListener(e -> {
			ChooseCustomerUI frame = new ChooseCustomerUI();
			frame.setVisible(true);
		});
		
		// we need to create CRUDEmployeesPanel
		btnEmployee.addActionListener(e -> {
			ChooseEmployeeUI frame = new ChooseEmployeeUI();
			frame.setVisible(true);
		});
		
		
		
		
		
	} // end of event handlers
}
