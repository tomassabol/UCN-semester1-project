package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;

import model.Product;

import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.GridBagConstraints;
import javax.swing.JTextField;
import java.awt.Insets;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.JTextArea;

public class ProductUI extends JDialog {

	private JPanel contentPane;
	private JTextField txtId;
	private JTextField txtName;
	private JTextField txtMax;
	private JTextField txtMin;
	private JTextField txtLoaning;
	private JTextField txtSelling;
	private JButton btnOk;
	private JTextArea txtDescription;
	private String loaningPrice;

	/**
	 * Create the frame.
	 */
	public ProductUI(Product product, boolean view) {
		setModal(true);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 330);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{208, 208, 0};
		gbl_contentPane.rowHeights = new int[]{27, 19, 0, 0, 0, 0, 0, 19, 0, 0, 0};
		gbl_contentPane.columnWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		// Check if the product has a loaning price
		if(product.getLatestLoaningPrice() == null) {
			loaningPrice = "null";
		} else {
			loaningPrice = String.valueOf(product.getLatestLoaningPrice());
		}
		
		// Header
		JLabel lblTitle = new JLabel("Product: " + product.getName());
		lblTitle.setFont(new Font("Tahoma", Font.BOLD, 14));
		GridBagConstraints gbc_lblTitle = new GridBagConstraints();
		gbc_lblTitle.anchor = GridBagConstraints.WEST;
		gbc_lblTitle.insets = new Insets(0, 0, 5, 5);
		gbc_lblTitle.gridx = 0;
		gbc_lblTitle.gridy = 0;
		contentPane.add(lblTitle, gbc_lblTitle);
		
		JLabel lblId = new JLabel("Id");
		GridBagConstraints gbc_lblId = new GridBagConstraints();
		gbc_lblId.anchor = GridBagConstraints.WEST;
		gbc_lblId.insets = new Insets(0, 0, 5, 5);
		gbc_lblId.gridx = 0;
		gbc_lblId.gridy = 1;
		contentPane.add(lblId, gbc_lblId);
		
		txtId = new JTextField();
		txtId.setText(String.valueOf(product.ID));
		txtId.setColumns(10);
		GridBagConstraints gbc_txtId = new GridBagConstraints();
		gbc_txtId.anchor = GridBagConstraints.WEST;
		gbc_txtId.insets = new Insets(0, 0, 5, 5);
		gbc_txtId.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtId.gridx = 0;
		gbc_txtId.gridy = 2;
		contentPane.add(txtId, gbc_txtId);
		
		JLabel lblName = new JLabel("Name");
		GridBagConstraints gbc_lblName = new GridBagConstraints();
		gbc_lblName.anchor = GridBagConstraints.WEST;
		gbc_lblName.insets = new Insets(0, 0, 5, 0);
		gbc_lblName.gridx = 1;
		gbc_lblName.gridy = 1;
		contentPane.add(lblName, gbc_lblName);
		
		txtName = new JTextField();
		txtName.setText(product.getName());
		txtName.setColumns(10);
		GridBagConstraints gbc_txtName = new GridBagConstraints();
		gbc_txtName.anchor = GridBagConstraints.WEST;
		gbc_txtName.insets = new Insets(0, 0, 5, 0);
		gbc_txtName.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtName.gridx = 1;
		gbc_txtName.gridy = 2;
		contentPane.add(txtName, gbc_txtName);
		
		JLabel lblDescription = new JLabel("Description:");
		GridBagConstraints gbc_lblDescription = new GridBagConstraints();
		gbc_lblDescription.anchor = GridBagConstraints.WEST;
		gbc_lblDescription.insets = new Insets(0, 0, 5, 5);
		gbc_lblDescription.gridx = 0;
		gbc_lblDescription.gridy = 3;
		contentPane.add(lblDescription, gbc_lblDescription);
		
		txtDescription = new JTextArea();
		txtDescription.setText(product.getDescription());
		txtDescription.setLineWrap(true);
		GridBagConstraints gbc_txtDescription = new GridBagConstraints();
		gbc_txtDescription.gridwidth = 2;
		gbc_txtDescription.insets = new Insets(0, 0, 5, 5);
		gbc_txtDescription.fill = GridBagConstraints.BOTH;
		gbc_txtDescription.gridx = 0;
		gbc_txtDescription.gridy = 4;
		contentPane.add(txtDescription, gbc_txtDescription);
		
		JLabel lblMin = new JLabel("Minimum Stock");
		GridBagConstraints gbc_lblMin = new GridBagConstraints();
		gbc_lblMin.anchor = GridBagConstraints.WEST;
		gbc_lblMin.insets = new Insets(0, 0, 5, 5);
		gbc_lblMin.gridx = 0;
		gbc_lblMin.gridy = 5;
		contentPane.add(lblMin, gbc_lblMin);
		
