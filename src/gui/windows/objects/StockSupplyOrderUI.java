package gui.windows.objects;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.AuthenticationController;
import controller.SupplyController;
import gui.Messages;
import gui.windows.ChooseShelf;
import model.Shelf;
import model.SupplyOrder;

import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import javax.swing.JTextField;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JRadioButton;

/**
 * @author Daniels Kanepe
 *
 */
public class StockSupplyOrderUI extends JDialog {

    private JPanel contentPane;
	private JTextField txtSupplyOrder;
	private JTextField txtProduct;
	private JButton btnSubmit;

	AuthenticationController auth;
    SupplyOrder supplyOrder;
    Shelf shelf = null;
    SupplyController supplyCtrl;

	private JLabel lblQuantity;
	private JPanel storageLocationPanel;
	private JTextField txtQuantity;
	private JLabel lblShelf;
	private JTextField txtShelf;
	private JButton btnChooseShelf;
	private JLabel lblTrackable;
	private JPanel isTrackable;
	private JRadioButton rdbtnTrackableYes;
	private JRadioButton rdbtnTrackableNo;

	/**
	 * @param auth the auth
	 * @param supplyOrder the supplyOrder
     * @wbp.parser.constructor
	 */
	public StockSupplyOrderUI(AuthenticationController auth, SupplyOrder supplyOrder) {
		this.supplyOrder = supplyOrder;
		this.auth = auth;

        supplyCtrl = new SupplyController();
		
		setModal(true);
		setBounds(100, 100, 450, 341);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[]{273, 0, 0};
		gbl_contentPanel.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_contentPanel.columnWeights = new double[]{1.0, 0.0, Double.MIN_VALUE};
		gbl_contentPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 1.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPanel);
		
		JLabel lblSupplyOrder = new JLabel("Supply Order ID");
		GridBagConstraints gbc_lblSupplyOrder = new GridBagConstraints();
		gbc_lblSupplyOrder.anchor = GridBagConstraints.SOUTHWEST;
		gbc_lblSupplyOrder.insets = new Insets(0, 0, 5, 5);
		gbc_lblSupplyOrder.gridx = 0;
		gbc_lblSupplyOrder.gridy = 0;
		contentPane.add(lblSupplyOrder, gbc_lblSupplyOrder);
		
		
		txtSupplyOrder = new JTextField();
		txtSupplyOrder.setEnabled(false);
		txtSupplyOrder.setEditable(false);
		GridBagConstraints gbc_txtSupplyOrder = new GridBagConstraints();
		gbc_txtSupplyOrder.anchor = GridBagConstraints.NORTH;
		gbc_txtSupplyOrder.insets = new Insets(0, 0, 5, 5);
		gbc_txtSupplyOrder.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtSupplyOrder.gridx = 0;
		gbc_txtSupplyOrder.gridy = 1;
		contentPane.add(txtSupplyOrder, gbc_txtSupplyOrder);
		txtSupplyOrder.setColumns(10);
		
		
		JLabel lblProduct = new JLabel("Product");
		GridBagConstraints gbc_lblProduct = new GridBagConstraints();
		gbc_lblProduct.anchor = GridBagConstraints.SOUTHWEST;
		gbc_lblProduct.insets = new Insets(0, 0, 5, 5);
		gbc_lblProduct.gridx = 0;
		gbc_lblProduct.gridy = 2;
		contentPane.add(lblProduct, gbc_lblProduct);
		
		
		txtProduct = new JTextField();
		txtProduct.setEnabled(false);
		txtProduct.setEditable(false);
		GridBagConstraints gbc_txtProduct = new GridBagConstraints();
		gbc_txtProduct.anchor = GridBagConstraints.NORTH;
		gbc_txtProduct.insets = new Insets(0, 0, 5, 5);
		gbc_txtProduct.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtProduct.gridx = 0;
		gbc_txtProduct.gridy = 3;
		contentPane.add(txtProduct, gbc_txtProduct);
		txtProduct.setColumns(10);
		
		lblQuantity = new JLabel("Quantity");
		GridBagConstraints gbc_lblQuantity = new GridBagConstraints();
		gbc_lblQuantity.anchor = GridBagConstraints.SOUTHWEST;
		gbc_lblQuantity.insets = new Insets(0, 0, 5, 5);
		gbc_lblQuantity.gridx = 0;
		gbc_lblQuantity.gridy = 4;
		contentPane.add(lblQuantity, gbc_lblQuantity);
		
		storageLocationPanel = new JPanel();
		storageLocationPanel.setBorder(null);
		GridBagConstraints gbc_storageLocationPanel = new GridBagConstraints();
		gbc_storageLocationPanel.anchor = GridBagConstraints.NORTH;
		gbc_storageLocationPanel.insets = new Insets(0, 0, 5, 5);
		gbc_storageLocationPanel.fill = GridBagConstraints.HORIZONTAL;
		gbc_storageLocationPanel.gridx = 0;
		gbc_storageLocationPanel.gridy = 5;
		contentPane.add(storageLocationPanel, gbc_storageLocationPanel);
		GridBagLayout gbl_storageLocationPanel = new GridBagLayout();
		gbl_storageLocationPanel.columnWidths = new int[]{0, 0};
		gbl_storageLocationPanel.rowHeights = new int[]{0, 0};
		gbl_storageLocationPanel.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_storageLocationPanel.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		storageLocationPanel.setLayout(gbl_storageLocationPanel);
		
		txtQuantity = new JTextField();
		txtQuantity.setEnabled(false);
		txtQuantity.setEditable(false);
		txtQuantity.setColumns(10);
		GridBagConstraints gbc_txtQuantity = new GridBagConstraints();
		gbc_txtQuantity.anchor = GridBagConstraints.NORTH;
		gbc_txtQuantity.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtQuantity.gridx = 0;
		gbc_txtQuantity.gridy = 0;
		storageLocationPanel.add(txtQuantity, gbc_txtQuantity);
		
