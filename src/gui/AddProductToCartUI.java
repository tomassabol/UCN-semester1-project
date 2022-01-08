/**
 * 
 */
package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import gui.CRUDProductsPanel.Display;

import javax.swing.JLabel;

/**
 * @author Daniels Kanepe
 *
 */
public class AddProductToCartUI extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2968937670159813565L;
	private final JPanel contentPane;


	/**
	 * Create the dialog.
	 */
	public AddProductToCartUI() {
		setModal(true);
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout());
		
		CRUDProductsPanel panel = new CRUDProductsPanel(Display.BUYABLE);
		getContentPane().add(panel, BorderLayout.CENTER);
		
		// Attach event handlers
		this.addEventHandlers();
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
	private void addEventHandlers() {
	
	}
	
}


