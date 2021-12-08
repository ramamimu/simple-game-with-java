import java.awt.*;

public class Triangle {
	float width, height;
	float speed;
//	float radius;
	float positionX, positionY;
	private Color color;
	
	public Triangle(float width, float height,  
			float positionX, float positionY, 
			float speed, Color color) {
		super();
		this.width = width;
		this.height = height;
		this.speed = speed;
		this.positionX = positionX;
		this.positionY = positionY;
		this.color = color;
//		this.radius = radius;
	}
	public void draw(Graphics g) {
		g.setColor(color);
		this.speed+=0.007;
		g.fillRect((int) positionX, (int) positionY, (int) width, (int) height);
	}
	 public void collide(TriangleArea box) {
//		float MinX = box.minX + width;
//		float MinY = box.minY + width;
//		float MaxX = box.maxX - width;
//		float MaxY = box.maxY - width;
		
		positionX += speed;
		this.speed+=0.0001;
//		x += speedX;
//		y += speedY;
		
//		if (positionX < MinX-width ) {
//			speed = -speed;
//			positionX = MinX;
		
//		collide
//		if (positionX > MaxX) {
//			speed = -speed;
//			positionX = 0;
//		}

//		if (y < ballMinY) {
//			speedY = -speedY;
//			y = ballMinY;
//		} else if (y > ballMaxY) {
//			speedY = -speedY;
//			y = ballMaxY;
//		}
		
	}
}
