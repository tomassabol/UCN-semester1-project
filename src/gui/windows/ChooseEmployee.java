package gui.windows;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;

import controller.AuthenticationController;
import gui.JButtonPrimary;
import gui.panels.CRUDEmployees;
import gui.panels.tableModel.EmployeeTableModel;
import models.IFEmployee;

/**
 * @author Daniels Kanepe
 *
 */
public class ChooseEmployee extends JDialog {
	
	private static final long serialVersionUID = 2968937672159813565L;
	private final JPanel contentPane;
	private CRUDEmployees employeesPanel;
	private JButtonPrimary btnChoose;
	
	private IFEmployee selectedEmployee = null;
	
	AuthenticationController auth;

	/**
	 * Create the dialog.
	 */
	public ChooseEmployee(AuthenticationController auth) {
		this.auth = auth;
		this.setTitle("Choose a employee...");
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
		
		employeesPanel = new CRUDEmployees(auth);
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.insets = new Insets(0, 0, 5, 0);
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 0;
		getContentPane().add(employeesPanel, gbc_panel);
		
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
		public boolean isEmployeeSelected() {
			return selectedEmployee != null;
		}
	
		public IFEmployee getSelectedEmployee() {
			return selectedEmployee;
		}
	
	/*
	 * *******************************************************
	 * *******************  EVENT HANDLERS *******************
	 * *******************************************************
	 */
	private void addEventHandlers() {
		employeesPanel.getTable().getSelectionModel().addListSelectionListener(e -> {
			JTable table = employeesPanel.getTable();
			if (table.getSelectionModel().isSelectionEmpty()) {
				btnChoose.setEnabled(false);
			} else {
				btnChoose.setEnabled(true);
			}
			
		});
		
		// Choose button
		btnChoose.addActionListener(e -> {
			JTable table = employeesPanel.getTable();
			if (!table.getSelectionModel().isSelectionEmpty()) {
				EmployeeTableModel tableModel = employeesPanel.getTableModel();
				IFEmployee employee = tableModel.getObj(table.getSelectedRow());
				selectedEmployee = employee;
				this.dispose();
			}
		});
	}

}
