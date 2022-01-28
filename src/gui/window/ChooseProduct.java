package gui.window;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.AuthenticationController;
import controller.StockController;
import gui.JButtonPrimary;
import gui.panel.CRUDProducts;
import gui.panels.tableModel.ProductTableModel;
import model.Product;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

import javax.swing.JTable;
/**
 * @author Daniels Kanepe
 *
 */
public class ChooseProduct extends JDialog {

	private static final long serialVersionUID = 2968937672159813565L;
	private final JPanel contentPane;
	private CRUDProducts CRUDPanel;
	private JButtonPrimary btnChoose;
	
	private Product selectedProduct = null;
	
	AuthenticationController auth;
	Mode mode;

	public enum Mode {
		BUYABLE,
		LOANABLE,
		ALL;
	}

	/**
	 * Create the dialog.
	 */
	public ChooseProduct(AuthenticationController auth, Mode mode) {
		this.auth = auth;
		this.mode = mode;
		
		this.setTitle("Choose a product...");
		setModal(true);
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{420, 0};
		gbl_contentPane.rowHeights = new int[]{210, 25, 0};
		gbl_contentPane.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{1.0, 0.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		switch (mode) {
			case BUYABLE:
				CRUDPanel = new CRUDProducts(auth, CRUDProducts.Mode.BUYABLE);
				break;
			case LOANABLE:
				CRUDPanel = new CRUDProducts(auth, CRUDProducts.Mode.LOANABLE);
				break;
			default:
				CRUDPanel = new CRUDProducts(auth, CRUDProducts.Mode.ALL);
		}
		
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.insets = new Insets(0, 0, 5, 0);
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 0;
		getContentPane().add(CRUDPanel, gbc_panel);
		
		btnChoose = new JButtonPrimary("Choose...");
		btnChoose.setEnabled(false);
		GridBagConstraints gbc_btnChoose = new GridBagConstraints();
		gbc_btnChoose.anchor = GridBagConstraints.EAST;
		gbc_btnChoose.gridx = 0;
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

	
	/*
	 * *******************************************************
	 * *******************  EVENT HANDLERS *******************
	 * *******************************************************
	 */
	private void addEventHandlers() {
		CRUDPanel.getTable().getSelectionModel().addListSelectionListener(e -> {
			JTable table = CRUDPanel.getTable();
			// Toggle 'choose' button
			if (table.getSelectionModel().isSelectionEmpty()) {
				// ** not selected **
				btnChoose.setEnabled(false);
			} else {
				//** selected**
				
				// get product
				int row = table.convertRowIndexToModel(table.getSelectedRow());
				Product product = CRUDPanel.getTableModel().getObj(row);
				
				// get quantity in stock
				int quantityInStock = Integer.MAX_VALUE;
				if (this.mode == Mode.BUYABLE) {
					quantityInStock = new StockController().getBuyableQuantityInStock(product);
				} else if (this.mode == Mode.LOANABLE) {
					quantityInStock = new StockController().getLoanableQuantityInStock(product);
				}
				// enable choose button only if stock > 0 && product is enabled
				if (quantityInStock > 0 && product.isEnabled()) {
					btnChoose.setEnabled(true);
				}
			}
			
		});
		
		// Choose button
		btnChoose.addActionListener(e -> {
			JTable table = CRUDPanel.getTable();
			if (!table.getSelectionModel().isSelectionEmpty()) {
				ProductTableModel tableModel = CRUDPanel.getTableModel();
				Product product = tableModel.getObj(table.getSelectedRow());
				selectedProduct = product;
				this.dispose();
			}
		});
	}
	
}