		lblShelf = new JLabel("Shelf");
		GridBagConstraints gbc_lblShelf = new GridBagConstraints();
		gbc_lblShelf.anchor = GridBagConstraints.WEST;
		gbc_lblShelf.insets = new Insets(0, 0, 5, 5);
		gbc_lblShelf.gridx = 0;
		gbc_lblShelf.gridy = 6;
		contentPane.add(lblShelf, gbc_lblShelf);
		
		txtShelf = new JTextField();
		txtShelf.setEnabled(false);
		txtShelf.setEditable(false);
		txtShelf.setColumns(10);
		GridBagConstraints gbc_txtShelf = new GridBagConstraints();
		gbc_txtShelf.insets = new Insets(0, 0, 5, 5);
		gbc_txtShelf.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtShelf.gridx = 0;
		gbc_txtShelf.gridy = 7;
		contentPane.add(txtShelf, gbc_txtShelf);
		
		btnChooseShelf = new JButton("Choose");
		GridBagConstraints gbc_btnChooseShelf = new GridBagConstraints();
		gbc_btnChooseShelf.insets = new Insets(0, 0, 5, 0);
		gbc_btnChooseShelf.gridx = 1;
		gbc_btnChooseShelf.gridy = 7;
		contentPane.add(btnChooseShelf, gbc_btnChooseShelf);
		
		lblTrackable = new JLabel("Are product Items trackable?");
		GridBagConstraints gbc_lblTrackable = new GridBagConstraints();
		gbc_lblTrackable.anchor = GridBagConstraints.WEST;
		gbc_lblTrackable.insets = new Insets(0, 0, 5, 5);
		gbc_lblTrackable.gridx = 0;
		gbc_lblTrackable.gridy = 8;
		contentPane.add(lblTrackable, gbc_lblTrackable);
		
		isTrackable = new JPanel();
		GridBagConstraints gbc_isTrackable = new GridBagConstraints();
		gbc_isTrackable.insets = new Insets(0, 0, 5, 5);
		gbc_isTrackable.fill = GridBagConstraints.BOTH;
		gbc_isTrackable.gridx = 0;
		gbc_isTrackable.gridy = 9;
		contentPane.add(isTrackable, gbc_isTrackable);
		GridBagLayout gbl_isTrackable = new GridBagLayout();
		gbl_isTrackable.columnWidths = new int[]{0, 0, 0};
		gbl_isTrackable.rowHeights = new int[]{0, 0};
		gbl_isTrackable.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gbl_isTrackable.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		isTrackable.setLayout(gbl_isTrackable);
		
		rdbtnTrackableYes = new JRadioButton("Yes");
		rdbtnTrackableYes.setSelected(true);
		GridBagConstraints gbc_rdbtnTrackableYes = new GridBagConstraints();
		gbc_rdbtnTrackableYes.anchor = GridBagConstraints.WEST;
		gbc_rdbtnTrackableYes.insets = new Insets(0, 0, 0, 5);
		gbc_rdbtnTrackableYes.gridx = 0;
		gbc_rdbtnTrackableYes.gridy = 0;
		isTrackable.add(rdbtnTrackableYes, gbc_rdbtnTrackableYes);
		
		rdbtnTrackableNo = new JRadioButton("No");
		GridBagConstraints gbc_rdbtnTrackableNo = new GridBagConstraints();
		gbc_rdbtnTrackableNo.anchor = GridBagConstraints.WEST;
		gbc_rdbtnTrackableNo.gridx = 1;
		gbc_rdbtnTrackableNo.gridy = 0;
		isTrackable.add(rdbtnTrackableNo, gbc_rdbtnTrackableNo);
		
		
		btnSubmit = new JButton("OK");
		GridBagConstraints gbc_btnOK = new GridBagConstraints();
		gbc_btnOK.anchor = GridBagConstraints.SOUTHEAST;
		gbc_btnOK.gridx = 1;
		gbc_btnOK.gridy = 10;
		contentPane.add(btnSubmit, gbc_btnOK);
		
        fillFields(supplyOrder);
		addEventHandlers();
	
	}

	/*
	 * *******************************************************
	 * *******************  Methods *******************
	 * *******************************************************
	 */
	

	// FIll in the fields
	private void fillFields(SupplyOrder supplyOrder) {
		txtSupplyOrder.setText(String.valueOf(supplyOrder.ID));
        txtProduct.setText(supplyOrder.getProduct().getName());
        txtQuantity.setText(String.valueOf(supplyOrder.getQuantity()));
	}

	/*
	 * *******************************************************
	 * *******************  EVENT HANDLERS *******************
	 * *******************************************************
	 */
	private void addEventHandlers() {

        btnChooseShelf.addActionListener(e -> {
            ChooseShelf frame = new ChooseShelf(auth);
			frame.setVisible(true);
			if (frame.getSelectedShelf() != null) {
				this.shelf = frame.getSelectedShelf();
				txtShelf.setText(shelf.getName());
			}
        });
		
		// 'update' button: Update the customer
		btnSubmit.addActionListener(e -> {
			String message = "";
			message = "Are you sure you want to stock this supply order?";
			if (Messages.confirm(StockSupplyOrderUI.this, message)) {
				
                /*
				// Validate that shelf name is not empty
				String name = txtName.getText().strip();
				if (name.isEmpty()) {
					Messages.error(this, "Shelf name cannot be empty!");
					return;
				}
                */

				// if mode == Create, create a new customer
				supplyCtrl.StockAndMarkDelivered(supplyOrder, shelf, true);
				}
			// Dispose of the window
			this.dispose();
		});
        
	}
}

