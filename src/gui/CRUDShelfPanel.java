package gui;

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

import controller.AuthenticationController;
import controller.StockController;
import gui.JLink.COLORS;
import model.Shelf;

/**
 * @author Daniels Kanepe
 *
 */
public class CRUDShelfPanel extends JPanel {
	
	
	private JButton btnAddContractor;
    private StockController stockCtrl;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8329527605114016878L;
	private JTable tableMain;
	private ShelfTableModel tableModel;
	private JLink btnView;
	private JLink btnEdit;
	private JLink btnDisable;

	AuthenticationController auth;

	/**
	 * Create the dialog.
	 * Constructor class CRUDShelfPanel
	 */
	public CRUDShelfPanel(AuthenticationController auth) {
		this.auth = auth;
		stockCtrl = new StockController();
		setLayout(new BorderLayout(0, 0));
		
		tableModel = new ShelfTableModel(stockCtrl.getShelves());
		
		// ***** TOP PANEL *****
		JPanel topPanel = new JPanel();
		this.add(topPanel, BorderLayout.NORTH);
		GridBagLayout gbl_topPanel = new GridBagLayout();
		gbl_topPanel.columnWidths = new int[]{0, 0, 0};
		gbl_topPanel.rowHeights = new int[]{0, 0, 0, 0};
		gbl_topPanel.columnWeights = new double[]{1.0, 0.0, Double.MIN_VALUE};
		gbl_topPanel.rowWeights = new double[]{0.0, 0.0, 1.0, Double.MIN_VALUE};
		topPanel.setLayout(gbl_topPanel);
			// ***** Title *****
			JLabel lblTitle = new JLabel("Shelf");
			GridBagConstraints gbc_lblTitle = new GridBagConstraints();
			gbc_lblTitle.gridwidth = 2;
			gbc_lblTitle.insets = new Insets(0, 0, 5, 0);
			gbc_lblTitle.gridx = 0;
			gbc_lblTitle.gridy = 0;
			topPanel.add(lblTitle, gbc_lblTitle);
			
			// ***** button: Add Shelf  *****
			btnAddContractor = new JButton("Add Shelf");
			GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
			gbc_btnNewButton.insets = new Insets(0, 0, 5, 0);
			gbc_btnNewButton.gridx = 1;
			gbc_btnNewButton.gridy = 1;
			topPanel.add(btnAddContractor, gbc_btnNewButton);
		
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
			btnDisable = new JLink("Delete", COLORS.RED);
			GridBagConstraints gbc_btnDisable = new GridBagConstraints();
			gbc_btnDisable.gridx = 3;
			gbc_btnDisable.gridy = 0;
			bottomPanel.add(btnDisable, gbc_btnDisable);
		
		// By default: all selection buttons disabled
		btnView.setEnabled(false);
		btnEdit.setEnabled(false);
		btnDisable.setEnabled(false);
		
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
		return tableModel;
	}
	

	/**
	 * Select a Shelf in the CRUD table.
	 *
	 * @param customer the customer
	 * @return true, if successful
	 */
	public boolean selectContractor(Shelf shelf) {
		int rows = tableModel.getRowCount();
		for (int i = 0; i < rows; i++) {
			Shelf founShelf = tableModel.getObj(i);
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
		// Table row selection
		tableMain.getSelectionModel().addListSelectionListener(e -> {
			if (tableMain.getSelectionModel().isSelectionEmpty()) {
				// Not selected
				btnView.setEnabled(false);
				btnEdit.setEnabled(true);
				btnDisable.setEnabled(true);
			} else {
				// Selected
				btnView.setEnabled(true);
				btnEdit.setEnabled(true);
				btnDisable.setEnabled(true);
			}
		});
		
		// Delete Shelf
		btnDisable.addActionListener(e -> {
			int row = tableMain.getSelectedRow();
			Shelf shelf = tableModel.getObj(row);
			if (Messages.confirm(this, String.format("Are you sure you wish to delete the shelf '%s'?", shelf.getName()))) {
				stockCtrl.removeShelf(shelf);
				tableModel.remove(row);
			}
		});
		// View Shelf
		btnView.addActionListener(e -> {
			int row = tableMain.getSelectedRow();
			Shelf shelf = tableModel.getObj(row);
			ShelfUI frame = new ShelfUI(auth, shelf, ShelfUI.Mode.VIEW);
			frame.setVisible(true);
		});
		// Edit Shelf
		btnEdit.addActionListener(e -> {
			int row = tableMain.getSelectedRow();
			Shelf shelf = tableModel.getObj(row);
			ShelfUI frame = new ShelfUI(auth, shelf, ShelfUI.Mode.EDIT);
			frame.setVisible(true);
			tableModel.fireTableRowsUpdated(row, row);
		});
		// Add Shelf
		btnAddContractor.addActionListener(e -> {
			ShelfUI frame = new ShelfUI(auth);
			frame.setVisible(true);
			if (frame.getShelf() != null) {
				tableModel.add(frame.getShelf());
			}
		});
	}
}