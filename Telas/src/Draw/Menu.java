package Draw;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

import javax.swing.JButton;
import javax.swing.JPanel;

import Game.Cor;

public class Menu extends JPanel {
	JButton b1 = new JButton("oi");
	public Menu() 
	{
		setPos();
	}
	
	public void setPos()
	{
		this.setSize(new Dimension(400, 400));
		this.setPreferredSize(new Dimension(400, 400));
		this.setLocation(200, 200);
		this.setBackground(Color.BLACK);
		this.setOpaque(false);
	}
	
	public void paintComponent(Graphics g)
	{
		super.paintComponents(g);
		Graphics2D g2d=(Graphics2D) g;
		double leftX=0.0;
		double topY=0.0;
		double larg=400.0;
		double alt=400.0;

		add(b1);
		
		Rectangle2D rt1=new Rectangle2D.Double(leftX,topY,larg,alt);
		g2d.setPaint(Color.BLACK);
		g2d.fill(rt1);
	} 

}
