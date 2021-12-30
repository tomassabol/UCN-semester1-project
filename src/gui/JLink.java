package gui;

import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JLabel;

public class JLink extends JLabel {


	private static final long serialVersionUID = -6117412042952963334L;
	
	public enum COLORS {
		BLUE
	}
	
	String text;
	COLORS color;

	public JLink(String text, COLORS color) {
		super(text);
		this.text = text;
		this.color = color;
		
		this.setForeground(ColorPalette.LINK_COLOR);
		this.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		
		this.addMouseListener(new MouseAdapter() {
			
			@Override
		    public void mouseEntered(MouseEvent e) {
		        // the mouse is on the label: underline it
		    	JLink.this.setText("<HTML><U>" + text +"</U></HTML>");
		    }
		 
			@Override
		    public void mouseExited(MouseEvent e) {
		        // the mouse has exited the label: set back to original
		    	JLink.this.setText(text);
		    }
		});
	}
	
	public JLink(String text) {
		this(text, COLORS.BLUE);
	}
	


}
