/**
 * 
 */
package gui;


import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.AuthenticationController;
import controller.StockController;
import model.StorageLocation;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JTable;

/**
 * @author Daniels Kanepe
 *
 */
public class ChooseStorageLocationUI extends JDialog {

	/**
	 * 
	 */
	private StockController stockCtrl;
	
	private static final long serialVersionUID = 2968937672159813565L;
	private final JPanel contentPane;
	private CRUDStorageLocationPanel CRUDPanel;
	private JButton btnChoose;
	
	private StorageLocation selectedStorageLocation = null;
	
	AuthenticationController auth;


	/**
	 * Create the dialog.
	 */
	public ChooseStorageLocationUI(AuthenticationController auth) {
		this.auth = auth;
		this.setTitle("Choose a storage location...");
		stockCtrl = new StockController();
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
		
		CRUDPanel = new CRUDStorageLocationPanel(auth);
		GridBagConstraints gbc_CRUDPanel = new GridBagConstraints();
		gbc_CRUDPanel.fill = GridBagConstraints.BOTH;
		gbc_CRUDPanel.insets = new Insets(0, 0, 5, 0);
		gbc_CRUDPanel.gridx = 0;
		gbc_CRUDPanel.gridy = 0;
		getContentPane().add(CRUDPanel, gbc_CRUDPanel);
		
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
		public boolean isStorageLocationSelected() {
			return selectedStorageLocation != null;
		}
	
		public StorageLocation getSelectedStorageLocation() {
			return selectedStorageLocation;
		}
	
	/*
	 * *******************************************************
	 * *******************  EVENT HANDLERS *******************
	 * *******************************************************
	 */
	private void addEventHandlers() {
		CRUDPanel.getTable().getSelectionModel().addListSelectionListener(e -> {
			JTable table = CRUDPanel.getTable();
			if (table.getSelectionModel().isSelectionEmpty()) {
				btnChoose.setEnabled(false);
			} else {
				btnChoose.setEnabled(true);
			}
			
		});
		
		// Choose button
		btnChoose.addActionListener(e -> {
			JTable table = CRUDPanel.getTable();
			if (!table.getSelectionModel().isSelectionEmpty()) {
				StorageLocationTableModel tableModel = CRUDPanel.getTableModel();
				StorageLocation storageLocation = tableModel.getObj(table.getSelectedRow());
				selectedStorageLocation = storageLocation;
				this.dispose();
			}
		});
	}
	
}


