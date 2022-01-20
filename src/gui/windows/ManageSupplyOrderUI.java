package gui.windows;


import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.AuthenticationController;
import gui.panels.CRUDSupplyOrder;
import model.Product;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;

public class ManageSupplyOrderUI extends JDialog {

	
	private static final long serialVersionUID = 2968937622159813565L;
	private final JPanel contentPane;
	private CRUDSupplyOrder supplyOrderPanel;
	
	AuthenticationController auth;
	Product product;

	/**
	 * Create the dialog.
	 */
	public ManageSupplyOrderUI(AuthenticationController auth, Product product) {
		this.auth = auth;
		this.product = product;
		this.setTitle("Manage Supply Orders");
		setModal(true);
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{420, 0};
		gbl_contentPane.rowHeights = new int[]{210, 0};
		gbl_contentPane.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		supplyOrderPanel = new CRUDSupplyOrder(auth, product);
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 0;
		getContentPane().add(supplyOrderPanel, gbc_panel);
		
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


