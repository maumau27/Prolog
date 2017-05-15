package Draw;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

import javax.swing.JPanel;

public class Player extends JPanel{
	
	private Point position;
	private Dimension size = new Dimension(50, 50);
	
	public Player(Point p)
	{
		position = p;
		position.x = (int) (position.getX() * size.getWidth());
		position.y = (int) (position.getY() * size.getHeight());
		
		setPos();
	}
	
	public void setPos()
	{
		this.setLayout(null);
		this.setSize(size);
		this.setPreferredSize(size);
		this.setLocation(position);
		this.setOpaque(false);
	}
	
	public void paintComponent(Graphics g)
	{
		super.paintComponents(g);
		Graphics2D g2d=(Graphics2D) g;

		drawPlayer(g2d);
	} 
	
	private void drawPlayer(Graphics2D g2d)
	{
		// Desenha círculo com o mesmo centro
		double cX=size.getWidth()/2;
		double cY=size.getWidth()/2;
		double raio=25.0/2;
		
		Ellipse2D circ = new Ellipse2D.Double();
		circ.setFrameFromCenter(cX,cY,cX+raio,cY+raio);
		g2d.setColor(Color.BLACK);
		g2d.fill(circ);
	}
}