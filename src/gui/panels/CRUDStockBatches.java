package gui.panels;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;

import controller.AuthenticationController;
import controller.StockController;
import gui.panels.tableModels.ShelfTableModel;
import model.Shelf;

/**
 * @author Daniels Kanepe
 *
 */
public class CRUDStockBatches extends JPanel {
    private StockController stockCtrl;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8329527605114016878L;
	private JTable tableMain;
	private ShelfTableModel tableModel;

	AuthenticationController auth;

	/**
	 * Create the dialog.
	 * Constructor class CRUDShelfPanel
	 */
	public CRUDStockBatches(AuthenticationController auth) {
		this.auth = auth;
		stockCtrl = new StockController();
		setLayout(new BorderLayout(0, 0));
		
		tableModel = new ShelfTableModel(stockCtrl.getShelves());
		
		// ***** Middle panel: Scroll panel *****
		JScrollPane scrollPanel = new JScrollPane();
		add(scrollPanel, BorderLayout.CENTER);
			// ***** Table *****
			tableMain = new JTable();
			tableMain.setModel(tableModel);
			tableMain.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			scrollPanel.setViewportView(tableMain);
		
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
	}
}