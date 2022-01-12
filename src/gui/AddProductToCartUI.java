/**
 * 
 */
package gui;


import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.StockController;
import gui.CRUDProductsPanel.Mode;
import model.Product;

import javax.swing.JLabel;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.SpinnerNumberModel;


/**
 * @author Daniels Kanepe
 *
 */
public class AddProductToCartUI extends JDialog {

	/**
	 * 
	 */
	private StockController stockCtrl;
	
	private static final long serialVersionUID = 2968937670159813565L;
	private final JPanel contentPane;
	private CRUDProductsPanel productsPanel;
	private JButton btnChoose;
	private JSpinner spinnerQuantity;
	
	private Product selectedProduct = null;
	private int selectedQuantity;


	/**
	 * Create the dialog.
	 */
	public AddProductToCartUI() {
		this.setTitle("Choose product to add to cart");
		stockCtrl = new StockController();
		setModal(true);
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{0, 420, 0, 0};
		gbl_contentPane.rowHeights = new int[]{210, 25, 0};
		gbl_contentPane.columnWeights = new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{1.0, 0.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		productsPanel = new CRUDProductsPanel(Mode.BUYABLE);
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.gridwidth = 3;
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.insets = new Insets(0, 0, 5, 0);
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 0;
		getContentPane().add(productsPanel, gbc_panel);
		
		JLabel lblQuantity = new JLabel("Quantity");
		GridBagConstraints gbc_lblQuantity = new GridBagConstraints();
		gbc_lblQuantity.anchor = GridBagConstraints.EAST;
		gbc_lblQuantity.insets = new Insets(0, 0, 0, 5);
		gbc_lblQuantity.gridx = 0;
		gbc_lblQuantity.gridy = 1;
		contentPane.add(lblQuantity, gbc_lblQuantity);
		
		spinnerQuantity = new JSpinner();
		spinnerQuantity.setModel(new SpinnerNumberModel(new Integer(1), null, null, new Integer(1)));
		spinnerQuantity.setEnabled(false);
		GridBagConstraints gbc_spinnerQuantity = new GridBagConstraints();
		gbc_spinnerQuantity.anchor = GridBagConstraints.WEST;
		gbc_spinnerQuantity.insets = new Insets(0, 0, 0, 5);
		gbc_spinnerQuantity.gridx = 1;
		gbc_spinnerQuantity.gridy = 1;
		contentPane.add(spinnerQuantity, gbc_spinnerQuantity);
		
		btnChoose = new JButton("Choose...");
		btnChoose.setEnabled(false);
		GridBagConstraints gbc_btnChoose = new GridBagConstraints();
		gbc_btnChoose.gridx = 2;
		gbc_btnChoose.gridy = 1;
		contentPane.add(btnChoose, gbc_btnChoose);
		
		// Attach event handlers
		this.addEventHandlers();
	}
	/*
	 * *******************************************************
	 * *******************  Methods *******************
	 * *******************************************************
	 */
		public boolean isProductSelected() {
			return selectedProduct != null;
		}
	
		public Product getSelectedProduct() {
			return selectedProduct;
		}
		
		public int getSelectedQuantity() {
			return selectedQuantity;
		}
	
	/*
	 * *******************************************************
	 * *******************  EVENT HANDLERS *******************
	 * *******************************************************
	 */
	private void addEventHandlers() {
		productsPanel.getTable().getSelectionModel().addListSelectionListener(e -> {
			JTable table = productsPanel.getTable();
			if (table.getSelectionModel().isSelectionEmpty()) {
				spinnerQuantity.setEnabled(false);
				btnChoose.setEnabled(false);
			} else {
				ProductTableModel tableModel = productsPanel.getTableModel();
				Product product = tableModel.getProduct(table.getSelectedRow());
				// Enable only if buyable quantity > 0 & product is enabled
				int buyableQuantity = stockCtrl.getBuyableQuantityInStock(product);
				if (buyableQuantity > 0 && product.isEnabled()) {
					btnChoose.setEnabled(true);
					spinnerQuantity.setEnabled(true);
					spinnerQuantity.setModel(
							new SpinnerNumberModel(1, 1, buyableQuantity, 1)
					);
				}
			}
			
		});
		
		// Choose button
		btnChoose.addActionListener(e -> {
			JTable table = productsPanel.getTable();
			if (!table.getSelectionModel().isSelectionEmpty()) {
				ProductTableModel tableModel = productsPanel.getTableModel();
				Product product = tableModel.getProduct(table.getSelectedRow());
				selectedProduct = product;
				selectedQuantity = (int) spinnerQuantity.getValue();
				this.dispose();
			}
		});
	}
	
}