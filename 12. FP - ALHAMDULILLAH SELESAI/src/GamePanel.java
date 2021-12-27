import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionListener;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

//import simplekeyboardevent.Key;

public class GamePanel extends JPanel implements ActionListener, KeyListener{
	private static final long serialVersionUID = 1L;
	private float REFRESH_RATE = 15;
	private Triangle triangle;
	ArrayList<Obstacle> triangleArray;
	ArrayList<Obstacle> triangleSmall;
	ArrayList<Obstacle> coinList;
	ArrayList<Obstacle> obstacleArray;
	Obstacle obstacle;
	private int nTriangle = 2; //jumlah triangle besar
	private int nTriangleSmall = 6; //jumlah triangle kecil
	private int nCoin = 2; //jumlah coin
	
	private Line line;
	private Bola player1;
	Timer timer = new Timer(0, this);
	private Coin coin;
	Image background;
	String text;
	int score;	
	private int areaWidth;
	private int areaHeight;
	private int speed = 1;
	private int speedSmallBox ;
	private int positionLineY = 330;
	private int heightLine = 10;
	private ArrayList<Keyboard> keysAWSD;
	private ArrayList<Keyboard> keysArrow;
	Clip clip;
	private int statusSound = 2; //1 -> suara hidup || 2 -> suara mati
	String textSound = "Sound saat ini:";
	String instruksiSound = "Tekan '1' untuk menghidupkan suara || Tekan '2' untuk mematikan suara";
	
	public GamePanel(int width, int height) {
		this.areaWidth = width; 
		this.areaHeight = height;
		this.setPreferredSize(new Dimension(areaWidth, areaHeight));
		
		/* REVISI - ABSTRACT */
		obstacleArray = new ArrayList<Obstacle>();
		for(int i=0; i<nTriangle; i++) {
			obstacleArray.add(addTriangle());
		}
		for(int i=0; i<nTriangleSmall; i++) {
			obstacleArray.add(addTriangleSmall());
		}
		for(int i=0; i<nCoin; i++) {
			obstacleArray.add(addCoin());
		}
		/* BATAS REVISI - ABSTRACT */
		
//		box = new TriangleArea(0, 0, width, height, Color.BLACK , Color.PINK);
		line = new Line(width, heightLine, 0, 330, Color.green);
		Color red = new Color(221, 74, 72);
		player1 = new Bola(red, areaWidth/2, positionLineY, areaWidth, areaHeight, heightLine);
		background = Toolkit.getDefaultToolkit().getImage("bgGame.png");
		score = 0;
		text = "Score: "+score;
		
		
		this.keysAWSD = new ArrayList<Keyboard>();
		this.keyBoard('A', 'W', 'S', 'D', 550, 50, this.keysAWSD, "A", "W", "S", "D");
		this.keysArrow = new ArrayList<Keyboard>();
		this.keyBoard((char)KeyEvent.VK_LEFT, (char)KeyEvent.VK_UP, (char)KeyEvent.VK_DOWN, (char)KeyEvent.VK_RIGHT, 420, 50, this.keysArrow, "<", "^", "v", ">");
		
		this.addKeyListener(this);
		this.setFocusable(true);	
		
	}
	
	
	//(width, height, positionX, positionY, speed, color) 
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
//					REFRESH_RATE += 0.003;
					if(REFRESH_RATE >=25)
						REFRESH_RATE =25;

					/* REVISI - ABSTRACT */
					for(int i=0; i<obstacleArray.size(); i++) {
						obstacle = obstacleArray.get(i);
						obstacle.move();
						if(obstacle.positionX > areaWidth + obstacle.width){
							obstacle.setStart();
						}
					}
					/* BATAS REVISI - ABSTRACT */
					
//						if((int)player1.getRadius()<=0) {
//							text = "YOUR SCORE: "+score+ " (GAME OVER)";
//						}else {							
						score+=1000/(int) REFRESH_RATE;
						text="Score: "+ score;
//						}
					repaint();
					
