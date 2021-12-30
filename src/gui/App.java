package gui;

import java.awt.EventQueue;
import java.time.LocalDate;

import javax.swing.UIManager;

import com.formdev.flatlaf.FlatLightLaf;

import controller.EmployeeController;
import model.IFEmployee;

public class App {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		// Set FlatLaf 'Look And Feel' 
		try {
		    UIManager.setLookAndFeel( new FlatLightLaf() );
		} catch( Exception ex ) {
		    System.err.println( "Failed to initialize LaF" );
		}
		
		// Add default employee (for logging in)
		new EmployeeController().createEmployee("080600-1111", "admin@admin.com", "password", "Admin", "no surname", "Admin street 4", "991", LocalDate.now());

		// Show login window
		EventQueue.invokeLater(() -> {
			try {
//				Login frame = new Login();
				ShoppingCart frame = new ShoppingCart();
				frame.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
		
	}
}
