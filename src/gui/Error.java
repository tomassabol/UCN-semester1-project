package gui;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Error {

	private Error() {
	}
	
	public static void show(String message) {
        JOptionPane.showMessageDialog(new JFrame(), message, "Error",
                JOptionPane.ERROR_MESSAGE);
	}

}
