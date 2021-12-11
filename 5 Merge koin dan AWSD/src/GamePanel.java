import java.awt.*;
import java.awt.event.ComponentAdapter;
//import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;

//import java.awt.event.*;
//import java.util.Random;
//import javax.swing.*;

import java.awt.Color;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.ActionListener;

//import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

public class GamePanel extends JPanel implements ActionListener, KeyListener{
	private static final long serialVersionUID = 1L;
	private float REFRESH_RATE = 20;
	private Triangle triangle;
	ArrayList<Triangle> triangleArray;
	ArrayList<Triangle> triangleSmall;
	ArrayList<Coin> coinList;
	private TriangleArea box;
	private Line line;
	private Bola player1;
//	private Garis jalur;
	Timer timer = new Timer(0, this);
	private Coin coin;

	private int areaWidth;
	private int areaHeight;
	private int speed = 1;
	private int speedSmallBox = 2;
	private int positionLineY = 330;
	
	
	public GamePanel(int width, int height) {
		this.areaWidth = width; 
		this.areaHeight = height;
		this.setPreferredSize(new Dimension(areaWidth, areaHeight));
//		(width, height,  
//		positionX, positionY, 
//		speed, color) 
		Color blueTeal = new Color(149, 209, 204);
		
		triangleArray = new ArrayList<Triangle>();	
		triangleSmall = new ArrayList<Triangle>();	

		triangleArray.add(addTriangle());
		triangleSmall.add(addTriangleSmall());

		coinList = new ArrayList<Coin>();
		coinList.add(addCoin());
		
		box = new TriangleArea(0, 0, width, height, Color.BLACK , Color.PINK);
		line = new Line(width, 10, 0, 330, Color.green);

		player1 = new Bola(blueTeal, areaWidth/2, positionLineY, areaWidth, areaHeight);
//		this.add(player1);

//		jalur= new Garis(areaWidth, areaHeight, blueTeal);
//		this.add(jalur);
		
		this.addKeyListener(this);
		this.setFocusable(true);

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
	
//		(width, height, positionX, positionY, speed, color) 
		public Coin addCoin() {
			Color gold = new Color(255, 233, 0); 
			coin = new Coin(20,20, 0, 310, 5, gold);
			return coin;
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
						
						for(int i =0 ; i< coinList.size(); i++) {
							coinList.get(i).collide(box);
						}
//						coin.collide();
						
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
//			jalur.draw(g);
//			coin.draw(g);
//			coin
			if(coinList.get(coinList.size()-1).positionX > randomNumber(2000) + randomNumber(1000)) {
				coinList.add(addCoin());
			}
			for(int i =0 ; i< coinList.size(); i++) {
				coinList.get(i).draw(g);
				if(coinList.get(i).positionX + 25 > 640) {					
					coinList.get(i).width-=10;
					if(coinList.get(i).width < 0 ) {
						coinList.get(i).width=0;							
					}
				}
				if(coinList.get(i).width <-300) {					
					coinList.remove(i);
				}
			}

			player1.draw(g);

			if(triangleArray.get(triangleArray.size()-1).positionX > 250 + randomNumber(1000)) {
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
			if(triangleSmall.get(triangleSmall.size()-1).positionX >  randomNumber(1000)+ randomNumber(500)) {
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
		
		public void mouseClicked(MouseEvent e) {
//			player1.move();
			timer.start();
			repaint();
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			if(player1.yDirection == -1) {
				if(player1.y >= player1.yUnder) {
					timer.stop();
				}
				else {
					player1.y = player1.y + player1.speed;
					repaint();
				}
			}
			else if(player1.yDirection == 1) {
				if(player1.y <= player1.yTop) {
					System.out.println("yah");
					timer.stop();
				}
				else {
					player1.y = player1.y - player1.speed;
					repaint();
				}
			}
		}

		@Override
		public void keyTyped(KeyEvent e) {
			
		}

		@Override
		public void keyPressed(KeyEvent e) {
			
			if(e.getKeyCode()==KeyEvent.VK_W) {
				player1.setYDirection(1);
				timer.start();
			}
			else if(e.getKeyCode()==KeyEvent.VK_S) {
				player1.setYDirection(-1);
				timer.start();
			}
			else{
				player1.keyPressed(e);
			}
		}

		@Override
		public void keyReleased(KeyEvent e) {
			player1.keyReleased(e);
		}
		

}
