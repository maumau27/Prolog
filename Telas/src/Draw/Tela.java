package Draw;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

import javax.swing.JPanel;

public class Tela extends JPanel{
	
	private Color c1 = Color.BLACK;
	
	public Tela()
	{
		setPos();
	}
	
	public Tela(Color c1)
	{
		this.c1 = c1;
		setPos();
	}
	
	public void setPos()
	{
		this.setSize(new Dimension(800, 800));
		this.setPreferredSize(new Dimension(800,800));
		this.setLocation(0, 0);
		this.setBackground(Color.BLACK);
		this.setOpaque(false);
	}
	
	public void paintComponent(Graphics g)
	{
		super.paintComponents(g);
		Graphics2D g2d=(Graphics2D) g;
		
		/*// Desenha retângulo
		double leftX=0.0;
		double topY=0.0;
		double larg=400.0;
		double alt=400.0;
		Rectangle2D rt1=new Rectangle2D.Double(leftX,topY,larg,alt);
		g2d.setPaint(c1);
		g2d.fill(rt1);*/

	} 
}
