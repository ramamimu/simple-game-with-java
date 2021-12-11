import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.IOException;
import javax.swing.JPanel;
public class BackgroundImg extends javax.swing.JFrame{
	   Image img = Toolkit.getDefaultToolkit().getImage("C:\\bgGame.png");
	   public BackgroundImg() throws IOException {
	      this.setContentPane(new JPanel() {
	         @Override
	         public void paintComponent(Graphics g) {
	            super.paintComponent(g);
	            g.drawImage(img, 100, 100, null);
	         }
	      });
	      pack();
	      setVisible(true);
	   }
//	   public static void main(String[] args) throws Exception {
//	      new BackgroundImg();
//	   }
}
