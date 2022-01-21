package gui.windows;


import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.AuthenticationController;
import gui.panels.CRUDSupplyOrder;
import gui.panels.tableModels.SupplyOrderTableModel;
import model.Product;
import model.SupplyOrder;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JTable;

public class ChooseSupplyOrder extends JDialog {
	
	private static final long serialVersionUID = 2968937672159813565L;
	private final JPanel contentPane;
	private CRUDSupplyOrder supplyOrdersPanel;
	private JButton btnChoose;
	
	private SupplyOrder selectedSupplyOrder = null;
	
	AuthenticationController auth;
    Product product;


	/**
	 * Create the dialog.
	 */
	public ChooseSupplyOrder(AuthenticationController auth, Product product) {
		this.auth = auth;
        this.product =product;
		this.setTitle("Choose Supply Order...");
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
		
		supplyOrdersPanel = new CRUDSupplyOrder(auth, product);
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.insets = new Insets(0, 0, 5, 0);
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 0;
		getContentPane().add(supplyOrdersPanel, gbc_panel);
		
		btnChoose = new JButton("Choose...");
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
		public boolean isSupplyOrderSelected() {
			return selectedSupplyOrder != null;
		}
	
		public SupplyOrder getSelectedSupplyOrder() {
			return selectedSupplyOrder;
		}
	
	/*
	 * *******************************************************
	 * *******************  EVENT HANDLERS *******************
	 * *******************************************************
	 */
	private void addEventHandlers() {
		supplyOrdersPanel.getTable().getSelectionModel().addListSelectionListener(e -> {
			JTable table = supplyOrdersPanel.getTable();
			if (table.getSelectionModel().isSelectionEmpty()) {
				btnChoose.setEnabled(false);
			} else {
				btnChoose.setEnabled(true);
			}
			
		});
		
		// Choose button
		btnChoose.addActionListener(e -> {
			JTable table = supplyOrdersPanel.getTable();
			if (!table.getSelectionModel().isSelectionEmpty()) {
				SupplyOrderTableModel tableModel = supplyOrdersPanel.getTableModel();
				SupplyOrder supplyOrder = tableModel.getObj(table.getSelectedRow());
				selectedSupplyOrder = supplyOrder;

				this.dispose();
			}
		});
	}
	
}


