package gui.windows;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.AuthenticationController;
import gui.JButtonPrimary;
import gui.panels.CRUDLoans;
import gui.panels.tableModels.LoansTableModel;
import models.Customer;
import models.Loan;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JTable;

/**
 * @author Daniels Kanepe
 *
 */
public class ChooseLoan extends JDialog {
	
	private final JPanel contentPane;
	private CRUDLoans CRUDPanel;
	private JButtonPrimary btnChoose;
	
	private Loan selectedLoan = null;
	
	AuthenticationController auth;
    Customer customer;


	/**
	 * Create the dialog.
	 */
	public ChooseLoan(AuthenticationController auth, Customer customer) {
		this.auth = auth;
        this.customer = customer;

		this.setTitle("Choose a Loan...");
		setModal(true);
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 700, 450);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{420, 0};
		gbl_contentPane.rowHeights = new int[]{210, 25, 0};
		gbl_contentPane.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{1.0, 0.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		CRUDPanel = new CRUDLoans(auth, customer);
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.insets = new Insets(0, 0, 5, 0);
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 0;
		getContentPane().add(CRUDPanel, gbc_panel);
		
		btnChoose = new JButtonPrimary("Choose...");
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

		public boolean isLoanSelected() {
			return selectedLoan != null;
		}
	
		/**
		  *  @return selected loan
		 */
		public Loan getSelectedLoan() {
			return selectedLoan;
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
				LoansTableModel tableModel = CRUDPanel.getTableModel();
				Loan loan = tableModel.getObj(table.getSelectedRow());
				selectedLoan = loan;
				this.dispose();
			}
		});
	}
	
}


