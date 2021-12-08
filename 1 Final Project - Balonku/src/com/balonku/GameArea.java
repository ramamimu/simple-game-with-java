package com.balonku;

import java.awt.Color;
import java.awt.Graphics;

public class GameArea {
	
	int minX;
	int maxX;
	int minY;
	int maxY;
	private Color colorFilled;
	private Color colorBorder;
	
	public GameArea(int x, int y, int width, int height, Color colorFilled, Color colorBorder) {
		
		this.minX = x;
		this.minY = y;
		this.maxX = x + width - 1;
		this.maxY = y + height - 1;
		this.colorFilled = colorFilled;
		this.colorBorder = colorBorder;
	}
	
	public void draw(Graphics g) {
		g.setColor(colorFilled);
		g.fillRect(minX, minY, maxX-minX-1, maxY-minY-1);
		g.setColor(colorBorder);
		g.drawRect(minX, minY, maxX-minX-1, maxY-minY-1);
	}
}
