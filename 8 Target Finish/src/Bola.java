import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.awt.event.MouseEvent;
//import java.awt.event.MouseListener;
import java.util.Random;

//import java.util.Timer;
import javax.swing.Timer;

import javax.swing.JPanel;

public class Bola{
	
	private float radius = 50;
	private Color color;
	int x, y;
	int yInitial;
	int yTop; // posisi y ketika bola di atas line
	int yUnder; // posisi y ketika bola di bawah line
	int speed = 10;
	int speedY =  1;
	int yDirection;
	int xDirection;
	int xVelocity;
	int yVelocity;
	int widthArea, heightArea;
	int heightLine;
	
	
	public Bola(Color color, int x, int y, int width, int height, int heightLine) {		
		this.color = color;
		this.heightLine = heightLine;
		this.yInitial = y;
		setYBound();
		
		//initial position
		this.x = x;
		this.y = yTop;
		
		//batas frame
		this.widthArea = width;
		this.heightArea = height;
	}
	
	public void setYBound() {
		this.yTop = yInitial - (int) this.radius;
		this.yUnder = yInitial + this.heightLine;
	}
	
	public float getRadius() {
		return this.radius;
	}
	
	// drawing ball
	public void draw(Graphics g) {
		g.setColor(color);
		g.fillOval((int)x, (int)y, (int)radius, (int)radius);
	}
		
	public void xMove() {
		if(x+ xVelocity<=0)
			x = 0;
		if(x+ xVelocity>=widthArea-radius)
			x=widthArea-(int)radius;
		else
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
//		System.out.println("alhamdulillah");
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
//		System.out.println("bismillah");
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
	
	public void collide(ArrayList<Coin> koin) {
		float pusatBolaX = x+radius/2;
		float pusatBolaY = y+radius/2;

		for(Coin i : koin) {
			float pusatKoinX = i.positionX + i.width/2;
			float pusatKoinY = i.positionY + i.width/2;
			
			float selisihX = Math.abs(pusatBolaX-pusatKoinX);
			float selisihY = Math.abs(pusatBolaY-pusatKoinY);
			double distance = Math.sqrt(selisihX*selisihX+selisihY*selisihY);
			if(distance+radius/4 <= radius && i.positionY+i.height == y + radius ) {				
//				System.out.printf("%f distance KOIN\n", distance);			
//				System.out.printf("%d %f bola koin\n", y, i.positionY);
				radius+=3;
				y-=3;
				i.width-=20;
				i.height-=20;
				i.positionY-=50;
			}
	
		}
	}
	
	public void collide(ArrayList<Triangle> triangleArray, int status) {
		float pusatBolaX = x+radius/2;
		float pusatBolaY = y+radius/2;
		for(Triangle i : triangleArray) {
			float pusatRectangleX = i.positionX + i.width/2;
			float pusatRectangleY = i.positionY + i.width/2;
			
			float selisihX = Math.abs(pusatBolaX-pusatRectangleX);
			float selisihY = Math.abs(pusatBolaY-pusatRectangleY);
			double distance = Math.sqrt(selisihX*selisihX+selisihY*selisihY);
			if(distance+radius/4 <= radius) {
				if(status == 0) {
//					System.out.printf("besar\n", distance);								
//					System.out.printf("%f distance ", distance);
					radius = 0;
				}else if (status == 1 && i.positionY == y) {					
//					System.out.printf("%f distance ", distance);			
//					System.out.printf("kecil\n", distance);			
//					System.out.printf("");
					i.width = 0;
					i.height = 0;
					i.positionY += 50;
					radius-=5;
					if(radius<=10)
						radius = 0;
				}
			}
			
		}
		setYBound();
	}
	
	public void setXDirection(int xDirection) {
		xVelocity = xDirection;
	}
	
	public void setYDirection(int yDirection) {
		this.yDirection = yDirection;
//		yVelocity = yDirection;
	}
	
}
