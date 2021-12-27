import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

public abstract class Obstacle {
	float width, height;
	float speed;
	float positionX, positionY;
	Color color;
	float init_width, init_height, init_positionY;
	
	public Obstacle(float width, float height,  
			float positionX, float positionY, 
			float speed, Color color) {
		this.init_width = width;
		this.init_height = height;
		this.init_positionY = positionY;
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
		width = init_width;
		height = init_height;
		positionY = init_positionY;
		
	}
	
	public abstract void draw(Graphics g);

}
