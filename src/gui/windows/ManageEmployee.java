/**
 * 
 */
package gui.windows;


import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.AuthenticationController;
import controller.CustomerController;
import gui.panels.CRUDEmployees;
import model.Customer;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JTable;
/**
 * @author Daniels Kanepe
 *
 */
public class ManageEmployee extends JDialog {

	/**
	 * 
	 */
	private CustomerController customerCtrl;
	
	private static final long serialVersionUID = 2968937622159813565L;
	private final JPanel contentPane;
	private CRUDEmployees employeesPanel;
	
	private Customer selectedCustomer = null;
	
	AuthenticationController auth;


	/**
	 * Create the dialog.
	 */
	public ManageEmployee(AuthenticationController auth) {
		this.auth = auth;
		this.setTitle("Manage employees");
		customerCtrl = new CustomerController();
		setModal(true);
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{420, 0};
		gbl_contentPane.rowHeights = new int[]{210, 0};
		gbl_contentPane.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		employeesPanel = new CRUDEmployees(auth);
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 0;
		getContentPane().add(employeesPanel, gbc_panel);
		
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
	}
	
}

