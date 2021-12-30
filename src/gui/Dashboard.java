package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.AuthenticationController;
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

public class Dashboard extends JFrame {

	private JPanel contentPane;
	private AuthenticationController auth;
	private String logoutText;
	private JLabel lblGreeting;
	private JLabel lblLogout;
	private JTabbedPane tabsPane;
	private JPanel sellPane;
	private JPanel sellPaneTopPanel;
	private JPanel sellPaneBottomPanel;
	private JTextField textField;
	private JButton btnNewButton;
	private JLabel lblTest;
	private JButton btnSellAnItem;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	private JButton btnNewButton_1;
	private JButton btnNewButton_2;

	/**
	 * Create the frame.
	 */
	public Dashboard(AuthenticationController auth) {
		setTitle("Vestbjerg Byggecenter System");
		this.auth = auth;
		
		// *Main content pane
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
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
		lblLogout.setForeground(Color.BLUE.darker());
		lblLogout.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		GridBagConstraints gbc_lblLogout = new GridBagConstraints();
		gbc_lblLogout.insets = new Insets(0, 0, 5, 0);
		gbc_lblLogout.gridx = 2;
		gbc_lblLogout.gridy = 0;
		topPanel.add(lblLogout, gbc_lblLogout);
		
		// **Tabs
		tabsPane = new JTabbedPane(JTabbedPane.TOP);
		contentPane.add(tabsPane, BorderLayout.CENTER);
		
		/// *** 'Sell tab'
		sellPane = new JPanel();
		sellPane.setToolTipText("");
		sellPane.setBorder(null);
		tabsPane.addTab("Sell", null, sellPane, "Sell Products");
		sellPane.setLayout(new BorderLayout(0, 0));
		
		sellPaneTopPanel = new JPanel();
		sellPane.add(sellPaneTopPanel, BorderLayout.NORTH);
		sellPaneTopPanel.setLayout(new BorderLayout(0, 0));
		
		btnNewButton = new JButton("New button");
		sellPaneTopPanel.add(btnNewButton, BorderLayout.NORTH);
		
		textField = new JTextField();
		sellPaneTopPanel.add(textField, BorderLayout.SOUTH);
		textField.setColumns(10);
		
		sellPaneBottomPanel = new JPanel();
		sellPane.add(sellPaneBottomPanel, BorderLayout.CENTER);
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
		
		btnSellAnItem = new JButton("Sell items");
		GridBagConstraints gbc_btnSellAnItem = new GridBagConstraints();
		gbc_btnSellAnItem.insets = new Insets(0, 0, 0, 5);
		gbc_btnSellAnItem.gridx = 0;
		gbc_btnSellAnItem.gridy = 1;
		sellPaneBottomPanel.add(btnSellAnItem, gbc_btnSellAnItem);
		
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
		
		// Handle events
		addEventHandlers();
	}
	
	/*
	 * *******************************************************
	 * *******************  EVENT HANDLERS *******************
	 * *******************************************************
	 */
	
	public void addEventHandlers() {
		// Log out button
		lblLogout.addMouseListener(new MouseAdapter() {
			 
		    @Override
		    public void mouseClicked(MouseEvent e) {
		        // User clicked on logout
		    	if (Messages.confirmation(Dashboard.this, "Are you sure you want to log out?", "Log Out?")) {
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
	}
}
