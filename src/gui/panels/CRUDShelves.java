package gui.panels;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.regex.Pattern;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.RowFilter;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import controller.AuthenticationController;
import controller.StockController;
import gui.JLink;
import gui.Messages;
import gui.JLink.COLORS;
import gui.panels.tableModels.ShelfTableModel;
import gui.panels.tableModels.StockBatchTableModel;
import gui.windows.model.ShelfUI;
import models.Shelf;

/**
 * @author Daniels Kanepe
 *
 */
public class CRUDShelves extends JPanel {
	
	
	private JButton btnAddContractor;
    private StockController stockCtrl;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8329527605114016878L;
	private JTable tableMain;
	private ShelfTableModel tableMainModel;
	private JLink btnView;
	private JLink btnEdit;
	private JLink btnDisable;
	private JTextField txtSearch;
	private TableRowSorter<TableModel> rowSorter;
	AuthenticationController auth;
	private JScrollPane scrollPanelStockBatches;
	private JTable tableStockBatches;

	/**
	 * Create the dialog.
	 * Constructor class CRUDShelfPanel
	 */
	public CRUDShelves(AuthenticationController auth) {
		this.auth = auth;
		stockCtrl = new StockController();
		
		tableMainModel = new ShelfTableModel(stockCtrl.getShelves());
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{450, 0};
		gridBagLayout.rowHeights = new int[]{50, 73, 21, 0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 1.0, 0.0, 1.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		// ***** TOP PANEL *****
		JPanel topPanel = new JPanel();
		GridBagConstraints gbc_topPanel = new GridBagConstraints();
		gbc_topPanel.anchor = GridBagConstraints.NORTH;
		gbc_topPanel.fill = GridBagConstraints.HORIZONTAL;
		gbc_topPanel.insets = new Insets(0, 0, 5, 0);
		gbc_topPanel.gridx = 0;
		gbc_topPanel.gridy = 0;
		this.add(topPanel, gbc_topPanel);
		GridBagLayout gbl_topPanel = new GridBagLayout();
		gbl_topPanel.columnWidths = new int[]{0, 0, 0, 0};
		gbl_topPanel.rowHeights = new int[]{0, 0, 0, 0};
		gbl_topPanel.columnWeights = new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_topPanel.rowWeights = new double[]{0.0, 0.0, 1.0, Double.MIN_VALUE};
		topPanel.setLayout(gbl_topPanel);
		
		// ***** Title *****
		JLabel lblTitle = new JLabel("Shelf");
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
		
		// ***** button: Add Shelf  *****
		btnAddContractor = new JButton("Add Shelf");
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.insets = new Insets(0, 0, 5, 0);
		gbc_btnNewButton.gridx = 2;
		gbc_btnNewButton.gridy = 1;
		topPanel.add(btnAddContractor, gbc_btnNewButton);
		
		// ***** Middle panel: Scroll panel *****
		JScrollPane scrollPanelMain = new JScrollPane();
		GridBagConstraints gbc_scrollPanelMain = new GridBagConstraints();
		gbc_scrollPanelMain.fill = GridBagConstraints.BOTH;
		gbc_scrollPanelMain.insets = new Insets(0, 0, 5, 0);
		gbc_scrollPanelMain.gridx = 0;
		gbc_scrollPanelMain.gridy = 1;
		add(scrollPanelMain, gbc_scrollPanelMain);
		
		// ***** Table *****
		tableMain = new JTable();
		tableMain.setModel(tableMainModel);
		tableMain.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPanelMain.setViewportView(tableMain);
		
		// ***** Bottom panel *****
		JPanel bottomPanel = new JPanel();
		GridBagConstraints gbc_bottomPanel = new GridBagConstraints();
		gbc_bottomPanel.insets = new Insets(0, 0, 5, 0);
		gbc_bottomPanel.anchor = GridBagConstraints.NORTH;
		gbc_bottomPanel.fill = GridBagConstraints.HORIZONTAL;
		gbc_bottomPanel.gridx = 0;
		gbc_bottomPanel.gridy = 2;
		this.add(bottomPanel, gbc_bottomPanel);
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
		btnDisable = new JLink("Delete", COLORS.RED);
		GridBagConstraints gbc_btnDisable = new GridBagConstraints();
		gbc_btnDisable.gridx = 3;
		gbc_btnDisable.gridy = 0;
		bottomPanel.add(btnDisable, gbc_btnDisable);
		
		// By default: all selection buttons disabled
		btnView.setEnabled(false);
		btnEdit.setEnabled(false);
		btnDisable.setEnabled(false);
		
		// TODO: Implement in a separate CRUDStockBatchesPanel file
		scrollPanelStockBatches = new JScrollPane();
		GridBagConstraints gbc_scrollPanelStockBatches = new GridBagConstraints();
		gbc_scrollPanelStockBatches.fill = GridBagConstraints.BOTH;
		gbc_scrollPanelStockBatches.gridx = 0;
		gbc_scrollPanelStockBatches.gridy = 3;
		add(scrollPanelStockBatches, gbc_scrollPanelStockBatches);
		
		tableStockBatches = new JTable();
		tableStockBatches.setEnabled(false);
		tableStockBatches.setRowSelectionAllowed(false);
		tableStockBatches.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPanelStockBatches.setViewportView(tableStockBatches);
		
		// Add filtering
		rowSorter = new TableRowSorter<>(tableMainModel);
		tableMain.setRowSorter(rowSorter);
		
		// Attach event handler
		this.addEventHandlers();
	}
	
	/*
	 * *******************************************************
	 * *******************  Methods *******************
	 * *******************************************************
	 */
	
	public JTable getTable() {
		return tableMain;
	}
	
	public ShelfTableModel getTableModel() {
		return tableMainModel;
	}
	
	public void setTableModel(ShelfTableModel tableModel) {
		this.tableMain.setModel(tableModel);
		this.tableMainModel = tableModel;
		// Update table row sorter
		rowSorter = new TableRowSorter<>(tableMain.getModel());
		tableMain.setRowSorter(rowSorter);
	}	

	/**
	 * Select a Shelf in the CRUD table.
	 *
	 * @param shelf the shelf
	 * @return true, if successful
	 */
	public boolean selectContractor(Shelf shelf) {
		int rows = tableMainModel.getRowCount();
		for (int i = 0; i < rows; i++) {
			Shelf founShelf = tableMainModel.getObj(i);
			if (founShelf == shelf) {
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
		// When a table row is selected:
		tableMain.getSelectionModel().addListSelectionListener(e -> {
			if (tableMain.getSelectionModel().isSelectionEmpty()) {
				// *** Not selected ***
				
				// Disable bottom options
				btnView.setEnabled(false);
				btnEdit.setEnabled(false);
				btnDisable.setEnabled(false);
				
				// clear stock batch table
				tableStockBatches.setModel(new DefaultTableModel());
			} else {
				// *** Selected ***
				
				// Disable bottom options
				btnView.setEnabled(true);
				btnEdit.setEnabled(true);
				btnDisable.setEnabled(true);
				
				// Fill stock batch table
				int selectedRow = tableMain.getSelectedRow();
				Shelf shelf = tableMainModel.getObj(selectedRow);
				StockBatchTableModel stockBatchTableModel = new StockBatchTableModel(shelf.getStockBatches());
				tableStockBatches.setModel(stockBatchTableModel);
			}
		});
		
		// Delete Shelf
		btnDisable.addActionListener(e -> {
			int row = tableMain.convertRowIndexToModel(tableMain.getSelectedRow());
			Shelf shelf = tableMainModel.getObj(row);
			if (Messages.confirm(this, String.format("Are you sure you wish to delete the shelf '%s'?", shelf.getName()))) {
				stockCtrl.removeShelf(shelf);
				tableMainModel.remove(row);
				setTableModel(tableMainModel);
			}
		});
		
		// View Shelf
		btnView.addActionListener(e -> {
			int row = tableMain.convertRowIndexToModel(tableMain.getSelectedRow());
			Shelf shelf = tableMainModel.getObj(row);
			ShelfUI frame = new ShelfUI(auth, shelf, ShelfUI.Mode.VIEW);
			frame.setVisible(true);
		});
		
		// Edit Shelf
		btnEdit.addActionListener(e -> {
			int row = tableMain.convertRowIndexToModel(tableMain.getSelectedRow());
			Shelf shelf = tableMainModel.getObj(row);
			ShelfUI frame = new ShelfUI(auth, shelf, ShelfUI.Mode.EDIT);
			frame.setVisible(true);
			tableMainModel.fireTableRowsUpdated(row, row);
			setTableModel(tableMainModel);
		});
		
		// Add Shelf
		btnAddContractor.addActionListener(e -> {
			ShelfUI frame = new ShelfUI(auth);
			frame.setVisible(true);
			if (frame.getShelf() != null) {
				tableMainModel.add(frame.getShelf());
				setTableModel(tableMainModel);
			}
		});
		
		// Search implementation
		txtSearch.getDocument().addDocumentListener(new DocumentListener(){
			
			private void search() {
				String text = txtSearch.getText();
				if(text.trim().length() == 0) {
					rowSorter.setRowFilter(null);
				} else {
					rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + Pattern.quote(text)));
				}
			}
													
			@Override
			public void insertUpdate(DocumentEvent e) {
				search();
			}
										
			@Override
			public void  removeUpdate(DocumentEvent e) {
				search();
			}
													
			@Override
			public void changedUpdate(DocumentEvent e) { /* Empty due to interface */ }
		});
	}
}