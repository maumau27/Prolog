package Draw;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.Rectangle2D;

import javax.swing.JPanel;

public class Tile extends JPanel{
		
	private Point position;
	private Dimension size = new Dimension(50, 50);
	
	public Tile(Point p)
	{
		position = p;
		position.x = (int) (position.getX() * size.getWidth());
		position.y = (int) (position.getY() * size.getHeight());
		size.height = (int) (size.getHeight() + 1);
		size.width = (int) (size.getWidth() + 1);
		
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

		drawTile(g2d);
	} 
	
	private void drawTile(Graphics2D g2d)
	{
		Rectangle2D rectangle=new Rectangle2D.Double(0, 0, size.width - 1, size.height - 1);
		g2d.draw(rectangle);
	}
}
