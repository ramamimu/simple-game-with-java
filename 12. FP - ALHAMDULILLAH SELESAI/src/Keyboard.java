import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class Keyboard {
	
	private static final int WIDTH = 30;
	private static final int HEIGHT = 30;
	private static final int RELEASED = 1;
	private static final int PRESSED = 2;
	
	private int x;
	private int y;
	private char symbol;
	private String text;
	private int state;
	
	public Keyboard(int x, int y, char symbol, String text) {
		// TODO Auto-generated constructor stub
		this.x = x;
		this.y = y;
		this.state = RELEASED;
		this.symbol = symbol;
		this.text = text;
	}
	
	public void render(Graphics g) {
		//render tuts sesuai dengan state
		if(this.state == RELEASED) {
			g.setColor(Color.BLUE);
		} else if(this.state == PRESSED) {
			g.setColor(Color.RED);
		} else {
			g.setColor(Color.WHITE);
		}
		g.fillRect(this.x, this.y, WIDTH, HEIGHT);
		
		//render text
		int stringPosX = (int)this.x + (WIDTH / 4);
		int stringPosY = this.y + (HEIGHT / 2 + HEIGHT/8);
		g.setColor(Color.BLACK);
//		g.drawString(String.valueOf(this.symbol), stringPosX, stringPosY);
		g.setFont(new Font("SansSerif", Font.BOLD, 14));
		g.drawString(this.text, stringPosX, stringPosY);
	}

	public boolean isSymbolMatch(char symbol) {
		if(this.symbol == symbol) {
			return true;
		}
		
		return false;
	}
	
	public void setPressed() {
		this.state = PRESSED;
	}
	
	public void setRelease() {
		this.state = RELEASED;
	}
}
