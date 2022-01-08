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
	private JLabel lblTest;
	private JButton btnSellItems;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	private JButton btnViewQuotes;
	private JButton btnViewOrders;
	private JPanel loanPanel;
	private JPanel statisticsPanel;

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
		
		lblTest = new JLabel("IMG");
		GridBagConstraints gbc_lblTest = new GridBagConstraints();
		gbc_lblTest.fill = GridBagConstraints.VERTICAL;
		gbc_lblTest.insets = new Insets(0, 0, 5, 5);
		gbc_lblTest.gridx = 0;
		gbc_lblTest.gridy = 0;
		sellPaneBottomPanel.add(lblTest, gbc_lblTest);
		
		lblNewLabel = new JLabel("IMG");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.fill = GridBagConstraints.VERTICAL;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 1;
		gbc_lblNewLabel.gridy = 0;
		sellPaneBottomPanel.add(lblNewLabel, gbc_lblNewLabel);
		
		lblNewLabel_1 = new JLabel("IMG");
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.fill = GridBagConstraints.VERTICAL;
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel_1.gridx = 2;
		gbc_lblNewLabel_1.gridy = 0;
		sellPaneBottomPanel.add(lblNewLabel_1, gbc_lblNewLabel_1);
		
		
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
		tabsPane.addTab("People", null, peoplePanel, null);
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
			customer = new CustomerController().findCustomerByID(0);
			if (customer != null) {
				txtCustomer.setText(String.format("(%s) %s %s", 
						customer.ID,
						customer.getFirstName(),
						customer.getLastName()));
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
				}
			}

		});
		
		btnViewQuotes.addActionListener(e -> {
			if (this.customer == null) {
				Messages.info(this, "Please choose a customer", "Choose a customer");
			} else {
				QuotesUI frame = new QuotesUI(auth, customer);
				frame.setVisible(true);
			}
		});
		
		
		
	} // end of event handlers
}
