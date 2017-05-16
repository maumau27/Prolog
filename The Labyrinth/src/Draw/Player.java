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
	private Dimension size = Grid.tileSize;
	public static Dimension playerBounds = new Dimension((MainFrame.LARG_DEFAULT / 10) * 8, (MainFrame.ALT_DEFAULT / 10) * 8);
	
	public Player()
	{
		setPos();
	}
	
	public void setPos()
	{
		this.setLayout(null);
		this.setSize(size);
		this.setPreferredSize(size);
		this.setOpaque(false);
	}
	
	public void paintComponent(Graphics g)
	{
		super.paintComponents(g);
		Graphics2D g2d=(Graphics2D) g;
		
		Point player = MainFrame.t.getPlayerPos();
		position = player;
		position.x = (int) ((position.getX() * size.getWidth()) + Grid.deslocamento.x);
		position.y = (int) ((position.getY() * size.getHeight()) + Grid.deslocamento.y);
		this.setLocation(position);

		drawPlayer(g2d);
	} 
	
	private void drawPlayer(Graphics2D g2d)
	{
		// Desenha c�rculo com o mesmo centro
		double cX=size.getWidth()/2;
		double cY=size.getWidth()/2;
		double raio=25.0/2;
		
		Ellipse2D circ = new Ellipse2D.Double();
		circ.setFrameFromCenter(cX,cY,cX+raio,cY+raio);
		g2d.setColor(Color.BLACK);
		g2d.fill(circ);
	}
}