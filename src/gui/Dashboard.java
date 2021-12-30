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

public class Dashboard extends JFrame {

	private JPanel contentPane;
	private AuthenticationController auth;
	private String logoutText;
	private JLabel lblGreeting;
	private JLabel lblLogout;

	/**
	 * Create the frame.
	 */
	public Dashboard(AuthenticationController auth) {
		this.auth = auth;
		
		// Main content pane
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{375, 0};
		gbl_contentPane.rowHeights = new int[]{233, 15, 0};
		gbl_contentPane.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		// Top panel (greeting & log out)
		JPanel topPanel = new JPanel();
		GridBagConstraints gbc_topPanel = new GridBagConstraints();
		gbc_topPanel.fill = GridBagConstraints.BOTH;
		gbc_topPanel.insets = new Insets(0, 0, 5, 0);
		gbc_topPanel.gridx = 0;
		gbc_topPanel.gridy = 0;
		contentPane.add(topPanel, gbc_topPanel);
		GridBagLayout gbl_topPanel = new GridBagLayout();
		gbl_topPanel.columnWidths = new int[]{0, 0, 0, 0};
		gbl_topPanel.rowHeights = new int[]{0, 0, 0};
		gbl_topPanel.columnWeights = new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_topPanel.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		topPanel.setLayout(gbl_topPanel);
		
		// left-top:  greeting
		lblGreeting = new JLabel("Hi, " + auth.getLoggedInUser().getFirstName());
		GridBagConstraints gbc_lblGreeting = new GridBagConstraints();
		gbc_lblGreeting.insets = new Insets(0, 0, 5, 5);
		gbc_lblGreeting.gridx = 0;
		gbc_lblGreeting.gridy = 0;
		topPanel.add(lblGreeting, gbc_lblGreeting);
		
		// Right-top: Log out button
		logoutText = "Log out";
		lblLogout = new JLabel(logoutText);
		lblLogout.setForeground(Color.BLUE.darker());
		lblLogout.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		GridBagConstraints gbc_lblLogout = new GridBagConstraints();
		gbc_lblLogout.insets = new Insets(0, 0, 5, 0);
		gbc_lblLogout.gridx = 2;
		gbc_lblLogout.gridy = 0;
		topPanel.add(lblLogout, gbc_lblLogout);
		
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
