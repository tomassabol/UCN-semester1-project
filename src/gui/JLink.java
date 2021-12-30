package gui;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.border.EmptyBorder;

public class JLink extends JButton {
	
	public static final Color INDIGO_COLOR = new Color(101, 88, 245);
	public static final Color GREEN_COLOR = new Color(32, 120, 104);
	public static final Color RED_COLOR = new Color(211, 69, 91);


	private static final long serialVersionUID = -6117412042952963334L;
	
	public enum COLORS {
		INDIGO,
		GREEN,
		RED,
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
        
		switch(color) {
			case INDIGO: this.setForeground(INDIGO_COLOR);break;
			case GREEN: this.setForeground(GREEN_COLOR);break;
			case RED: this.setForeground(RED_COLOR);break;
			default: this.setForeground(INDIGO_COLOR);break;
		}
		
		this.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		
		this.addMouseListener(new MouseAdapter() {
			
			@Override
		    public void mouseEntered(MouseEvent e) {
				if (JLink.this.isEnabled()) {
			        // the mouse is on the label: underline it
			    	JLink.this.setText("<HTML><U>" + text +"</U></HTML>");
				}

		    }
		 
			@Override
		    public void mouseExited(MouseEvent e) {
				if (JLink.this.isEnabled()) {
			        // the mouse has exited the label: set back to original
			    	JLink.this.setText(text);
				}
		    }
		});
	}
	
	public JLink(String text) {
		this(text, COLORS.INDIGO);
	}
	


}
