import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.IOException;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

//import simplekeyboardevent.Key;

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
	Timer timer = new Timer(0, this);
	private Coin coin;
	Image background;
	String text;
	int score;	
	private int areaWidth;
	private int areaHeight;
	private int speed = 1;
	private int speedSmallBox = 2;
	private int positionLineY = 330;
	private int heightLine = 10;
	private ArrayList<Keyboard> keysAWSD;
	private ArrayList<Keyboard> keysArrow;
	
	public GamePanel(int width, int height) {
		this.areaWidth = width; 
		this.areaHeight = height;
		this.setPreferredSize(new Dimension(areaWidth, areaHeight));
//		Color blueTeal = new Color(149, 209, 204);
		
		triangleArray = new ArrayList<Triangle>();	
		triangleSmall = new ArrayList<Triangle>();	

		triangleArray.add(addTriangle());
		triangleSmall.add(addTriangleSmall());

		coinList = new ArrayList<Coin>();
		coinList.add(addCoin());
		
		box = new TriangleArea(0, 0, width, height, Color.BLACK , Color.PINK);
		line = new Line(width, heightLine, 0, 330, Color.green);
		player1 = new Bola(Color.GRAY, areaWidth/2, positionLineY, areaWidth, areaHeight, heightLine);
		background = Toolkit.getDefaultToolkit().getImage("C:\\Users\\ASUS\\eclipse-workspace\\8 FP petunjuk game\\src");
		score = 0;
		text = "Score: "+score;
		
		this.keysAWSD = new ArrayList<Keyboard>();
		this.keyBoard('A', 'W', 'S', 'D', 550, 50, this.keysAWSD, "A", "W", "S", "D");
		this.keysArrow = new ArrayList<Keyboard>();
		this.keyBoard((char)KeyEvent.VK_LEFT, (char)KeyEvent.VK_UP, (char)KeyEvent.VK_DOWN, (char)KeyEvent.VK_RIGHT, 420, 50, this.keysArrow, "<", "^", "v", ">");
		
		this.addKeyListener(this);
		this.setFocusable(true);
		
//		startThread();
		
//		NewWindow instruction = new NewWindow();
//		JOptionPane.showMessageDialog(getComponentPopupMenu(), background);
		
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
					REFRESH_RATE += 0.01;
					for(int i =0 ; i< triangleArray.size(); i++) {
						triangleArray.get(i).collide(box);
					}
					for(int i =0 ; i< triangleSmall.size(); i++) {
						triangleSmall.get(i).collide(box);
					}
					
					for(int i =0 ; i< coinList.size(); i++) {
						coinList.get(i).collide(box);
					}
					
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
		box.draw(g);
		g.drawImage(background, 0, 0, null);
		line.draw(g);
		g.setColor( new Color(0, 0, 0));
		Font stringFont = new Font( "SansSerif", Font.PLAIN, 25 );
		g.setFont(stringFont);
		g.drawString(text, 20, 30);
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
		
		for(Keyboard key : this.keysAWSD) {
			key.render(g);
		}
		for(Keyboard key : this.keysArrow) {
			key.render(g);
		}

		if(triangleArray.get(triangleArray.size()-1).positionX > 250 + randomNumber(1000)) {
			triangleArray.add(addTriangle());
		}

		for(int i =0 ; i< triangleArray.size(); i++) {
			if(triangleArray.get(i).positionX + 45 > 640) {					
				triangleArray.get(i).width-=5;
				if(triangleArray.get(i).width < 0 ) {
					triangleArray.get(i).width=0;							
				}
			}
			if(triangleArray.get(i).positionX >= 640+740) {					
				triangleArray.remove(i);
			}
//				System.out.printf("%d jumlah array\n", triangleArray.size());
			triangleArray.get(i).draw(g);
		}
		
//			Kotak kecil
		if(triangleSmall.get(triangleSmall.size()-1).positionX >  randomNumber(1000)+ randomNumber(500)) {
			triangleSmall.add(addTriangleSmall());
		}

		for(int i =0 ; i< triangleSmall.size(); i++) {
			if(triangleSmall.get(i).positionX + 25 > 640) {					
				triangleSmall.get(i).width-=10;
				if(triangleSmall.get(i).width < 0 ) {
					triangleSmall.get(i).width=0;							
				}
			}
			if(triangleSmall.get(i).width <-300) {					
				triangleSmall.remove(i);
			}
			triangleSmall.get(i).draw(g);
		}
		player1.collide(triangleArray, 0);
		player1.collide(triangleSmall, 1);
		player1.collide(coinList);
		
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

}
