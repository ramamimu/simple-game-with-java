import java.awt.*;
import java.awt.event.ComponentAdapter;
//import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.ArrayList;
import java.util.Random;

//import java.awt.event.*;
//import java.util.Random;
import javax.swing.*;

public class GamePanel extends JPanel{
	private static final long serialVersionUID = 1L;
	private float REFRESH_RATE = 20;
	private Triangle triangle;
	ArrayList<Triangle> triangleArray;
	ArrayList<Triangle> triangleSmall;
	private TriangleArea box;
	private Line line;
	private int areaWidth;
	private int areaHeight;
	private int speed = 1;
	private int speedSmallBox = 2;
	
	
	public GamePanel(int width, int height) {
		this.areaWidth = width; 
		this.areaHeight = height;
		this.setPreferredSize(new Dimension(areaWidth, areaHeight));
//		(width, height,  
//		positionX, positionY, 
//		speed, color) 
//		triangle = new Triangle(50, 50, 30, 280, 10, Color.blue);
//		
//		speed = 10;
		triangleArray = new ArrayList<Triangle>();	
		triangleSmall = new ArrayList<Triangle>();	

		triangleArray.add(addTriangle());
		triangleSmall.add(addTriangleSmall());
		
		box = new TriangleArea(0, 0, width, height, Color.BLACK , Color.PINK);
		line = new Line(width, 10, 0, 330, Color.green);
		this.addComponentListener(new ComponentAdapter() {
			@Override						
			public void componentResized(ComponentEvent e) {
				Component c = (Component)e.getSource();
				Dimension dim = c.getSize();
				areaWidth = dim.width;
				areaHeight = dim.height;
				box.set(0, 0, width, height);
			}
		});
		
		startThread();
			
		}

		public Triangle addTriangle() {
			triangle = new Triangle(50, 50, 0, 280, speed, Color.blue);
			return triangle;
		}

		public Triangle addTriangleSmall() {
			triangle = new Triangle(10, 10, 0, 340, speedSmallBox, Color.blue);
			return triangle;
		}
		
		public int randomNumber(int n) {
			Random rand = new Random();
			return rand.nextInt(n);
		}
	
		public void startThread() {
			Thread gameThread = new Thread() {
				public void run() {
					while (true) {
						REFRESH_RATE += 0.01;
//						speed += 1;
//						speedSmallBox += 1;
//						float[][] ballPosition = {{ball2.x, ball2.y, ball2.radius},{ball3.x,ball3.y, ball3.radius}};
//						float[][] ballPosition2 = {{ball.x, ball.y, ball.radius},{ball3.x,ball3.y, ball3.radius}};
//						float[][] ballPosition3 = {{ball.x, ball.y, ball.radius},{ball2.x, ball2.y, ball2.radius}};
						
//						ball.collide(box, ballPosition);
//						ball2.collide(box, ballPosition2);
//						ball3.collide(box, ballPosition3);
//						textBall = "Bola biru berada di x = " + (int)ball.x + ", y = " + (int)ball.y;
//						textBall2 = "Bola merah berada di x = " + (int)ball2.x + ", y = " +(int)ball2.y;
//						textBall3 = "Bola kuning berada di x = " + (int)ball3.x + ", y = " +(int)ball3.y;
//						triangle.collide(box);
						for(int i =0 ; i< triangleArray.size(); i++) {
							triangleArray.get(i).collide(box);
						}
						for(int i =0 ; i< triangleSmall.size(); i++) {
							triangleSmall.get(i).collide(box);
						}
						
						repaint();
						try {
							Thread.sleep(1000/(int) REFRESH_RATE);
						} catch (InterruptedException ex) {}
					}
				}
			};
			gameThread.start();
		}
		@Override
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			box.draw(g);
			line.draw(g);
			if(triangleArray.get(triangleArray.size()-1).positionX > 150 + randomNumber(1000)) {
				triangleArray.add(addTriangle());
			}

			for(int i =0 ; i< triangleArray.size(); i++) {
				triangleArray.get(i).draw(g);
				if(triangleArray.get(i).positionX + 45 > 640) {					
					triangleArray.get(i).width-=10;
					if(triangleArray.get(i).width < 0 ) {
						triangleArray.get(i).width=0;							
					}
				}
				if(triangleArray.get(i).width <-300) {					
					triangleArray.remove(i);
				}
			}
			
//			Kotak kecil
			if(triangleSmall.get(triangleSmall.size()-1).positionX > 120 + randomNumber(500)) {
				triangleSmall.add(addTriangleSmall());
			}

			for(int i =0 ; i< triangleSmall.size(); i++) {
				triangleSmall.get(i).draw(g);
				if(triangleSmall.get(i).positionX + 25 > 640) {					
					triangleSmall.get(i).width-=10;
					if(triangleSmall.get(i).width < 0 ) {
						triangleSmall.get(i).width=0;							
					}
				}
				if(triangleSmall.get(i).width <-300) {					
					triangleSmall.remove(i);
				}
			}
		}		
}
