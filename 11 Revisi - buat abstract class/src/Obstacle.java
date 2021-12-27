import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

public abstract class Obstacle {
	float width, height;
	float speed;
	float positionX, positionY;
	Color color;
	
	public Obstacle(float width, float height,  
			float positionX, float positionY, 
			float speed, Color color) {
		this.width = width;
		this.height = height;
		this.speed = speed;
//		this.positionX = positionX;
		this.positionX = - this.randomNumber(400);
		this.positionY = positionY;
		this.color = color;
	}
	
	public void move() {
		positionX += speed;
	}
	
	public int randomNumber(int n) {
		Random rand = new Random();
		return rand.nextInt(n);
	}
	
	public void setStart() {
		positionX = -width - randomNumber(250) - randomNumber(100);
	}
	
	public abstract void draw(Graphics g);
}
