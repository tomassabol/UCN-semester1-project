package gui.windows;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.AuthenticationController;
import controller.SupplyController;
import exception.IllegalModificationException;
import gui.Common;
import gui.JButtonPrimary;
import gui.Messages;
import models.Shelf;
import models.SupplyOrder;
import models.TrackableItem;

import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import javax.swing.JTextField;
import java.awt.Insets;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

import javax.swing.JButton;
import javax.swing.JRadioButton;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;

/**
 * @author Daniels Kanepe
 *
 */
public class StockSupplyOrder extends JDialog {

    private JPanel contentPane;
	private JButton btnSubmit;

	AuthenticationController auth;
    SupplyOrder supplyOrder;
    SupplyController supplyCtrl;
	private JLabel lblShelf;
	private JLabel lblTrackable;
	private JPanel isTrackablePanel;
	private JRadioButton rdbtnTrackableYes;
	private JRadioButton rdbtnTrackableNo;
	private JPanel shelfPanel;
	private JTextField txtShelfDisplay;
	private JButton btnChooseShelf;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	
    Shelf shelf = null;
    private JLabel lblDeliveryDate;
    private JTextField txtDeliveryDate;
    private JButton btnAutofill;
    private JLabel lblItemType;
    private JPanel itemTypePanel;
    private JRadioButton rdbtnTypeLoanable;
    private JRadioButton rdbtnTypeBuyable;
    private final ButtonGroup buttonGroup_1 = new ButtonGroup();

