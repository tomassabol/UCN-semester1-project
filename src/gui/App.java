package gui;

import java.awt.EventQueue;
import java.time.LocalDate;

import javax.swing.UIManager;

import com.formdev.flatlaf.FlatLightLaf;

import controller.EmployeeController;
import controller.GenerateDataController;

/**
 * @author Daniels Kanepe
 *
 */
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
		new EmployeeController().createEmployee("080600-1111", "admin@admin.com", "password", "Admin", "Admin", "Admin street 4", "991", LocalDate.now());

		// Generate some default data
		new GenerateDataController().generateData();
		
		// Show login window
		EventQueue.invokeLater(() -> {
			try {
				Login frame = new Login();
				frame.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
		
	}
}
