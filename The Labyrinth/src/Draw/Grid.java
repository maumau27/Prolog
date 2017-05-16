package Draw;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

import javax.swing.JPanel;

public class Grid extends JPanel{
		
	private Point position;
	public static Dimension gridSize;
	public static Dimension tileSize; 
	public static Point deslocamento;
	
	public Grid()
	{
		tileSize = new Dimension(MainFrame.t.getTileSize(), MainFrame.t.getTileSize());
		gridSize = new Dimension((MainFrame.t.getMapSize().x + 1) * tileSize.width + 1, (MainFrame.t.getMapSize().y + 1) * tileSize.height + 1);
		deslocamento = new Point((MainFrame.LARG_DEFAULT / 2) - (gridSize.width / 2), (MainFrame.ALT_DEFAULT / 2) - (gridSize.height / 2));
		System.out.println(gridSize);
		setPos();
	}
	
	public void setPos()
	{
		this.setLayout(null);
		this.setSize(gridSize);
		this.setPreferredSize(gridSize);
		this.setLocation(deslocamento);
		this.setOpaque(false);
	}
	
	public void paintComponent(Graphics g)
	{
		super.paintComponents(g);
		Graphics2D g2d=(Graphics2D) g;
		
		ArrayList<Point> tile_points = MainFrame.t.getTiles();
		
		for (Point point : tile_points) {
			drawTile(g2d, point, tileSize);
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
}
