package gui.window;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;

import controller.AuthenticationController;
import gui.JButtonPrimary;
import gui.panel.CRUDLoans;
import model.Customer;
import model.Employee;
import model.Loan;
import model.SupplyOrder;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JButton;

/**
 * @author Daniels Kanepe
 *
 */
public class ManageLoans extends JDialog {

	private static final long serialVersionUID = 2968937622159813565L;
	private final JPanel contentPane;
	private CRUDLoans CRUDPanel;
	
	AuthenticationController auth;
	Customer customer;
	Employee employee;
	private JButton btnReturn;


	/**
	 * Constructor class ManageLoans
	 */
	public ManageLoans(AuthenticationController auth, Customer customer) {
		this.auth = auth;
		this.customer = customer;
		
		this.setTitle("Manage Loans");
		setModal(true);
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{420, 0};
		gbl_contentPane.rowHeights = new int[]{210, 0, 0};
		gbl_contentPane.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{1.0, 0.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		CRUDPanel = new CRUDLoans(auth, customer);
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.insets = new Insets(0, 0, 5, 0);
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 0;
		getContentPane().add(CRUDPanel, gbc_panel);
		
		btnReturn = new JButtonPrimary("Return");
		btnReturn.setEnabled(false);
		GridBagConstraints gbc_btnReturn = new GridBagConstraints();
		gbc_btnReturn.anchor = GridBagConstraints.EAST;
		gbc_btnReturn.gridx = 0;
		gbc_btnReturn.gridy = 1;
		contentPane.add(btnReturn, gbc_btnReturn);
		
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
		
		// Toggle return depending on if already returned or not
		CRUDPanel.getTable().getSelectionModel().addListSelectionListener(e -> {
			JTable table = CRUDPanel.getTable();
			if (table.getSelectionModel().isSelectionEmpty()) {
				// *** NOT SELECTED ***
				btnReturn.setEnabled(false);
			} else {
				// *** SELECTED ***
				
				// Get selected loan
				int row = table.getSelectedRow();
				Loan loan = CRUDPanel.getTableModel().getObj(row);
				// enable 'return' only if not already returned
				if (loan.isReturned()) {
					btnReturn.setEnabled(false);
				} else {
					btnReturn.setEnabled(true);
				}
				
			}
			
		});
		
		btnReturn.addActionListener(e -> {
			JTable table = CRUDPanel.getTable();
			// Get selected loan
			int row = table.getSelectedRow();
			System.out.println("row: " + row);
			Loan loan = CRUDPanel.getTableModel().getObj(row);
			ReturnLoan frame = new ReturnLoan(auth, loan);
			frame.setVisible(true);
			// Update rendered table
			CRUDPanel.getTableModel().fireTableRowsUpdated(row, row);
		});
		
	}
	
}


