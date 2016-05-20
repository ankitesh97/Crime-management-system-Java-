package police;

import java.awt.Color;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

import javax.swing.JTextField;

public class Hover {
	
	static void effect (JTextField x){
		x.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				x.setBackground(new Color(250,235,46));
			}
			@Override
			public void focusLost(FocusEvent e) {
				x.setBackground(new Color(240,240,240));
			}
		});
	}

}
