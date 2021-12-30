package gui;

import java.awt.EventQueue;

import javax.swing.JDialog;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.Component;
import javax.swing.Box;
import javax.swing.UIManager;
import java.awt.Color;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;

public class ShoppingCart extends JDialog {
	private JTable table;

	/**
	 * Create the dialog.
	 */
	public ShoppingCart() {
		setModal(true);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout(0, 0));
		
		JPanel topPanel = new JPanel();
		getContentPane().add(topPanel, BorderLayout.NORTH);
		GridBagLayout gbl_topPanel = new GridBagLayout();
		gbl_topPanel.columnWidths = new int[]{0, 0, 0, 0};
		gbl_topPanel.rowHeights = new int[]{0, 0, 0};
		gbl_topPanel.columnWeights = new double[]{1.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_topPanel.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		topPanel.setLayout(gbl_topPanel);
		
		JLabel lblTest_1 = new JLabel("Attila Bako's shopping cart");
		GridBagConstraints gbc_lblTest_1 = new GridBagConstraints();
		gbc_lblTest_1.gridwidth = 3;
		gbc_lblTest_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblTest_1.gridx = 0;
		gbc_lblTest_1.gridy = 0;
		topPanel.add(lblTest_1, gbc_lblTest_1);
		
		JButton btnTest_2 = new JButton("Clear");
		GridBagConstraints gbc_btnTest_2 = new GridBagConstraints();
		gbc_btnTest_2.insets = new Insets(0, 0, 0, 5);
		gbc_btnTest_2.gridx = 1;
		gbc_btnTest_2.gridy = 1;
		topPanel.add(btnTest_2, gbc_btnTest_2);
		
		JButton btnNewButton = new JButton("Add item");
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.gridx = 2;
		gbc_btnNewButton.gridy = 1;
		topPanel.add(btnNewButton, gbc_btnNewButton);
		
		JPanel middlePanel = new JPanel();
		getContentPane().add(middlePanel, BorderLayout.CENTER);
		middlePanel.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPanel = new JScrollPane();
		middlePanel.add(scrollPanel);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{"test", "test", "test", "test", "test"},
				{null, null, null, null, null},
				{null, null, null, null, null},
			},
			new String[] {
				"New column", "New column", "New column", "New column", "New column"
			}
		));
		scrollPanel.setViewportView(table);
		
		JPanel bottomPanel = new JPanel();
		getContentPane().add(bottomPanel, BorderLayout.SOUTH);
		GridBagLayout gbl_bottomPanel = new GridBagLayout();
		gbl_bottomPanel.columnWidths = new int[]{0, 0, 0, 0};
		gbl_bottomPanel.rowHeights = new int[]{0, 0, 0, 0, 0};
		gbl_bottomPanel.columnWeights = new double[]{0.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_bottomPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		bottomPanel.setLayout(gbl_bottomPanel);
		
		JLabel lblSubtotal = new JLabel("Subtotal:");
		GridBagConstraints gbc_lblSubtotal = new GridBagConstraints();
		gbc_lblSubtotal.anchor = GridBagConstraints.WEST;
		gbc_lblSubtotal.insets = new Insets(0, 0, 5, 5);
		gbc_lblSubtotal.gridx = 0;
		gbc_lblSubtotal.gridy = 0;
		bottomPanel.add(lblSubtotal, gbc_lblSubtotal);
		
		JLabel lblDkk = new JLabel("20 DKK");
		GridBagConstraints gbc_lblDkk = new GridBagConstraints();
		gbc_lblDkk.insets = new Insets(0, 0, 5, 5);
		gbc_lblDkk.gridx = 1;
		gbc_lblDkk.gridy = 0;
		bottomPanel.add(lblDkk, gbc_lblDkk);
		
		JLabel lblVipDiscount = new JLabel("VIP Discount:");
		GridBagConstraints gbc_lblVipDiscount = new GridBagConstraints();
		gbc_lblVipDiscount.anchor = GridBagConstraints.WEST;
		gbc_lblVipDiscount.insets = new Insets(0, 0, 5, 5);
		gbc_lblVipDiscount.gridx = 0;
		gbc_lblVipDiscount.gridy = 1;
		bottomPanel.add(lblVipDiscount, gbc_lblVipDiscount);
		
		JLabel label = new JLabel("-20%");
		GridBagConstraints gbc_label = new GridBagConstraints();
		gbc_label.insets = new Insets(0, 0, 5, 5);
		gbc_label.gridx = 1;
		gbc_label.gridy = 1;
		bottomPanel.add(label, gbc_label);
		
		JLabel lblTotal = new JLabel("Total:");
		GridBagConstraints gbc_lblTotal = new GridBagConstraints();
		gbc_lblTotal.insets = new Insets(0, 0, 5, 5);
		gbc_lblTotal.anchor = GridBagConstraints.WEST;
		gbc_lblTotal.gridx = 0;
		gbc_lblTotal.gridy = 2;
		bottomPanel.add(lblTotal, gbc_lblTotal);
		
		JLabel lblDkk_1 = new JLabel("15 DKK");
		GridBagConstraints gbc_lblDkk_1 = new GridBagConstraints();
		gbc_lblDkk_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblDkk_1.gridx = 1;
		gbc_lblDkk_1.gridy = 2;
		bottomPanel.add(lblDkk_1, gbc_lblDkk_1);
		
		JButton btnCreateQuote = new JButton("Create quote");
		GridBagConstraints gbc_btnCreateQuote = new GridBagConstraints();
		gbc_btnCreateQuote.anchor = GridBagConstraints.EAST;
		gbc_btnCreateQuote.gridx = 2;
		gbc_btnCreateQuote.gridy = 3;
		bottomPanel.add(btnCreateQuote, gbc_btnCreateQuote);

	}

}
