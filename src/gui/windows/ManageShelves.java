package gui.windows;


import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.AuthenticationController;
import gui.panels.CRUDShelves;
/**
 * @author Daniels Kanepe
 *
 */
public class ManageShelves extends JDialog {

	private static final long serialVersionUID = 2968937622159813565L;
	private final JPanel contentPane;
	private CRUDShelves shelfPanel;

	AuthenticationController auth;


	/**
	 * Create the dialog.
	 */
	public ManageShelves(AuthenticationController auth) {
		this.auth = auth;
		this.setTitle("Manage Shelves");
		setModal(true);
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 440, 365);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{420, 0};
		gbl_contentPane.rowHeights = new int[]{210, 0};
		gbl_contentPane.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		shelfPanel = new CRUDShelves(auth);
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 0;
		getContentPane().add(shelfPanel, gbc_panel);
		
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
	
	