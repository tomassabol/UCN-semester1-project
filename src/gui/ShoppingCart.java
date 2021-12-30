package gui;

import java.awt.EventQueue;

import javax.swing.JDialog;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.Component;
import javax.swing.Box;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import java.awt.Color;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import controller.ShoppingCartController;
import model.Customer;
import model.ShoppingItemLine;

import javax.swing.ListSelectionModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ShoppingCart extends JDialog {
	private Customer customer;
	private JPanel contentPane;
	private JTable table;
	private JButton btnClear;
	private JButton btnAddItem;
	private ShoppingCartModel tableModel;
	private JButton btnRemove;
	private JTable mainTable;
	private JButton btnView;
	private JLink btnEditQuantity;

	/**
	 * Create the dialog.
	 */
	public ShoppingCart(Customer customer) {
		if (customer == null) {
			throw new IllegalArgumentException("Customer cannot be null!");
		}
		this.customer = customer;
		
		setModal(true);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel topPanel = new JPanel();
		getContentPane().add(topPanel, BorderLayout.NORTH);
		GridBagLayout gbl_topPanel = new GridBagLayout();
		gbl_topPanel.columnWidths = new int[]{0, 0, 0, 0};
		gbl_topPanel.rowHeights = new int[]{0, 0, 0, 0};
		gbl_topPanel.columnWeights = new double[]{1.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_topPanel.rowWeights = new double[]{0.0, 0.0, 1.0, Double.MIN_VALUE};
		topPanel.setLayout(gbl_topPanel);
		
		JLabel lblTest_1 = new JLabel(
				String.format("%s %s's shopping cart", 
						customer.getFirstName(), 
						customer.getLastName()
				)
		);
		GridBagConstraints gbc_lblTest_1 = new GridBagConstraints();
		gbc_lblTest_1.gridwidth = 3;
		gbc_lblTest_1.insets = new Insets(0, 0, 5, 0);
		gbc_lblTest_1.gridx = 0;
		gbc_lblTest_1.gridy = 0;
		topPanel.add(lblTest_1, gbc_lblTest_1);
		
		btnClear = new JButton("Clear");
		GridBagConstraints gbc_btnTest_2 = new GridBagConstraints();
		gbc_btnTest_2.insets = new Insets(0, 0, 5, 5);
		gbc_btnTest_2.gridx = 1;
		gbc_btnTest_2.gridy = 1;
		topPanel.add(btnClear, gbc_btnTest_2);
		
		btnAddItem = new JButton("Add item");
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.insets = new Insets(0, 0, 5, 0);
		gbc_btnNewButton.gridx = 2;
		gbc_btnNewButton.gridy = 1;
		topPanel.add(btnAddItem, gbc_btnNewButton);
		
		JPanel middlePanel = new JPanel();
		getContentPane().add(middlePanel, BorderLayout.CENTER);
		middlePanel.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPanel = new JScrollPane();
		middlePanel.add(scrollPanel);
		
		// Table
		tableModel = new ShoppingCartModel(this.customer.getShoppingCart());
		mainTable = new JTable();
		mainTable.setModel(tableModel);
		mainTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPanel.setViewportView(mainTable);
		
		JPanel bottomPanel = new JPanel();
		contentPane.add(bottomPanel, BorderLayout.SOUTH);
		bottomPanel.setLayout(new BorderLayout(0, 0));
		
		JPanel tableBottomOptionsPanel = new JPanel();
		bottomPanel.add(tableBottomOptionsPanel, BorderLayout.NORTH);
		GridBagLayout gbl_tableBottomOptionsPanel = new GridBagLayout();
		gbl_tableBottomOptionsPanel.columnWidths = new int[]{0, 0, 0, 0, 0};
		gbl_tableBottomOptionsPanel.rowHeights = new int[]{0, 0};
		gbl_tableBottomOptionsPanel.columnWeights = new double[]{1.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_tableBottomOptionsPanel.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		tableBottomOptionsPanel.setLayout(gbl_tableBottomOptionsPanel);
		
		btnView = new JLink("View", JLink.COLORS.GREEN);
		btnView.setEnabled(false);
		GridBagConstraints gbc_btnView = new GridBagConstraints();
		gbc_btnView.insets = new Insets(0, 0, 0, 5);
		gbc_btnView.gridx = 1;
		gbc_btnView.gridy = 0;
		tableBottomOptionsPanel.add(btnView, gbc_btnView);
		
		btnEditQuantity = new JLink("Edit Quantity", JLink.COLORS.INDIGO);
		btnEditQuantity.setEnabled(false);
		GridBagConstraints gbc_btnEditQuantity = new GridBagConstraints();
		gbc_btnEditQuantity.insets = new Insets(0, 0, 0, 5);
		gbc_btnEditQuantity.gridx = 2;
		gbc_btnEditQuantity.gridy = 0;
		tableBottomOptionsPanel.add(btnEditQuantity, gbc_btnEditQuantity);
		
		btnRemove = new JLink("Remove", JLink.COLORS.RED);
		btnRemove.setEnabled(false);
		GridBagConstraints gbc_btnRemove = new GridBagConstraints();
		gbc_btnRemove.gridx = 3;
		gbc_btnRemove.gridy = 0;
		tableBottomOptionsPanel.add(btnRemove, gbc_btnRemove);
		
		JPanel tableOptionsPanel = new JPanel();
		bottomPanel.add(tableOptionsPanel, BorderLayout.SOUTH);
		GridBagLayout gbl_tableOptionsPanel = new GridBagLayout();
		gbl_tableOptionsPanel.columnWidths = new int[]{0, 17, 0, 0, 0};
		gbl_tableOptionsPanel.rowHeights = new int[]{0, 0, 0, 0, 0};
		gbl_tableOptionsPanel.columnWeights = new double[]{0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_tableOptionsPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		tableOptionsPanel.setLayout(gbl_tableOptionsPanel);
		
		JLabel lblSubtotal = new JLabel("Subtotal:");
		GridBagConstraints gbc_lblSubtotal = new GridBagConstraints();
		gbc_lblSubtotal.anchor = GridBagConstraints.WEST;
		gbc_lblSubtotal.insets = new Insets(0, 0, 5, 5);
		gbc_lblSubtotal.gridx = 0;
		gbc_lblSubtotal.gridy = 0;
		tableOptionsPanel.add(lblSubtotal, gbc_lblSubtotal);
		
		JLabel lblDkk = new JLabel("20 DKK");
		lblDkk.setForeground(new Color(102, 102, 102));
		GridBagConstraints gbc_lblDkk = new GridBagConstraints();
		gbc_lblDkk.anchor = GridBagConstraints.WEST;
		gbc_lblDkk.insets = new Insets(0, 0, 5, 5);
		gbc_lblDkk.gridx = 2;
		gbc_lblDkk.gridy = 0;
		tableOptionsPanel.add(lblDkk, gbc_lblDkk);
		
		JLabel lblVipDiscount = new JLabel("VIP Discount:");
		GridBagConstraints gbc_lblVipDiscount = new GridBagConstraints();
		gbc_lblVipDiscount.anchor = GridBagConstraints.WEST;
		gbc_lblVipDiscount.insets = new Insets(0, 0, 5, 5);
		gbc_lblVipDiscount.gridx = 0;
		gbc_lblVipDiscount.gridy = 1;
		tableOptionsPanel.add(lblVipDiscount, gbc_lblVipDiscount);
		
		JLabel label = new JLabel("-20%");
		label.setForeground(new Color(0, 102, 0));
		GridBagConstraints gbc_label = new GridBagConstraints();
		gbc_label.anchor = GridBagConstraints.WEST;
		gbc_label.insets = new Insets(0, 0, 5, 5);
		gbc_label.gridx = 2;
		gbc_label.gridy = 1;
		tableOptionsPanel.add(label, gbc_label);
		
		JLabel lblTotal = new JLabel("Total:");
		GridBagConstraints gbc_lblTotal = new GridBagConstraints();
		gbc_lblTotal.insets = new Insets(0, 0, 5, 5);
		gbc_lblTotal.anchor = GridBagConstraints.WEST;
		gbc_lblTotal.gridx = 0;
		gbc_lblTotal.gridy = 2;
		tableOptionsPanel.add(lblTotal, gbc_lblTotal);
		
		JLabel lblDkk_1 = new JLabel("15 DKK");
		GridBagConstraints gbc_lblDkk_1 = new GridBagConstraints();
		gbc_lblDkk_1.anchor = GridBagConstraints.WEST;
		gbc_lblDkk_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblDkk_1.gridx = 2;
		gbc_lblDkk_1.gridy = 2;
		tableOptionsPanel.add(lblDkk_1, gbc_lblDkk_1);
		
		JButton btnCreateQuote = new JButton("Create quote");
		GridBagConstraints gbc_btnCreateQuote = new GridBagConstraints();
		gbc_btnCreateQuote.anchor = GridBagConstraints.EAST;
		gbc_btnCreateQuote.gridx = 3;
		gbc_btnCreateQuote.gridy = 3;
		tableOptionsPanel.add(btnCreateQuote, gbc_btnCreateQuote);
		this.addEventHandlers();
	}
	
	public void addEventHandlers() {
		
		 mainTable.getSelectionModel().addListSelectionListener(e -> {
			 if (mainTable.getSelectionModel().isSelectionEmpty()) {
				 btnView.setEnabled(false);
				 btnEditQuantity.setEnabled(false);
				 btnRemove.setEnabled(false);
			 } else {
				 btnView.setEnabled(true);
				 btnEditQuantity.setEnabled(true);
				 btnRemove.setEnabled(true);			 }
		    });
		
		btnClear.addActionListener(e -> {
			tableModel.clear();
			new ShoppingCartController().clearCart(customer.getShoppingCart());
		});
		
		btnRemove.addActionListener(e -> {
			tableModel.remove(mainTable.getSelectedRow());
		});
	}
	
}
