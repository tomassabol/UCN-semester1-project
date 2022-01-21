package gui.windows.objects;

import java.awt.Component;

import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.NumberFormatter;

import controller.AuthenticationController;
import controller.SupplyController;
import gui.Messages;
import gui.windows.ChooseSupplyOffer;
import model.PrimaryKey;
import model.Product;
import model.SupplyOffer;
import model.SupplyOrder;

import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import javax.swing.JTextField;
import java.awt.Insets;
import java.text.NumberFormat;

import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

public class SupplyOrderUI extends JDialog {
	
	public enum Mode {
		VIEW,
		CREATE
	}
	
	SupplyController supplyCtrl;
	Mode mode;
	AuthenticationController auth;
	SupplyOrder supplyOrder;
	SupplyOffer supplyOffer;
	int quantity;
	Product product;


	/**
	 * Constructor for view/edit
	 *
	 * @param auth the auth
	 * @param supplyOrder the Supply Order
	 * @param product the product
	 * @param mode the mode
	 * @wbp.parser.constructor
	 */
	public SupplyOrderUI(AuthenticationController auth, SupplyOrder supplyOrder, Mode mode) {
		this.mode = mode;
		this.auth = auth;
		this.product = product;
		
		supplyCtrl = new SupplyController();
		
		setModal(true);
		setBounds(100, 100, 450, 341);
		

		addEventHandlers();
	
	}
	
	public SupplyOrderUI(AuthenticationController auth) {
		this(auth, null, Mode.CREATE);
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

