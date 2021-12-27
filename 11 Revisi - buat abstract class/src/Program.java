import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
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
				"Anda dapat menggerakkan bola dengan keyboard AWSD atau panah\n" +
				"Selamat bermain!",
				"Instruction", JOptionPane.PLAIN_MESSAGE);
		
		
		try{
			File file = new File("Calimba.wav");
			AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);
			Clip clip = AudioSystem.getClip();
			clip.open(audioStream);
			clip.start();
		}
		catch (Exception e){
			System.out.println(e);
		}
		fp.startThread();
	}
}
