import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class NewWindow {
	
	JFrame frame = new JFrame();
	JLabel label = new JLabel("bismillah");
	
	public NewWindow() {
		frame.setPreferredSize(new Dimension(420, 420));
		frame.add(label);
	}
	
}