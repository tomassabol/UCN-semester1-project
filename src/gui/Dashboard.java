package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import java.awt.Color;
import java.awt.Cursor;

import javax.swing.UIManager;
import javax.swing.JTabbedPane;
import javax.swing.border.TitledBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;

public class Dashboard extends JFrame {
	
	private Customer customer = null;
	

	private JPanel contentPane;
	private AuthenticationController auth;
	private String logoutText;
	private JLabel lblGreeting;
	private JLabel lblLogout;
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
	private JButton btnNewButton_1;
	private JButton btnNewButton_2;
	private JPanel loanPanel;
	private JPanel statisticsPanel;

	/**
	 * Create the frame.
	 */
	public Dashboard(AuthenticationController auth) {
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
			GridBagLayout gbl_topPanel = new GridBagLayout();
			gbl_topPanel.columnWidths = new int[]{0, 0, 0, 0};
			gbl_topPanel.rowHeights = new int[]{0, 0, 0};
			gbl_topPanel.columnWeights = new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE};
			gbl_topPanel.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
			topPanel.setLayout(gbl_topPanel);
		
				// ***left-top:  greeting
				lblGreeting = new JLabel("Hi, " + auth.getLoggedInUser().getFirstName());
				GridBagConstraints gbc_lblGreeting = new GridBagConstraints();
				gbc_lblGreeting.insets = new Insets(0, 0, 5, 5);
				gbc_lblGreeting.gridx = 0;
				gbc_lblGreeting.gridy = 0;
				topPanel.add(lblGreeting, gbc_lblGreeting);
		
				// ***Right-top: Log out button
				logoutText = "Log out";
				lblLogout = new JLabel(logoutText);
				lblLogout.setForeground(ColorPalette.LINK_COLOR);
				lblLogout.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
				GridBagConstraints gbc_lblLogout = new GridBagConstraints();
				gbc_lblLogout.insets = new Insets(0, 0, 5, 0);
				gbc_lblLogout.gridx = 2;
				gbc_lblLogout.gridy = 0;
				topPanel.add(lblLogout, gbc_lblLogout);
		
			// **Tabs
			tabsPane = new JTabbedPane(JTabbedPane.TOP);
			contentPane.add(tabsPane, BorderLayout.CENTER);
		
				/// *** Sell tab
				this.initializeSellTab();
		
			/// *** Loans tab
			loanPanel = new JPanel();
			tabsPane.addTab("Loans", null, loanPanel, null);
			
			/// *** Inventory tab
			JPanel inventoryPanel = new JPanel();
			tabsPane.addTab("Inventory", null, inventoryPanel, null);
			
			/// *** People tab
			JPanel peoplePanel = new JPanel();
			tabsPane.addTab("People", null, peoplePanel, null);
			
			/// *** Statistcs tab
			statisticsPanel = new JPanel();
			tabsPane.addTab("Statistics", null, statisticsPanel, null);
			
		// Handle events
		addEventHandlers();
	}
	
	/*
	 * -------------------------------------------------------
	 * ----------------------  Sell tab ----------------------
	 * -------------------------------------------------------
	 */
	public void initializeSellTab() {
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
		
		btnNewButton_1 = new JButton("Quotes");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		GridBagConstraints gbc_btnNewButton_1 = new GridBagConstraints();
		gbc_btnNewButton_1.insets = new Insets(0, 0, 0, 5);
		gbc_btnNewButton_1.gridx = 1;
		gbc_btnNewButton_1.gridy = 1;
		sellPaneBottomPanel.add(btnNewButton_1, gbc_btnNewButton_1);
		
		btnNewButton_2 = new JButton("Orders");
		GridBagConstraints gbc_btnNewButton_2 = new GridBagConstraints();
		gbc_btnNewButton_2.gridx = 2;
		gbc_btnNewButton_2.gridy = 1;
		sellPaneBottomPanel.add(btnNewButton_2, gbc_btnNewButton_2);
	}
	
	/*
	 * *******************************************************
	 * *******************  EVENT HANDLERS *******************
	 * *******************************************************
	 */
	
	public void addEventHandlers() {
		// ***** Log out button *****
		lblLogout.addMouseListener(new MouseAdapter() {
			 
		    @Override
		    public void mouseClicked(MouseEvent e) {
		        // User clicked on logout
		    	if (Messages.confirm(Dashboard.this, "Are you sure you want to log out?", "Log Out?")) {
			    	auth.Logout();
					Login frame = new Login();
					frame.setVisible(true);
			    	// free up memory by destroying the current dashboard
			    	Dashboard.this.dispose();
		    	}

		    	
		    }
		    
		    @Override
		    public void mouseEntered(MouseEvent e) {
		        // the mouse is on the label: underline it
		    	lblLogout.setText(String.format("<html><a href=''>%s</a></html>", logoutText));
		    }
		 
		    @Override
		    public void mouseExited(MouseEvent e) {
		        // the mouse has exited the label: set back to original
		    	lblLogout.setText(logoutText);
		    }
		});
		
		// ***** Sell items button *****
		btnSellItems.addActionListener(e -> {
			if (this.customer == null) {
				Messages.info(this, "Please choose a customer", "Choose a customer");
			} else {
				ShoppingCart frame = new ShoppingCart();
				frame.setVisible(true);
			}

		});
		
		// ***** Choose customer button *****
		btnChooseCustomer.addActionListener(e -> {
			customer = new CustomerController().findCustomerByID(0);
			if (customer != null) {
				this.txtCustomer.setText(customer.getFirstName() + " " + customer.getLastName());
			}
		});
		
		
		
	} // end of event handlers
}
