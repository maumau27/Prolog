package Draw;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

import javax.swing.JPanel;

public class Grid extends JPanel{
		
	private Point position;

	public Grid()
	{
		setPos();
	}
	
	public void setPos()
	{
		this.setLayout(null);
		this.setSize(MainFrame.gridSize);
		this.setPreferredSize(MainFrame.gridSize);
		this.setLocation(MainFrame.deslocamento);
		this.setOpaque(false);
		
		addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				System.out.println("X : " + (e.getX() / MainFrame.tileSize.width));
				System.out.println("Y : " + (e.getY() / MainFrame.tileSize.height));
				MainFrame.t.goTo(new Point(e.getX() / MainFrame.tileSize.width, e.getY() / MainFrame.tileSize.height));
			}
		});
	}
	
	public void paintComponent(Graphics g)
	{
		super.paintComponents(g);
		Graphics2D g2d=(Graphics2D) g;
		
		ArrayList<Point> tile_points = MainFrame.t.getTiles();
		
		for (Point point : tile_points) {
			drawTile(g2d, point, MainFrame.tileSize);
		}
	} 
	
	private void drawTile(Graphics2D g2d, Point pos, Dimension size)
	{
		int posX = pos.x * size.width;
		int posY = pos.y * size.height;
		int larg = size.width;
		int alt = size.height;
		
		Rectangle2D rectangle=new Rectangle2D.Double(posX, posY, larg, alt);
		g2d.draw(rectangle);
	}
	
	private void drawPlayerBounds(Graphics2D g2d)
	{
		int posX = Player.position.x - MainFrame.deslocamento.x - MainFrame.tileSize.width;
		int posY = Player.position.y - MainFrame.deslocamento.y - MainFrame.tileSize.height;
		
		int larg = Player.playerBounds.width;
		int alt = Player.playerBounds.height;
		
		Rectangle2D rectangle=new Rectangle2D.Double(posX, posY, larg, alt);
		g2d.setColor(Color.RED);
		g2d.draw(rectangle);
	}
	
}
