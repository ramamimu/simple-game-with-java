package com.balonku;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Garis extends JPanel{
	
	int areaWidth;
	int areaHeight;
	Color color;
	
	public Garis(int widthArea, int heightArea, Color color) {
		this.areaWidth = widthArea;
		this.areaHeight = heightArea;
		this.color = color;
	}
	
	public void draw(Graphics g) {
//		g.drawRect(0, areaHeight/4, areaWidth, areaHeight/120);
//		g.drawRect(0, areaHeight/4, areaWidth, areaHeight);
//		g.fillRect(0, (int)(areaHeight/1.5), areaWidth, (int)areaHeight/120);
		g.setColor(color);
		g.fillRect(0, 300, areaWidth, (int)areaHeight/120);
	}
	
}