		txtMin = new JTextField();
		txtMin.setText(String.valueOf(product.getMinStock()));
		txtMin.setColumns(10);
		GridBagConstraints gbc_txtMin = new GridBagConstraints();
		gbc_txtMin.insets = new Insets(0, 0, 5, 5);
		gbc_txtMin.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtMin.gridx = 0;
		gbc_txtMin.gridy = 6;
		contentPane.add(txtMin, gbc_txtMin);
		
		JLabel lblMax = new JLabel("Maximum Stock");
		GridBagConstraints gbc_lblMax = new GridBagConstraints();
		gbc_lblMax.anchor = GridBagConstraints.WEST;
		gbc_lblMax.insets = new Insets(0, 0, 5, 0);
		gbc_lblMax.gridx = 1;
		gbc_lblMax.gridy = 5;
		contentPane.add(lblMax, gbc_lblMax);
		
		txtMax = new JTextField();
		txtMax.setText(String.valueOf(product.getMaxStock()));
		txtMax.setColumns(10);
		GridBagConstraints gbc_txtMax = new GridBagConstraints();
		gbc_txtMax.insets = new Insets(0, 0, 5, 0);
		gbc_txtMax.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtMax.gridx = 1;
		gbc_txtMax.gridy = 6;
		contentPane.add(txtMax, gbc_txtMax);
		
		JLabel lblSelling = new JLabel("Selling Price");
		GridBagConstraints gbc_lblSelling = new GridBagConstraints();
		gbc_lblSelling.anchor = GridBagConstraints.WEST;
		gbc_lblSelling.insets = new Insets(0, 0, 5, 5);
		gbc_lblSelling.gridx = 0;
		gbc_lblSelling.gridy = 7;
		contentPane.add(lblSelling, gbc_lblSelling);
		
		// For selling price make a button, where you can check previous selling prices as well
		txtSelling = new JTextField();
		txtSelling.setText(String.valueOf(product.getLatestSellingPrice()));
		txtSelling.setColumns(10);
		GridBagConstraints gbc_txtSelling = new GridBagConstraints();
		gbc_txtSelling.insets = new Insets(0, 0, 5, 5);
		gbc_txtSelling.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtSelling.gridx = 0;
		gbc_txtSelling.gridy = 8;
		contentPane.add(txtSelling, gbc_txtSelling);
		
		JLabel lblLoaning = new JLabel("Loaning Price");
		GridBagConstraints gbc_lblLoaning = new GridBagConstraints();
		gbc_lblLoaning.anchor = GridBagConstraints.WEST;
		gbc_lblLoaning.insets = new Insets(0, 0, 5, 0);
		gbc_lblLoaning.gridx = 1;
		gbc_lblLoaning.gridy = 7;
		contentPane.add(lblLoaning, gbc_lblLoaning);
		
		
		txtLoaning = new JTextField();
		txtLoaning.setText(loaningPrice);
		txtLoaning.setColumns(10);
		GridBagConstraints gbc_txtLoaning = new GridBagConstraints();
		gbc_txtLoaning.insets = new Insets(0, 0, 5, 0);
		gbc_txtLoaning.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtLoaning.gridx = 1;
		gbc_txtLoaning.gridy = 8;
		contentPane.add(txtLoaning, gbc_txtLoaning);
		
		btnOk = new JButton("Ok");
		GridBagConstraints gbc_btnOk = new GridBagConstraints();
		gbc_btnOk.anchor = GridBagConstraints.EAST;
		gbc_btnOk.gridx = 1;
		gbc_btnOk.gridy = 9;
		contentPane.add(btnOk, gbc_btnOk);
		
		// Check if the page is for viewing or editing
		if(view == true) {
			view();
			setTitle("View product");
		}else {
			edit();
			setTitle("Edit product");
		}
	}
	
	/*
	 * *******************************************************
	 * *******************  Methods *******************
	 * *******************************************************
	 */
	
	/*
	 * Makes the textfields uneditable
	 */
	public void view() {
		txtId.setEnabled(false);
		txtName.setEditable(false);
		txtDescription.setEditable(false);
		txtMin.setEditable(false);
		txtMax.setEditable(false);
		txtSelling.setEditable(false);
		txtLoaning.setEditable(false);
	}
	
	/*
	 * Makes the textfields editable
	 */
	public void edit() {
		txtId.setEnabled(false);
		txtName.setEditable(true);
		txtDescription.setEditable(true);
		txtMin.setEditable(true);
		txtMax.setEditable(true);
		txtSelling.setEditable(true);
		txtLoaning.setEditable(true);
	}
	
	/*
	 * *******************************************************
	 * *******************  EVENT HANDLERS *******************
	 * *******************************************************
	 */

}
