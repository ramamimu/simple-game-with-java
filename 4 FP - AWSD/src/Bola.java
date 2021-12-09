import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.awt.event.MouseEvent;
//import java.awt.event.MouseListener;
import java.util.Random;

//import java.util.Timer;
import javax.swing.Timer;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Bola extends Rectangle{
	
	private static int radius = 50;
	private Color color;
	int x, y;
	int yTop; // posisi y ketika bola di atas line
	int yUnder; // posisi y ketika bola di bawah line
	int speed = 2;
	int yDirection;
	int xDirection;
	int xVelocity;
	int yVelocity;
	
	
	public Bola(Color color, int x, int y) {
		super(x, y, 2*radius, 2*radius);
		
		this.color = color;
		this.yTop = y - 50;
		this.yUnder = y + 10;
		System.out.println(yUnder);
		
		//initial position
		this.x = x;
		this.y = yTop;
		
	}
	
	// drawing ball
	public void draw(Graphics g) {
		g.setColor(color);
		g.fillOval((int)x, (int)y, radius, radius);
	}
	
	
	//bola bergerak ke atas dan bawah
//	public void move() {
//		if(y == yTop) {
//			yDirection = -1; // down
//		}
//		else {
//			yDirection = 1; // up
//		}
//	}
	
	public void xMove() {
		x = x + xVelocity;
		// if xVelocity positive, ball move to right
		// if xVelocity negative, ball move to left
	}
	
	public void yMove() {
		y = y + yVelocity;
		// if xVelocity positive, ball move to right
		// if xVelocity negative, ball move to left
	}
	
	public void keyPressed(KeyEvent e) {
		System.out.println("alhamdulillah");
//		if(e.getKeyCode()==KeyEvent.VK_W) {
//			setYDirection(1);
//		}
//		
//		if(e.getKeyCode()==KeyEvent.VK_S) {
//			setYDirection(-1);
//		}
		
		if(e.getKeyCode()==KeyEvent.VK_D) {
			setXDirection(speed);
			xMove();
		}
		
		if(e.getKeyCode()==KeyEvent.VK_A) {
			setXDirection(- speed);
			xMove();
		}
//		yMove();
//		xMove();
	}
	
	public void keyReleased(KeyEvent e) {
		System.out.println("bismillah");
		if(e.getKeyCode()==KeyEvent.VK_W) {
			setYDirection(1);
		}
		
		if(e.getKeyCode()==KeyEvent.VK_S) {
			setYDirection(-1);
		}
		
		if(e.getKeyCode()==KeyEvent.VK_D) {
			setXDirection(0);
		}
		
		if(e.getKeyCode()==KeyEvent.VK_A) {
			setXDirection(0);
		}
	}
	
	public void setXDirection(int xDirection) {
		xVelocity = xDirection;
	}
	
	public void setYDirection(int yDirection) {
		this.yDirection = yDirection;
//		yVelocity = yDirection;
	}
	
}
