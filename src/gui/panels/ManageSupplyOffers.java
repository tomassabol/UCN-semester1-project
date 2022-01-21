package gui.panels;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.AuthenticationController;
import controller.StockController;
import controller.SupplyController;
import gui.panels.CRUDSupplyOffers;
import model.Product;
import java.awt.Insets;
import javax.swing.JTextField;
import javax.swing.JLabel;

public class ManageSupplyOffers extends JDialog {

	private SupplyController supplyCtrl;
	
	private final JPanel contentPane;
	private CRUDSupplyOffers CRUDPanel;
	
	AuthenticationController auth;
	private JTextField txtProductDisplay;

	/**
	 * Create the dialog.
	 */
	public ManageSupplyOffers(AuthenticationController auth, Product product) {
		this.auth = auth;
		this.setTitle("Manage supply offers");
		supplyCtrl = new SupplyController();
		setModal(true);
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{420, 0, 0};
		gbl_contentPane.rowHeights = new int[]{0, 0, 210, 0};
		gbl_contentPane.columnWeights = new double[]{1.0, 0.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 1.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		JLabel lblChooseAProduct = new JLabel("Choose a product...");
		GridBagConstraints gbc_lblChooseAProduct = new GridBagConstraints();
		gbc_lblChooseAProduct.gridwidth = 2;
		gbc_lblChooseAProduct.insets = new Insets(0, 0, 5, 5);
		gbc_lblChooseAProduct.gridx = 0;
		gbc_lblChooseAProduct.gridy = 0;
		contentPane.add(lblChooseAProduct, gbc_lblChooseAProduct);
		
		txtProductDisplay = new JTextField();
		txtProductDisplay.setEnabled(false);
		txtProductDisplay.setText("Product");
		GridBagConstraints gbc_txtProductDisplay = new GridBagConstraints();
		gbc_txtProductDisplay.insets = new Insets(0, 0, 5, 5);
		gbc_txtProductDisplay.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtProductDisplay.gridx = 0;
		gbc_txtProductDisplay.gridy = 1;
		contentPane.add(txtProductDisplay, gbc_txtProductDisplay);
		txtProductDisplay.setColumns(10);
		
		JButton btnChoose = new JButton("Choose...");
		GridBagConstraints gbc_btnChoose = new GridBagConstraints();
		gbc_btnChoose.insets = new Insets(0, 0, 5, 0);
		gbc_btnChoose.gridx = 1;
		gbc_btnChoose.gridy = 1;
		contentPane.add(btnChoose, gbc_btnChoose);
		
		CRUDPanel = new CRUDSupplyOffers(auth, product);
		GridBagConstraints gbc_CRUDPanel = new GridBagConstraints();
		gbc_CRUDPanel.gridwidth = 2;
		gbc_CRUDPanel.fill = GridBagConstraints.BOTH;
		gbc_CRUDPanel.gridx = 0;
		gbc_CRUDPanel.gridy = 2;
		getContentPane().add(CRUDPanel, gbc_CRUDPanel);
		
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
