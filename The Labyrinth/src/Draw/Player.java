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
	
	public static Point position;
	public static Dimension playerBounds = new Dimension(MainFrame.tileSize.width * 3, MainFrame.tileSize.height * 3);
	
	public Player()
	{
		setPos();
	}
	
	public void setPos()
	{
		this.setLayout(null);
		this.setSize(MainFrame.tileSize);
		this.setPreferredSize(MainFrame.tileSize);
		this.setOpaque(false);
	}
	
	public void paintComponent(Graphics g)
	{
		super.paintComponents(g);
		Graphics2D g2d=(Graphics2D) g;
		
		Point player = MainFrame.t.getPlayerPos();
		position = player;
		position.x = (int) ((position.getX() * MainFrame.tileSize.getWidth()) + MainFrame.deslocamento.x);
		position.y = (int) ((position.getY() * MainFrame.tileSize.getHeight()) + MainFrame.deslocamento.y);
		this.setLocation(position);

		drawPlayer(g2d);
		
		repaint();
	} 
	
	private void drawPlayer(Graphics2D g2d)
	{
		// Desenha círculo com o mesmo centro
		double cX=MainFrame.tileSize.getWidth()/2;
		double cY=MainFrame.tileSize.getWidth()/2;
		double raio=MainFrame.tileSize.getWidth()/4;
		
		Ellipse2D circ = new Ellipse2D.Double();
		circ.setFrameFromCenter(cX,cY,cX+raio,cY+raio);
		g2d.setColor(Color.BLACK);
		g2d.fill(circ);
	}

	
}