import java.awt.*;

public class Coin{
	float width, height;
	float speed;
	float positionX, positionY;
	private Color color;

	public Coin(float width, float height,  
			float positionX, float positionY, 
			float speed, Color color) {
		super();
		this.width = width;
		this.height = height;
		this.speed = speed;
		this.positionX = positionX;
		this.positionY = positionY;
		this.color = color;
	}
	
	public void draw(Graphics g) {
		g.setColor(color);
		this.speed+=0.007;
		g.fillOval((int) positionX, (int) positionY, (int) width, (int) height);
	}
	 public void collide(TriangleArea box) {
		positionX += speed;		
	}

}