					try {
						if((int)player1.getRadius()<=0) {
							text = "YOUR SCORE: "+score+ " (GAME OVER)";
							repaint();
							JOptionPane.showMessageDialog(null, "yahh... Game Over :(\nuntuk bermain lagi, silakan close aplikasi dan run-kan lagi", "yah kalah :(",JOptionPane.PLAIN_MESSAGE);
							Thread.sleep(100000);							
						}else
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
//		box.draw(g);
		g.drawImage(background, 0, 0, this);
		line.draw(g);
		g.setColor( new Color(0, 0, 0));
		Font stringFont = new Font( "SansSerif", Font.PLAIN, 20);
		g.setFont(stringFont);
		g.drawString(text, 20, 30);
		
		this.infoSound();
		stringFont = new Font( "SansSerif", Font.PLAIN, 12);
		g.setFont(stringFont);
		g.drawString(textSound, 10, 458);
		g.drawString(instruksiSound, 10, 472);
		
		player1.draw(g);
		 
		for(Keyboard key : this.keysAWSD) {
			key.render(g);
		}
		for(Keyboard key : this.keysArrow) {
			key.render(g);
		}
		
		/* REVISI - ABSTRACT */
		for(int i=0; i<obstacleArray.size(); i++) {
			obstacleArray.get(i).draw(g);
		}

		player1.collideTriangle(obstacleArray.subList(0, nTriangle), 0);
		player1.collideTriangle(obstacleArray.subList(nTriangle, nTriangle+nTriangleSmall), 1);
		player1.collideCoin(obstacleArray.subList(nTriangle+nTriangleSmall, nTriangle+nTriangleSmall+nCoin));
		/* BATAS REVISI - ABSTRACT */
	}
	
	public void mouseClicked(MouseEvent e) {
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
				player1.y = player1.y + player1.speedY;
//				repaint();
			}
		}
		else if(player1.yDirection == 1) {
			if(player1.y <= player1.yTop) {
				timer.stop();
			}
			else {
				player1.y = player1.y - player1.speedY;
//				repaint();
			}
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		
		int keyCode = e.getKeyCode();
		
		if(keyCode==KeyEvent.VK_W || keyCode==KeyEvent.VK_UP) {
			player1.setYDirection(1);
			timer.start();
		}
		else if(keyCode==KeyEvent.VK_S || keyCode==KeyEvent.VK_DOWN) {
			player1.setYDirection(-1);
			timer.start();
		}
		else{
			player1.keyPressed(keyCode);
		}
		
		for(Keyboard k : this.keysAWSD) {
			if(k.isSymbolMatch((char)keyCode)) {
				k.setPressed();
				repaint(); 
			}
		}
		
		for(Keyboard k : this.keysArrow) {
			if(k.isSymbolMatch((char)keyCode)) {
				k.setPressed();
				repaint(); 
			}
		}
		
		if(keyCode==KeyEvent.VK_1) {
			clip.start();
			statusSound = 1;
		}
		if(keyCode==KeyEvent.VK_2) {
			clip.stop();
			statusSound = 2;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		
		int keyCode = e.getKeyCode();
		
		player1.keyReleased(keyCode);
		
		for(Keyboard k : this.keysAWSD) {
			if(k.isSymbolMatch((char)keyCode)) {
				k.setRelease();
				repaint(); 
			}
		}
		
		for(Keyboard k : this.keysArrow) {
			if(k.isSymbolMatch((char)keyCode)) {
				k.setRelease();
				repaint(); 
			}
		}
	}
	
	public void keyBoard(char left, char up, char down, char right, int x, int y, ArrayList<Keyboard> keys, String textLeft, String textUp, String textDown, String textRight) {
		//x, y -> position
		
//		this.keys = new ArrayList<Keyboard>();
		
		keys.add(new Keyboard(x, y-30, up, textUp));
		keys.add(new Keyboard(x-35, y+5, left, textLeft));
		keys.add(new Keyboard(x, y+5, down, textDown));
		keys.add(new Keyboard(x+35, y+5, right, textRight));
	}


	public void startSound() {
		try{
			File file = new File("Calimba.wav");
			AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);
			clip = AudioSystem.getClip();
			clip.open(audioStream);
			clip.start();
		}
		catch (Exception e){
			System.out.println(e);
		}
		statusSound = 1;
	}
	
	public void infoSound() {
		if(statusSound == 1) {
			textSound = "Saat ini: Suara hidup";
		}
		else if(statusSound == 2) {
			textSound = "Saat ini: Suara mati";
		}
	}

}
