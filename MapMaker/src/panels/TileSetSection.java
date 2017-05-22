package panels;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

import javax.swing.JPanel;

import managers.Controller;

public class TileSetSection extends JPanel{
	
	private Point position = new Point(0, 0);
	private Dimension size = new Dimension(Controller.mainFrame.LARG_DEFAULT / 3 , Controller.mainFrame.ALT_DEFAULT);
	
	public TileSetSection()
	{
		setPos();
	}
	
	public void setPos()
	{
		this.setLayout(null);
		this.setSize(size);
		this.setPreferredSize(size);
		this.setLocation(position);
		this.setOpaque(false);
		
		addMouseWheelListener(new MouseWheelListener() {
			public void mouseWheelMoved(MouseWheelEvent e) {
				
			}
		});
	}
	
	public void paintComponent(Graphics g)
	{
		Graphics2D g2d=(Graphics2D) g;
		
		int posX = position.x;
		int posY = position.y;
		int larg = size.width;
		int alt = size.height;
		
		Rectangle2D rectangle=new Rectangle2D.Double(posX, posY, larg-1, alt-1);
		g2d.setColor(Color.blue);
		g2d.draw(rectangle);
	} 
	
	public Dimension getSize()
	{
		return size;
	}
}
