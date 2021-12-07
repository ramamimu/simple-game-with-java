import java.awt.Color;
import java.awt.Graphics;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.awt.event.MouseEvent;
//import java.awt.event.MouseListener;
import java.util.Random;

//import java.util.Timer;
import javax.swing.Timer;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Bola extends JPanel {
	
	private int radius = 50;
	private Color color;
	int x, y;
	int areaHeight;
	int areaWidth;
	int speed = 2;
	int direction;
	
	
	public Bola(Color color, int areaHeight, int areaWidth) {
		this.color = color;
		this.areaHeight = areaHeight;
		this.areaWidth = areaWidth;
		
		//initial position
		x =(int) (areaWidth / 1.7);
		y = 250;
	}
	
	// drawing ball
	public void draw(Graphics g) {
		g.setColor(color);
		g.fillOval((int)x, (int)y, radius, radius);
	}
	
	public void paint(Graphics g) {
		Random random = new Random();
		int R = random.nextInt(255);
		int G = random.nextInt(255);
		int B = random.nextInt(255);
		
		Color randcolor = new Color(R, G, B); 
		g.setColor(randcolor);
		g.fillOval((int)x, (int)y, radius, radius);
//		System.out.println("ini di paint y = " + y);
//		System.out.println("ini paint");
	}
	
	//bola bergerak ke atas dan bawah
	public void move() {
		if(y == 300) {
			direction = -1; // down
		}
		else {
			direction = 1; // up
		}
//		repaint();
	}
	

	public void motion() {
//		System.out.println("alhamdulillah masuk ke act umum");
		if(direction == -1) {
			y = y - speed;
			if(y <= 250) {
				
			}
		}
		else if(direction == 1) {
			y = y + speed;
			repaint();
			if(y >= 300) {
			}
		}
	}
	
}
