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

import controller.AuthenticationController;
import controller.EmployeeController;
import gui.JLink;
import gui.Messages;
import gui.JLink.COLORS;
import gui.panels.tableModels.EmployeeTableModel;
import gui.windows.objects.EmployeeUI;
import model.Employee;
import model.IFEmployee;

/**
 * @author Daniels Kanepe
 *
 */
public class CRUDEmployees extends JPanel {

	private JButton btnAddEmployee;
	private EmployeeController employeeCtrl;
	private static final long serialVersionUID = -8329527605114016878L;
	private JTable tableMain;
	private EmployeeTableModel tableModel;
	private JLink btnView;
	private JLink btnEdit;
	private JLink btnDisable;
	AuthenticationController auth;

	/**
	 * Create the dialog.
	 * constructor class CRUDEmployeesPanel
	 */
	public CRUDEmployees(AuthenticationController auth) {
		this.auth = auth;
		employeeCtrl = new EmployeeController();
		setLayout(new BorderLayout(0, 0));
		
		tableModel = new EmployeeTableModel(employeeCtrl.getEmployees());
		
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
		JLabel lblTitle = new JLabel(
			String.format("Employees")
		);
		GridBagConstraints gbc_lblTitle = new GridBagConstraints();
		gbc_lblTitle.gridwidth = 2;
		gbc_lblTitle.insets = new Insets(0, 0, 5, 0);
		gbc_lblTitle.gridx = 0;
		gbc_lblTitle.gridy = 0;
		topPanel.add(lblTitle, gbc_lblTitle);
				
		// ***** button: Add employee  *****
		btnAddEmployee = new JButton("Add Employee");
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.insets = new Insets(0, 0, 5, 0);
		gbc_btnNewButton.gridx = 1;
		gbc_btnNewButton.gridy = 1;
		topPanel.add(btnAddEmployee, gbc_btnNewButton);
			
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
		
	/**
	 * @return JTable tableMain
	 */  
	public JTable getTable() {
		return tableMain;
	}
	
	/**
	 *  @return EmployeeTableModel tableModel
	 */
	public EmployeeTableModel getTableModel() {
		return tableModel;
	}
		

	/**
	 * Select an employee in the CRUD table.
	 *
	 * @param employee the employee
	 * @return true, if successful
	 */
	public boolean selectEmployee(Employee employee) {
		int rows = tableModel.getRowCount();
		for (int i = 0; i < rows; i++) {
			IFEmployee foundEmployee = tableModel.getObj(i);
			if (foundEmployee == employee) {
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
			
	// Delete employee
	btnDisable.addActionListener(e -> {
		int row = tableMain.getSelectedRow();
		IFEmployee employee = tableModel.getObj(row);
		if (employee == auth.getLoggedInUser()) {
			Messages.error(this, "You cannot delete currently logged in employee!");
		} else {
			if (Messages.confirm(this, String.format("Are you sure you wish to delete the employee '%s %s'?",
					employee.getFirstName(),
					employee.getLastName()))) {
				employeeCtrl.removeEmployee(employee);
				tableModel.remove(row);
			}
		}
		});

		// View Employee
		btnView.addActionListener(e -> {
			int row = tableMain.getSelectedRow();
			IFEmployee employee = tableModel.getObj(row);
			EmployeeUI frame = new EmployeeUI(auth, employee, EmployeeUI.Mode.VIEW);
			frame.setVisible(true);
		});

		// Edit employee
		btnEdit.addActionListener(e -> {
			int row = tableMain.getSelectedRow();
			IFEmployee employee = tableModel.getObj(row);
			EmployeeUI frame = new EmployeeUI(auth, employee, EmployeeUI.Mode.EDIT);
			frame.setVisible(true);
			tableModel.fireTableRowsUpdated(row, row);
		});

		// 'ADD Employee' button
		btnAddEmployee.addActionListener(e -> {
			EmployeeUI frame = new EmployeeUI(auth);
			frame.setVisible(true);
			if (frame.getEmployee() != null) {
				tableModel.add(frame.getEmployee());
			}
		});
	}

		
}
