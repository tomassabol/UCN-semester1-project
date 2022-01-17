/**
 * 
 */
package gui;


import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.AuthenticationController;
import controller.StockController;
import model.Product;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JTable;
/**
 * @author Daniels Kanepe
 *
 */
public class ManageProductUI extends JDialog {

	/**
	 * 
	 */
	private StockController stockCtrl;
	
	private static final long serialVersionUID = 2968937622159813565L;
	private final JPanel contentPane;
	private CRUDProductsPanel CRUDPanel;
	
	private Product selectedProduct = null;
	
	AuthenticationController auth;


	/**
	 * Create the dialog.
	 */
	public ManageProductUI(AuthenticationController auth) {
		this.auth = auth;
		this.setTitle("Manage products");
		stockCtrl = new StockController();
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
		
		CRUDPanel = new CRUDProductsPanel(auth, CRUDProductsPanel.Mode.ALL);
		GridBagConstraints gbc_CRUDPanel = new GridBagConstraints();
		gbc_CRUDPanel.fill = GridBagConstraints.BOTH;
		gbc_CRUDPanel.gridx = 0;
		gbc_CRUDPanel.gridy = 0;
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


