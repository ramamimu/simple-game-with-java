import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Program {
	public static void main(String[] args) {
		
		GamePanel fp = new GamePanel(640, 480);
		
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				JFrame frame = new JFrame("Balonku 8");
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setContentPane(fp);
				frame.pack();
				frame.setVisible(true);
			}
		});
		
		JOptionPane.showConfirmDialog(null, 
				"Selamat datang!\n" +  
				"Anda bermain sebagai bola\n" +
				"Menyentuh kotak besar akan game over\n" +
				"Menyentuh kotak kecil, bola akan mengecil\n" +
				"Menyentuh koin kuning, bola akan membesar\n" +
				"jika bola terus mengecil, bola dapat habis dan game over\n\n" +
				"Anda dapat menggerakkan bola dengan keyboard AWSD\n" +
				"Selamat bermain!",
				"Instruction", JOptionPane.PLAIN_MESSAGE);
		
//		NewWindow instruction = new NewWindow();
//		if(!instruction.end()) {
//			fp.startThread();
//		}
		
		fp.startThread();
	}
}
