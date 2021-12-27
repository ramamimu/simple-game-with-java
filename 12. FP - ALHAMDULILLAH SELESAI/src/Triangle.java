import java.awt.*;

public class Triangle extends Obstacle{
	
	public Triangle(float width, float height,  
			float positionX, float positionY, 
			float speed, Color color) {
		super(width, height, positionX, positionY, speed, color);
		
	}
	
	@Override
	public void draw(Graphics g) {
		g.setColor(color);
		this.speed+=0.007;
		g.fillRect((int) positionX, (int) positionY, (int) width, (int) height);
	}
	
	@Override
	public void move() {
		positionX += speed;
//		this.speed+=0.0001;
	}
	
}
