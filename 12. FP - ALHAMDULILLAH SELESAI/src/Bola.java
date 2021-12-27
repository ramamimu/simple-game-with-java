import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
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
		this.xMove();
		this.yMove();
		
		g.fillOval((int)x, (int)y, (int)radius, (int)radius);
	}
		
	public void yMove() {
		this.setYBound();
		if(y+ yVelocity<=yTop) {
			y = yTop;
		}
		if(y + yVelocity>=yUnder)
			y = yUnder;
		else
			y = y + yVelocity;
		// if xVelocity positive, ball move to right
		// if xVelocity negative, ball move to left
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
	
	public void keyPressed(int keyCode) {
		
		if(keyCode==KeyEvent.VK_D || keyCode==KeyEvent.VK_RIGHT) {
			setXDirection(speed);
			xMove();
		}
		
		if(keyCode==KeyEvent.VK_A || keyCode==KeyEvent.VK_LEFT) {
			setXDirection(- speed);
			xMove();
		}
	}
	
	public void keyReleased(int keyCode) {
		if(keyCode==KeyEvent.VK_W) {
			setYDirection(1);
		}
		
		if(keyCode==KeyEvent.VK_S) {
			setYDirection(-1);
		}
		
		if(keyCode==KeyEvent.VK_D || keyCode==KeyEvent.VK_RIGHT) {
			setXDirection(0);
		}
		
		if(keyCode==KeyEvent.VK_A || keyCode==KeyEvent.VK_LEFT) {
			setXDirection(0);
		}
	}
	
	public void collideCoin(List<Obstacle> koin) {
		float pusatBolaX = x+radius/2;
		float pusatBolaY = y+radius/2;

		for(Obstacle i : koin) {
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
	
	public void collideTriangle(List<Obstacle> triangleArray, int status) {
		float pusatBolaX = x+radius/2;
		float pusatBolaY = y+radius/2;
		for(Obstacle i : triangleArray) {
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
	}
	
}
