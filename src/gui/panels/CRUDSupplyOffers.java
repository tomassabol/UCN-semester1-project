package gui.panels;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.RowFilter;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import controller.AuthenticationController;
import controller.SupplyController;
import gui.JLink;
import gui.Messages;
import gui.JLink.COLORS;
import gui.panels.tableModels.SupplyOfferTableModel;
import gui.windows.objects.SupplyOfferUI;
import model.Product;
import model.SupplyOffer;
import javax.swing.JTextField;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class CRUDSupplyOffers extends JPanel {

	private JButton btnAdd;
	private SupplyController supplyCtrl;
	
	private static final long serialVersionUID = -8329527605114016878L;
	private JTable tableMain;
	private SupplyOfferTableModel tableModel;
	private JLink btnView;
	private JLink btnEdit;
	private JLink btnDisable;
	private TableRowSorter<TableModel> rowSorter;
	
	AuthenticationController auth;
	private JTextField txtSearch;

	/**
	 * Create the dialog.
	 * Currently you can only get the supply offers for only a specific product
	 */
	public CRUDSupplyOffers(AuthenticationController auth) {
		this.auth = auth;
		supplyCtrl = new SupplyController();
		setLayout(new BorderLayout(0, 0));
		
		tableModel = new SupplyOfferTableModel(supplyCtrl.getSupplyOffers());
		
		// ***** TOP PANEL *****
		JPanel topPanel = new JPanel();
		this.add(topPanel, BorderLayout.NORTH);
		GridBagLayout gbl_topPanel = new GridBagLayout();
		gbl_topPanel.columnWidths = new int[]{0, 0, 0, 0};
		gbl_topPanel.rowHeights = new int[]{0, 0, 0, 0};
		gbl_topPanel.columnWeights = new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_topPanel.rowWeights = new double[]{0.0, 0.0, 1.0, Double.MIN_VALUE};
		topPanel.setLayout(gbl_topPanel);
		
		// ***** Title *****
		JLabel lblTitle = new JLabel(
			String.format("Supply Offers")
		);
		GridBagConstraints gbc_lblTitle = new GridBagConstraints();
		gbc_lblTitle.gridwidth = 3;
		gbc_lblTitle.insets = new Insets(0, 0, 5, 0);
		gbc_lblTitle.gridx = 0;
		gbc_lblTitle.gridy = 0;
		topPanel.add(lblTitle, gbc_lblTitle);
		
		// ***** Search bar *****
		txtSearch = new JTextField();
		GridBagConstraints gbc_txtSearch = new GridBagConstraints();
		gbc_txtSearch.insets = new Insets(0, 0, 5, 5);
		gbc_txtSearch.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtSearch.gridx = 0;
		gbc_txtSearch.gridy = 1;
		topPanel.add(txtSearch, gbc_txtSearch);
		txtSearch.setColumns(10);
			
		// ***** button: Add product  *****
		btnAdd = new JButton("Add Supply Offer");
		GridBagConstraints gbc_btnAdd = new GridBagConstraints();
		gbc_btnAdd.insets = new Insets(0, 0, 5, 0);
		gbc_btnAdd.gridx = 2;
		gbc_btnAdd.gridy = 1;
		topPanel.add(btnAdd, gbc_btnAdd);
		
		// ***** Middle panel: Scroll panel *****
		JScrollPane scrollPanel = new JScrollPane();
		add(scrollPanel, BorderLayout.CENTER);
		
		// ***** Table *****
		tableMain = new JTable();
		tableMain.setModel(tableModel);
		tableMain.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPanel.setViewportView(tableMain);
		
		
		
		// ***** Bottom panel *****
		JPanel bottomPanel = new JPanel();
		this.add(bottomPanel, BorderLayout.SOUTH);
		GridBagLayout gbl_bottomPanel = new GridBagLayout();
		gbl_bottomPanel.columnWidths = new int[]{271, 0, 0, 0, 0};
		gbl_bottomPanel.rowHeights = new int[]{21, 0};
		gbl_bottomPanel.columnWeights = new double[]{1.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_bottomPanel.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		bottomPanel.setLayout(gbl_bottomPanel);
			
		// ***** View button *****
		btnView = new JLink("View", COLORS.GREEN);
		GridBagConstraints gbc_btnView = new GridBagConstraints();
		gbc_btnView.insets = new Insets(0, 0, 0, 5);
		gbc_btnView.gridx = 1;
		gbc_btnView.gridy = 0;
		bottomPanel.add(btnView, gbc_btnView);
			
		// ***** Edit button *****
		btnEdit = new JLink("Edit", COLORS.INDIGO);
		GridBagConstraints gbc_btnEdit = new GridBagConstraints();
		gbc_btnEdit.insets = new Insets(0, 0, 0, 5);
		gbc_btnEdit.gridx = 2;
		gbc_btnEdit.gridy = 0;
		bottomPanel.add(btnEdit, gbc_btnEdit);
			
		// ***** Disable button *****
		btnDisable = new JLink("Remove", COLORS.RED);
		GridBagConstraints gbc_btnDisable = new GridBagConstraints();
		gbc_btnDisable.gridx = 3;
		gbc_btnDisable.gridy = 0;
		bottomPanel.add(btnDisable, gbc_btnDisable);
		
		// By default: all selection buttons disabled
		btnView.setEnabled(false);
		btnEdit.setEnabled(false);
		btnDisable.setEnabled(false);
		
		// ***** Search filter *****
		rowSorter = new TableRowSorter<>(tableMain.getModel());
		tableMain.setRowSorter(rowSorter);
		
		// Attach event handler
		this.addEventHandlers();
	}
	
	/*
	 * *******************************************************
	 * *******************  Methods *******************
	 * *******************************************************
	 */
	
	public SupplyOfferTableModel getTableModel() {
		return tableModel;
	}
	
	
	public void setTableModel(SupplyOfferTableModel tableModel) {
		this.tableMain.setModel(tableModel);
		this.tableModel = tableModel;
		// Update table row sorter
		rowSorter = new TableRowSorter<>(tableMain.getModel());
		tableMain.setRowSorter(rowSorter);
	}
	

	/**
	 * Select a supply offer in the CRUD table.
	 *
	 * @param supplyOffer
	 * @return true, if successful
	 */
	public boolean selectCustomer(SupplyOffer supplyOffer) {
		int rows = tableModel.getRowCount();
		for (int i = 0; i < rows; i++) {
			SupplyOffer foundSupplyOffer = tableModel.getObj(i);
			if (foundSupplyOffer == supplyOffer) {
				tableMain.getSelectionModel().setSelectionInterval(0, i);
				return true;
			}
		}
		return false;
	}
	
	
	/*
	 * *******************************************************
	 * *******************  EVENT HANDLERS *******************
	 * *******************************************************
	 */
	private void addEventHandlers() {
		// Table row selection
		tableMain.getSelectionModel().addListSelectionListener(e -> {
			if (tableMain.getSelectionModel().isSelectionEmpty()) {
				// Not selected
				btnView.setEnabled(false);
				btnEdit.setEnabled(false);
				btnDisable.setEnabled(false);
			} else {
				// Selected
				btnView.setEnabled(true);
				btnEdit.setEnabled(true);
				btnDisable.setEnabled(true);
			}
		});
		
		// Delete supply offer
		btnDisable.addActionListener(e -> {
			int row = tableMain.convertRowIndexToModel(tableMain.getSelectedRow());
			SupplyOffer supplyOffer = tableModel.getObj(row);
			if (Messages.confirm(this, String.format("Are you sure you wish to remove the supply offer with the ID of '%s'?",
					supplyOffer.ID))) {
				supplyCtrl.removeSupplyOffer(supplyOffer);
				tableModel.remove(row);
			}
		});

		// View supply offer
		btnView.addActionListener(e -> {
			int row = tableMain.convertRowIndexToModel(tableMain.getSelectedRow());
			SupplyOffer supplyOffer = tableModel.getObj(row);
			SupplyOfferUI frame = new SupplyOfferUI(auth, supplyOffer, SupplyOfferUI.Mode.VIEW);
			frame.setVisible(true);
		});

		// Edit supply offer
		btnEdit.addActionListener(e -> {
			int modelIndex = tableMain.convertRowIndexToModel(tableMain.getSelectedRow());
			SupplyOffer supplyOffer = tableModel.getObj(modelIndex);
			SupplyOfferUI frame = new SupplyOfferUI(auth, supplyOffer, SupplyOfferUI.Mode.EDIT);
			frame.setVisible(true);
			tableModel.fireTableRowsUpdated(modelIndex, modelIndex);
		});

		// 'ADD' supply offer button
		btnAdd.addActionListener(e -> {
			SupplyOfferUI frame = new SupplyOfferUI(auth);
			frame.setVisible(true);
			if (frame.getSupplyOffer() != null) {
				tableModel.add(frame.getSupplyOffer());
			}
		});
		
		// Search product with a dynamic filter		
		txtSearch.getDocument().addDocumentListener(new DocumentListener(){
											
			@Override
			public void insertUpdate(DocumentEvent e) {
				String text = txtSearch.getText();
												
				if(text.trim().length() == 0) {
					rowSorter.setRowFilter(null);
				}else {
					rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
				}
			}
										
			@Override
			public void  removeUpdate(DocumentEvent e) {
				String text = txtSearch.getText();
												
				if (text.trim().length() == 0) {
					rowSorter.setRowFilter(null);
				} else {
					rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
				}
			}
											
			@Override
			public void changedUpdate(DocumentEvent e) {
				throw new UnsupportedOperationException("Not supported yet.");
			}
		});
	}

}