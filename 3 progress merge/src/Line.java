import java.awt.Color;
import java.awt.Graphics;

public class Line {
	float width, height;
//	float speed;
//	float radius;
	float positionX, positionY;
	private Color color;

	public Line(float width, float height, float positionX, float positionY, Color color) {
		super();
		this.width = width;
		this.height = height;
		this.positionX = positionX;
		this.positionY = positionY;
		this.color = color;
	}

	public void draw(Graphics g) {
		g.setColor(color);
		g.fillRect((int) positionX, (int) positionY, (int) width, (int) height);
	}

}
