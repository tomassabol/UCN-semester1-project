package gui;


import javax.swing.JFrame;


import java.awt.Dimension;
import javax.swing.JComboBox;
import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import javax.swing.JButton;
import java.awt.GridBagConstraints;

import controller.AuthenticationController;
import controller.EmployeeController;
import model.Authentication;
import model.IFEmployee;

import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import java.awt.Insets;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Rectangle;
import java.awt.Component;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.awt.event.ActionEvent;

public class Login extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private JPanel contentPane;
	private JTextField txtEmail;
	private JLabel lblPassword;
	private JTextField txtPassword;
	private JButton btnLogin;

	/**
	 * Create the Login Window
	 */
	public Login() {
		setBounds(new Rectangle(0, 0, 0, 0));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 250, 250);
		contentPane = new JPanel();
		contentPane.setAlignmentY(0.0f);
		contentPane.setAlignmentX(0.0f);
		contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{200, 0};
		gbl_contentPane.rowHeights = new int[]{22, 0, 0, 0, 0, 6, 37, 0};
		gbl_contentPane.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		JLabel lblTitle = new JLabel("Log In");
		lblTitle.setFont(new Font("Dialog", Font.PLAIN, 30));
		GridBagConstraints gbc_lblTitle = new GridBagConstraints();
		gbc_lblTitle.anchor = GridBagConstraints.WEST;
		gbc_lblTitle.insets = new Insets(0, 0, 5, 0);
		gbc_lblTitle.gridx = 0;
		gbc_lblTitle.gridy = 0;
		contentPane.add(lblTitle, gbc_lblTitle);
		
		JLabel lblEmail = new JLabel("E-mail");
		lblEmail.setVerticalAlignment(SwingConstants.BOTTOM);
		GridBagConstraints gbc_lblEmail = new GridBagConstraints();
		gbc_lblEmail.anchor = GridBagConstraints.SOUTHWEST;
		gbc_lblEmail.insets = new Insets(0, 0, 5, 0);
		gbc_lblEmail.gridx = 0;
		gbc_lblEmail.gridy = 1;
		contentPane.add(lblEmail, gbc_lblEmail);
		
		txtEmail = new JTextField();
		txtEmail.setName("");
		GridBagConstraints gbc_txtEmail = new GridBagConstraints();
		gbc_txtEmail.anchor = GridBagConstraints.NORTH;
		gbc_txtEmail.insets = new Insets(0, 0, 5, 0);
		gbc_txtEmail.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtEmail.gridx = 0;
		gbc_txtEmail.gridy = 2;
		contentPane.add(txtEmail, gbc_txtEmail);
		txtEmail.setColumns(10);
		
		lblPassword = new JLabel("Password");
		lblPassword.setVerticalAlignment(SwingConstants.BOTTOM);
		GridBagConstraints gbc_lblPassword = new GridBagConstraints();
		gbc_lblPassword.anchor = GridBagConstraints.SOUTHWEST;
		gbc_lblPassword.insets = new Insets(0, 0, 5, 0);
		gbc_lblPassword.gridx = 0;
		gbc_lblPassword.gridy = 3;
		contentPane.add(lblPassword, gbc_lblPassword);
		
		txtPassword = new JTextField();
		txtPassword.setToolTipText("");
		txtPassword.setColumns(10);
		GridBagConstraints gbc_txtPassword = new GridBagConstraints();
		gbc_txtPassword.anchor = GridBagConstraints.NORTH;
		gbc_txtPassword.insets = new Insets(0, 0, 5, 0);
		gbc_txtPassword.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtPassword.gridx = 0;
		gbc_txtPassword.gridy = 4;
		contentPane.add(txtPassword, gbc_txtPassword);
		
		btnLogin = new JButton("Log In");

		btnLogin.setFont(new Font("Dialog", Font.PLAIN, 16));
		GridBagConstraints gbc_btnLogin = new GridBagConstraints();
		gbc_btnLogin.fill = GridBagConstraints.BOTH;
		gbc_btnLogin.gridx = 0;
		gbc_btnLogin.gridy = 6;
		contentPane.add(btnLogin, gbc_btnLogin);
		
		this.txtEmail.setText("admin@admin.com");
		this.txtPassword.setText("password");
		// Handle events
		addEventHandlers();
	}
	
	/*
	 * *******************************************************
	 * *******************  EVENT HANDLERS *******************
	 * *******************************************************
	 */
	public void addEventHandlers() {
		btnLogin.addActionListener(e -> {
		    String email = txtEmail.getText().trim();
		    String password = txtPassword.getText();
		    
		    // if empty, show error
		    if (email.isEmpty() || password.isEmpty()) {
	            Messages.error(this, "Email or password cannot be empty!");
	            return;
		    }
		    
		    // Log in
		    AuthenticationController auth = new AuthenticationController();
		    if (auth.login(email, password)) {
				DashboardUI frame = new DashboardUI(auth);
				frame.setVisible(true);
		    	// free up memory by destroying the current login form
		    	this.dispose();
		    } else {
		    	Messages.error(this, "The e-mail and/or password is incorrect.");
		    	return;
		    }
		    
		   
		});
	}
	
}
