package gui;

import java.awt.Cursor;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.border.EmptyBorder;

public class JLink extends JButton {


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
		
        setFocusPainted(false);
        setMargin(new Insets(0, 0, 0, 0));
        setContentAreaFilled(false);
        setBorderPainted(false);
        setOpaque(false);
		
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
