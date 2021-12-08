package com.balonku;

import java.awt.Color;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

@SuppressWarnings("serial")
public class GamePanel extends JPanel implements MouseListener, ActionListener{
	
	private int areaWidth;
	private int areaHeight;
	Bola player1;
	Garis jalur;
	GameArea box;

	public GamePanel(int width, int height) {
		this.areaWidth = width;
		this.areaHeight = height;
		System.out.println("ini gamepanel area height = " + this.areaHeight );
		this.setPreferredSize(new Dimension(areaWidth, areaHeight));
		
		Color navy = new Color(34, 87, 126);
		box = new GameArea(0, 0, areaWidth, areaHeight, navy, Color.white);
		
		Color blueTeal = new Color(149, 209, 204);
		player1 = new Bola(blueTeal, areaWidth, areaHeight);
		this.add(player1);
		
		jalur= new Garis(areaWidth, areaHeight, blueTeal);
		this.add(jalur);
		
		this.addMouseListener(this);
		repaint();

	}
	
	
	public void paintComponent (Graphics g) {
		super.paintComponent(g);
		
		box.draw(g);
		player1.draw(g);
		jalur.draw(g);
	}
	

	@Override
	public void mouseClicked(MouseEvent e) {
		player1.move();
		timer.start();
		repaint();
	}

	@Override
	public void mousePressed(MouseEvent e) {}

	@Override
	public void mouseReleased(MouseEvent e) {}

	@Override
	public void mouseEntered(MouseEvent e) {}

	@Override
	public void mouseExited(MouseEvent e) {}
	
	@Override
	public void actionPerformed(ActionEvent e) {
//		System.out.println("alhamdulillah masuk ke act umum");
		if(player1.direction == -1) {
			player1.y = player1.y - player1.speed;
			repaint();
			if(player1.y <= 250) {
				timer.stop();
			}
		}
		else if(player1.direction == 1) {
			player1.y = player1.y + player1.speed;
			repaint();
			if(player1.y >= 300) {
				timer.stop();
			}
		}
	}
}
