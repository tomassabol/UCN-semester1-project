package gui.windows;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import controller.AuthenticationController;
import controller.SupplyController;
import gui.JButtonPrimary;
import gui.panels.CRUDSupplyOrders;
import gui.panels.tableModels.SupplyOrderTableModel;
import models.Product;
import models.SupplyOrder;

/**
 * @author Daniels Kanepe
 *
 */
public class ManageSupplyOrders extends JDialog {

	private static final long serialVersionUID = -6693315350035542078L;

	private SupplyController supplyCtrl;
	
	private final JPanel contentPane;
	private CRUDSupplyOrders CRUDPanel;
	
	AuthenticationController auth;
	private JButton btnChooseProduct;
	private JTextField txtProductDisplay;
	private JButton btnClear;
	private JButtonPrimary btnPutIntoStock;

	/**
	 * Create the dialog.
	 */
	public ManageSupplyOrders(AuthenticationController auth) {
		this.auth = auth;
		this.setTitle("Manage supply orders");
		supplyCtrl = new SupplyController();
		setModal(true);
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 700, 450);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{420, 0, 0, 0};
		gbl_contentPane.rowHeights = new int[]{0, 0, 210, 0, 0};
		gbl_contentPane.columnWeights = new double[]{1.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		JLabel lblChooseAProduct = new JLabel("Filter by product");
		GridBagConstraints gbc_lblChooseAProduct = new GridBagConstraints();
		gbc_lblChooseAProduct.gridwidth = 3;
		gbc_lblChooseAProduct.insets = new Insets(0, 0, 5, 0);
		gbc_lblChooseAProduct.gridx = 0;
		gbc_lblChooseAProduct.gridy = 0;
		contentPane.add(lblChooseAProduct, gbc_lblChooseAProduct);
		
		txtProductDisplay = new JTextField();
		txtProductDisplay.setEnabled(false);
		txtProductDisplay.setText("Choose a product...");
		GridBagConstraints gbc_txtProductDisplay = new GridBagConstraints();
		gbc_txtProductDisplay.insets = new Insets(0, 0, 5, 5);
		gbc_txtProductDisplay.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtProductDisplay.gridx = 0;
		gbc_txtProductDisplay.gridy = 1;
		contentPane.add(txtProductDisplay, gbc_txtProductDisplay);
		txtProductDisplay.setColumns(10);
		
		btnChooseProduct = new JButton("Choose...");
		GridBagConstraints gbc_btnChooseProduct = new GridBagConstraints();
		gbc_btnChooseProduct.insets = new Insets(0, 0, 5, 5);
		gbc_btnChooseProduct.gridx = 1;
		gbc_btnChooseProduct.gridy = 1;
		contentPane.add(btnChooseProduct, gbc_btnChooseProduct);
		
		btnClear = new JButton();
		btnClear.setIcon(new ImageIcon("images/clear-filter.png"));
		GridBagConstraints gbc_btnClear = new GridBagConstraints();
		gbc_btnClear.insets = new Insets(0, 0, 5, 0);
		gbc_btnClear.gridx = 2;
		gbc_btnClear.gridy = 1;
		contentPane.add(btnClear, gbc_btnClear);
		
		CRUDPanel = new CRUDSupplyOrders(auth);
		GridBagConstraints gbc_CRUDPanel = new GridBagConstraints();
		gbc_CRUDPanel.insets = new Insets(0, 0, 5, 0);
		gbc_CRUDPanel.gridwidth = 3;
		gbc_CRUDPanel.fill = GridBagConstraints.BOTH;
		gbc_CRUDPanel.gridx = 0;
		gbc_CRUDPanel.gridy = 2;
		getContentPane().add(CRUDPanel, gbc_CRUDPanel);
		
		btnPutIntoStock = new JButtonPrimary("Put into stock");
		GridBagConstraints gbc_btnPutIntoStock = new GridBagConstraints();
		gbc_btnPutIntoStock.anchor = GridBagConstraints.EAST;
		gbc_btnPutIntoStock.gridwidth = 3;
		gbc_btnPutIntoStock.gridx = 0;
		gbc_btnPutIntoStock.gridy = 3;
		contentPane.add(btnPutIntoStock, gbc_btnPutIntoStock);
		
		// disabled by default
		btnPutIntoStock.setEnabled(false);
		
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
		
		// Choose product button
		btnChooseProduct.addActionListener(e -> {
			ChooseProduct frame = new ChooseProduct(auth, ChooseProduct.Mode.ALL);
			frame.setVisible(true);
			if (frame.getSelectedProduct() != null) {
				Product product = frame.getSelectedProduct();
				txtProductDisplay.setText(product.getName());
				// Filter
				CRUDPanel.getTable().setModel(new SupplyOrderTableModel(supplyCtrl.getSupplyOrders(product)));
			}
		});
		
		// Clear button
		btnClear.addActionListener(e -> {
			// Reset 'choose' btn display text
			txtProductDisplay.setText("");
			// reset filter (show all products)
			CRUDPanel.getTable().setModel(new SupplyOrderTableModel(supplyCtrl.getSupplyOrders()));
		});
		
		// Toggle 'Put into stock' button depending on if there is a selection 
		// and if the supply order has already been delivered
		CRUDPanel.getTable().getSelectionModel().addListSelectionListener(e -> {
			JTable table = CRUDPanel.getTable();
			if (table.getSelectionModel().isSelectionEmpty()) {
				btnPutIntoStock.setEnabled(false);
			} else {
				// Get selected supply order
				int row = table.getSelectedRow();
				SupplyOrder supplyOrder = CRUDPanel.getTableModel().getObj(row);
				// enable 'put into stock' if not delivered
				if (!supplyOrder.isStocked()) {
					btnPutIntoStock.setEnabled(true);
				} else {
					btnPutIntoStock.setEnabled(false);
				}
				
			}
			
		});
		
		btnPutIntoStock.addActionListener(e -> {
			// get selected supply order
			JTable table = CRUDPanel.getTable();
			int row = table.convertRowIndexToModel(table.getSelectedRow());
			SupplyOrder supplyOrder = CRUDPanel.getTableModel().getObj(row);
			// Show UI
			StockSupplyOrder frame = new StockSupplyOrder(auth, supplyOrder);
			frame.setVisible(true);
			// Update row in case there were changes (delivered field might be true now)
			CRUDPanel.getTableModel().fireTableRowsUpdated(row, row);
			// Refresh selection (to execute the selection event again and toggle the relevant buttons)
			table.getSelectionModel().clearSelection();
			table.getSelectionModel().setSelectionInterval(0, row);
		});
		
		
	}

}