package gui;

import java.awt.Component;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Messages {

	private Messages() {
	}
	
	/**
	 * Show an error message
	 *
	 * @param message the message
	 */
	public static void Error(String message, String title) {
        JOptionPane.showMessageDialog(new JFrame(), message, title,
                JOptionPane.ERROR_MESSAGE);
	}
	public static void show(String message) {
        Error(message, "Error");
	}
	
	/**
	 * Shows a confirmation window
	 *
	 * @param component the component to center the confirmation window in. It can be null!
	 * @param message the message
	 * @param title the title
	 * @return true, if successful
	 */
	public static boolean confirmation(Component component, String message, String title) {
		int confirmResult = JOptionPane.showConfirmDialog(component, message, title, JOptionPane.YES_NO_OPTION);
        return confirmResult == 0 ? true : false;
	}
	public static boolean confirmation(Component component, String message) {
		return confirmation(null, message, "Confirm");
	}
	
	
	

}