	/**
	 * @param auth the auth
	 * @param supplyOrder the supplyOrder
     * @wbp.parser.constructor
	 */
	public StockSupplyOrder(AuthenticationController auth, SupplyOrder supplyOrder) {
		this.supplyOrder = supplyOrder;
		this.auth = auth;

        supplyCtrl = new SupplyController();
		
		setModal(true);
		setBounds(100, 100, 600, 450);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[]{273, 0, 0};
		gbl_contentPanel.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_contentPanel.columnWeights = new double[]{1.0, 0.0, Double.MIN_VALUE};
		gbl_contentPanel.rowWeights = new double[]{1.0, 1.0, 1.0, 0.0, 0.0, 1.0, 1.0, 0.0, 1.0, 1.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPanel);
		
		JLabel lblTitle = new JLabel("Stock a supply order");
		GridBagConstraints gbc_lblTitle = new GridBagConstraints();
		gbc_lblTitle.gridwidth = 2;
		gbc_lblTitle.anchor = GridBagConstraints.SOUTH;
		gbc_lblTitle.insets = new Insets(0, 0, 5, 0);
		gbc_lblTitle.gridx = 0;
		gbc_lblTitle.gridy = 0;
		contentPane.add(lblTitle, gbc_lblTitle);
		
		lblShelf = new JLabel("Shelf *");
		GridBagConstraints gbc_lblShelf = new GridBagConstraints();
		gbc_lblShelf.gridwidth = 2;
		gbc_lblShelf.anchor = GridBagConstraints.SOUTHWEST;
		gbc_lblShelf.insets = new Insets(0, 0, 5, 0);
		gbc_lblShelf.gridx = 0;
		gbc_lblShelf.gridy = 1;
		contentPane.add(lblShelf, gbc_lblShelf);
		
		shelfPanel = new JPanel();
		GridBagConstraints gbc_shelfPanel = new GridBagConstraints();
		gbc_shelfPanel.anchor = GridBagConstraints.NORTH;
		gbc_shelfPanel.gridwidth = 2;
		gbc_shelfPanel.insets = new Insets(0, 0, 5, 0);
		gbc_shelfPanel.fill = GridBagConstraints.HORIZONTAL;
		gbc_shelfPanel.gridx = 0;
		gbc_shelfPanel.gridy = 2;
		contentPane.add(shelfPanel, gbc_shelfPanel);
		shelfPanel.setLayout(new BoxLayout(shelfPanel, BoxLayout.X_AXIS));
		
		txtShelfDisplay = new JTextField();
		txtShelfDisplay.setEnabled(false);
		txtShelfDisplay.setEditable(false);
		txtShelfDisplay.setColumns(10);
		shelfPanel.add(txtShelfDisplay);
		
		btnChooseShelf = new JButton("Choose");
		shelfPanel.add(btnChooseShelf);
		
		lblDeliveryDate = new JLabel("Delivery date (" + Common.getDateTimeFormat() + ") *");
		GridBagConstraints gbc_lblDeliveryDate = new GridBagConstraints();
		gbc_lblDeliveryDate.anchor = GridBagConstraints.SOUTHWEST;
		gbc_lblDeliveryDate.insets = new Insets(0, 0, 5, 5);
		gbc_lblDeliveryDate.gridx = 0;
		gbc_lblDeliveryDate.gridy = 3;
		contentPane.add(lblDeliveryDate, gbc_lblDeliveryDate);
		
		txtDeliveryDate = new JTextField();
		GridBagConstraints gbc_txtDeliveryDate = new GridBagConstraints();
		gbc_txtDeliveryDate.anchor = GridBagConstraints.NORTH;
		gbc_txtDeliveryDate.insets = new Insets(0, 0, 5, 5);
		gbc_txtDeliveryDate.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtDeliveryDate.gridx = 0;
		gbc_txtDeliveryDate.gridy = 4;
		contentPane.add(txtDeliveryDate, gbc_txtDeliveryDate);
		txtDeliveryDate.setColumns(10);
		
		btnAutofill = new JButton("Auto fill");
		GridBagConstraints gbc_btnAutofill = new GridBagConstraints();
		gbc_btnAutofill.anchor = GridBagConstraints.NORTH;
		gbc_btnAutofill.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnAutofill.insets = new Insets(0, 0, 5, 0);
		gbc_btnAutofill.gridx = 1;
		gbc_btnAutofill.gridy = 4;
		contentPane.add(btnAutofill, gbc_btnAutofill);
		
		lblTrackable = new JLabel("Do the items have serial numbers?");
		GridBagConstraints gbc_lblTrackable = new GridBagConstraints();
		gbc_lblTrackable.gridwidth = 2;
		gbc_lblTrackable.anchor = GridBagConstraints.SOUTHWEST;
		gbc_lblTrackable.insets = new Insets(0, 0, 5, 0);
		gbc_lblTrackable.gridx = 0;
		gbc_lblTrackable.gridy = 5;
		contentPane.add(lblTrackable, gbc_lblTrackable);
		
		isTrackablePanel = new JPanel();
		GridBagConstraints gbc_isTrackablePanel = new GridBagConstraints();
		gbc_isTrackablePanel.gridwidth = 2;
		gbc_isTrackablePanel.insets = new Insets(0, 0, 5, 0);
		gbc_isTrackablePanel.fill = GridBagConstraints.BOTH;
		gbc_isTrackablePanel.gridx = 0;
		gbc_isTrackablePanel.gridy = 6;
		contentPane.add(isTrackablePanel, gbc_isTrackablePanel);
		GridBagLayout gbl_isTrackablePanel = new GridBagLayout();
		gbl_isTrackablePanel.columnWidths = new int[]{0, 0, 0};
		gbl_isTrackablePanel.rowHeights = new int[]{0, 0};
		gbl_isTrackablePanel.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gbl_isTrackablePanel.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		isTrackablePanel.setLayout(gbl_isTrackablePanel);
		
		rdbtnTrackableYes = new JRadioButton("Yes (auto-generate)");
		buttonGroup.add(rdbtnTrackableYes);
		GridBagConstraints gbc_rdbtnTrackableYes = new GridBagConstraints();
		gbc_rdbtnTrackableYes.anchor = GridBagConstraints.WEST;
		gbc_rdbtnTrackableYes.insets = new Insets(0, 0, 0, 5);
		gbc_rdbtnTrackableYes.gridx = 0;
		gbc_rdbtnTrackableYes.gridy = 0;
		isTrackablePanel.add(rdbtnTrackableYes, gbc_rdbtnTrackableYes);
		
		rdbtnTrackableNo = new JRadioButton("No");
		buttonGroup.add(rdbtnTrackableNo);
		GridBagConstraints gbc_rdbtnTrackableNo = new GridBagConstraints();
		gbc_rdbtnTrackableNo.anchor = GridBagConstraints.WEST;
		gbc_rdbtnTrackableNo.gridx = 1;
		gbc_rdbtnTrackableNo.gridy = 0;
		isTrackablePanel.add(rdbtnTrackableNo, gbc_rdbtnTrackableNo);
		
		lblItemType = new JLabel("Item type:");
		GridBagConstraints gbc_lblItemType = new GridBagConstraints();
		gbc_lblItemType.anchor = GridBagConstraints.SOUTHWEST;
		gbc_lblItemType.insets = new Insets(0, 0, 5, 5);
		gbc_lblItemType.gridx = 0;
		gbc_lblItemType.gridy = 7;
		contentPane.add(lblItemType, gbc_lblItemType);
		
		itemTypePanel = new JPanel();
		GridBagConstraints gbc_itemTypePanel = new GridBagConstraints();
		gbc_itemTypePanel.insets = new Insets(0, 0, 5, 5);
		gbc_itemTypePanel.fill = GridBagConstraints.BOTH;
		gbc_itemTypePanel.gridx = 0;
		gbc_itemTypePanel.gridy = 8;
		contentPane.add(itemTypePanel, gbc_itemTypePanel);
		GridBagLayout gbl_itemTypePanel = new GridBagLayout();
		gbl_itemTypePanel.columnWidths = new int[]{0, 0, 0};
		gbl_itemTypePanel.rowHeights = new int[]{0, 0};
		gbl_itemTypePanel.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gbl_itemTypePanel.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		itemTypePanel.setLayout(gbl_itemTypePanel);
		
		rdbtnTypeLoanable = new JRadioButton("Loanable");
		buttonGroup_1.add(rdbtnTypeLoanable);
		rdbtnTypeLoanable.setEnabled(false);
		GridBagConstraints gbc_rdbtnTypeLoanable = new GridBagConstraints();
		gbc_rdbtnTypeLoanable.anchor = GridBagConstraints.WEST;
		gbc_rdbtnTypeLoanable.insets = new Insets(0, 0, 0, 5);
		gbc_rdbtnTypeLoanable.gridx = 0;
		gbc_rdbtnTypeLoanable.gridy = 0;
		itemTypePanel.add(rdbtnTypeLoanable, gbc_rdbtnTypeLoanable);
		
		rdbtnTypeBuyable = new JRadioButton("Buyable");
		buttonGroup_1.add(rdbtnTypeBuyable);
		rdbtnTypeBuyable.setSelected(true);
		GridBagConstraints gbc_rdbtnTypeBuyable = new GridBagConstraints();
		gbc_rdbtnTypeBuyable.anchor = GridBagConstraints.WEST;
		gbc_rdbtnTypeBuyable.gridx = 1;
		gbc_rdbtnTypeBuyable.gridy = 0;
		itemTypePanel.add(rdbtnTypeBuyable, gbc_rdbtnTypeBuyable);
		
		
		btnSubmit = new JButtonPrimary("Put Into Stock");
		GridBagConstraints gbc_btnOK = new GridBagConstraints();
		gbc_btnOK.anchor = GridBagConstraints.SOUTHEAST;
		gbc_btnOK.gridx = 1;
		gbc_btnOK.gridy = 9;
		contentPane.add(btnSubmit, gbc_btnOK);
		rdbtnTrackableNo.setSelected(true);
		
		addEventHandlers();
	
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
		
		// If trackable == no, the items cannot be loanable.
		rdbtnTrackableNo.addActionListener(e -> {
			rdbtnTypeBuyable.setSelected(true);
			rdbtnTypeLoanable.setEnabled(false);
		});
		rdbtnTrackableYes.addActionListener(e -> {
			rdbtnTypeLoanable.setEnabled(true);
		});
		
		// Choose shelf button
		btnChooseShelf.addActionListener(e -> {
			ChooseShelf frame = new ChooseShelf(auth);
			frame.setVisible(true);
			if (frame.getSelectedShelf() != null) {
				this.shelf = frame.getSelectedShelf();
				txtShelfDisplay.setText(shelf.getName());
			}
		});
		
		// Autofill delivery date button
		btnAutofill.addActionListener(e -> {
			txtDeliveryDate.setText(Common.datetimeToString(LocalDateTime.now()));
		});
		
		// 'Submit' button - put the supply order into stock
		btnSubmit.addActionListener(e -> {
			if (Messages.confirm(StockSupplyOrder.this, "Stock this supply order?")) {
				
				// Validate shelf: a shelf has been chosen
				if (this.shelf == null) {
					Messages.error(this, "Please choose a shelf to put the delivered items into");
					return;
				}
				
				// Validate delivery datetime: not empty
				String deliveryDateString = txtDeliveryDate.getText().strip();
				if (deliveryDateString.isEmpty()) {
					Messages.error(this, "Delivery Date cannot be empty!");
					return;
				}
				// Parse birth date
				LocalDateTime deliveryDate;
				try {
					deliveryDate = Common.stringToDateTime(deliveryDateString);
				} catch (DateTimeParseException e1) {
					Messages.error(this, "Please enter a delivery date in the format of: " + Common.getDateTimeFormat());
					return;
				}
				// Validate delivery datetime: not < order date
				if (deliveryDate.isBefore(supplyOrder.getDateOrdered())) {
					Messages.error(this, "Delivery date must be later than the order date!");
				}
				
				// With serial numbers?
				boolean trackable = rdbtnTrackableYes.isSelected() ? true : false;
				
				try {
					TrackableItem.TRACKABLE_ITEM_TYPE type;
					type = rdbtnTypeBuyable.isSelected() ?
							TrackableItem.TRACKABLE_ITEM_TYPE.BUYABLE : TrackableItem.TRACKABLE_ITEM_TYPE.LOANABLE;
					supplyCtrl.StockAndMarkDelivered(supplyOrder, shelf, deliveryDate, trackable, type);
				} catch (IllegalModificationException e1) {
					Messages.error(this, "You cannot stock a supply order that has already been stocked!");
				}

			}
			// Dispose of the window
			this.dispose();
		});
        
	}
}

