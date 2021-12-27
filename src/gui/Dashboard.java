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

public class Dashboard extends JFrame {

	private JPanel contentPane;
	private AuthenticationController auth;

	/**
	 * Create the frame.
	 */
	public Dashboard(AuthenticationController auth) {
		this.auth = auth;
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{0, 0};
		gbl_contentPane.rowHeights = new int[]{0, 0};
		gbl_contentPane.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		JLabel lblGreeting = new JLabel("Hi, " + auth.getLoggedInUser().getFirstName());
		GridBagConstraints gbc_lblGreeting = new GridBagConstraints();
		gbc_lblGreeting.gridx = 0;
		gbc_lblGreeting.gridy = 0;
		contentPane.add(lblGreeting, gbc_lblGreeting);
	}

}
